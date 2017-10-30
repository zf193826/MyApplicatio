package lianxi.com.week_03;

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

import lianxi.com.week_03.fragment.Fragment_FaXian;
import lianxi.com.week_03.fragment.Fragment_ShouYe;
import lianxi.com.week_03.fragment.Fragment_WoDe;
import lianxi.com.week_03.fragment.Fragment_XiaZai;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private RelativeLayout relativeLayout;
    private RadioGroup radioGroup;
    private ListView listView;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到布局文件的控件
        imageView = (ImageView) findViewById(R.id.image_touxiang);
        //抽屉布局边框
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //主内容区域
        frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);
        //抽屉布局
        relativeLayout = (RelativeLayout) findViewById(R.id.drawer_relative);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        listView = (ListView) findViewById(R.id.list_view);

        //创建一个集合里面放抽屉布局里面的list的数据
        titles = new ArrayList<>();
        titles.add("红包");
        titles.add("形成记录");
        titles.add("好友邀请");
        titles.add("设置");
        //调用系统的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1, titles);
        //给listView设置适配器
        listView.setAdapter(adapter);
        //给button设置点击的监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case  R.id.radio_01:
                        //做到这步  给按钮创建布局和fragment文件
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_ShouYe()).commit();
                        break;
                    case  R.id.radio_02:
                        //做到这步  给按钮创建布局和fragment文件
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_FaXian()).commit();
                        break;
                    case  R.id.radio_03:
                        //做到这步  给按钮创建布局和fragment文件
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_XiaZai()).commit();
                        break;
                    case  R.id.radio_04:
                        //做到这步  给按钮创建布局和fragment文件
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
                //点击头像的照片,关闭侧拉
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
