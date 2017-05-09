package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by janecer on 2016/4/5.
 * email:jxc@fancyf.cn
 * des:
 */
public class UpdateStudentInfoReq extends BaseReq {


    public long id ;
    public long classesId ;
    public String name ;
    public int sexType ;
    public String birthday ;
    public int relationType ;
    public int height ;
    public int weight ;
    public String pictureUrl ;
}
