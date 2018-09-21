package com.damon.base;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description:
 */
public abstract class BaseActivity<T extends AbstractPresenter> extends AbstractActivity implements AbstractView{
    protected T mPresenter;
}
