package com.damon.ui.project;

import com.damon.base.AYBaseObserver;
import com.damon.base.presenter.BasePresenter;
import com.damon.core.DataManager;
import com.damon.core.bean.BannerData;
import com.damon.core.bean.BaseResponse;
import com.damon.core.bean.ProjectTabData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProjectPresent extends BasePresenter<ProjectContact.View> implements ProjectContact.Presenter {
    private DataManager mDataManager;

    @Inject
    public ProjectPresent(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void initProjectTabData() {
        addSubscribe(mDataManager.getProjectTabData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new AYBaseObserver<BaseResponse<List<ProjectTabData>>>(mView,"tab 数据获取失败") {
                    @Override
                    public void onNext(BaseResponse<List<ProjectTabData>> data) {
                        if (mView == null) return;
                        if (data.getErrorCode() == 0) {
                            mView.onShowProjectTabData(data.getData());
                        }else{
                            mView.onShowError(data.getErrorMsg());
                        }
                    }
                }));
    }
}
