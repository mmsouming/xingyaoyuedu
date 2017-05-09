package com.shinread.StarPlan.Teacher.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.shinyread.StarPlan.Teacher.R;

import java.util.List;

/**
 * Created by lizehong on 2016/5/4.
 */
public class GrideView_SetClassAdapter extends BaseAdapter{


    private Context context;
    List<GenericListVo>list;

    public GrideView_SetClassAdapter(Context context, List<GenericListVo> list, OnAdapterHandleListeners onAdapterHandleListeners) {
        this.context = context;
        this.list = list;
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
            convertView = layoutInflator.inflate(R.layout.lv_select_class_item,null);
            holder = new ViewHolder();
            holder.rb_select_class = (CheckBox) convertView.findViewById(R.id.rb_select_class);
            convertView.setTag(holder);}
        holder = (ViewHolder) convertView.getTag();

        holder.rb_select_class.setTag(position);
        holder.rb_select_class.setText(list.get(position).getName());
        holder.rb_select_class.setChecked(list.get(position).isChoosed());
        holder.rb_select_class.setOnCheckedChangeListener(new CheckBoxChangedListener());

        return convertView;
    }

    static class ViewHolder {
        CheckBox rb_select_class;
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
//flag//			isSelected.put(position, flag);
//            ShouCang_Video_List gwc_Goods = list.get(position);
////			gwc_Goods.setChoosed(flag);
//            float TotalPrice = getTotalPrice();
            onAdapterHandleListeners.select( flag);
        }
    }
    /**
     * 下面是一个自定义的回调函数，用到回调适配器操作
     *
     */
    public static interface OnAdapterHandleListeners {



        void select(boolean flag);
    }

    private OnAdapterHandleListeners onAdapterHandleListeners;

    public void setOnAdapterHandleListener(OnAdapterHandleListeners onAdapterHandleListeners) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;

    }
}
