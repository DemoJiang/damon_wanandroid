package com.damon.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.damon.R;
import com.damon.core.bean.ArticleData;

import java.util.List;

/**
 * @author: DamonJiang
 * @date: 2018/10/9 0009
 * @description:
 */
public class HomePageAdapter extends BaseQuickAdapter<ArticleData,BaseViewHolder> {

    public HomePageAdapter(@Nullable List<ArticleData> data) {
        super(R.layout.item_article, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleData item) {
        helper.setText(R.id.id_tv_author,item.getAuthor())
                .setText(R.id.id_tv_flag,item.getChapterName())
                .setText(R.id.id_tv_title,item.getTitle())
                .setText(R.id.id_tv_time, item.getNiceDate());
    }
}
