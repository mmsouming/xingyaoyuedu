package com.shinread.StarPlan.Parent.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GradeVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.SchoolVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.StudentRegisterResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseDialog;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseWorkFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.lib.multi_image_selector.MultiImageSelectorActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.Int2StrUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UploadPicUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogClassPicker;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogDatePicker;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogTip;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogWheelPicker;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.shinyread.StarPlan.Parent.R;

import java.util.ArrayList;
import java.util.List;

public class AddKindsInfoActivity extends BaseWorkFragmentActivity implements View.OnClickListener {

    private final static int REQ_GET_SCHOOL_CODE = BASE_CODE + 1 ;
    private final static int REQ_STUDENT_REGISTER_CODE = REQ_GET_SCHOOL_CODE + 1 ;

    private static final int REQUEST_IMAGE = 2;

    private TitleBar mTb ;
    private BaseDialog mAuditTipDialog;

    private ImageView mIvHeader ;//头像
    private TextView mTvSchoolValue ,mTvLevel ,mTvClassValue ,mTvBirthdayValue ,mTvRelationValue ;
    private EditText mEtName ,mEtHeight ,mEtWeight ;

    private int sexType = 1 ; // 1 男，2女
    private String pwdCard ; //扫描后的璀璨卡的二维码
    private int relationType = 1 ;

    private ArrayList<String> mSelectPath;
    private String relations[] = {"爸爸" ,"妈妈" ,"其他"} ;

