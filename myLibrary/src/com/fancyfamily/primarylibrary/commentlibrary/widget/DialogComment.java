package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.fancyfamily.primarylibrary.R;


public class DialogComment extends Dialog implements
		View.OnClickListener {

	public interface OnDialogComment {
		public void ChooseResult(String content);
	}

	OnDialogComment listenser;

	public DialogComment setListenser(OnDialogComment listenser) {
		this.listenser = listenser;
		return this;
	}

	Activity mContext;



    public Button zan;
    public EditText book_comment;

	public DialogComment(Activity context) {
		super(context, R.style.dialog);
		setContentView(R.layout.layout_comment_edit);
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

        book_comment = (EditText) findViewById(R.id.book_comment);
        zan = (Button) findViewById(R.id.zan);
        zan.setOnClickListener(this);

	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.zan){
			if (listenser != null) {
				listenser.ChooseResult(book_comment.getText().toString().trim());
			}
		}

		dismiss();
	}

}
