package com.sskj.k3dlib.http;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.k3dlib.base.IPresenter;
import com.sskj.k3dlib.router.provider.LogoutProvider;
import com.sskj.k3dlib.util.ExceptionUtil;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public abstract class JsonCallBack<T> extends AbsCallback<T> {


    private IPresenter mBasePresenter;

    private boolean showDialog = true;
    private boolean isShowToast = true;
    private int dealCode = 0;

    public JsonCallBack(IPresenter basePresenter) {
        this.mBasePresenter = basePresenter;
        showDialog = true;
    }

    public JsonCallBack(IPresenter basePresenter, int dealCode) {
        this.mBasePresenter = basePresenter;
        showDialog = true;
        this.dealCode = dealCode;
    }

    public JsonCallBack(boolean isShowToast) {
        this.isShowToast = isShowToast;
    }


    public JsonCallBack(IPresenter basePresenter, boolean isShow) {
        this.mBasePresenter = basePresenter;
        showDialog = isShow;
    }

    public JsonCallBack() {
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (mBasePresenter != null) {
            if (showDialog) {
                mBasePresenter.showLoading();
            }
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (mBasePresenter != null) {
            mBasePresenter.hideLoading();
        }
    }


    @Override
    public T convertResponse(okhttp3.Response response) throws JsonParseException {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        try {
            String jsonBody = body.string();
            jsonBody = jsonBody.replace("\"data\":\"\"}", "\"data\":null}");
            Gson gson = GSonUtil.gson;
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            data = gson.fromJson(jsonBody, type);
        } catch (IOException e) {
            throw new JsonParseException();
        } catch (JsonSyntaxException e) {
            throw new JsonParseException();
        }
        int status = 0;
        String msg = "";
        if (data instanceof HttpData) {
            status = ((HttpData) data).getStatus();
            msg = ((HttpData) data).getMsg();
        }
        if (status == 404 || status == 505) {
            ToastUtil.showShort(msg);
            LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
            logoutProvider.logout();
        }
        return data;
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        if (mBasePresenter != null) {
            mBasePresenter.hideLoading();
        }
        if (!isShowToast) {
            return;
        }
        ExceptionUtil.dealException(response.getException());
    }
}
