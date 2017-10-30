package lianxi.com.yuekao_b;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lianxi.com.yuekao_b.adapter.ViewPagerAdapter;

public class ShowActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button button;
    private List<Integer> list;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show);
        //得到SharedPreferences
        preferences = getSharedPreferences("sadsad", MODE_PRIVATE);

        //设置初始值
        boolean boo = preferences.getBoolean("boo", false);
        if (boo){
            Intent it=new Intent(ShowActivity.this,Zhu_Activity.class);
            startActivity(it);
            finish();
        }else{

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        button = (Button) findViewById(R.id.button);
        list = new ArrayList<Integer>();
        list.add(R.drawable.aa);
        list.add(R.drawable.bb);
        list.add(R.drawable.cc);
        ViewPagerAdapter adapter = new ViewPagerAdapter(ShowActivity.this,list);
        viewPager.setAdapter(adapter);
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == list.size()-1){
                        button.setVisibility(View.VISIBLE);


                    }else {

                        button.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });



        }
        editor = preferences.edit();
        editor.putBoolean("boo", true);
        editor.commit();
    }

    public void tiaoZhuan(View view){
        //跳转
        Intent intent = new Intent(ShowActivity.this,Zhu_Activity.class);
        startActivity(intent);
        finish();

    }
}
