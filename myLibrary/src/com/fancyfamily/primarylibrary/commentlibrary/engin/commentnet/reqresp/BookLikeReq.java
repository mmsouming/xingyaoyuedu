package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by wenxiyuan on 16/4/8.
 */
public class BookLikeReq extends BaseReq {
    public Long id	;//是	Long	是	评论id
    public int contentType;//是	枚举 是	;//评论类型
    public int likeType	;//是	枚举 是	赞类型
}
