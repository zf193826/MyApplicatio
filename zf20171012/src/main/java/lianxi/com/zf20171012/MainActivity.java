package lianxi.com.zf20171012;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private RelativeLayout relativeLayout;
    private ListView lv;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //抽屉的跟布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //主内容区域的布局
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        //抽屉布局
        relativeLayout = (RelativeLayout) findViewById(R.id.drawer_relative);
        lv = (ListView) findViewById(R.id.list_view);
        //给listView设置适配器
        titles = new ArrayList<>();
        titles.add("头条");
        titles.add("社会");
        titles.add("国内");
        titles.add("军事");
        titles.add("财经");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, titles);
        lv.setAdapter(adapter);


        //刚进入页面的时候
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",titles.get(0));
        newsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFragment).commit();

        //设置点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
