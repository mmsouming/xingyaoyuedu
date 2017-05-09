package com.shinread.StarPlan.Teacher.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.shinread.StarPlan.Teacher.ui.activity.userinfo.ScanQRActivity;
import com.shinyread.StarPlan.Teacher.R;

public class ActivateTipActivity extends BaseFragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_tip);

        findViewById(R.id.btn_activate).setOnClickListener(this);
        findViewById(R.id.btn_skip).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activate:
                Intent i = new Intent(this, ScanQRActivity.class);
                startActivity(i);
                break ;
            case R.id.btn_skip:
                startActivity(new Intent(this , MainTabActivity.class));
                finish();
                break;
        }
    }
}
