package com.shinread.StarPlan.Teacher.ui.activity.work;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseDialog;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;


import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogDatePicker;
import com.shinread.StarPlan.Teacher.bean.ClassesListVo;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionResponseVo;
import com.shinread.StarPlan.Teacher.lib.datetime.datetimepicker.date.DatePickerDialog;
import com.shinread.StarPlan.Teacher.ui.activity.work.adapter.ChoseBookAdapter;
import com.shinread.StarPlan.Teacher.ui.adapter.Work_ClassnameAdapter;
import com.shinread.StarPlan.Teacher.ui.widget.DatePicker;
import com.shinread.StarPlan.Teacher.ui.widget.MyChoseBook;
import com.shinyread.StarPlan.Teacher.R;

import java.security.acl.Group;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
*
*
* 布置作业界面
* */
public class SetWorkActivit extends BaseFragmentActivity implements View.OnClickListener, Work_ClassnameAdapter.OnAdapterHandleListeners {
    private static final int REQ_LOAD_ZhiJiao = BASE_CODE + 1;
    private static final int REQ_RETURN_Work_Class = REQ_LOAD_ZhiJiao + 1;

    private EditText work_teme;
    private TextView publish_time;
    private TextView finish_time;
    private EditText work_request;
    private TextView tv_class_name;
    private TextView book_tv;
    private GridView work_sort;
    private Button btn_tijiao;
    private ImageView btn_back;
    private TextView txt_title;
    private Button btn_right;
    BaseDialog classDialog;
    DatePicker datePicker;
    private RelativeLayout photo_relatout;
    private Work_ClassnameAdapter work_classnameAdapter;
    private ImageView img_add;
    private LinearLayout groupView;

