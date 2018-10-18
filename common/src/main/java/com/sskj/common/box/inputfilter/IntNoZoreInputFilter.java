package com.sskj.common.box.inputfilter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-08-29 19:56
 */
public class IntNoZoreInputFilter implements InputFilter {
    Pattern mPattern;

    public IntNoZoreInputFilter() {
        mPattern = Pattern.compile("([0-9]|)*");
    }

    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String result = dest.toString() + source.toString();
        //验证删除等按键
        if (TextUtils.isEmpty(source)) {
            if (dest.toString().length() > 1) {
                return "";
            } else {
                return "1";
            }
        }
        Matcher matcher = mPattern.matcher(source);
        if (!matcher.matches()) {
            return "";
        }
        if (TextUtils.isEmpty(result) || Long.valueOf(result) == 0) {
            return "1";
        }
        return source;
    }
}
