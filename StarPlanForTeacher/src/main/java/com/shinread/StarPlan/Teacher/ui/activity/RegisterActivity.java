package com.shinread.StarPlan.Teacher.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterSmsCodeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.WebActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.fancyfamily.primarylibrary.commentlibrary.util.MD5Util;
import com.fancyfamily.primarylibrary.commentlibrary.util.SharePrefUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.util.SPKey;
import com.shinyread.StarPlan.Teacher.R;

public class RegisterActivity extends BaseFragmentActivity implements View.OnClickListener {


    public static final String EXTRA_TYPE = "extra_type" ;

    private int isRegisterPage = 0 ;// 0,注册，1忘记密码,2位修改密码

    /* 获取验证码计时器 */
    private final static int MSG_CODE_COUNT = 10 ;

    private EditText mEtPhone ,mEtCode ,mEtPwd ;
    private TextView mTvProtocol ;
    private Button mBtnGetCode ,mBtnRegister ;


    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent i = getIntent() ;
        if( null != i) {
            isRegisterPage = i.getIntExtra(EXTRA_TYPE , 0) ;
        }

        initViews() ;
    }

    private void initViews() {
        mEtPhone = (EditText) findViewById(R.id.et_phone) ;
        mEtCode = (EditText) findViewById(R.id.et_code) ;
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtnGetCode = (Button) findViewById(R.id.btn_get_code) ;
        TitleBar mTb = (TitleBar) findViewById(R.id.tb);
        mTb.setTitleText(getString(R.string.login_forgetpwd));
        mTvProtocol = (TextView) findViewById(R.id.tv_protocol);

        mBtnRegister = (Button) findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(this);
        mBtnGetCode.setOnClickListener(this);
        mTvProtocol.setOnClickListener(this);

        if(isRegisterPage == 0) {
            mTb.setTitleText(getString(R.string.register));
            SpannableStringBuilder builder = new SpannableStringBuilder(mTvProtocol.getText().toString());
            builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FBE805")), 8, mTvProtocol.getText().toString().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            mTvProtocol.setText(builder);
        } else if(isRegisterPage == 1) {
            mTb.setTitleText(getString(R.string.login_forgetpwd));
            mBtnRegister.setText(getString(R.string.kind_commit));
            mTvProtocol.setVisibility(View.GONE);
        } else if(isRegisterPage == 2) {
            mTb.setTitleText(getString(R.string.update_pwd_setting));
            mBtnRegister.setText(getString(R.string.kind_commit));
            mTvProtocol.setVisibility(View.GONE);
        }

        mTb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_code:
                Logger.msg(TAG , "获取验证码 ") ;
                String phonenum = mEtPhone.getText().toString() ;
                if(TextUtils.isEmpty(phonenum)){
                    ToastUtil.showMsg(getString(R.string.input_phone_tip));
                    return ;
                }
//                if(!CommonUtils.isMobile(phonenum)){
//                    ToastUtil.showMsg(getString(R.string.input_phone_error_tip));
//                    return ;
//                }
                showLoaddingDialog();
                AppModel.userRegisterOrUpdatePwdCodeSmsGet(isRegisterPage , phonenum, TAG, new HttpResultListener<RegisterSmsCodeResponseVo>() {
                    @Override
                    public void onSuccess(RegisterSmsCodeResponseVo resp) {

                        dismissLoaddingDialog();

                        if(resp.isSuccess()){
                            mBtnGetCode.setText("60s") ;
                            mBtnGetCode.setTag(60);
                            mBtnGetCode.setClickable(false);
                            mBtnGetCode.setBackgroundResource(R.drawable.btn_register_code_unclick_circle);
                            sendEmptyUiMessageDelayed(MSG_CODE_COUNT , 1 * 1000);
                            return ;
                        }
                        ToastUtil.showMsg(resp.getMsg());
                    }

                    @Override
                    public void onFailed(Exception e, String msg) {
                        dismissLoaddingDialog();
                        ToastUtil.showMsg(msg);
                    }
                });
                break ;
            case R.id.btn_register:
                final String phone = mEtPhone.getText().toString() ;
                String code = mEtCode.getText().toString() ;
                final String pwd = mEtPwd.getText().toString() ;
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.showMsg(getString(R.string.input_phone_tip));
                    return ;
                }
//                if(!CommonUtils.isMobile(phone)){
//                    ToastUtil.showMsg(getString(R.string.input_phone_tip));
//                    return ;
//                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showMsg(getString(R.string.input_code_tip));
                    return ;
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showMsg(getString(R.string.input_pwd_tip));
                    return ;
                }
                if(pwd.length() < 6 ){
                    ToastUtil.showMsg(getString(R.string.input_pwd_error_tip));
                    return ;
                }
                showLoaddingDialog();
                AppModel.userRegisterOrUpdatepwd(isRegisterPage ,phone, MD5Util.getMd5(pwd+ Constants.MD5_MARK), code, TAG, new HttpResultListener<RegisterResponseVo>() {
                    @Override
                    public void onSuccess(RegisterResponseVo resp) {
                        dismissLoaddingDialog();
                        if(resp.isSuccess()) {
                            SharePrefUtil.saveString(getApplicationContext() , SPKey.function_last_login_account,phone +"-" + pwd);
                            setResult(RESULT_OK);
                            finish();
                            return ;
                        }
                        ToastUtil.showMsg(resp.getMsg());
                    }

                    @Override
                    public void onFailed(Exception e, String msg) {
                        dismissLoaddingDialog();
                        ToastUtil.showMsg(msg);
                    }
                }) ;
                break ;
            case R.id.tv_protocol:
                Intent protocolIntent = new Intent(this,WebActivity.class) ;
                protocolIntent.putExtra(WebActivity.WEB_URL, Constants.WEB_AGREEMENT);
                startActivity(protocolIntent);
                break;
        }
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what){
            case MSG_CODE_COUNT :
                int count = (int) mBtnGetCode.getTag();
                if(--count == 0) {
                    mBtnGetCode.setText(getString(R.string.get_code));
                    mBtnGetCode.setClickable(true);
                    mBtnGetCode.setBackgroundResource(R.drawable.btn_register_code_circle_selector);
                } else {
                    mBtnGetCode.setText(count+"s");
                    mBtnGetCode.setTag(count);
                    sendEmptyUiMessageDelayed(MSG_CODE_COUNT , 1 * 1000);
                }

                break ;
        }
    }
}
