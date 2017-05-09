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
import com.fancyfamily.primarylibrary.commentlibrary.lib.wheelview.adapter.ArrayWheelAdapter;


public class DialogWheelPicker extends Dialog implements
		View.OnClickListener {

	public interface OnDialogWheelPicker{
		public void ChooseResult(String content);
	}

	OnDialogWheelPicker listenser;

	public DialogWheelPicker setListenser(OnDialogWheelPicker listenser) {
		this.listenser = listenser;
		return this;
	}

	Activity mContext;

	String chooseStr;
	TextView btn_cancel;
	TextView btn_sure;
	OnWheelScrollListener onWheelScrollListener;
	WheelView wv_1,wv_2,wv_3;
	String mDatas[];
	public DialogWheelPicker(Activity context,  String datas[]) {
		super(context, R.style.customDialog);
		setContentView(R.layout.cc_dialog_wheel_picker);
		mContext = context;
		mDatas = datas;
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
		wv_1 = (WheelView)findViewById(R.id.wv_1);
		wv_1.setVisibility(View.VISIBLE);
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

				int index = wheel.getCurrentItem() + 1;
				chooseStr = mDatas[index - 1];

			}
		};
		wv_1.setViewAdapter(new ArrayWheelAdapter<String>(mContext,datas));
		wv_1.setCyclic(false);//是否可循环滑动
		wv_1.addScrollingListener(onWheelScrollListener);
		if (datas.length >0 && chooseStr == null){
			chooseStr = mDatas[0];
		}
		wv_1.setCurrentItem(getLen(datas,chooseStr));
		setCancelable(true);

	}

	public int getLen(String[] strs, String str) {
		int size = strs.length;
		for (int i = 0; i < size; i++) {
			if (strs[i].equals(str)) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btn_sure){

			if (listenser != null) {
				listenser.ChooseResult(chooseStr);
			}
		}

		dismiss();
	}


}
