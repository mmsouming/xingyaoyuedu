package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

/**
 * author:janecer on 2016/4/1 16:13
 * email:janecer@sina.cn
 */

public class SearchBookReq extends BasePageReq {

    public String name ;
    public Long labelId ;//图书分类id
    public String barcode ;//条码
}
