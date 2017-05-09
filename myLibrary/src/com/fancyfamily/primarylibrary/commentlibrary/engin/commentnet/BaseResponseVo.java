package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet;

import android.text.TextUtils;

/**
 * Created by janecer on 2016/3/9.
 * email:jxc@fancyf.cn
 * des:构建所有的网络响应类继承此类
 */
public class BaseResponseVo {

    /**
     * 操作状态码
     */
    private Integer statusCode;
    /**
     * 操作消息，通常是服务端的业务处理异常消息
     */
    private String statusMsg;



    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public boolean isSuccess(){
        return statusCode < 10 ;
    }

    public String getMsg(){
        if(isSuccess() && TextUtils.isEmpty(statusMsg)) {
            return "操作成功" ;
        }
        return TextUtils.isEmpty(statusMsg) ? "未知错误" : statusMsg ;
    }
}
