package com.sskj.common.box.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerHorizenLineItemDecoration extends RecyclerView.ItemDecoration {


    //分割线画笔
    private Paint mPaint;

    //分割线距离下边距离
    private int bottomPadding = 0;
    //分割线距离上边距离
    private int topPadding = 0;

    private boolean isFirstDraw = true;
    private boolean isLastDraw = true;
    private int paintWidth = 1;
    private int paintColor = 0xfff3f3f3;


    public DividerHorizenLineItemDecoration setPaintWidth(int paintWidth) {
        this.paintWidth = paintWidth;
        mPaint.setStrokeWidth(paintWidth);
        return this;
    }

    public DividerHorizenLineItemDecoration setPaintColor(int paintColor) {
        this.paintColor = paintColor;
        mPaint.setColor(paintColor);
        return this;
    }

    public DividerHorizenLineItemDecoration setFirstDraw(boolean firstDraw) {
        isFirstDraw = firstDraw;
        return this;
    }

    public DividerHorizenLineItemDecoration setLastDraw(boolean lastDraw) {
        isLastDraw = lastDraw;
        return this;
    }

    public DividerHorizenLineItemDecoration setBottomPadding(int bottomPadding) {
        this.bottomPadding = bottomPadding;
        return this;
    }

    public DividerHorizenLineItemDecoration setTopPadding(int topPadding) {
        this.topPadding = topPadding;
        return this;
    }

    public DividerHorizenLineItemDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(paintWidth);
        mPaint.setColor(paintColor);
        mPaint.setAntiAlias(true);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (!isFirstDraw && position == 0) {
            } else {
                c.drawLine(view.getLeft(), view.getTop() + topPadding, view.getLeft(), bottom - bottomPadding, mPaint);
            }
            if (isLastDraw && parent.getAdapter().getItemCount() == position + 1) {
                c.drawLine(view.getRight(), view.getTop() + topPadding, view.getRight(), bottom - bottomPadding, mPaint);
            }
        }
    }

    /**
     * 设置item分割线的size
     *
     * @param outRect outRect
     * @param view    view
     * @param parent  parent
     * @param state   state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        outRect.set(!isFirstDraw && position == 0 ? 0 : paintWidth, 0, isLastDraw && parent.getAdapter().getItemCount() == position + 1 ? paintWidth : 0, 0);

    }
}