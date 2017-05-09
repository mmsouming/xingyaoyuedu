package com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyVo;

import java.util.ArrayList;
import java.util.List;


public class CommentReplyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ReplyVo> objs = new ArrayList<ReplyVo>();

    public CommentReplyAdapter(Context mContext) {
        context = mContext;

    }

    public void setList(List<ReplyVo> ls) {
        if (ls != null) {
            this.objs.clear();
            this.objs.addAll(ls);
        }
        notifyDataSetChanged();
    }
    public void setListLimin(List<ReplyVo> ls) {
        if (ls != null) {
            this.objs.clear();
            int legth = ls.size() >3? 3:ls.size();
            for (int i = 0; i < legth; i++) {
                this.objs.add(ls.get(i));
            }

        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public ReplyVo getItem(int position) {

        return objs.get(position);
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
            convertView = layoutInflator.inflate(R.layout.item_list_reply,
                    null);
            holder = new ViewHolder();
            holder.label_txt = (TextView) convertView
                    .findViewById(R.id.label_txt);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        ReplyVo obj = objs.get(position);
        String content = obj.accountVo.nickname;
        String back = " 回复 :";

        content += back + obj.content;
        SpannableString builder = new SpannableString(content);
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(
                Color.parseColor("#56AEAB"));

        int a1s = content.indexOf(obj.accountVo.nickname);


        builder.setSpan(blueSpan, a1s, a1s + obj.accountVo.nickname.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.label_txt.setText(builder);

        return convertView;
    }

    static class ViewHolder {
        TextView label_txt;
    }
}
