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
 * author:janecer on 2016/2/10 16:37
 * email:janecer@sina.cn
 *
 * 提供刷新Ui的Fragment
 */

public class BaseFragment extends BaseV4Fragment implements IFragment{
    protected final static int BASE_CODE = 10 ;
    protected UiHandler mUiHandler = new UiHandler(this) ;

    private IntentFilter mIntentFilter ;

    private BaseBroadCastReceiver mReceiver ;



    private static class UiHandler extends Handler {

        private final WeakReference<BaseFragment> mWeakFragmentReference  ;

        public UiHandler(BaseFragment baseFragmentActivity) {
            mWeakFragmentReference  = new WeakReference<BaseFragment>(baseFragmentActivity) ;
        }
        public void handleMessage(Message msg) {
            super.handleMessage(msg) ;
            if(mWeakFragmentReference.get() != null) {
                mWeakFragmentReference.get().handleUiMessage(msg) ;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIntentFilter = null ;
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
                getActivity().registerReceiver(mReceiver , mIntentFilter) ;
            }
        }
    } ;

    @Override
    public void onStart() {
        super.onStart();
        if(mReceiver != null) {
            getActivity().registerReceiver(mReceiver , mIntentFilter) ;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mReceiver != null) {
            getActivity().unregisterReceiver(mReceiver);
        }
    }



    /**
     * 刷新界面
     *
     * @param msg
     */
    @Override
    public void handleUiMessage(Message msg) {

    }

    /**
     * 处理广播
     *
     * @param context
     * @param intent
     */
    @Override
    public void handleBroadCast(Context context, Intent intent) {

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
