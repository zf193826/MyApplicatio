package lianxi.com.zhangfeng20171019;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import lianxi.com.zhangfeng20171019.fragment.Fragment_HangQing;
import lianxi.com.zhangfeng20171019.fragment.Fragment_JiaoYi;
import lianxi.com.zhangfeng20171019.fragment.Fragment_ShouYe;
import lianxi.com.zhangfeng20171019.fragment.Fragment_ZiXuan;
import lianxi.com.zhangfeng20171019.fragment.Fragment_ZiXun;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        //设置适配器
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.btn_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_ShouYe()).commit();
                        break;
                    case R.id.btn_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_ZiXuan()).commit();
                        break;
                    case R.id.btn_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_HangQing()).commit();
                        break;
                    case R.id.btn_04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_ZiXun()).commit();
                        break;
                    case R.id.btn_05:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_JiaoYi()).commit();
                        break;
                    default:
                        break;
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_ZiXun()).commit();
    }
}
