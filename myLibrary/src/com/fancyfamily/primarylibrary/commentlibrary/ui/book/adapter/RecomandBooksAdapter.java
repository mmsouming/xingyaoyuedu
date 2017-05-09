package com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janecer on 2016/3/24.
 * email:jxc@fancyf.cn
 * des: 个人设置的兴趣爱好集合
 */
public class RecomandBooksAdapter extends BaseAdapter {


    Activity mContext;

    public RecomandBooksAdapter(Activity context) {
        mContext = context;


    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_recomand_interst, parent, false);
            holder = new ViewHolder();
            holder.mIvBook1 = (ImageView) convertView.findViewById(R.id.iv_book1);
            holder.rbStar1 = (RatingBar) convertView.findViewById(R.id.rb_star1);
            holder.tv_pro_title1 = (TextView) convertView.findViewById(R.id.tv_pro_title1);


            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }
        holder = (ViewHolder) convertView.getTag();

        BookListVo obj = objects.get(position);

        CommonUtils.loadImage(holder.mIvBook1, obj.coverUrl);
        holder.rbStar1.setRating(obj.getRecommend()/20);
        holder.tv_pro_title1.setText(obj.getName());

        return convertView;
    }


    static class ViewHolder {

        ImageView mIvBook1;
        RatingBar rbStar1;
        TextView tv_pro_title1;
    }


}
