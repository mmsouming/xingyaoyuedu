package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.Activity;
import android.view.View;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

/**
 * Created by cloud-pc on 2016/5/4.
 */
public class LoadUtil {

    public enum LoadUtilRefreshLayoutDirection {

        TOP(0),
        BOTTOM(1),
        BOTH(2);

        private int mValue;

        LoadUtilRefreshLayoutDirection(int value) {
            this.mValue = value;
        }

    }

    public interface onLoadListener {

        void OnLoadException();

        void OnRefresh(LoadUtilRefreshLayoutDirection direction);

    }

    SwipyRefreshLayout swipyrefreshlayout;
    public LoadTipManager manager;
    onLoadListener mListener;

    Activity mContext;
    public LoadUtil(Activity context,  onLoadListener listener) {
        this(context,null,listener);
    }
    public LoadUtil(Activity context, View view, onLoadListener listener) {
        this(context,view,R.id.swipyrefreshlayout,listener);

    }

    public LoadUtil(Activity context, View view, int id, onLoadListener listener) {
        mContext = context;
        mListener = listener;

        manager = new LoadTipManager(mContext, view, id, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnLoadException();
            }
        });
        manager.setBackgroundResource(R.drawable.radiu_white_bg);

        if (view == null){
            swipyrefreshlayout = (SwipyRefreshLayout) context.findViewById(R.id.swipyrefreshlayout);
        }else{
            swipyrefreshlayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        }

        swipyrefreshlayout.setColorSchemeResources(R.color.bule_1);
        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                mListener.OnRefresh(direction == SwipyRefreshLayoutDirection.TOP ? LoadUtilRefreshLayoutDirection.TOP : LoadUtilRefreshLayoutDirection.BOTTOM);
            }
        });

    }

    public void setDirection(LoadUtilRefreshLayoutDirection direction){

        if (direction == LoadUtilRefreshLayoutDirection.BOTH){
            swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        }
        if (direction == LoadUtilRefreshLayoutDirection.TOP){
            swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.TOP);
        }
        if (direction == LoadUtilRefreshLayoutDirection.BOTTOM){
            swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
        }
    }

    public void setRefreshing(boolean refreshing){
        if (!refreshing){
            manager.showLoadSuccess();
        }
        swipyrefreshlayout.setRefreshing(refreshing);
    }

    public void showLoadSuccess(){
        manager.showLoadSuccess();
        swipyrefreshlayout.setRefreshing(false);
    }

    public void showLoadException(){
        manager.showLoadException();
        swipyrefreshlayout.setRefreshing(false);
    }

}
