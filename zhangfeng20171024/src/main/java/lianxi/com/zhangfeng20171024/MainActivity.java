package lianxi.com.zhangfeng20171024;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import lianxi.com.zhangfeng20171024.fragment.Fragment_ShouYe;
import lianxi.com.zhangfeng20171024.fragment.Fragment_TouTiao;
import lianxi.com.zhangfeng20171024.fragment.Fragment_WoDe;
import lianxi.com.zhangfeng20171024.fragment.Fragment_XiGua;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        //设置点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_ShouYe()).commit();

                        break;
                    case R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_XiGua()).commit();

                        break;
                    case R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_TouTiao()).commit();

                        break;
                    case R.id.radio_04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_WoDe()).commit();

                        break;
                    default:
                        break;
                }
            }
        });
    }


}
