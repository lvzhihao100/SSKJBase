package com.sskj.k3dlib.mvchelper;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shizhefei.mvc.ILoadViewFactory;
import com.shizhefei.view.vary.VaryViewHelper;
import com.sskj.k3dlib.R;
import com.sskj.k3dlib.util.ExceptionUtil;


/**
 * Created by vzhihao on 2016/11/24.
 */
public class LoadViewFactory implements ILoadViewFactory {
    @Override
    public ILoadMoreView madeLoadMoreView() {
        return new LoadMoreHelper();
    }

    @Override
    public ILoadView madeLoadView() {
        return new LoadViewHelper();
    }

    private static class LoadMoreHelper implements ILoadMoreView {

        protected TextView footView;

        protected View.OnClickListener onClickRefreshListener;

        @Override
        public void init(FootViewAdder footViewHolder, View.OnClickListener onClickRefreshListener) {
            View contentView = footViewHolder.getContentView();

            Context context = contentView.getContext();
            TextView textView = new TextView(context);
            textView.setTextColor(Color.GRAY);
            textView.setPadding(0, dip2px(context, 16), 0, dip2px(context, 16));
            textView.setGravity(Gravity.CENTER);
            footViewHolder.addFootView(textView);

            footView = textView;
            this.onClickRefreshListener = onClickRefreshListener;
            showNormal();
        }

        @Override
        public void showNormal() {
            footView.setVisibility(View.VISIBLE);

            footView.setText("点击加载更多");
            footView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showLoading() {
            footView.setVisibility(View.VISIBLE);
            footView.setText("正在加载中..");
            footView.setOnClickListener(null);
        }

        @Override
        public void showFail(Exception exception) {
            footView.setVisibility(View.VISIBLE);

            footView.setText("加载失败，点击重新加载");
            footView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showNomore() {
            footView.setVisibility(View.GONE);
            footView.setText("");
            footView.setOnClickListener(null);
        }

    }

    private static class LoadViewHelper implements ILoadView {
        private VaryViewHelper helper;
        private View.OnClickListener onClickRefreshListener;
        private Context context;

        @Override
        public void init(View switchView, View.OnClickListener onClickRefreshListener) {
            this.context = switchView.getContext().getApplicationContext();
            this.onClickRefreshListener = onClickRefreshListener;
            helper = new VaryViewHelper(switchView);
        }

        @Override
        public void restore() {
            helper.restoreView();
        }

        @Override
        public void showLoading() {
            View loadingView = LayoutInflater.from(context).inflate(R.layout.mvc_loading_view, null);
            helper.showLayout(loadingView);
        }

        @Override
        public void tipFail(Exception exception) {
            ExceptionUtil.dealException(exception.getCause());
        }

        @Override
        public void showFail(Exception exception) {
            String msg = ExceptionUtil.getExceptionMessage(exception.getCause());
            Context context = helper.getContext();
            View errorView = LayoutInflater.from(context).inflate(R.layout.mvc_error_view, null);
            errorView.findViewById(R.id.btRetry).setOnClickListener(onClickRefreshListener);
            ((TextView) errorView.findViewById(R.id.tvError)).setText(TextUtils.isEmpty(msg) ? "网络请求错误" : msg);
            helper.showLayout(errorView);
        }

        @Override
        public void showEmpty() {
            Context context = helper.getContext();
            View emptyView = LayoutInflater.from(context).inflate(R.layout.mvc_empty_view, null);
            emptyView.setOnClickListener(onClickRefreshListener);
            emptyView.findViewById(R.id.ivEmpty).setOnClickListener(onClickRefreshListener);
            helper.showLayout(emptyView);
        }

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
