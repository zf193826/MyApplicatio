package lianxi.com.zhangfeng20171019.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lianxi.com.zhangfeng20171019.R;
import lianxi.com.zhangfeng20171019.adapter.ListViewAdapter;
import lianxi.com.zhangfeng20171019.bean.DataDataBean;
import lianxi.com.zhangfeng20171019.util.JsonCallBack;
import lianxi.com.zhangfeng20171019.util.NetDataUtil;

/**
 * Created by 张峰 on 2017/10/19.
 */

public class Fragment_ZiXun extends Fragment{

    private PullToRefreshListView refreshListView;
    private List<DataDataBean.NewslistBean> list = new ArrayList<>();
    private ListViewAdapter listViewAdapter;
    private int page_num = 1;
    private ILoadingLayout startLabels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zixun_layout,container,false);
        //找控件
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.refresh_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取数据
        getDataFromNet();

        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //这里通过getLoadingLayoutProxy 方法来指定上拉和下拉时显示的状态的区别，第一个true 代表下来状态 ，第二个true 代表上拉的状态
        startLabels = refreshListView.getLoadingLayoutProxy(true, false);

        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在拉");
        startLabels.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = refreshListView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");

        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新....请求第一页的数据,清空之前的数据,然后再添加设置
                getRefreshData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉刷新/加载...加载的时候页数
                page_num++;
                getDataFromNet();

            }
        });
    }

    private void getRefreshData() {
        NetDataUtil.getData("https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=1", getActivity(), new JsonCallBack() {
            @Override
            public void getJsonString(String json) {
                //解析
                Gson gson = new Gson();

                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                //先清空一下数据
                list.clear();

                //添加到集合的最前边,,,,(0,,,,)
                list.addAll(0,dataDataBean.getNewslist());

                //设置适配器
                setAdapter();

                //设置适配器之后停止刷新的操作
                refreshListView.onRefreshComplete();

                //可以设置刷新的时间....
                startLabels.setLastUpdatedLabel("上次更新时间:"+new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));//last最近的,最后一次update修改/更新
            }
        });
    }

    private void setAdapter() {
        if (listViewAdapter == null){
            listViewAdapter = new ListViewAdapter(getActivity(),list);
            refreshListView.setAdapter(listViewAdapter);
        }else{
            listViewAdapter.notifyDataSetChanged();
        }
    }

    private void getDataFromNet() {

        NetDataUtil.getData("http://gank.io/api/data/Android/10/" + page_num, getActivity(), new JsonCallBack() {
            @Override
            public void getJsonString(String json) {
                //解析
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);

                //往后面添加
                list.addAll(dataDataBean.getNewslist());
                //设置适配器
                setAdapter();
                //停止刷新
                refreshListView.onRefreshComplete();

            }
        });
    }
}
