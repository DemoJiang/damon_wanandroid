package com.damon.base;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description:
 */
public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {

    protected T mView;


    @Override
    public void onAttach(T view) {
        this.mView = view;
    }
}
