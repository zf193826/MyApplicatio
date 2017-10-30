package lianxi.com.week_one;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView text_toutiao;

    private  int index = 0;
    private List<String> list = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                String json = (String) msg.obj;

                Gson gson = new Gson();
                Type type = new TypeToken<List<String>>() {}.getType();
                List<String> jsonList = gson.fromJson(json, type);

                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0;i<jsonArray.length();i++){
                        String string = jsonArray.getString(i);
                        list.add(string);
                    }
                    text_toutiao.setText(list.get(index));

                    handler.sendEmptyMessageDelayed(1,3000);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if(msg.what == 1){
                //接收到延时消
                index ++ ;
                index = index%list.size();
                text_toutiao.setText(list.get(index));
                //发送一个延时消息
                handler.sendEmptyMessageDelayed(1,3000);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        text_toutiao = (TextView) findViewById(R.id.text_toutiao);
        //获取网络上的头条数据
        getToutiao();
        //展示listView的数据
        getListViewData();
    }

    public void getToutiao() {
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.toutiao.com/hot_words/");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    //获取请求码
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();
                        //将访问到的字节流转换为字符流
                        String json = streamToString(inputStream,"utf-8");
                        Message message = Message.obtain();
                        message.what = 0;
                        message.obj = json;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private String streamToString(InputStream inputStream, String s) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, s);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            StringBuilder builder = new StringBuilder();
            while((str = bufferedReader.readLine())!= null){
                builder.append(str);

            }
            bufferedReader.close();
            return  builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    private void getListViewData(){
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
                    HttpClient client = new DefaultHttpClient();

                    HttpGet httpGet = new HttpGet("https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=1");
                    HttpResponse httpResponse = client.execute(httpGet);

                    int statusCode = httpResponse.getStatusLine().getStatusCode();
                    if (statusCode == 200){
                        InputStream inputStream = httpResponse.getEntity().getContent();

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
                List<DataDataBean.NewslistBean> newslist = dataDataBean.getNewslist();
                //设置适配器
                MyAdapter myAdapter = new MyAdapter(newslist, MainActivity.this);
                listView.setAdapter(myAdapter);

            }
        };
        asyncTask.execute();

    }



}
