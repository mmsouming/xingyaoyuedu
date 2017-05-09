package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.OkHttpUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.DialogUtil;

/**
 * Created by Administrator on 2016/3/8.
 */
public class BaseV4Fragment extends android.support.v4.app.Fragment  {

    /** 主要用来Log日志调试，okHttpUtil根据TAG取消网络请求 **/
    protected String TAG  ;

    protected View mContentView ;

    private Dialog mLoaddingDialog ;

    protected String setTag(){
        return null ;
    } ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = TextUtils.isEmpty(setTag()) ? this.getClass().getSimpleName() : setTag() ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(TAG);
    }

    /**
     * 显示正在加载...对话框
     * @param loaddingText
     */
    protected void showLoaddingDialog (String loaddingText,boolean isCancelable){
        if(null == mLoaddingDialog) {
            mLoaddingDialog = DialogUtil.creatRequestDialog(getActivity(),"正在提交");
        }
        if(mLoaddingDialog.isShowing()){
            return ;
        }
        mLoaddingDialog.setCancelable(isCancelable);
        //((LoaddingImageView)mLoaddingDialog.findViewById(R.id.liv_loadding)).setVisibility(View.VISIBLE);
       // TextView mTvTip  = (TextView) mLoaddingDialog.findViewById(R.id.tv_tip);
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
}
