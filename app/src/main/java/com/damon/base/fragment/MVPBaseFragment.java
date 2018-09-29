package com.damon.base.fragment;


import com.damon.base.presenter.IPresenter;

import javax.inject.Inject;

/**
 * @author: DamonJiang
 * @date: 2018/9/26 0026
 * @description:  mvp 模式下的 fragment 基类
 */
public abstract class MVPBaseFragment<T extends IPresenter> extends BaseFragment{
    @Inject
    protected T mPresenter;
    protected abstract void inject();      // Dageer 注入

    @Override
    protected void onAttachView() {
        inject();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }
}
