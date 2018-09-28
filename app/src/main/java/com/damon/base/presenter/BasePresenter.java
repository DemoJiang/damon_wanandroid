package com.damon.base.presenter;


import com.damon.base.view.IView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description: presenter 超类
 * <p>
 * 作用：给所有子类提供公共的回调函数，所有具体的 presenter 都要实现这个类，
 * </p>
 */
public class BasePresenter<T extends IView> implements IPresenter<T> {
    private CompositeDisposable compositeDisposable;
    protected T mView;


    @Override
    public void onAttachView(T view) {
        this.mView = view;
    }

    @Override
    public void onDetachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            System.gc();
        }
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

}
