package lianxi.com.yuekao_b.fragment;

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

import lianxi.com.yuekao_b.R;

/**
 * Created by 张峰 on 2017/10/24.
 */

public class Fragment_XinWen extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xinwen_layout, container, false);
        //在这个view布局里找对应的控件
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add("推荐");
        list.add("热点");
        list.add("北京");
        list.add("视频");
        list.add("社会");
        list.add("图片");
        //设置适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {

                    case 0:
                        fragment = new Fragment_TuiJian();
                        break;
                    case 1:
                        fragment = new Fragment_ReDian();
                        break;
                    case 2:
                        fragment = new Fragment_BeiJing();
                        break;
                    case 3:
                        fragment = new Fragment_ShiPing();
                        break;
                    case 4:
                        fragment = new Fragment_SheHui();
                        break;
                    case 5:
                        fragment = new Fragment_TuPian();
                        break;

                }
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
