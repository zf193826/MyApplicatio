package lianxi.com.yk_lianxi;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lianxi.com.yk_lianxi.fragment.FragmentHome;
import lianxi.com.yk_lianxi.fragment.FragmentMy;
import lianxi.com.yk_lianxi.fragment.FragmentVideo;
import lianxi.com.yk_lianxi.fragment.FragmentZiXun;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RelativeLayout relativeLayout;
    private RadioGroup radioGroup;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //根布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //抽屉布局
        relativeLayout = (RelativeLayout) findViewById(R.id.drawer_relative);
        //展示抽屉数据的listView
        listView = (ListView) findViewById(R.id.list_view_drawer);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        //设置抽屉中listView的数据
        List<String> list = new ArrayList<>();
        list.add("客服热线");
        list.add("钱包");
        list.add("我的资料");
        list.add("理财");
        list.add("设置");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        //设置适配器
        listView.setAdapter(adapter);

        //radiogroup的监听事件
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
