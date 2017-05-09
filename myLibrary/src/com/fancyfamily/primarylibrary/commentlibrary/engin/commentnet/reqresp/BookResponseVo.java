package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * 图书查看
 * */
public class BookResponseVo extends BaseResponseVo {



    private BookVo bookVo;// 图书列表显示

    private CommentVo commentVo;// 评论

    private List<BookListVo> guessLikeArr;// 猜你喜欢列表



    public BookVo getBookVo() {
        return bookVo;
    }

    public void setBookVo(BookVo bookVo) {
        this.bookVo = bookVo;
    }

    public CommentVo getCommentVo() {
        return commentVo;
    }

    public void setCommentVo(CommentVo commentVo) {
        this.commentVo = commentVo;
    }

    public List<BookListVo> getGuessLikeArr() {
        return guessLikeArr;
    }

    public void setGuessLikeArr(List<BookListVo> guessLikeArr) {
        this.guessLikeArr = guessLikeArr;
    }

}
