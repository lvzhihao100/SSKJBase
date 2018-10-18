package com.sskj.common.box.androidinput;

import com.github.yoojia.inputs.Input;
import com.github.yoojia.inputs.MessageDisplay;
import com.sskj.common.util.ToastUtil;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-05 09:29
 */
public class ToastMessageDisplay implements MessageDisplay {
    @Override
    public void show(Input input, String message) {
        ToastUtil.showShort(message);

    }
}
