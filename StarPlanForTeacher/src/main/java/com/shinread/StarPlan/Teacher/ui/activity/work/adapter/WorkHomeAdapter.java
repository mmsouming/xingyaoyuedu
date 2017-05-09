package com.shinread.StarPlan.Teacher.ui.activity.work.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinyread.StarPlan.Teacher.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


public class WorkHomeAdapter extends BaseAdapter{

    private Activity mContext;

    private List<WorkQuestionListVo> objects = new ArrayList<WorkQuestionListVo>();

    public List<WorkQuestionListVo> getObjects() {
        return objects;
    }

    public void setObjects(List<WorkQuestionListVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }

    }

    private boolean ispage;

    public WorkHomeAdapter(Activity context) {

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
        TextView tv_task_starttime;
        TextView tv_task_title;
        TextView tv_class;
        TextView tv_submit;
        TextView tv_comment_wk;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_home_work, parent, false);
            holder = new ViewHolder();
            holder.tv_task_starttime = (TextView) convertView.findViewById(R.id.tv_task_starttime);
            holder.tv_task_title = (TextView) convertView.findViewById(R.id.tv_task_title);
            holder.tv_class = (TextView) convertView.findViewById(R.id.tv_class);
            holder.tv_submit = (TextView) convertView.findViewById(R.id.tv_submit);
            holder.tv_comment_wk = (TextView) convertView.findViewById(R.id.tv_comment_wk);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }
        holder = (ViewHolder) convertView.getTag();


        WorkQuestionListVo obj = objects.get(position);

        holder.tv_task_title.setText(obj.getName());
        holder.tv_task_starttime.setText(obj.getEffectiveTime());
        holder.tv_class.setText(obj.getClassesName());
        holder.tv_submit.setText("已提交 "+obj.getSubmitNo());
        holder.tv_comment_wk.setText("点评 "+obj.getCommentNo());
        return convertView;
    }


}
