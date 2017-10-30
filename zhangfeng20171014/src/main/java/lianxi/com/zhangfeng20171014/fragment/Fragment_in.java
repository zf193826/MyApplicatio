package lianxi.com.zhangfeng20171014.fragment;


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

import lianxi.com.zhangfeng20171014.DataDataBean;
import lianxi.com.zhangfeng20171014.R;
import lianxi.com.zhangfeng20171014.View.XListView;


/**
 * Created by 张峰 on 2017/10/13.
 */

public class Fragment_in extends Fragment implements XListView.IXListViewListener {

    private XListView xListView;
    private List<DataDataBean.DataBean> data_list = new ArrayList<>();
    private int page_num = 1;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_layout,container,false);
        xListView = (XListView) view.findViewById(R.id.x_list_view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String name = getArguments().getString("name","");
        if (name.equals("头条")){
            name = "top";

        }else if (name.equals("军事")){
            name = "junshi";

        }else if (name.equals("时尚")){
            name = "shishang";

        }else if (name.equals("财经")){
            name = "caijing";

        }else if (name.equals("国内")){
            name = "guonei";

        }else if (name.equals("国际")){
            name = "guoji";

        }else if (name.equals("体育")){
            name = "tiyu";

        }else if (name.equals("娱乐")){
            name = "yule";
            
        }
        getDataFromNet(name);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
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

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();
                        String json = streamToString(inputStream, "utf-8");
                        return  json;
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
                MyAdapter myAdapter = new MyAdapter(dataDataBean.getData(), getActivity());
                xListView.setAdapter(myAdapter);
                data_list.addAll(dataDataBean.getData());
                xListView.stopLoadMore();


            }
        };
        asyncTask.execute();
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
        page_num++;
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page=1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();
                        String json = streamToString(inputStream,"utf-8");
                        return  json;

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
                data_list.addAll(0,dataDataBean.getData());
                //停止加载
                xListView.stopRefresh();

            }
        };
        asyncTask.execute();

    }

    @Override
    public void onLoadMore() {
        getDataFromNet(name);

    }
}
