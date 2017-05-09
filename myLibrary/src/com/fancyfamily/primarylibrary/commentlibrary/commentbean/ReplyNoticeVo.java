package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;

import java.util.List;

public class ReplyNoticeVo {
    private Long id;// 回复通知id

    private Long replyId;//回复id

    private CommentShowVo commentShowVo;//评论显示vo

    private AccountVo accountVo;// 回复者

    private String content;// 回复内容

    private String time;// 回复时间

    private Long timestamp;// 回复时间戳

    private Integer replyNoticeType;//回复通知类型

    public ReplyNoticeVo() {

    }
    public ReplyNoticeVo(Long id,Long replyId, CommentShowVo commentShowVo,
                         AccountVo accountVo, String content, String time, Long timestamp,Integer replyNoticeType) {
        this.id = id;
        this.replyId = replyId;
        this.accountVo = accountVo;
        this.content = content;
        this.time = time;
        this.timestamp = timestamp;
        this.replyNoticeType = replyNoticeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }

    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public CommentShowVo getCommentShowVo() {
        return commentShowVo;
    }

    public void setCommentShowVo(CommentShowVo commentShowVo) {
        this.commentShowVo = commentShowVo;
    }
    public Integer getReplyNoticeType() {
        return replyNoticeType;
    }
    public void setReplyNoticeType(Integer replyNoticeType) {
        this.replyNoticeType = replyNoticeType;
    }
    public Long getReplyId() {
        return replyId;
    }
    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }
}
