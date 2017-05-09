package com.shinread.StarPlan.Teacher.ui.activity.work.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.shinread.StarPlan.Teacher.bean.ClassesListVo;
import com.shinread.StarPlan.Teacher.bean.SchoolListVo;
import com.shinread.StarPlan.Teacher.bean.SchoolVo;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesListResponseVo;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;
import java.util.List;


public class ClassAdapter extends BaseAdapter{

    private Activity mContext;

    private List<ClassesListVo> list;


    public ClassAdapter(Activity mContext, List<ClassesListVo> list) {
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




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_class,null);
            holder = new ViewHolder();
            holder.tv_class_name = (TextView) convertView.findViewById(R.id.tv_class_name);
            holder.tv_class_people = (TextView) convertView.findViewById(R.id.tv_class_people);
            holder.tv_identification = (TextView) convertView.findViewById(R.id.tv_identification);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();


        holder.tv_class_name.setText(list.get(position).getName());
        holder.tv_class_people.setText(list.get(position).getStudentNo()+"人");
        holder.tv_identification.setText("已认证"+list.get(position).getAuthNo()+"人");


        return convertView;}
    static class ViewHolder {
        TextView tv_class_name;
        TextView tv_class_people;
        TextView tv_identification;
    }
}
