package com.shinread.StarPlan.Parent.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.StudentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DefaultMarkEnum;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.CollectActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.LendRecordActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.shinread.StarPlan.Parent.ui.activity.ActivateTipActivity;
import com.shinread.StarPlan.Parent.ui.activity.task.TaskStudyRecodersActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.CommentListActivity;
import com.shinread.StarPlan.Parent.ui.activity.userinfo.KindInfoActivity;
import com.shinread.StarPlan.Parent.ui.activity.userinfo.MsgCenterActivity;
import com.shinread.StarPlan.Parent.ui.activity.userinfo.SettingActivity;
import com.shinread.StarPlan.Parent.ui.activity.userinfo.UserInfoUpdateActivity;
import com.shinread.StarPlan.Parent.ui.adapter.KindsAdapter;
import com.shinyread.StarPlan.Parent.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseFragment implements View.OnClickListener {

    private final static int REQ_ACTIVITY_CODE_KINDS_DETAIL = BASE_CODE + 1;
    private final static int REQ_ACTIVITY_CODE_USERINFO = REQ_ACTIVITY_CODE_KINDS_DETAIL + 1;

    private RecyclerView mRclChilds;

    private CircleImageView mIvHeader;
    private TextView mTvName, mTvMsgCount, mTvCommentMsgCount;

    private AccountVo accountVo;
    private StudentVo defaultStudent;
    private List<StudentVo> studentVos;

    private KindsAdapter mKindsAdapter;//孩子信息列表适配
    UserInfoManager userInfoManager = UserInfoManager.getInstance();

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
        return mContentView;
    }

    private LinearLayout layout_unactivite, layout_activited;

    private void initViews(View mContentView) {

        mIvHeader = (CircleImageView) mContentView.findViewById(R.id.iv_header);
        mTvName = (TextView) mContentView.findViewById(R.id.tv_name);
        mTvMsgCount = (TextView) mContentView.findViewById(R.id.tv_msg_count);
        mTvCommentMsgCount = (TextView) mContentView.findViewById(R.id.tv_comment_count);
        mContentView.findViewById(R.id.tv_comment).setOnClickListener(this);
        mContentView.findViewById(R.id.fl_select_pic).setOnClickListener(this);
        mContentView.findViewById(R.id.iv_setting).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_record_lend).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_collect).setOnClickListener(this);

        mContentView.findViewById(R.id.rl_msg).setOnClickListener(this);
        mContentView.findViewById(R.id.iv_add).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_kindinfo).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_record_study).setOnClickListener(this);
        mRclChilds = (RecyclerView) mContentView.findViewById(R.id.rcv_childs);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRclChilds.setLayoutManager(linearLayoutManager);

        layout_unactivite = (LinearLayout) mContentView.findViewById(R.id.layout_unactivite);
        layout_activited = (LinearLayout) mContentView.findViewById(R.id.layout_activited);
        layout_unactivite.setOnClickListener(this);
        layout_unactivite.setVisibility(View.GONE);
        layout_activited.setVisibility(View.GONE);


    }




    //是否激活璀璨卡
    private boolean isActivate() {
        if (null == studentVos || studentVos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_select_pic://进入个人信息修改页面
                Intent intentUserInfo = new Intent(getActivity(), UserInfoUpdateActivity.class);
                intentUserInfo.putExtra(UserInfoUpdateActivity.KEY_USERINFO, accountVo);
                startActivityForResult(intentUserInfo, REQ_ACTIVITY_CODE_USERINFO);
                break;
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_record_lend://借书记录
                Intent intent = new Intent(getActivity(), LendRecordActivity.class);
                intent.putExtra(KindInfoActivity.KEY_STUDENT, defaultStudent);
                startActivity(intent);
                break;
            case R.id.tv_collect://我的收藏
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.tv_comment://评论回复
                startActivity(new Intent(getActivity(), CommentListActivity.class));
                break;
            case R.id.rl_msg://消息中心
                startActivity(new Intent(getActivity(), MsgCenterActivity.class));
                break;
            case R.id.layout_unactivite:
            case R.id.iv_add://扫描二维码添加孩子信息
                startActivity(new Intent(getActivity(), ActivateTipActivity.class));
                break;
            case R.id.tv_kindinfo:

                Intent intentKind = new Intent(getActivity(), KindInfoActivity.class);
                intentKind.putExtra(KindInfoActivity.KEY_STUDENT, defaultStudent);
                startActivity(intentKind);

                break;
            case R.id.tv_record_study://孩子学习记录
                Intent intentRecord = new Intent(getActivity(), TaskStudyRecodersActivity.class);
                startActivity(intentRecord);
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
        accountVo = userInfoManager.getLoginAccountVos();
        studentVos = userInfoManager.getStudentVos();
        defaultStudent = userInfoManager.getDefaultStudent();
        initDatas2Views();
    }


    public void initDatas2Views() {

        if (isActivate()) {
            layout_activited.setVisibility(View.VISIBLE);
            layout_unactivite.setVisibility(View.GONE);
        } else {
            layout_activited.setVisibility(View.GONE);
            layout_unactivite.setVisibility(View.VISIBLE);
        }
        if (null != accountVo) {
            CommonUtils.loadImage(mIvHeader, accountVo.headUrl);
            mTvName.setText(accountVo.nickname);
        }
        if (null != studentVos) {
            mKindsAdapter = new KindsAdapter(studentVos, new KindsAdapter.ViewHolder.MyItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    int size = studentVos.size();
                    for (int i = 0; i < size; i++) {
                        studentVos.get(i).defaultMark = DefaultMarkEnum.NOT_DEFULT.getNo();
                    }
                    defaultStudent = studentVos.get(postion);
                    defaultStudent.defaultMark = DefaultMarkEnum.DEFULT.getNo();
                    mKindsAdapter.notifyDataSetChanged();
                }
            });
            mRclChilds.setAdapter(mKindsAdapter);
        }
    }


    public void setMessage() {
        
        mTvMsgCount.setText(CacheDataUtil.getMsgNo() != 0 ? CacheDataUtil.getMsgNo() + "" : "");
        mTvCommentMsgCount.setText(CacheDataUtil.getReplyNo() != 0 ? CacheDataUtil.getReplyNo() + "" : "");
    }
}
