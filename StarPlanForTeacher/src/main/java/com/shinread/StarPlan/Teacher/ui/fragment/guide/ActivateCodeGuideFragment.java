package com.shinread.StarPlan.Teacher.ui.fragment.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.shinyread.StarPlan.Teacher.R;

/**
 * Created by janecer on 2016/3/21.
 * email:jxc@fancyf.cn
 * des:
 */
public class ActivateCodeGuideFragment extends BaseFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if( mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_guilde_1, null);
        } else {
            ViewGroup parent= (ViewGroup) mContentView.getParent();
            if(null != parent){
                parent.removeAllViews();
            }
        }
        return mContentView ;

    }

}
