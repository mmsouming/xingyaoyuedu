package com.shinread.StarPlan.Parent.ui.activity.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.WebActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.AppUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Parent.service.AppService;
import com.shinread.StarPlan.Parent.ui.activity.LoginActivity;
import com.shinread.StarPlan.Parent.ui.activity.RegisterActivity;
import com.shinyread.StarPlan.Parent.R;

/**
 * Created by janecer on 2016/3/30.
 * email:jxc@fancyf.cn
 * des:
 */
public class SettingActivity extends BaseFragmentActivity implements View.OnClickListener {

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
                Intent i = new Intent(this, WebActivity.class);
                i.putExtra(WebActivity.WEB_URL, Constants.WEB_AGREEMENT);
                startActivity(i);
                break ;
            case R.id.tv_update_pwd://修改密码
                Intent iUpdate = new Intent(this, RegisterActivity.class) ;
                iUpdate.putExtra(RegisterActivity.EXTRA_TYPE, 2) ;
                startActivityForResult(iUpdate,REQ_UPDATE_PWD);
                break ;
            case R.id.btn_exit_login://退出登录
                AppService.realse();
                startActivity(new Intent(this, LoginActivity.class));

                Intent action = new Intent(ACTION_FINISH_ALL) ;
                sendBroadcast(action);
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
