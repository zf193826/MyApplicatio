package lianxi.com.day03_lianxi;

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
    public void getData(View view){
        //需要一个异步任务对象...在异步任务中去处理耗时操作,如获取网络,是放在子线程中（后台线程中
        // ）
        /**
         * 第一个参数：params一般是执行任务的时候传递过来得到数据m一般是访问的url，可以为void
         *Progress 当前这个任务执行的进度....如果需要的话一般是integer，，，不需要的话是void
         * Result 异步任务执行完成以后返回的结果。。如果没有结果,用void
         */
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {

            //在后台执行的方法。。。相当于子线程。。。执行耗时的操作。。。不能再这个方法里面执行更新界面
            //                    @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    //获取状态码
                    int responseCode = connection.getResponseCode();
                    //判断
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();
                        //字节流转字符串
                        String s = streamToString(inputStream,"utf-8");
                        return  s;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            //发送任务执行的结果到主线程，，，这个方法是处于主线程，，，可以更新界面，相当于handler机制
            //string json就是doInBackground返回的数据
            @Override
            protected void onPostExecute(String s) {
                //可以解析返回到的json字符串
                Log.i("================", s);
            }
        };
        //异步任务对象如果需要执行，需要调用下面的方法

        asyncTask.execute("http://v.juhe.cn/toutiao/index?type=top&key=597b4f9dcb50e051fd725a9ec54d6653");
    }

    private String streamToString(InputStream inputStream, String s) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, s);
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
}
