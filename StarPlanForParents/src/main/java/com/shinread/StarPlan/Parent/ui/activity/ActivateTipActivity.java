package com.shinread.StarPlan.Parent.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.shinyread.StarPlan.Parent.R;
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
                finish();
                break ;
            case R.id.btn_skip:
                startActivity(new Intent(this , MainTabActivity.class));
                finish();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this , MainTabActivity.class));
        finish();
    }
}
