package com.fancyfamily.primarylibrary.commentlibrary.framework;

import android.util.Log;

/**
 * Created by janecer on 2014/11/4.
 */
public class Logger {

    private static final String SYSTAG = "PrimaryLibrary" ;
    public static final int V=0;
    public static final int D=1;
    public static final int I=2;
    public static final int W=3;
    public static final int E=4;

    private static int mLevel = I ;

    /**
     * 打印自定义级别log
     * @param tag
     * @param level
     * @param msg
     */
    public static void msg(String tag,int level ,String msg){
       switch (level){
           case V:
               Log.v(tag, msg);
               break;
           case D:
               Log.d(tag, msg);
               break;
           case I:
               Log.i(tag, msg);
               break;
           case W:
               Log.w(tag, msg);
               break;
           case E:
               Log.e(tag, msg);
               break;
           default:
               break;
       }

    }

    /**
     * 打印系统log
     * @param msg
     */
    public static void msg(String msg){
        msg(SYSTAG,msg);
    }

    /**
     * 打印自定义TAG log
     * @param msg
     */
    public static void msg(String Tag ,String msg){
        msg(Tag,mLevel ,msg);
    }
}