    private String name ,birthday, headUrl ;
    public long classId = -1  ;
    private int heightInt ,weightInt ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }


    SchoolVo vo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kinds_info);

        vo = (SchoolVo) getIntent().getSerializableExtra("SchoolVo");
        pwdCard = getIntent().getStringExtra("pwdCard");
        initViews() ;

    }




    private void initViews() {
        mTb = (TitleBar) findViewById(R.id.tb);
        mIvHeader = (ImageView) findViewById(R.id.iv_header) ;
        mTvSchoolValue = (TextView) findViewById(R.id.tv_school_value) ;
        mTvLevel = (TextView) findViewById(R.id.tv_level) ;
        mTvClassValue = (TextView) findViewById(R.id.tv_class_value) ;
        mTvBirthdayValue = (TextView) findViewById(R.id.tv_birthday_value) ;
        mEtName = (EditText) findViewById(R.id.et_name) ;
        mTvRelationValue = (TextView) findViewById(R.id.tv_relation_value) ;
        mEtHeight = (EditText) findViewById(R.id.et_height) ;
        mEtWeight = (EditText) findViewById(R.id.et_weight) ;


        findViewById(R.id.btn_commit).setOnClickListener(this);
        mTvLevel.setOnClickListener(this);
        mTvClassValue.setOnClickListener(this);
        mTvBirthdayValue.setOnClickListener(this); //日期对话框
        mIvHeader.setOnClickListener(this);
        mTvRelationValue.setOnClickListener(this);
        ((RadioGroup)findViewById(R.id.rg_sex)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.rb_boy :
                        sexType = 1 ;
                        break;
                    case R.id.rb_girl :
                        sexType = 2 ;
                        break;
                }
            }
        });

        mTb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        mTb.setOnRightNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(AddKindsInfoActivity.this, MainTabActivity.class);
                intentMain.putExtra(MainTabActivity.EXTRA_SHOW_INDEX, 2);
                startActivity(intentMain);
                AddKindsInfoActivity.this.finish();
            }
        });
    }
    UploadPicUtil uploadPicUtil = new UploadPicUtil(this);

    private void postRegister(){

        if(classId < 0){
            ToastUtil.showMsg(getString(R.string.input_class_not_null));
            return ;
        }
        name = mEtName.getText().toString() ;
        if(TextUtils.isEmpty(name)){
            ToastUtil.showMsg(getString(R.string.input_name_not_null));
            return ;
        }

        if(sexType < 0) {
            ToastUtil.showMsg(getString(R.string.input_sex_not_null));
            return ;
        }
        // String relation = mEtRelation.getText().toString() ;
        birthday = mTvBirthdayValue.getText().toString() ;
        String height = mEtHeight.getText().toString() ;
        String weight = mEtWeight.getText().toString() ;
        if(!TextUtils.isEmpty(height)){
            heightInt = Integer.parseInt(height) ;
        }
        if(!TextUtils.isEmpty(weight)){
            weightInt = Integer.parseInt(weight) ;
        }


        if (mSelectPath == null || mSelectPath.size() == 0){
            registerStudentInfo();
        }else{
            uploadPicUtil.startUploadPic(mSelectPath, new HttpResultListener<HeadUploadResponseVo>() {
                @Override
                public void onSuccess(HeadUploadResponseVo resp) {
                    if (resp.isSuccess()){
                        List<String> pictureUrlArr = resp.pictureUrlArr;
                        if (null != pictureUrlArr && pictureUrlArr.size() > 0){
                            headUrl = pictureUrlArr.get(0);
                            registerStudentInfo();
                        }
                    }
                }

                @Override
                public void onFailed(Exception e, String msg) {

                }
            });
        }


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_commit:

                postRegister();

                break;
            case R.id.tv_relation_value ://关系选择
                showSelectRelationDialog();
                break ;
            case R.id.tv_birthday_value ://生日选择对话框
                showBirthdayDialog();
                break;
            case R.id.tv_level://班级选择对话框
            case R.id.tv_class_value:
                showClassDialog();
                break ;
            case R.id.iv_header://头像选择
                onChoose(this);
                break ;
        }
    }


    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {

            case REQ_STUDENT_REGISTER_CODE :
                registerStudentInfo();
                break ;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(
                        MultiImageSelectorActivity.EXTRA_RESULT);
                if (mSelectPath != null && mSelectPath.size() > 0) {
                    //ImageLoader.getInstance().displayImage("file://" + mSelectPath.get(0) , mIvHeader);
                    CommonUtils.loadImageLocal(getApplicationContext() ,mIvHeader ,mSelectPath.get(0));

                }
            }
        }
    }


    /**
     * 学生登记
     */
    private void registerStudentInfo() {
        AppModel.studentRegister(pwdCard, classId, name, sexType, birthday, relationType, heightInt, weightInt, headUrl, TAG, new HttpResultListener<StudentRegisterResponseVo>() {
            @Override
            public void onSuccess(StudentRegisterResponseVo resp) {
                uploadPicUtil.finishUpload();
                if(resp.isSuccess()){
                    showAuditTipDialog();
                }

            }

            @Override
            public void onFailed(Exception e, String msg) {
                uploadPicUtil.finishUpload();
            }
        }) ;
    }

    /**
     * 显示提交等待教师审核dialog
     */
    public void showAuditTipDialog(){
        if(null == mAuditTipDialog) {
            View view = getLayoutInflater().inflate(R.layout.dialog_activate_audit_tip,null);
            mAuditTipDialog = new BaseDialog(this , view ,R.style.customDialog) ;
            view.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mAuditTipDialog.dismiss();


                    backfrom();

                }
            });
        }
        mAuditTipDialog.setCancelable(false);
        mAuditTipDialog.show();
    }


    private void backfrom() {
        //UserInfoFragment.isShouldLoadInfoData = true ;
        Intent intent = new Intent(AddKindsInfoActivity.this,MainTabActivity.class);
        intent.putExtra(MainTabActivity.EXTRA_SHOW_INDEX,2) ;
        startActivity(intent);

        AddKindsInfoActivity.this.finish();
    }




    DialogDatePicker picker;
    DialogWheelPicker pickerRelate, pickerSex;
    DialogClassPicker classPicker;

    /**
     * 孩子关系的选取
     */
    public void showSelectRelationDialog() {
        if (pickerRelate == null) {
            pickerRelate = new DialogWheelPicker(this, relations);
            pickerRelate.setListenser(new DialogWheelPicker.OnDialogWheelPicker() {
                @Override
                public void ChooseResult(String content) {
                    mTvRelationValue.setText(content);
                    relationType = Int2StrUtil.str2Relation(content);

                }
            });
        }
        pickerRelate.show();

    }


    /**
     * 班级对话框
     */
    public void showClassDialog() {

        if (vo == null) {
            DialogTip tip = new DialogTip(this, "提示", "当前没有班级，请重试");
            tip.show();
            return;
        }
        if (classPicker == null) {
            classPicker = new DialogClassPicker(this,vo.gradeVoArr);
            classPicker.setListenser(new DialogClassPicker.OnDialogClassPicker() {
                @Override
                public void ChooseResult(GradeVo gradeVo, GenericListVo classVo) {
                    if (classVo != null) {
                        mTvLevel.setText(gradeVo.getName());
                        mTvClassValue.setText(classVo.getName());
                        classId = classVo.getId();

                    }
                }
            });
        }
        classPicker.show();

    }


    /**
     * 日历对话框
     */
    public void showBirthdayDialog() {
        if (picker == null) {
            picker = new DialogDatePicker(this);
            picker.setListenser(new DialogDatePicker.OnDialogWheelPicker() {
                @Override
                public void ChooseResult(String date) {
                    mTvBirthdayValue.setText(date);
                    birthday = date;
                   // updateStudentInfo();
                }
            });
        }
        picker.show();
    }




    public void onChoose(BaseActivity context) {

        int selectedMode = MultiImageSelectorActivity.MODE_SINGLE;
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                selectedMode);
        startActivityForResult(intent, REQUEST_IMAGE);
    }


}
