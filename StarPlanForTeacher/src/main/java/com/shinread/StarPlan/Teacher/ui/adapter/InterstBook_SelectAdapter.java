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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookCollectVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyNoticeVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ContentTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ReplyNoticeTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentReq;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogTip;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shinyread.StarPlan.Teacher.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizehong on 2016/5/6.
 */
public class InterstBook_SelectAdapter extends BaseAdapter {

        private Activity mContext;
        private List<BookCollectVo> objects = new ArrayList<BookCollectVo>();
        public List<BookCollectVo> getObjects() {
            return objects;
        }
        public void setObjects(List<BookCollectVo> objects) {
            if (objects != null) {
                this.objects = objects;
                notifyDataSetChanged();
            }

        }
        private boolean ispage;
        public InterstBook_SelectAdapter(Activity context) {

            mContext = context;
        }

    public InterstBook_SelectAdapter(OnAdapterHandleListeners onAdapterHandleListeners, Activity mContext) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;
        this.mContext = mContext;
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
         class ViewHolder {
            CheckBox iv_interbook_check;
            ImageView iv_interbook_img;
            TextView iv_interbook_name;
            TextView iv_interbook_zhuanjia;
            RatingBar rb_star;
            TextView iv_interbook_descri;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater layoutInflator = LayoutInflater.from(mContext);
                convertView = layoutInflator.inflate(R.layout.lv_item_interbook, parent,false);
                holder = new ViewHolder();

                holder.iv_interbook_check = (CheckBox) convertView.findViewById(R.id.iv_interbook_check);
                holder.iv_interbook_img = (ImageView) convertView.findViewById(R.id.iv_interbook_img);
                holder.iv_interbook_name = (TextView) convertView.findViewById(R.id.iv_interbook_name);
                holder.iv_interbook_zhuanjia = (TextView) convertView.findViewById(R.id.iv_interbook_zhuanjia);
                holder.rb_star = (RatingBar) convertView.findViewById(R.id.rb_star);
                holder.iv_interbook_descri = (TextView) convertView.findViewById(R.id.iv_interbook_descri);

              //  holder.tv_arm.setOnClickListener(this);
                convertView.setTag(holder);
                AutoUtils.autoSize(convertView);
            }
            holder = (ViewHolder) convertView.getTag();

            BookCollectVo obj = objects.get(position);


            ImageLoader.getInstance().displayImage(obj.getCoverUrl(), holder.iv_interbook_img);
            holder.iv_interbook_name.setText(obj.getName());
            holder.iv_interbook_descri.setText(obj.getIntroduction());
            holder.rb_star.setRating((obj.getRecommend() * 5 / 100));
            holder.iv_interbook_check.setTag(position);

            holder.iv_interbook_check.setChecked(obj.isChoosed());
            holder.iv_interbook_check.setOnCheckedChangeListener(new CheckBoxChangedListener());

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



        void select(List<BookCollectVo> objects, boolean flag);
    }

    private OnAdapterHandleListeners onAdapterHandleListeners;

    public void setOnAdapterHandleListener(OnAdapterHandleListeners onAdapterHandleListeners) {
        this.onAdapterHandleListeners = onAdapterHandleListeners;

    }

    }

