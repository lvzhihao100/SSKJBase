package com.sskj.sskjbase.ui.activity;


import com.sskj.sskjbase.R;
import com.sskj.sskjbase.presenter.LoginActivityPresenter;
import com.sskj.k3dlib.base.BaseActivity;


public class LoginActivity extends BaseActivity<LoginActivityPresenter> {
    @Override
    protected int getLayoutResId() {
        return R.layout.app_activity_login;
    }

    @Override
    public LoginActivityPresenter getPresenter() {
        return new LoginActivityPresenter();
    }
}
