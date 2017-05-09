package com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetMyInterestTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetRecomandBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookDetailsActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter.RecomandBooksAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoadUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogRecomandTagsPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janecer on 2016/3/24.
 * email:jxc@fancyf.cn
 * des:
 */
public class RecomandFragment extends BaseFragment implements View.OnClickListener {
    private GridView mLvRecomand;
    private GridView mGvInterst;
    private RecomandBooksAdapter mRecommadBookAdapter;
    private CommonAdapter<GenericListVo> mInterestTagAdapter;//感兴趣标签集合适配
    private List<GenericListVo> mInterestTags = new ArrayList<>();//个人感兴趣标签集合
    public List<BookListVo> mRecomandsBooks = new ArrayList<>();
    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_library_recomand, null);
            initView(mContentView);
            loadAllData();
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeAllViews();
            }
        }
        return mContentView;
    }
    private TextView tv_edit;
    private void initView(View mContentView) {
        mGvInterst = (GridView) mContentView.findViewById(R.id.gv_interst);
        mLvRecomand = (GridView) mContentView.findViewById(R.id.grid_recomend);
        tv_edit = (TextView) mContentView.findViewById(R.id.tv_edit);
        mInterestTagAdapter = new CommonAdapter<GenericListVo>(FFApplication.instance.getApplicationContext(), mInterestTags, R.layout.gv_item_recomand_interst) {
            @Override
            protected void convert(ViewHolder vh, GenericListVo item) {
                TextView tvTag = vh.getView(R.id.tv_tag);
                tvTag.setText(item.getName());
                tvTag.setTag(item.getId() + "");
            }
        };
        mRecommadBookAdapter = new RecomandBooksAdapter(getActivity());
        mGvInterst.setAdapter(mInterestTagAdapter);
        mLvRecomand.setAdapter(mRecommadBookAdapter);
        mLvRecomand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookListVo vo = mRecomandsBooks.get(position);
                Intent i = new Intent(getActivity(), BookDetailsActivity.class);
                i.putExtra("bookId", vo.getId());
                startActivity(i);
            }
        });
        tv_edit.setOnClickListener(this);
        loadUtil = new LoadUtil(getActivity(), mContentView, new LoadUtil.onLoadListener() {
            @Override
            public void OnLoadException() {
                loadAllData();
            }
            @Override
            public void OnRefresh(LoadUtil.LoadUtilRefreshLayoutDirection direction) {
                loadAllData();
            }
        });
    }
    LoadUtil loadUtil;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_edit) {
            showInterestSelectDialog();
        }
    }
    private void loadAllData(){
        loadRecomandBook();
        loadInterestTag();
    }
    DialogRecomandTagsPicker dialogRecomandTagsPicker;
    private void showInterestSelectDialog(){
        if (dialogRecomandTagsPicker == null){
            dialogRecomandTagsPicker = new DialogRecomandTagsPicker(getActivity(),mInterestTags);
            dialogRecomandTagsPicker.setListenser(new DialogRecomandTagsPicker.OnDialogRecomandTagsPicker() {
                @Override
                public void ChooseResult(List<GenericListVo> interestTags) {
                    mInterestTags = interestTags;
                    mInterestTagAdapter.setDataChange(mInterestTags);
                    loadRecomandBook();
                }
            });
        }
        dialogRecomandTagsPicker.show();
    }
    /**
     * 加载感兴趣标签列表
     */
    public void loadInterestTag() {
        CommonAppModel.getMyInterestTag(TAG, new HttpResultListener<GetMyInterestTagResp>() {
            @Override
            public void onSuccess(GetMyInterestTagResp resp) {
                if (resp.isSuccess()) {
                    mInterestTags = resp.bookLabelVoArr;
                    mInterestTagAdapter.setDataChange(mInterestTags);
                }
            }
            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }
    /**
     * 加载图书推荐列表
     */
    public void loadRecomandBook() {
        CommonAppModel.getRecomandBooks(TAG, new HttpResultListener<GetRecomandBookResp>() {
            @Override
            public void onSuccess(GetRecomandBookResp resp) {
                loadUtil.showLoadSuccess();
                if (resp.isSuccess()) {
                    mRecomandsBooks = resp.bookListVoArr;
                    mRecommadBookAdapter.setObjects(mRecomandsBooks);
                }
                if (mRecomandsBooks != null && mRecomandsBooks.size() > 0) {
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
