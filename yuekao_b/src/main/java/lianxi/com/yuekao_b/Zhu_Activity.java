package lianxi.com.yuekao_b;

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

import lianxi.com.yuekao_b.fragment.Fragment_DongTai;
import lianxi.com.yuekao_b.fragment.Fragment_GridView;
import lianxi.com.yuekao_b.fragment.Fragment_WoDe;
import lianxi.com.yuekao_b.fragment.Fragment_XinWen;


public class Zhu_Activity extends AppCompatActivity {

    private ImageView imageView;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private RelativeLayout relativeLayout;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_);

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
        List<String> titles = new ArrayList<>();
        titles.add("微信登录");
        titles.add("QQ登录");
        titles.add("微博登录");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Zhu_Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, titles);
        listView.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radio_01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_XinWen()).commit();
                        break;
                    case R.id.radio_02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_GridView()).commit();
                        break;
                    case R.id.radio_03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_DongTai()).commit();
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
