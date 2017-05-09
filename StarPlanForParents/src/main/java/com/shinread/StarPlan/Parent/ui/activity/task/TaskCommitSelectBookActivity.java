package com.shinread.StarPlan.Parent.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.shinyread.StarPlan.Parent.R;

import java.util.ArrayList;

/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:
 */
public class TaskCommitSelectBookActivity extends BaseFragmentActivity {

    private ArrayList<BookListVo> bookListVos ;//图书集合，

    private GridView gvBooks ;
    private TitleBar mTb ;

    private long workId ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_commit_book_select);

        bookListVos = getIntent().getParcelableArrayListExtra("books") ;
        workId = getIntent().getLongExtra("workId",0) ;

        if(null == bookListVos || bookListVos.size() == 0) {
            ToastUtil.showMsg(getString(R.string.operate_fail));
            finish();
            return ;
        }

        initViews();
    }

    private void initViews() {
        mTb = (TitleBar) findViewById(R.id.tb) ;
        mTb.setOnLeftNavClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gvBooks = (GridView) findViewById(R.id.gv_books) ;

        gvBooks.setAdapter(new CommonAdapter<BookListVo>(this,bookListVos,R.layout.gv_item_select_books) {
            @Override
            protected void convert(ViewHolder vh, BookListVo item) {
                ImageView ivBook = (ImageView)vh.getView(R.id.iv_book_ico) ;
                ImageLoader.getInstance().displayImage(item.getCoverUrl(),ivBook);
            }
        });
        
        gvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TaskCommitSelectBookActivity.this,TaskCommitNextActivity.class) ;
                intent.putExtra("book",bookListVos.get(position)) ;
                intent.putExtra("workId",workId) ;
                startActivity(intent);
            }
        });
    }
    
}
