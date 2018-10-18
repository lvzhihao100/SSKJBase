package com.sskj.k3dlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulong.rudeness.RudenessScreenHelper;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Created by Lee on 2018/2/28 0028.
 */

public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IBaseView {

    protected P mPresenter;
    private Unbinder mUnbinder;
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RudenessScreenHelper.resetDensity(getContext(), 750);
        if (inflate == null) {
            inflate = inflater.inflate(getLayoutId(), null);
            mPresenter = getPresenter();
            mPresenter.attachView(this, getActivity());
            mUnbinder = ButterKnife.bind(this, inflate);
            initView();
            initData();
        }
        if (inflate.getParent() != null) {
            ((ViewGroup) inflate.getParent()).removeView(inflate);
        }
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.cancelRequest();
        }
        mUnbinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    public void initView() {
    }

    public void initData() {
    }
}
