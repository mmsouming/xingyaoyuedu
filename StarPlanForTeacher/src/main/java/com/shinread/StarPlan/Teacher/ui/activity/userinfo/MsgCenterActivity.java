package com.shinread.StarPlan.Teacher.ui.activity.userinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.MsgVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ReadStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;
import java.util.List;


public class MsgCenterActivity extends BaseFragmentActivity {


    private ListView mLvMsgs;
    private List<MsgVo> datas = new ArrayList<>();

    private CommonAdapter<MsgVo> msgVoCommonAdapter = null;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_userinfo_msgcenter);

        initViews();
    }

    SwipyRefreshLayout swipyrefreshlayout;
    LoadTipManager manager;

    private void initViews() {

        manager = new LoadTipManager(this, R.id.swipyrefreshlayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(false);
            }
        });
        manager.setBackgroundResource(R.drawable.radiu_white_bg);
        mLvMsgs = (ListView) findViewById(R.id.lv_msg);
        TitleBar tb = (TitleBar) findViewById(R.id.tb);
        tb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        msgVoCommonAdapter = new CommonAdapter<MsgVo>(this, datas, R.layout.lv_item_userinfo_msgcenter) {
            @Override
            protected void convert(ViewHolder vh, MsgVo item) {
                ((TextView) vh.getView(R.id.tv_msg)).setText(item.getName());
                ((TextView) vh.getView(R.id.tv_msg_detail)).setText(item.getContent());
                ((TextView) vh.getView(R.id.tv_time)).setText(item.getTime());
            }
        };

        swipyrefreshlayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        swipyrefreshlayout.setColorSchemeResources(R.color.bule_1);
        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                loadData(direction == SwipyRefreshLayoutDirection.TOP ? false : true);
            }
        });


        mLvMsgs.setAdapter(msgVoCommonAdapter);
        req = new MsgListReq();

        if (CacheDataUtil.getMsgNo() > 0) {
            req.readStatus = ReadStatusEnum.NO_READ_MSG.getNo();
        } else {
            req.readStatus = ReadStatusEnum.HAS_READ_MSG.getNo();
        }

        loadData(false);

    }

    MsgListReq req;
    Long timestamp;

    private void loadData(final boolean isMore) {

        if (!isMore) {
            timestamp = Long.valueOf(0);
        }
        req.timestamp = timestamp;
        AppModel.msgList(req, new HttpResultListener<MsgListResp>() {
            @Override
            public void onSuccess(MsgListResp resp) {
                manager.showLoadSuccess();
                //manager.showLoadException();
                swipyrefreshlayout.setRefreshing(false);
                if (resp.isSuccess()) {
                    CacheDataUtil.clearNotice();
                    if (resp.msgVoArr.size() > 0) {
                        timestamp = resp.msgVoArr.get(resp.msgVoArr.size() - 1).getTimestamp();
                    }

                    if (!isMore) {
                        if (resp.msgVoArr.size() == 0) {
                            manager.showLoadException();
                        }
                        datas = resp.msgVoArr;
                    } else {
                        datas.addAll(resp.msgVoArr);
                    }
                    msgVoCommonAdapter.setDataChange(datas);
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                if (!isMore) {
                    manager.showLoadException();
                }
                swipyrefreshlayout.setRefreshing(false);
            }
        });

    }


}
