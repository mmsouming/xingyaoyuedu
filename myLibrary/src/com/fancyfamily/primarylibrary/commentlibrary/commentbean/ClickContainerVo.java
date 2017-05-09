package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

/**
 * 可点容器vo
 * 
 * @author qhfang
 * */
public class ClickContainerVo {
    private String pictureUrl;// 图片url

    private String linkUrl;// 连接url

    private Integer clickType;// 点击处理类型

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getClickType() {
        return clickType;
    }

    public void setClickType(Integer clickType) {
        this.clickType = clickType;
    }

}
