package com.sskj.common.log;

import android.text.InputFilter;

import com.sskj.common.util.JsonUtil;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-10 16:23
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> HTTP FAILD")) {
            LogUtil.d(message);
            return;
        }
        if (message.startsWith("--> POST") || message.startsWith("--> GET")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtil.formatJson(message);
        }
        mMessage.append(message.concat("\n"));
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP") || message.startsWith("--> END GET")) {
            LogUtil.d(mMessage.toString());
        }
    }
}