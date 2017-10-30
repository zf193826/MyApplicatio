package lianxi.com.zhangfeng20171011;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 张峰 on 2017/10/11.
 */

class MyAdapter extends BaseAdapter{
    Context context;
    List<DataDataBean.DataBean> list;
    private int IMAGE_Left = 0;
    private int IMAGE_RIGHT = 1;

    public MyAdapter(Context context, List<DataDataBean.DataBean> list) {

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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2 == 0){
            return  IMAGE_RIGHT;
        }
        return  IMAGE_RIGHT;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == IMAGE_RIGHT){
            LeftHolder holder;
            if (view == null){
                view = View.inflate(context,R.layout.left_layout,null);
                holder = new LeftHolder();
                holder.textView = (TextView) view.findViewById(R.id.text_title);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);


            }else{
                holder = (LeftHolder) view.getTag();
            }
            //赋值
            holder.textView.setText(list.get(i).getTitle());
            ImageLoader.getInstance().displayImage(list.get(i).getImg(),holder.imageView,ImageLoaderUtil.getDefaultOption());

        }else if (getItemViewType(i) == IMAGE_RIGHT){
            RightHolder holder;
            if (view == null){
                view = View.inflate(context,R.layout.left_layout,null);
                holder = new RightHolder();
                holder.textView = (TextView) view.findViewById(R.id.text_title);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);


            }else{
                holder = (RightHolder) view.getTag();
            }
            //赋值
            holder.textView.setText(list.get(i).getTitle());
            ImageLoader.getInstance().displayImage(list.get(i).getImg(),holder.imageView,ImageLoaderUtil.getDefaultOption());

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
