package com.shinread.StarPlan.Parent.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkQuestionVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkGetResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.BookDetailsActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.fancyfamily.primarylibrary.commentlibrary.util.LogicUtil;
import com.shinyread.StarPlan.Parent.R;

import java.util.ArrayList;

/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:
 */
public class TaskCommitDetailActivity extends BaseFragmentActivity implements View.OnClickListener {

    private TitleBar mTb;
    private long workId;

    private WorkVo workVo;
    WorkQuestionVo workQuestionVo;
    private ImageView ivContents, ivState, ivBook;
    private TextView tvPicCount, tvBookDes, tvBook, tvDes, tvComment, tvTeacherTitle, tvState;
    private Button btnSure,btn_send;
    LinearLayout layout_teacher_comment;
    RelativeLayout layout_book_info;
    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_commit_detail);

        workId = getIntent().getLongExtra("workId", -1);
        if (workId == -1) {
            ToastUtil.showMsg(getString(R.string.operate_fail));
            return;
        }

        initViews();

        manager = new LoadTipManager(this, R.id.ll_scontent, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWork();
            }
        });

        loadWork();
    }
    LoadTipManager manager;
    boolean hasMeasured = false;

    private void initViews() {
        mTb = (TitleBar) findViewById(R.id.tb);
        mTb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_send = (Button) findViewById(R.id.btn_send);
        btnSure = (Button) findViewById(R.id.btn_sure);
        ivContents = (ImageView) findViewById(R.id.iv_contents);
        ivState = (ImageView) findViewById(R.id.iv_state);
        ivBook = (ImageView) findViewById(R.id.iv_book);
        tvPicCount = (TextView) findViewById(R.id.tv_pic_count);
        tvBookDes = (TextView) findViewById(R.id.tv_book_des);
        tvBook = (TextView) findViewById(R.id.tv_book);
        tvDes = (TextView) findViewById(R.id.tv_des);
        tvComment = (TextView) findViewById(R.id.tv_comment);
        tvTeacherTitle = (TextView) findViewById(R.id.tv_teacher_title);
        tvState = (TextView) findViewById(R.id.tv_state);
        layout_teacher_comment  = (LinearLayout) findViewById(R.id.layout_teacher_comment);
        layout_book_info  = (RelativeLayout) findViewById(R.id.layout_book_info);
        btn_send.setOnClickListener(this);
        layout_book_info.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        ivContents.setOnClickListener(this);
    }

    private void initData2Views() {

        mTb.setTitleText(workVo.getName());

        if (null != workVo.pictureUrlArr && workVo.pictureUrlArr.size() > 0) {
            String pic = workVo.pictureUrlArr.get(0);
            int size = workVo.pictureUrlArr.size();
            CommonUtils.loadImage(ivContents, pic);
            tvPicCount.setText(size + "");
            tvPicCount.setVisibility(View.VISIBLE);
        } else {
            tvPicCount.setVisibility(View.GONE);
        }

        tvBookDes.setText(workVo.getContent());

        CommonUtils.loadImage(ivBook, workVo.getBookListVo().getCoverUrl());
        tvBook.setText(workVo.getBookListVo().getName());
        tvDes.setText(workVo.getBookListVo().getIntroduction());

//        if (workVo.getWorkStatus().equals(WorkStatusEnum.SUBMITTED.getNo())) {//已提交  点评中
//            if (workVo.getWorkLevel().equals(WorkLevelEnum.UNCOMMENT.getNo())) {
//                btnSure.setVisibility(View.GONE);
//                tvState.setVisibility(View.VISIBLE);
//                layout_teacher_comment.setVisibility(View.GONE);
//            } else {
//                btnSure.setVisibility(View.VISIBLE);
//                btnSure.setText(R.string.share_read_feel);
//                tvState.setVisibility(View.GONE);
//                tvTeacherTitle.setVisibility(View.VISIBLE);
//                tvComment.setText(workVo.getComment());
//            }
//        } else if (workVo.getWorkStatus().equals(WorkStatusEnum.REPULSE.getNo())) {
//            btnSure.setText(R.string.commit_agin_task);
//        }

        tvComment.setText(workVo.getComment());
        Integer workState = workVo.getWorkStatus();
        Integer level = workVo.getWorkLevel();

        if (workState >= WorkStatusEnum.HAS_COMMENTED.getNo()) {
            btnSure.setVisibility(View.VISIBLE);
            layout_teacher_comment.setVisibility(View.VISIBLE);
            tvState.setVisibility(View.GONE);
            btnSure.setText(R.string.share_read_feel);
            btnSure.setEnabled(true);
            if (workState >= WorkStatusEnum.HAS_COMMENTED_NOT_SHERE.getNo()){
                btnSure.setText("已分享");
                btnSure.setEnabled(false);
            }

        } else {
            layout_teacher_comment.setVisibility(View.GONE);
            tvState.setVisibility(View.VISIBLE);
            btnSure.setVisibility(View.GONE);
        }

        ivState.setImageResource(LogicUtil.getLevelResId(workVo.getWorkStatus(), workVo.getWorkLevel(),workQuestionVo.getDisposeProgress()));
    }

    private void share(){

        AppModel.shareWork(this, workId, new HttpResultListener<BaseResponseVo>() {
            @Override
            public void onSuccess(BaseResponseVo resp) {
                if (resp.isSuccess()){
                    btnSure.setText("已分享");
                    btnSure.setEnabled(false);
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                share();
                break;
            case R.id.iv_contents: {
                if (null == workVo.pictureUrlArr || workVo.pictureUrlArr.size() == 0) {
                    return;
                }
                Intent intent = new Intent(this, TaskPicShowsActivity.class);
                intent.putStringArrayListExtra("selectPics", (ArrayList<String>) workVo.pictureUrlArr);
                intent.putExtra("mode", 1);//表示查看
                intent.putExtra("net", 1);
                startActivity(intent);
            }
                break;
            case R.id.btn_send: {
                Intent intent = new Intent(TaskCommitDetailActivity.this, TaskCommitActivity.class);//提交作业页面
                intent.putExtra("workId", workId);
                startActivity(intent);
            }
                break;
            case R.id.layout_book_info: {
                Intent i = new Intent(this, BookDetailsActivity.class);
                i.putExtra("bookId", workVo.getBookListVo().getId());
                startActivity(i);
            }
            break;


        }
    }

    public void loadWork() {

        AppModel.getWorkById(workId, TAG, new HttpResultListener<WorkGetResponseVo>() {
            @Override
            public void onSuccess(WorkGetResponseVo resp) {
                if (resp.isSuccess()) {
                    manager.showLoadSuccess();
                    workVo = resp.getWork();
                    workQuestionVo = resp.getWorkQuestionVo();
                    initData2Views();

                }else {
                    manager.showLoadException();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                manager.showLoadException();
            }
        });
    }

}
