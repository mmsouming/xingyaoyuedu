package com.shinread.StarPlan.Teacher.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkQuestionVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkGetResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;

/**
 * Created by janecer on 2016/3/24.
 * email:jxc@fancyf.cn
 * des:
 */
public class TaskCommitActivity extends BaseFragmentActivity implements View.OnClickListener {

    private TitleBar mTb ;
    private ListView mLvTask  ;
    private TextView tvNeed ,tvStartTimeValue ,tvEndTimeValue ;//作业要求

    private WorkVo work;
    private WorkQuestionVo workQuestionVo;

    private long workId ;

    private ArrayList<BookListVo> bookListVos ;//图书集合，

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_commit);

        workId  = getIntent().getLongExtra("workId" , 0) ;
        if(workId == 0) {
            ToastUtil.showMsg(getString(R.string.operate_fail));
            finish();
        }

        initViews() ;

        loadData();
    }

    private void initViews() {
        mTb = (TitleBar) findViewById(R.id.tb) ;
        mTb.setOnLeftNavClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_commit).setOnClickListener(this);
        mLvTask = (ListView) findViewById(R.id.lv_task) ;

        View headView = getLayoutInflater().inflate(R.layout.lv_item_header_task , null) ;
        tvNeed = (TextView) headView.findViewById(R.id.tv_need) ;
        tvStartTimeValue = (TextView) headView.findViewById(R.id.tv_start_time_value) ;
        tvEndTimeValue = (TextView) headView.findViewById(R.id.tv_end_time_value);

        mLvTask.addHeaderView(headView);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_commit://提交作业
                Intent intent = new Intent(this,TaskCommitSelectBookActivity.class) ;
                intent.putParcelableArrayListExtra("books",bookListVos) ;
                intent.putExtra("workId",work.getId()) ;
                startActivity(intent);
                break ;
        }
    }

    public void loadData() {
        showLoaddingDialog();
        AppModel.getWorkById(workId, TAG, new HttpResultListener<WorkGetResponseVo>() {
            @Override
            public void onSuccess(WorkGetResponseVo resp) {
                dismissLoaddingDialog();
                if(resp.isSuccess()){
                    work = resp.getWork() ;
                    workQuestionVo = resp.getWorkQuestionVo() ;

                    tvNeed.setText(workQuestionVo.getContent());
                    tvStartTimeValue.setText(workQuestionVo.getEffectiveTime());
                    tvEndTimeValue.setText(workQuestionVo.getInvalidTime());
                    if(null == workQuestionVo) {
                        return ;
                    }
                    bookListVos = (ArrayList<BookListVo>) workQuestionVo.getBookListVoArr();
                    mLvTask.setAdapter(new CommonAdapter<BookListVo>(TaskCommitActivity.this , bookListVos ,R.layout.lv_item_task) {
                        @Override
                        protected void convert(ViewHolder vh, BookListVo item) {
                            ImageView ivBook = vh.getView(R.id.iv_book) ;
                            TextView tvBook = vh.getView(R.id.tv_book) ;
                            TextView tvAuthor = vh.getView(R.id.tv_author) ;
                            RatingBar rbStar = vh.getView(R.id.rb_star) ;
                            TextView tvDes = vh.getView(R.id.tv_des) ;

                            ImageLoader.getInstance().displayImage(item.getCoverUrl(),ivBook);
                            tvBook.setText(item.name);
                            tvAuthor.setText(item.getAuthor());
                            rbStar.setNumStars(5);
                            rbStar.setMax(100);
                            rbStar.setRating(item.getRecommend());
                            tvDes.setText(item.getIntroduction());
                        }
                    });
                    return;
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                dismissLoaddingDialog();
                ToastUtil.showMsg(msg);
            }
        }) ;
    }

}
