package lianxi.com.day16xia.application;

import android.app.Application;

import lianxi.com.day16xia.util.ImageLoaderUtil;


/**
 * @author Dash
 * @date 2017/10/18
 * @description:
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        ImageLoaderUtil.init(this);
    }
}