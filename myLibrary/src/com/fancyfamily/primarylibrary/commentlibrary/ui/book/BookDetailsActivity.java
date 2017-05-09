package com.fancyfamily.primarylibrary.commentlibrary.ui.book;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.CommentVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter.RecomandLikeAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.CommentManager;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.CommentPicActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipManager;

import java.util.List;

/**
 * author:janecer on 2016/3/26 13:57
 * email:janecer@sina.cn
 */

public class BookDetailsActivity extends BaseFragmentActivity implements View.OnClickListener {

    private RecyclerView mRcvRecomand;//猜你喜欢

    private View mContent;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    private Long bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libarary_detail);
        bookId = getIntent().getLongExtra("bookId", 0);

        initRes();
        loadData();
    }

    LoadTipManager loadTipManager;

    private void initRes() {

        NavBarManager manager = new NavBarManager(this);

        //  mLtl = (LoadTipLayout) findViewById(R.id.ltl);
        mContent = findViewById(R.id.content);

        loadTipManager = new LoadTipManager(this, R.id.content, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        manager.txt_title.setText("图书简介");
        mRcvRecomand = (RecyclerView) findViewById(R.id.rcv_recomand);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcvRecomand.setLayoutManager(linearLayoutManager);
        recomandLikeAdapter = new RecomandLikeAdapter();
        mRcvRecomand.setAdapter(recomandLikeAdapter);

        recomandLikeAdapter.setOnItemContentClickListener(new RecomandLikeAdapter.OnItemContentClickListener() {
            @Override
            public void onItemContentClick(long bookId) {
                Intent intentBookDetail = new Intent(BookDetailsActivity.this, BookDetailsActivity.class);
                intentBookDetail.putExtra("bookId", bookId);
                startActivity(intentBookDetail);
            }
        });

        rl_detail = (RelativeLayout) findViewById(R.id.rl_detail);
        iv_book_ico = (ImageView) findViewById(R.id.iv_book_ico);
        tv_book_name = (TextView) findViewById(R.id.tv_book_name);
        tv_book_author = (TextView) findViewById(R.id.tv_book_author);
        tv_book_publish = (TextView) findViewById(R.id.tv_book_publish);
        rl_recomand = (RelativeLayout) findViewById(R.id.rl_recomand);
        tv_book_recomand = (TextView) findViewById(R.id.tv_book_recomand);
        rb_star = (RatingBar) findViewById(R.id.rb_star);
        layout_book_des = (LinearLayout) findViewById(R.id.layout_book_des);
        tv_location = (TextView) findViewById(R.id.tv_location);
        tv_book_collect = (TextView) findViewById(R.id.tv_book_collect);
        tv_book_order = (TextView) findViewById(R.id.tv_book_order);
        btn_strategy = (Button) findViewById(R.id.btn_strategy);
        tv_book_des = (TextView) findViewById(R.id.tv_book_des);
        tv_report_title = (TextView) findViewById(R.id.tv_report_title);
        tv_recommand = (TextView) findViewById(R.id.tv_recommand);
        iv_modification = (ImageView) findViewById(R.id.iv_modification);
        gvBookLabel = (GridView) findViewById(R.id.gv_lable);

        commentManager = new CommentManager(this, getWindow().getDecorView(),
                bookId + "", false);


        layout_book_des.setOnClickListener(this);
        tv_book_collect.setOnClickListener(this);
        tv_book_order.setOnClickListener(this);
        iv_modification.setOnClickListener(this);
        btn_strategy.setOnClickListener(this);
    }

    private RecomandLikeAdapter recomandLikeAdapter;
    private CommentManager commentManager;
    private RelativeLayout rl_detail;
    private ImageView iv_book_ico;
    private TextView tv_book_name;
    private TextView tv_book_author;
    private TextView tv_book_publish;
    private RelativeLayout rl_recomand;
    private TextView tv_book_recomand;
    private RatingBar rb_star;
    private LinearLayout layout_book_des;
    private TextView tv_location;
    private TextView tv_book_collect;
    private TextView tv_book_order;
    private Button btn_strategy;
    private TextView tv_book_des;
    private TextView tv_report_title;
    private TextView tv_recommand;
    private GridView gvBookLabel;


    private ImageView iv_modification;

    private BookResponseVo responseVo;

    private void initView(BookResponseVo responseVo) {

        this.responseVo = responseVo;
        BookVo bookVo = responseVo.getBookVo();// 图书列表显示

        CommentVo commentVo = responseVo.getCommentVo();// 评论
        commentManager.setData(commentVo);

        List<BookListVo> guessLikeArr = responseVo.getGuessLikeArr();

        tv_book_name.setText(bookVo.name);
        tv_book_author.setText(bookVo.author);

        CommonUtils.loadImage(iv_book_ico, bookVo.coverUrl);

        tv_book_publish.setText(bookVo.publishing);

//        rb_star.setMax(100);
//        rb_star.setNumStars(5);
        rb_star.setRating(bookVo.recommend / 20);

        tv_location.setText(bookVo.locationTags);

        tv_book_des.setText(bookVo.introduction);

        initCollect(bookVo.isCollect);

        recomandLikeAdapter.setmImgUrls(guessLikeArr);


        gvBookLabel.setAdapter(new CommonAdapter<BookLabelVo>(getApplicationContext(), bookVo.getBookLabelVoArr(), R.layout.gv_item_bookdetail_lable) {
            @Override
            protected void convert(ViewHolder vh, BookLabelVo item) {
                ((TextView) vh.getView(R.id.tv_book_lable)).setText(item.getName());
            }
        });

        setBookOrder(bookVo.getReserveNo(),bookVo.getStockNo());
    }

    private void initCollect(boolean bookVo) {
        if (bookVo) {
            tv_book_collect.setText("取消收藏");
            tv_book_collect.setTag(2);
            Drawable drawable = getResources().getDrawable(R.drawable.img_collection);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_book_collect.setCompoundDrawables(drawable, null, null, null);
            tv_book_collect.setTextColor(getResources().getColor(R.color.yellow_1));
        } else {
            tv_book_collect.setText("收藏");
            tv_book_collect.setTag(1);
            Drawable drawable = getResources().getDrawable(R.drawable.img_uncollection);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_book_collect.setCompoundDrawables(drawable, null, null, null);
            tv_book_collect.setTextColor(getResources().getColor(R.color.white));
        }
    }



    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        // TODO Auto-generated method stub
        loadData();
    }

    private void loadData() {

        CommonAppModel.getBook(bookId, TAG, new HttpResultListener<BookResponseVo>() {
            @Override
            public void onSuccess(BookResponseVo resp) {
                if (resp.isSuccess()) {

                    loadTipManager.showLoadSuccess();
                    mContent.setVisibility(View.VISIBLE);
                    iv_modification.setVisibility(View.VISIBLE);

                    initView(resp);


                } else {

                    loadTipManager.showLoadException();
                    mContent.setVisibility(View.GONE);
                    iv_modification.setVisibility(View.GONE);

                    ToastUtil.showMsg(resp.getMsg());
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

                loadTipManager.showLoadException();
                mContent.setVisibility(View.GONE);
                iv_modification.setVisibility(View.GONE);

                ToastUtil.showMsg(msg);
            }
        });
    }

    private void setBookOrder(int reserveNo,int stockNo){
        if (reserveNo > 0){
            Drawable drawable = getResources().getDrawable(R.drawable.img_order);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_book_order.setCompoundDrawables(drawable, null, null, null);
            tv_book_order.setTextColor(getResources().getColor(R.color.yellow_1));
            tv_book_order.setClickable(true);
        }else {
            if (stockNo == 0){
                Drawable drawable = getResources().getDrawable(R.drawable.order_disable);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv_book_order.setCompoundDrawables(drawable, null, null, null);
                tv_book_order.setTextColor(getResources().getColor(R.color.gray_4));
                tv_book_order.setClickable(false);
            }else {
                Drawable drawable = getResources().getDrawable(R.drawable.img_unorders);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv_book_order.setCompoundDrawables(drawable, null, null, null);
                tv_book_order.setTextColor(getResources().getColor(R.color.white));
                tv_book_order.setClickable(true);
            }


        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            finish();
        } else if (v.getId() == R.id.tv_book_order) {


            showLoaddingDialog();
            CommonAppModel.reserve(bookId, TAG, new HttpResultListener<ReserveResponseVo>() {
                @Override
                public void onSuccess(ReserveResponseVo resp) {
                    if (resp.isSuccess()) {//重新获取预借列表
                        setBookOrder(resp.getReserveNo(),resp.getStockNo());
                        ToastUtil.showMsg(resp.getMsg());
                    }

                    dismissLoaddingDialog();
                }

                @Override
                public void onFailed(Exception e, String msg) {
                    dismissLoaddingDialog();
                }
            });

        } else if (v.getId() == R.id.tv_book_collect) {
            String typeStr = tv_book_collect.getTag() + "";
            if (TextUtils.isEmpty(typeStr)) {
                ToastUtil.showMsg(getString(R.string.operate_fail));
                return;
            }
            final int type = Integer.parseInt(typeStr);

            showLoaddingDialog();
            CommonAppModel.collect(bookId, type, TAG, new HttpResultListener<BookResponseVo>() {
                @Override
                public void onSuccess(BookResponseVo resp) {
                    dismissLoaddingDialog();
                    if (resp.isSuccess()) {
                        initCollect(type == 1);
                    }
                    return;
                }

                @Override
                public void onFailed(Exception e, String msg) {
                    dismissLoaddingDialog();
                    ToastUtil.showMsg(msg);
                }
            });
        } else if (v.getId() == R.id.layout_book_des) {
            if (tv_book_des.getMaxLines() == 100) {
                tv_book_des.setMaxLines(3);
            } else {
                tv_book_des.setMaxLines(100);
            }
        } else if (v.getId() == R.id.iv_modification) {
            Intent i = new Intent(this, CommentPicActivity.class);
//                i.putExtra("type", type);
            i.putExtra("bookId", bookId);
            startActivityForResult(i, 1);
        } else if (v.getId() == R.id.btn_strategy) {
//            DialogComment dialogComment = new DialogComment(this);
//            dialogComment.show();
        }


    }


}
