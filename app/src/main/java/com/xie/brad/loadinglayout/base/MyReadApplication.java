package com.xie.brad.loadinglayout.base;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Created by jingbin on 2016/11/22.
 */

public class MyReadApplication extends Application {

    private static MyReadApplication myReadApplication;

    public static MyReadApplication getInstance() {
        return myReadApplication;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        myReadApplication = this;
        initTextSize();
    }

    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

}
