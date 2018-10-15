package com.damon.ui.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.damon.R;
import com.damon.widget.PageLayout;


/**
 * @author: DamonJiang
 * @date: 2018/10/12 0012
 * @description:
 */
public class StateActivity extends AppCompatActivity implements View.OnClickListener {
    private PageLayout pageLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.id_btn_lodding).setOnClickListener(this);
        findViewById(R.id.id_btn_content).setOnClickListener(this);
        findViewById(R.id.id_btn_empty).setOnClickListener(this);
        findViewById(R.id.id_btn_errow).setOnClickListener(this);

        pageLayout = new PageLayout.Builder(this)
                .initPage(findViewById(R.id.id_image))
                .setCustomView(LayoutInflater.from(this).inflate(R.layout.layout_empty, null))
                .setOnRetryListener(new PageLayout.OnRetryClickListener() {
                    @Override
                    public void onRetry() {
                        loadData();
                    }


                })
                .create();
        pageLayout.hide();
    }

    private void loadData() {
        pageLayout.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageLayout.hide();
            }
        },2000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_lodding:
                pageLayout.showLoading();
                break;
            case R.id.id_btn_content:
                pageLayout.hide();
                break;
            case R.id.id_btn_empty:
                pageLayout.showEmpty();
                break;
            case R.id.id_btn_errow:
                pageLayout.showError();
                break;
        }
    }
}
