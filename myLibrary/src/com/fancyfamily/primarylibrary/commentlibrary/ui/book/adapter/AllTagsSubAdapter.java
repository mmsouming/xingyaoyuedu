package com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelVo;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


public class AllTagsSubAdapter extends BaseAdapter implements  View.OnClickListener {

    private Activity mContext;
    private List<BookLabelVo> objects = new ArrayList<BookLabelVo>();

    public List<BookLabelVo> getObjects() {
        return objects;
    }

    public void setObjects(List<BookLabelVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }

    }
    AllTagsAdapter mSourceAdapter;
    public AllTagsSubAdapter(Activity context,AllTagsAdapter sourceAdapter) {
        mContext = context;
        mSourceAdapter = sourceAdapter;
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

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        BookLabelVo vo = objects.get(position);
        if (vo.isSelect == true || mSourceAdapter.canChoose()) {

            vo.isSelect = !vo.isSelect;

        }else {

            ToastUtil.showMsg("最多只能选"+mSourceAdapter.maxNum+"条");
        }
        notifyDataSetChanged();
    }


    static class ViewHolder {
        CheckBox rb_recomand;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_subjects_interest, parent, false);
            holder = new ViewHolder();
            holder.rb_recomand = (CheckBox) convertView.findViewById(R.id.rb_recomand);

            AutoUtils.autoSize(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        BookLabelVo obj = objects.get(position);
        holder.rb_recomand.setText(obj.getName());
        holder.rb_recomand.setTag(position);
        holder.rb_recomand.setOnClickListener(this);
        holder.rb_recomand.setChecked(obj.isSelect);
        return convertView;
    }


}



