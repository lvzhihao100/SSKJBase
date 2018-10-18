package com.sskj.k3dlib.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;

/**
 * <pre>
 *     author : wk_math
 *     e-mail : wk_math@163.com
 *     time   : 2018/03/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CopyUtil {
    public static void copy(String content) {
        ClipboardManager cm = (ClipboardManager) App.INSTANCE.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setPrimaryClip(ClipData.newPlainText("", content));
        ToastUtil.showShort("复制成功");
    }
}
