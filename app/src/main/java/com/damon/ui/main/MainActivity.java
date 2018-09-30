package com.damon.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.damon.R;
import com.damon.base.activity.BaseActivity;
import com.damon.helper.FragmentHelper;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private List<Fragment> mFragmentList;
    private FragmentHelper mFragmentHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onAttachView() {
        mFragmentList = new ArrayList<>();
        initFragment();
        mFragmentHelper = new FragmentHelper(getSupportFragmentManager(),R.id.id_fl_contentView,mFragmentList);
        mFragmentHelper.addAndShowFragment(0);
    }

    /**
     * 初始化 fragment
     */
    private void initFragment() {
    }

    /**
     * 底部导航 item 点击回调
     *
     * @param item item对象
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_main_pager:
                mFragmentHelper.showCurrentFragment(0);
                break;
            case R.id.tab_knowledge_hierarchy:
                mFragmentHelper.showCurrentFragment(1);
                break;
            case R.id.tab_navigation:
                mFragmentHelper.showCurrentFragment(2);
                break;
            case R.id.tab_project:
                mFragmentHelper.showCurrentFragment(3);
                break;
        }
        return true;
    }
}
