package com.sskj.common.router.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.bulong.rudeness.RudenessScreenHelper;
import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.sskj.common.BuildConfig;
import com.sskj.common.base.App;
import com.sskj.common.base.BaseActivityLifecycleCallbacks;
import com.sskj.common.box.okgointeceptor.CookieInterceptor;
import com.sskj.common.log.HttpLogger;
import com.sskj.common.log.LogUtil;

import java.util.logging.Level;

import okhttp3.OkHttpClient;


@Route(path = "/common/application")
public class CommonService implements IProvider {
    @Override
    public void init(Context context) {
        App.INSTANCE.registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks());
        new RudenessScreenHelper(App.INSTANCE, 750).activate();
        Stetho.initializeWithDefaults(context);
        initOkGoHttp(context);
    }


    private void initOkGoHttp(Context context) {
        LogUtil.init(BuildConfig.DEBUG);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        CookieJarImpl cookieJar = new CookieJarImpl(new SPCookieStore(context));
        builder.cookieJar(cookieJar);
        builder.addInterceptor(new CookieInterceptor(cookieJar));
        setColorfulLog(builder);
        OkGo.getInstance().init(App.INSTANCE)//必须调用初始化
                .setOkHttpClient(builder.build())//必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);
    }

    private void setColorfulLog(OkHttpClient.Builder builder) {
        okhttp3.logging.HttpLoggingInterceptor logInterceptor = new okhttp3.logging.HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logInterceptor);
    }

    private void setWithLog(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
    }

}
