package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

/**
 * 图书标签vo
 * */
public class BookLabelVo {
    private Long id;

    private String name;// 图书标签名

    private String iconUrl;// 图书标签图标地址

    public boolean isSelect;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
