package com.shinread.StarPlan.Teacher.ui.activity.work.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shinyread.StarPlan.Teacher.R;


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

    }
    private void initEvent(){
        btn_back.setOnClickListener(this);
        btn_right.setOnClickListener(this);
    }
    public void setRight(String msg){
        btn_right.setVisibility(View.VISIBLE);
        btn_right.setText(msg);

    } public void settitle(String msg){

        txt_title.setText(msg);

    }
    public  void setRelayoutBack(String msg,Activity context){
        if (msg.equals("userinfo_main")){
            title_bar_layout.setBackgroundColor(context.getResources().getColor(R.color.userinfo_main));
        }

    }


    @Override
    public void onClick(View view) {
        if(view.getId() == com.fancyfamily.primarylibrary.R.id.btn_back){
            context.finish();
        }

    }

}

