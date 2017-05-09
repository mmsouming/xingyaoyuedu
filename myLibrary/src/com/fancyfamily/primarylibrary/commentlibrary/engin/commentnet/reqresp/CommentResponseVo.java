package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

/**
 * 3.5.1    发表评论
 * */
public class CommentResponseVo extends BaseResponseVo {
    private CommentVo commentVo;// 评论

    public CommentResponseVo(CommentVo commentVo) {
        //super(StatusCodeEnum.OPERATION_SUCCESS.getNo(), "");
        this.commentVo = commentVo;
    }

    public CommentVo getCommentVo() {
        return commentVo;
    }

    public void setCommentVo(CommentVo commentVo) {
        this.commentVo = commentVo;
    }

}
