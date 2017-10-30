package lianxi.com.yuekao_b.MyApplication;

import android.app.Application;

import lianxi.com.yuekao_b.util.ImageLoaderUtil;


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
