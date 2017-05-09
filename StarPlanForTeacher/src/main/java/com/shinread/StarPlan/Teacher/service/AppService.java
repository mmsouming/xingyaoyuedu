package com.shinread.StarPlan.Teacher.service;

import android.content.Context;
import android.content.Intent;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseService;

/**
 * Created by janecer on 2016/4/6.
 * email:jxc@fancyf.cn
 * des:
 */
public class AppService extends BaseService {



    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return  START_STICKY ;
    }

    public static void start(Context context) {
        context.startService(new Intent(context , AppService.class)) ;
    }


}
