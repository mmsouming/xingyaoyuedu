
package com.fancyfamily.primarylibrary.commentlibrary.framework.base;

import android.content.Context;
import android.content.Intent;
import android.os.Message;

import java.util.List;

/**
 * 描述:Fragment抽象接口:拥有设置广播监听，刷新UI，处理广播三个功能
 * 
 * @author zhj
 */
public interface IFragment {

    /**
     * 让子类设置广播的监听动作,手动调用（支持多次调用）
     * @return
     */
    void setUpActions(List<String> actions) ;

    /**
     * 刷新界面
     * 
     * @param msg
     */
    void handleUiMessage(Message msg);

    /**
     * 处理广播
     * 
     * @param context
     * @param intent
     */
    void handleBroadCast(Context context, Intent intent);

}
