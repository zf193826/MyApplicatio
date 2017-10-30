package lianxi.com.monikao;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lianxi.com.monikao.View.XListView;

/**
 * Created by 张峰 on 2017/10/14.
 */

@SuppressLint("ValidFragment")
class NewsFragment extends Fragment implements  XListView.IXListViewListener{
    private List<DataDataBean.DataBean> list = new ArrayList<>();
    private int page_num = 1;
    private XListView xListView;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        xListView = (XListView) view.findViewById(R.id.fragment_list_view_01);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取数据
        /**
         * titles.add("我的消息");
         titles.add("会员中心");
         titles.add("商城");
         titles.add("在线听歌免流量");
         titles.add("我的好友");
         titles.add("附近的人");
         titles.add("个性换肤");
         titles.add("听歌识曲");
         titles.add("定时停止播放");
         titles.add("扫一扫");
         */
        String name = getArguments().getString("name", "");
        if (name.equals("我的消息")) {
            name = "top";
        } else if (name.equals("会员中心")) {
            name = "junshi";
        } else if (name.equals("商城")) {
            name = "shehui";

        } else if (name.equals("在线听歌免流量")) {
            name = "caijing";
        } else if (name.equals("我的好友")) {
            name = "guonei";
        }else if (name.equals("附近的人")) {
            name = "guonei";
        }else if (name.equals("个性换肤")) {
            name = "guonei";
        }else if (name.equals("听歌识曲")) {
            name = "guonei";
        }else if (name.equals("定时停止播放")) {
            name = "guonei";
        }else if (name.equals("扫一扫")) {
            name = "guonei";
        }
        getDataFromNet(name);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);
        xListView.setXListViewListener(this);
    }

    private void getDataFromNet(String name) {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page=1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    //获取
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String json = streamToString(inputStream, "utf-8");
                        return json;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(s, DataDataBean.class);
                MyAdapter myAdapter = new MyAdapter(dataDataBean.getData(),getActivity());
                xListView.setAdapter(myAdapter);
                list.addAll(dataDataBean.getData());


            }
        };
        asyncTask.execute();
        xListView.stopRefresh();

    }

    private String streamToString(InputStream inputStream, String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null){
                builder.append(s);
            }

            bufferedReader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;


    }

    @Override
    public void onRefresh() {
        getDataFromNet(name);
    }

    @Override
    public void onLoadMore() {
        page_num++;
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page="+page_num);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    //获取
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String json = streamToString(inputStream, "utf-8");
                        return json;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(s, DataDataBean.class);
                MyAdapter myAdapter = new MyAdapter(dataDataBean.getData(),getActivity());
                xListView.setAdapter(myAdapter);
                list.addAll(0,dataDataBean.getData());
                //停止加载
                xListView.stopLoadMore();


            }
        };
        asyncTask.execute();
    }



}
