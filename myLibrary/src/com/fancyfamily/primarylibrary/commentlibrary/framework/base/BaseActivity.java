package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import com.fancyfamily.primarylibrary.commentlibrary.util.DialogUtil;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * author:janecer on 2016/2/10 11:55
 * email:janecer@sina.cn
 *
 * 简单的界面展示可继承此类
 */

public class BaseActivity extends AutoLayoutActivity {

    public static final String ACTION_FINISH_ALL = "action_finish_all" ;
    public static final String EXTRA_EXPECT = "extra_expect" ;

    /** 主要用来Log日志调试，okHttpUtil根据TAG取消网络请求 **/
    protected String TAG  ;

    protected Dialog mLoaddingDialog ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // FFApplication.instance.addActivity(this);

        TAG = TextUtils.isEmpty(setTag()) ? BaseActivity.class.getSimpleName() : setTag() ;

        registerFinishCast();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // FFApplication.instance.removeActivity(this);
        //OkHttpUtils.getInstance().cancelTag(TAG);

        if(null != finishReceiver) {
            this.unregisterReceiver(finishReceiver);
        }
    }

    protected String setTag(){
        return null ;
    } ;


    protected void registerFinishCast(){
        if(null == finishReceiver){
            finishReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(intent.getAction().equals(ACTION_FINISH_ALL)&&!TAG.equals(intent.getStringExtra(EXTRA_EXPECT))){
                        finish();
                    }
                }
            } ;
        }
        IntentFilter intentFilter = new IntentFilter(ACTION_FINISH_ALL) ;
        this.registerReceiver(finishReceiver,intentFilter) ;
    }

    /**
     * 显示正在加载...对话框
     * @param loaddingText
     */
    protected void showLoaddingDialog (String loaddingText,boolean isCancelable){
        if(null == mLoaddingDialog) {

            mLoaddingDialog = DialogUtil.creatRequestDialog(this,"正在提交");
        }
        if(mLoaddingDialog.isShowing()){
            return ;
        }
        mLoaddingDialog.setCancelable(isCancelable);

        //TextView mTvTip  = (TextView) mLoaddingDialog.findViewById(R.id.tv_tip);
       // mTvTip.setText(TextUtils.isEmpty(loaddingText) ? getString(R.string.loading_txt) : loaddingText);
        mLoaddingDialog.show();
    }

    /**
     * 默认，不可手动取消对话框
     */
    protected void showLoaddingDialog(){
        showLoaddingDialog(null ,false);
    }

    /**
     * 隐藏对话框
     */
    protected void dismissLoaddingDialog(){
        if(null == mLoaddingDialog || !mLoaddingDialog.isShowing()){
            return ;
        }

        mLoaddingDialog.dismiss();
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftInput(Context context) {
        InputMethodManager manager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
        if (getCurrentFocus() != null) {
//            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    /**
     * 显示软键盘
     */
    protected void showSoftInput() {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public BroadcastReceiver finishReceiver = null ;
}
