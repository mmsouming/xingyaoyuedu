package com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter.RecomandAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends BaseFragment {


    private ViewPager mVpContent ;
    private RadioGroup mRgSort ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(null == mContentView) {
            initViews(inflater);
        } else {
            ViewGroup parent= (ViewGroup) mContentView.getParent();
            if(null != parent){
                parent.removeAllViews();
            }
        }
        return mContentView ;
    }

    private void initViews(LayoutInflater inflater) {
        mContentView = inflater.inflate(R.layout.fragment_library , null) ;
        mVpContent = (ViewPager) mContentView.findViewById(R.id.vp_content);
        mVpContent.setAdapter(new RecomandAdapter(getChildFragmentManager()));
        mRgSort = ((RadioGroup)mContentView.findViewById(R.id.rg_sort)) ;
        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton)mRgSort.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mRgSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_recomand) {
                    mVpContent.setCurrentItem(0);
                }else if (checkedId == R.id.rb_sort){
                    mVpContent.setCurrentItem(1);
                }

            }
        });
        ((RadioButton)mRgSort.getChildAt(0)).setChecked(true);
    }

}
