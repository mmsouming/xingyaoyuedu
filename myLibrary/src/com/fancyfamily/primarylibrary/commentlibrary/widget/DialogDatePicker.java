package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.lib.wheelview.OnWheelScrollListener;
import com.fancyfamily.primarylibrary.commentlibrary.lib.wheelview.WheelView;
import com.fancyfamily.primarylibrary.commentlibrary.lib.wheelview.adapter.NumericWheelAdapter;

import java.util.Calendar;


public class DialogDatePicker extends Dialog implements
		View.OnClickListener {

	public interface OnDialogWheelPicker{
		void ChooseResult(String date);
	}

	OnDialogWheelPicker listenser;

	public DialogDatePicker setListenser(OnDialogWheelPicker listenser) {
		this.listenser = listenser;
		return this;
	}

	Activity mContext;

	private OnWheelScrollListener onWheelScrollListener;

	public final String YEAR = "年";
	public final String MONTH = "月";
	public final String DAY = "日";

	public DialogDatePicker(Activity context) {
		super(context, R.style.customDialog);
		setContentView(R.layout.cc_dialog_wheel_picker);
		mContext = context;
		Window window = getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
        window.setGravity(Gravity.BOTTOM);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		lp.width =  width;
		//lp.height =  height;

		window.setWindowAnimations(R.style.dialogWindowAnim);
		mWvYear = (WheelView) findViewById(R.id.wv_1);
		mWvMonth = (WheelView) findViewById(R.id.wv_2);
		mWvDay = (WheelView) findViewById(R.id.wv_3);
		mWvYear.setVisibility(View.VISIBLE);
		mWvMonth.setVisibility(View.VISIBLE);
		mWvDay.setVisibility(View.VISIBLE);

		btn_cancel = (TextView) findViewById(R.id.btn_cancel);
		btn_sure = (TextView) findViewById(R.id.btn_sure);
		btn_cancel.setOnClickListener(this);
		btn_sure.setOnClickListener(this);

		onWheelScrollListener = new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				int n_year = mWvYear.getCurrentItem() + 1950;//年
				int n_month = mWvMonth.getCurrentItem() + 1;//月

				initWvDay(mWvYear, mWvMonth, mWvDay);

				int day = mWvDay.getCurrentItem() + 1;
				chooseDateStr =  n_year + YEAR + n_month + MONTH + day + DAY;
			}
		};

		Calendar c = Calendar.getInstance();
		int norYear = c.get(Calendar.YEAR);
		NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(mContext, 1950, norYear);
		numericWheelAdapter1.setLabel("");
		mWvYear.setViewAdapter(numericWheelAdapter1);
		mWvYear.setCyclic(true);//是否可循环滑动
		mWvYear.setCurrentItem(norYear - 1950);
		mWvYear.addScrollingListener(onWheelScrollListener);

		NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(mContext, 1, 12, "%02d");
		numericWheelAdapter2.setLabel(MONTH);
		mWvMonth.setViewAdapter(numericWheelAdapter2);
		mWvMonth.setCyclic(true);
		mWvMonth.addScrollingListener(onWheelScrollListener);

		initWvDay(mWvYear, mWvMonth, mWvDay);
		mWvDay.setCyclic(true);
		mWvDay.addScrollingListener(onWheelScrollListener);

		setCancelable(true);

	}

	WheelView mWvYear,mWvMonth,mWvDay;
	TextView btn_cancel;
	TextView btn_sure;
	String chooseDateStr;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btn_sure){

			int n_year = mWvYear.getCurrentItem() + 1950;//年
			int n_month = mWvMonth.getCurrentItem() + 1;//月
			int day = mWvDay.getCurrentItem() + 1;
			chooseDateStr =  n_year + YEAR + n_month + MONTH + day + DAY;

			if (listenser != null) {
			 	listenser.ChooseResult(chooseDateStr);
			}
		}

		dismiss();
	}

	/**
	 * 根据年份和月份显示一个月的相应日期
	 *
	 * @param mWvYear
	 * @param mWvMonth
	 * @param mWvDay
	 */
	private void initWvDay(WheelView mWvYear, WheelView mWvMonth, WheelView mWvDay) {
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(mContext, 1, getDay(mWvYear.getCurrentItem() + 1950, mWvMonth.getCurrentItem() + 1), "%02d");
		numericWheelAdapter.setLabel(DAY);
		mWvDay.setViewAdapter(numericWheelAdapter);
	}

	/**
	 * @param year
	 * @param month
	 * @return
	 */
	private int getDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
			case 0:
				flag = true;
				break;
			default:
				flag = false;
				break;
		}
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				break;
			case 2:
				day = flag ? 29 : 28;
				break;
			default:
				day = 30;
				break;
		}
		return day;
	}
}
