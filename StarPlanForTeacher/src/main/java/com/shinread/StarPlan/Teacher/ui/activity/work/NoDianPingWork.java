package com.shinread.StarPlan.Teacher.ui.activity.work;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BorrowVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BorrowedListResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.NewWorkReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UncommentedWorkNoResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UncommentedWorkResponseVo;
import com.shinread.StarPlan.Teacher.ui.activity.work.adapter.TWorkListAdapter;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;
import java.util.List;
    public class NoDianPingWork extends BaseFragmentActivity implements AdapterView.OnItemClickListener {
        TWorkListAdapter adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_no_dian_ping_work);
            initRes();
    }
        private WorkQuestionListVo source;
        NavBarManager navBarManager;
        private com.handmark.pulltorefresh.library.PullToRefreshScrollView pull_refresh_scrollview;
        private com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureListView list_work;
        private void initRes(){
            navBarManager = new NavBarManager(this);
            navBarManager.txt_title.setText("未点评作业");
            navBarManager.btn_right.setVisibility(View.GONE);
            pull_refresh_scrollview=(com.handmark.pulltorefresh.library.PullToRefreshScrollView)findViewById(R.id.pull_refresh_scrollview);
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
            req.timestamp = timestamp;
            AppModel.uncommentedWork(req, new HttpResultListener<UncommentedWorkResponseVo>() {
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