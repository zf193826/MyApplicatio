package lianxi.com.week_03.MyApplication;

import android.app.Application;

import lianxi.com.week_03.util.ImageLoaderUtil;


/**
 * Created by 张峰 on 2017/10/17.
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.init(this);
    }
}
