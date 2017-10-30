package lianxi.com.rikao6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by 张峰 on 2017/10/8.
 */

public class MyAdapater extends BaseAdapter {
    List<DataDataBean.NewslistBean> list;
    Context content;

    public MyAdapater(List<DataDataBean.NewslistBean> newslist, Context content) {
        this.list = list;
        this.content = content;
    }

    @Override
    public int getCount() {
        return list.size() ;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(content, R.layout.item_layout, null);
            holder = new ViewHolder();
            holder.imagerView = (ImageView) view.findViewById(R.id.image_view);
            holder.text_time = (TextView) view.findViewById(R.id.text_time);
            holder.text_title = (TextView) view.findViewById(R.id.text_title);
            view.setTag(holder);
        }else{
           holder = (ViewHolder) view.getTag();
        }
        //赋值
        holder.text_title.setText(list.get(i).getTitle());
        holder.text_time.setText(list.get(i).getCtime());
        //显示图片
        dispalyImage(list.get(i).getPicUrl(),holder.imagerView);
        return view;
    }

    private void dispalyImage(final String picUrl, final ImageView imagerView) {
        AsyncTask<Void, Void, Bitmap> asyncTask = new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... voids) {
                try {
                    URL url = new URL(picUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    //获取成功码
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                imagerView.setImageBitmap(bitmap);
            }
        };
        asyncTask.execute();

    }

    private class ViewHolder{
        ImageView imagerView;
        TextView text_title;
        TextView text_time;

    }
}
