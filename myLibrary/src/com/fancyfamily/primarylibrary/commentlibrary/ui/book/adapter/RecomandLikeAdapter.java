package com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author:janecer on 2016/3/26 14:53
 * email:janecer@sina.cn
 */

public class RecomandLikeAdapter extends RecyclerView.Adapter<RecomandLikeAdapter.ViewHolder> {

    private OnItemContentClickListener onItemClickListener ;

    public void setOnItemContentClickListener(OnItemContentClickListener listener) {
        this.onItemClickListener = listener ;
    }

    public interface OnItemContentClickListener{

        /**
         * @param bookId 被点击的bookid
         */
        void onItemContentClick(long bookId) ;

    }

    public void setmImgUrls(List<BookListVo> mImgUrls) {
        this.mImgUrls = mImgUrls;
        notifyDataSetChanged();
    }

    private List<BookListVo> mImgUrls = new ArrayList<BookListVo>() ;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_recomand_book,parent,false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mIvIcon.setImageResource(R.drawable.img_red_point);
        BookListVo obj = mImgUrls.get(position);
        CommonUtils.loadImage(holder.mIvIcon,obj.coverUrl);
        holder.mIvIcon.setTag(R.drawable.cc_book_no,obj.getId());
        holder.mIvIcon.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return mImgUrls.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvIcon;
        public ViewHolder(View view){
            super(view);
            mIvIcon = (ImageView) view.findViewById(R.id.iv_recomand);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(null != onItemClickListener) {
                onItemClickListener.onItemContentClick(Long.parseLong(v.getTag(R.drawable.cc_book_no)+""));
            }
        }
    } ;
}
