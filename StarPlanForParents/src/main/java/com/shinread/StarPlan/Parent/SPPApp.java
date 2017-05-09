package com.shinread.StarPlan.Parent;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.shinread.StarPlan.Parent.engin.net.UrlManager;

/**
 * Created by wenxiyuan on 16/4/25.
 */
public class SPPApp extends FFApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        UrlManager.init();
    }
}
