package com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelSortVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelVo;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


public class AllTagsAdapter extends BaseAdapter {

    private Activity mContext;
    private List<BookLabelSortVo> objects = new ArrayList<BookLabelSortVo>();

    public List<BookLabelSortVo> getObjects() {
        return objects;
    }

    public void setObjects(List<BookLabelSortVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }

    }


    public AllTagsAdapter(Activity context) {
        mContext = context;

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

    static class ViewHolder {
        TextView tv_tag_title;
        GridView gv_tags;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_interesttag_item, parent, false);
            holder = new ViewHolder();
            AllTagsSubAdapter adapter = new AllTagsSubAdapter(mContext,this);
            holder.tv_tag_title = (TextView) convertView.findViewById(R.id.tv_tag_title);
            holder.gv_tags = (GridView) convertView.findViewById(R.id.gv_tags);
            holder.gv_tags.setAdapter(adapter);

            AutoUtils.autoSize(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        BookLabelSortVo obj = objects.get(position);
        holder.tv_tag_title.setText(obj.getName());
        ((AllTagsSubAdapter)holder.gv_tags.getAdapter()).setObjects(obj.getBookLabelVoArr());
        return convertView;
    }

    public int maxNum = 6;

    public boolean canChoose(){
        int chooseNum = 0;
        for (int i = 0;i<objects.size();i++){
            BookLabelSortVo obj = objects.get(i);
            List<BookLabelVo> bookLabelVoArr = obj.getBookLabelVoArr();
            for (int j = 0;j < bookLabelVoArr.size(); j++){
                BookLabelVo vo = bookLabelVoArr.get(j);
                if (vo.isSelect){
                    chooseNum ++;
                }
            }
        }
        return chooseNum < maxNum;
    }


    public ArrayList<Long> getSelectList(){
        ArrayList<Long> selectlist = new ArrayList<>();
        for (int i = 0;i<objects.size();i++){
            BookLabelSortVo obj = objects.get(i);
            List<BookLabelVo> bookLabelVoArr = obj.getBookLabelVoArr();
            for (int j = 0;j < bookLabelVoArr.size(); j++){
                BookLabelVo vo = bookLabelVoArr.get(j);
                if (vo.isSelect){
                    selectlist.add(vo.getId());
                }
            }
        }
        return selectlist;
    }
}



