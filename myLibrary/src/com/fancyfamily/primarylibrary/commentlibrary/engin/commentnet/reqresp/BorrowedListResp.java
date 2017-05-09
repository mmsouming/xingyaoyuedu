package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BorrowVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * Created by janecer on 2016/4/7.
 * email:jxc@fancyf.cn
 * des:
 */
public class BorrowedListResp extends BaseResponseVo {

    public List<BorrowVo> borrowVoArr ;

    public int pageNo ;
}
