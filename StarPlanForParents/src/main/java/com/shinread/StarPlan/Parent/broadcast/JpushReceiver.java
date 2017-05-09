package com.shinread.StarPlan.Parent.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.fancyfamily.primarylibrary.commentlibrary.broadcast.RecevieMsg;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.MsgTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.PushMsgTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseBroadCastReceiver;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.google.gson.Gson;
import com.shinread.StarPlan.Parent.ui.activity.userinfo.MsgCenterActivity;
import com.shinyread.StarPlan.Parent.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by janecer on 2016/3/22.
 * email:jxc@fancyf.cn
 * des:
 */
public class JpushReceiver extends BaseBroadCastReceiver {

    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

            try {
                recevieMsg(context,
                        bundle.getString(JPushInterface.EXTRA_EXTRA));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");

            //打开自定义的Activity
//            Intent i = new Intent(context, JpushShowActivity.class);
//            i.putExtras(bundle);
//            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            context.startActivity(i);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " +json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


    private static Gson mGson = new Gson() ;
    private void recevieMsg(Context context, String response) throws Exception {
        Logger.msg("MYJPUSH", "recevie : " + response);

        RecevieMsg info  = mGson.fromJson(response,RecevieMsg.class) ;

        if (info == null) {
            return;
        }



        if (info.pushMsgType == PushMsgTypeEnum.REPLY_NOTICE.getNo()){

            //回复消息数
            int no = CacheDataUtil.getReplyNo() + 1;
            CacheDataUtil.setReplyNo(no);

        } else if (info.pushMsgType == PushMsgTypeEnum.SYSTEM_MSG.getNo()) {

            int no = CacheDataUtil.getMsgNo() + 1;
            CacheDataUtil.setMsgNo(no);

            if (info.showType == MsgTypeEnum.SILENCE_MSG.getNo()){



            }else if(info.showType == MsgTypeEnum.SCREEN_MSG.getNo()){

                int showno = CacheDataUtil.getMsgShowNo() + 1;
                CacheDataUtil.setMsgShowNo(showno);

                NotificationManager mNotificationManager = (NotificationManager) context
                        .getSystemService(
                                android.content.Context.NOTIFICATION_SERVICE);

                int icon = R.mipmap.ic_launcher;


                PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0,
                        new Intent(context, MsgCenterActivity.class), 0);
                // 通过Notification.Builder来创建通知，注意API Level
                // API11之后才支持
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

                builder.setSmallIcon(icon) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                        // icon)
                        .setTicker("您有新消息，请注意查收！")// 设置在status
                        // bar上显示的提示文字
                        .setContentIntent(pendingIntent2) // 关联PendingIntent
                        .setNumber(showno); // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                         // 需要注意build()是在API level
                // 16及之后增加的，在API11中可以使用getNotificatin()来代替

                if (showno > 1) {
                    builder.setContentTitle("收到" + no + "条消息").setContentText("点击打开应用");


                } else {
                    builder.setContentTitle(info.title).setContentText(info.msg);

                }

                Notification notify = builder.getNotification();
                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(
                        Constants.NOTIFY_ID + info.pushMsgType, notify);// 通知一下才会生效哦
            }
        }

        Intent broadCast = new Intent();
        broadCast.setAction(Constants.ACTION);
        broadCast.putExtra(Constants.MSGKEY, info);
        context.sendBroadcast(broadCast);// 把收到的消息已广播的形式发出

    }
}
