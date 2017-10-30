package lianxi.com.zhangfeng20171024.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import lianxi.com.zhangfeng20171024.R;
import lianxi.com.zhangfeng20171024.bean.DataDataBean;
import lianxi.com.zhangfeng20171024.util.ImageLoaderUtil;


/**

 *
 * 多条目加载
 */
public class ListViewAdapter extends BaseAdapter {
    Context context;
    List<DataDataBean.ResultBean.DataBean> list;
    private int TEXT_ONLY = 0;
    private int IMAGE_TEXT = 1;

    public ListViewAdapter(Context context, List<DataDataBean.ResultBean.DataBean> list) {
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
    public int getViewTypeCount() {//返回有多少种条目的视图
        return 2;
    }

    @Override
    public int getItemViewType(int position) {//返回当前条目视图的类型

        //根据当前位置是否有图片决定,该条目的类型是什么
        if (list.get(position).getThumbnail_pic_s()!= null){
            return IMAGE_TEXT;
        }

        return TEXT_ONLY;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == TEXT_ONLY){
            TextHolder holder;
            if (view == null){
                view = View.inflate(context, R.layout.item_text_layout,null);
                holder = new TextHolder();

                holder.textView = (TextView) view.findViewById(R.id.text_title);

                view.setTag(holder);


            }else {
                holder = (TextHolder) view.getTag();
            }

            holder.textView.setText(list.get(i).getTitle());

        }else if (getItemViewType(i) == IMAGE_TEXT){

            ImageHolder holder;
            if (view == null){
                view = View.inflate(context, R.layout.item_iamge_layout,null);
                holder = new ImageHolder();

                holder.textView = (TextView) view.findViewById(R.id.text_title);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);

                view.setTag(holder);


            }else {
                holder = (ImageHolder) view.getTag();
            }

            holder.textView.setText(list.get(i).getTitle());
            //设置图片的显示
            ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),holder.imageView, ImageLoaderUtil.getRoundOption());


        }

        return view;
    }

    private class TextHolder{
        TextView textView;
    }
    private class ImageHolder{
        TextView textView;
        ImageView imageView;
    }
}