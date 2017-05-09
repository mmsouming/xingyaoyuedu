package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet;


import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

/**
 * Created by janecer
 * 主要用来与xutils3解耦
 */
public interface HttpResultListener<T extends BaseResponseVo> {
    public void onSuccess(T resp);
    public void onFailed(Exception e,String msg);
}
