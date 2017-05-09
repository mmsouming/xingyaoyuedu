package com.fancyfamily.primarylibrary.commentlibrary.ui.comment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.CommentTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


public class CommentActivity extends BaseActivity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initRes();
    }

    private final String mPageName = "添加回复页面";

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(this);
    }

    private String allID;
    private String singleId;
    private CommentManager commentManager;
    private int pageNo;
    private PullToRefreshScrollView pull_refresh_scrollview;
    private Long timestamp;
    private ImageButton btn_back;
    private TextView txt_title;


    protected void initRes() {
        NavBarManager manager = new NavBarManager(this);
        manager.txt_title.setText(mPageName);

        allID = getIntent().getStringExtra("bookId");
        singleId = getIntent().getStringExtra("commentId");
        pull_refresh_scrollview = (PullToRefreshScrollView) findViewById(
                R.id.pull_refresh_scrollview);
        pull_refresh_scrollview.setMode(
                PullToRefreshBase.Mode.BOTH);

        pull_refresh_scrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                // TODO Auto-generated method stub
                refreshData(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                // TODO Auto-generated method stub
                refreshData(true);
            }

        });
        if (singleId != null) {
            /**
             * 评论不分页，回复分页
             */
            commentManager = new CommentManager(this,
                    getWindow().getDecorView(), singleId, true);
            loadSingerData();
        } else {
            /**
             * 评论分页，回复不分页
             */
            commentManager = new CommentManager(this,
                    getWindow().getDecorView(), allID, false);
            loadALL(false);
        }

//        commentManager.isShowMore = false;
//        commentManager.setMore();
        commentManager.book_info_comentall.setVisibility(View.GONE);

    }

    private void refreshData(boolean isMore) {
        if (singleId != null) {
            /**
             * 评论不分页，回复分页
             */
            loadReply(isMore);
        } else {
            /**
             * 评论分页，回复不分页
             */
            loadALL(isMore);
        }
    }


    private ArrayList<CommentVo> commentList = new ArrayList<CommentVo>();
    protected CommentVo comment;

    private void loadALL(final boolean isMore) {
        if (isMore == false) {
            pageNo = 0;
        }

        if (allID == null) {
            return;
        }
        final CommentListReq req = new CommentListReq();
        req.id = Long.parseLong(allID);
        req.contentType = CommentTypeEnum.BOOK.getNo();
        req.pageNo = pageNo;
        CommonAppModel.commentList(req, new HttpResultListener<CommentListResponseVo>() {
            @Override
            public void onSuccess(CommentListResponseVo resp) {
                pull_refresh_scrollview.onRefreshComplete();
                if (resp.isSuccess()) {
                    pageNo ++;
                    if (isMore) {
                        commentList.addAll(resp.commentVoArr);
                    } else {
                        commentList = resp.commentVoArr;
                    }
                    commentManager.setData(commentList);
                } else {
                    ToastUtil.showMsg(resp.getMsg());
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                pull_refresh_scrollview.onRefreshComplete();

            }
        });

    }


    private void loadSingerData() {

        if (singleId == null) {
            return;
        }

        CommonAppModel.getCommentById(Long.parseLong(singleId), CommentTypeEnum.BOOK.getNo(), "", new HttpResultListener<CommentResponseVo>() {
            @Override
            public void onSuccess(CommentResponseVo resp) {
                if (resp.isSuccess()) {
                    comment = resp.getCommentVo();
                    commentManager.setData(comment);
                    timestamp = comment.replyVoArr.get(comment.replyVoArr.size() - 1).timestamp;

                } else {

                    ToastUtil.showMsg(resp.getMsg());
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });

    }


    private void loadReply(final boolean isMore) {
        if (isMore == false) {
            timestamp = new Long(0);
        }

        if (singleId == null) {
            return;
        }
        ReplyListReq req = new ReplyListReq();
        req.id = Long.parseLong(singleId);
        req.contentType = CommentTypeEnum.BOOK.getNo();
        req.timestamp = timestamp;

        CommonAppModel.replyList(req, new HttpResultListener<ReplyListResponseVo>() {
            @Override
            public void onSuccess(ReplyListResponseVo resp) {
                pull_refresh_scrollview.onRefreshComplete();
                if (resp.isSuccess()) {
                    List<ReplyVo> replyVoArr = resp.getReplyVoArr();
                    if (replyVoArr != null && replyVoArr.size() > 0) {
                        timestamp = replyVoArr.get(replyVoArr.size() - 1).timestamp;
                        if (isMore) {
                            comment.replyVoArr.addAll(replyVoArr);
                        } else {
                            comment.replyVoArr = replyVoArr;
                        }
                        commentManager.setData(comment);
                    }

                } else {
                    ToastUtil.showMsg(resp.getMsg());
                }

            }

            @Override
            public void onFailed(Exception e, String msg) {
                pull_refresh_scrollview.onRefreshComplete();
            }
        });

    }

}
