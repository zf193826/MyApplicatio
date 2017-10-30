package lianxi.com.day16;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private List<String> list;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        list = new ArrayList<>();
        list.add("ListView");
        list.add("GridView");
        list.add("ScrolliView");

        viewPager.setOffscreenPageLimit(list.size());

        //设置适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {

                return list.get(position);
            }
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new FragmentListView();
                        break;
                    case 1:
                        fragment = new FragmentGridView();
                        break;
                    case 2:
                        fragment = new FragmentScrollView();
                        break;
                    default:
                        break;
                }
                return fragment;
            }
        });
        //tab和viewpager关联使用
      tabLayout.setupWithViewPager(viewPager);
    }
}