    private final int REQUESTCODE = 1;// 返回的结果码
    private ChoseBookAdapter choseadapter;
    private View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_work);
        Bitmap bimap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused);
        parentView = getLayoutInflater().inflate(R.layout.activity_set_work, null);
        setContentView(parentView);
        Initview();
        loadData();
    }

    public void Initview() {
        datePicker = new DatePicker(this);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("作业");
        btn_right = (Button) findViewById(R.id.btn_right);
        work_teme = (EditText) findViewById(R.id.work_teme);
        publish_time = (TextView) findViewById(R.id.publish_time);
        finish_time = (TextView) findViewById(R.id.finish_time);
        work_request = (EditText) findViewById(R.id.work_request);
        tv_class_name = (TextView) findViewById(R.id.tv_class_name);
        book_tv = (TextView) findViewById(R.id.book_tv);
        btn_tijiao = (Button) findViewById(R.id.btn_tijiao);
        work_sort = (GridView) findViewById(R.id.work_sort);
        btn_tijiao = (Button) findViewById(R.id.btn_tijiao);
        photo_relatout = (RelativeLayout) findViewById(R.id.photo_relatout);
        img_add = (ImageView) findViewById(R.id.img_add);
        img_add.setVisibility(View.GONE);
        btn_back.setOnClickListener(this);//点击返回按钮
        btn_right.setOnClickListener(this);//点击取消按钮
        tv_class_name.setOnClickListener(this);//点击选择班级名字
        publish_time.setOnClickListener(this);//点击发布作业时间
        finish_time.setOnClickListener(this);//点击完成时间
        btn_tijiao.setOnClickListener(this);//点击提交
        photo_relatout.setOnClickListener(this);
        choseadapter = new ChoseBookAdapter(this);
        choseadapter.update();
        work_sort.setAdapter(choseadapter);
        work_sort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == MyChoseBook.mSelectedbookimg.size()) {
                    Intent intent = new Intent(getApplicationContext(), YueDuActivity.class);
                    startActivityForResult(intent, REQUESTCODE);// 表示可以返回结果
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
                work_sort.setAdapter(choseadapter);
            }
        }
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case REQ_LOAD_ZhiJiao: //加载执教班级
                dispalydialog();
                break;
            case REQ_RETURN_Work_Class: //加载执教班级
                break;
        }
    }

    List<ClassesListVo> classesListVoArr;
    List<String> class_name_string = new ArrayList<>();

    private void loadData() {//获取执教班级列表
        AppModel.getClassesList(new HttpResultListener<ClassesListResponseVo>() {
            @Override
            public void onSuccess(ClassesListResponseVo resp) {
                classesListVoArr = resp.getSchoolListVoArr().get(0).getClassesListVoArr();
                // sendEmptyUiMessage(REQ_LOAD_ZhiJiao);
            }
            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }
    public void dispalydialog() {
        View view = LayoutInflater.from(FFApplication.instance.getApplicationContext()).inflate(R.layout.dialog_class_name, null);
        classDialog = new BaseDialog(this, view, R.style.customDialog);
        GridView gv = (GridView) view.findViewById(R.id.gv_work_tags_tags);
        Button btn_work_select_sure = (Button) view.findViewById(R.id.btn_work_select_sure);
        btn_work_select_sure.setOnClickListener(this);
        work_classnameAdapter = new Work_ClassnameAdapter(this, classesListVoArr, R.layout.lv_select_class_name_item, SetWorkActivit.this);
        gv.setAdapter(work_classnameAdapter);
        setdialog();
        classDialog.setCancelable(true);
        classDialog.show();
    }
    public void addLayout() {
        groupView = (LinearLayout) parentView.findViewById(R.id.pers_photo_layout);
        if (class_name_string.size() > 0) {
            tv_class_name.setVisibility(View.GONE);
            TextView tx;
            groupView.removeAllViews();
            for (int i = 0; i < class_name_string.size(); i++) {
                // 每行都有一个linearlayout
                tx = new TextView(this);
                LinearLayout lLayout = new LinearLayout(getApplicationContext());
                lLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams lLayoutlayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lLayout.setLayoutParams(lLayoutlayoutParams);
                tx.setLayoutParams(lLayoutlayoutParams); // image的布局方式
                tx.setPadding(10, 2, 4, 2);
                String txtname = class_name_string.get(i);
                tx.setText(txtname);
                groupView.addView(tx);
            }
            img_add.setVisibility(View.VISIBLE);
            class_name_string.clear();
        } else if (class_name_string.size() == 0) {
            img_add.setVisibility(View.GONE);
            tv_class_name.setVisibility(View.VISIBLE);
            groupView.removeAllViews();
        }
    }
    public void setdialog() {//dialog设置
        Window window = classDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        window.setGravity(Gravity.CENTER);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lp.width = (int) (width * 0.858);
        lp.height = (int) (height * 0.418);
    }

    String name;
    String content;
    Long effectiveTimestamp;
    Long invalidTimestamp;
    String publish;
    String finish;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tijiao:
                name = work_teme.getText().toString();
                content = work_request.getText().toString();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showMsg("请输入主题");
                    return ;
                }
                if(TextUtils.isEmpty(content)){
                    ToastUtil.showMsg("请输入作业要求");
                    return ;
                }
                buzhiwork();


                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_right:
                finish();
                break;
            case R.id.btn_work_select_sure:
                classDialog.dismiss();
                work_classnameAdapter.notifyDataSetChanged();
                addLayout();
                class_name_string.clear();
                break;
            case R.id.publish_time:
                datePicker.show();
                datePicker.setListenser(new DatePicker.OnDialogWheelPicker() {
                    @Override
                    public void ChooseResult(String date) {
                        publish_time.setText(date);
                        publish = date;

                    }

                });
                break;
            case R.id.finish_time:
                datePicker.show();
                datePicker.setListenser(new DatePicker.OnDialogWheelPicker() {
                    @Override
                    public void ChooseResult(String date) {
                        finish_time.setText(date);
                        finish = date;
                    }


                });
                break;
            case R.id.photo_relatout:

                dispalydialog();
                break;
        }
    }
    /*
    *
    * 回调函数选择chook之后
    * */
    List<Long> idArr = new ArrayList<>();
    @Override
    public void select(List<ClassesListVo> list, boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChoosed()&&flag==true) {
                ToastUtil.showMsg(list.get(i).getName());
                class_name_string.add(list.get(i).getName());
                idArr.add(list.get(i).getId());
            }
        }
    }
    public void buzhiwork() {

        Timestamp tt=null;
        Timestamp tts=null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        try {
           tt = new Timestamp(format.parse(publish).getTime());
            tts = new Timestamp(format.parse(finish).getTime());
            System.out.println(tt.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AppModel.workQuestion(this, name, content, tt.getTime()/1000,   tt.getTime()/1000 , idArr, MyChoseBook.mSelectedbook, new HttpResultListener<WorkQuestionResponseVo>() {
            @Override
            public void onSuccess(WorkQuestionResponseVo resp) {
                if (resp.isSuccess()) {
                    ToastUtil.showMsg("布置作业成功");
                              Intent intents = new Intent();
             // 通过Intent对象返回结果，调用setResult方法
               setResult(2, intents);
              finish();

                } else {
                    ToastUtil.showMsg(resp.getMsg());
                }
            }
            @Override
            public void onFailed(Exception e, String msg) {
                //  ToastUtil.showMsg(e.getMsg());
            }
        });
    }

}

