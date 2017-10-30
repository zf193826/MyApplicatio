package lianxi.com.zhangfeng20171010;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 张峰 on 2017/10/10.
 */

class MyAdapter extends BaseAdapter{
    Context context;
    List<DataDataBean.ResultBean.ListBean> list;
    private int TYTLE_ONLY = 0;
    private int IMAGE_LEFT = 1;
    private int IMAGE_RIGHT = 2;
    private int IMAGE_BOTTOM = 3;
    public MyAdapter(Context context, List<DataDataBean.ResultBean.ListBean> list) {
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
    public int getViewTypeCount() {

        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%4 ==0){
            return  TYTLE_ONLY;
        }else if(position%4 ==1){
            return IMAGE_LEFT;
        }else if (position%4 == 2){
            return  IMAGE_RIGHT;
        }
        return IMAGE_BOTTOM;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (getItemViewType(i) == TYTLE_ONLY){
            TitleHolder holder;
            if (view == null){

                view = View.inflate(context,R.layout.item_title_layout,null);
                holder = new TitleHolder();
                holder.textView = (TextView) view.findViewById(R.id.text_title);
                view.setTag(holder);
            }else {
                holder = (TitleHolder) view.getTag();
            }
            holder.textView.setText(list.get(i).getTitle());

        }else if (getItemViewType(i)==IMAGE_LEFT){
            ImageLeftHolder holder;
            if (view == null){
                view = View.inflate(context,R.layout.item_layout,null);
                holder = new ImageLeftHolder();
                holder.textView = (TextView) view.findViewById(R.id.text_title);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);
                view.setTag(holder);
            }else{
                holder = (ImageLeftHolder) view.getTag();
            }
            //显示字符串
            holder.textView.setText(list.get(i).getTitle());
            //显示图片
            ImageLoader.getInstance().displayImage(list.get(i).getFirstImg(),holder.imageView,ImageLoaderUtil.getDefaultOption());

        }else  if (getItemViewType(i) == IMAGE_RIGHT){
            ImageRightHolder holder;
            if (view == null){
                view = View.inflate(context,R.layout.item_right_layout,null);
                holder = new ImageRightHolder();
                holder.textView = (TextView) view.findViewById(R.id.text_title);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);
                view.setTag(holder);
            }else{
                holder = (ImageRightHolder) view.getTag();
            }
            holder.textView.setText(list.get(i).getTitle());
            ImageLoader.getInstance().displayImage(list.get(i).getFirstImg(),holder.imageView,ImageLoaderUtil.getDefaultOption());

        }else  if (getItemViewType(i) == IMAGE_BOTTOM){
            ImageBottomHolder holder;
            if (view == null){
                view = View.inflate(context,R.layout.item_bottom_layout,null);
                holder = new ImageBottomHolder();
                holder.textView = (TextView) view.findViewById(R.id.text_title);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);
                view.setTag(holder);

            }else {
                holder = (ImageBottomHolder) view.getTag();
            }
            holder.textView.setText(list.get(i).getTitle());
            ImageLoader.getInstance().displayImage(list.get(i).getFirstImg(),holder.imageView,ImageLoaderUtil.getDefaultOption());

        }

        return view;
    }
    private class TitleHolder{
        TextView textView;
    }
    private  class  ImageLeftHolder{
        TextView textView;
        ImageView imageView;
    }
    private class ImageRightHolder{
        TextView textView;
        ImageView imageView;
    }
    private  class  ImageBottomHolder{
        TextView textView;
        ImageView imageView;

    }

}
