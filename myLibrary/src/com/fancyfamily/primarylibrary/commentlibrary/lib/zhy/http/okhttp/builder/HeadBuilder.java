package com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.builder;


import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.OkHttpUtils;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.request.OtherRequest;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
