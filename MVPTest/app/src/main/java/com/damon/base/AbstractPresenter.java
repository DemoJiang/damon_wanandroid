package com.damon.base;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description:
 */
public interface AbstractPresenter<T extends AbstractView> {
    void onAttach(T view);
}
