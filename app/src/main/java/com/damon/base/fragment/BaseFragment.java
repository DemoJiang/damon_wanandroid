package com.damon.base.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.damon.app.App;
import com.damon.base.view.IView;
import com.damon.di.component.DaggerFragmentComponent;
import com.damon.di.component.FragmentComponent;
import com.damon.utils.CommonUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: DamonJiang
 * @date: 2018/9/26 0026
 * @description: Fragment 超类
 */
public abstract class BaseFragment extends Fragment implements IView {
    private Unbinder unBinder;
    protected FragmentComponent fragmentComponent;

    protected abstract int getLayoutId();              // 初始化 UI

    protected abstract void onAttachView();            // 注入 view

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        unBinder = ButterKnife.bind(this, view);
        // 依赖注入
        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build();

        onAttachView();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }

    @Override
    public void onShowError(String errorMsg) {
        CommonUtils.showToast(this.getActivity(), errorMsg);
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onDissLoading() {

    }
}
