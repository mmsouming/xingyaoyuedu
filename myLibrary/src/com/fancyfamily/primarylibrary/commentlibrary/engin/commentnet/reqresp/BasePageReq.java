package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by wenxiyuan on 16/4/8. 分页基类
 */
public class BasePageReq extends BaseReq {


    public int pageNo ;
    public int pageRecordNo	;//是	Int	是	记录页数（当前记录页数+1，不入查询逻辑，留作分析用），首页为0
    public Long timestamp	;//是	Long	否	己知最老消息的时间戳（null为当前时间）
    public int rowSize	= 50;//否	Int	否	分页数（为空时取后台默认 18）
    public Long invalidTimestamp;//是，作业完成时间  时间戳形式
}
