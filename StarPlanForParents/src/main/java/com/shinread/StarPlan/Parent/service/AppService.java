package com.shinread.StarPlan.Parent.service;

import android.content.Context;
import android.content.Intent;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseService;

/**
 * Created by janecer on 2016/4/6.
 * email:jxc@fancyf.cn
 * des:
 */
public class AppService extends BaseService {


//    public static AccountVo mAccount ;//家长信息
//    public static List<StudentVo> mKinds ;//家长下的孩子信息,登陆时进行初始化
//
//    public static List<MsgVo>  mUnReadMsg  ;//未读消息

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

    /**
     * 释放登录信息
     */
    public static void realse() {
//        mAccount = null ;
//        mKinds = null ;
//        mUnReadMsg = null ;
    }

    /**
     * 是否激活璀璨卡
     * @return
     */
//    public static boolean isActivateCard(){
//
//        return null != mKinds && mKinds.size() > 0 ;
//    }

}
