package com.shinread.StarPlan.Teacher.ui.activity.work;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GradeVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseDialog;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.utils.L;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureGridView;
import com.shinread.StarPlan.Teacher.bean.ClassesListVo;
import com.shinread.StarPlan.Teacher.bean.SchoolListVo;
import com.shinread.StarPlan.Teacher.bean.SchoolVo;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesAllListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesListResponseVo;
import com.shinread.StarPlan.Teacher.ui.activity.work.adapter.ClassAdapter;
import com.shinread.StarPlan.Teacher.ui.adapter.Student_ManergerAdapter;
import com.shinread.StarPlan.Teacher.ui.adapter.Work_SetClassAdapter;
import com.shinyread.StarPlan.Teacher.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 班级管理--获取执教班级
 */
public class ClassManagerActivity extends BaseFragmentActivity implements View.OnClickListener,Work_SetClassAdapter.OnAdapterHandleListeners, AdapterView.OnItemClickListener {
    private static final int REQ_LOAD_ZhiJiao = BASE_CODE + 1;
    private static final int REQ_SHOW_Banji = REQ_LOAD_ZhiJiao + 1;
    private static final int REQ_LOAD_SetBanJi = REQ_SHOW_Banji + 1;
    private static final int REQ_LOAD_RefreshBanJi = REQ_LOAD_SetBanJi + 1;
    List<SchoolVo> schoolVos = null;
    private BaseDialog mselecttDialog;//班级挑选对话框
    List<ClassesListVo> classesListVoArr;

