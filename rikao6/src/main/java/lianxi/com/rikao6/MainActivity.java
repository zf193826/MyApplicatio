package lianxi.com.rikao6;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        lv = (ListView) findViewById(R.id.lv);
    }
    //创建点击的onclieck方法
    public void getConnection(View view){
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String path = "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";
                try {
                    URL url = new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    //获取请求码
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
                List<DataDataBean.NewslistBean> newslist = dataDataBean.getNewslist();
                //设置适配器
                MyAdapater myAdapater = new MyAdapater(newslist, MainActivity.this);

                lv.setAdapter(myAdapater);
            }
        };
        asyncTask.execute();


    }
    public void getHttpClient(View view){
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String path = "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";
                    //创建一个客户端对象
                    HttpClient client = new DefaultHttpClient();
                    //创建一个请求对象,可以是get也可以是post
                    HttpGet httpGet = new HttpGet();
                    HttpResponse response = client.execute(httpGet);
                    //获取状态码
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        //获取响应的实体内容
                        InputStream inputStream = response.getEntity().getContent();
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
                final List<DataDataBean.NewslistBean> newslist = dataDataBean.getNewslist();
                //设置适配器
                MyAdapater myAdapater = new MyAdapater(newslist, MainActivity.this);
                lv.setAdapter(myAdapater);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this,OtherActivity.class);
                        intent.putExtra("url",newslist.get(i).getUrl());
                        startActivity(intent);
                    }
                });

            }
        };
        asyncTask.execute();

    }

    private String streamToString(InputStream inputStream,String charset) {
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


}
