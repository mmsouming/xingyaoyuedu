package com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BorrowVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BorrowedListResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookDetailsActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoadUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janecer on 2016/3/28.
 * email:jxc@fancyf.cn
 * des: 已还 书籍列表
 */
public class OrderedFragment extends BaseFragment {

    private ListView mLvOrdering ;

    private List<BorrowVo> mDatas = new ArrayList<>() ;


    private CommonAdapter<BorrowVo> adapter ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if ( null == mContentView ) {
            mContentView = inflater.inflate(R.layout.fragment_ordering , null) ;
            mLvOrdering = (ListView) mContentView.findViewById(R.id.lv_pull);
            adapter = new CommonAdapter<BorrowVo>(FFApplication.instance.getApplicationContext() , mDatas ,R.layout.lv_item_ordered) {
                @Override
                protected void convert(ViewHolder vh, BorrowVo item) {
                    ImageView ivBookIco = vh.getView(R.id.iv_book_ico) ;
                    //ImageLoader.getInstance().displayImage(item.getCoverUrl() , ivBookIco);
                    CommonUtils.loadImage(ivBookIco,item.getCoverUrl());
                    ((TextView)vh.getView(R.id.tv_book_name)).setText(item.getName());
                    ((TextView)vh.getView(R.id.tv_start_time)).setText(getString(R.string.order_start_time) + item.getBorrowDate());
                    ((TextView)vh.getView(R.id.tv_end_time)).setText(getString(R.string.order_end_time) + item.getDate());
                }
            } ;
            mLvOrdering.setAdapter(adapter);

            mLvOrdering.setDividerHeight(0);
            mLvOrdering.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BorrowVo vo = datas.get(position);
                    Intent i = new Intent(getActivity(), BookDetailsActivity.class);
                    i.putExtra("bookId",vo.getId());
                    startActivity(i);
                }
            });
            loadUtil = new LoadUtil(getActivity(),mContentView, new LoadUtil.onLoadListener() {
                @Override
                public void OnLoadException() {

                    loadData(false);
                }

                @Override
                public void OnRefresh(LoadUtil.LoadUtilRefreshLayoutDirection direction) {
                    loadData(direction == LoadUtil.LoadUtilRefreshLayoutDirection.TOP ?false:true);
                }
            });

            //loadUtil.setDirection(LoadUtil.LoadUtilRefreshLayoutDirection.TOP);
        }

        return mContentView ;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(false);
    }

    LoadUtil loadUtil;

    BasePageReq req = new BasePageReq();
    Long timestamp = Long.valueOf(0);

    private List<BorrowVo> datas = new ArrayList<>();

    private void loadData(final boolean isMore) {

        if (!isMore) {
            timestamp = Long.valueOf(0);
        }
        req.id = UserInfoManager.getInstance().getDefaultID();
        req.timestamp = timestamp;
        CommonAppModel.borrowedList(req, new HttpResultListener<BorrowedListResp>() {
            @Override
            public void onSuccess(BorrowedListResp resp) {

                if (resp.isSuccess()) {
                    List<BorrowVo> temp = resp.borrowVoArr;
                    if (temp.size() > 0) {
                        timestamp = temp.get(temp.size() - 1).timestamp;
                    }
                    if (!isMore) {
                        datas = temp;
                    } else {
                        datas.addAll(temp);
                    }
                    adapter.setDataChange(datas);
                }

                if (datas != null && datas.size() > 0) {
                    loadUtil.showLoadSuccess();
                } else {
                    loadUtil.showLoadException();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                loadUtil.showLoadException();
            }
        });

    }

}
