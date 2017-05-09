package com.shinread.StarPlan.Parent.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shinread.StarPlan.Parent.ui.fragment.guide.ActivateCodeGuideFragment;
import com.shinread.StarPlan.Parent.ui.fragment.guide.AddKindInfoGuideFragment;

public class GuideActivateFragmentAdapter extends FragmentPagerAdapter {

    private Class[] mClazzs = {ActivateCodeGuideFragment.class , AddKindInfoGuideFragment.class ,AddKindInfoGuideFragment.class} ;
    private Context mContext ;

    public GuideActivateFragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.mContext = context ;
    }

    @Override
    public Fragment getItem(int position) {
        return Fragment.instantiate(mContext , mClazzs[position % mClazzs.length].getName()) ;
    }

    @Override
    public int getCount() {
        return mClazzs.length;
    }


}