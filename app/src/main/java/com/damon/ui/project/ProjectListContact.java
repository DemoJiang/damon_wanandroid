package com.damon.ui.project;

import com.damon.base.presenter.IPresenter;
import com.damon.base.view.IView;
import com.damon.core.bean.ProjectListData;

import java.util.List;

public interface ProjectListContact {

    interface View extends IView{
        void onShowProjectListData(List<ProjectListData> projectListData);
    }


    interface Present extends IPresenter<ProjectListContact.View>{
        void initShowProjectListData();
    }
}
