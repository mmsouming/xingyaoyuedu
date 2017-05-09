package com.shinread.StarPlan.Parent.ui.activity.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GradeVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.StudentVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ClassListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SettingDefaultStuResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateStudentInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
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


/**
 * Created by janecer on 2016/3/30.
 * email:jxc@fancyf.cn
 * des:
 */
public class KindInfoActivity extends BaseFragmentActivity implements View.OnClickListener {


    private static final int REQ_UPDATEINFO = BASE_CODE + 1;
    private static final int REQUEST_IMAGE = REQ_UPDATEINFO + 1;

    public static final String KEY_STUDENT = "key_student";


    private LinearLayout mllContent;

    private ImageView mIvHeader;
    private TextView mTvName, mTvLevel, mTvLevelNick, mTvSchoolValue, mTvClassValue, mTvSexValue, mTvBirthdayValue, mTvRelationValue;

    private EditText mEtWeight, mEtHeight;
    private StudentVo mKind;

    private String sexs[] = {"男", "女"};

    private String relations[] = {"爸爸", "妈妈", "其他"};

    private ArrayList<String> mSelectPath;

    public long id;
    public long classId;
    public String name;
    public int sexType;
    public String birthday;
    public int relationType;
    public int height;
    public int weight;
    public String picUrl;

