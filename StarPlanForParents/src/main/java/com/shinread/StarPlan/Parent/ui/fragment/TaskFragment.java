package com.shinread.StarPlan.Parent.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.StudentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UnfinishWorkResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.WebActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.shinread.StarPlan.Parent.ui.activity.task.TaskCommitActivity;
import com.shinyread.StarPlan.Parent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janecer on 2016/4/7.
 * email:jxc@fancyf.cn
 * des:
 */
public class TaskFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager mVpTask;

    private List<String> mTasks;

    public StudentVo studentVo; //默认孩子信息

    private ImageView ivHeader;
    private TextView tvName, tv_no_text, tvLevelNick, tvLevelValue;
    private ProgressBar progressBar1;

    private LayoutInflater inflater;
    private List<View> taskViews;

    private boolean isFirst = true;

    Button btn_test, btn_read_plan;
    private int currentPostion;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        studentVo = UserInfoManager.getInstance().getDefaultStudent();
        if (null == mContentView) {
            this.inflater = inflater;
            mContentView = inflater.inflate(R.layout.fragment_task, null);
            ivHeader = (ImageView) mContentView.findViewById(R.id.iv_header);
            tvName = (TextView) mContentView.findViewById(R.id.tv_name);
            tvLevelNick = (TextView) mContentView.findViewById(R.id.tv_level_nick);
            tv_no_text = (TextView) mContentView.findViewById(R.id.tv_no_text);
            tvLevelValue = (TextView) mContentView.findViewById(R.id.tv_level_value);
            progressBar1 = (ProgressBar) mContentView.findViewById(R.id.progressBar1);
            mContentView.findViewById(R.id.iv_left).setOnClickListener(this);
            mContentView.findViewById(R.id.iv_right).setOnClickListener(this);
            btn_test = (Button) mContentView.findViewById(R.id.btn_test);
            btn_read_plan = (Button) mContentView.findViewById(R.id.btn_read_plan);
            btn_test.setOnClickListener(this);
            btn_read_plan.setOnClickListener(this);
            mVpTask = (ViewPager) mContentView.findViewById(R.id.vp_task);
            mVpTask.setAdapter(taskAdapter);
            mVpTask.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    currentPostion = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeAllViews();
            }
        }
        return mContentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        studentVo = UserInfoManager.getInstance().getDefaultStudent();
        tvName.setText(studentVo.name);
        tvLevelValue.setText(studentVo.score + "");//分数
        tvLevelNick.setText(studentVo.studentLevelType + "");//等级昵称
        progressBar1.setProgress(studentVo.score);
        CommonUtils.loadImage(ivHeader, studentVo.getHeadUrl());

        loadDatas();
    }

    public void loadDatas() {
        AppModel.unFinishWorkList(studentVo.getId(), TAG, new HttpResultListener<UnfinishWorkResponseVo>() {
            @Override
            public void onSuccess(UnfinishWorkResponseVo resp) {

                if (resp.isSuccess()) {
                    currentPostion = 0;
                    if (resp.getWorkListVoArr().size() > 0) {
                        tv_no_text.setVisibility(View.GONE);
                    } else {
                        tv_no_text.setVisibility(View.VISIBLE);
                    }
                    taskViews = getVpItemViews(resp.getWorkListVoArr());
                    taskAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }


    public List<View> getVpItemViews(List<WorkListVo> items) {
        List<View> views = new ArrayList<>();
        for (WorkListVo item : items) {
            View view = inflater.inflate(R.layout.vp_task_item, null);
            Button btn = (Button) view.findViewById(R.id.btn_task);
            btn.setOnClickListener(this);
            btn.setTag(item);
            if (item.getWorkStatus() == WorkStatusEnum.REPULSE.getNo()) {
                btn.setText("重做作业");
            } else {
                btn.setText("做作业");
            }
            ((TextView) view.findViewById(R.id.tv_title)).setText(item.getName());
            ((TextView) view.findViewById(R.id.tv_end_time)).setText("结束时间：" + item.getInvalidTime());
            views.add(view);
        }
        return views;
    }

    ;


    private PagerAdapter taskAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return taskViews == null ? 0 : taskViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(taskViews.get(position));
            return taskViews.get(position);
        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_task:
                WorkListVo vo = (WorkListVo) v.getTag();
                Intent intent = new Intent(getActivity(), TaskCommitActivity.class);
                intent.putExtra("workId", vo.getId());
                startActivity(intent);
                break;
            case R.id.iv_left:
                if (--currentPostion < 0) {
                    currentPostion = 0;
                    return;
                }
                mVpTask.setCurrentItem(currentPostion);
                break;
            case R.id.iv_right:
                if (++currentPostion > taskViews.size() - 1) {
                    currentPostion = taskViews.size() - 1;
                    return;
                }
                mVpTask.setCurrentItem(currentPostion);
                break;
            case R.id.btn_read_plan: {

                Intent i = new Intent(getActivity(), WebActivity.class);
                i.putExtra(WebActivity.WEB_URL, Constants.WEB_READPLAN);
                startActivity(i);
            }

            break;
            case R.id.btn_test: {
                Intent i = new Intent(getActivity(), WebActivity.class);
                i.putExtra(WebActivity.WEB_URL, Constants.WEB_TEST);
                startActivity(i);
            }
            break;
        }
    }
}
