package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookCollectVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * Created by janecer on 2016/4/7.
 * email:jxc@fancyf.cn
 * des:
 */
public class CollectListResp extends BaseResponseVo {

    public List<BookCollectVo> bookCollectVoArr ;



    public List<BookCollectVo> getBookCollectVoArr() {
        return bookCollectVoArr;
    }

    public void setBookCollectVoArr(List<BookCollectVo> bookCollectVoArr) {
        this.bookCollectVoArr = bookCollectVoArr;
    }
}
