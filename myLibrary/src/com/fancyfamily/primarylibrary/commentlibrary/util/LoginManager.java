package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.Activity;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LoginResponseVo;

/**
 * Created by janecer on 2016/4/8.
 * email:jxc@fancyf.cn
 * des:
 */
public class LoginManager {



    private static final String TAG = LoginManager.class.getSimpleName() ;


    public static final int CODE_GET_LOGIN_INFO_FAIL = 0 ;//获取登陆信息失败！
    public static final int CODE_LOGIN_FAIL = 1 ;//登陆失败
    public static final int CODE_LOGIN_SUCCESS = 2 ;//登陆成功

    private LoginListener loginListener ;


//    public LoginManager(LoginListener loginListener) {
//        this.loginListener = loginListener ;
//    }


//    public void setLoginListener(LoginListener loginListener) {
//        this.loginListener = loginListener ;
//    }

    public LoginManager(Activity context){
        mContext = context;
    }
    public interface LoginListener {
        void onResult(int code, String msg) ;
    }

    Activity mContext;
    public void autoLogin(LoginListener listener) {

        this.loginListener = listener ;

        String accout = SharePrefUtil.getString(FFApplication.instance, SharePrefUtil.KEY.function_last_login_account ,"") ;
        String[] accounts = accout.split("-") ;
        if( null != accounts && accounts.length == 2){
            userLogin(accounts[0] ,accounts[1]);
        } else {
            loginListener.onResult(CODE_GET_LOGIN_INFO_FAIL ,"获取登陆信息失败！");
        }
    }


    /**
     * 登陆逻辑
     */
    private void userLogin(final String account , final String pwd) {

        CommonAppModel.userLogin(account, MD5Util.getMd5(pwd+ Constants.MD5_MARK), TAG, new HttpResultListener<LoginResponseVo>() {
            @Override
            public void onSuccess(LoginResponseVo resp) {

                if(resp.isSuccess()){
                    SharePrefUtil.saveString(FFApplication.instance.getApplicationContext() , SharePrefUtil.KEY.function_last_login_account,account +"-" + pwd);
                    SharePrefUtil.saveString(FFApplication.instance.getApplicationContext(), SharePrefUtil.KEY.function_token, resp.token);
                    loginListener.onResult(CODE_LOGIN_SUCCESS ,resp.getMsg());
                    new PushManager().loginInLinkJpush(mContext);
                    return ;
                }
                loginListener.onResult(CODE_LOGIN_FAIL ,resp.getMsg());
            }

            @Override
            public void onFailed(Exception e,String msg) {
                loginListener.onResult(CODE_LOGIN_FAIL ,msg);
            }
        }) ;
    }


}
