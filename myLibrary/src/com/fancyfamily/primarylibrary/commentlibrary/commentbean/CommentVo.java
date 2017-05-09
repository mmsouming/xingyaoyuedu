package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;

import java.util.List;


/**
 * 评论vo
 * 
 * @author qhfang
 * */
public class CommentVo {
    public Long id;// 内容id

    public Integer commentType;// 内容类型

    public AccountVo accountVo;// 评论者

    public String userRemark;// 用户备注(显示名)

    public List<String> pictureUrlArr;// 图片url数组
    
    public String time;//评论时间
    
    public Long timestamp;//时间戳

    public String content;// 评论内容

    public Integer likeNo;// 点赞数

    public Integer likeStatus;// 点赞状态

    public Integer replyNo;// 回复数

    public List<ReplyVo> replyVoArr;// 回复列表

    public boolean isExtend ;
    public CommentVo() {
    }
    public CommentVo(Long id, Integer commentType, AccountVo accountVo,
            String userRemark, List<String> pictureUrlArr,String time, Long timestamp,String content,
            Integer likeNo, Integer likeStatus, Integer replyNo,
            List<ReplyVo> replyVoArr) {
        this.id = id;
        this.commentType = commentType;
        this.accountVo = accountVo;
        this.userRemark = userRemark;
        this.pictureUrlArr = pictureUrlArr;
        this.time =time;
        this.timestamp = timestamp;
        this.content = content;
        this.likeNo = likeNo;
        this.likeStatus = likeStatus;
        this.replyNo = replyNo;
        this.replyVoArr = replyVoArr;
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

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(Integer likeNo) {
        this.likeNo = likeNo;
    }

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }

    public List<ReplyVo> getReplyVoArr() {
        return replyVoArr;
    }

    public void setReplyVoArr(List<ReplyVo> replyVoArr) {
        this.replyVoArr = replyVoArr;
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
