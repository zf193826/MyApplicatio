package lianxi.com.zhangfeng20171012;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 张峰 on 2017/10/12.
 */

public class MyAdapter extends BaseAdapter{
    List<DataDataBean.DataBean> list;
    Context context;

    public MyAdapter(List<DataDataBean.DataBean> list, Context context) {
        this.context =context;
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
        LeftHolder holder;
        if (view == null){
            view = View.inflate(context,R.layout.item_layout,null);
            holder = new LeftHolder();
            holder.textView = (TextView) view.findViewById(R.id.text_title);
            holder.imageView = (ImageView) view.findViewById(R.id.image_view);
            view.setTag(holder);

        }else{
            holder = (LeftHolder) view.getTag();
        }
        //赋值
        holder.textView.setText(list.get(i).getTitle());
        ImageLoader.getInstance().displayImage(list.get(i).getImg(),holder.imageView,ImageLoaderUtil.getDefaultOption());
        return view;
    }
    private class LeftHolder{
        ImageView imageView;
        TextView textView;
    }
}
