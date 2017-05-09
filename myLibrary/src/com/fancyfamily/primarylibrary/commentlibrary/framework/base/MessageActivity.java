package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.fancyfamily.primarylibrary.commentlibrary.broadcast.RecevieMsg;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;

/**
 * 自定义一个抽象的Activity类，每个Activity都继承他，实现消息的接收
 */
public abstract class MessageActivity extends BaseFragmentActivity {

    /**
     * 抽象方法，用于子类处理消息，
     *
     * @param msg
     *            传递给子类的消息对象
     */
    public abstract void getMessage(RecevieMsg msg);

    /**
     * 广播接收者，接收发送过来的消息
     */
    private BroadcastReceiver MsgReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            RecevieMsg msg = (RecevieMsg) intent.getSerializableExtra(Constants.MSGKEY);

            if (msg != null) {// 如果不是空，说明是消息广播
                getMessage(msg);// 把收到的消息传递给子类
            } else {//如果是空消息，说明是关闭应用的广播
                close();
            }
        }
    };
    /**
     * 子类直接调用这个方法关闭应用
     */
    public void close() {
        Intent i = new Intent();
        i.setAction(Constants.ACTION);
        sendBroadcast(i);
        finish();
    }

    @Override
    protected void onStart() {// 在start方法中注册广播接收者
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION);
        registerReceiver(MsgReceiver, intentFilter);// 注册接受消息广播

    }

    @Override
    protected void onStop() {// 在stop方法中注销广播接收者
        super.onStop();
        unregisterReceiver(MsgReceiver);// 注销接受消息广播
    }
}
