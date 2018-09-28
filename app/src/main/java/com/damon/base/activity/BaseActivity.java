package com.damon.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.damon.app.App;
import com.damon.base.view.IView;
import com.damon.di.component.ActivityComponent;
import com.damon.di.component.DaggerActivityComponent;
import com.damon.utils.CommonUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description: activity 超类
 */
public abstract class BaseActivity extends AppCompatActivity  implements IView {

    private Unbinder unbinder;
    protected ActivityComponent activityComponent;

    protected abstract int getLayoutId();              // 初始化 UI

    protected abstract void onAttachView();            // 注入 view

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        // 依赖注入
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build();

        onAttachView();

        //绑定初始化 ButterKnife
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }


    /**
     * 不带返回码的页面跳转,
     * 如果是不需要传参，则 bundle 传 null 即可
     *
     * @param clazz
     * @param bundle
     */
    public void startActivity(Class<?> clazz, Bundle bundle) {
        try {
            Intent intent = new Intent(this, clazz);
            if (bundle != null) intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 带返回码的页面跳转,
     * 如果是不需要传参，则 bundle 传 null 即可
     *
     * @param clazz
     * @param bundle
     */
    public void startActivityForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        try {
            Intent intent = new Intent(this, clazz);
            if (bundle != null) intent.putExtras(bundle);
            startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onShowError(String errorMsg) {
        CommonUtils.showToast(this, errorMsg);
    }

    @Override
    public void onShowLoading() {}

    @Override
    public void onDissLoading() {

    }

}
