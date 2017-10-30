package lianxi.com.zhangfeng20171018;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

import lianxi.com.zhangfeng20171018.View.XListView;
import lianxi.com.zhangfeng20171018.bean.DataDataBean;
import lianxi.com.zhangfeng20171018.inter.JsonStringCallaBack;
import lianxi.com.zhangfeng20171018.util.NetDataUtil;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView xListView;
    private List<DataDataBean.ResultsBean> list = new ArrayList<>();
    private int page_num = 1;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        xListView = (XListView) findViewById(R.id.x_list_view);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        getDataFromNet();
        //获取数据的方法
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                String path = "http://v.juhe.cn/toutiao/index?type=top&key=dbedecbcd1899c9785b95cc2d17131c5";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
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
                super.onPostExecute(s);
            }
        };
        asyncTask.execute();
    }

    private void getDataFromNet() {
        //在这里解析获取的数据
        NetDataUtil.getData(MainActivity.this,"http://gank.io/api/data/Android/10/"+page_num,new JsonStringCallaBack(){
                    @Override
                    public void getJsonString(String json) {
                        Gson gson = new Gson();
                        DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                        list.addAll(dataDataBean.getResults());
                        //设置适配器
                        setAdapter();


                    }
                }

        );
    }

    private void setAdapter() {
        if (myAdapter == null){
            myAdapter = new MyAdapter(MainActivity.this, list);
            xListView.setAdapter(myAdapter);
        }else {
            myAdapter.notifyDataSetChanged();
        }
    }

    private String streamToString(InputStream inputStream, String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String a = null;
            StringBuilder builder = new StringBuilder();
            while ((a = bufferedReader.readLine())!= null){
                builder.append(a);
            }
            bufferedReader.close();
            return  builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;


    }

    @Override
    public void onRefresh() {
        getDataRefresh();

    }

    

    @Override
    public void onLoadMore() {
        page_num++;
        getDataFromNet();

    }


    public void getDataRefresh() {
        //当列表下拉刷新时，接口的页数设置为1，清除原来的列表数据，请求网络

        NetDataUtil.getData(MainActivity.this, "http://gank.io/api/data/Android/10/1", new JsonStringCallaBack() {
            @Override
            public void getJsonString(String json) {
                //在这里解析
                Gson gson = new Gson();

                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                //先清除所有
                list.clear();
                //再添加
                list.addAll(0,dataDataBean.getResults());

                //设置适配器
                setAdapter();

                //停止刷新
                xListView.stopRefresh();
                //设置刷新时间
                Date data = new Date();
                String format = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
                xListView.setRefreshTime(format);
            }
        });

    };

}
