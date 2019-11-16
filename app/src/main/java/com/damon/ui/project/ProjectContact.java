package com.damon.ui.project;

import com.damon.base.presenter.IPresenter;
import com.damon.base.view.IView;
import com.damon.core.bean.ProjectTabData;

import java.util.List;

public class ProjectContact {

    interface View extends IView {
        void onShowProjectTabData(List<ProjectTabData> projectTabData);
    }

    interface Presenter extends IPresenter<View> {
        void initProjectTabData();           // 加载tab数据
    }
}
