package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyNoticeVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * 3.5.9    获取回复通知列表
 * @author qhfang
 * */
public class ReplyNoticeListResponseVo extends BaseResponseVo {
    private List<ReplyNoticeVo> replyNoticeVoArr;// 回复通知列表

    private Integer pageRecordNo;// 当前记录页数



    public List<ReplyNoticeVo> getReplyNoticeVoArr() {
        return replyNoticeVoArr;
    }

    public void setReplyNoticeVoArr(List<ReplyNoticeVo> replyNoticeVoArr) {
        this.replyNoticeVoArr = replyNoticeVoArr;
    }

    public Integer getPageRecordNo() {
        return pageRecordNo;
    }

    public void setPageRecordNo(Integer pageRecordNo) {
        this.pageRecordNo = pageRecordNo;
    }

}
