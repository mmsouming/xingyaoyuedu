package com.shinread.StarPlan.Teacher.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GradeVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseDialog;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.shinread.StarPlan.Teacher.bean.ClassesListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesAllListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesListResponseVo;
import com.shinread.StarPlan.Teacher.ui.adapter.Student_ManergerAdapter;
import com.shinread.StarPlan.Teacher.ui.adapter.Work_SetClassAdapter;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizehong on 2016/5/4.选择执教班级dialog
 */

public class DialogSetClass  implements
        View.OnClickListener,Work_SetClassAdapter.OnAdapterHandleListeners {







    Activity mContext;

    public DialogSetClass(Activity mContext) {
        this.mContext = mContext;
    }

    private BaseDialog mselecttDialog;

    public void  DialogSetClass(Activity context) {
        View view = LayoutInflater.from(FFApplication.instance.getApplicationContext()).inflate(R.layout.dialog_select_class, null);
        mselecttDialog = new BaseDialog(context, view, R.style.customDialog);
        btn_class = (Button) view.findViewById(R.id.btn_class);
        ImageButton btn_class_left = (ImageButton) view.findViewById(R.id.btn_class_left);
        ImageButton btn_class_right = (ImageButton) view.findViewById(R.id.btn_class_right);
        btn_select_sure = (Button) view.findViewById(R.id.btn_select_sure);

        btn_class_left.setOnClickListener(this);
        btn_class_right.setOnClickListener(this);
        btn_select_sure.setOnClickListener(this);
        GridView gv = (GridView) view.findViewById(R.id.gv_class_tags);



        loadallclass();

        Work_SetClassAdapter work_setClassAdapter=new Work_SetClassAdapter(mContext, classesVoArr, R.layout.lv_select_class_item, this);

        gv.setAdapter(work_setClassAdapter);

        setdialog();
        mselecttDialog.setCancelable(true);
        mselecttDialog.show();
    }

    private ImageButton btn_class_left;
    private Button btn_class;
    private ImageButton btn_class_right;
    private GridView gv_class_tags;
    private Button btn_select_sure;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_select_sure://点击确认按钮

                if (classId.size() > 0) {
                    setClassDta();


                }else{
                    ToastUtil.showMsg("您此次没有选额执教班级");
                }
                break;
            case R.id.btn_class_left://点击向左按钮
                if (i == 0) {
                    ToastUtil.showMsg("已经是最低年级");
                } else if (i > 0) {
                    i -= 1;
                    btn_class.setText(gradeVoArr.get(i).getName());
                    classesVoArr.clear();

                    loadallclass();
                }
                break;
            case R.id.btn_class_right://点击向右按钮
                i += 1;
                if(i<gradeVoArr.size()){
                    btn_class.setText(gradeVoArr.get(i).getName());
                    classesVoArr.clear();

                    loadallclass();
                }else{
                    ToastUtil.showMsg("已经是最高年级");
                }

                break;
        }

    }
    List<ClassesListVo> classesListVoArr;

    List<GenericListVo> classesVoArr;
    List<GradeVo> gradeVoArr;
    int i = 0;
    private void loadallclass() {//获取所有班级列表
        AppModel.classesAllList(new HttpResultListener<ClassesAllListResponseVo>() {
            @Override
            public void onSuccess(ClassesAllListResponseVo resp) {
                gradeVoArr = resp.getSchoolVoArr().get(0).getGradeVoArr();
                classesVoArr = resp.getSchoolVoArr().get(0).getGradeVoArr().get(i).getClassesVoArr();
                //idArr=resp.getIdArr();
               // btn_class.setText(gradeVoArr.get(i).getName());
                return;
            }
            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }
    private void setClassDta() {//上传设置班级
        AppModel.postClassesList(mContext, classId, new HttpResultListener<ClassesListResponseVo>() {
            @Override
            public void onSuccess(ClassesListResponseVo resp) {
                if (resp.isSuccess()) {
                    ToastUtil.showMsg("上传成功");
                    return;
                }
                ToastUtil.showMsg(resp.getMsg());
            }
            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }

    List<String> classId = new ArrayList<>();
    @Override
    public void select(List<GenericListVo> list, boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChoosed()) {

                classId.add(list.get(i).getId() + "");
            }
        }
    }
    public void setdialog() {//dialog设置
        Window window = mselecttDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        window.setGravity(Gravity.CENTER);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lp.width = (int) (width*0.85);
        lp.height = (int) (height*0.51);
    }
}
