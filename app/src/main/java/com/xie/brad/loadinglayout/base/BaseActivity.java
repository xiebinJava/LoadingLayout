package com.xie.brad.loadinglayout.base;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xie.brad.loadinglayout.R;
import com.xie.brad.loadinglayout.utils.CommonUtils;
import com.xie.brad.loadinglayout.utils.PerfectClickListener;
import com.xie.brad.loadinglayout.utils.StatusBarUtil;



/**
 * Created by jingbin on 16/12/10.
 */
public class BaseActivity extends AppCompatActivity {

    // 布局view
    protected View myContentView;
    private LinearLayout llProgressBar;
    private View refresh;
    private View myBaseView;
    private AnimationDrawable mAnimationDrawable;
    private Toolbar toolbar;


    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        myBaseView = View.inflate(this, R.layout.activity_base, null);
        myContentView = View.inflate(this, layoutResID, null);
        RelativeLayout mContainer = (RelativeLayout) myBaseView.findViewById(R.id.container);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myContentView.setLayoutParams(params);
        toolbar = myBaseView.findViewById(R.id.tool_bar);
        mContainer.addView(myContentView);
        getWindow().setContentView(myBaseView);

        // 设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.colorTheme),0);
        llProgressBar = getView(R.id.ll_progress_bar);
        refresh = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        setToolBar();
        // 点击加载失败布局
        refresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        myContentView.setVisibility(View.GONE);
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setTitle(CharSequence text) {
        toolbar.setTitle(text);
    }

    protected void showLoading() {
        if (llProgressBar.getVisibility() != View.VISIBLE) {
            llProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (myContentView.getVisibility() != View.GONE) {
            myContentView.setVisibility(View.GONE);
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (myContentView.getVisibility() != View.VISIBLE) {
            myContentView.setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (myContentView.getVisibility() != View.GONE) {
            myContentView.setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }

//    public void addSubscription(Subscription s) {
//        if (this.mCompositeSubscription == null) {
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//        this.mCompositeSubscription.add(s);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
//            this.mCompositeSubscription.unsubscribe();
//        }
//    }
//
//    public void removeSubscription() {
//        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
//            this.mCompositeSubscription.unsubscribe();
//        }
//    }
}
