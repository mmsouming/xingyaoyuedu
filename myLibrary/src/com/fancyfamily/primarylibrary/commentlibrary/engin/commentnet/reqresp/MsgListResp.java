package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.MsgVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * Created by janecer on 2016/4/7.
 * email:jxc@fancyf.cn
 * des:
 */
public class MsgListResp extends BaseResponseVo {

    public List<MsgVo> msgVoArr ;

    public int pageRecordNo ;
}
