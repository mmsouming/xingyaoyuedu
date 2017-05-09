package com.shinread.StarPlan.Teacher.ui.activity.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment.SimpleScannerFragment;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.CardResponseVo;
import com.shinread.StarPlan.Teacher.ui.activity.MainTabActivity;
import com.shinyread.StarPlan.Teacher.R;

public class ScanQRActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleScannerFragment fragment = (SimpleScannerFragment) fragmentManager.findFragmentById(com.fancyfamily.primarylibrary.R.id.scanner_fragment);

        fragment.setListener(new SimpleScannerFragment.OnScanResultListener() {
            @Override
            public void onScanResult(String content, String format) {
                postCard(content);
            }
        });
    }

    private void postCard(final String pwdCard) {
        if(TextUtils.isEmpty(pwdCard)){
            ToastUtil.showMsg(getString(R.string.code_get_fail_from_code));
            return ;
        }
        showLoaddingDialog();
        AppModel.card(pwdCard,new HttpResultListener<CardResponseVo>()

                {
                    @Override
                    public void onSuccess (CardResponseVo resp){

                        if (resp.isSuccess()){

                            UserInfoManager.getInstance().loadTeacherUserInfo(new HttpResultListener<MeResponseVo>() {
                                @Override
                                public void onSuccess(MeResponseVo resp) {
                                    dismissLoaddingDialog();
                                    onBackPressed();
                                }

                                @Override
                                public void onFailed(Exception e, String msg) {
                                    dismissLoaddingDialog();
                                    onBackPressed();
                                }
                            });

                        }else {
                            dismissLoaddingDialog();
                           // ToastUtil.showMsg(resp.getMsg());
                        }



                    }

                    @Override
                    public void onFailed (Exception e, String msg){
                        dismissLoaddingDialog();
                       // onBackPressed();
                    }
                }

        );
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainTabActivity.class));
        finish();
    }
}
