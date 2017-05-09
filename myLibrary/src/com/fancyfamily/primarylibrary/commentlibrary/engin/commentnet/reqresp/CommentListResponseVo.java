package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.ArrayList;

/**
 * 3.5.3 获取评论列表
 * */
public class CommentListResponseVo extends BaseResponseVo {

    public ArrayList<CommentVo> commentVoArr;// 评论列表

    public Integer pageNo;// 当前页数





}
