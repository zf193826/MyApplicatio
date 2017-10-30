package lianxi.com.zhangfeng20170929;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ListView lv;
    private RadioButton rb1;
    private RadioButton rb2;

    /**
     * 2017年9月29日08:29:44
     * @param savedInstanceState
     * 作用：实现两个按钮的监听方法,并且进行数据的展示
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        rg = (RadioGroup) findViewById(R.id.rg);
        lv = (ListView) findViewById(R.id.list_view);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);



        //按钮的点击事件
       rb1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //创建httpclient实例
               getHttpClient();

           }
       });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
                    String s = "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";

                    @Override
                    protected String doInBackground(String... strings) {


                        try {
                            URL url = new URL(s);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setReadTimeout(5000);
                            connection.setConnectTimeout(5000);

                            int responseCode = connection.getResponseCode();
                            if (responseCode == 200){
                                InputStream inputStream = connection.getInputStream();
                                String  json = streamToString(inputStream,"utf-8");
                                return  json;

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        Toast.makeText(MainActivity.this, s,Toast.LENGTH_SHORT).show();
                    }
                };
                asyncTask.execute();


            }
        });



    }

    private void getHttpClient() {
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();
                        String  json = streamToString(inputStream,"utf-8");

                        return  json;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                Log.i("==============",s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        };
        asyncTask.execute("https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10");


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
