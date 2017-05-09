package com.shinread.StarPlan.Teacher.ui.fragment.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookCollectVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BorrowVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ReplyNoticeVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.ReadStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BorrowedListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CollectListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragment;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.ui.adapter.InterstBook_SelectAdapter;
import com.shinread.StarPlan.Teacher.ui.adapter.Student_ManergerAdapter;
import com.shinread.StarPlan.Teacher.ui.widget.MyChoseBook;
import com.shinyread.StarPlan.Teacher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizehong on 2016/5/3.從兴趣选择部分
 */

public class InterstBook_SelectFragment extends BaseFragment implements InterstBook_SelectAdapter.OnAdapterHandleListeners {
    //  private ListView mLvOrdering ;

    private List<BookCollectVo> datas = new ArrayList<>();
    //    private CommonAdapter<BookCollectVo> adapter ;
    private int pageNo = 0;
    LoadTipManager manager;
    private ListView lv_pull;
    private InterstBook_SelectAdapter adapter;
    private SwipyRefreshLayout swipyrefreshlayout;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_interstbook, null);

            adapter = new InterstBook_SelectAdapter(this, getActivity());

            initListView(mContentView);


            loadData(false);
        }


        return mContentView;
    }

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

    public void loadData(final boolean isMore) {
        AppModel.collectList(TAG, new HttpResultListener<CollectListResp>() {
            @Override
            public void onSuccess(CollectListResp resp) {
                manager.showLoadSuccess();
                if (resp.isSuccess()) {

                    if (!isMore) {
                        if (resp.getBookCollectVoArr().size() == 0) {
                            manager.showLoadException();
                        }
                        datas = resp.getBookCollectVoArr();
                    } else {
                        datas.addAll(resp.getBookCollectVoArr());
                    }
                    adapter.setObjects(datas);
                    return;
                }
                ToastUtil.showMsg(resp.getMsg());
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

    List<String> imgArr = new ArrayList<>();
    List<String> bookIdArr = new ArrayList<>();

    @Override
    public void select(List<BookCollectVo> objects, boolean flag) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).isChoosed()) {
                MyChoseBook.mSelectedbookimg.add(objects.get(i).getCoverUrl());
                MyChoseBook.mSelectedbook.add(objects.get(i).getId());
            }
        }
    }
}