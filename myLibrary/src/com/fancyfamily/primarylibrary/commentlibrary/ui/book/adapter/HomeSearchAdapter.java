package com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelSortVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.LabelSowTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.SearchBookActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


public class HomeSearchAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Activity mContext;

    public void setObjects(List<BookLabelSortVo> objects) {
        if (objects != null) {
            this.objects = objects;
        }
        notifyDataSetChanged();
    }

    private List<BookLabelSortVo> objects = new ArrayList<>();

    public HomeSearchAdapter(Activity context) {
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_sort, parent,
                    false);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.gv_sort = (GridView) convertView.findViewById(R.id.gv_sort);

            HomeSearchGridAdapter adapter = new HomeSearchGridAdapter(mContext);
            holder.gv_sort.setAdapter(adapter);
            holder.gv_sort.setOnItemClickListener(this);
            AutoUtils.autoSize(convertView);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();

        BookLabelSortVo obj = objects.get(position);

        if (obj.getLabelSowType() == LabelSowTypeEnum.WORDS.getNo()) {
            holder.gv_sort.setNumColumns(3);
        }
        if (obj.getLabelSowType() == LabelSowTypeEnum.PICTURES.getNo()) {
            holder.gv_sort.setNumColumns(4);
        }
        if (obj.getLabelSowType() == LabelSowTypeEnum.FIGURE_BELOW.getNo()) {
            holder.gv_sort.setNumColumns(4);
        }

        holder.tv_title.setText(obj.getName());
        ((HomeSearchGridAdapter) holder.gv_sort.getAdapter())
                .showType = obj.getLabelSowType();
        ((HomeSearchGridAdapter) holder.gv_sort.getAdapter())
                .setList(obj.getBookLabelVoArr());

        return convertView;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookLabelVo vo = ((HomeSearchGridAdapter) parent.getAdapter())
                .getItem(position);

        Intent intent = new Intent(mContext, SearchBookActivity.class) ;
        intent.putExtra(SearchBookActivity.EXTRA_SORT_ID,vo.getId()) ;
        intent.putExtra(SearchBookActivity.EXTRA_SORT_NAME,vo.getName()) ;
        mContext.startActivity(intent);
//        Intent intent = new Intent(mContext, SearchBookActivity.class);
//        intent.putExtra("labelId", vo.labelId + "");
//        intent.putExtra("labelName", vo.labelName);

 //       mContext.startActivity(intent);

    }


    static class ViewHolder {
        TextView tv_title;
        GridView gv_sort;
    }

}
