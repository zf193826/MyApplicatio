package lianxi.com.yuekao_c.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lianxi.com.yuekao_c.R;


/**
 * Created by 张峰 on 2017/10/25.
 */

public class Fragment_FenLei extends Fragment {


    private ListView listView;
    private FrameLayout framLayout;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fenlei_layout, container, false);
        //找分类页面的控件

        listView = (ListView) view.findViewById(R.id.fenlei_listView);
        framLayout = (FrameLayout) view.findViewById(R.id.fenlei_frame_layout);



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add("箱包手袋");
        list.add("美妆个护");
        list.add("钟表珠宝");
        list.add("手机数码");
        list.add("电脑办公");
        list.add("家用电器");
        list.add("视频三鲜");
        list.add("酒水饮料");
        list.add("母婴童装");

        //给ListView设置适配器
        //第一个参数 上下文 第二个参数 对应条目布局 第三个参数
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        //给listView设置适配器
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i){
                    case 0:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_XiangBao()).commit();
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_meihu()).commit();
                        break;
                    case 2:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_ZhongBiao()).commit();
                        break;
                    case 3:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_ZhongBiao()).commit();
                        break;
                    case 4:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_DianNao()).commit();
                        break;
                    case 5:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_JiaYong()).commit();
                        break;
                    case 6:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_ShiPin()).commit();
                        break;
                    case 7:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_JiuShui()).commit();
                        break;
                    case 8:
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame_layout,new Fragment_MuYing()).commit();
                        break;

                }
            }
        });


    }

}
