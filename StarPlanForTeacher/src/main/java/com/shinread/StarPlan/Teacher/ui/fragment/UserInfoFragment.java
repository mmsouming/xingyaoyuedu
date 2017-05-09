package com.shinread.StarPlan.Teacher.ui.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.CollectActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.LendRecordActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.CommentListActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.shinread.StarPlan.Teacher.ui.activity.ActivateTipActivity;
import com.shinread.StarPlan.Teacher.ui.activity.userinfo.MsgCenterActivity;
import com.shinread.StarPlan.Teacher.ui.activity.userinfo.SettingActivity;
import com.shinread.StarPlan.Teacher.ui.activity.userinfo.UserInfoUpdateActivity;
import com.shinread.StarPlan.Teacher.ui.activity.work.WorkMangerActivity;
import com.shinread.StarPlan.Teacher.util.CommonUtils;
import com.shinyread.StarPlan.Teacher.R;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseFragment implements View.OnClickListener {
    private final static int REQ_ACTIVITY_CODE_KINDS_DETAIL = BASE_CODE + 1;
    private final static int REQ_ACTIVITY_CODE_USERINFO = REQ_ACTIVITY_CODE_KINDS_DETAIL + 1;
    private CircleImageView mIvHeader;
    private TextView mTvName, mTvMsgCount, mTvRecordLend;
    private AccountVo mAccount;
    private TextView tv_record_info,mTvCommentMsgCount;
    private RelativeLayout rl_record;
    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_user_info, container, false);
            initViews(mContentView);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeAllViews();
            }
        }
        initView(mContentView);
        return mContentView;
    }

    private void initViews(View mContentView) {

        mIvHeader = (CircleImageView) mContentView.findViewById(R.id.iv_header);
        mTvName = (TextView) mContentView.findViewById(R.id.tv_name);
        mTvMsgCount = (TextView) mContentView.findViewById(R.id.tv_msg_count);

        mTvCommentMsgCount = (TextView) mContentView.findViewById(R.id.tv_comment_count);
        mContentView.findViewById(R.id.tv_comment).setOnClickListener(this);

        tv_record_info = (TextView) mContentView.findViewById(R.id.tv_record_info);

        mContentView.findViewById(R.id.fl_select_pic).setOnClickListener(this);
        mContentView.findViewById(R.id.iv_setting).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_task_manager).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_class_manager).setOnClickListener(this);
        mTvRecordLend = (TextView) mContentView.findViewById(R.id.tv_record_lend);
        //mTvRecordLend.setOnClickListener(this);
        //mContentView.findViewById(R.id.tv_cast_manager).setVisibility(View.GONE);
        mContentView.findViewById(R.id.tv_collect).setOnClickListener(this);
        mContentView.findViewById(R.id.rl_msg).setOnClickListener(this);
        mContentView.findViewById(R.id.rl_record).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_select_pic://进入个人信息修改页面
                Intent intentUserInfo = new Intent(getActivity(), UserInfoUpdateActivity.class);
                intentUserInfo.putExtra(UserInfoUpdateActivity.KEY_USERINFO, mAccount);
                startActivityForResult(intentUserInfo, REQ_ACTIVITY_CODE_USERINFO);
                break;
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_task_manager://作业管理
               startActivity(new Intent(getActivity(), WorkMangerActivity.class)) ;
                break;
            case R.id.tv_class_manager://班级管理

                break;
            case R.id.rl_record://借书记录
                if (UserInfoManager.getInstance().isTeacherActivate()) {
                    startActivity(new Intent(getActivity(), LendRecordActivity.class));
                } else {
                    Intent i = new Intent(getActivity(), ActivateTipActivity.class);
                    startActivity(i);
                }
                break;
            case R.id.tv_cast_manager://公告管理
                // TODO: 2016/4/20
                break;
            case R.id.tv_collect://我的收藏
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.rl_msg://消息中心
                startActivity(new Intent(getActivity(), MsgCenterActivity.class));
                break;
            case R.id.tv_comment://评论回复
                startActivity(new Intent(getActivity(), CommentListActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setMessage();
        loadUserInfo();
    }

    public void loadUserInfo() {

        initDatas2Views();

    }

    /**
     * 将网络数据 显示到view上
     */
    public void initDatas2Views() {
        mAccount = UserInfoManager.getInstance().getLoginAccountVos();
        CommonUtils.loadImage(mIvHeader, mAccount.headUrl);
        mTvName.setText(mAccount.nickname);

        if (UserInfoManager.getInstance().isTeacherActivate()) {
            tv_record_info.setText("");
            mTvRecordLend.setText(getString(R.string.record_books));
        } else {
            tv_record_info.setText("尚未激活璀璨卡");

        }
    }

    public void setMessage() {
        mTvMsgCount.setText(CacheDataUtil.getMsgNo() != 0 ? CacheDataUtil.getMsgNo() + "" : "");
        mTvCommentMsgCount.setText(CacheDataUtil.getReplyNo() != 0 ? CacheDataUtil.getReplyNo() + "" : "");
    }

    private void initView(View mContentView) {
        tv_record_info = (TextView) mContentView.findViewById(R.id.tv_record_info);
        rl_record = (RelativeLayout) mContentView.findViewById(R.id.rl_record);
    }
}
