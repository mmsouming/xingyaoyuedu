package com.shinread.StarPlan.Teacher.engin.net.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:提交作业
 */
public class TWorkReq extends BaseReq {

    public int workStatus ;//作业状态
    public int workLevel ;//作业评级（设为己点评时不能为空）
    public String comment ;//点评（设为己点评时不能为空）
}
