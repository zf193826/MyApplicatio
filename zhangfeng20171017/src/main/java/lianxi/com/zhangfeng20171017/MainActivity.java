package lianxi.com.zhangfeng20171017;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import lianxi.com.zhangfeng20171017.fragment.fragment_DongTai;
import lianxi.com.zhangfeng20171017.fragment.fragment_ShouYe;
import lianxi.com.zhangfeng20171017.fragment.fragment_WoDe;
import lianxi.com.zhangfeng20171017.fragment.fragment_ZiLiao;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);


        //radiogroup的点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new fragment_ShouYe()).commit();
                       break;
                    case R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new fragment_ZiLiao()).commit();
                        break;
                    case R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new fragment_DongTai()).commit();
                        break;
                    case R.id.radio_04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new fragment_WoDe()).commit();
                        break;
                    default:
                        break;

                }

            }
        });

    }
}
