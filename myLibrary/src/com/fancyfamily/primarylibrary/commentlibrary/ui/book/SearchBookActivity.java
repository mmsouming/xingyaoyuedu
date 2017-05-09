package com.fancyfamily.primarylibrary.commentlibrary.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter.RecomandBooksAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoadUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;

import java.util.List;

/**
 * author:janecer on 2016/4/1 16:35
 * email:janecer@sina.cn
 */

public class SearchBookActivity extends BaseFragmentActivity {

    public static final String EXTRA_BOOK_NAME = "extra_book_name" ;
    public static final String EXTRA_SORT_ID = "extra_sort_id" ;
    public static final String EXTRA_SORT_NAME = "extra_sort_name" ;
    public static final String EXTRA_CODE = "extra_code" ;

    private GridView grid_recomend ;

    private RecomandBooksAdapter mSearchBookAdapter ;
    private List<BookListVo> mSearchBooks  ;

    private String name ;
    private Long sordId ;
    private String code ;//条形码

    private String mTitleTxt ; //标题栏显示的字体

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_library_search);

        initIntentData();

        initViews();

    }

    private void initIntentData() {
        Intent intent = getIntent();
        name = intent.getStringExtra(EXTRA_BOOK_NAME) ;
        code = intent.getStringExtra(EXTRA_CODE) ;
        sordId = intent.getLongExtra(EXTRA_SORT_ID, -1) ;
        if(!TextUtils.isEmpty(name)){
            mTitleTxt = getString(R.string.search_title_content) ;
        } else if(!TextUtils.isEmpty(code)){
            mTitleTxt = getString(R.string.search_title_content) ;
        } else if(sordId != -1){
            mTitleTxt = intent.getStringExtra(EXTRA_SORT_NAME) ;
        }

        req.name = name;
        req.barcode = code;
        req.labelId = sordId == -1?null:sordId;
    }

    private void initViews() {
        TitleBar tb = (TitleBar) findViewById(R.id.tb);
        tb.setTitleText(mTitleTxt);
        tb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        grid_recomend = (GridView) findViewById(R.id.grid_recomend) ;
        mSearchBookAdapter = new RecomandBooksAdapter(this) ;
        grid_recomend.setAdapter(mSearchBookAdapter);
        grid_recomend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookListVo vo = mSearchBooks.get(position);
                Intent intentBookDetail = new Intent(SearchBookActivity.this , BookDetailsActivity.class) ;
                intentBookDetail.putExtra("bookId",vo.getId());
                startActivity(intentBookDetail);
            }
        });


        loadUtil = new LoadUtil(this, new LoadUtil.onLoadListener() {
            @Override
            public void OnLoadException() {
                loadData(false);
            }

            @Override
            public void OnRefresh(LoadUtil.LoadUtilRefreshLayoutDirection direction) {
                loadData(direction == LoadUtil.LoadUtilRefreshLayoutDirection.TOP?false:true);
            }
        });
        loadData(false);
    }

    LoadUtil loadUtil;


    SearchBookReq req = new SearchBookReq();
    int pageNo = 0;


    private void loadData(final boolean isMore) {

        if (!isMore) {
            pageNo = 0;
        }
        req.id = UserInfoManager.getInstance().getDefaultID();
        req.pageNo = pageNo;
        CommonAppModel.searchBooks(req, new HttpResultListener<SearchBookResp>() {
            @Override
            public void onSuccess(SearchBookResp resp) {

                if (resp.isSuccess()) {
                    List<BookListVo> temp = resp.bookListVoArr;
                    pageNo++;
                    if (!isMore) {
                        mSearchBooks = temp;
                    } else {
                        mSearchBooks.addAll(temp);
                    }
                    mSearchBookAdapter.setObjects(mSearchBooks);
                }

                if (mSearchBooks != null && mSearchBooks.size() > 0) {
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
