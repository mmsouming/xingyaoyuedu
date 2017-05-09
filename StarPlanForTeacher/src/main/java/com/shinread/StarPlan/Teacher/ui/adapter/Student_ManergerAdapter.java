package com.shinread.StarPlan.Teacher.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.CertificateStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.shinread.StarPlan.Teacher.bean.StudentListVo;
import com.shinread.StarPlan.Teacher.util.CommonUtils;
import com.shinyread.StarPlan.Teacher.R;

import java.util.List;

/**
 * Created by lizehong on 2016/4/27.
 */
public class Student_ManergerAdapter extends BaseAdapter{

    private Activity context;
    List<StudentListVo>list;
    private int resourceId; // 适配器视图资源ID
    private boolean isOP;

    public Student_ManergerAdapter(Activity context, List<StudentListVo> list, int resourceId, OnAdapterHandleListeners onAdapterHandleListeners) {
        this.context = context;
        this.list = list;
        this.resourceId = resourceId;
        this.onAdapterHandleListeners = onAdapterHandleListeners;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public void setOP(boolean isOP) {
        this.isOP = isOP;
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(context);
            convertView = layoutInflator.inflate(resourceId,null);
            holder = new ViewHolder();
            holder.imbtn_check = (CheckBox) convertView.findViewById(R.id.imbtn_check);
            holder.iv_student_header = (de.hdodenhof.circleimageview.CircleImageView) convertView.findViewById(R.id.iv_student_header);
            holder.tv_student_name = (TextView) convertView.findViewById(R.id.tv_student_name);
            holder.tv_renzheng = (TextView) convertView.findViewById(R.id.tv_renzheng);
            holder.tv_renzheng_a = (TextView) convertView.findViewById(R.id.tv_renzheng_a);


            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        if (isOP) {
            holder.imbtn_check.setVisibility(View.VISIBLE);
        } else {
            holder.imbtn_check.setVisibility(View.GONE);
        }
        holder.tv_student_name.setText(list.get(position).getName());
        if(list.get(position).getCertificateStatus()== CertificateStatusEnum.NO_CERTIFICATE.getNo()){
            holder.tv_renzheng.setText("未认证");
        }else if(list.get(position).getCertificateStatus()==CertificateStatusEnum.HAS_CERTIFICATE.getNo()){
            holder.tv_renzheng.setText("已认证");
        }
        holder.tv_renzheng_a.setText(list.get(position).getSubmitNo()+"");
        CommonUtils.loadImage(holder.iv_student_header , list.get(position).getHeadUrl());
        holder.imbtn_check.setTag(position);
        holder.imbtn_check.setChecked(list.get(position).isChoosed());
        holder.imbtn_check.setOnCheckedChangeListener(new CheckBoxChangedListener());
        return convertView;
    }
    /**
     * @author Administrator CheckBox选择改变监听器
     *
     */
    class CheckBoxChangedListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton cb, boolean flag) {
            int position = (Integer) cb.getTag();
            list.get(position).setChoosed(flag);
            onAdapterHandleListeners.select(list, flag);
        }
    }
    /**
     * 删除选中的学生
     */
    public void deletegoods() {
        // TODO Auto-generated method stub
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).isChoosed()) {
                list.remove(i);
            }
            notifyDataSetChanged();
        }
    }

/*
* 认证选中的学生
*
* */
    public void renzheng(){
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).isChoosed()) {

                    list.get(i).setCertificateStatus(2);

            }
            notifyDataSetChanged();
        }
    }
    /**
     * 下面是一个自定义的回调函数，用到回调适配器操作
     *
     */
    public static interface OnAdapterHandleListeners {



        void select(List<StudentListVo> list, boolean flag);
    }

    private OnAdapterHandleListeners onAdapterHandleListeners;

    public void setOnAdapterHandleListener(OnAdapterHandleListeners onAdapterHandleListeners) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;

    }

    static class ViewHolder {
        CheckBox imbtn_check;
        de.hdodenhof.circleimageview.CircleImageView iv_student_header;
        TextView tv_student_name;
        TextView tv_renzheng;
        TextView tv_renzheng_a;

    }
}

