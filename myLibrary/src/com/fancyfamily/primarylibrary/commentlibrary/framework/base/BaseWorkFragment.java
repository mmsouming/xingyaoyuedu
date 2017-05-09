package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * author:janecer on 2016/2/10 16:56
 * email:janecer@sina.cn
 */

public class BaseWorkFragment extends BaseFragment implements IWorkFragment {

    protected BackgroundHandle mBackgroundHandler = null ;

    private HandlerThread mHandleThread ;

    private static class BackgroundHandle extends Handler {

        private final WeakReference<BaseWorkFragment> mWeakBackgroundFragmentReference  ;

        public BackgroundHandle(BaseWorkFragment baseWorkFragmentActivity , Looper looper){
            super(looper) ;
            mWeakBackgroundFragmentReference = new WeakReference<BaseWorkFragment>(baseWorkFragmentActivity) ;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mWeakBackgroundFragmentReference.get() != null) {
                mWeakBackgroundFragmentReference.get().handleBackgroundMsg(msg);
            }
        }
    }

    @Override
    public void handleBackgroundMsg(Message msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBackgroundHandler != null && mBackgroundHandler.getLooper() != null ) {
            mBackgroundHandler.getLooper().quit();
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
