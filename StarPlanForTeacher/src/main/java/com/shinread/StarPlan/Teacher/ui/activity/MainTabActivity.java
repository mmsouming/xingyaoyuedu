package com.shinread.StarPlan.Teacher.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.fancyfamily.primarylibrary.commentlibrary.broadcast.RecevieMsg;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.NewMsgNoResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.NewReplyNoResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseWorkFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment.LibraryFragment;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.widget.CustomRadioButton;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.ui.activity.work.fragment.WorkHomeFragment;
import com.shinread.StarPlan.Teacher.ui.fragment.UserInfoFragment;
import com.shinyread.StarPlan.Teacher.R;

/**
 * Created by janecer on 2016/3/15.
 * email:jxc@fancyf.cn
 * des:
 */
public class MainTabActivity extends BaseWorkFragmentActivity {

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义数组来存放Fragment界面
    private Class mFragmentArray[] = { WorkHomeFragment.class , LibraryFragment.class, UserInfoFragment.class};
    private String mTabTags[] = {"tagTask" ,"tagLibrary" , "tagUser"} ;

    private RadioGroup mRgTabs ;

    public static final String EXTRA_SHOW_INDEX = "showIndex" ;

    private int showIndex ;

    UserInfoManager userInfoManager = UserInfoManager.getInstance();
    CustomRadioButton rb_user_center;
    @Override
    protected String setTag() {
        return MainTabActivity.class.getSimpleName() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
        rb_user_center = (CustomRadioButton) findViewById(R.id.rb_user_center);

        Intent intent = getIntent() ;
        if(null != intent) {
            initShowIndex(intent);
        }

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //设置选项卡不可见
        mTabHost.getTabWidget().setVisibility(View.GONE);

        initViews() ;


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

//        Logger.msg(TAG," onNewIntent " +(null != intent) +"　　"+ mFragmentArray[0].getSimpleName()+"  == ? " + GuideFragment.class.getSimpleName());
//        if(null != intent) {
//            initShowIndex(intent);
//            if(mFragmentArray[0].getSimpleName().equals(GuideFragment.class.getSimpleName())){
//
//                mFragmentArray[0] = (userInfoManager.isParentActivate()) ? TaskFragment.class : GuideFragment.class ;
//                Logger.msg(TAG, mFragmentArray[0].getSimpleName());
//                initViews();
//            }
//            ((CustomRadioButton)mRgTabs.getChildAt(showIndex)).setChecked(true);
//        }
    }

    private void initViews() {
        mTabHost.clearAllTabs();
        //得到fragment的个数
        int count = mFragmentArray.length;
        for(int i = 0; i < count; i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTags[i]).setIndicator(i+"") ;
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);

        }

        mRgTabs = ((RadioGroup)findViewById(R.id.rg_tab)) ;
        mRgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rb_guid:

                        showIndex = 0;

                        mTabHost.setCurrentTabByTag(mTabTags[0]);

                        break;
                    case R.id.rb_library:

                        showIndex = 1;
                        mTabHost.setCurrentTabByTag(mTabTags[1]);

                        break;
                    case R.id.rb_user_center:

                        showIndex = 2;
                        mTabHost.setCurrentTabByTag(mTabTags[2]);
                        break;
                }
            }
        });


        ((CustomRadioButton)mRgTabs.getChildAt(showIndex)).setChecked(true);
    }

    private void initShowIndex(Intent intent) {
        showIndex = intent.getIntExtra(EXTRA_SHOW_INDEX,-1) ;
        if(showIndex < 0 || showIndex >2) {
            showIndex = 0 ;
        }
        Logger.msg(TAG, "showIndex : " + showIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private long lastClickBackTime ;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - lastClickBackTime < 2 * 1000) {

            finish();
        } else {
            ToastUtil.showMsg(getString(R.string.back_app_tip));
            lastClickBackTime = System.currentTimeMillis() ;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMessage(null);
        userInfoManager.loadTeacherUserInfo(new HttpResultListener<MeResponseVo>() {
            @Override
            public void onSuccess(MeResponseVo resp) {
                UserInfoFragment fragment = (UserInfoFragment) getSupportFragmentManager().findFragmentByTag(mTabTags[2]);
                if (fragment != null){
                    fragment.loadUserInfo();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
        loadNewMsgNo();
        loadNewReplyNo();
    }

    private void loadNewMsgNo() {
        AppModel.newMsgNo(new HttpResultListener<NewMsgNoResponseVo>() {
            @Override
            public void onSuccess(NewMsgNoResponseVo resp) {
                if (resp.isSuccess()) {
                    CacheDataUtil.setMsgNo(resp.msgNo);
                    getMessage(null);
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    private void loadNewReplyNo() {
        AppModel.newReplyNo(new HttpResultListener<NewReplyNoResponseVo>() {
            @Override
            public void onSuccess(NewReplyNoResponseVo resp) {
                if (resp.isSuccess()) {
                    CacheDataUtil.setReplyNo(resp.getReplyNo());
                    getMessage(null);
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }
    @Override
    public void getMessage(RecevieMsg msg) {


        int rno = CacheDataUtil.getReplyNo();
        int no = CacheDataUtil.getMsgNo();
        UserInfoFragment fragment = (UserInfoFragment) getSupportFragmentManager().findFragmentByTag(mTabTags[2]);
        if (fragment != null){
            fragment.setMessage();
        }

        if (rno == 0 && no == 0) {
            rb_user_center.setShowRedPoint(false);
        } else {
            rb_user_center.setShowRedPoint(true);
        }

    }
}
