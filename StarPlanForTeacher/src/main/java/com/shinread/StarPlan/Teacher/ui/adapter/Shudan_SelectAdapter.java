package com.shinread.StarPlan.Teacher.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookCollectVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shinyread.StarPlan.Teacher.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizehong on 2016/5/6.
 */

public class Shudan_SelectAdapter extends BaseAdapter {
    private int resourceId; // 适配器视图资源ID
    private Activity mContext;
    private List<BookListVo> objects = new ArrayList<BookListVo>();
    public List<BookListVo> getObjects() {
        return objects;
    }
    public void setObjects(List<BookListVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }

    }
    public Shudan_SelectAdapter(OnAdapterHandleListeners onAdapterHandleListeners, Activity mContext) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;
        this.mContext = mContext;
    }
    private boolean ispage;

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
    class ViewHolder {
        CheckBox iv_shudan_check;
        ImageView iv_shudan_img;
        TextView iv_shudan_name;
        TextView iv_shudan_zhuanjia;
        RatingBar rb_star;
        TextView iv_shudan_descri;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_shudan, parent,false);
            holder = new ViewHolder();

            holder.iv_shudan_check = (CheckBox) convertView.findViewById(R.id.iv_shudan_check);
            holder.iv_shudan_img = (ImageView) convertView.findViewById(R.id.iv_shudan_img);
            holder.iv_shudan_name = (TextView) convertView.findViewById(R.id.iv_shudan_name);
            holder.iv_shudan_zhuanjia = (TextView) convertView.findViewById(R.id.iv_shudan_zhuanjia);
            holder.rb_star = (RatingBar) convertView.findViewById(R.id.rb_star);
            holder.iv_shudan_descri = (TextView) convertView.findViewById(R.id.iv_shudan_descri);

            //  holder.tv_arm.setOnClickListener(this);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }
        holder = (ViewHolder) convertView.getTag();

        BookListVo obj = objects.get(position);


        ImageLoader.getInstance().displayImage(obj.getCoverUrl(), holder.iv_shudan_img);
        holder.iv_shudan_name.setText(obj.getName());
        holder.iv_shudan_descri.setText(obj.getIntroduction());
        holder.rb_star.setRating((obj.getRecommend() * 5 / 100));
        holder.iv_shudan_check.setTag(position);

        holder.iv_shudan_check.setChecked(obj.isChoosed());
        holder.iv_shudan_check.setOnCheckedChangeListener(new CheckBoxChangedListener());

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
            objects.get(position).setChoosed(flag);
            onAdapterHandleListeners.select(objects, flag);
        }
    }
    /**
     * 下面是一个自定义的回调函数，用到回调适配器操作
     *
     */
    public static interface OnAdapterHandleListeners {



        void select(List<BookListVo> objects, boolean flag);
    }

    private OnAdapterHandleListeners onAdapterHandleListeners;

    public void setOnAdapterHandleListener(OnAdapterHandleListeners onAdapterHandleListeners) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;

    }

}
