package com.damon.ui.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.damon.R;
import com.damon.utils.CommonUtils;
import com.damon.widget.StateLayout;


/**
 * @author: DamonJiang
 * @date: 2018/10/12 0012
 * @description:
 */
public class StateActivity extends AppCompatActivity implements View.OnClickListener {
    StateLayout stateLayout;
    LinearLayout mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        mView = findViewById(R.id.id_ll);
        findViewById(R.id.id_btn_lodding).setOnClickListener(this);
        findViewById(R.id.id_btn_content).setOnClickListener(this);
        findViewById(R.id.id_btn_empty).setOnClickListener(this);
        findViewById(R.id.id_btn_errow).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        StateLayout.Builder mStateLayout = new StateLayout.Builder(this);
        stateLayout = mStateLayout.initPage(mView)
                .setOnRetryListener(new StateLayout.Builder.OnRetryClickListener() {
                    @Override
                    public void onClick() {
                        CommonUtils.showToast(StateActivity.this,"重新加载数据");
                        stateLayout.showLoading();
                        new Handler().postDelayed(()->stateLayout.hideContent(), 3000);
                    }
                })
                .create();

        switch (view.getId()) {

            case R.id.id_btn_lodding:

                stateLayout.showLoading();

                new Handler().postDelayed(()->stateLayout.hideContent(), 3000);

                break;
            case R.id.id_btn_content:

                break;
            case R.id.id_btn_empty:
                stateLayout.showEmpty();
                break;
            case R.id.id_btn_errow:
                stateLayout.showError();
                break;
        }
    }
}
