package com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.LabelSowTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeSearchGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<BookLabelVo> objects = new ArrayList<BookLabelVo>();
    public Integer showType;


    public HomeSearchGridAdapter(Context context) {
        mContext = context;

    }

    public void setList(List<BookLabelVo> ls) {
        if (ls != null) {
            objects = ls;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return this.objects.size();
    }

    @Override
    public BookLabelVo getItem(int arg0) {

        return this.objects.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    static class ViewHolder {
        ImageView cate_img;
        TextView cate_txt;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_sort_subject, parent,
                    false);
            holder = new ViewHolder();
            holder.cate_img = (ImageView) convertView.findViewById(R.id.iv_subject);
            holder.cate_txt = (TextView) convertView.findViewById(R.id.tv_subject);

            AutoUtils.autoSize(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        BookLabelVo obj = objects.get(position);

        holder.cate_txt.setText(obj.getName());
        CommonUtils.loadImage(holder.cate_img, obj.getIconUrl());

        if (showType == LabelSowTypeEnum.WORDS.getNo()) {
            holder.cate_txt.setVisibility(View.VISIBLE);
            holder.cate_img.setVisibility(View.GONE);
            holder.cate_txt.setBackgroundResource(R.drawable.btn_traspbg_yellow_circle);
            holder.cate_txt.setTextColor(mContext.getResources().getColor(R.color.bule_1));
        }
        if (showType == LabelSowTypeEnum.PICTURES.getNo()) {
            holder.cate_txt.setVisibility(View.GONE);
            holder.cate_img.setVisibility(View.VISIBLE);
        }
        if (showType == LabelSowTypeEnum.FIGURE_BELOW.getNo()) {
            holder.cate_txt.setVisibility(View.VISIBLE);
            holder.cate_img.setVisibility(View.VISIBLE);
            holder.cate_txt.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
            holder.cate_txt.setTextColor(mContext.getResources().getColor(R.color.gray_3));
        }

        return convertView;
    }
}
