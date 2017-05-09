package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:提交作业
 */
public class WorkReq extends BaseReq {

    public Long id ;//作业Id
    public Long bookId ;//作业图书Id
    public String pictureUrlArr ;//图片url数组（不超过9）
    public String content ;//作业内容
}
