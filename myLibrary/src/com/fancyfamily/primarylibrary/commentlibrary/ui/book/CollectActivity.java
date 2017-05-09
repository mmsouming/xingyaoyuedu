package com.fancyfamily.primarylibrary.commentlibrary.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookCollectVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CollectListResp;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoadUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janecer on 2016/3/30.
 * email:jxc@fancyf.cn
 * des:
 */
public class CollectActivity extends BaseFragmentActivity {


    private ListView mLvCollects;


    private CommonAdapter<BookCollectVo> adapter;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_userinfo_collect);

        initViews();
    }

    private void initViews() {
        mLvCollects = (ListView) findViewById(R.id.lv_pull);
        TitleBar tb = (TitleBar) findViewById(R.id.tb);
        tb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        adapter = new CommonAdapter<BookCollectVo>(this, datas, R.layout.lv_item_userinfo_collect) {
            @Override
            protected void convert(ViewHolder vh, BookCollectVo item) {
                ImageView ivBook = vh.getView(R.id.iv_book);
                TextView tvBookName = vh.getView(R.id.tv_book_name);
                TextView tvBookDes = vh.getView(R.id.tv_book_des);
                TextView tvTime = vh.getView(R.id.tv_time);
                TextView tvMsg = vh.getView(R.id.tv_msg);
                RatingBar rbStar = vh.getView(R.id.rb_star);
                CommonUtils.loadImage(ivBook, item.getCoverUrl());
                //ImageLoader.getInstance().displayImage(item.getCoverUrl(),ivBook);
                tvBookName.setText(item.getName());
                tvBookDes.setText(item.getIntroduction());
                tvTime.setText(item.getTime());
                tvMsg.setText(item.getCommentNo() + "");
                rbStar.setRating((item.getRecommend() / 20));
            }
        };
        mLvCollects.setAdapter(adapter);

        loadUtil = new LoadUtil(this, new LoadUtil.onLoadListener() {
            @Override
            public void OnLoadException() {

                loadData(false);
            }

            @Override
            public void OnRefresh(LoadUtil.LoadUtilRefreshLayoutDirection direction) {
                loadData(false);
            }
        });

        loadUtil.setDirection(LoadUtil.LoadUtilRefreshLayoutDirection.TOP);

        mLvCollects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookCollectVo vo = datas.get(position);
                Intent i = new Intent(CollectActivity.this, BookDetailsActivity.class);
                i.putExtra("bookId",vo.getId());
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(false);
    }

    LoadUtil loadUtil;

    BasePageReq req = new BasePageReq();
    Long timestamp = Long.valueOf(0);

    private List<BookCollectVo> datas = new ArrayList<>();

    private void loadData(final boolean isMore) {

        if (!isMore) {
            timestamp = Long.valueOf(0);
        }
        req.timestamp = timestamp;
        CommonAppModel.collectList(req, new HttpResultListener<CollectListResp>() {
            @Override
            public void onSuccess(CollectListResp resp) {

                if (resp.isSuccess()) {
                    List<BookCollectVo> temp = resp.bookCollectVoArr;
                    if (temp.size() > 0) {
                        timestamp = temp.get(temp.size() - 1).getTimestamp();
                    }
                    if (!isMore) {
                        datas = temp;
                    } else {
                        datas.addAll(temp);
                    }
                    adapter.setDataChange(datas);
                }

                if (datas != null && datas.size() > 0) {
                    loadUtil.showLoadSuccess();
                } else {
                    loadUtil.showLoadException();
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                loadUtil.showLoadException();
            }
        });

    }


}
