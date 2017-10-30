package lianxi.com.monikao;

import android.app.Application;

/**
 * Created by 张峰 on 2017/10/14.
 */

public class MyAppliction extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.init(this);
    }
}
