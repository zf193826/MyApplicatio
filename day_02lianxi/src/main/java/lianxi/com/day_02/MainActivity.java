package lianxi.com.day_02;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                DataDataBean bean = (DataDataBean) msg.obj;
                List<DataDataBean.ResultBean.DataBean> list = bean.getResult().getData();
                //设置适配器
                MyAdapter adapter = new MyAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
    }
    /**
     * 点击事件取获取网络上的json数据
     * HttpUrlConnection请求的post方式
     */
    public  void getDataFromNet(View view){
        new Thread(){
            @Override
            public void run() {
                //获取数据的地址
                String path = "http://v.juhe.cn/toutiao/index";

                //把字符串转成网络访问数据的urL格式
                try {
                    URL url = new URL(path);

                    //打开连接
                   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);//读取超时
                    connection.setConnectTimeout(5000);//
                    //设置请求内容的类型
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    //设置可以向外输出...因为post方式的时候参数是以流的形式写给服务器,所以设置向外输出
                    connection.setDoOutput(true);
                    //参数
                    String params = "type=top&key=0ae4c26e5646d9e247b4be508df6b195";
                    connection.getOutputStream().write(params.getBytes());//代码写到这个位置,参数写给服务器
                    //获取
                    int responseCode = connection.getResponseCode();
                    //获取连接,200表示获取成功
                    if (responseCode == 200){
                        //获取到服务器返回的输入字节流...装的是json格式的数据
                        InputStream inputStream = connection.getInputStream();
                        //把字节流转成字符串
                        String json = streamToString(inputStream,"utf-8");
                        //解析获取到的json格式的字符串
                        Gson gson = new Gson();
                        DataDataBean bean = gson.fromJson(json, DataDataBean.class);

                        //处于子线程,不能设置适配器
                        Message message = Message.obtain();
                        message.what = 0;
                        message.obj = bean;
                        handler.sendMessage(message);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String streamToString(InputStream inputStream, String charset) {
        //将输入的字节流转为字符流
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
            //快速的读取需要缓冲流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();//字符串缓冲区
            while((s = bufferedReader.readLine())!= null){
                //添加到字符串缓冲区
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
