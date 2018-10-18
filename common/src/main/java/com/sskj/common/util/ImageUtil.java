/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.sskj.common.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sskj.common.base.App;
import com.sskj.common.base.GlideApp;
import com.sskj.common.box.glidetransform.GlideRoundTransformCenterCrop;


public class ImageUtil {

    private static RequestOptions optionsRound;
    private static RequestOptions optionsNormal;
    private static RequestOptions optionsCircle;
    static { optionsRound = new RequestOptions().transform(new GlideRoundTransformCenterCrop(20)); }
    static { optionsCircle = new RequestOptions().circleCrop(); }
    static { optionsNormal = new RequestOptions(); }


    /**
     * 设置圆形图片
     *
     * @param url
     * @param imageView
     */
    public static void setCircleImage(Object url, ImageView imageView) {
        GlideApp.with(App.INSTANCE).load(url).apply(optionsCircle).into(imageView);
    }

    /**
     * 返回圆形图片Drawable
     *
     * @param url
     * @param drawableBack
     */
    public static void setCircleImageReady(Object url, DrawableBack drawableBack) {
        GlideApp.with(App.INSTANCE).load(url).apply(optionsCircle).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                drawableBack.back(resource);
            }
        });
    }

    /**
     * 设置图片
     *
     * @param url
     * @param imageView
     */
    public static void setImage(Object url, ImageView imageView) {
        GlideApp.with(App.INSTANCE).load(url).apply(optionsNormal).into(imageView);

    }

    /**
     * 设置圆角矩形图片
     *
     * @param url
     * @param imageView
     */
    public static void setRoundImage(Object url, ImageView imageView) {
        GlideApp.with(App.INSTANCE).load(url).apply(optionsRound).into(imageView);
    }

//    /**
//     * 模糊图片
//     * @param url
//     * @param imageView
//     * @param radius
//     */
//    public static void flurImage(Object url, ImageView imageView, int radius) {
//        GlideApp.with(App.INSTANCE).load(url)
//                .apply(bitmapTransform(new BlurTransformation(radius)))
//                .into(imageView);
//    }

    /**
     * 返回圆形图片Bitmap
     *
     * @param url
     * @param bitmapBack
     */
    public static void bitmapCircle(Object url, BitmapBack bitmapBack) {
        GlideApp.with(App.INSTANCE)
                .asBitmap()
                .load(url)
                .apply(optionsCircle)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        bitmapBack.back(resource);
                    }
                });
    }

    public interface BitmapBack {
        void back(Bitmap bitmap);
    }

    public interface DrawableBack {
        void back(Drawable drawable);
    }
}
