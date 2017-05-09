package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * author:janecer on 2016/4/1 10:44
 * email:janecer@sina.cn
 */

public class GetRecomandBookResp extends BaseResponseVo {

    public List<BookListVo> bookListVoArr ;//图书列表显示

    public List<BookListVo> getBookListVoArr() {
        return bookListVoArr;
    }

    public void setBookListVoArr(List<BookListVo> bookListVoArr) {
        this.bookListVoArr = bookListVoArr;
    }
}
