package lianxi.com.day16;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lianxi.com.day16.adapter.ListViewAdapter;
import lianxi.com.day16.bean.DataDataBean;
import lianxi.com.day16.util.NetDataUtil;

/**
 * Created by 张峰 on 2017/10/18.
 */

public class FragmentGridView extends Fragment{

    private int page_num = 1;
    private List<DataDataBean.ResultsBean> list = new ArrayList<>();
    private ListViewAdapter listViewAdapter;
    private ILoadingLayout startLabels;
    private PullToRefreshGridView refreshGridView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view = inflater.inflate(R.layout.fragment_gridview_layout,container,false);
        //找控件
        refreshGridView = (PullToRefreshGridView) view.findViewById(R.id.refresh_grid_view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取数据
        getDataFromNet();
        //设置刷新模式
        //PullToRefreshBase.Mode.BOTH  这句话的意思是可以现实向上拉和向下拉的刷新功能
        refreshGridView.setMode(PullToRefreshBase.Mode.BOTH);

        //这里通过getLoadingLayoutProxy 方法来指定上拉和下拉时显示的状态的区别，第一个true 代表下来状态 ，第二个true 代表上拉的状态
        startLabels = refreshGridView.getLoadingLayoutProxy(true, false);

        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在拉");
        startLabels.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = refreshGridView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");

        //4.设置下拉和上拉刷新的监听事件
        refreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                //下拉刷新....请求第一页的数据,清空之前的数据,然后再添加设置适配器
                getRefreshData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                //上拉刷新/加载...加载的时候页数++
                page_num++;
                getDataFromNet();
            }
        });

    }

    private void getRefreshData() {
        NetDataUtil.getData("http://gank.io/api/data/Android/10/1", getActivity(), new JsonCallBack() {
            @Override
            public void getJsonString(String json) {
                //解析
                Gson gson = new Gson();

                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                //先清空一下数据
                list.clear();

                //添加到集合的最前边,,,,(0,,,,)
                list.addAll(0,dataDataBean.getResults());

                //设置适配器
                setAdapter();

                //设置适配器之后停止刷新的操作
                refreshGridView.onRefreshComplete();

                //可以设置刷新的时间....
                startLabels.setLastUpdatedLabel("上次更新时间:"+new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));//last最近的,最后一次update修改/更新
            }
        });
    }

    private void getDataFromNet() {

        NetDataUtil.getData("http://gank.io/api/data/Android/10/" + page_num, getActivity(), new JsonCallBack() {
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
                refreshGridView.onRefreshComplete();

            }
        });
    }

    private void setAdapter() {
        if (listViewAdapter == null){
            listViewAdapter = new ListViewAdapter(getActivity(),list);
            refreshGridView.setAdapter(listViewAdapter);
        }else{
            listViewAdapter.notifyDataSetChanged();
        }
    }
}
