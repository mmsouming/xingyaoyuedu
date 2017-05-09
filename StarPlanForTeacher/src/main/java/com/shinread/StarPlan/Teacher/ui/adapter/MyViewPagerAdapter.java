package com.shinread.StarPlan.Teacher.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

public class MyViewPagerAdapter extends PagerAdapter {//共用viewpager适配器
	List<View> views = new ArrayList<View>();

	public MyViewPagerAdapter(List<View> lists) {
		super();
		this.views = lists;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// 每次滑动时生成当前的组件
		((ViewGroup) container).addView(views.get(position));
		return views.get(position);
	}

	@Override // 滑动切换时销毁当前的组件
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(views.get(position));

	}
}
