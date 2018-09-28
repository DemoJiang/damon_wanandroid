package com.damon.base.activity;


import android.util.Log;

import com.damon.base.presenter.IPresenter;
import com.damon.base.view.IView;

import javax.inject.Inject;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description: mvp 模式下的 Activity 超类
 * <p>
 * 作用：沉淀 view 的公共回调函数，提供给子类使用。
 * 约束：所有 mvp 下的 activity 都必须实现该类，并明确指定泛型 presenter，
 * 使用 dagger2 注解将 p 注入到 view 中，子类直接使用 mPresenter 即可。
 * </P>
 */
public abstract class MVPBaseActivity<T extends IPresenter> extends BaseActivity implements IView {
    private static final String TAG = "MVPBaseActivity";
    @Inject
    protected T mPresenter;
    protected abstract void inject();      // Dageer 注入

    /**
     * 将 view 注入到 presenter
     */
    @Override
    protected void onAttachView() {
        inject();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }

    /**
     * 销毁当前页面时清除 View 层持有的 Presenter层对象，防止内存泄露
     */
    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
            System.gc();
        }
        super.onDestroy();
    }
}
