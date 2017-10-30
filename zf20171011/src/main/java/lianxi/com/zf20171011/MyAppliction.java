package lianxi.com.zf20171011;

import android.app.Application;

/**
 * Created by 张峰 on 2017/10/11.
 */

public class MyAppliction extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.init(this);
    }
}
