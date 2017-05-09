package com.shinread.StarPlan.Teacher.ui.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shinread.StarPlan.Teacher.ui.adapter.Work_YueDu_SelectorAdapter;
import com.shinyread.StarPlan.Teacher.R;

/*
*
*
* 阅读范围Activity
* */
public class YueDuActivity extends FragmentActivity implements View.OnClickListener {
    private RelativeLayout relyout_listview;
    private RadioButton btn_select_interst;
    private RadioButton btn_select_shudan;
    private android.support.v4.view.ViewPager work_content;
    private Button btn_sure;
    private RadioGroup yuedu_sort;
    private ImageView yuedu_btn_back;
    private TextView yuedu_txt_title;
    private Button yuedu_btn_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yue_du);
        initRes();
        Initview();
    }

    private void initRes() {
//       NavBarManager navBarManager=new NavBarManager(this);
//        navBarManager.txt_title.setText("阅读范围");
//        navBarManager.btn_right.setVisibility(View.VISIBLE);
//        navBarManager.btn_right.setBackgroundResource(R.drawable.sousuo);
//        navBarManager.btn_right.setText("");
//        navBarManager.title_bar_layout.setBackgroundColor(Color.WHITE);

        yuedu_btn_back = (ImageView) findViewById(R.id.yuedu_btn_back);
        yuedu_txt_title = (TextView) findViewById(R.id.yuedu_txt_title);
        yuedu_btn_right = (Button) findViewById(R.id.yuedu_btn_right);
        relyout_listview = (RelativeLayout) findViewById(R.id.relyout_listview);
        btn_select_interst = (RadioButton) findViewById(R.id.btn_select_interst);
        btn_select_shudan = (RadioButton) findViewById(R.id.btn_select_shudan);
        work_content = (ViewPager) findViewById(R.id.work_content);
        Work_YueDu_SelectorAdapter work_yueDu_selectorAdapter = new Work_YueDu_SelectorAdapter(getSupportFragmentManager());
        work_content.setAdapter(work_yueDu_selectorAdapter);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        yuedu_sort = (RadioGroup) findViewById(R.id.yuedu_sort);

    }

    public void Initview() {
        yuedu_btn_back.setOnClickListener(this);
        yuedu_btn_right.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        work_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                ((RadioButton) yuedu_sort.getChildAt(position)).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        yuedu_sort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_select_interst:
                        work_content.setCurrentItem(0);
                        break;
                    case R.id.btn_select_shudan:
                        work_content.setCurrentItem(1);
                        break;
                }
            }
        });
        ((RadioButton) yuedu_sort.getChildAt(0)).setChecked(true);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yuedu_btn_back:
                finish();
                break;
            case R.id.yuedu_btn_right:
                Intent intent = new Intent(getApplicationContext(), SousuoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sure:
                Intent intents = new Intent();

                // 通过Intent对象返回结果，调用setResult方法
                setResult(2, intents);
                finish();

                break;

        }
    }
}
