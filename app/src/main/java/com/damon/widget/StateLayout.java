package com.damon.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damon.R;
import com.damon.helper.ResourceHelper;

/**
 * @author: DamonJiang
 * @date: 2018/10/12 0012
 * @description: 全局管理页面状态的 layout，包括 加载中，空数据，网络错误， 内容页，自定义
 */
public class StateLayout extends LinearLayout {

    private static final String TAG = "StateLayout";

    public static final int TYPE_CONTENT = 1;           // 内容页面
    public static final int TYPE_LOADING = 2;           // 加载中
    public static final int TYPE_ERROR = 3;             // 错误页面
    public static final int TYPE_EMPTY = 4;             // 空页面
    public static final int TYPE_CUSTOM = 5;            // 自定义页面


    private View mLoading = null;
    private View mEmpty = null;
    private View mError = null;
    private View mContent = null;
    private View mCustom = null;
    private BlinkLayout mBlinkLayout = null;

    private Context mContext = null;
    private int mCurrentState = TYPE_CONTENT;


    public StateLayout(Context context) {
        super(context);
    }

    public StateLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void showView(int type) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            changeView(type);
        }
    }

    private void changeView(int type) {
        mCurrentState = type;
        if (mLoading != null)
            mLoading.setVisibility(type == TYPE_LOADING ? View.VISIBLE : View.GONE);
        if (mContent != null)
            mContent.setVisibility(type == TYPE_CONTENT ? View.VISIBLE : View.GONE);
        if (mError != null) mError.setVisibility(type == TYPE_ERROR ? View.VISIBLE : View.GONE);
        if (mEmpty != null) mEmpty.setVisibility(type == TYPE_EMPTY ? View.VISIBLE : View.GONE);
        if (mCustom != null) mCustom.setVisibility(type == TYPE_CUSTOM ? View.VISIBLE : View.GONE);
        Log.d(TAG, "changeView: "+type);
    }

    /**
     * 当在非正常显示的情况下需要隐藏掉我们想要展示的正文内容页
     */
    public void hideContent() {
        showView(TYPE_CONTENT);
        mBlinkLayout.stopShimmerAnimation();
    }

    /**
     * 展示加载页面
     */
    public void showLoading() {
        showView(TYPE_LOADING);
        mBlinkLayout.startShimmerAnimation();
        Log.d(TAG, "showLoading: " + mLoading);
    }

    /**
     * 展示错误页面
     */
    public void showError() {
        showView(TYPE_ERROR);
    }

    /**
     * 展示空页面
     */
    public void showEmpty() {
        showView(TYPE_EMPTY);
    }

    /**
     * 展示自定义页面
     */
    public void showCustom() {
        showView(TYPE_CUSTOM);
    }

    public static class Builder {
        private StateLayout mStateLayout;
        private LayoutInflater mInflater;
        private Context mContext;
        private TextView mTvEmpty;
        private TextView mTvError;
        private TextView mTvErrorRetry;
        private TextView mTvLoading;
        private TextView mTvLoadingBlink;
        private BlinkLayout mBlinkLayout;
        private OnRetryClickListener mOnRetryClickListener = null;

        public Builder(Context context) {
            this.mContext = context;
            this.mStateLayout = new StateLayout(context);
            mInflater = LayoutInflater.from(context);
        }

        private void initDefault() {
            if (mStateLayout.mEmpty == null) {
                setDefaultEmpty();
            }
            if (mStateLayout.mError == null) {
                setDefaultError();
            }
            if (mStateLayout.mLoading == null) {
                setDefaultLoading();
            }
        }

        private void setDefaultEmpty() {
            mStateLayout.mEmpty = mInflater.inflate(R.layout.layout_empty, mStateLayout, false);
            mTvEmpty = mStateLayout.mEmpty.findViewById(R.id.tv_page_empty);
            mStateLayout.mEmpty.setVisibility(View.GONE);
            mStateLayout.addView(mStateLayout.mEmpty);
            Log.d(TAG, "setDefaultEmpty: 空 已创建" + mStateLayout.mEmpty);
        }

        private void setDefaultError() {
            mStateLayout.mError = mInflater.inflate(R.layout.layout_error, mStateLayout, false);
            mTvError = mStateLayout.mError.findViewById(R.id.tv_page_error);
            mTvErrorRetry = mStateLayout.mError.findViewById(R.id.tv_page_error_retry);
            mTvErrorRetry.setOnClickListener(view -> {
                if (mOnRetryClickListener != null) {
                    mOnRetryClickListener.onClick();
                }
            });
            mStateLayout.mError.setVisibility(View.GONE);
            mStateLayout.addView(mStateLayout.mError);
            Log.d(TAG, "setDefaultError: 错误 已创建" + mStateLayout.mError);
        }

        private void setDefaultLoading() {
            mStateLayout.mLoading = mInflater.inflate(R.layout.layout_loading, mStateLayout, false);

            mBlinkLayout = mStateLayout.mLoading.findViewById(R.id.blinklayout);
            mStateLayout.mBlinkLayout = mBlinkLayout;
            mTvLoadingBlink = mStateLayout.mLoading.findViewById(R.id.tv_page_loading_blink);

            mStateLayout.mLoading.setVisibility(View.GONE);
            mStateLayout.addView(mStateLayout.mLoading);
            Log.d(TAG, "setDefaultLoading: 加载 已创建" + mStateLayout.mLoading);
        }

        /**
         * 设置loading布局
         */
        public Builder setLoading(int loading, int loadingTvId) {
            View view = mInflater.inflate(loading, mStateLayout, false);
            mTvLoading = view.findViewById(loadingTvId);
            mStateLayout.mLoading = view;
            mStateLayout.addView(view);
            return this;
        }

        /**
         * 自定义错误布局
         * 默认样式，传入错误文案ID，及点击回调
         */
        public Builder setError(int errorView, int errorClickId, OnRetryClickListener onRetryClickListener) {
            View view = mInflater.inflate(errorView, mStateLayout, false);
            mTvError = view.findViewById(errorClickId);
            mStateLayout.mError = view;
            mStateLayout.addView(view);
            mTvError.setOnClickListener(view1 -> {
                if (mOnRetryClickListener != null) {
                    mOnRetryClickListener.onClick();
                }
            });
            return this;
        }

        /**
         * 自定义错误布局
         * 设置前需手动初始化好View中各个事件
         */
        public Builder setError(View errorView) {
            mStateLayout.mError = errorView;
            mStateLayout.addView(errorView);

            return this;
        }


        /**
         * 自定义空布局
         */
        public Builder setEmpty(int empty, int emptyTvId) {
            View view = mInflater.inflate(empty, null, false);
            mTvEmpty = view.findViewById(emptyTvId);
            mStateLayout.mEmpty = view;
            mStateLayout.addView(view);

            return this;
        }

        /**
         * 自定义布局
         */
        public Builder setCustomView(View view) {
            mStateLayout.mCustom = view;
            mStateLayout.addView(view);
            return this;
        }

        /**
         * 设置加载文案
         */
        public Builder setLoadingText(String text) {
            mTvLoading.setText(text);
            return this;
        }

        /**
         * 设置默认闪烁加载文案
         */
        public Builder setDefaultLoadingBlinkText(String text) {
            mTvLoadingBlink.setText(text);
            return this;
        }

        /**
         * 设置加载文字颜色
         */
        public Builder setLoadingTextColor(int color) {
            mTvLoading.setTextColor(ResourceHelper.getColor(color));
            return this;
        }

        /**
         * 设置默认加载闪烁颜色
         */
        public Builder setDefaultLoadingBlinkColor(int color) {
            mBlinkLayout.setShimmerColor(ResourceHelper.getColor(color));
            return this;
        }

        /**
         * 设置默认空布局文案
         */
        public Builder setDefaultEmptyText(String text) {
            mTvEmpty.setText(text);
            return this;
        }

        /**
         * 设置默认空布局文案颜色
         */
        public Builder setDefaultEmptyTextColor(int color)

        {
            mTvEmpty.setTextColor(ResourceHelper.getColor(color));
            return this;
        }

        /**
         * 设置默认错误布局文案
         */
        public Builder setDefaultErrorText(String text) {
            mTvError.setText(text);
            return this;
        }


        /**
         * 设置默认错误布局文案颜色
         */
        public Builder setDefaultErrorTextColor(int color) {
            mTvError.setTextColor(ResourceHelper.getColor(color));
            return this;
        }

        /**
         * 设置默认错误布局重试文案
         */
        public Builder setDefaultErrorRetryText(String text) {
            mTvErrorRetry.setText(text);
            return this;
        }

        /**
         * 设置默认错误布局重试文案颜色
         */
        public Builder setDefaultErrorRetryTextColor(int color) {
            mTvErrorRetry.setTextColor(ResourceHelper.getColor(color));
            return this;
        }

        /**
         * 设置空布局提醒图片
         */
        public Builder setEmptyDrawable(int resId) {
            setTopDrawables(mTvEmpty, resId);
            return this;
        }

        /**
         * 设置错误布局提醒图片
         */
        public Builder setErrorDrawable(int resId) {
            setTopDrawables(mTvError, resId);
            return this;
        }


        /**
         * 设置布局top drawable
         */
        private void setTopDrawables(TextView textView, int resId) {
            if (resId == 0) {
                textView.setCompoundDrawables(null, null, null, null);
            }
            Drawable drawable = ResourceHelper.getDrawable(resId);
            //必须设置图片大小，否则不显示
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, drawable, null, null);
            textView.setCompoundDrawablePadding(20);
        }

        /**
         * set target view for root
         */
        public Builder initPage(Object targetView) {
            ViewGroup content = null;

            // 如果是Activity，获取到android.R.content
            if (targetView instanceof Activity) {
                Log.d(TAG, "=====Activity======");
                mContext = ((Activity) targetView);
                content = ((Activity) targetView).findViewById(android.R.id.content);
            }
            // 如果是Fragment获取到parent
            if (targetView instanceof Fragment) {
                Log.d(TAG, "=====Fragment======");
                mContext = ((Fragment) targetView).getActivity();
                content = (ViewGroup) ((ViewGroup) targetView).getParent();
            }
            // 如果是View，也取到 parent
            if (targetView instanceof View) {
                Log.d(TAG, "=====View======");
                mContext = ((ViewGroup) targetView).getContext();
                content = (ViewGroup) ((ViewGroup) targetView).getParent();
            }


            int childCount = content.getChildCount();
            int index = 0;
            View oldContent;

            //如果是某个线性布局或者相对布局时，遍历它的孩子，找到对应的索引，记录下来
            if (targetView instanceof View) {
                Log.d(TAG, "=====view===2===");
                oldContent = (View) targetView;
                for (int i = 0; i < childCount; i++) {
                    if (content.getChildAt(i) == targetView) {
                        index = i;
                        break;
                    }
                }

            } else {
                Log.d(TAG, "=====Activity或者Fragment======");
                //如果是Activity或者Fragment时，取到索引为第一个的View
                oldContent = content.getChildAt(0);
            }
            //给PageLayout设置contentView
            mStateLayout.mContent = oldContent;
            mStateLayout.removeAllViews();
            //将本身content移除，并且把PageLayout添加到DecorView中去
            content.removeView(oldContent);

            ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
            content.addView(mStateLayout, index, lp);
            mStateLayout.addView(oldContent);
            //设置默认状态布局
            initDefault();
            return this;
        }

        public StateLayout create() {
            return mStateLayout;
        }

        public Builder setOnRetryListener(OnRetryClickListener onRetryClickListener) {
            this.mOnRetryClickListener = onRetryClickListener;
            return this;
        }

        public interface OnRetryClickListener {
            void onClick();
        }
    }


}
