package com.fancyfamily.primarylibrary.commentlibrary.commentbean;
/**
 * 回复vo
 * */
public class ReplyVo {
    public Long id;// 内容id

    public Integer commentType;// 内容类型

    public AccountVo accountVo;// 回复者

    public String time;//评论时间
    
    public Long timestamp;//时间戳

    public String content;// 评论内容
    
    public ReplyVo(Long id,Integer commentType,AccountVo accountVo,String time,Long timestamp,String content) {
        this.id = id;
        this.commentType = commentType;
        this.accountVo = accountVo;
        this.time = time;
        this.timestamp = timestamp;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
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

}
