package com.shinread.StarPlan.Teacher.ui.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.MsgVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ReadStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListReq;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.shinread.StarPlan.Teacher.bean.StudentListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.StudentListResponseVo;
import com.shinread.StarPlan.Teacher.ui.adapter.Student_ManergerAdapter;
import com.shinread.StarPlan.Teacher.util.CommonUtils;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
/*
*
*
* 学生列表界面 包括姓名头像  是否认证
* */
public class StudentManergerActivity extends BaseFragmentActivity implements View.OnClickListener,Student_ManergerAdapter.OnAdapterHandleListeners {

    long vo;
    private static final int REQ_LOAD_StudentList = BASE_CODE + 1;
    private static final int REQ_REMOVE_DATA= REQ_LOAD_StudentList + 1 ;
    private static final int   REQ_FRESH_DATA= REQ_REMOVE_DATA + 1 ;
    private RelativeLayout title_bar_layout;

    private Button btn_right;
    ListView student_list;
    private Button btn_remove;
    private Button btn_sure;
    LinearLayout btn_manger_layout;
    private boolean isOP;
    Student_ManergerAdapter manergerAdapter;
    Integer certificateStatus;
    private LoadTipManager manager;
    private SwipyRefreshLayout swipyrefreshlayout;
    private NavBarManager ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manerger);
        initRes();
    }
    private void initRes(){
        ma = new NavBarManager(this);
        ma.setRight("管理");

        btn_right = (Button)findViewById(R.id.btn_right);
        title_bar_layout=(RelativeLayout)findViewById(R.id.title_bar_layout);
        student_list=(ListView)findViewById(R.id.lv_msg);
        btn_remove=(Button)findViewById(R.id.btn_remove);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_manger_layout = (LinearLayout) findViewById(R.id.btn_manger_layout);
        btn_right.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        Intent intent=getIntent();
        vo = intent.getLongExtra("id",0);
        Log.i("运行","已经运行了");
        ToastUtil.showMsg(vo+"");
        initViews();
    }
    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case REQ_LOAD_StudentList: //加载执教班级适配器
                manergerAdapter=new Student_ManergerAdapter(this,studentListVos,R.layout.iv_item_student_list,this);
                student_list.setAdapter(manergerAdapter);
                break;
            case  REQ_REMOVE_DATA:
                loadData(false);
                break;
            case  REQ_FRESH_DATA:
                manergerAdapter=new Student_ManergerAdapter(this,studentListVos,R.layout.iv_item_student_list,this);
                student_list.setAdapter(manergerAdapter);
                break;
        }
    }
    List<StudentListVo>studentListVos;
    public void loadData(final boolean isMore){//加载学生列表数据
        AppModel.studentList(vo, new HttpResultListener<StudentListResponseVo>() {
            @Override
            public void onSuccess(StudentListResponseVo resp) {
                manager.showLoadSuccess();
                if (!isMore) {
                    if (resp.getStudentListVoArr().size() == 0) {
                        manager.showLoadException();
                    }
                    studentListVos=resp.getStudentListVoArr();
                    ma.txt_title.setText(resp.getName());
                } else {
                    studentListVos.addAll(resp.getStudentListVoArr());
                }
                sendEmptyUiMessage(REQ_LOAD_StudentList);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case  R.id. btn_right:
                isOP = !isOP;
                if (isOP) {
                    btn_right.setText("取消");
                    btn_manger_layout.setVisibility(View.VISIBLE);
                } else {
                    btn_right.setText("管理");
                    btn_manger_layout.setVisibility(View.GONE);
                }
                manergerAdapter.setOP(isOP);
                manergerAdapter.notifyDataSetChanged();
                break;
            case  R.id. btn_remove:
                manergerAdapter.deletegoods();
                remove_student();
                break;
            case  R.id. btn_sure:
                manergerAdapter.renzheng();
                yanzheng_student();
                manergerAdapter.notifyDataSetChanged();

                break;
        }
    }
    List<String>idArr=new ArrayList<>();
    @Override
    public void select(List<StudentListVo> list, boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChoosed()) {
                idArr.add(list.get(i).getId()+"");
            }
        }
    }
    public void remove_student(){//移除学生
        AppModel.studentanger(this, idArr, 3, new HttpResultListener<StudentListResponseVo>() {
            @Override
            public void onSuccess(StudentListResponseVo resp) {
                if (resp.isSuccess()) {
                    sendEmptyUiMessage(REQ_REMOVE_DATA);
                    return;
                }
                ToastUtil.showMsg(resp.getMsg());
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    public void yanzheng_student(){//验证学生
        AppModel.studentanger(this, idArr,1, new HttpResultListener<StudentListResponseVo>() {
            @Override
            public void onSuccess(StudentListResponseVo resp) {
                if (resp.isSuccess()) {
                    studentListVos=resp.getStudentListVoArr();

                }
                ToastUtil.showMsg(resp.getMsg());
            }
            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }
    private void initViews() {

        manager = new LoadTipManager(this, R.id.swipyrefreshlayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(false);
            }
        });
        manager.setBackgroundResource(R.drawable.radiu_white_bg);


        swipyrefreshlayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        swipyrefreshlayout.setColorSchemeResources(R.color.bule_1);
        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                loadData(direction == SwipyRefreshLayoutDirection.TOP ? false : true);
            }
        });
        loadData(false);
    }
}
