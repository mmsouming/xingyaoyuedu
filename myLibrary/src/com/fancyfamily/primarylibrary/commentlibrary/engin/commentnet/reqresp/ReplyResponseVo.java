package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

public class ReplyResponseVo extends BaseResponseVo {
    private ReplyVo replyVo;//回复

    public ReplyVo getReplyVo() {
        return replyVo;
    }

    public void setReplyVo(ReplyVo replyVo) {
        this.replyVo = replyVo;
    }

}
