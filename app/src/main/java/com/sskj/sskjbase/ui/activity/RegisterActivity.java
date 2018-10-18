package com.sskj.sskjbase.ui.activity;


import com.sskj.sskjbase.R;
import com.sskj.sskjbase.presenter.RegisterActivityPresenter;
import com.sskj.k3dlib.base.BaseActivity;


public class RegisterActivity extends BaseActivity<RegisterActivityPresenter> {
    @Override
    protected int getLayoutResId() {
        return R.layout.app_activity_register;
    }

    @Override
    public RegisterActivityPresenter getPresenter() {
        return new RegisterActivityPresenter();
    }
}
