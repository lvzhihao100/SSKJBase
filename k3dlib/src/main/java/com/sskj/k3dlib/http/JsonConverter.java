package com.sskj.k3dlib.http;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.sskj.common.base.AppManager;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.k3dlib.router.RConfig;
import com.sskj.k3dlib.router.provider.LogoutProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * Created by lvzhihao on 17-7-4.
 */

public abstract class JsonConverter<T> extends AbsConverter<T> {

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
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
        } catch (Exception e) {
            throw new JsonParseException();
        }
        int status = 0;
        String msg = "";
        if (data instanceof HttpData) {
            status = ((HttpData) data).getStatus();
            msg = ((HttpData) data).getMsg();
        }
        //  根据状态码，统一处理（如用户过期，重新登录）
        if (status == 404 || status == 505) {
            ToastUtil.showShort(msg);
            LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
            logoutProvider.logout();
        }
        return data;
    }

}
