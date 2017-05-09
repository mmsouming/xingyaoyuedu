package com.shinread.StarPlan.Teacher.ui.activity.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.WebActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.shinread.StarPlan.Teacher.ui.activity.LoginActivity;
import com.shinread.StarPlan.Teacher.ui.activity.RegisterActivity;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.fancyfamily.primarylibrary.commentlibrary.util.AppUtil;
import com.shinyread.StarPlan.Teacher.R;

/**
 * Created by janecer on 2016/3/30.
 * email:jxc@fancyf.cn
 * des:
 */
public class      SettingActivity extends BaseFragmentActivity implements View.OnClickListener {

    public static final int REQ_UPDATE_PWD = BASE_CODE + 1;

    private TextView mtvCurrentVersionVal ; //星耀平台版本号

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_setting);

        initViews() ;

        initDatas();
    }

    private void initDatas() {
        mtvCurrentVersionVal.setText(AppUtil.getVersion(this.getApplicationContext()));
    }

    private void initViews() {
        TitleBar tb = (TitleBar) findViewById(R.id.tb);
        tb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mtvCurrentVersionVal = (TextView) findViewById(R.id.tv_current_version_val) ;

        findViewById(R.id.tv_protocol).setOnClickListener(this);
        findViewById(R.id.tv_update_pwd).setOnClickListener(this);
        findViewById(R.id.btn_exit_login).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_protocol://星耀平台协议
                Intent protocolIntent = new Intent(this,WebActivity.class) ;
                protocolIntent.putExtra(WebActivity.WEB_URL, Constants.WEB_AGREEMENT);
                startActivity(protocolIntent);
                break ;
            case R.id.tv_update_pwd://修改密码
                Intent iUpdate = new Intent(this, RegisterActivity.class) ;
                iUpdate.putExtra(RegisterActivity.EXTRA_TYPE, 2) ;
                startActivityForResult(iUpdate,REQ_UPDATE_PWD);
                break ;
            case R.id.btn_exit_login://退出登录
                // TODO: 2016/4/19 清空当前登陆用户的信息 
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break ;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_UPDATE_PWD && resultCode == RESULT_OK) { //密码修改成功

        }
    }
}
