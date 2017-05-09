package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;

/**
 * 描述:显示提示信息
 * 
 * @author chenys
 * @since 2014-3-26 下午3:08:14
 */
public class ToastUtil {

    private static final int MSG_SHOW_MESSAGE = 0X01;

    private static final int MSG_SHOW_MESSAGE_LOWER = 0X02;

    private static Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(android.os.Message msg) {
            Context context = FFApplication.instance.getApplicationContext();
            switch (msg.what) {
                case MSG_SHOW_MESSAGE:
                    String text = (String) msg.obj;
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, DimensionUtil.dip2px(context, 15));
                    toast.show();
                    break;
                case MSG_SHOW_MESSAGE_LOWER:
                    String text1 = (String) msg.obj;
                    Toast toast1 = Toast.makeText(context, text1, Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.BOTTOM, 0, DimensionUtil.dip2px(context, 5));
                    toast1.show();
                    break;
            }
        };
    };

    /**
     * 显示提示信息
     * 
     * @param msg
     */
    public static void showMsg(String msg) {
        mHandler.obtainMessage(MSG_SHOW_MESSAGE, msg).sendToTarget();
    }

    /**
     * 显示提示信息
     * 
     * @param msg
     */
    public static void showMsgAtLower(String msg) {
        mHandler.obtainMessage(MSG_SHOW_MESSAGE_LOWER, msg).sendToTarget();
    }

}
