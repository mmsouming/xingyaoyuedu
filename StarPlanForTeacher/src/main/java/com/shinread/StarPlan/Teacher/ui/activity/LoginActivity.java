package com.shinread.StarPlan.Teacher.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LoginResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.fancyfamily.primarylibrary.commentlibrary.util.MD5Util;
import com.fancyfamily.primarylibrary.commentlibrary.util.PushManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.SharePrefUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.service.AppService;
import com.shinyread.StarPlan.Teacher.R;

import java.util.Calendar;

/**
 * Created by janecer on 2016/3/17.
 * email:jxc@fancyf.cn
 * des:
 */
public class LoginActivity extends BaseFragmentActivity implements View.OnClickListener {


    private EditText mEtUsername ,mEtPwd ;
    private final static int REQ_REGISTER_CODE  = BASE_CODE + 1 ;
    private final static int REQ_FORGETPWD_CODE = REQ_REGISTER_CODE + 1 ;
    private final static int REQ_GET_KINDSINFO = REQ_FORGETPWD_CODE + 1 ;
    private final static int REQ_GET_UNREADMSG = REQ_GET_KINDSINFO + 1 ;

    @Override
    protected String setTag() {
        return LoginActivity.class.getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        AppService.start(getApplicationContext());

        initViews() ;
    }

    private void initViews() {
        mEtUsername = (EditText) findViewById(R.id.et_username) ;
        mEtPwd = (EditText) findViewById(R.id.et_pwd) ;
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.tv_forgetpwd).setOnClickListener(this);

        String accout = SharePrefUtil.getString(getApplicationContext(), SharePrefUtil.KEY.function_last_login_account ,"") ;
        String[] accouts = accout.split("-") ;
        if( null != accouts && accouts.length == 2){
            mEtUsername.setText(accouts[0]);
            mEtPwd.setText(accouts[1]);
        }
    }

    Calendar calendar = Calendar.getInstance();
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login :
                String userName = mEtUsername.getText().toString() ;
                String pwd = mEtPwd.getText().toString() ;
                if(TextUtils.isEmpty(userName)){
                    ToastUtil.showMsg(getString(R.string.input_phone_tip));
                    return ;
                }
//                if(!CommonUtils.isMobile(userName)){
//                    ToastUtil.showMsg(getString(R.string.input_phone_error_tip));
//                    return ;
//                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showMsg(getString(R.string.input_pwd_tip));
                    return ;
                }
                if(pwd.length() < 6) {
                    ToastUtil.showMsg(getString(R.string.input_pwd_error_tip));
                    return ;
                }
                userLogin(userName ,pwd);
                break;
            case R.id.btn_register :
                startActivityForResult(new Intent(this,RegisterActivity.class),REQ_REGISTER_CODE);
                break;
            case R.id.tv_forgetpwd :
                Intent iForget = new Intent(this, RegisterActivity.class) ;
                iForget.putExtra(RegisterActivity.EXTRA_TYPE , 1) ;
                startActivityForResult(iForget , REQ_FORGETPWD_CODE);
                break;
        }
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case REQ_GET_KINDSINFO:
                getKindsInfo();
                break ;
        }
    }

    /**
     * 登陆逻辑
     */
    private void userLogin(final String account , final String pwd) {
        showLoaddingDialog();
        AppModel.userLogin(account, MD5Util.getMd5(pwd+ Constants.MD5_MARK), TAG, new HttpResultListener<LoginResponseVo>() {
            @Override
            public void onSuccess(LoginResponseVo resp) {
                if(resp.isSuccess()){
                    SharePrefUtil.saveString(getApplicationContext() , SharePrefUtil.KEY.function_last_login_account,account +"-" + pwd);
                    SharePrefUtil.saveString(getApplicationContext(), SharePrefUtil.KEY.function_token, resp.token);
                    sendEmptyUiMessage(REQ_GET_KINDSINFO);
                    new PushManager().loginInLinkJpush(LoginActivity.this);
                    return ;
                }
                dismissLoaddingDialog();
                ToastUtil.showMsg(resp.getMsg());
            }

            @Override
            public void onFailed(Exception e,String msg) {
                dismissLoaddingDialog();
                ToastUtil.showMsg(msg);
            }
        }) ;
    }

    public void getKindsInfo(){

        UserInfoManager.getInstance().loadTeacherUserInfo(new HttpResultListener<MeResponseVo>() {
            @Override
            public void onSuccess(MeResponseVo resp) {
                if(resp.isSuccess()) {

                    toNextPage();
                    return ;
                } else {
                    ToastUtil.showMsg(resp.getMsg());
                }
                dismissLoaddingDialog();
            }

            @Override
            public void onFailed(Exception e, String msg) {
                dismissLoaddingDialog();
                toNextPage();
            }
        });

    }



    private void toNextPage() {

        if(!UserInfoManager.getInstance().isTeacherActivate()){
            startActivity(new Intent(LoginActivity.this,ActivateTipActivity.class));
        } else {
            Intent intent =new Intent(LoginActivity.this,MainTabActivity.class) ;
            intent.putExtra(MainTabActivity.EXTRA_SHOW_INDEX,0) ;
            startActivity(intent);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_REGISTER_CODE ){
            if(resultCode == RESULT_OK){
                String accout = SharePrefUtil.getString(getApplicationContext(), SharePrefUtil.KEY.function_last_login_account ,"") ;
                String[] accounts = accout.split("-") ;
                if( null != accounts && accounts.length == 2){
                    userLogin(accounts[0] ,accounts[1]);
                }
            }
        } else if (requestCode == REQ_FORGETPWD_CODE) {
            if(resultCode == RESULT_OK) {
                mEtPwd.setText("");
            }
        }
    }
}
