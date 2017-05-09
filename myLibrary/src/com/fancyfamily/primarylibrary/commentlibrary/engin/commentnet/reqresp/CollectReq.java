package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by janecer on 2016/4/6.
 * email:jxc@fancyf.cn
 * des:
 */
public class CollectReq extends BaseReq {

    public Long id; //图书id

    public int collectType ;// 1 收藏 ，2取消操作
}
