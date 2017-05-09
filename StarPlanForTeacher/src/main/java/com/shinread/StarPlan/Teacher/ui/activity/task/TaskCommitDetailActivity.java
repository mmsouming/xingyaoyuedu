package com.shinread.StarPlan.Teacher.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkLevelEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkGetResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.DimensionUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.LogicUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;

/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:
 */
public class TaskCommitDetailActivity extends BaseFragmentActivity implements View.OnClickListener {

    private TitleBar mTb ;
    private long workId ;

    private WorkVo workVo ;

    private ImageView ivContents,ivState ,ivBook;
    private TextView tvPicCount,tvBookDes ,tvBook,tvDes,tvComment ,tvTeacherTitle,tvState;
    private Button btnSure ;
    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_commit_detail);

        workId = getIntent().getLongExtra("workId" , -1) ;
        if(workId == -1) {
            ToastUtil.showMsg(getString(R.string.operate_fail));
            return ;
        }

        initViews();

        loadWork();
    }

    boolean hasMeasured = false ;
    private void initViews() {
        mTb = (TitleBar) findViewById(R.id.tb) ;
        mTb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTb.getmTvTitle().setTextSize(DimensionUtil.dip2px(this, 10));
        mTb.invalidate();

        btnSure = (Button) findViewById(R.id.btn_sure);
        ivContents = (ImageView) findViewById(R.id.iv_contents) ;
        ivState = (ImageView) findViewById(R.id.iv_state) ;
        ivBook = (ImageView) findViewById(R.id.iv_book);
        tvPicCount = (TextView) findViewById(R.id.tv_pic_count) ;
        tvBookDes = (TextView) findViewById(R.id.tv_book_des);
        tvBook = (TextView) findViewById(R.id.tv_book) ;
        tvDes = (TextView) findViewById(R.id.tv_des) ;
        tvComment = (TextView) findViewById(R.id.tv_comment) ;
        tvTeacherTitle = (TextView) findViewById(R.id.tv_teacher_title) ;
        tvState = (TextView) findViewById(R.id.tv_state) ;


        btnSure.setOnClickListener(this);
        ivContents.setOnClickListener(this);
    }

    private void initData2Views(){

        String pic = "" ;
        int size = 0 ;
        if(null != workVo.pictureUrlArr && workVo.pictureUrlArr.size() > 0) {
            pic = workVo.pictureUrlArr.get(0) ;
            size = workVo.pictureUrlArr.size() ;
        }
        ImageLoader.getInstance().displayImage(pic ,ivContents);
        tvPicCount.setText(size+"");
        tvBookDes.setText(workVo.getContent());

        ImageLoader.getInstance().displayImage(workVo.getBookListVo().getCoverUrl(),ivBook);
        tvBook.setText(workVo.getBookListVo().getName());
        tvDes.setText(workVo.getBookListVo().getIntroduction());

        if(workVo.getWorkStatus().equals(WorkStatusEnum.SUBMITTED.getNo())){//已提交  点评中
            if(workVo.getWorkLevel().equals(WorkLevelEnum.UNCOMMENT.getNo())){
                btnSure.setVisibility(View.GONE);
                tvTeacherTitle.setVisibility(View.GONE);
                tvState.setVisibility(View.VISIBLE);
                tvComment.setVisibility(View.GONE);
            } else {
                btnSure.setVisibility(View.VISIBLE);
                btnSure.setText(R.string.share_read_feel);
                tvState.setVisibility(View.GONE);
                tvTeacherTitle.setVisibility(View.VISIBLE);
                tvComment.setText(workVo.getComment());
            }
        } else if(workVo.getWorkStatus().equals(WorkStatusEnum.REPULSE.getNo())){
            btnSure.setText(R.string.commit_agin_task);
        }

        ivState.setImageResource(LogicUtil.getLevelResId(workVo.getWorkStatus(), workVo.getWorkLevel()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                if(workVo.getWorkStatus().equals(WorkStatusEnum.REPULSE.getNo())){
                    //重新提交作业
                    Intent intent = new Intent(this, TaskCommitActivity.class) ;
                    intent.putExtra("workId",workVo.getId()) ;
                    startActivity(intent);
                } else if(workVo.getWorkStatus().equals(WorkStatusEnum.SUBMITTED.getNo())) {
                    //已提交作业，分享读书读后感

                }
                break ;
            case R.id.iv_contents:
                if(null == workVo.pictureUrlArr || workVo.pictureUrlArr.size() == 0){
                    return ;
                }
                Intent intent = new Intent(this,TaskPicShowsActivity.class) ;
                intent.putStringArrayListExtra("selectPics", (ArrayList<String>) workVo.pictureUrlArr);
                intent.putExtra("mode", 1) ;//表示查看
                intent.putExtra("net",1) ;
                startActivity(intent);
                break ;
        }
    }

    public void loadWork() {
        showLoaddingDialog();
        AppModel.getWorkById(workId, TAG, new HttpResultListener<WorkGetResponseVo>() {
            @Override
            public void onSuccess(WorkGetResponseVo resp) {
                dismissLoaddingDialog();
                if(resp.isSuccess()) {
                    workVo = resp.getWork() ;
                    initData2Views();
                    return ;
                }
                ToastUtil.showMsg(resp.getMsg());
            }
            @Override
            public void onFailed(Exception e, String msg) {
                dismissLoaddingDialog();
                ToastUtil.showMsg(msg);
            }
        });
    }

}
