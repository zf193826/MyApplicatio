package lianxi.com.monikao;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private RelativeLayout relativeLayout;
    private ListView listView;
    private List<String> titles;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image_touxiang);
        //抽屉布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //主内容区域
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        //抽屉布局
        relativeLayout = (RelativeLayout) findViewById(R.id.drawer_relative);
        listView = (ListView) findViewById(R.id.list_view);
        //给listView设置适配器
        titles = new ArrayList<>();
        titles.add("我的消息");
        titles.add("会员中心");
        titles.add("商城");
        titles.add("在线听歌免流量");
        titles.add("我的好友");
        titles.add("附近的人");
        titles.add("个性换肤");
        titles.add("听歌识曲");
        titles.add("定时停止播放");
        titles.add("扫一扫");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, titles);
        listView.setAdapter(adapter);
        //刚进入页面的时候
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",titles.get(0));
        newsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFragment).commit();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(relativeLayout);

            }
        });


        //设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //隐藏侧滑菜单
                drawerLayout.closeDrawer(relativeLayout);
                NewsFragment newsFragment = new NewsFragment();
                Bundle bundle = new Bundle();
                newsFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFragment).commit();
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
