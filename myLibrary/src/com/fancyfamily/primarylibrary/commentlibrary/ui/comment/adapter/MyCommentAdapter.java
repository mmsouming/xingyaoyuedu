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
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentShowVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ContentTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentReq;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookDetailsActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.fragment.MyCommentsFragment;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogTip;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.ClickNineGridViewAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


public class MyCommentAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity mContext;

    private List<CommentShowVo> objects = new ArrayList<CommentShowVo>();

    public List<CommentShowVo> getObjects() {
        return objects;
    }

    public void setObjects(List<CommentShowVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }

    }
    MyCommentsFragment mSource;

    public MyCommentAdapter(Activity context,MyCommentsFragment source) {
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
        final CommentShowVo obj = objects.get(position);
        int id = v.getId();
        if (id == R.id.tv_delete){
            DialogTip tip = new DialogTip(mContext, "删除", "您确定要删除选中的条目吗？");

            tip.setListenser(new DialogTip.OnChooseDialog() {

                @Override
                public void ChooseResult(Boolean isConfirm) {
                    // TODO Auto-generated method stub
                    if (isConfirm) {
                        deleteContent(mContext,obj);
                    }
                }
            });
            tip.show();
        }
        if (id == R.id.rl_target){
            Intent i = new Intent(mContext, BookDetailsActivity.class);
            i.putExtra("bookId",obj.getCommentTargetVo().getId());
            mContext.startActivity(i);
        }
    }


    private void deleteContent(Activity mContext, final CommentShowVo vo){
        CommentReq req = new CommentReq();
        req.id = vo.getId();
        req.contentType = ContentTypeEnum.BOOK_COMMENT.getNo();
        CommonAppModel.deleteComment(mContext, req, new HttpResultListener<BaseResponseVo>() {
            @Override
            public void onSuccess(BaseResponseVo resp) {
                if (resp.isSuccess()){
                    objects.remove(vo);
                    notifyDataSetChanged();
                    mSource.setLoad();
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
        ImageView iv_book;
        TextView tv_book;
        TextView tv_des;
        TextView tv_time;
        TextView tv_delete;
        NineGridView nineGrid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_comments, parent,false);
            holder = new ViewHolder();
            holder.iv_header = (de.hdodenhof.circleimageview.CircleImageView) convertView.findViewById(R.id.iv_header);
            holder.tv_nick_name = (TextView) convertView.findViewById(R.id.tv_nick_name);
            holder.tv_from = (TextView) convertView.findViewById(R.id.tv_from);
            holder.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
            holder.rl_target = (RelativeLayout) convertView.findViewById(R.id.rl_target);
            holder.nineGrid =(NineGridView) convertView.findViewById(R.id.nineGrid);
            holder.iv_book = (ImageView) convertView.findViewById(R.id.iv_book);
            holder.tv_book = (TextView) convertView.findViewById(R.id.tv_book);
            holder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
            holder.tv_delete.setOnClickListener(this);
            holder.rl_target.setOnClickListener(this);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }
        holder = (ViewHolder) convertView.getTag();

        CommentShowVo obj = objects.get(position);
        holder.tv_delete.setTag(position);
        holder.rl_target.setTag(position);
        CommonUtils.loadImage(holder.iv_header,obj.getAccountVo().headUrl);
        holder.tv_nick_name.setText(obj.getAccountVo().nickname);
        holder.tv_from.setText(obj.getUserRemark());
        holder.tv_comment.setText(obj.getContent());

        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<String> imageDetails = obj.getPictureUrlArr();
        if (imageDetails != null) {
            for (String imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(imageDetail);
                info.setBigImageUrl(imageDetail);
                imageInfo.add(info);
            }
        }
        holder.nineGrid.setAdapter(new ClickNineGridViewAdapter(mContext, imageInfo));

        CommonUtils.loadImage(holder.iv_book,obj.getCommentTargetVo().getPictureUrl());
        holder.tv_book.setText(obj.getCommentTargetVo().getName());
        holder.tv_des.setText(obj.getCommentTargetVo().getIntroduction());
        return convertView;
    }


}
