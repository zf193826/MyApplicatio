package lianxi.com.week_03.fragment;

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

import lianxi.com.week_03.R;

/**
 * Created by 张峰 on 2017/10/21.
 */

public class Fragment_ShouYe extends Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye_layout, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建一个list集合里面存放tablayout里面的数据
        list = new ArrayList<>();
        list.add("推荐");
        list.add("课程");
        list.add("实战");
        list.add("职业路径");
        //设置viewpager的适配器
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
                        //创建tablayout所对应的布局文件和fragment文件
                        fragment = new Fragment_TuiJian();

                        break;
                    case 1:
                        fragment = new Fragment_KeCheng();
                        break;
                    case 2:
                        fragment = new Fragment_ShiZhan();
                        break;
                    case 3:
                        fragment = new Fragment_ZhiYe();
                        break;

                }
                return fragment;
            }

            @Override
            public int getCount() {

                return list.size();
            }
        });
        //把tablayout和viewPager进行关联
        tabLayout.setupWithViewPager(viewPager);

    }
}
