package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;


public class DialogTip extends Dialog implements
		View.OnClickListener {

	public interface OnChooseDialog {
		public void ChooseResult(Boolean isConfirm);
	}

	OnChooseDialog listenser;

	public DialogTip setListenser(OnChooseDialog listenser) {
		this.listenser = listenser;
		return this;
	}

	Activity mContext;

	public DialogTip(Activity context, String mtitle, String mcontent, int rid) {
	    super(context, R.style.dialog);
	    
	    setContentView(rid);
        mContext = context;
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        lp.width = (int) (0.8 * width);

        this.title = (TextView) findViewById(R.id.title);
        this.content = (TextView) findViewById(R.id.content);
        
        title.setText(mtitle);
        content.setText(mcontent);
        
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        btn_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
	}

	public void setOkText(String text){
	    btn_confirm.setText(text);
	}
	public void setcancelText(String text){
	    btn_cancel.setText(text);
    }
	
	public DialogTip(Activity context, String mtitle, String mcontent) {
		super(context, R.style.dialog);
		setContentView(R.layout.dlg_custom_tip);
		mContext = context;
		Window window = getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;

		lp.width = (int) (0.8 * width);

		this.title = (TextView) findViewById(R.id.cc_txt_title);
		this.content = (TextView) findViewById(R.id.content);
		
		title.setText(mtitle);
		if (mcontent == null) {
		    content.setVisibility(View.GONE);
        }else {
            content.setVisibility(View.VISIBLE);
            content.setText(mcontent);
        }
		
		
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_confirm = (Button) findViewById(R.id.btn_confirm);

		btn_cancel.setOnClickListener(this);
		btn_confirm.setOnClickListener(this);

	}

	private TextView title;
	public Button btn_cancel;
	public Button btn_confirm;
	private TextView content;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btn_cancel){
			if (listenser != null) {
				listenser.ChooseResult(false);
			}
		}else if(v.getId() == R.id.btn_confirm){
			if (listenser != null) {
				listenser.ChooseResult(true);
			}
		}

		dismiss();
	}

}
