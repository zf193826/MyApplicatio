package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Bitmap bitmap  = (Bitmap) msg.obj;
                //设置显示
               iv.setImageBitmap(bitmap);
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.image_view);


    }
    /**
     * 获取图片的点击事件
     */
    public void  getPic(View view){

        new Thread(){
            @Override
            public void run() {
                //图片的地址
                String  path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503854327078&di=08bdf32f7a117deafd580ca006b80a67&imgtype=0&src=http%3A%2F%2Fnews.k618.cn%2Fpic%2Fdmyx%2F201505%2FW020150501335817970176.jpg";
                //把一个字符串类型的地址转换为网络上的路径
                try {
                    URL url = new URL(path);
                    //3.使用url路径打开一个连接...
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //4.对connnection进行设置
                    //4.1设置请求方式....默认是get方式,并且get方式是使用最多的
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    //5.获取
                    //5.1获取一个响应码200成功
                    int responseCode = connection.getResponseCode();
                    if (responseCode ==200){//200表示请求成功
                        //5.2
                        InputStream inputStream = connection.getInputStream();
                        //把字符流转换为一张图片....encode编码，.....decode解码
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                        Message message = Message.obtain();
                        message.what = 0;
                        message.obj = bitmap;
                        handler.sendMessage(message);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }
}
