package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;

/**
 * Created by wenxiyuan on 16/4/13.
 */
public class LoadTipManager {
    //LoadTipLayout loading;
    Activity mContext;
    public View mLoading;

    public LoadTipManager(Activity context, int id, final View.OnClickListener listener){
        this(context,null,id,listener);
    }
    public LoadTipManager(Activity context,View v, int id, final View.OnClickListener listener) {
        mContext = context;
        View view = null;
        if (v == null){
            view = context.findViewById(id);
        }else{
            view = v.findViewById(id);
        }
        if (view == null)
            return;

        ViewParent viewParent = view.getParent();
        if (viewParent instanceof RelativeLayout) {
            RelativeLayout parentLayout = (RelativeLayout) viewParent;

            if (parentLayout.findViewWithTag(30302) != null) {
                return;
            }

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            mLoading = layoutInflator.inflate(R.layout.cc_layout_loading,
                    null);
            mLoading.setLayoutParams(params);
            //mLoading.setTag(30302);
            cc_pv_loading = (com.wang.avi.AVLoadingIndicatorView) mLoading.findViewById(R.id.cc_pv_loading);
            cc_txt_load = (TextView) mLoading.findViewById(R.id.cc_txt_load);

            cc_layout_loading = (LinearLayout) mLoading.findViewById(R.id.cc_layout_loading);
            cc_layout_loadexception = (LinearLayout) mLoading.findViewById(R.id.cc_layout_loadexception);
            cc_layout_loadexception.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLoadding();
                    listener.onClick(v);
                }
            });

            parentLayout.addView(mLoading);

            showLoadding();
        }
    }


    private LinearLayout cc_layout_loading;
    private com.wang.avi.AVLoadingIndicatorView cc_pv_loading;
    private TextView cc_txt_load;
    private LinearLayout cc_layout_loadexception;


    public void showLoadSuccess() {
        if (mLoading == null) {
            return;
        }
        mLoading.setVisibility(View.GONE);

    }


    public void setBackgroundResource(int resid) {
        if (mLoading == null) {
            return;
        }
        mLoading.setBackgroundResource(resid);
    }

    public void setLoadingTxt(String text) {
        if (mLoading == null) {
            return;
        }
        cc_txt_load.setText(text);
    }

    public void showLoadding() {
        if (mLoading == null) {
            return;
        }
        mLoading.setVisibility(View.VISIBLE);
        cc_layout_loading.setVisibility(View.VISIBLE);
        cc_layout_loadexception.setVisibility(View.GONE);

    }

    public void showLoadException() {
        if (mLoading == null) {
            return;
        }
        mLoading.setVisibility(View.VISIBLE);
        cc_layout_loading.setVisibility(View.GONE);
        cc_layout_loadexception.setVisibility(View.VISIBLE);
    }
}
