package com.sskj.common.box.imagegetter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-08 09:05
 */
public class URLImageGetter implements Html.ImageGetter {
    private TextView textView;
    private Context context;
    private URLDrawable urlDrawable;

    public URLImageGetter(Context context, TextView textView) {
        this.textView = textView;
        this.context = context;
    }

    @Override
    public Drawable getDrawable(final String source) {
        urlDrawable = new URLDrawable();

        Glide.with(context)
                .asBitmap()
                .load(source)
                .apply(new RequestOptions())
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        urlDrawable.bitmap = resource;
                        urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                        textView.invalidate();
                        textView.setText(textView.getText());//不加这句显示不出来图片，原因不详
                    }
                });

        return urlDrawable;
    }
    public class URLDrawable extends BitmapDrawable {
        public Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }

}
