package com.xie.brad.loadinglayout.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.xie.brad.loadinglayout.R;
import com.xie.brad.loadinglayout.base.BaseFragment;

/**
 * Created by dell on 2017/10/30.
 */

public class TestTwoFragment extends BaseFragment {

    private static Handler handler = new Handler();
    private boolean mIsPrepared = false;
    @Override
    public int setContent() {
        return R.layout.fragment_two;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        init();
    }


    private void init() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIsPrepared = true;
                loadData();
            }
        }, 2000);
    }


    @Override
    protected void loadData() {
        if (!mIsVisible || !mIsPrepared) {
            return;
        }
        showContentView();
    }
}
