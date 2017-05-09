package com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.fragment.MyCommentsFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.fragment.ReceiverReplysFragment;

/**
 * author:janecer on 2016/3/25 22:48
 * email:janecer@sina.cn
 */

public class CommentsRecordsAdapter extends FragmentPagerAdapter {

    private Class[] mFragments = {ReceiverReplysFragment.class , MyCommentsFragment.class} ;

    public CommentsRecordsAdapter(FragmentManager fm) {
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
