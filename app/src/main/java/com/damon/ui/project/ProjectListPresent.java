package com.damon.ui.project;

import android.util.Log;

import com.damon.base.AYBaseObserver;
import com.damon.base.presenter.BasePresenter;
import com.damon.core.DataManager;
import com.damon.core.bean.BaseResponse;
import com.damon.core.bean.ProjectListData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProjectListPresent extends BasePresenter<ProjectListContact.View> implements ProjectListContact.Present {
    private DataManager mDataManager;

    @Inject
    public ProjectListPresent(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void initShowProjectListData() {
        addSubscribe(mDataManager.getProjectListData(0,294)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new AYBaseObserver<BaseResponse<List<ProjectListData>>>(mView, "获取项目列表失败") {
            @Override
            public void onNext(BaseResponse<List<ProjectListData>> listBaseResponse) {
                Log.d("jk", "onShowProjectListData: =000=====");
                if (mView == null) return;
                if (listBaseResponse.getErrorCode() == 0) {
                    mView.onShowProjectListData(listBaseResponse.getData());
                }else{
                    mView.onShowError(listBaseResponse.getErrorMsg());
                }
            }
        }));
    }
}
