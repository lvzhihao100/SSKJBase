package com.sskj.common.box.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.sskj.common.util.ImageUtil;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-08-08 09:09
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageUtil.setImage(path, imageView);
    }
}