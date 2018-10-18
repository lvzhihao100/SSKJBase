package com.sskj.common.box.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sskj.common.util.NumberUtil;

import java.lang.reflect.Type;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：string类型注解适配器,反序列化时将null，“”，转化为“0”，如果是数字类型，保留四位,四舍五入
 * 创建时间：2018-08-03 10:07
 */
public class StringKeep4Adapter implements JsonSerializer<String>, JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为double类型,如果后台返回""或者null,则返回0.00
                return "0";
            } else {
                if (NumberUtil.isDecimal(json.getAsString())) {
                    return NumberUtil.keep4(json.getAsString());
                }
            }
        } catch (Exception ignore) {
        }
        return json.getAsString();
    }

    @Override
    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}