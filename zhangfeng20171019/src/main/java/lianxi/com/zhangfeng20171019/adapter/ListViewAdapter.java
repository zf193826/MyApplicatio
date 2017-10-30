package lianxi.com.zhangfeng20171019.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lianxi.com.zhangfeng20171019.R;
import lianxi.com.zhangfeng20171019.bean.DataDataBean;


/**
 * Created by 张峰 on 2017/10/19.
 */

public class ListViewAdapter extends BaseAdapter {
    Context context;
    List<DataDataBean.NewslistBean> list;

    public ListViewAdapter(Context context, List<DataDataBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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

        Imageholder holder;
        if (view == null){
            view = View.inflate(context, R.layout.item_image_layout, null);
            holder = new Imageholder();
            holder.imageView = (ImageView) view.findViewById(R.id.image_view);
            holder.textView = (TextView) view.findViewById(R.id.text_title);
            holder.textView = (TextView) view.findViewById(R.id.text_title);
            view.setTag(holder);
        }else{
            holder = (Imageholder) view.getTag();
        }
        //赋值
        holder.textView.setText(list.get(i).getTitle());

        //显示图片

        return view;



    }
    private class Imageholder{
        TextView textView;
        ImageView imageView;
    }

}