package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.OkHttpUtils;

/**
 * author:janecer on 2016/2/10 15:51
 * email:janecer@sina.cn
 * 服务基类
 */

public class BaseService extends Service  {

    /** 主要用来Log日志调试，okHttpUtil根据TAG取消网络请求 **/
    protected String TAG  ;

    protected String setTag(){
        return null ;
    } ;

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = TextUtils.isEmpty(setTag()) ? this.getClass().getSimpleName() : setTag() ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(TAG);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
