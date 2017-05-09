package com.shinread.StarPlan.Teacher.ui.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UncommentedWorkResponseVo;
import com.shinread.StarPlan.Teacher.ui.activity.work.adapter.TWorkListAdapter;
import com.shinyread.StarPlan.Teacher.R;

import java.util.List;

public class WorkListActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener {

    TWorkListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);
        initRes();

    }

    private WorkQuestionListVo source;
    NavBarManager navBarManager;
    private com.handmark.pulltorefresh.library.PullToRefreshScrollView pull_refresh_scrollview;

    private com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureListView list_work;
    private void initRes(){
        navBarManager = new NavBarManager(this);
        navBarManager.txt_title.setText("详情");
          navBarManager.btn_right.setVisibility(View.GONE);
        source = (WorkQuestionListVo) getIntent().getSerializableExtra("WorkQuestionListVo");
        if (source == null){
            return;
        }



        pull_refresh_scrollview=(com.handmark.pulltorefresh.library.PullToRefreshScrollView)findViewById(R.id.pull_refresh_scrollview);
        img_head=(ImageView)findViewById(R.id.img_head);
        txt_class_name=(TextView)findViewById(R.id.txt_class_name);
        txt_submit_no=(TextView)findViewById(R.id.txt_submit_no);
        btn_un_submit=(Button)findViewById(R.id.btn_un_submit);
        list_work=(com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureListView)findViewById(R.id.list_work);
        adapter = new TWorkListAdapter(this);
        list_work.setAdapter(adapter);
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

        initHead(source);
    }

    private void initHead(WorkQuestionListVo source) {

        if (source == null){
            return;
        }
        txt_class_name.setText(source.getClassesName());
        txt_submit_no.setText("已提交"+source.getSubmitNo()+"人");
        btn_un_submit.setText("未提交"+(source.getDistributionNo() -source.getSubmitNo()) +"人");
    }
    private ImageView img_head;
    private TextView txt_class_name;
    private TextView txt_submit_no;
    private Button btn_un_submit;







    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    List<WorkVo> objects;
    Long timestamp;
    private void loadData(final boolean isMore){
        BasePageReq req = new BasePageReq();
        if (!isMore){
            timestamp = Long.valueOf(0);
        }
        req.id = source.getId();
        req.timestamp = timestamp;
        AppModel.workList(req, new HttpResultListener<UncommentedWorkResponseVo>() {
            @Override
            public void onSuccess(UncommentedWorkResponseVo resp) {
                pull_refresh_scrollview.onRefreshComplete();
                if (resp.isSuccess()){
                    if (resp.getWorkVoArr().size() > 0) {
                        timestamp = resp.getWorkVoArr().get(resp.getWorkVoArr().size() - 1).getTimestamp();
                    }
                    if (!isMore){
                        objects = resp.getWorkVoArr();
                    }else{
                        objects.addAll(resp.getWorkVoArr());
                    }
                    adapter.setObjects(objects);
                }
            }
            @Override
            public void onFailed(Exception e, String msg) {
                pull_refresh_scrollview.onRefreshComplete();
            }
        });
    }

}
