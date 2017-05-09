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
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BorrowVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BrrowingListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ExtensionResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookDetailsActivity;
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
 * des: 在借书籍列表
 */
public class OrderFragment extends BaseFragment implements View.OnClickListener {

    private ListView mLvOrdering ;

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

            adapter = new CommonAdapter<BorrowVo>(FFApplication.instance.getApplicationContext() , null ,R.layout.lv_item_ordering) {
                @Override
                protected void convert(ViewHolder vh, BorrowVo item) {

                }

                protected void convert(ViewHolder vh, BorrowVo item,int position) {
                    ((TextView)vh.getView(R.id.tv_book_name)).setText(item.getName());
                    ImageView ivBookIco = vh.getView(R.id.iv_book_ico) ;
                    ImageLoader.getInstance().displayImage(item.getCoverUrl() , ivBookIco);
                    TextView tvTime = vh.getView(R.id.tv_time) ;
                    tvTime.setText(item.getDate()+"  到期" );

                    Button btn = vh.getView(R.id.btn_ordering_cancel);

                    btn.setTag(position);
                    if(item.getExtensionNo() == 0) {
                        btn.setBackgroundResource(R.drawable.btn_gray_commit);
                        btn.setText("不可延期");
                    } else {
                        btn.setBackgroundResource(R.drawable.btn_blue_commit);
                        btn.setText("延迟归还" + item.getExtensionNo()+"次");
                    }


                    btn.setOnClickListener(OrderFragment.this);

                    Button btnOrdding = vh.getView(R.id.btn_ordering) ;
                    btnOrdding.setVisibility(View.GONE);
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
                    loadData(false);
                }
            });

            loadUtil.setDirection(LoadUtil.LoadUtilRefreshLayoutDirection.TOP);
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
        CommonAppModel.borrowingList(req, new HttpResultListener<BrrowingListResp>() {
            @Override
            public void onSuccess(BrrowingListResp resp) {

                if (resp.isSuccess()) {
                    List<BorrowVo> temp = resp.borrowVoArr;
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
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ordering_cancel){
            Integer position = (Integer) v.getTag();
            final BorrowVo vo = datas.get(position);
            CommonAppModel.borrowExtension(getActivity(),vo.getBorrowId(), new HttpResultListener<ExtensionResp>() {
                @Override
                public void onSuccess(ExtensionResp resp) {
                    if(resp.isSuccess()){
                        vo.setExtensionNo(resp.extensionNo);
                        vo.setDate(resp.date);
                        ToastUtil.showMsg(resp.getMsg());
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailed(Exception e, String msg) {
                }
            });
        }

    }



}