    List<GenericListVo> classesVoArr;
    List<GradeVo> gradeVoArr;
    private List<String> mSelectTags = null; //班级id的集合
    @Override
    protected String setTag() {
        return ClassManagerActivity.class.getSimpleName();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);
        initRes();
    }
    private ListView pulltolistview;
    private LinearLayout btn_setclass;
    Button btn_select_sure;
    private ClassAdapter adapter;
    private void initRes() {
        NavBarManager manager = new NavBarManager(this);
        manager.txt_title.setText("班级管理");
        pulltolistview = (ListView) findViewById(R.id.pulltolistviews);
        btn_setclass = (LinearLayout) findViewById(R.id.btn_setclass);
        btn_setclass = (LinearLayout) findViewById(R.id.btn_setclass);
        btn_setclass.setOnClickListener(this);
        Intent intent=getIntent();
        if(null!=intent.getStringExtra("shezhibanji")){
            loadallclass();
        }else{
            loadData();
        }
        pulltolistview.setOnItemClickListener(this);
    }
    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case REQ_LOAD_ZhiJiao: //加载执教班级
               dispalayclass();
                break;
            case REQ_SHOW_Banji: //获取班级列表
                showSelectclass();
                break;
            case REQ_LOAD_SetBanJi://设置班级
                setClassDta();

                break;
            case   REQ_LOAD_RefreshBanJi://刷新班级界面
                classesListVoArr.clear();
                loadData();

                break;
        }
    }
    private void loadData() {//获取执教班级列表
        showLoaddingDialog();
        AppModel.getClassesList(new HttpResultListener<ClassesListResponseVo>() {
            @Override
            public void onSuccess(ClassesListResponseVo resp) {
                classesListVoArr = resp.getSchoolListVoArr().get(0).getClassesListVoArr();

                sendEmptyUiMessage(REQ_LOAD_ZhiJiao);
                dismissLoaddingDialog();
            }
            @Override
            public void onFailed(Exception e, String msg) {
                dismissLoaddingDialog();
            }
        });
    }
    int i = 0;
    private void loadallclass() {//获取所有班级列表
        AppModel.classesAllList(new HttpResultListener<ClassesAllListResponseVo>() {
            @Override
            public void onSuccess(ClassesAllListResponseVo resp) {
                gradeVoArr = resp.getSchoolVoArr().get(0).getGradeVoArr();
                classesVoArr = resp.getSchoolVoArr().get(0).getGradeVoArr().get(i).getClassesVoArr();
                //idArr=resp.getIdArr();
                sendEmptyUiMessage(REQ_SHOW_Banji);
                return;
            }
            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }
    private void setClassDta() {//上传设置班级
        AppModel.postClassesList(this, classId, new HttpResultListener<ClassesListResponseVo>() {
            @Override
            public void onSuccess(ClassesListResponseVo resp) {
                if (resp.isSuccess()) {

                    classesListVoArr = resp.getSchoolListVoArr().get(0).getClassesListVoArr();
                    sendEmptyUiMessage(REQ_LOAD_RefreshBanJi);
                    return;
                }
                ToastUtil.showMsg(resp.getMsg());

            }
            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }
    public void dispalayclass() {//展示执教班级列表
        pulltolistview.setAdapter(new CommonAdapter<ClassesListVo>(FFApplication.instance.getApplicationContext(), classesListVoArr, R.layout.lv_item_class) {
            @Override
            protected void convert(ViewHolder vh, ClassesListVo item) {
                ((TextView) vh.getView(R.id.tv_class_name)).setText(item.getName());
                ((TextView) vh.getView(R.id.tv_class_people)).setText(item.getStudentNo() + "人");
                ((TextView) vh.getView(R.id.tv_identification)).setText("已认证");
                ((TextView) vh.getView(R.id.tv_identification_nub)).setText( item.getAuthNo() + "人");
            }
        });
    }
    Button btn_class;
    public void showSelectclass() {//展示所有班级列表
        View view = LayoutInflater.from(FFApplication.instance.getApplicationContext()).inflate(R.layout.dialog_select_class, null);
        mselecttDialog = new BaseDialog(this, view, R.style.customDialog);
        btn_class = (Button) view.findViewById(R.id.btn_class);
        ImageButton btn_class_left = (ImageButton) view.findViewById(R.id.btn_class_left);
        ImageButton btn_class_right = (ImageButton) view.findViewById(R.id.btn_class_right);
        btn_select_sure = (Button) view.findViewById(R.id.btn_select_sure);
        btn_class.setText(gradeVoArr.get(i).getName());
        btn_class_left.setOnClickListener(this);
        btn_class_right.setOnClickListener(this);
        btn_select_sure.setOnClickListener(this);
        GridView gv = (GridView) view.findViewById(R.id.gv_class_tags);
        Work_SetClassAdapter work_setClassAdapter=new Work_SetClassAdapter(this, classesVoArr, R.layout.lv_select_class_item, ClassManagerActivity.this);
        gv.setAdapter(work_setClassAdapter);
        setdialog();
        mselecttDialog.setCancelable(true);
        mselecttDialog.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_setclass:
                loadallclass();

                break;
            case R.id.btn_select_sure://点击确认按钮

                if (classId.size() > 0) {
                    sendEmptyUiMessage(REQ_LOAD_SetBanJi);

                    mselecttDialog.dismiss();
                }else{
                    ToastUtil.showMsg("您此次没有选择执教班级");
                }
                break;
            case R.id.btn_class_left://点击向左按钮
                if (i == 0) {
                    ToastUtil.showMsg("已经是最低年级");
                } else if (i > 0) {
                    i -= 1;
                    btn_class.setText(gradeVoArr.get(i).getName());
                    classesVoArr.clear();
                    mselecttDialog.dismiss();
                    loadallclass();
                }
                break;
            case R.id.btn_class_right://点击向右按钮
                i += 1;
                if(i<gradeVoArr.size()){
                    btn_class.setText(gradeVoArr.get(i).getName());
                    classesVoArr.clear();
                    mselecttDialog.dismiss();
                    loadallclass();
                }else{
                    ToastUtil.showMsg("已经是最高年级");
                }
                break;
        }

    }

    public void setdialog() {//dialog设置
        Window window = mselecttDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        window.setGravity(Gravity.CENTER);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lp.width = (int) (width*0.85);
        lp.height = (int) (height*0.51);
    }
    List<String> classId = new ArrayList<>();
    @Override
    public void select(List<GenericListVo> list, boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChoosed()) {
                ToastUtil.showMsg(list.get(i).getName());
                classId.add(list.get(i).getId() + "");
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Long vo = classesListVoArr.get(position).getId();
        Intent intent = new Intent(getApplicationContext(),StudentManergerActivity.class);
        intent.putExtra("id",vo);
        startActivity(intent);
    }
}
