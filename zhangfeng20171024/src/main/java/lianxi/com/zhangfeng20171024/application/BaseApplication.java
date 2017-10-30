package lianxi.com.zhangfeng20171024.application;

import android.app.Application;

import lianxi.com.zhangfeng20171024.util.ImageLoaderUtil;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        ImageLoaderUtil.init(this);
    }
}