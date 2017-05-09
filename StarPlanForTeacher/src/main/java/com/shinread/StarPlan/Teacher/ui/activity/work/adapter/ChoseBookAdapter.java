package com.shinread.StarPlan.Teacher.ui.activity.work.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shinread.StarPlan.Teacher.ui.widget.MyChoseBook;
import com.shinyread.StarPlan.Teacher.R;

/**
 * Created by lizehong on 2016/4/29.选择阅读书籍适配器
 */
public class ChoseBookAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;
   private Activity context;
    public boolean isShape() {
        return shape;
    }

    public ChoseBookAdapter(Activity context) {
        this.context = context;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }



    public void update() {
        loading();
    }

    public int getCount() {
        if (MyChoseBook.mSelectedbookimg.size() == 9) {
            return 9;
        }
        return (MyChoseBook.mSelectedbookimg.size() + 1);
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_select_book, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.iv_book);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == MyChoseBook.mSelectedbookimg.size()) {
            holder.image
                    .setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic_unfocused));
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            String ImagePath =(MyChoseBook.mSelectedbookimg.get(position));
            ImageLoader.getInstance().displayImage(ImagePath, holder.image);

       }
        return convertView;
    }
    public class ViewHolder {
        public ImageView image;
    }
    public void loading() {
        notifyDataSetChanged();
    }
}
