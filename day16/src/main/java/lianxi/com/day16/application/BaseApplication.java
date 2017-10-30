package lianxi.com.day16.application;

import android.app.Application;

import lianxi.com.day16.util.ImageLoaderUtil;



public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        ImageLoaderUtil.init(this);
    }
}