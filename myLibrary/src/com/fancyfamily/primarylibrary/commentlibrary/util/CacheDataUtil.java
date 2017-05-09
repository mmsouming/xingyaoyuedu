package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.NotificationManager;
import android.content.Context;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;

/**
 * Created by wenxiyuan on 16/4/22.
 */
public class CacheDataUtil {


    static Context mContext = FFApplication.instance;


    private static int msgNo; //系统通知数



    private static int msgShowNo; //系统显示通知数
    private static int replyNo; //回复通知数


    public static int getReplyNo() {
        return SharePrefUtil.getInt(mContext,"replyNo",0);
    }

    public static int getMsgNo() {

        return SharePrefUtil.getInt(mContext,"msgNo",0);
    }

    public static void setReplyNo(int replyNo) {
        CacheDataUtil.replyNo = replyNo;
        SharePrefUtil.saveInt(mContext,"replyNo",replyNo);
    }

    public static void clearReplyNo(){
        setReplyNo(0);
    }

    public static void setMsgNo(int msgNo) {
        CacheDataUtil.msgNo = msgNo;
        SharePrefUtil.saveInt(mContext,"msgNo",msgNo);
    }


    public static int getMsgShowNo() {
        return SharePrefUtil.getInt(mContext,"msgShowNo",0);
    }

    public static void setMsgShowNo(int msgShowNo) {
        CacheDataUtil.msgShowNo = msgShowNo;
        SharePrefUtil.saveInt(mContext,"msgShowNo",msgShowNo);
    }

    public static void clearNotice(){
            NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(
                    android.content.Context.NOTIFICATION_SERVICE);
            mNotificationManager.cancelAll();
        setMsgNo(0);
        setMsgShowNo(0);
    }

}
