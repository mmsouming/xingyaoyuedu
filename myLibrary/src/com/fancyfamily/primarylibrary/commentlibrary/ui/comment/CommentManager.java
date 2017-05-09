package com.fancyfamily.primarylibrary.commentlibrary.ui.comment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.CommentTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ContentTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.InformantTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReportCommentReq;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter.CommentAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogComment;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogTip;
import com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureListView;

import java.util.ArrayList;


public class CommentManager implements OnClickListener {

    public interface CommentManagerListener {

        void onAddComment(int index, CommentVo comment);

    }

    CommentManagerListener listener;

    public void setListener(CommentManagerListener listener) {
        this.listener = listener;
    }


    private int type = CommentTypeEnum.BOOK.getNo();
    /**
     * 评论回复是否分页
     */
    public boolean ispage = false;

    private MeasureListView book_info_comments;

    private String c_comment_id;
    private String c_reply_id;
    private String default_reply_id;
    private String default_comment_id;
    private CommentAdapter comAdapter;
    private int c_comindex;
    private Activity context;
    private String bookId;
    private ArrayList<CommentVo> commentArr = new ArrayList<CommentVo>();
    private String hint = "发表读后感";

    private View view;

    public TextView book_info_comentall;

    private DialogComment dialogComment;

    public CommentManager(Activity context, View view, String bookid,
                          boolean ispage) {
        this.ispage = ispage;
        this.bookId = bookid;
        this.context = context;
        this.view = view;
        initView();
    }


    public void setData(ArrayList<CommentVo> commentArr) {

        this.commentArr = commentArr;
        comAdapter.setObjects(commentArr);

        if (commentArr != null && commentArr.size() > 0) {
            book_info_comentall.setEnabled(true);
            book_info_comentall.setText("查看所有评论");
        }
    }

    public void setData(CommentVo comment) {

        if (comment == null)
            return;
        this.commentArr.clear();
        this.commentArr.add(comment);
        default_comment_id = comment.id + "";
        hint = "回复:" + comment.accountVo.nickname;
        comAdapter.setObjects(commentArr);
        clearEdit();
        if (commentArr != null && commentArr.size() > 0) {
            book_info_comentall.setEnabled(true);
            book_info_comentall.setText("查看所有评论");
        }
    }

    public void setReply(String userNikeName, String replyId) {
        default_reply_id = replyId;
        default_comment_id = c_comment_id;
        c_comindex = 0;
        hint = "回复:" + userNikeName;

        clearEdit();
    }

    private void initView() {
        dialogComment = new DialogComment(context);

        dialogComment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogComment.book_comment.setHint(hint);
                clearEdit();
            }
        });

        dialogComment.setListenser(new DialogComment.OnDialogComment() {
            @Override
            public void ChooseResult(String content) {
                commentReply(context);
            }
        });

        comAdapter = new CommentAdapter(context, type, ispage);
        comAdapter.setListener(new CommentAdapter.CommetListener() {

            @Override
            public void reply(int index, CommentVo comment, ReplyVo vo) {

//                c_comindex = index;
//                c_comment_id = comment.id + "";
//                c_reply_id = vo.id + "";
//
//                dialogComment.book_comment.setHint("回复:" + vo.accountVo.nickname);
//                dialogComment.show();


            }

            @Override
            public void reply(int index, CommentVo comment) {
                // TODO Auto-generated method stub

                c_comindex = index;
                c_comment_id = comment.id + "";
                c_reply_id = null;
                dialogComment.book_comment.setHint("回复:" + comment.accountVo.nickname);
                dialogComment.show();

            }
        });

        book_info_comments = (MeasureListView) view
                .findViewById(R.id.book_list_comments);

        book_info_comments.setAdapter(comAdapter);

        book_info_comments.setFocusable(false);

        book_info_comments
                .setOnItemLongClickListener(new OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view, int position, long id) {

                        final CommentVo item = (CommentVo) parent.getAdapter()
                                .getItem(position);

                        DialogTip tip = new DialogTip(context, "您要举报该评论吗?",
                                null);
                        tip.setListenser(new DialogTip.OnChooseDialog() {

                            @Override
                            public void ChooseResult(Boolean isConfirm) {

                                if (isConfirm) {
                                    postReport(context, item);
                                }
                            }
                        });
                        tip.show();
                        return false;

                    }
                });

        book_info_comentall = (TextView) view
                .findViewById(R.id.book_info_comentall);
        book_info_comentall.setEnabled(false);
        book_info_comentall.setText("暂无评论");

        book_info_comentall.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("bookId", bookId);
                context.startActivityForResult(intent, 1);
            }
        });

    }



    private void clearEdit() {
        c_comment_id = default_comment_id;
        c_reply_id = default_reply_id;

    }



    private void commentReply(final Activity context) {

        String content = dialogComment.book_comment.getText().toString().trim();
        if (content.equals("")) {
            ToastUtil.showMsg("请输入评论");
            return;
        }
        ReplyReq req = new ReplyReq();
        req.contentType = this.type;
        req.id = Long.parseLong(c_comment_id);
        req.content = content;

        CommonAppModel.reply(context,req, new HttpResultListener<ReplyResponseVo>() {
            @Override
            public void onSuccess(ReplyResponseVo resp) {

                if (resp.isSuccess()) {

                    if (commentArr.get(c_comindex).replyVoArr != null) {
                        commentArr.get(c_comindex).replyVoArr.add(resp.getReplyVo());
                    }
                    commentArr.get(c_comindex).replyNo++;
                    comAdapter.notifyDataSetChanged();
                    dialogComment.book_comment.setText("");
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    private void postReport(final Activity context, CommentVo obj) {


            ReportCommentReq req = new ReportCommentReq();
            req.id = obj.getId();
            req.contentType = ContentTypeEnum.BOOK_COMMENT.getNo();
            req.informantType = InformantTypeEnum.OTHER.getNo();
            CommonAppModel.reportComment(context, req, new HttpResultListener<BaseResponseVo>() {
                @Override
                public void onSuccess(BaseResponseVo resp) {
                    if (resp.isSuccess()){
                        //objects.remove(vo);
                        //notifyDataSetChanged();
                        ToastUtil.showMsg(resp.getMsg());
                        //mSource.setLoad();
                    }
                }

                @Override
                public void onFailed(Exception e, String msg) {

                }
            });


    }




    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.book_comment){
            if (c_comment_id == null) {
                Intent i = new Intent(context, CommentPicActivity.class);
                i.putExtra("type", type);
                i.putExtra("bookId", bookId);
                context.startActivity(i);
            }
        }

    }
}
