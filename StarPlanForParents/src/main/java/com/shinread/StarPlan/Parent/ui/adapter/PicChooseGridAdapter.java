package com.shinread.StarPlan.Parent.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.shinyread.StarPlan.Parent.R;

import java.util.ArrayList;
import java.util.List;

public class PicChooseGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> objects = new ArrayList<String>();

    public int showType;

    public PicChooseGridAdapter(Context context, List<String> ls) {
        mContext = context;
        this.objects = ls;


    }

    @Override
    public int getCount() {

        return this.objects.size();
    }

    @Override
    public String getItem(int arg0) {

        return this.objects.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    static class ViewHolder {
        ImageView camera_img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.item_list_pic_grid,
                    null);
            holder = new ViewHolder();
            holder.camera_img = (ImageView) convertView
                    .findViewById(R.id.camera_img);

            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        String obj = objects.get(position);

        if (obj.equals("")) {
            Glide.with(mContext).load(R.drawable.add_pic).centerCrop()
                    .into(holder.camera_img);

            // holder.camera_img.setImageResource(R.drawable.add_pic);
            // Utils.loadImageLocal(mContext, holder.camera_img, "");
        } else {
            CommonUtils.loadImageLocal(mContext, holder.camera_img, obj);
        }

        return convertView;
    }

}
