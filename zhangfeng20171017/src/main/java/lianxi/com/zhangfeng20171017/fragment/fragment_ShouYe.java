package lianxi.com.zhangfeng20171017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lianxi.com.zhangfeng20171017.R;

/**
 * Created by 张峰 on 2017/10/17.
 */

public class fragment_ShouYe extends Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye_layout,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add("新闻");
        list.add("历史");
        list.add("健康");

        //设置ViewPager的适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new fragment_ShouYe();
                        break;
                    case 1:
                        fragment = new fragment_ZiLiao();
                        break;
                    case 2:
                        fragment = new fragment_DongTai();
                        break;
                    case 3:
                        fragment = new fragment_WoDe();
                        break;
                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //tab和ViewPager关联
        tabLayout.setupWithViewPager(viewPager);
    }
}
