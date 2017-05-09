package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by wenxiyuan on 16/4/20.
 */
public class WorkQuestionReq extends BaseReq {
    public String name;//	是	String	是	作业名
    public String content	;//是	String	是	作业内容(长度不能超256，1个中文算1）
    public Long effectiveTimestamp;//	是	Long	是	开始时间
    public Long invalidTimestamp;//	是	Long	是	结束时间
    public String idArr;//	是	[Long]	是	目标班级id列表
    public String bookIdArr;//	是	[Long]	是	目标图书id列表

}
