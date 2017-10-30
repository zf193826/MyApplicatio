package lianxi.com.zhangfeng20171014.fragment;


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

import lianxi.com.zhangfeng20171014.R;

/**
 * Created by 张峰 on 2017/10/13.
 */

public class FragmentHome extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.fragment_home_view_pager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add("头条");
        list.add("军事");
        list.add("娱乐");
        list.add("国内");
        list.add("财经");
        list.add("国际");
        list.add("时尚");
        list.add("体育");

        //设置viewPager的适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                Fragment_in fragment = new Fragment_in();
                Bundle bundle = new Bundle();
                bundle.putString("name",list.get(position));
                fragment.setArguments(bundle);

                return fragment;

            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }
}
