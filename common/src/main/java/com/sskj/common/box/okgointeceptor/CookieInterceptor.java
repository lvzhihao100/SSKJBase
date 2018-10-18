package com.sskj.common.box.okgointeceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-13 19:09
 */
public class CookieInterceptor implements Interceptor{
    private final CookieJar cookieJar;

    public CookieInterceptor(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request userRequest = chain.request();
        Request.Builder requestBuilder = userRequest.newBuilder();
        List<Cookie> cookies = cookieJar.loadForRequest(userRequest.url());
        for (Cookie cookie : cookies) {
            requestBuilder.header(cookie.name(), cookie.value());

        }
        return chain.proceed(requestBuilder.build());
    }
}
