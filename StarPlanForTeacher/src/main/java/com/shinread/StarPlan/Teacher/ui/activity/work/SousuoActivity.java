package com.shinread.StarPlan.Teacher.ui.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinyread.StarPlan.Teacher.R;

import java.util.List;

public class SousuoActivity extends BaseFragmentActivity implements View.OnClickListener {

    public static final String EXTRA_BOOK_NAME = "extra_book_name" ;
    public static final String EXTRA_SORT_ID = "extra_sort_id" ;
    public static final String EXTRA_SORT_NAME = "extra_sort_name" ;
    public static final String EXTRA_CODE = "extra_code" ;

    private ImageView btn_back;
    private EditText txt_title;
    private ListView sousuo_listview;
    private Button btn_sure;
    private List<BookListVo> mSearchBooks ;

    private Long sordId ;
    private String code ;//条形码
    String searchTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);

        initRes();

    }
    private void initIntentData() {
        Intent intent = getIntent();

        code = EXTRA_CODE ;
        sordId = Long.parseLong(EXTRA_SORT_ID );

    }

    private void initRes(){

        btn_back=(ImageView)findViewById(R.id.btn_back);
        txt_title=(EditText)findViewById(R.id.txt_title);
        sousuo_listview=(ListView)findViewById(R.id.sousuo_listview);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_back.setOnClickListener(this);
        txt_title.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        txt_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchByText();
                    return true;
                }
                return false;
            }
        });
    }
    private void searchByText() {
      searchTxt = txt_title.getText().toString() ;
        if(TextUtils.isEmpty(searchTxt)){
            ToastUtil.showMsg(getString(R.string.input_search_content_tip));

            return ;
        }else{
            loadSearchBook();
            CommonAdapter adapter = new CommonAdapter<BookListVo>(FFApplication.instance.getApplicationContext() , mSearchBooks ,R.layout.lv_item_interbook) {
                @Override
                protected void convert(ViewHolder vh, BookListVo item) {
                    CheckBox iv_searchbook_check = vh.getView(R.id.iv_searchbook_check);
                    ImageView iv_searchbook_img = vh.getView(R.id.iv_searchbook_img);
                    TextView iv_searchbook_name = vh.getView(R.id.iv_searchbook_name);
                    TextView iv_searchbook_zhuanjia = vh.getView(R.id.iv_searchbook_zhuanjia);
                    RatingBar rbStar = vh.getView(R.id.rb_star);
                    ;
                    TextView iv_searchbook_descri = vh.getView(R.id.iv_interbook_descri);

                    ImageLoader.getInstance().displayImage(item.getCoverUrl(), iv_searchbook_img);
                    iv_searchbook_name.setText(item.getName());
                    iv_searchbook_descri.setText(item.getIntroduction());


                    rbStar.setRating((item.getRecommend() * 5 / 100));
                }

            };
            sousuo_listview.setAdapter(adapter);
         //   mLvOrdering.setMode(PullToRefreshBase.Mode.PULL_FROM_END);

        }



    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_back:
               finish();
               break;

           case R.id.btn_sure:


       }
    }
    public void loadSearchBook() {
        AppModel.searchBooks(searchTxt, sordId, code, null, null, TAG, new HttpResultListener<SearchBookResp>() {
            @Override
            public void onSuccess(SearchBookResp resp) {
                dismissLoaddingDialog();
                if (resp.isSuccess()) {
                    mSearchBooks = resp.bookListVoArr;

                    if(mSearchBooks == null || mSearchBooks.size() == 0) {
                        ToastUtil.showMsg(getString(R.string.search_null));
                    } else {
                     //   mSearchBookAdapter.setDataChanges(mSearchBooks);
                    }
                    return;
                }
                ToastUtil.showMsg(resp.getMsg());
            }

            @Override
            public void onFailed(Exception e, String msg) {
                ToastUtil.showMsg(msg);
            }
        }) ;
    }
}
