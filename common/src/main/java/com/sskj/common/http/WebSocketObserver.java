package com.sskj.common.http;

/*
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-07-26 11:31
 */

import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


public abstract class WebSocketObserver extends Observable<String> {
    private String wsUrl;
    private Listener listener;
    private final OkHttpClient okHttpClient;
    private final Request request;

    public WebSocketObserver(String wsUrl) {
        this.wsUrl = wsUrl;
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(13, TimeUnit.SECONDS).build();
        request = new Request.Builder()
                .url(wsUrl)
                .build();
    }

    public abstract void onOpen(WebSocket webSocket, Response response);

    @Override
    protected void subscribeActual(Observer<? super String> observer) {
        listener = new Listener(observer, wsUrl);
        observer.onSubscribe(listener);

    }

    public void send(String msg) {
        if (listener != null && listener.getWebSocket() != null) {
            listener.getWebSocket().send(msg);
        }
    }

    final class Listener extends MainThreadDisposable {
        private WebSocket mWebSocket;
        private Observer<? super String> observer;
        private boolean isClose;

        WebSocket getWebSocket() {
            return mWebSocket;
        }

        Listener(Observer<? super String> observer, String wsUrl) {
            this.observer = observer;
            connect();
        }

        private void connect() {
            isClose = false;
            mWebSocket = okHttpClient.newWebSocket(request, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {
                    super.onOpen(webSocket, response);
                    WebSocketObserver.this.onOpen(webSocket, response);
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {
                    super.onMessage(webSocket, text);
                    System.out.println(text);
                    observer.onNext(text);
                }

                @Override
                public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
                    super.onFailure(webSocket, t, response);
                    System.out.println("长连接失败" + t.toString());
                    if (!isClose) {
                        reConnect();
                    }

                }
                private void reConnect() {
                    Flowable.timer(15, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.io())
                            .subscribe(i -> {
                                connect();
                            });
                }
                @Override
                public void onClosed(WebSocket webSocket, int code, String reason) {
                    super.onClosed(webSocket, code, reason);
                    System.out.println("长连接关闭" + reason);
                    if (code != 1000 && !"主动关闭".equals(reason)) {
                        reConnect();
                    }
                }
            });
        }

        @Override
        protected void onDispose() {
            if (mWebSocket != null) {
                isClose = true;
                mWebSocket.close(1000, "主动关闭");
                mWebSocket = null;
            }
        }
    }


}