    private List<GradeVo> mGradeVos;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinds_info);

        mKind = (StudentVo) getIntent().getSerializableExtra(KEY_STUDENT);
        initViews();
        initDatas();
        loadClass();
    }

    private void loadClass() {
        AppModel.getClassList(mKind.schoolId, TAG, new HttpResultListener<ClassListResp>() {
            @Override
            public void onSuccess(ClassListResp resp) {
                if (resp.isSuccess()) {
                    mGradeVos = resp.gradeVoArr;
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }

    private void initDatas() {
        if (null != mKind) {
            id = mKind.getId();
            classId = mKind.getClassesId();
            name = mKind.getName();
            sexType = mKind.getSexType();
            birthday = mKind.getBirthday();
            relationType = mKind.getRelationType();
            height = mKind.getHeight();
            weight = mKind.getWeight();
            picUrl = mKind.getHeadUrl();

            CommonUtils.loadImage(mIvHeader, mKind.headUrl);

            mTvName.setText(mKind.name);
            mTvLevel.setText(mKind.score + "");//等级
            mTvLevelNick.setText(mKind.studentLevelType + "");//等级昵称
            mTvSchoolValue.setText(mKind.schoolName);
            mTvClassValue.setText(mKind.getClassesName());
            mTvSexValue.setText(Int2StrUtil.sex2Str(mKind.sexType));
            mTvBirthdayValue.setText(mKind.birthday);
            mEtHeight.setText(mKind.height + "");
            mEtWeight.setText(mKind.weight + "");
            mTvRelationValue.setText(Int2StrUtil.relation2Str(mKind.relationType == null ? 100 : mKind.relationType));

            mEtHeight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    height = Integer.parseInt(s.toString());
                }
            });

            mEtWeight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    weight = Integer.parseInt(s.toString());
                }
            });

            final View mRoot = findViewById(R.id.my_content_view);
            mRoot.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {
                            // TODO Auto-generated method stub
                            // 比较Activity根布局与当前布局的大小
                            int offset = mRoot.getRootView().getHeight()
                                    - mRoot.getHeight();
                            // 根据视图的偏移值来判断键盘是否显示
                            boolean isopen;
                            if (offset < 300) {
                                // 关闭

                                isopen = false;

                                // setMore();
                                // clearEdit();
                            } else {

                                // 打开
                                isopen = true;
                                // setSend();

                            }

                            if (mIsOpen != isopen && isopen == false){
                                updateStudentInfo();
                            }
                            mIsOpen = isopen;
                        }
                    });

        }

    }

    private boolean mIsOpen;
    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        if(msg.what == REQ_UPDATEINFO){

            updateStudentInfo();
        }
    }

    private void initViews() {
        TitleBar tb = (TitleBar) findViewById(R.id.tb);
        tb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.btn_score).setOnClickListener(this);
        findViewById(R.id.btn_setting_default).setOnClickListener(this);
        findViewById(R.id.iv_header).setOnClickListener(this);
        mIvHeader = (ImageView) findViewById(R.id.iv_header);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvLevel = (TextView) findViewById(R.id.tv_level);
        mTvLevelNick = (TextView) findViewById(R.id.tv_level_nick);
        mTvSchoolValue = (TextView) findViewById(R.id.tv_school_value);
        mTvClassValue = (TextView) findViewById(R.id.tv_class_value);
        mTvSexValue = (TextView) findViewById(R.id.tv_sex_value);
        mTvBirthdayValue = (TextView) findViewById(R.id.tv_birthday_value);
        mEtHeight = (EditText) findViewById(R.id.et_height_value);
        mEtWeight = (EditText) findViewById(R.id.et_weight_value);
        mTvRelationValue = (TextView) findViewById(R.id.tv_relation_value);


        mllContent = (LinearLayout) findViewById(R.id.ll_content);
        mIvHeader.setOnClickListener(this);
        mTvClassValue.setOnClickListener(this);
        mTvSexValue.setOnClickListener(this);
        mTvBirthdayValue.setOnClickListener(this);
        mTvRelationValue.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_score:
                startActivity(new Intent(this, KindScoreActivity.class));
                break;
            case R.id.tv_class_value://显示年级选择对话框
                showClassDialog();
                break;
            case R.id.tv_sex_value://性别对话框
                showSexDialog();
                break;
            case R.id.tv_birthday_value://生日选取对话框
                showBirthdayDialog();
                break;
            case R.id.tv_relation_value://关系选取
                showSelectRelationDialog();
                break;
            case R.id.btn_setting_default:
                showLoaddingDialog();
                AppModel.setDefaultStuInfo(mKind.id, TAG, new HttpResultListener<SettingDefaultStuResp>() {
                    @Override
                    public void onSuccess(SettingDefaultStuResp resp) {
                        dismissLoaddingDialog();
                        ToastUtil.showMsg(resp.getMsg());
                        if (resp.isSuccess()) {
                            mKind = resp.student;
                            initDatas();
                            finish();
                        }
                    }

                    @Override
                    public void onFailed(Exception e, String msg) {
                        dismissLoaddingDialog();
                    }
                });
                break;
            case R.id.iv_header://上传头像
                onChoose(this);
                break;
        }
    }
    UploadPicUtil uploadPicUtil = new UploadPicUtil(this);
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
                    CommonUtils.loadImageLocal(getApplicationContext(), mIvHeader, mSelectPath.get(0));


                    uploadPicUtil.startUploadPic(mSelectPath, new HttpResultListener<HeadUploadResponseVo>() {
                        @Override
                        public void onSuccess(HeadUploadResponseVo resp) {
                            if (resp.isSuccess()){
                                List<String> pictureUrlArr = resp.pictureUrlArr;
                                if (null != pictureUrlArr && pictureUrlArr.size() > 0){
                                    picUrl = pictureUrlArr.get(0);
                                    updateStudentInfo();
                                }
                            }
                        }

                        @Override
                        public void onFailed(Exception e, String msg) {

                        }
                    });
                }
            }
        }
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

    public void updateStudentInfo() {
        AppModel.updateStudentInfo(id, classId, name, sexType, birthday, relationType, height, weight, picUrl, TAG, new HttpResultListener<UpdateStudentInfoResp>() {
            @Override
            public void onSuccess(UpdateStudentInfoResp resp) {
                uploadPicUtil.finishUpload();
                if (resp.isSuccess()) {
                    mKind = resp.student;
                    initDatas();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                uploadPicUtil.finishUpload();
            }
        });
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
                    updateStudentInfo();
                }
            });
        }
        pickerRelate.show();

    }


    /**
     * 班级对话框
     */
    public void showClassDialog() {

        if (mGradeVos == null) {
            DialogTip tip = new DialogTip(this, "提示", "当前没有班级，请重试");
            tip.show();
            loadClass();
            return;
        }
        if (classPicker == null) {
            classPicker = new DialogClassPicker(this, mGradeVos);
            classPicker.setListenser(new DialogClassPicker.OnDialogClassPicker() {
                @Override
                public void ChooseResult(GradeVo gradeVo, GenericListVo classVo) {
                    if (classVo != null) {
                        mTvClassValue.setText(classVo.getName());
                        classId = classVo.getId();
                        updateStudentInfo();
                    }
                }
            });
        }
        classPicker.show();

    }

    /**
     * 孩子性别的选取
     */
    public void showSexDialog() {
        if (pickerSex == null) {
            pickerSex = new DialogWheelPicker(this, sexs);
            pickerSex.setListenser(new DialogWheelPicker.OnDialogWheelPicker() {
                @Override
                public void ChooseResult(String content) {
                    mTvSexValue.setText(content);
                    sexType = Int2StrUtil.str2Sex(content);
                    updateStudentInfo();
                }
            });
        }
        pickerSex.show();

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
                    updateStudentInfo();
                }
            });
        }
        picker.show();
    }

}
