package lianxi.com.yuekao_b.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lianxi.com.yuekao_b.Bean.BanKuaiBean;
import lianxi.com.yuekao_b.Bean.DataDataBean;
import lianxi.com.yuekao_b.Bean.LunBoBean;
import lianxi.com.yuekao_b.R;
import lianxi.com.yuekao_b.adapter.ListViewAdapter;
import lianxi.com.yuekao_b.adapter.ListViewAdapter2;
import lianxi.com.yuekao_b.adapter.MyViewPagerAdapter;
import lianxi.com.yuekao_b.inter.JsonStringCallaBack;
import lianxi.com.yuekao_b.util.NetDataUtil;

/**
 * Created by 张峰 on 2017/10/24.
 */

public class Fragment_GridView extends Fragment {

    private PullToRefreshScrollView pullToRefreshScrollView;
    private ListView listView;
    private List<BanKuaiBean.ResultsBean> list1 = new ArrayList<>();
    private ListViewAdapter2 listViewAdapter2;
    private ILoadingLayout startLabels;
    private int page_num = 1;
    private List<String> imageUrlList;
    private ListViewAdapter listViewAdapter;
    private List<DataDataBean.ResultsBean> list = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                //显示下一页....拿到当前页+1
                viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
                //再次发送消息
                handler.sendEmptyMessageDelayed(0,2000);

            }
        }
    };
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_gridviewlayout, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.image_view_pager);
        pullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.refresh_scroll_view);
        listView = (ListView) view.findViewById(R.id.scroll_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //失去焦点
        listView.setFocusable(false);
        
        //轮播图
        lunBoTu();
        
        //获取数据....解析的方法
        getDataFromNet();
        //设置刷新模式
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);

        //3.通过getLoadingLayoutProxy 方法来指定上拉和下拉时显示的状态的区别(也就是设置向下拉的时候头部里面显示的文字)
        //此时这里设置的是下拉刷新的时候显示的文字,所以第一个设置true表示现在是刷新,第二个设置为false
        startLabels = pullToRefreshScrollView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在刷新...");
        startLabels.setReleaseLabel("放开刷新");

        ILoadingLayout endLabels = pullToRefreshScrollView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");

        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getRefreshData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page_num++;
                getDataFromNet();

            }
        });

    }

    private void lunBoTu() {
        NetDataUtil.getData(getActivity(), "http://v3.wufazhuce.com:8000/api/reading/index/?version=3.5.0&platform=android", new JsonStringCallaBack() {
            @Override
            public void getJsonString(String json) {
                //这个结合记录轮播图的所有地址
                imageUrlList = new ArrayList<String>();


                //解析
                Gson gson = new Gson();
                LunBoBean lunBoBean = gson.fromJson(json, LunBoBean.class);
                List<LunBoBean.DataBean.EssayBean> essay = lunBoBean.getData().getEssay();

                for (LunBoBean.DataBean.EssayBean essayBean:essay) {
                    imageUrlList.add(essayBean.getAuthor().get(0).getWeb_url());
                    //此时应该根据图片的路径,加载图片,设置适配器
                    MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getActivity(), imageUrlList);
                    viewPager.setAdapter(myViewPagerAdapter);
                    //1.手动可以无限滑动....maxValue....把当前开始展示的位置放在足够大的某个位置
                    viewPager.setCurrentItem(imageUrlList.size()*100000);
                    //2.自动轮播
                    handler.sendEmptyMessageDelayed(0,2000);
                    
                }
            }
        });
    }

    private void getRefreshData() {
        NetDataUtil.getData(getActivity(), "http://gank.io/api/data/Android/10/1", new JsonStringCallaBack() {
            @Override
            public void getJsonString(String json) {
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                //先清空一下数据
                list.clear();
                //添加到集合的最前边,,,,(0,,,,)
                list.addAll(0,dataDataBean.getResults());
                //设置适配器
                setAdater();
                //设置适配器之后停止刷新的操作
                pullToRefreshScrollView.onRefreshComplete();

                //可以设置刷新的时间....
                startLabels.setLastUpdatedLabel("上次更新时间:"+new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));//last最近的,最后一次update修改/更新
            }
        });
    }

    private void getDataFromNet() {
        NetDataUtil.getData(getActivity(), "http://gank.io/api/data/Android/10/1", new JsonStringCallaBack() {
            @Override
            public void getJsonString(String json) {
                Log.i("---",json);
                Gson gson = new Gson();


                    BanKuaiBean banKuaiBean = gson.fromJson(json,BanKuaiBean.class);

                    List<BanKuaiBean.ResultsBean> bklist = banKuaiBean.getResults();
                    list1.addAll(bklist);
                    //设置适配器
                    setAdater();

            }
        });
    }

    private void setAdater() {
        if (listViewAdapter2 == null){

            listViewAdapter2 = new ListViewAdapter2(getActivity(),list1);
            listView.setAdapter(listViewAdapter2);
        }else {
            listViewAdapter2.notifyDataSetChanged();
        }
        pullToRefreshScrollView.onRefreshComplete();
    }
}
