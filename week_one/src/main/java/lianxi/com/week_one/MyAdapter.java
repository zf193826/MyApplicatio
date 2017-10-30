package lianxi.com.week_one;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张峰 on 2017/10/9.
 */

class MyAdapter extends BaseAdapter{
    List<DataDataBean.NewslistBean> newslist;
    Context context;
    public MyAdapter(List<DataDataBean.NewslistBean> newslist, Context context) {
        this.newslist = newslist;
        this.context =context;
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public Object getItem(int i) {
        return newslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context,R.layout.item_layout,null);
            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.text_title);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(newslist.get(i).getTitle());
        return view;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }

}
