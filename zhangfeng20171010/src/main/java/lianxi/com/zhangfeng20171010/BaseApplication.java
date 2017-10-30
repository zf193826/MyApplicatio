package lianxi.com.zhangfeng20171010;

import android.app.Application;

/**
 * Created by 张峰 on 2017/10/10.
 */

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderUtil.init(this);
    }
}
