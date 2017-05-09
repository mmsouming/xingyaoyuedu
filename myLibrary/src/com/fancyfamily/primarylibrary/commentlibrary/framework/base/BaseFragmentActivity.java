package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author:janecer on 2016/2/10 10:43
 * email:janecer@sina.cn
 *
 * 提供 刷新Ui
 * 在子类Activity销毁时，注意mUiHandle是否有，没有处理完的消息，避免内存泄露 需要及时移除
 */

public  class BaseFragmentActivity extends BaseActivity implements IActivity {

    protected final static int BASE_CODE = 10 ;

    protected UiHandler mUiHandler = new UiHandler(this) ;

    private BaseBroadCastReceiver mReceiver ;
    private IntentFilter mIntentFilter ;
    private static class UiHandler extends Handler {

        private final WeakReference<BaseFragmentActivity> mWeakActivityReference  ;

        public UiHandler(BaseFragmentActivity baseFragmentActivity) {
            mWeakActivityReference  = new WeakReference<BaseFragmentActivity>(baseFragmentActivity) ;
        }
        public void handleMessage(Message msg) {
            super.handleMessage(msg) ;
            if(mWeakActivityReference.get() != null) {
                mWeakActivityReference.get().handleUiMessage(msg) ;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mReceiver != null) {
            this.registerReceiver(mReceiver , mIntentFilter) ;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mReceiver != null) {
            this.unregisterReceiver(mReceiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIntentFilter = null ;
    }

    @Override
    public void handleUiMessage(Message msg) {

    }

    @Override
    public void setUpActions(List<String> mActions) {
        if(null != mActions && mActions.size() > 0) {
            if( null  == mIntentFilter){
                mIntentFilter = new IntentFilter() ;
                int size = mActions.size() ;
                for(int i = 0 ; i < size ; i ++) {
                    mIntentFilter.addAction(mActions.get(i));
                }
                mReceiver = new BaseBroadCastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        handleBroadCast(context, intent);
                    }
                } ;
            } else {
                int size = mActions.size() ;
                for(int i = 0 ; i < size ; i ++) {
                    mIntentFilter.addAction(mActions.get(i));
                }
                this.registerReceiver(mReceiver , mIntentFilter) ;
            }
        }
    } ;

    @Override
    public void handleBroadCast(Context ctx, Intent intent) {

    }


    protected void sendUiMessage(Message msg) {
        mUiHandler.sendMessage(msg);
    }

    protected void sendUiMessageDelayed(Message msg, long delayMillis) {
        mUiHandler.sendMessageDelayed(msg, delayMillis);
    }

    protected void sendEmptyUiMessage(int what) {
        mUiHandler.sendEmptyMessage(what);
    }

    protected void sendEmptyUiMessageDelayed(int what, long delayMillis) {
        mUiHandler.sendEmptyMessageDelayed(what, delayMillis);
    }

    protected void removeUiMessages(int what) {
        mUiHandler.removeMessages(what);
    }

    protected Message obtainUiMessage() {
        return mUiHandler.obtainMessage();
    }

}
