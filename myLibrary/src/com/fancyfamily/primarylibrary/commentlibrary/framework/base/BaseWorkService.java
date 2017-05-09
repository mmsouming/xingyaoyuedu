package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * author:janecer on 2016/2/10 16:10
 * email:janecer@sina.cn
 * 提供BackgroundHandler
 */

public abstract class BaseWorkService extends BaseService {

    protected BackgroundHandle mBackgroundHandler = null ;

    private HandlerThread mHandlerThread ;

    private static class BackgroundHandle extends Handler {

        private final WeakReference<BaseWorkService> mWeakBackgroundServiceReference  ;

        public BackgroundHandle(BaseWorkService baseWorkService , Looper looper){
            super(looper) ;
            mWeakBackgroundServiceReference = new WeakReference<BaseWorkService>(baseWorkService) ;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mWeakBackgroundServiceReference.get() != null) {
                mWeakBackgroundServiceReference.get().handleBackgroundMsg(msg);
            }
        }
    }

    protected abstract void handleBackgroundMsg(Message msg) ;


    @Override
    public void onCreate() {
        mHandlerThread = new HandlerThread("Service..." + BaseWorkService.class.getSimpleName()) ;
        mHandlerThread.start() ;
        mBackgroundHandler = new BackgroundHandle(this,mHandlerThread.getLooper()) ;
    }


    @Override
    public void onDestroy(){
        if(mBackgroundHandler != null && mBackgroundHandler.getLooper() != null) {
            mBackgroundHandler.getLooper().quit() ;
        }
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
