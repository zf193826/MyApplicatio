package lianxi.com.week2;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import lianxi.com.week2.fragment.FragmentHome;
import lianxi.com.week2.fragment.FragmentMarket;
import lianxi.com.week2.fragment.FragmentMore;
import lianxi.com.week2.fragment.FragmentNotify;
import lianxi.com.week2.fragment.FragmentXiangFa;


public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.现在创建主布局文件

        //2.找控件
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        //3.给radioGroup设置监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //3.1 根据按钮的id替换所对应的布局
                switch (i){
                    case  R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new FragmentHome()).commit();
                        break;
                    case  R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new FragmentXiangFa()).commit();
                        break;
                    case  R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new FragmentMarket()).commit();
                        break;
                    case  R.id.radio_04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new FragmentNotify()).commit();
                        break;
                    case  R.id.radio_05:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new FragmentMore()).commit();
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
