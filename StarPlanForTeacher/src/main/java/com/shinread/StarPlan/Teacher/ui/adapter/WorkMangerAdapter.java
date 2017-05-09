package com.shinread.StarPlan.Teacher.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DisposeProgressEnum;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinyread.StarPlan.Teacher.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizehong on 2016/5/11.
 */

public class WorkMangerAdapter extends BaseAdapter {
    Activity mContext;
    int   index;
    public WorkMangerAdapter(Activity context) {
        mContext = context;
    }
    public WorkMangerAdapter(Activity mContext, OnAdapterHandleListeners onAdapterHandleListeners) {
        this.mContext = mContext;
        this.onAdapterHandleListeners = onAdapterHandleListeners;
    }
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
    @Override
    public int getCount() {
        return objects.size();
    }
    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_work_manerger_item,null);
            holder = new ViewHolder();
            holder.tv_task_title = (TextView) convertView.findViewById(R.id.tv_task_title);
            holder.tv_class = (TextView) convertView.findViewById(R.id.tv_class);
            holder.tv_task_starttime = (TextView) convertView.findViewById(R.id.tv_task_starttime);
            holder.btn_cancel = (Button) convertView.findViewById(R.id.btn_cancel);
            holder.btn_finish = (Button) convertView.findViewById(R.id.btn_finish);
            holder.tv_zhuaitai = (TextView) convertView.findViewById(R.id.tv_zhuaitai);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        WorkQuestionListVo obj = objects.get(position);
        holder.tv_task_title.setText(obj.getName());
        holder.tv_class.setText(obj.getClassesName());
        holder.tv_task_starttime.setText(obj.getEffectiveTime());


        if(obj.getDisposeProgress()==DisposeProgressEnum.NOT_STARTED.getNo()){
            holder.btn_cancel.setVisibility(View.VISIBLE);
            holder.btn_finish.setVisibility(View.GONE);
        } else if (obj.getDisposeProgress()==(DisposeProgressEnum.PROCESSING.getNo())){
            holder.btn_finish.setVisibility(View.VISIBLE);
            holder.btn_cancel.setVisibility(View.GONE);
        }else if(obj.getDisposeProgress()==DisposeProgressEnum.HAS_ENDED.getNo()||obj.getDisposeProgress()==DisposeProgressEnum.HAS_EXPIRED.getNo()){
            holder.btn_finish.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.GONE);
            holder.tv_zhuaitai.setVisibility(View.VISIBLE);
            holder.tv_zhuaitai.setText("已完成");
        }else if (obj.getDisposeProgress()==DisposeProgressEnum.HAS_CANCLE.getNo()){
            holder.btn_finish.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.GONE);
            holder.tv_zhuaitai.setVisibility(View.VISIBLE);
            holder.tv_zhuaitai.setText("已取消");
        }
        holder.btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//延迟操作
                onAdapterHandleListeners.yanchi(objects,position);
            }
        });
        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//取消操作
                onAdapterHandleListeners.cancel(objects,position);
            }
        });
        return convertView;
    }
 class ViewHolder {
        TextView tv_task_title;
        TextView tv_class;
        TextView tv_task_starttime;
        Button btn_cancel,btn_finish;
        TextView tv_zhuaitai;
    }
    /**
     * 下面是一个自定义的回调函数，用到回调适配器操作
     *
     */
    public static interface OnAdapterHandleListeners {
        void yanchi(List<WorkQuestionListVo> list,int postition);
        void cancel(List<WorkQuestionListVo> list,int postition);

    }
    private OnAdapterHandleListeners onAdapterHandleListeners;
    public void setOnAdapterHandleListener(OnAdapterHandleListeners onAdapterHandleListeners) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;
    }

    public void changezhuantai() {
        // TODO Auto-generated method stub
        objects.get(index).setDisposeProgress(30);
        notifyDataSetChanged();
    }
}