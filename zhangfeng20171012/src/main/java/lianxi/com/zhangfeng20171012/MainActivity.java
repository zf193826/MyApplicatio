package lianxi.com.zhangfeng20171012;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lianxi.com.zhangfeng20171012.View.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {


    private XListView xListView;
    //创建一个集合,因为要分页加载 ,所以要创建一个最大的集合
    private List<DataDataBean.DataBean> list = new ArrayList<>();
    private MyAdapter myAdapter;
    //定义一个int值记录第几页
    private int page_num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        xListView = (XListView) findViewById(R.id.x_list_view);
        //设置
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);
        xListView.setXListViewListener(this);
        //解析网络数据的方法
        getDataFromNet();
    }

    public void getDataFromNet() {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                //http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page="+page_num

                try {
                    String path = "http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page=1";
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String json = streamToString(inputStream, "utf-8");
                        Log.i("=================",json.toString());

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
                //添加到最大的集合
                list.addAll(dataDataBean.getData());

                //设置适配器
                Adapter();
                //上拉加载完成。。。停止加载
                xListView.stopLoadMore();


            }
        };
        asyncTask.execute();


    }

    private void Adapter() {
        if (myAdapter == null) {
            myAdapter = new MyAdapter(list, MainActivity.this);
        }else{
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        page_num++;
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String path = "http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page=" + page_num;
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
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
                list.addAll(0, dataDataBean.getData());
                //设置适配器
                Adapter();
                //停止刷新
                xListView.stopRefresh();
                Date date = new Date(System.currentTimeMillis());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                xListView.setRefreshTime(simpleDateFormat.format(date));

            }
        };
        asyncTask.execute();


    }

    @Override
    public void onLoadMore() {
        getDataFromNet();

    }

    private String streamToString(InputStream inputStream, String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null) {
                builder.append(s);
            }

            bufferedReader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}