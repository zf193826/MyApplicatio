package lianxi.com.zhangfeng20171012;

import android.app.Application;

/**
 * Created by 张峰 on 2017/10/12.
 */

public class MyAppcliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.init(this);
    }
}
