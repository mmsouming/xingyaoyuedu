package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by janecer on 2016/3/25.
 * email:jxc@fancyf.cn
 * des:
 */
public class ReplyReq extends BaseReq {
    public Long id	;//是	Long	是	评论id
    public int contentType;//	是	枚举 是	评论类型
    public String pictureUrlArr;//	否	[String]	是	评论图片
    public String content;//	否	String	是	评论内容(长度不能超256，1个中文算1）

}
