package com.fancyfamily.primarylibrary.commentlibrary.util;


import android.app.Activity;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.JpushReq;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.google.gson.JsonObject;

import cn.jpush.android.api.JPushInterface;

public class PushManager {

    public PushManager() {

    }

    /**
     * 关联push（在登陆时）
     *
     * @param context
     */
    public void loginInLinkJpush(final Activity context) {
        linkJpush(context, 1);
    }

    /**
     * 取消关联push（在退出登陆时）
     *
     * @param context
     */
    public void loginOutLinkJpush(final Activity context) {
        linkJpush(context, 2);
    }

    public void linkJpush(final Activity context, int linkType) {

        JsonObject request = new JsonObject();

        String id = JPushInterface.getRegistrationID(context);
        Logger.msg("JPUSH", "RegistrationID : "+id);
        request.addProperty("jpushId",id );


        JpushReq req = new JpushReq();
        req.jpushId = id;
        CommonAppModel.jpushId(req, new HttpResultListener<BaseResponseVo>() {
            @Override
            public void onSuccess(BaseResponseVo resp) {

            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }
}
