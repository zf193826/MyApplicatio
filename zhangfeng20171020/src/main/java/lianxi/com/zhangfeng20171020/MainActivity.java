package lianxi.com.zhangfeng20171020;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import lianxi.com.zhangfeng20171020.adapter.ListViewAdapter;
import lianxi.com.zhangfeng20171020.bean.DataDataBean;
import lianxi.com.zhangfeng20171020.util.JsonCallBack;
import lianxi.com.zhangfeng20171020.util.NetDataUtil;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PullToRefreshListView refreshListView;
    private int page_num = 1;
    private List<DataDataBean.ResultsBean> list = new ArrayList<>();
    private ListViewAdapter listViewAdapter;
    private ILoadingLayout startLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        refreshListView = (PullToRefreshListView) findViewById(R.id.refresh_list_view);


        //获取数据
        getDataFromNet();
        //轮播图
    }


    private void getDataFromNet() {

        NetDataUtil.getData("http://gank.io/api/data/Android/10/" + page_num, MainActivity.this, new JsonCallBack() {
            @Override
            public void getJsonString(String json) {
                //解析
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);

                //往后面添加
                list.addAll(dataDataBean.getResults());
                //设置适配器
                setAdapter();
                //停止刷新
                refreshListView.onRefreshComplete();

            }
        });
    }
    private void setAdapter() {
        if (listViewAdapter == null){
            listViewAdapter = new ListViewAdapter(MainActivity.this,list);
            refreshListView.setAdapter(listViewAdapter);
        }else{
            listViewAdapter.notifyDataSetChanged();
        }
    }


}
