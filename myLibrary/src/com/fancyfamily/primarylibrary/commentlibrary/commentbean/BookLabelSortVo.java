package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import java.util.List;

/**
 * 图书标签分类vo
 * */
public class BookLabelSortVo {

    private String name;// 图书标签分类名

    private List<BookLabelVo> bookLabelVoArr;// 图书标签vo列表

    private Integer labelSowType;// 显示类型  1、图片  2、文字 10、图上文下

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookLabelVo> getBookLabelVoArr() {
        return bookLabelVoArr;
    }

    public void setBookLabelVoArr(List<BookLabelVo> bookLabelVoArr) {
        this.bookLabelVoArr = bookLabelVoArr;
    }

    public Integer getLabelSowType() {
        return labelSowType;
    }

    public void setLabelSowType(Integer labelSowType) {
        this.labelSowType = labelSowType;
    }

}
