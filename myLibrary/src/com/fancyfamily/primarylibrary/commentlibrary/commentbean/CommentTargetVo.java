package com.fancyfamily.primarylibrary.commentlibrary.commentbean;
/**
 * 评论目标vo
 * */
public class CommentTargetVo {
    private Long id;// 评论目标Id

    private String pictureUrl;// 图片url

    private String name;// 评论目标名

    private String introduction;// 评论目标简介

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
