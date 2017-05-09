package com.shinread.StarPlan.Teacher.ui.activity.work;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.MsgVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DisposeProgressEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ReadStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogDatePicker;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionResponseVo;
import com.shinread.StarPlan.Teacher.ui.adapter.Student_ManergerAdapter;
import com.shinread.StarPlan.Teacher.ui.adapter.WorkMangerAdapter;
import com.shinyread.StarPlan.Teacher.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkMangerActivity extends BaseFragmentActivity implements WorkMangerAdapter.OnAdapterHandleListeners {

    List<WorkQuestionListVo>datas=new ArrayList<>();
    private ListView mLvMsgs;



    private WorkMangerAdapter workMangerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_work_manerger);

        initViews();
    }

    SwipyRefreshLayout swipyrefreshlayout;
    LoadTipManager manager;

    private void initViews() {
        NavBarManager navBarManager=new NavBarManager(this);
        navBarManager.txt_title.setText("作业管理");
        manager = new LoadTipManager(this, R.id.swipyrefreshlayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(false);
            }
        });
        manager.setBackgroundResource(R.drawable.radiu_white_bg);
        mLvMsgs = (ListView) findViewById(R.id.lv_msg);

        workMangerAdapter = new WorkMangerAdapter(this,WorkMangerActivity.this);
        swipyrefreshlayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        swipyrefreshlayout.setColorSchemeResources(R.color.bule_1);
        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                loadData(direction == SwipyRefreshLayoutDirection.TOP ? false : true);
            }
        });
        mLvMsgs.setAdapter(workMangerAdapter);


        loadData(false);

    }
    BasePageReq req = new BasePageReq();
    int pageNo = 0;
    private void loadData(final boolean isMore) {//加载布置作业列表

        if (!isMore) {
            pageNo = 0;
        }
        req.pageNo = pageNo;
     AppModel.workQuestionList(req, new HttpResultListener<WorkQuestionListResponseVo>() {
         @Override
         public void onSuccess(WorkQuestionListResponseVo resp) {
             if (resp.isSuccess()) {
                // CacheDataUtil.clearNotice();
                 manager.showLoadSuccess();

                 if (!isMore) {
                     if (resp.getWorkQuestionListVoArr().size() == 0) {
                         manager.showLoadException();
                     }
                     datas = resp.getWorkQuestionListVoArr();
                 } else {
                     datas.addAll(resp.getWorkQuestionListVoArr());
                 }
                 workMangerAdapter.setObjects(datas);
             }
         }
         @Override
         public void onFailed(Exception e, String msg) {
             if (!isMore) {
                 manager.showLoadException();
             }
             swipyrefreshlayout.setRefreshing(false);
         }
     });
    }
    public void cancel(){//取消成功
        AppModel.invalid(this, cancelid, new HttpResultListener<WorkQuestionResponseVo>() {
            @Override
            public void onSuccess(WorkQuestionResponseVo resp) {
                loadData(false);
            }
            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }
    public void delay(){//延迟处理
        DialogDatePicker dialogDatePicker=new DialogDatePicker(this);
        dialogDatePicker.show();
        dialogDatePicker.setListenser(new DialogDatePicker.OnDialogWheelPicker() {
            @Override
            public void ChooseResult(String date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    Timestamp tt=null;
                    tt = new Timestamp(format.parse(date).getTime());
                    AppModel.workQuestionTime(WorkMangerActivity.this, yanchiid, tt.getTime() / 1000, new HttpResultListener<WorkQuestionResponseVo>() {
                        @Override
                        public void onSuccess(WorkQuestionResponseVo resp) {

                        }
                        @Override
                        public void onFailed(Exception e, String msg) {
                        }
                    });
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


    }
    Long yanchiid;
    Long cancelid;



    @Override
    public void yanchi(List<WorkQuestionListVo> list, int postition) {
        yanchiid=list.get(postition).getId();
        delay();
    }

    @Override
    public void cancel(List<WorkQuestionListVo> list, int postition) {
        cancelid=list.get(postition).getId();
             cancel();
    }
}
