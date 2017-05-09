package com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelSortVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookScanActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.SearchBookActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter.HomeSearchAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;

import java.util.List;

/**
 * Created by janecer on 2016/3/25.
 * email:jxc@fancyf.cn
 * des:
 */
public class SortFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtSearch;
    private ImageView  mIvCode ;

    private ListView mLvSort ;


    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == mContentView) {
            initViews(inflater);

            loadDatasFromNet();
        }else {
            ViewGroup parent= (ViewGroup) mContentView.getParent();
            if(null != parent){
                parent.removeAllViews();
            }
        }
        return mContentView ;
    }

    private void initViews(LayoutInflater inflater) {
        mContentView = inflater.inflate(R.layout.fragment_library_sort ,null) ;
        mEtSearch = (EditText) mContentView.findViewById(R.id.et_search) ;
        mIvCode = (ImageView) mContentView.findViewById(R.id.iv_code);
        mContentView.findViewById(R.id.iv_code).setOnClickListener(this);
        mLvSort = (ListView) mContentView.findViewById(R.id.lvs);

        mEtSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchByText();
                    return true;
                }
                return false;
            }
        });
        adapter = new HomeSearchAdapter(getActivity());
        mLvSort.setAdapter(adapter);

    }

    HomeSearchAdapter adapter;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_code){
            Intent i = new Intent(getActivity(), BookScanActivity.class) ;
           // i.putExtra(CaptureActivity.EXTRA_SEARCHBOOK ,1) ;
            startActivity(i);
        }

    }

    private void searchByText() {
        String searchTxt = mEtSearch.getText().toString() ;
        if(TextUtils.isEmpty(searchTxt)){
            ToastUtil.showMsg(getString(R.string.input_search_content_tip));
            return ;
        }

        Intent intent = new Intent(getActivity(), SearchBookActivity.class) ;
        intent.putExtra(SearchBookActivity.EXTRA_BOOK_NAME,searchTxt) ;
        startActivity(intent);
    }

    List<BookLabelSortVo> mDatas = null ;
    public void loadDatasFromNet(){
        CommonAppModel.getLibraryTag(TAG, new HttpResultListener<GetTagResp>() {
            @Override
            public void onSuccess(GetTagResp resp) {

                if (resp.isSuccess()) {
                    mDatas = resp.bookLabelSortVoArr;
                    adapter.setObjects(mDatas);
                }

            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        }) ;
    }


}
