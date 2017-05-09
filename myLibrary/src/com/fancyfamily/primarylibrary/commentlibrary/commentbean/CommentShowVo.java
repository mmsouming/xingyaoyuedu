package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import java.util.List;

/**
 * 评论显示vo
 * */
public class CommentShowVo {
    private Long id;// 内容id

    private Integer contentType;// 内容类型

    private AccountVo accountVo;// 评论者

    private String userRemark;// 用户备注(显示名)

    private List<String> pictureUrlArr;// 图片url数组

    private String time;// 评论时间

    private Long timestamp;// 时间戳

    private String content;// 评论内容

    private CommentTargetVo commentTargetVo;// 评论目标vo

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }

    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public List<String> getPictureUrlArr() {
        return pictureUrlArr;
    }

    public void setPictureUrlArr(List<String> pictureUrlArr) {
        this.pictureUrlArr = pictureUrlArr;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentTargetVo getCommentTargetVo() {
        return commentTargetVo;
    }

    public void setCommentTargetVo(CommentTargetVo commentTargetVo) {
        this.commentTargetVo = commentTargetVo;
    }

}
