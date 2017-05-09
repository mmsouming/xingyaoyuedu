package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.fancyfamily.primarylibrary.commentlibrary.broadcast.RecevieMsg;

import java.lang.ref.WeakReference;

/**
 * author:janecer on 2016/2/10 12:03
 * email:janecer@sina.cn
 * 可处理耗时的Activity
 */

public class BaseWorkFragmentActivity extends MessageActivity implements IWorkActivity {

    protected BackgroundHandle mBackgroundHandler = null ;

    private HandlerThread mHandleThread ;

    @Override
    public void getMessage(RecevieMsg msg) {

    }

    private static class BackgroundHandle extends Handler {

        private final WeakReference<BaseWorkFragmentActivity> mWeakBackgroundActivityReference  ;

        public BackgroundHandle(BaseWorkFragmentActivity baseWorkFragmentActivity , Looper looper){
            super(looper) ;
            mWeakBackgroundActivityReference = new WeakReference<BaseWorkFragmentActivity>(baseWorkFragmentActivity) ;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mWeakBackgroundActivityReference.get() != null) {
                mWeakBackgroundActivityReference.get().handleBackgroundMsg(msg);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandleThread = new HandlerThread("BackgroudnThread " + this.getClass().getSimpleName()) ;
        mHandleThread.start();
        mBackgroundHandler = new BackgroundHandle(this,mHandleThread.getLooper()) ;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBackgroundHandler != null && mBackgroundHandler.getLooper() != null) {
            mBackgroundHandler.getLooper().quit();
        }
    }

    @Override
    public void handleBackgroundMsg(Message msg) {

    }

    protected void sendBackgroundMessage(Message msg) {
        mBackgroundHandler.sendMessage(msg);
    }

    protected void sendBackgroundMessageDelayed(Message msg, long delayMillis) {
        mBackgroundHandler.sendMessageDelayed(msg, delayMillis);
    }

    /**
     * 发送UI更新操作
     *
     * @param what
     */
    protected void sendEmptyBackgroundMessage(int what) {
        mBackgroundHandler.sendEmptyMessage(what);
    }

    protected void sendEmptyBackgroundMessageDelayed(int what, long delayMillis) {
        mBackgroundHandler.sendEmptyMessageDelayed(what, delayMillis);
    }

    protected void removeBackgroundMessages(int what) {
        mBackgroundHandler.removeMessages(what);
    }

    protected Message obtainBackgroundMessage() {
        return mBackgroundHandler.obtainMessage();
    }
}
