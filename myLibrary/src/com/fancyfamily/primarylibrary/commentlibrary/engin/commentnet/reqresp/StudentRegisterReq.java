package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;

/**
 * Created by janecer on 2016/3/25.
 * email:jxc@fancyf.cn
 * des:
 */
public class StudentRegisterReq extends BaseReq {
    public String password ;
    public long id ;
    public String name ;
    public int sexType ; // 1 男, 2 女
    public String birthday ; //yyyy年MM月dd日
    public int relationType ; //1、爸爸 2、妈妈 100、其他
    public int height ;
    public int weight ;
    public String headPicture ;//头像地址

}
