package com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.SexTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookLikeReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.CommentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureListView;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.ClickNineGridViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class CommentAdapter extends BaseAdapter
        implements OnClickListener, OnItemClickListener {

    private int type;

    public interface CommetListener {
        void reply(int index, CommentVo comment);

        void reply(int index, CommentVo comment, ReplyVo vo);
    }

    private CommetListener listener;

    public void setListener(CommetListener listener) {
        this.listener = listener;
    }

    private Activity mContext;
    private List<CommentVo> objects = new ArrayList<CommentVo>();

    public List<CommentVo> getObjects() {
        return objects;
    }

    public void setObjects(List<CommentVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }

    }

    private boolean ispage;

    public CommentAdapter(Activity context, int type, boolean ispage) {
        this.ispage = ispage;
        this.type = type;
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

    static class ViewHolder {
        ImageView book_item_image;
        TextView comment_nike;
        TextView comment_time;
        TextView comment_info,comment_school;
        LinearLayout reply_layout;
        MeasureListView reply_list;
        TextView reply_more;
        Button reply_zan;
        Button reply_reply;

        //MeasureGridView grid_pic;
        NineGridView nineGrid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.item_list_comment,
                    null);
            holder = new ViewHolder();
            holder.book_item_image = (ImageView) convertView
                    .findViewById(R.id.book_item_image);
            holder.comment_nike = (TextView) convertView
                    .findViewById(R.id.comment_nike);
            holder.comment_time = (TextView) convertView
                    .findViewById(R.id.comment_time);
            holder.comment_info = (TextView) convertView
                    .findViewById(R.id.comment_info);
            holder.reply_layout = (LinearLayout) convertView
                    .findViewById(R.id.reply_layout);
            holder.reply_list = (MeasureListView) convertView
                    .findViewById(R.id.reply_list);
            holder.comment_school = (TextView) convertView
                    .findViewById(R.id.comment_school);
            holder.reply_list.setAdapter(new CommentReplyAdapter(mContext));

            holder.reply_list.setOnItemClickListener(this);

            holder.reply_more = (TextView) convertView
                    .findViewById(R.id.reply_more);
            holder.reply_zan = (Button) convertView
                    .findViewById(R.id.reply_zan);
            holder.reply_reply = (Button) convertView
                    .findViewById(R.id.reply_reply);
            holder.reply_zan.setOnClickListener(this);
            holder.reply_reply.setOnClickListener(this);
            holder.reply_more.setOnClickListener(this);

            holder.nineGrid = (NineGridView) convertView
                    .findViewById(R.id.nineGrid);
//                    holder.grid_pic = (MeasureGridView) convertView
//                    .findViewById(R.id.grid_pic_pic);
//            CommentGridPicAdapter adapter = new CommentGridPicAdapter(mContext);
//            holder.grid_pic.setAdapter(adapter);
//            holder.grid_pic.setOnItemClickListener(this);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        CommentVo obj = objects.get(position);
        holder.comment_nike.setText(obj.accountVo.nickname);
        holder.comment_info.setText(obj.content);
        holder.comment_school.setText(obj.userRemark);
        holder.comment_time.setText(CommonUtils.getFormatTimeStr(obj.timestamp));
        if (obj.accountVo.sexType == SexTypeEnum.MALE.getNo()){
            CommonUtils.loadImage( holder.book_item_image, obj.accountVo.headUrl,R.drawable.male_3x_20160409_lf);
        }else{
            CommonUtils.loadImage( holder.book_item_image, obj.accountVo.headUrl,R.drawable.female_3x_20160409_lf);
        }


        holder.reply_zan.setText(obj.likeNo + "");
        holder.reply_reply.setText(obj.replyNo + "");

        holder.reply_zan.setTag(position);
        holder.reply_reply.setTag(position);
        holder.reply_list.setTag(position);
        holder.reply_more.setTag(position);

        if (obj.likeStatus == 1) {
            holder.reply_zan.setBackgroundResource(R.drawable.zaned);
        } else {
            holder.reply_zan.setBackgroundResource(R.drawable.zan);
        }

        if (obj.replyVoArr != null && obj.replyVoArr.size() > 0) {

            holder.reply_layout.setVisibility(View.VISIBLE);

            if (!ispage) {
                if (obj.isExtend) {

                    if (obj.replyVoArr.size() <= obj.replyNo) {
                        holder.reply_more.setVisibility(View.VISIBLE);
                        holder.reply_more.setText("查看全部回复");
                    } else {
                        holder.reply_more.setVisibility(View.GONE);
                    }

                    ((CommentReplyAdapter) holder.reply_list.getAdapter())
                            .setList(obj.replyVoArr);
                } else {
                    if (obj.replyVoArr.size() >= 3) {
                        // 大于3条 小于总回复数
                        holder.reply_more.setVisibility(View.VISIBLE);
                        holder.reply_more.setText("查看更多回复");

                    } else {
                        holder.reply_more.setVisibility(View.GONE);
                    }
                    ((CommentReplyAdapter) holder.reply_list.getAdapter())
                            .setListLimin(obj.replyVoArr);
                }
            } else {
                holder.reply_more.setVisibility(View.GONE);
                ((CommentReplyAdapter) holder.reply_list.getAdapter())
                        .setList(obj.replyVoArr);
            }

        } else {
            holder.reply_layout.setVisibility(View.GONE);
        }


        ArrayList<com.lzy.ninegrid.ImageInfo> imageInfo = new ArrayList<>();
        List<String> imageDetails = obj.pictureUrlArr;
        if (imageDetails != null) {
            for (String imageDetail : imageDetails) {
                com.lzy.ninegrid.ImageInfo info = new com.lzy.ninegrid.ImageInfo();
                info.setThumbnailUrl(imageDetail);
                info.setBigImageUrl(imageDetail);
                imageInfo.add(info);
            }
        }
        holder.nineGrid.setAdapter(new ClickNineGridViewAdapter(mContext, imageInfo));
//        CommentGridPicAdapter adapter =  (CommentGridPicAdapter) holder.grid_pic.getAdapter();
//        float width = 240 * CommonUtils.getScreenDensity(mContext);
//        int numCount = 3;
//        if (obj.pictureUrlArr.size() == 1 ) {
//            numCount = 1;
//            width = 160 * CommonUtils.getScreenDensity(mContext);
//        }else if (obj.pictureUrlArr.size() == 4) {
//            numCount = 2;
//            width = 160 * CommonUtils.getScreenDensity(mContext);
//        }
//
//        float columnSpace = 5 * CommonUtils.getScreenDensity(mContext);
//        int magin = (int) (10 * CommonUtils.getScreenDensity(mContext));
//        int columnWidth = (int) ((width - columnSpace * (numCount - 1))
//                / numCount);
//        holder.grid_pic.setNumColumns(numCount);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) width, LinearLayout.LayoutParams.MATCH_PARENT);
//        params.setMargins(magin, magin, magin, magin);
//        holder.grid_pic.setLayoutParams(params);
//        adapter.setItemSize(columnWidth);
//        adapter.setObjects(obj.pictureUrlArr);
        return convertView;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        Integer pos = (Integer) v.getTag();
        CommentVo obj = objects.get(pos);
        if (v.getId() == R.id.reply_reply){
            if (listener != null) {
                listener.reply(pos, obj);
            }
        }else if (v.getId() == R.id.reply_zan){
            commentLike(mContext, obj);
        }else if (v.getId() == R.id.reply_more){
            if (obj.isExtend) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("commentId", obj.id+"");
                mContext.startActivityForResult(intent, 1);
            } else {
                obj.isExtend = true;
                notifyDataSetChanged();
            }
        }

    }

    private void commentLike(final Activity mContext2, final CommentVo obj) {

        BookLikeReq req = new BookLikeReq();
        req.id = obj.id;
        req.contentType = obj.commentType;
        if (obj.getLikeStatus() == 1){
            req.likeType = 2;
        }else{
            req.likeType = 1;
        }

        CommonAppModel.bookLike( mContext2,req, new HttpResultListener<BookResponseVo>() {
            @Override
            public void onSuccess(BookResponseVo resp) {

                if (resp.isSuccess()){
                    if (obj.likeStatus == 1) {
                        obj.likeStatus = 2;
                        obj.likeNo --;
                    } else {
                        obj.likeStatus = 1;
                        obj.likeNo ++;
                    }

                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> v, View view, int position,
                            long id) {
        // TODO Auto-generated method stub

        if (v.getId() == R.id.reply_list){
            Integer pos = (Integer) v.getTag();
            CommentVo obj = objects.get(pos);

            if (listener != null) {
                listener.reply(pos, obj, obj.replyVoArr.get(position));
            }
        }
//        else if (v.getId() == R.id.grid_pic_pic){
//            Intent intent = new Intent(mContext,
//                    PicBrowserActivity.class);
//
//            ImageBDInfo bdInfo = new ImageBDInfo();
//            int[] location = new int[2];
//            View img = view.findViewById(R.id.camera_img);
//            img.getLocationOnScreen(location);
//            int x = location[0];
//            int y = location[1];
//            bdInfo.x = x;
//            bdInfo.y = y;
//            bdInfo.width =img.getWidth();
//            bdInfo.height = img.getHeight();
//
//
//
//            List<String> temp = ((CommentGridPicAdapter) v.getAdapter())
//                    .getObjects();
//
//            ArrayList<ImageInfo> data = new ArrayList<ImageInfo>();
//            for (int i = 0; i < temp.size(); i++) {
//                if (!temp.get(i).equals("")) {
//                    ImageInfo info = new ImageInfo();
//                    info.setUrlandType(temp.get(i), 1);
//                    data.add(info);
//                }
//            }
//            intent.putExtra("data", (Serializable)data );
//            intent.putExtra("bdinfo", bdInfo);
//            intent.putExtra("index", position);
//            intent.putExtra("type", 3);
//            intent.putExtra("isEdit", false);
//            mContext.startActivity(intent);
//        }

    }

}
