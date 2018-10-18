package com.sskj.k3dlib.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sskj.common.base.AppManager;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.k3dlib.R;
import com.sskj.lib.loadprogress.ProgressDialogs;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Date;
import java.util.Stack;

import butterknife.ButterKnife;


/**
 * Created by Lee on 2018/1/25 0025.
 */

public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IBaseView {
    public T mPresenter;
    public BaseActivity mActivity;
    private long oldTime = 0;
    private ProgressDialogs mProgressDialogs;
    private boolean mActivityIsActive;
    private int mRequestCount;
    boolean IS_ORIENTATION_PORTRAIT = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getOrientation()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mProgressDialogs = new ProgressDialogs(this);
        setContentView(getLayoutResId());

        mActivity = this;
        mPresenter = getPresenter();
        mPresenter.attachView(this, this);
        initView();
        initData();
        View back = findViewById(R.id.ivBack);
        if (back != null) {
            ClickUtil.click(back, this::onBackPressed);
        }
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        TextView tvTitle = findViewById(R.id.tvTitle);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.cancelRequest();
        mPresenter.detachView();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        mActivityIsActive = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mActivityIsActive = false;
        super.onPause();
    }

    /**
     * 初始化布局ID
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化当前View 的 presenter
     *
     * @return
     */
    public abstract T getPresenter();

    @Override
    public void onBackPressed() {
        Stack<Activity> allActivities = AppManager.getAppManager().getAllActivities();
        if (allActivities.size() <= 1) {
            long nowTime = new Date().getTime();
            if (nowTime - oldTime <= 2000) {
                AppManager.getAppManager().AppExit(this);
            } else {
                oldTime = nowTime;
                ToastUtil.showShort("再次点击退出程序");
            }
        } else {
            super.onBackPressed();
        }
    }

    public void showLoading() {
        if (mActivityIsActive && !isFinishing()) {
            if (mProgressDialogs != null && mRequestCount <= 0) {
                mRequestCount = 1;
                mProgressDialogs.showDialog();
            } else {
                ++mRequestCount;
            }
        }
    }

    public void hideLoading() {
        if (mRequestCount <= 1) {
            mRequestCount = 0;
            mProgressDialogs.closeDialog();
        } else {
            --mRequestCount;
        }
    }

    public boolean getOrientation() {
        return IS_ORIENTATION_PORTRAIT;
    }
}
