package com.shinread.StarPlan.Parent.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoadUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.fancyfamily.primarylibrary.commentlibrary.util.LogicUtil;
import com.shinyread.StarPlan.Parent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author:janecer on 2016/4/10 09:37
 * email:janecer@sina.cn
 */

public class TaskStudyRecodersActivity extends BaseFragmentActivity implements OnItemClickListener {


    //private List<WorkListVo> workListVoArr;

    private TitleBar mTb;
    private ListView lvTasks;

    private long stuId;
    private int pageNo;
    private Integer rowSize;

    private CommonAdapter<WorkListVo> taskAdapter;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_study_records);

        //stuId = CommonUtils.getDefaultStudentVo().getId() ;

        initViews();
    }

    private void initViews() {
        mTb = (TitleBar) findViewById(R.id.tb);
        mTb.setOnLeftNavClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvTasks = (ListView) findViewById(R.id.lv_pull);

        lvTasks.setDividerHeight(0);
        taskAdapter = new CommonAdapter<WorkListVo>(this, null, R.layout.lv_item_task_study_record) {
            @Override
            protected void convert(ViewHolder vh, WorkListVo item) {
                ((TextView) vh.getView(R.id.tv_task_title)).setText(item.getName());
                ((TextView) vh.getView(R.id.tv_task_time)).setText(item.getEffectiveTime() + "布置");
                ((ImageView) vh.getView(R.id.iv_task_state)).setImageResource(LogicUtil.getLevelResIdw(item.getWorkStatus(), item.getWorkLevel(), item.getDisposeProgress()));
            }
        };

        lvTasks.setAdapter(taskAdapter);
        lvTasks.setOnItemClickListener(this);


        loadUtil = new LoadUtil(this, new LoadUtil.onLoadListener() {
            @Override
            public void OnLoadException() {

                loadData(false);
            }

            @Override
            public void OnRefresh(LoadUtil.LoadUtilRefreshLayoutDirection direction) {
                loadData(direction == LoadUtil.LoadUtilRefreshLayoutDirection.TOP ? false : true);
            }
        });

        req.id = UserInfoManager.getInstance().getDefaultStudent().getId();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(false);
    }

    LoadUtil loadUtil;

    BasePageReq req = new BasePageReq();
    Long timestamp = Long.valueOf(0);

    private List<WorkListVo> datas = new ArrayList<>();

    private void loadData(final boolean isMore) {

        if (!isMore) {
            timestamp = Long.valueOf(0);
        }
        req.timestamp = timestamp;
        AppModel.workList(req, new HttpResultListener<WorkListResponseVo>() {
            @Override
            public void onSuccess(WorkListResponseVo resp) {

                if (resp.isSuccess()) {
                    List<WorkListVo> temp = resp.getWorkListVoArr();
                    if (temp.size() > 0) {
                        timestamp = temp.get(temp.size() - 1).getTimestamp();
                    }
                    if (!isMore) {
                        datas = temp;
                    } else {
                        datas.addAll(temp);
                    }
                    taskAdapter.setDataChange(datas);
                }

                if (datas != null && datas.size() > 0) {
                    loadUtil.showLoadSuccess();
                } else {
                    loadUtil.showLoadException();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                loadUtil.showLoadException();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        WorkListVo wv = datas.get(position);
        Logger.msg("WorkStatusEnum",wv.getWorkStatus()+"  "+wv.getDisposeProgress());
        long tagId = wv.getId();
        if (wv.getWorkStatus() == WorkStatusEnum.UNCOMMITTED.getNo()
                || wv.getWorkStatus() == WorkStatusEnum.REPULSE.getNo()) {
            Intent intent = new Intent(this, TaskCommitActivity.class);//提交作业页面
            intent.putExtra("workId", tagId);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, TaskCommitDetailActivity.class);//作业详细页面
            intent.putExtra("workId", tagId);
            startActivity(intent);
        }
    }


}
