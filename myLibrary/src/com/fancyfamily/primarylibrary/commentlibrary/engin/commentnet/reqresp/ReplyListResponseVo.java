package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

public class ReplyListResponseVo extends BaseResponseVo {
    private List<ReplyVo> replyVoArr;// 回复列表

    private Integer pageRecordNo;// 当前记录页数


    public List<ReplyVo> getReplyVoArr() {
        return replyVoArr;
    }

    public void setReplyVoArr(List<ReplyVo> replyVoArr) {
        this.replyVoArr = replyVoArr;
    }

    public Integer getPageRecordNo() {
        return pageRecordNo;
    }

    public void setPageRecordNo(Integer pageRecordNo) {
        this.pageRecordNo = pageRecordNo;
    }

}
