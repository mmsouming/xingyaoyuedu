package com.shinread.StarPlan.Parent.ui.activity.test;

import android.os.Bundle;
import android.view.View;

import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.shinyread.StarPlan.Parent.R;

/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:
 */
public class WorkTestActivity extends BaseFragmentActivity implements View.OnClickListener {


    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_worktest);

        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button4:
//                AppModel.unFinishWorkList(CommonUtils.getDefaultStudentVo().getId(),TAG ,new HttpResultListener<UnfinishWorkResponseVo>(){
//
//                    @Override
//                    public void onSuccess(UnfinishWorkResponseVo resp) {
//
//                    }
//
//                    @Override
//                    public void onFailed(Exception e, String msg) {
//
//                    }
//                }) ;
//                break ;
//            case R.id.button5:
//                AppModel.workList(CommonUtils.getDefaultStudentVo().getId(), 0, null, null, TAG, new HttpResultListener<WorkListResponseVo>() {
//                    @Override
//                    public void onSuccess(WorkListResponseVo resp) {
//
//                    }
//
//                    @Override
//                    public void onFailed(Exception e, String msg) {
//
//                    }
//                }) ;
//                break ;
//            case R.id.button6:
//                long id  = 0 ;
//                AppModel.getWorkById(id, TAG, new HttpResultListener<WorkGetResponseVo>() {
//                    @Override
//                    public void onSuccess(WorkGetResponseVo resp) {
//
//                    }
//
//                    @Override
//                    public void onFailed(Exception e, String msg) {
//
//                    }
//                }) ;
//                break ;
//            case R.id.button7:
//
//                break ;
//            case R.id.button8:
//
//                break ;
//        }
    }
}
