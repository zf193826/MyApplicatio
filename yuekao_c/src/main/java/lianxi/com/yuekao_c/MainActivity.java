package lianxi.com.yuekao_c;

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

import lianxi.com.yuekao_c.fragment.Fragment_FaXian;
import lianxi.com.yuekao_c.fragment.Fragment_FenLei;
import lianxi.com.yuekao_c.fragment.Fragment_GouWuChe;
import lianxi.com.yuekao_c.fragment.Fragment_ShouYe;
import lianxi.com.yuekao_c.fragment.Fragment_WoDe;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private RelativeLayout relativeLayout;
    private ListView listView;
    private List<String> titles;
    private RadioGroup radioGroup;
    private ImageView imageView;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //抽屉的跟布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //主内容区域的布局
        frameLayout = (FrameLayout) findViewById(R.id.fragment1_layout);
        //抽屉布局
        relativeLayout = (RelativeLayout) findViewById(R.id.drawer_relative);
        listView = (ListView) findViewById(R.id.list_view);
        imageView = (ImageView) findViewById(R.id.image_touxiang);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        //给listView设置适配器
        titles = new ArrayList<>();
        titles.add("个人设置");
        titles.add("缓存");
        titles.add("夜间模式");
        titles.add("配置");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1, titles);
        listView.setAdapter(adapter);
        //刚进入页面的时候

        //设置button按钮对应的布局
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1_layout,new Fragment_ShouYe()).commit();
                        break;
                    case R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1_layout,new Fragment_FenLei()).commit();
                        break;
                    case R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1_layout,new Fragment_FaXian()).commit();
                        break;
                    case R.id.radio_04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1_layout,new Fragment_GouWuChe()).commit();
                        break;
                    case R.id.radio_05:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1_layout,new Fragment_WoDe()).commit();
                        break;
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击上面的 头像 关闭侧拉
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
