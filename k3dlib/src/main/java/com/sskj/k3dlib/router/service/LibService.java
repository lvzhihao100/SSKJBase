package com.sskj.k3dlib.router.service;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.shizhefei.mvc.MVCHelper;
import com.sskj.common.util.SPUtil;
import com.sskj.k3dlib.SPConfig;
import com.sskj.k3dlib.mvchelper.LoadViewFactory;


@Route(path = "/k3dlib/application")
public class LibService implements IProvider {
    @Override
    public void init(Context context) {
        MVCHelper.setLoadViewFractory(new LoadViewFactory());
        HttpParams httpParams = new HttpParams();
        HttpHeaders httpHeaders = new HttpHeaders();
        OkGo.getInstance().addCommonParams(httpParams);
        OkGo.getInstance().addCommonHeaders(httpHeaders);
    }
}
