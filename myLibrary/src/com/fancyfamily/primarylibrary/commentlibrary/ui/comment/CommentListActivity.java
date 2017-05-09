package com.fancyfamily.primarylibrary.commentlibrary.ui.comment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter.CommentsRecordsAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;

/**
 * Created by janecer on 2016/3/28.
 * email:jxc@fancyf.cn
 * des:
 */
public class CommentListActivity extends BaseFragmentActivity {

    private RadioGroup mRgComments;
    private ViewPager mVpComments;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_commentlist);

        initViews() ;
    }

    private void initViews() {
        mVpComments = (ViewPager) findViewById(R.id.vp_comments) ;

        ((TitleBar)findViewById(R.id.tb)).setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentListActivity.this.finish();
            }
        });
        mRgComments = ((RadioGroup)findViewById(R.id.rg_comments)) ;
        mRgComments.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_replys){
                    mVpComments.setCurrentItem(0);
                }else if(i == R.id.rb_comments){
                    mVpComments.setCurrentItem(1);
                }

            }
        });

        mVpComments.setAdapter(new CommentsRecordsAdapter(getSupportFragmentManager()));
        mVpComments.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = ((RadioButton) mRgComments.getChildAt(position)) ;
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        ((RadioButton) mRgComments.getChildAt(0)).setChecked(true);
    }
}
