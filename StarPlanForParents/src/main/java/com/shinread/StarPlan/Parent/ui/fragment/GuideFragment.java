package com.shinread.StarPlan.Parent.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.shinread.StarPlan.Parent.ui.activity.ScanQRActivity;
import com.shinread.StarPlan.Parent.ui.adapter.GuideActivateFragmentAdapter;
import com.shinyread.StarPlan.Parent.R;
import com.viewpagerindicator.CirclePageIndicator;

public class GuideFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager mVpGuide ;
    private CirclePageIndicator mIndicator ;
    private GuideActivateFragmentAdapter mGuideAdapter ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_guider, null);
            initViews(mContentView);
        } else {
            ViewGroup parent= (ViewGroup) mContentView.getParent();
            if(null != parent){
               parent.removeAllViews();
            }
        }
        Logger.msg("______________________guide");
        return mContentView ;
    }

    private void initViews(View view) {
        view.findViewById(R.id.btn_activate).setOnClickListener(this);
        mGuideAdapter = new GuideActivateFragmentAdapter(getActivity().getSupportFragmentManager() , FFApplication.instance.getApplicationContext());

        mVpGuide = (ViewPager)view.findViewById(R.id.vp_guide);
        mVpGuide.setAdapter(mGuideAdapter);

        mIndicator = (CirclePageIndicator)view.findViewById(R.id.indicator);
        mIndicator.setViewPager(mVpGuide);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activate :
                Intent i = new Intent(getActivity(), ScanQRActivity.class);
                startActivity(i);
                break ;
        }
    }
}
