package com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReserveVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CancelReserveResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveListResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookDetailsActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoadUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janecer on 2016/3/28.
 * email:jxc@fancyf.cn
 * des:预约书籍列表
 */
public class OrderingFragment extends BaseFragment {


    private ListView mLvOrdering ;
    private CommonAdapter<ReserveVo> adapter ;


    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if ( null == mContentView ) {
            mContentView = inflater.inflate(R.layout.fragment_ordering , null) ;


            mLvOrdering = (ListView)mContentView.findViewById(R.id.lv_pull);

            adapter = new CommonAdapter<ReserveVo>(FFApplication.instance.getApplicationContext() , null ,R.layout.lv_item_ordering) {
                @Override
                protected void convert(ViewHolder vh, ReserveVo item) {

                }
                @Override
                protected void convert(ViewHolder vh,ReserveVo item,int position){
                    ((TextView)vh.getView(R.id.tv_book_name)).setText(item.getName());
                    ImageView ivBookIco = vh.getView(R.id.iv_book_ico) ;
                    ImageLoader.getInstance().displayImage(item.getCoverUrl() , ivBookIco);
                    CommonUtils.loadImage(ivBookIco,item.getCoverUrl());
                    TextView tvTime = vh.getView(R.id.tv_time) ;
                    tvTime.setText(item.getDate()+"  前取书" );
                    Button btn_ordering_cancel = vh.getView(R.id.btn_ordering_cancel) ;
                    Button btn_ordering = vh.getView(R.id.btn_ordering) ;
                    btn_ordering.setVisibility(View.GONE);
                    btn_ordering_cancel.setTag(position);
                    // btnOrdding.setTag(R.string.deleted_key,item.getReserveId());
                    btn_ordering_cancel.setOnClickListener(listener);
                }
            } ;
            mLvOrdering.setAdapter(adapter);

            mLvOrdering.setDividerHeight(0);
            mLvOrdering.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ReserveVo vo = datas.get(position);
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
                    loadData(false);
                }
            });

            loadUtil.setDirection(LoadUtil.LoadUtilRefreshLayoutDirection.TOP);
        }


        return mContentView ;
    }


    public void setLoad(){
        if (datas != null && datas.size() >0){
            loadUtil.showLoadSuccess();
        }else {
            loadUtil.showLoadException();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(false);
    }

    LoadUtil loadUtil;

    BasePageReq req = new BasePageReq();
    Long timestamp = Long.valueOf(0);

    private List<ReserveVo> datas = new ArrayList<>();

    private void loadData(final boolean isMore) {

        if (!isMore) {
            timestamp = Long.valueOf(0);
        }
        req.id = UserInfoManager.getInstance().getDefaultID();
        req.timestamp = timestamp;
        CommonAppModel.reserveList(req, new HttpResultListener<ReserveListResp>() {
            @Override
            public void onSuccess(ReserveListResp resp) {

                if (resp.isSuccess()) {
                    List<ReserveVo> temp = resp.reserveVoArr;
//                    if (temp.size() > 0) {
//                        timestamp = temp.get(temp.size() - 1).getTimestamp();
//                    }
                    if (!isMore) {
                        datas = temp;
                    } else {
                        datas.addAll(temp);
                    }
                    adapter.setDataChange(datas);
                }

                setLoad();
            }

            @Override
            public void onFailed(Exception e, String msg) {
                loadUtil.showLoadException();
            }
        });

    }



    private  View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.btn_ordering_cancel) {
                Integer position = (Integer) v.getTag();
                ReserveVo vo = datas.get(position);
                CommonAppModel.unReserve(getActivity(),vo.getReserveId(), new HttpResultListener<CancelReserveResp>() {
                    @Override
                    public void onSuccess(CancelReserveResp resp) {

                        if (resp.isSuccess()) {
                            datas = resp.reserveVoArr;
                            adapter.setDataChange(datas);
                            setLoad();
                            ToastUtil.showMsg(resp.getMsg());
                        }

                    }

                    @Override
                    public void onFailed(Exception e, String msg) {


                    }
                });
            }
        }
    } ;


}
