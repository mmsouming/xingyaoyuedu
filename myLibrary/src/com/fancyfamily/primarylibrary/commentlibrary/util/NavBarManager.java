package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;


/**
 * Created by wenxiyuan on 16/3/30.
 */
public class NavBarManager implements View.OnClickListener {

    private Activity context;


    public NavBarManager(Activity context){
        this.context = context;
        initRes();
        initEvent();
    }


    public RelativeLayout title_bar_layout;
    public ImageView btn_back;
    public TextView txt_title;
    public Button btn_right;

    private void initRes(){

        btn_back=(ImageView)context.findViewById(R.id.btn_back);
        txt_title=(TextView)context.findViewById(R.id.txt_title);
        btn_right=(Button)context.findViewById(R.id.btn_right);
        title_bar_layout=(RelativeLayout)context.findViewById(R.id.title_bar_layout);
    }
    private void initEvent(){
        btn_back.setOnClickListener(this);
        btn_right.setOnClickListener(this);
    }
    public void setRight(String msg){
        btn_right.setVisibility(View.VISIBLE);
        btn_right.setText(msg);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_back){
            context.finish();
        }

    }

}
