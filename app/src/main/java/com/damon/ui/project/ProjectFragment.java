package com.damon.ui.project;

import android.content.Context;

import com.damon.R;
import com.damon.base.fragment.MVPBaseFragment;
import com.damon.core.bean.ProjectTabData;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


/**
 * @author: DamonJiang
 * @date: 2018/9/30 0030
 * @description:
 */
public class ProjectFragment extends MVPBaseFragment<ProjectPresent> implements ProjectContact.View {
    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;

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
        
    }

    @Override
    public void onShowProjectTabData(List<ProjectTabData> projectTabData) {

    }
}
