package lianxi.com.zhangfeng20171010;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

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
        lv = (ListView) findViewById(R.id.list_view);
        getDataFromNet();

    }

    private void getDataFromNet() {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String path="http://v.juhe.cn/weixin/query?key=88f7bbc507e3ecacfaeab2b47dd8936f&ps=30";
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
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
                /*Log.d("TAG","-------------------------6" +
                        "26");
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(s, DataDataBean.class);
                List<DataDataBean.ResultBean.ListBean> list1 = dataDataBean.getResult().getList();
                //List<DataDataBean.ResultBean.ListBean> list = dataDataBean.getResult().getList();
               // Log.d("TAG","-------------------------"+list1.toString());*/
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(s, DataDataBean.class);
                Log.d("TAG","-------------------------"+s);
                List<DataDataBean.ResultBean.ListBean> list = dataDataBean.getResult().getList();
                MyAdapter myAdapter = new MyAdapter(MainActivity.this, list);
                lv.setAdapter(myAdapter);



            }
        };
        asyncTask.execute();
    }

    private String streamToString(InputStream inputStream, String s) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,s);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String src= null;
            StringBuilder builder = new StringBuilder();
            while ((src = bufferedReader.readLine()) != null){
                builder.append(src);
            }

            bufferedReader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }

}
