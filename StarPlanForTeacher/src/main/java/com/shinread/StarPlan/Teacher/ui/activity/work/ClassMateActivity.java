package com.shinread.StarPlan.Teacher.ui.activity.work;

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
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UncommentedWorkResponseVo;
import com.shinread.StarPlan.Teacher.ui.activity.work.adapter.TWorkListAdapter;
import com.shinyread.StarPlan.Teacher.R;

import java.util.List;

public class ClassMateActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    TWorkListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_list);
        initRes();
        initEvent();
    }


    NavBarManager navBarManager;
    private PullToRefreshListView pull_refresh_listview;

    private void initRes(){
        navBarManager = new NavBarManager(this);
        //navBarManager.setRight("详情");
        pull_refresh_listview=(PullToRefreshListView)findViewById(R.id.pull_refresh_listview);
        adapter = new TWorkListAdapter(this);
        pull_refresh_listview.getRefreshableView().setAdapter(adapter);
       // pull_refresh_listview.getli.setAdapter(adapter);
        pull_refresh_listview.setMode(
                PullToRefreshBase.Mode.BOTH);

        pull_refresh_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

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
       // loadData(false);


    }



    private void initEvent(){


        navBarManager.btn_right.setOnClickListener(this);
    }



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
       // req.id = source.getId();
        req.timestamp = timestamp;
        AppModel.workList(req, new HttpResultListener<UncommentedWorkResponseVo>() {
            @Override
            public void onSuccess(UncommentedWorkResponseVo resp) {
                pull_refresh_listview.onRefreshComplete();
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
                pull_refresh_listview.onRefreshComplete();
            }
        });
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.btn_un_submit){


        }else if (id == R.id.btn_right){


        }
    }
}
