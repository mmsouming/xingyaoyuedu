package com.shinread.StarPlan.Teacher;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.shinread.StarPlan.Teacher.engin.net.UrlManager;

/**
 * Created by wenxiyuan on 16/4/25.
 */
public class SPTAPP extends FFApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        UrlManager.init();
    }

}
