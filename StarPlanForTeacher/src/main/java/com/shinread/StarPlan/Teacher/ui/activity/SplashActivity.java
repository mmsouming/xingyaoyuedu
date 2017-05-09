package com.shinread.StarPlan.Teacher.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoginManager;
import com.shinyread.StarPlan.Teacher.R;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by janecer on 2016/4/8.
 * email:jxc@fancyf.cn
 * des:
 */
public class SplashActivity extends BaseFragmentActivity implements View.OnClickListener, LoginManager.LoginListener {

    private final static int REQ_COUNT = BASE_CODE + 1 ;

    private LoginManager loginManager ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        findViewById(R.id.btn_skip).setOnClickListener(this);

        loginManager = new LoginManager(this) ;

        sendEmptyUiMessageDelayed(REQ_COUNT,1 * 1000);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip:
                removeUiMessages(REQ_COUNT);
                sendEmptyUiMessage(REQ_COUNT);
                break ;
        }
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case REQ_COUNT:
                //showLoaddingDialog();
                loginManager.autoLogin(this);
                break ;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }


    private void toNextPage() {

        Intent intent =new Intent(this,MainTabActivity.class) ;
        intent.putExtra(MainTabActivity.EXTRA_SHOW_INDEX,0) ;
        startActivity(intent);

        finish();
    }

    @Override
    public void onResult(int code, String msg) {
        dismissLoaddingDialog();
        switch (code) {
            case LoginManager.CODE_LOGIN_SUCCESS:
                toNextPage();
                break ;
            case LoginManager.CODE_GET_LOGIN_INFO_FAIL:
            case LoginManager.CODE_LOGIN_FAIL:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeUiMessages(REQ_COUNT);
    }
}
