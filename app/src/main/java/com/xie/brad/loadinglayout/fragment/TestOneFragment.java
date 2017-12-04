package com.xie.brad.loadinglayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.xie.brad.loadinglayout.R;
import com.xie.brad.loadinglayout.activity.TestOneActivity;
import com.xie.brad.loadinglayout.base.BaseFragment;

/**
 * Created by dell on 2017/10/30.
 */

public class TestOneFragment extends BaseFragment{

    private static Handler handler = new Handler();
    private boolean mIsPrepared = false;
    @Override
    public int setContent() {
        return R.layout.fragment_one;
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
        },2000);
        Button button = myContentView.findViewById(R.id.buttons);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TestOneActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void loadData() {
        if (!mIsVisible || !mIsPrepared) {
            return;
        }
        showContentView();
    }


}
