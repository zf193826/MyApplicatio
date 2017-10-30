package lianxi.com.yuekao_b.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter{
	Context context;
	List<Integer> list;
	
	public ViewPagerAdapter(Context context, List<Integer> list) {
		this.context = context;
		this.list = list;

	}


	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(list.get(position));
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);

		container.addView(imageView);

		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
