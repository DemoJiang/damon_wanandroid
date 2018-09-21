package com.damon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description:
 */
public abstract class AbstractActivity extends AppCompatActivity{

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }


}
