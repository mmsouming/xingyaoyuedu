package com.shinread.StarPlan.Parent.ui.activity.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateUserInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.lib.multi_image_selector.MultiImageSelectorActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.Int2StrUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UploadPicUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogDatePicker;
import com.fancyfamily.primarylibrary.commentlibrary.widget.DialogWheelPicker;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.shinyread.StarPlan.Parent.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by janecer on 2016/3/30.
 * email:jxc@fancyf.cn
 * des:
 */
public class UserInfoUpdateActivity extends BaseFragmentActivity implements View.OnClickListener {

    public static final String KEY_USERINFO = "key_userinfo";

    private static final int REQUEST_IMAGE = BASE_CODE + 1;
    private static final int REQ_UPDATEINFO = REQUEST_IMAGE + 1;


    private CircleImageView mIvHead;
    private EditText mEtNickName;
    private TextView mTvSex, mTvBirthday;

    private String sexs[] = {"男", "女"};
    private ArrayList<String> mSelectPath;

    public String headUrl, nickName, birthday;
    public int sexType;


    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_update);

        AccountVo mInfos = (AccountVo) getIntent().getSerializableExtra(KEY_USERINFO);
        headUrl = mInfos.headUrl;
        nickName = mInfos.nickname;
        birthday = mInfos.birthday;
        sexType = mInfos.sexType;


        initViews();

        initDatas();

    }
    private boolean mIsOpen = true;
    private void initDatas() {

        CommonUtils.loadImage(mIvHead, headUrl);
        mEtNickName.setText(nickName);
        mTvSex.setText(Int2StrUtil.sex2Str(sexType));
        mTvBirthday.setText(birthday);


    }

    private void initViews() {
        mIvHead = (CircleImageView) findViewById(R.id.iv_header);
        mEtNickName = (EditText) findViewById(R.id.et_nick_name);
        mTvSex = (TextView) findViewById(R.id.tv_sex);
        mTvBirthday = (TextView) findViewById(R.id.tv_birthday);

        mIvHead.setOnClickListener(this);
        mTvSex.setOnClickListener(this);
        mTvBirthday.setOnClickListener(this);
        TitleBar tb = (TitleBar) findViewById(R.id.tb);
        tb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mEtNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                nickName = s.toString();

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
                            isopen = false;
                        } else {
                            // 打开
                            isopen = true;
                        }

                        if (mIsOpen != isopen && isopen == false) {
                            updateUserInfo();
                        }
                        mIsOpen = isopen;
                    }
                });


    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sex:
                showSexDialog();
                break;
            case R.id.tv_birthday:
                showBirthdayDialog();
                break;
            case R.id.iv_header:
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
                    CommonUtils.loadImageLocal(getApplicationContext(), mIvHead, mSelectPath.get(0));


                    uploadPicUtil.startUploadPic(mSelectPath, new HttpResultListener<HeadUploadResponseVo>() {
                        @Override
                        public void onSuccess(HeadUploadResponseVo resp) {
                            if (resp.isSuccess()) {
                                List<String> pictureUrlArr = resp.pictureUrlArr;
                                if (null != pictureUrlArr && pictureUrlArr.size() > 0) {
                                    headUrl = pictureUrlArr.get(0);
                                    updateUserInfo();
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


    public void updateUserInfo() {

        AppModel.updateUserInfo(nickName, sexType, birthday, headUrl, TAG, new HttpResultListener<UpdateUserInfoResp>() {
            @Override
            public void onSuccess(UpdateUserInfoResp resp) {
                uploadPicUtil.finishUpload();
                if (resp.isSuccess()) {
                    initDatas();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                uploadPicUtil.finishUpload();
            }
        });
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

    /**
     * 孩子性别的选取
     */
    public void showSexDialog() {
        if (pickerSex == null) {
            pickerSex = new DialogWheelPicker(this, sexs);
            pickerSex.setListenser(new DialogWheelPicker.OnDialogWheelPicker() {
                @Override
                public void ChooseResult(String content) {
                    mTvSex.setText(content);
                    sexType = Int2StrUtil.str2Sex(content);
                    updateUserInfo();
                }
            });
        }
        pickerSex.show();

    }


    DialogDatePicker picker;
    DialogWheelPicker pickerSex;

    /**
     * 日历对话框
     */
    public void showBirthdayDialog() {
        if (picker == null) {
            picker = new DialogDatePicker(this);
            picker.setListenser(new DialogDatePicker.OnDialogWheelPicker() {
                @Override
                public void ChooseResult(String date) {
                    mTvBirthday.setText(date);
                    birthday = date;
                    updateUserInfo();
                }
            });
        }
        picker.show();
    }


}
