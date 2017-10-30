package lianxi.com.yk_lianxi.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import lianxi.com.yk_lianxi.DataDataBean;
import lianxi.com.yk_lianxi.ImageLoaderUtil;
import lianxi.com.yk_lianxi.R;

/**
 * Created by 张峰 on 2017/10/13.
 */

class MyAdapter extends BaseAdapter{
    List<DataDataBean.DataBean> list;
    Context context;
    private int IMAGE_LEFT = 0;
    private int IMAGE_RIGHT = 1;


    public MyAdapter(List<DataDataBean.DataBean> list, Context context) {
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2 == 0){
            return  IMAGE_LEFT;
        }
        return  IMAGE_RIGHT;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == IMAGE_LEFT){
            LeftHolder holder;
            if (view == null){
                view = View.inflate(context,R.layout.left_layout,null);
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

        }else if (getItemViewType(i) == IMAGE_RIGHT){
            RightHolder holder;
            if (view == null){
                view = View.inflate(context, R.layout.right_layout,null);
                holder = new RightHolder();
                holder.textView = (TextView) view.findViewById(R.id.text_title);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);

                view.setTag(holder);
            }else{
                holder = (RightHolder) view.getTag();
            }
            //赋值
            holder.textView.setText(list.get(i).getTitle());
            ImageLoader.getInstance().displayImage(list.get(i).getImg(),holder.imageView, ImageLoaderUtil.getDefaultOption());

        }
        return view;
    }
    private class LeftHolder{
        TextView textView;
        ImageView imageView;

    }
    private class RightHolder{
        TextView textView;
        ImageView imageView;
    }
}
