package com.damon.ui.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.damon.R;
import com.damon.base.fragment.MVPBaseFragment;
import com.damon.config.Constants;
import com.damon.core.bean.ProjectListData;
import com.damon.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

public class ProjectListFragment extends MVPBaseFragment<ProjectListPresent> implements ProjectListContact.View {
    @BindView(R.id.id_tv_tab)
    TextView mTVTabName;

    private int mId;
    private String mTabStr;

    public static ProjectListFragment getInstance(int id, String param) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.PROJECTLIST_ID, id);
        args.putString(Constants.PROJECTLIST_STR, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void inject() {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onAttachView() {
        super.onAttachView();
        Bundle bundle = getArguments();
        mId = bundle.getInt(Constants.PROJECTLIST_ID);
        mTabStr = bundle.getString(Constants.PROJECTLIST_STR);
        mPresenter.initShowProjectListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    public void onShowProjectListData(List<ProjectListData> projectListData) {
        mTVTabName.setText(mTabStr);
    }
}
