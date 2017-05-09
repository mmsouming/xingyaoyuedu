package com.shinread.StarPlan.Teacher.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.shinread.StarPlan.Teacher.bean.StudentListVo;
import com.shinyread.StarPlan.Teacher.R;

import java.util.List;

/**
 * Created by lizehong on 2016/5/5.设置执教班级的适配器
 */
public class Work_SetClassAdapter extends BaseAdapter{

    private Activity context;
    List<GenericListVo> list;
    private int resourceId; // 适配器视图资源ID


    public Work_SetClassAdapter(Activity context, List<GenericListVo> list, int resourceId, OnAdapterHandleListeners onAdapterHandleListeners) {
        this.context = context;
        this.list = list;
        this.resourceId = resourceId;
        this.onAdapterHandleListeners = onAdapterHandleListeners;
    }

    @Override
    public int getCount() {
        return list.size();
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
            holder.rb_select_class = (CheckBox) convertView.findViewById(R.id.rb_select_class);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        holder.rb_select_class.setTag(position);
        holder.rb_select_class.setText(list.get(position).getName());
        holder.rb_select_class.setChecked(list.get(position).isChoosed());
        holder.rb_select_class.setOnCheckedChangeListener(new CheckBoxChangedListener());
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
     * 下面是一个自定义的回调函数，用到回调适配器操作
     *
     */
    public static interface OnAdapterHandleListeners {



        void select(List<GenericListVo> list, boolean flag);
    }

    private OnAdapterHandleListeners onAdapterHandleListeners;

    public void setOnAdapterHandleListener(OnAdapterHandleListeners onAdapterHandleListeners) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;

    }


    static class ViewHolder {
        CheckBox rb_select_class;

    }
}
