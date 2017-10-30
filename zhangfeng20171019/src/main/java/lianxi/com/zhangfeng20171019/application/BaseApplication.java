package lianxi.com.zhangfeng20171019.application;

import android.app.Application;

import lianxi.com.zhangfeng20171019.util.ImageLoaderUtil;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        ImageLoaderUtil.init(this);
    }
}