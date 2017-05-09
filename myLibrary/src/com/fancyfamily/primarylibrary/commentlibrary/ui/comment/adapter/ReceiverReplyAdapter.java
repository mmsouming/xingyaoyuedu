package com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyNoticeVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ContentTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.InformantTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ReplyNoticeTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReportCommentReq;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookDetailsActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.fragment.ReceiverReplysFragment;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogTip;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


public class ReceiverReplyAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity mContext;

    private List<ReplyNoticeVo> objects = new ArrayList<ReplyNoticeVo>();

    public List<ReplyNoticeVo> getObjects() {
        return objects;
    }

    public void setObjects(List<ReplyNoticeVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }

    }

    private boolean ispage;
    ReceiverReplysFragment mSource;
    public ReceiverReplyAdapter(Activity context,ReceiverReplysFragment source) {
        mSource = source;
        mContext = context;

    }

    @Override
    public int getCount() {

        return this.objects.size();
    }

    @Override
    public Object getItem(int arg0) {

        return this.objects.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        final ReplyNoticeVo obj = objects.get(position);
        int id = v.getId();
        if (id == R.id.tv_arm){
            DialogTip tip = new DialogTip(mContext, "举报", "您确定要举报该条目吗？");

            tip.setListenser(new DialogTip.OnChooseDialog() {

                @Override
                public void ChooseResult(Boolean isConfirm) {
                    // TODO Auto-generated method stub
                    if (isConfirm) {
                        reportReply(mContext,obj);
                    }
                }
            });
            tip.show();
        }

        if (id == R.id.rl_target){
            Intent i = new Intent(mContext, BookDetailsActivity.class);
            i.putExtra("bookId",obj.getCommentShowVo().getCommentTargetVo().getId());
            mContext.startActivity(i);
        }
    }


    private void reportReply(Activity mContext, final ReplyNoticeVo vo){
        ReportCommentReq req = new ReportCommentReq();
        req.id = vo.getReplyId();
        req.contentType = ContentTypeEnum.BOOK_COMMENT.getNo();
        req.informantType = InformantTypeEnum.OTHER.getNo();
        CommonAppModel.reportReply(mContext, req, new HttpResultListener<BaseResponseVo>() {
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

    static class ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView iv_header;
        TextView tv_nick_name;
        TextView tv_from;
        TextView tv_comment;
        RelativeLayout rl_target;
        TextView tv_parents;
        TextView tv_parents_comments;
        ImageView iv_book;
        TextView tv_book;
        TextView tv_des;
        TextView tv_time;
        TextView tv_arm;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_replynotify_receiver, parent,false);
            holder = new ViewHolder();

            holder.iv_header = (de.hdodenhof.circleimageview.CircleImageView) convertView.findViewById(R.id.iv_header);
            holder.tv_nick_name = (TextView) convertView.findViewById(R.id.tv_nick_name);
            holder.tv_from = (TextView) convertView.findViewById(R.id.tv_from);
            holder.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
            holder.rl_target = (RelativeLayout) convertView.findViewById(R.id.rl_target);
            holder.tv_parents = (TextView) convertView.findViewById(R.id.tv_parents);
            holder.tv_parents_comments = (TextView) convertView.findViewById(R.id.tv_parents_comments);
            holder.iv_book = (ImageView) convertView.findViewById(R.id.iv_book);
            holder.tv_book = (TextView) convertView.findViewById(R.id.tv_book);
            holder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_arm = (TextView) convertView.findViewById(R.id.tv_arm);
            holder.rl_target.setOnClickListener(this);
            holder.tv_arm.setOnClickListener(this);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }
        holder = (ViewHolder) convertView.getTag();

        ReplyNoticeVo obj = objects.get(position);
        holder.tv_arm.setTag(position);
        holder.rl_target.setTag(position);
        CommonUtils.loadImage(holder.iv_header,obj.getAccountVo().headUrl);
        holder.tv_nick_name.setText(obj.getAccountVo().nickname);
        holder.tv_from.setText(obj.getCommentShowVo().getUserRemark());
        if (obj.getReplyNoticeType() == ReplyNoticeTypeEnum.REPLY.getNo()) {
            holder.tv_comment.setTextColor(mContext.getResources().getColor(R.color.gray_3));
            holder.tv_comment.setText(obj.getContent());
        } else {
            holder.tv_comment.setTextColor(mContext.getResources().getColor(R.color.red_1));
            holder.tv_comment.setText(R.string.liketoyou);
        }
        holder.tv_parents.setText(obj.getCommentShowVo().getAccountVo().nickname+":");
        holder.tv_parents_comments.setText(obj.getCommentShowVo().getContent());
        CommonUtils.loadImage(holder.iv_book,obj.getCommentShowVo().getCommentTargetVo().getPictureUrl());
        holder.tv_book.setText(obj.getCommentShowVo().getCommentTargetVo().getName());
        holder.tv_des.setText(obj.getCommentShowVo().getCommentTargetVo().getIntroduction());
        holder.tv_time.setText(obj.getTime());

        return convertView;
    }


}
