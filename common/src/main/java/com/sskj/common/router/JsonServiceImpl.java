package com.sskj.common.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-08-24 08:26
 */
@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {
    @Override
    public void init(Context context) {

    }

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return null;
    }

    @Override
    public String object2Json(Object instance) {
        return new Gson().toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return new Gson().fromJson(input, clazz);
    }
}