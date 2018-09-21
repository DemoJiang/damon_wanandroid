package com.damon.login;

import com.damon.R;
import com.damon.base.BaseActivity;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description:
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        // ...

        // 初始化完控件后开始访问网络获取数据

        mPresenter.initLoginData();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onDissLoading() {

    }
}
