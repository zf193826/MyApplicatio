package lianxi.com.zhangfeng20171014;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import lianxi.com.zhangfeng20171014.fragment.FragmentHome;
import lianxi.com.zhangfeng20171014.fragment.FragmentMy;
import lianxi.com.zhangfeng20171014.fragment.FragmentVideo;
import lianxi.com.zhangfeng20171014.fragment.FragmentZiXun;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case  R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentHome()).commit();

                        break;
                    case  R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentVideo()).commit();

                        break;
                    case  R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentZiXun()).commit();

                        break;
                    case  R.id.radio_04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentMy()).commit();

                        break;
                    default:
                        break;
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentHome()).commit();

    }
}
