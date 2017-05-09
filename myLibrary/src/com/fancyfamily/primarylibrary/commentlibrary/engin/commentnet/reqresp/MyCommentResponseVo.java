package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentShowVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * 3.5.7    获取我的评论
 * */
public class MyCommentResponseVo extends BaseResponseVo {

    private List<CommentShowVo> commentShowVoArr;// 评论列表

    private Integer pageRecordNo;// 当前页数


    public List<CommentShowVo> getCommentShowVoArr() {
        return commentShowVoArr;
    }

    public void setCommentShowVoArr(List<CommentShowVo> commentVoArr) {
        this.commentShowVoArr = commentVoArr;
    }

    public Integer getPageRecordNo() {
        return pageRecordNo;
    }

    public void setPageRecordNo(Integer pageRecordNo) {
        this.pageRecordNo = pageRecordNo;
    }

}
