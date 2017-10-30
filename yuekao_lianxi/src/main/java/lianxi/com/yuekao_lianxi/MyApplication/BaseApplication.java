package lianxi.com.yuekao_lianxi.MyApplication;

import android.app.Application;

import lianxi.com.yuekao_lianxi.util.ImageLoaderUtil;


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
