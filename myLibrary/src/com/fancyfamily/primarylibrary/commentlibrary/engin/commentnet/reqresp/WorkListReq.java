package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:
 */
public class WorkListReq extends BaseReq {

    public Long id ;//学生id
    public Integer pageRecordNo  = 0  ;
    public Long timestamp ;
    public Integer rowSize ;
}
