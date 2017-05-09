package com.shinread.StarPlan.Parent.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.StudentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.CertificateStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DefaultMarkEnum;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.shinyread.StarPlan.Parent.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author:janecer on 2016/3/26 14:53
 * email:janecer@sina.cn
 */

public class KindsAdapter extends RecyclerView.Adapter<KindsAdapter.ViewHolder> {

    private List<StudentVo> mKinds = null ;
    private ViewHolder.MyItemClickListener myItemClickListener ;

    public KindsAdapter(List<StudentVo> kinds, ViewHolder.MyItemClickListener myItemClickListener) {
        this.mKinds = kinds ;
        this.myItemClickListener = myItemClickListener ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_userinfo_kinds,parent,false);
        ViewHolder vh = new ViewHolder(view,myItemClickListener);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        StudentVo studentVo = mKinds.get(position);

        CommonUtils.loadImage(holder.ivHeader,studentVo.headUrl);

        holder.tvKindName.setText(studentVo.name);

        if(studentVo.defaultMark == DefaultMarkEnum.DEFULT.getNo()) {
            holder.ivArrow.setVisibility( View.VISIBLE );
            holder.ivHeader.setBorderColor(FFApplication.instance.getResources().getColor(R.color.btn_yellow_bg));
            holder.tvKindName.setTextColor(FFApplication.instance.getResources().getColor(R.color.btn_yellow_bg));
        } else {
            holder.ivArrow.setVisibility(View.GONE);
            holder.ivHeader.setBorderColor(FFApplication.instance.getResources().getColor(R.color.transparent));
            holder.tvKindName.setTextColor(FFApplication.instance.getResources().getColor(R.color.white));
        }

        if (studentVo.certificateStatus == CertificateStatusEnum.HAS_CERTIFICATE.getNo()){

            holder.img_certified.setImageResource(R.drawable.certified);
        }else {
            holder.img_certified.setImageResource(R.drawable.unauthorized);
        }
    }


    @Override
    public int getItemCount() {
        return mKinds.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CircleImageView ivHeader ;
        private ImageView ivArrow,img_certified;
        public TextView  tvKindName;
        public LinearLayout llInfo ;
        private MyItemClickListener myItemClickListener ;

        public ViewHolder(View view , MyItemClickListener myItemClickListener ){
            super(view);
            this.myItemClickListener = myItemClickListener ;
            view.setOnClickListener(this);
            ivHeader = (CircleImageView) view.findViewById(R.id.iv_header);
            tvKindName = (TextView) view.findViewById(R.id.tv_kind_name);
            ivArrow = (ImageView) view.findViewById(R.id.iv_arrow) ;
            llInfo = (LinearLayout) view.findViewById(R.id.ll_info);
            img_certified  = (ImageView) view.findViewById(R.id.img_certified) ;
        }

        @Override
        public void onClick(View view) {
            if(null == myItemClickListener) {
                return ;
            }
            myItemClickListener.onItemClick(view,getLayoutPosition());
        }

        public interface MyItemClickListener {
            public void onItemClick(View view,int postion);
        }

    }
}
