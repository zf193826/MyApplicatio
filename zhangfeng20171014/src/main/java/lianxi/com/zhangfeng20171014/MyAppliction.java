package lianxi.com.zhangfeng20171014;

import android.app.Application;

/**
 * Created by 张峰 on 2017/10/13.
 */

public class MyAppliction extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.init(this);
    }
}
