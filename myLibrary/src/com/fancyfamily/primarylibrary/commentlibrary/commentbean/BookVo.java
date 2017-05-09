package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import java.util.List;

/**
 * 图书显示ＶＯ
 * 
 * @author qhfang
 * */
public class BookVo {
    public Long id;// 书目id

    public String name;// 书目名

    public String coverUrl;// 封面URL

    public String author;// 作者

    public String publishing;// 出版社

    public String introduction;// 简介

    public String locationTags;// 定位标签

    public Integer recommend;// 推荐指数(满分100)

    public Integer commentNo;// 评论数

    public Integer stockNo;// 库存数

    public Integer reserveNo;// 己预借数

    public Boolean isCollect;// 是否己收藏
    
    public List<BookLabelVo> bookLabelVoArr;//图书标签vo列表
    
    public BookVo() {
    }
    public BookVo(Long id, String name, String coverUrl, String author,
            String publishing, String introduction, String locationTags,
            Integer recommend, Integer commentNo, Integer stockNo,
            Integer reserveNo, Boolean isCollect,List<BookLabelVo> bookLabelVoArr) {
        this.id = id;
        this.name = name;
        this.coverUrl = coverUrl;
        this.author = author;
        this.publishing = publishing;
        this.introduction = introduction;
        this.locationTags = locationTags;
        this.recommend = recommend;
        this.commentNo = commentNo;
        this.stockNo = stockNo;
        this.reserveNo = reserveNo;
        this.isCollect = isCollect;
        this.bookLabelVoArr = bookLabelVoArr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLocationTags() {
        return locationTags;
    }

    public void setLocationTags(String locationTags) {
        this.locationTags = locationTags;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public Integer getStockNo() {
        return stockNo;
    }

    public void setStockNo(Integer stockNo) {
        this.stockNo = stockNo;
    }

    public Integer getReserveNo() {
        return reserveNo;
    }

    public void setReserveNo(Integer reserveNo) {
        this.reserveNo = reserveNo;
    }

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean isCollect) {
        this.isCollect = isCollect;
    }
    public List<BookLabelVo> getBookLabelVoArr() {
        return bookLabelVoArr;
    }
    public void setBookLabelVoArr(List<BookLabelVo> bookLabelVoArr) {
        this.bookLabelVoArr = bookLabelVoArr;
    }

}
