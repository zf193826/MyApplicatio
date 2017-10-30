package lianxi.com.yuekao_b;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import lianxi.com.yuekao_b.util.SharedUtil;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        int count = 3;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count < 0 ){ //跳转

                switch (msg.what){
                    case 0://第三个
                        Intent intent = new Intent(MainActivity.this,Zhu_Activity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case  1:
                        Intent intent2 = new Intent(MainActivity.this, ShowActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        break;

                }
                finish();

            }else{ //倒计时处理
                miao.setText(count + "s");
                count--;
                handler.sendEmptyMessageDelayed(1, 1000);

            }
        }
    };
    private TextView miao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        miao = (TextView) findViewById(R.id.miao);
        handler.sendEmptyMessageDelayed(1,1000);

        //进入页面之后获取存的那个boolean数据,判断...false表示没进入过,第一次进入
        boolean flag = SharedUtil.getBooleanData(MainActivity.this, "flag", false);
        if (flag){ ////已经进入过...延时跳转到第三个界
            handler.sendEmptyMessageDelayed(0,3000);
        }else{//存入已经进入的boolean数据,,,延时跳转到第二个界面
            SharedUtil.saveBooleanData(MainActivity.this, "flag", true);
            handler.sendEmptyMessageDelayed(1, 3000);


        }

    }

}
