package lianxi.com.yuekao_b.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import lianxi.com.yuekao_b.Bean.BanKuaiBean;
import lianxi.com.yuekao_b.R;
import lianxi.com.yuekao_b.util.ImageLoaderUtil;


/**
 * Created by 张峰 on 2017/10/17.
 */

public class ListViewAdapter2 extends BaseAdapter{
    Context context;
    List<BanKuaiBean.ResultsBean> list;
    private int TEXT_ONLY = 0;
    private int IMAGE_TEXT = 1;



    public ListViewAdapter2(Context context, List<BanKuaiBean.ResultsBean> list) {
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImages() != null){
            return  IMAGE_TEXT;
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

                //holder.textView = view.findViewById(R.id.text_title);
                holder.textView = (TextView) view.findViewById(R.id.text_title);

                view.setTag(holder);

            }else {
                holder = (TextHolder) view.getTag();
            }

            holder.textView.setText(list.get(i).getDesc());

        }else if (getItemViewType(i)==IMAGE_TEXT){

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

            holder.textView.setText(list.get(i).getDesc());
            //图片
           // Log.i("----",list.get(i).getImg().getBytes()+"?imageView2/0/w/100");
            ImageLoader.getInstance().displayImage(list.get(i).getImages().get(0),holder.imageView, ImageLoaderUtil.getRoundOption());
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
