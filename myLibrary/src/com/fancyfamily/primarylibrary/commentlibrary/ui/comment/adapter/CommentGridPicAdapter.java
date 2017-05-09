package com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fancyfamily.primarylibrary.R;

import java.util.ArrayList;
import java.util.List;

public class CommentGridPicAdapter extends BaseAdapter {
    private int mItemSize;
    private GridView.LayoutParams mItemLayoutParams;
    private Context mContext;
    private List<String> objects = new ArrayList<String>();

    public List<String> getObjects() {
        return objects;
    }

    public void setObjects(List<String> objects) {
        if (objects != null) {
            this.objects.clear();
            this.objects.addAll(objects);
            notifyDataSetChanged();
        }
    }


    public CommentGridPicAdapter(Context context) {
        mContext = context;
        mItemLayoutParams = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);
    }

    /**
     * 重置每个Column的Size
     *
     * @param columnWidth
     */
    public void setItemSize(int columnWidth) {

        if (mItemSize == columnWidth) {
            return;
        }

        mItemSize = columnWidth;

        mItemLayoutParams = new GridView.LayoutParams(mItemSize, mItemSize);

        notifyDataSetChanged();
    }

    public void setList(List<String> ls) {
        if (ls != null) {
            this.objects.clear();
            this.objects.addAll(ls);
            notifyDataSetChanged();
        }

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
            convertView = layoutInflator.inflate(R.layout.item_list_pic_grid2,
                    null);
            holder = new ViewHolder();
            holder.camera_img = (ImageView) convertView
                    .findViewById(R.id.camera_img);

            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        String obj = objects.get(position);
        Glide.with(mContext).load(obj).error(R.drawable.defaul_head).into(holder.camera_img);

        convertView.setLayoutParams(mItemLayoutParams);

        return convertView;
    }
}
