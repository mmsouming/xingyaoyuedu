package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

/**
 * 3.5.8    获取新回复数
 * */
public class NewReplyNoResponseVo extends BaseResponseVo {
    private Integer replyNo;// 新回复数



    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }

}
