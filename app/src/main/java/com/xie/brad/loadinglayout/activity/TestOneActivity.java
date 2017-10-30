package com.xie.brad.loadinglayout.activity;

import android.os.Bundle;
import android.os.Handler;

import com.xie.brad.loadinglayout.R;
import com.xie.brad.loadinglayout.base.BaseActivity;

/**
 * Created by dell on 2017/10/30.
 */

public class TestOneActivity extends BaseActivity{
    private static Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_one);
        showLoading();
        init();
    }

    private void init() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showContentView();
            }
        },2000);
    }
}
