package lianxi.com.zf20171017lianxi.Activity;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import lianxi.com.zf20171017lianxi.Activity.fragment.Fragment_WeiDengLu;
import lianxi.com.zf20171017lianxi.Activity.fragment.Fragment_YaoWen;
import lianxi.com.zf20171017lianxi.Activity.fragment.Fragment_ZhuYe;
import lianxi.com.zf20171017lianxi.R;

public class Zhu_Activity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_);
        //1.找控件
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);
        //2.给radiogroup设置监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //在替换布局前,把对应的布局文件创建出来
                //根据id替换对应fragment布局
                switch (i){
                    case R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_ZhuYe()).commit();
                        break;
                    case R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_YaoWen()).commit();
                        break;
                    case R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_WeiDengLu()).commit();
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
