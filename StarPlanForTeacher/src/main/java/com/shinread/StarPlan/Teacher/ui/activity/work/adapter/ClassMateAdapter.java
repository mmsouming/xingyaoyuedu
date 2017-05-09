package com.shinread.StarPlan.Teacher.ui.activity.work.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shinread.StarPlan.Teacher.bean.SchoolListVo;
import com.shinyread.StarPlan.Teacher.R;

import java.util.List;


public class ClassMateAdapter extends BaseAdapter{

    private Activity mContext;

    private List<SchoolListVo> list;


    public ClassMateAdapter(Activity mContext, List<SchoolListVo> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int arg0) {

        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }




    static class ViewHolder {
        ImageView img_mate_head;
        TextView tv_mate_name;
        TextView tv_status;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_class_mate,null);
            holder = new ViewHolder();
            holder.img_mate_head = (ImageView) convertView.findViewById(R.id.img_mate_head);
            holder.tv_mate_name = (TextView) convertView.findViewById(R.id.tv_mate_name);
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            convertView.setTag(holder);}
        holder = (ViewHolder) convertView.getTag();



        return convertView;}

}
