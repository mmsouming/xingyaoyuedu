package com.shinread.StarPlan.Teacher.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.shinread.StarPlan.Teacher.ui.fragment.guide.InterstBook_SelectFragment;
import com.shinread.StarPlan.Teacher.ui.fragment.guide.Shudan_SelectFragment;

/**
 * Created by lizehong on 2016/5/3.
 */
public class Work_YueDu_SelectorAdapter extends FragmentPagerAdapter {
    private Class[] mFragments = {InterstBook_SelectFragment.class , Shudan_SelectFragment.class } ;

    public Work_YueDu_SelectorAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return Fragment.instantiate(FFApplication.instance.getApplicationContext(),mFragments[position].getName());
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragments.length;
    }
}


