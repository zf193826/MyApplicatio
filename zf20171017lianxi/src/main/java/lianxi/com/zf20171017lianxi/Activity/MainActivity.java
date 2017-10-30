package lianxi.com.zf20171017lianxi.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import lianxi.com.zf20171017lianxi.R;

public class MainActivity extends AppCompatActivity {
    private TextView miao;
    private Handler handler = new Handler(){
        int count = 3;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count<0){//跳转
                startActivity(new Intent(MainActivity.this,Zhu_Activity.class));
                finish();
            }else{//倒计时处理
                miao.setText(count + "s");
                count--;
                handler.sendEmptyMessageDelayed(1,1000);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //发送延时消息.三秒跳转
        miao = (TextView) findViewById(R.id.Text_miao);
        handler.sendEmptyMessageDelayed(1,1000);
    }
}
