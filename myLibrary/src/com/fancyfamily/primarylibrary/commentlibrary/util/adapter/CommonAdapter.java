package com.fancyfamily.primarylibrary.commentlibrary.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author:janecer 
 * email:jiangxiaocai@youline.com.cn
 */
public abstract class CommonAdapter<T>  extends BaseAdapter {

	protected List<T> mDatas = new ArrayList<T>();
	protected LayoutInflater mInflater;
    protected Context mContext;
    protected int layoutId;
    
    public CommonAdapter(Context ctx, List<T> lists, int layoutId){
    	this.mDatas=lists;
    	mInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	this.mContext=ctx;
    	this.layoutId=layoutId;
    }



    @Override
	public int getCount() {
		return mDatas==null?0:mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh=ViewHolder.getViewHolder(convertView, mContext, parent, layoutId, position);
		convert(vh, mDatas.get(position));
        convert(vh, mDatas.get(position),position);
        AutoUtils.autoSize(vh.getConvertView());
		return vh.getConvertView();
	}

	abstract protected void convert(ViewHolder vh,T item);

    protected void convert(ViewHolder vh,T item,int position){

    }

    public void setDataChange(List<T> list){
        if(null==list){
            return;
        }
//        if(null==mDatas){
//            mDatas=new ArrayList<T>();
//        } else {
//            mDatas.clear();
//        }
        mDatas = list;
        this.notifyDataSetChanged();
    }

    public void addSetDataChange(List<T> list){
        if(null==list){
            return;
        }
//        if(null==mDatas){
//            mDatas=new ArrayList<T>();
//        }
        mDatas.addAll(list);
        this.notifyDataSetChanged();
    }

    public List<T> getApps(){
      return mDatas;
    };

    public void clearData(){
        if(null!=mDatas){
            mDatas.clear();
        }
    }

    public T getItemByPosition(int i){
        if(i<0){
            return null;
        }
        return mDatas.get(i);
    }
}
