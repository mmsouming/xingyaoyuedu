package com.shinread.StarPlan.Teacher.ui.activity.work.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.ImageBDInfo;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.PicBrowserActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.LogicUtil;
import com.shinread.StarPlan.Teacher.ui.widget.DialogComment2;
import com.shinyread.StarPlan.Teacher.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




public class TWorkListAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity mContext;
    private List<WorkVo> objects = new ArrayList<WorkVo>();
    private WorkVo vo;

    public List<WorkVo> getObjects() {
        return objects;
    }
    public void setObjects(List<WorkVo> objects) {
        if (objects != null) {
            this.objects = objects;
            notifyDataSetChanged();
        }
    }
    public TWorkListAdapter(Activity context) {
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

    private DialogComment2 dg;
    @Override
    public void onClick(View view) {
        int id = view.getId();
        final int position = (int) view.getTag();
        WorkVo vo = objects.get(position);
        if (id == R.id.txt_all){
            vo.isExtend = !vo.isExtend;
            notifyDataSetChanged();
        }else if(id == R.id.btn_dianping){
             dg = new DialogComment2(mContext,vo.getId());
             dg.setListenser(new DialogComment2.OnDialogComment() {
                @Override
                public void ChooseResult(WorkVo work) {
                    objects.remove(position);
                    objects.add(position,work);
                    notifyDataSetChanged();
                }
            });
            dg.show();
        }else if(id == R.id.img_pics){

        }
    }
    static class ViewHolder {
        ImageView img_head;
        TextView txt_classmate_name;
        TextView txt_classmate_detail;
        TextView txt_classmate_time;
        TextView txt_content;
        TextView txt_all;
        ImageView img_pics;
        TextView txt_pic_no;
        TextView txt_info;
        Button btn_dianping;
        LinearLayout layout_dianping;
        TextView txt_dianping;
        ImageView img_level;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lv_item_work_list, parent,false);
            holder = new ViewHolder();
            holder.img_head = (ImageView) convertView.findViewById(R.id.img_head);
            holder.txt_classmate_name = (TextView) convertView.findViewById(R.id.txt_classmate_name);
            holder.txt_classmate_detail = (TextView) convertView.findViewById(R.id.txt_classmate_detail);
            holder.txt_classmate_time = (TextView) convertView.findViewById(R.id.txt_classmate_time);
            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            holder.txt_all = (TextView) convertView.findViewById(R.id.txt_all);
            holder.img_pics = (ImageView) convertView.findViewById(R.id.img_pics);
            holder.txt_pic_no = (TextView) convertView.findViewById(R.id.txt_pic_no);
            holder.txt_info = (TextView) convertView.findViewById(R.id.txt_info);
            holder.btn_dianping = (Button) convertView.findViewById(R.id.btn_dianping);
            holder.layout_dianping = (LinearLayout) convertView.findViewById(R.id.layout_dianping);
            holder.txt_dianping = (TextView) convertView.findViewById(R.id.txt_dianping);
            holder.img_level = (ImageView) convertView.findViewById(R.id.img_level);
            convertView.setTag(holder);

            AutoUtils.autoSize(convertView);
            holder.txt_all.setOnClickListener(this);

            holder.txt_all.setTag(position);
            holder.btn_dianping.setTag(position);
            holder.btn_dianping.setOnClickListener(this);

            holder.img_pics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext,
//                            PicBrowserActivity.class);
//                    ImageBDInfo bdInfo = new ImageBDInfo();
//                    int[] location = new int[2];
//                    int x = location[0];
//                    int y = location[1];
//                    bdInfo.x = x;
//                    bdInfo.y = y;
//                    bdInfo.width = v.getWidth();
//                    bdInfo.height =v.getHeight();
//                    intent.putExtra("data", (Serializable) vo.pictureUrlArr);
//                    intent.putExtra("bdinfo", bdInfo);
//                    intent.putExtra("index", position);
//                    intent.putExtra("type", 3);
//                    mContext.startActivity(intent);
                }
            });
        }
        holder = (ViewHolder) convertView.getTag();
        vo = objects.get(position);
        CommonUtils.loadImage(holder.img_head, vo.getHeadUrl());
        holder.txt_classmate_name.setText(vo.getStudentName());
        holder.txt_classmate_detail.setText(vo.getClassesName());
        holder.txt_classmate_time.setText(vo.getTime());
        holder.txt_content.setText(vo.getContent());
        if (vo.pictureUrlArr.size()>0){
            CommonUtils.loadImage(holder.img_pics,vo.pictureUrlArr.get(0));
//            Glide.with(FFApplication.instance).load(vo.pictureUrlArr.get(0)).centerCrop()
//                    .into(holder.img_pics);
            holder.txt_pic_no.setText(vo.pictureUrlArr.size()+"");
            holder.txt_pic_no.setVisibility(View.VISIBLE);

        }else {
            holder.txt_pic_no.setVisibility(View.GONE);
        }
        if (vo.isExtend){
            holder.txt_content.setMaxLines(100);
        }else {
            holder.txt_content.setMaxLines(2);
        }
        if (vo.getWorkStatus() < WorkStatusEnum.HAS_COMMENTED.getNo()){
            //未点评
            holder.btn_dianping.setVisibility(View.VISIBLE);
            holder.layout_dianping.setVisibility(View.GONE);
            holder.img_level.setVisibility(View.GONE);
        }else{
            //已点评
            holder.btn_dianping.setVisibility(View.GONE);
            holder.layout_dianping.setVisibility(View.VISIBLE);
            holder.img_level.setVisibility(View.VISIBLE);
            holder.img_level.setImageResource(LogicUtil.getLevelResId(vo.getWorkStatus(), vo.getWorkLevel()));
            holder.txt_dianping.setText(vo.getComment());
        }
        holder.txt_info.setText(vo.getName());
        return convertView;
    }



}
