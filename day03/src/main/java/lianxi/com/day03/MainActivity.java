package lianxi.com.day03;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void  getData(View view){
        asyncAll();


    }

    private void asyncAll() {
        //需要一个异步任务对象,在异步任务中去处理耗时工作,比如获取网络,是放在子线程中
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            //在doInBackground之前调用的方法，也就是异步任务开始执行的第一个方法
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //在后台执行的方法...相当于子线程。。执行耗时的操作。。。不能再这个方法里面更新界面
            @Override
            protected String doInBackground(String... strings) {

                try {
                    URL url = new URL(strings[0]);
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
            //异步任务执行进度发生改变的时候调用的方法。。。这个方法不会自动调用，需要在doInBackGroud里面调用publishProgress()方法的时候会调用onProg


            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
            //发送任务执行的结果到主线程，，，，这个方法是处于主线程，，可以更新界面
            //String json 就是doInBackground返回的数据

            @Override
            protected void onPostExecute(String json) {
                Log.i("=================", json);
            }
            //异步任务对象如果需要执行,需要调用下面的方法。。。

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        };
        //异步任务对象如果需要执行,需要调用下面的方法
        asyncTask.execute("http://v.juhe.cn/toutiao/index?type=top&key=597b4f9dcb50e051fd725a9ec54d6653");
        //asyncTask.cancel(true);
    }

    private String streamToString(InputStream inputStream, String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s= null;
            StringBuilder builder = new StringBuilder();
            while((s = bufferedReader.readLine()) != null){
                builder.append(s);
            }
            bufferedReader.close();
            return  builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
