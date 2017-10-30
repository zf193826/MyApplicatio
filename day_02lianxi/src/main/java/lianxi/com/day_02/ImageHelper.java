package lianxi.com.day_02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 张峰 on 2017/9/27.
 */

public class ImageHelper {
    private ImageView imageView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                Bitmap bitmap = (Bitmap) msg.obj;
                imageView.setImageBitmap(bitmap);
            }
        }
    };


    /**
     * 根据一个图片的路径设置显示到imageView上
     * @param imageView
     * @param
     */
    public void displayImage(ImageView imageView, final String path) {
        this.imageView = imageView;

        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    //读取
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();

                        //转成图片
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        //发送
                        Message message = Message.obtain();
                        message.what = 1;
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
