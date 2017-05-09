package com.fancyfamily.primarylibrary.commentlibrary.ui.book;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter.OrderRecordsAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;

/**
 * Created by janecer on 2016/3/28.
 * email:jxc@fancyf.cn
 * des:
 */
public class LendRecordActivity extends BaseFragmentActivity {

    private RadioGroup mRgOrders ;
    private ViewPager mVpOrders ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lendrecord);

        initViews() ;
    }

    private void initViews() {
        mVpOrders = (ViewPager) findViewById(R.id.vp_orders) ;

        ((TitleBar)findViewById(R.id.tb)).setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LendRecordActivity.this.finish();
            }
        });
        mRgOrders = ((RadioGroup)findViewById(R.id.rg_orders)) ;
        mRgOrders.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_ordering){
                    mVpOrders.setCurrentItem(0);
                }else if(i == R.id.rb_order){
                    mVpOrders.setCurrentItem(1);
                }else if(i == R.id.rb_ordered){
                    mVpOrders.setCurrentItem(2);
                }
            }
        });

        mVpOrders.setAdapter(new OrderRecordsAdapter(getSupportFragmentManager()));
        mVpOrders.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = ((RadioButton)mRgOrders.getChildAt(position)) ;
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        ((RadioButton)mRgOrders.getChildAt(0)).setChecked(true);
    }
}
