package com.fancyfamily.primarylibrary.commentlibrary.ui.comment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyNoticeVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ReadStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter.ReceiverReplyAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.CacheDataUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

/**
 * Created by janecer on 2016/3/28.
 * email:jxc@fancyf.cn
 * des: 已还 书籍列表
 */
public class ReceiverReplysFragment extends BaseFragment {

    private ReceiverReplyAdapter adapter;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_ordering, null);

            adapter = new ReceiverReplyAdapter(getActivity(), this);

            initListView(mContentView);

            req = new ReplyNoticeListReq();
            if (CacheDataUtil.getReplyNo() > 0) {
                req.readStatus = ReadStatusEnum.NO_READ_MSG.getNo();
            } else {
                req.readStatus = ReadStatusEnum.HAS_READ_MSG.getNo();
            }
            loadData(false);
            CacheDataUtil.clearReplyNo();
        }



        return mContentView;
    }


    SwipyRefreshLayout swipyrefreshlayout;
    LoadTipManager manager;
    private ListView lv_pull;

    private void initListView(View context) {
        lv_pull = (ListView) context.findViewById(R.id.lv_pull);
        manager = new LoadTipManager(getActivity(), context, R.id.swipyrefreshlayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(false);
            }
        });
        manager.setBackgroundResource(R.drawable.radiu_white_bg);
        lv_pull.setAdapter(adapter);

        swipyrefreshlayout = (SwipyRefreshLayout) context.findViewById(R.id.swipyrefreshlayout);
        swipyrefreshlayout.setColorSchemeResources(R.color.bule_1);
        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                loadData(direction == SwipyRefreshLayoutDirection.TOP ? false : true);
            }
        });

    }

    public void setLoad() {
        if (datas != null && datas.size() > 0) {
            manager.showLoadSuccess();
        } else {
            manager.showLoadException();
        }
    }

    ReplyNoticeListReq req ;
    Long timestamp;
    List<ReplyNoticeVo> datas;

    private void loadData(final boolean isMore) {

        if (!isMore) {
            timestamp = Long.valueOf(0);
        }

        req.timestamp = timestamp;
        CommonAppModel.replyNoticeList(req, new HttpResultListener<ReplyNoticeListResponseVo>() {
            @Override
            public void onSuccess(ReplyNoticeListResponseVo resp) {
                manager.showLoadSuccess();
                swipyrefreshlayout.setRefreshing(false);
                if (resp.isSuccess()) {

                    if (resp.getReplyNoticeVoArr().size() > 0) {
                        timestamp = resp.getReplyNoticeVoArr().get(resp.getReplyNoticeVoArr().size() - 1).getTimestamp();
                    }

                    if (!isMore) {
                        if (resp.getReplyNoticeVoArr().size() == 0) {
                            manager.showLoadException();
                        }
                        datas = resp.getReplyNoticeVoArr();
                    } else {
                        datas.addAll(resp.getReplyNoticeVoArr());
                    }
                    adapter.setObjects(datas);
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
