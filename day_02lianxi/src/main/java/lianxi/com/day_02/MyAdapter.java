package lianxi.com.day_02;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张峰 on 2017/9/27.
 */

public class MyAdapter extends BaseAdapter{
    Context context;
    List<DataDataBean.ResultBean.DataBean> list;
    public MyAdapter(Context context, List<DataDataBean.ResultBean.DataBean> list) {
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
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context,R.layout.list_item,null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) view.findViewById(R.id.image_view);
            holder.textView = (TextView) view.findViewById(R.id.text_view);
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }
        //设置显示
        holder.textView.setText(list.get(i).getTitle());

        new ImageHelper().displayImage(holder.imageView,list.get(i).getThumbnail_pic_s());


        return view;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textView;


    }
}
