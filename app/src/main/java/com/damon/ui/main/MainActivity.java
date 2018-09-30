package com.damon.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.damon.R;
import com.damon.base.activity.BaseActivity;
import com.damon.helper.BottomNavigationViewHelper;
import com.damon.helper.FragmentHelper;
import com.damon.ui.home.HomeFragment;
import com.damon.ui.knowledge.KnowFragment;
import com.damon.ui.navigation.NavigationFragment;
import com.damon.ui.project.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.id_bnv_tab)
    BottomNavigationView mBottomNavigationView;

    private List<Fragment> mFragmentList;
    private FragmentHelper mFragmentHelper;
    private HomeFragment mHomeFragment;
    private KnowFragment mKnowFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onAttachView() {
        // 当tab个数大于 3 时处理切换动画
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        initFragment();
        mFragmentHelper = new FragmentHelper(getSupportFragmentManager(),R.id.id_fl_contentView,mFragmentList);
        mFragmentHelper.addAndShowFragment(0);
    }

    /**
     * 初始化 fragment 并加入集合
     */
    private void initFragment() {
        mFragmentList = new ArrayList<>();

        mHomeFragment = HomeFragment.getInstance();
        mKnowFragment = KnowFragment.getInstance();
        mNavigationFragment = NavigationFragment.getInstance();
        mProjectFragment = ProjectFragment.getInstance();

        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mKnowFragment);
        mFragmentList.add(mNavigationFragment);
        mFragmentList.add(mProjectFragment);
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
