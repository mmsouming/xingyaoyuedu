package com.shinread.StarPlan.Parent.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CardClassResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment.SimpleScannerFragment;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.shinyread.StarPlan.Parent.R;

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
        AppModel.schoolClassGetByCode(pwdCard,TAG,new HttpResultListener<CardClassResponseVo>()

                {
                    @Override
                    public void onSuccess (CardClassResponseVo resp){
                        dismissLoaddingDialog();
                        if (resp.isSuccess() && resp.schoolVo != null) {
                            Intent i = new Intent(ScanQRActivity.this, AddKindsInfoActivity.class);
                            i.putExtra("SchoolVo", resp.schoolVo);
                            i.putExtra("pwdCard",pwdCard);
                            startActivity(i);
                            finish();
                        }else{
                            onBackPressed();
                        }

                    }

                    @Override
                    public void onFailed (Exception e, String msg){
                        dismissLoaddingDialog();
                        onBackPressed();
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
