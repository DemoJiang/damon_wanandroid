package com.damon.ui.project;

import android.util.Log;

import com.damon.R;
import com.damon.base.fragment.MVPBaseFragment;
import com.damon.core.bean.ProjectTabData;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


/**
 * @author: DamonJiang
 * @date: 2018/9/30 0030
 * @description:
 */
public class ProjectFragment extends MVPBaseFragment<ProjectPresent> implements ProjectContact.View {
    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;
    private List<ProjectTabData> mProjectTabData;
    private List<ProjectListFragment> mFragments = new ArrayList<>();

    public static ProjectFragment getInstance() {
        ProjectFragment fragment = new ProjectFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }


    @Override
    protected void inject() {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onAttachView() {
        super.onAttachView();
        mPresenter.initProjectTabData();

    }

    @Override
    public void onShowProjectTabData(List<ProjectTabData> projectTabData) {
        this.mProjectTabData = projectTabData;
        initView();
    }


    /**
     * 设置viewpage和tablayout
     */
    private void initView() {
        for (ProjectTabData data : mProjectTabData) {
            ProjectListFragment projectListFragment = ProjectListFragment.getInstance(data.getId(), data.getName());
            mFragments.add(projectListFragment);
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mProjectTabData == null ? 0 : mProjectTabData.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mProjectTabData.get(position).getName();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }
}
