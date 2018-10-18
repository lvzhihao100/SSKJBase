package com.sskj.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-08-03 20:12
 */
public class ViewPagerSlide extends ViewPager {
    private Map<Integer, Integer> map = new HashMap<>(2);
    private int currentPage;
    private boolean isSlide = true;//是否可以进行滑动

    public void setSlide(boolean slide) {
        isSlide = slide;
    }

    public ViewPagerSlide(Context context) {
        super(context);
    }

    public ViewPagerSlide(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isSlide && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide && super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        try {
            if (!map.containsKey(currentPage)) {
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    int h = child.getMeasuredHeight();
                    map.put(i, h);
                }
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(map.get(currentPage), MeasureSpec.EXACTLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * 在切换tab的时候，重置ViewPager的高度
     *
     * @param current
     */
    public void resetHeight(int current) {
        this.currentPage = current;
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        if (map.size() > 0 && map.containsKey(currentPage)) {
            if (params == null) {
                params = new MarginLayoutParams(LayoutParams.MATCH_PARENT, map.get(current));
            } else {
                params.height = map.get(current);
            }
            setLayoutParams(params);
        }
    }

//    /**
//     * 在切换tab的时候，重置ViewPager的高度
//     *
//     * @param current
//     */
//    public void resetHeight(int current, int height) {
//        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
//        if (params == null) {
//            params = new MarginLayoutParams(LayoutParams.MATCH_PARENT, height);
//        } else {
//            params.height = height;
//        }
//        map.put(current,params.height);
//        setLayoutParams(params);
//
//    }

    /**
     * 获取、存储每一个tab的高度，在需要的时候显示存储的高度
     *
     * @param current tab的position
     * @param height  当前tab的高度
     */
    public void addHeight(int current, int height) {
        map.put(current, height);
    }

}