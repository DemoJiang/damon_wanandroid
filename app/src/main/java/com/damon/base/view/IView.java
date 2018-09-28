package com.damon.base.view;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description: mvp 模式下 view 层的抽象出来的接口类，
 * <p>
 * 作用：主要封装一些公共的回调函数，由 BasePresenter 实现该
 * 接口类，BasePresenter 的子类可以根据自己的业务需求进行
 * 继续扩展业务回调函数。
 * </p>
 */
public interface IView {
    void onShowError(String errorMsg);
    void onShowLoading();
    void onDissLoading();
}
