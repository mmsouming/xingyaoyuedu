package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseDialog;


/**
 * Created by janecer on 2016/03/20.
 * 一个activity中 可以允许有一个DialogUtil，多个activity中不能共用同一个DialogUtil
 *
 * 显示在正中央
 */
public class DialogUtil {

    private BaseDialog myDialog;
    private TextView mTvTitle,mTvContent;
    private Button mBtnOk,mBtnCancel;

    /**
     * @param activity  需要在这个activity上显示的对话框
     * @param title  标题
     * @param content 内容
     * @param ok  设置positive按钮  传null或者"" 将显示确定
     * @param cancel  设置negative按钮 传null或"" 将显示取消
     * @param cancelable 是否可按返回键取消
     * @param okOrCancelClickListener  对按钮 点击取消或者确定的回调监听
     */
    public  void showDialog(Activity activity,String title,String content,String ok,String cancel,boolean cancelable, final OkOrCancelClickListener okOrCancelClickListener){
        if(null==myDialog){
            View view=activity.getLayoutInflater().inflate(R.layout.dialog_style,null);
            mTvTitle= (TextView) view.findViewById(R.id.tv_title);
            mTvContent= (TextView) view.findViewById(R.id.tv_content);
            mBtnOk= (Button) view.findViewById(R.id.btn_ok);
            mBtnCancel= (Button) view.findViewById(R.id.btn_cancel);

            myDialog=new BaseDialog(activity,view,R.style.customDialog);
        }
        mTvTitle.setText(title);
        mTvContent.setText(content);
        mBtnOk.setText(TextUtils.isEmpty(ok)?"确定":ok);
        mBtnCancel.setText(TextUtils.isEmpty(cancel)?"取消":cancel);
        myDialog.setCancelable(cancelable);
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okOrCancelClickListener.clickOk();
                myDialog.dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                okOrCancelClickListener.clickCanel();
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    public void setPositionTextColor(int color){
        if(null!=mBtnOk){
            mBtnOk.setTextColor(color);
        }
    }
    public void setCancelTextColor(int color){
        if(null!=mBtnCancel){
            mBtnCancel.setTextColor(color);
        }
    }

    public  void showDialog(Activity activity,String title,String content, final OkOrCancelClickListener okOrCancelClickListener){
        showDialog(activity, title, content, "","",true, okOrCancelClickListener);
    }

    public  void showDialog(Activity activity,String title,String content,String ok,String cancel, final OkOrCancelClickListener okOrCancelClickListener){
        showDialog(activity, title, content, "","",true, okOrCancelClickListener);
    }

    public void dimiss(){
        if(null!=myDialog&&myDialog.isShowing()){
            myDialog.dismiss();
        }
    }

    public void realse (){
        dimiss();
        myDialog = null ;
    }

    public interface OkOrCancelClickListener {
        /**
         * 点击了ok
         */
        void clickOk();

        /**
         * 点击了取消
         */
        void clickCanel();
    }


    public static Dialog creatRequestDialog(Context context, String tip) {

        Dialog dialog = new Dialog(context, R.style.dlg_request_style);
        dialog.setContentView(R.layout.dlg_request);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        dialog.setCancelable(false);
        int width = display.getWidth();

        lp.width = (int) (0.6 * width);

        TextView titleTxtv = (TextView) dialog.findViewById(R.id.tvLoad);
        if (tip == null || tip.length() == 0) {
            titleTxtv.setText("正在发送");
        } else {
            titleTxtv.setText(tip);
        }
        return dialog;
    }


}
