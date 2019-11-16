package com.damon.ui.home;


import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.damon.R;
import com.damon.adapter.HomePageAdapter;
import com.damon.base.fragment.MVPBaseFragment;
import com.damon.config.Constants;
import com.damon.core.bean.ArticleListData;
import com.damon.core.bean.BannerData;
import com.damon.helper.GlideLoaderHelper;
import com.damon.ui.article.ArticleDetailActivity;
import com.damon.utils.CommonUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author: DamonJiang
 * @date: 2018/9/30 0030
 * @description:
 */
public class HomeFragment extends MVPBaseFragment<HomePresenter> implements HomeContact.View {
    @BindView(R.id.id_rv)
    RecyclerView mRecycleView;
    private Banner mBanner;

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mBanner != null){
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mBanner != null){
            mBanner.stopAutoPlay();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void inject() {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onAttachView() {
        super.onAttachView();
        mBanner = (Banner) getActivity().getLayoutInflater().inflate(R.layout.container_banner,null);
        mBanner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getContext().getResources().getDisplayMetrics().heightPixels / 4));
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mPresenter.initBannerData();
        mPresenter.initArticleData();
    }

    @Override
    public void onShowBannerData(List<BannerData> bannerData) {
        List<String> bannerImageList = new ArrayList<>();     // 轮播图篇片地址
        List<String> bannerTitleList = new ArrayList<>();     // 轮播标题
        List<String> bannerUrlList = new ArrayList<>();       // 轮播图跳转的网页地址
        for(BannerData banner:bannerData){
            bannerImageList.add(banner.getImagePath());
            bannerTitleList.add(banner.getTitle());
            bannerUrlList.add(banner.getUrl());
        }
        mBanner.setImages(bannerImageList);
        mBanner.setBannerTitles(bannerTitleList);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideLoaderHelper());
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3500);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtra(Constants.ARTICLE_LINK,bannerUrlList.get(position));
                intent.putExtra(Constants.ARTICLE_TITLE,bannerTitleList.get(position));
                startActivity(intent);
            }
        });
        mBanner.start();
    }

    @Override
    public void onShowArticleData(ArticleListData articleData) {
        HomePageAdapter homePageAdapter = new HomePageAdapter(R.layout.item_article,articleData.getDatas());
        homePageAdapter.setHeaderView(mBanner);
        homePageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtra(Constants.ARTICLE_LINK,articleData.getDatas().get(position).getLink());
                intent.putExtra(Constants.ARTICLE_TITLE,articleData.getDatas().get(position).getTitle());
                startActivity(intent);
            }
        });
        homePageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_search_pager_like_iv:
                        if(articleData.getDatas().get(position).isCollect()){
                            CommonUtils.showToast(getActivity(),"已取消");
                        }else{
                            CommonUtils.showToast(getActivity(),"已收藏");
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        mRecycleView.setAdapter(homePageAdapter);
    }
}
