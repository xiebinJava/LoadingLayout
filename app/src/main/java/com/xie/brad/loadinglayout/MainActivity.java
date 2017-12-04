package com.xie.brad.loadinglayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.xie.brad.loadinglayout.fragment.TestFourFragment;
import com.xie.brad.loadinglayout.fragment.TestOneFragment;
import com.xie.brad.loadinglayout.fragment.TestThreeFragment;
import com.xie.brad.loadinglayout.fragment.TestTwoFragment;
import com.xie.brad.loadinglayout.utils.MyFragmentPagerAdapter;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.myviewpage);
        TabLayout table = findViewById(R.id.viewpager_table);

        initFragmentList();
        viewPager.setOffscreenPageLimit(3);
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        table.setTabMode(TabLayout.MODE_FIXED);
        table.setupWithViewPager(viewPager);
        viewPager.setAdapter(myAdapter);
    }

    private void initFragmentList() {
        mTitleList.add("首页");
        mTitleList.add("分类");
        mTitleList.add("购物车");
        mTitleList.add("我的");
        mFragments.add(new TestOneFragment());
        mFragments.add(new TestTwoFragment());
        mFragments.add(new TestThreeFragment());
        mFragments.add(new TestFourFragment());
    }
}
