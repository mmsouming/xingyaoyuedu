package com.shinread.StarPlan.Parent.ui.activity.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ScoreVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ScoreResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.WebActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.fancyfamily.primarylibrary.commentlibrary.util.LoadUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
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
public class KindScoreActivity extends BaseFragmentActivity implements View.OnClickListener {


    CommonAdapter<ScoreVo> adapter;
    ListView mLvOrdering;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kind_score);

        initViews();
    }

    private void initViews() {
        TitleBar tb = (TitleBar) findViewById(R.id.tb);
        tb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.btn_rule).setOnClickListener(this);

        mLvOrdering = (ListView) findViewById(R.id.lv_pull);
        adapter = new CommonAdapter<ScoreVo>(this, null, R.layout.lv_item_userinfo_score) {




            @Override
            protected void convert(ViewHolder vh, ScoreVo item) {
                ((TextView)vh.getView(R.id.tv_title)).setText(item.getRemark());
                ((TextView)vh.getView(R.id.tv_time)).setText(item.getTime());
                ((TextView)vh.getView(R.id.tv_score_add)).setText(item.getAmount()+"");
            }
        };

        mLvOrdering.setAdapter(adapter);
        loadUtil = new LoadUtil(this, new LoadUtil.onLoadListener() {
            @Override
            public void OnLoadException() {

                loadData(false);
            }

            @Override
            public void OnRefresh(LoadUtil.LoadUtilRefreshLayoutDirection direction) {
                loadData(direction == LoadUtil.LoadUtilRefreshLayoutDirection.TOP ? false : true);
            }
        });

        loadData(false);
    }

    LoadUtil loadUtil;

    BasePageReq req = new BasePageReq();
    Long timestamp = Long.valueOf(0);

    private List<ScoreVo> datas = new ArrayList<>();

    private void loadData(final boolean isMore) {

        if (!isMore) {
            timestamp = Long.valueOf(0);
        }
        req.id = UserInfoManager.getInstance().getDefaultStudent().getId();
        req.timestamp = timestamp;
        AppModel.score(req, new HttpResultListener<ScoreResponseVo>() {
            @Override
            public void onSuccess(ScoreResponseVo resp) {

                if (resp.isSuccess()) {
                    List<ScoreVo> temp = resp.getScoreVoArr();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rule:

                Intent i = new Intent(this, WebActivity.class);
                i.putExtra(WebActivity.WEB_URL, Constants.WEB_INTEGRALRULE);
                startActivity(i);
                break;
        }
    }
}
