package com.shinread.StarPlan.Teacher.ui.activity.work.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.shinread.StarPlan.Teacher.bean.ClassesListVo;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UncommentedWorkNoResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionListResponseVo;
import com.shinread.StarPlan.Teacher.ui.activity.work.ClassManagerActivity;
import com.shinread.StarPlan.Teacher.ui.activity.work.NoDianPingWork;
import com.shinread.StarPlan.Teacher.ui.activity.work.SetWorkActivit;
import com.shinread.StarPlan.Teacher.ui.activity.work.WorkListActivity;
import com.shinread.StarPlan.Teacher.ui.activity.work.YueDuActivity;
import com.shinread.StarPlan.Teacher.ui.activity.work.adapter.WorkHomeAdapter;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;
import java.util.List;


public class WorkHomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private final int REQUESTCODE = 1;// 返回的结果码
    List<ClassesListVo> classesListVoArr=null;
    private RelativeLayout relayout_banji;
    private Button btn_banji;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work_home, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    public void initView() {
        initRes();
        initEvent();
//        refreshData(getActivity(), false);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_work://布置作业
            {
                Intent intent = new Intent(getActivity(), SetWorkActivit.class);
                startActivityForResult(intent, REQUESTCODE);// 表示可以返回结果
            }
               break;
            case R.id.btn_undo_work://未评论作业
            {
                Intent intent = new Intent(getActivity(),NoDianPingWork.class);
                startActivity(intent);
            }
                break;
            case R.id.btn_manager://班级管理
            {
                Intent intent = new Intent(getActivity(),ClassManagerActivity.class);
                intent.putExtra("banjiguanli","banjiguanli");
                startActivity(intent);
            }
                break;
            case R.id.btn_banji://设置班级
            {
                Intent intent = new Intent(getActivity(), ClassManagerActivity.class);
                intent.putExtra("shezhibanji","shezhibanji");
                startActivityForResult(intent, REQUESTCODE);// 表示可以返回结果
                startActivity(intent);
            }
            default:
                break;
        }
    }
    private final int REQUESTCODEs = 1;// 返回的结果码
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
                loadzhijiao();
            }
        }
    }
    private TextView btn_manager;
    private TextView btn_work;
    private Button btn_undo_work;
    private com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureListView list_work;

    private WorkHomeAdapter adapter;
    private PullToRefreshScrollView pull_refresh_scrollview;
    private void initRes(){

        relayout_banji = (RelativeLayout)getView().findViewById(R.id.relayout_banji);
        btn_banji = (Button)getView().findViewById(R.id.btn_banji);
        btn_manager=(TextView)getView().findViewById(R.id.btn_manager);
        btn_work=(TextView)getView().findViewById(R.id.btn_work);
        btn_undo_work=(Button)getView().findViewById(R.id.btn_undo_work);
        list_work=(com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureListView)getView().findViewById(R.id.list_work);

        adapter = new WorkHomeAdapter(getActivity());
        list_work.setAdapter(adapter);

        pull_refresh_scrollview = (PullToRefreshScrollView) getView().findViewById(
                R.id.pull_refresh_scrollview);

        loadzhijiao();
        pull_refresh_scrollview.setMode(
                PullToRefreshBase.Mode.BOTH);

        pull_refresh_scrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                // TODO Auto-generated method stub
                loadData(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                // TODO Auto-generated method stub
                loadData(true);
            }

        });
        loadData(false);
    }

    private void initEvent(){

        list_work.setOnItemClickListener(this);
        btn_manager.setOnClickListener(this);
        btn_work.setOnClickListener(this);
        btn_undo_work.setOnClickListener(this);
        btn_banji.setOnClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        WorkQuestionListVo vo = objects.get(i);
        Intent intent = new Intent(getActivity(),WorkListActivity.class);
        intent.putExtra("WorkQuestionListVo",vo);
        startActivity(intent);
    }


    List<WorkQuestionListVo> objects;
    int page;
    private void loadData(final boolean isMore){

        BasePageReq req = new BasePageReq();
        if (!isMore){
            page = 0;
        }
        req.pageNo = page;
        AppModel.unfinishedQuestionList(req, new HttpResultListener<WorkQuestionListResponseVo>() {
            @Override
            public void onSuccess(WorkQuestionListResponseVo resp) {
                pull_refresh_scrollview.onRefreshComplete();
                if (resp.isSuccess()){
                    page ++;
                    if (!isMore){
                        objects = resp.getWorkQuestionListVoArr();
                    }else{
                        objects.addAll(resp.getWorkQuestionListVoArr());
                    }
                    adapter.setObjects(objects);
                }
            }
            @Override
            public void onFailed(Exception e, String msg) {
                pull_refresh_scrollview.onRefreshComplete();
            }
        });
        AppModel.uncommentedWorkNo("", new HttpResultListener<UncommentedWorkNoResponseVo>() {
            @Override
            public void onSuccess(UncommentedWorkNoResponseVo resp) {
                Integer workNo=resp.getworkNo();
                btn_undo_work.setText(workNo+"篇未点评作业");
            }
            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    private void loadzhijiao() {//获取执教班级列表
        showLoaddingDialog();
        AppModel.getClassesList(new HttpResultListener<ClassesListResponseVo>() {
            @Override
            public void onSuccess(ClassesListResponseVo resp) {
                classesListVoArr = resp.getSchoolListVoArr().get(0).getClassesListVoArr();
                dismissLoaddingDialog();
                if(classesListVoArr.size()==0){//判断是执教班级是否为空
            pull_refresh_scrollview.setVisibility(View.GONE);
            relayout_banji.setVisibility(View.VISIBLE);
        }else{
            pull_refresh_scrollview.setVisibility(View.VISIBLE);
            relayout_banji.setVisibility(View.GONE);
        }
            }
            @Override
            public void onFailed(Exception e, String msg) {
                dismissLoaddingDialog();
            }
        });
    }

}
