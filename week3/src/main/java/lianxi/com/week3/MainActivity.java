package lianxi.com.week3;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lianxi.com.week3.fragment.Fragment_FaXian;
import lianxi.com.week3.fragment.Fragment_ShouYe;
import lianxi.com.week3.fragment.Fragment_WoDe;
import lianxi.com.week3.fragment.Fragment_XiaZai;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private DrawerLayout drawerLayout;
    private RelativeLayout relativeLayout;
    private List<String> titles;
    private RadioGroup radioGroup;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image_touxiang);
        //抽屉布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //主内容区域
        frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);

        //抽屉布局
        relativeLayout = (RelativeLayout) findViewById(R.id.drawer_relative);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        ListView listView = (ListView) findViewById(R.id.list_view);
        //给listView设置适配器
        titles = new ArrayList<>();
        titles.add("我的消息");
        titles.add("会员中心");
        titles.add("商城");
        titles.add("在线听歌免流量");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, titles);
        listView.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_ShouYe()).commit();
                        break;
                    case R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_FaXian()).commit();
                        break;
                    case R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_XiaZai()).commit();
                        break;
                    case R.id.radio_04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_WoDe()).commit();
                        break;
                    default:
                        break;
                }
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(relativeLayout);
            }
        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }

        });

    }
}
