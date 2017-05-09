package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.util.DimensionUtil;


/**
 * author:janecer on 2016/2/11 12:22
 * email:janecer@sina.cn
 *
 * 通用标题栏 ，
 * 标题栏左边 TextView与ImageView
 * 中间一个TextView
 * 右边一个TextView与一个ImageView(只显示一个隐藏一个 textView设置为显示则自动隐藏ImageView)
 */

public class TitleBar extends RelativeLayout {


    /* 确保为相关控件的初始化只执行一次 */
    private boolean onLayoutFlag = false ;

    /* 标题栏左边的提示语 */
    private TextView mTvLeftTitle ;
    /* 标题栏中间显示的标题 */
    private TextView mTvTitle ;
    /* 标题栏右边的提示语 */
    private TextView mTvRightTitle ;
    /* 标题栏左边的图标 */
    private ImageView mIvLeftNav ;
    /* 标题栏右边的图标 */
    private ImageView mIvRightNav ;

    /* 控制相关标题和图标是否显示 */
    private boolean mTvLeftIsShow = true ;
    private boolean mIvLeftIsShow = true ;
    private boolean mTvRightIsShow = false ;

    /* 三个个标题栏显示的值 */
    private String mTvLeftTitleStr , mTvTitleStr ,mTvRightTitleStr ;

    /* 三个标题栏显示的字体颜色*/
    private int mTextColor  , mTitleRightTextColor;
    private int mTitle_text_size;
    /* 二个图标的res路径 */
    private int mLeftDrawble ,mRighDrawble ;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TitleBar,defStyleAttr,0) ;
        mTvLeftIsShow = typedArray.getBoolean(R.styleable.TitleBar_title_left_visible, true) ;
        mIvLeftIsShow = typedArray.getBoolean(R.styleable.TitleBar_title_left_icon_visible, true) ;
        mTvRightIsShow = typedArray.getBoolean(R.styleable.TitleBar_title_right_visible, false) ;

        mTvLeftTitleStr = typedArray.getString(R.styleable.TitleBar_title_left_text) ;
        mTvTitleStr = typedArray.getString(R.styleable.TitleBar_title_text) ;
        mTvRightTitleStr = typedArray.getString(R.styleable.TitleBar_title_right_text) ;
        mLeftDrawble = typedArray.getResourceId(R.styleable.TitleBar_title_left_icon_src, R.mipmap.ic_launcher) ;
        mRighDrawble = typedArray.getResourceId(R.styleable.TitleBar_title_right_icon_src, R.mipmap.ic_launcher) ;
        mTextColor = typedArray.getColor(R.styleable.TitleBar_title_text_color, getResources().getColor(android.R.color.white)) ;
        mTitle_text_size = typedArray.getColor(R.styleable.TitleBar_title_text_size, DimensionUtil.dip2px(getContext(),5) ) ;
        mTitleRightTextColor = typedArray.getColor(R.styleable.TitleBar_title_right_text_color, getResources().getColor(android.R.color.white)) ;
        typedArray.recycle();

        mTvLeftTitle = new TextView(getContext()) ;
        mIvLeftNav = new ImageView(getContext()) ;
        mTvTitle = new TextView(getContext()) ;
        mIvRightNav = new ImageView(getContext()) ;
        mTvRightTitle = new TextView(getContext()) ;

        mIvLeftNav.setScaleType(ImageView.ScaleType.CENTER);
        mIvRightNav.setScaleType(ImageView.ScaleType.CENTER);
        mIvLeftNav.setId(1);
        RelativeLayout.LayoutParams rlp_title = new RelativeLayout.LayoutParams(DimensionUtil.dip2px(getContext(),39), DimensionUtil.dip2px(getContext(),39)) ;

        rlp_title.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlp_title.addRule(RelativeLayout.CENTER_VERTICAL);
        this.addView(mIvLeftNav, rlp_title);
        mIvLeftNav.getLayoutParams().height = DimensionUtil.dip2px(getContext(),36) ;
        mIvLeftNav.getLayoutParams().width = DimensionUtil.dip2px(getContext(),36) ;
        mIvLeftNav.setLayoutParams(rlp_title);


        rlp_title = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT) ;
        rlp_title.addRule(RelativeLayout.CENTER_VERTICAL);
        rlp_title.addRule(RelativeLayout.RIGHT_OF, mIvLeftNav.getId());
        this.addView(mTvLeftTitle, rlp_title);

        rlp_title = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT) ;
        rlp_title.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addView(mTvTitle, rlp_title);

        rlp_title = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT) ;
        rlp_title.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp_title.addRule(RelativeLayout.CENTER_VERTICAL);
        this.addView(mTvRightTitle, rlp_title);

        this.addView(mIvRightNav, rlp_title);

        mTvLeftTitle.setGravity(Gravity.CENTER);
        mTvTitle.setGravity(Gravity.CENTER);
        mTvRightTitle.setGravity(Gravity.CENTER);

        mTvLeftTitle.setVisibility(mTvLeftIsShow ? View.VISIBLE : View.INVISIBLE);
        mIvLeftNav.setVisibility(mIvLeftIsShow ? View.VISIBLE : View.INVISIBLE);
        mTvRightTitle.setVisibility(mTvRightIsShow ? View.VISIBLE : View.INVISIBLE);
        mIvRightNav.setVisibility(mTvRightIsShow ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if(!onLayoutFlag){
            float textSize = (getMeasuredHeight()*1)/(getResources().getDisplayMetrics().density*3) ;

            mTvLeftTitle.setTextSize(textSize);
            mTvTitle.setTextSize(textSize);
            mTvRightTitle.setTextSize(textSize);

            mTvLeftTitle.setTextColor(mTextColor);
            mTvTitle.setTextColor(mTextColor);
            mTvRightTitle.setTextColor(mTitleRightTextColor);

            mTvLeftTitle.setText(mTvLeftTitleStr);
            mTvTitle.setText(mTvTitleStr);
            mTvRightTitle.setText(mTvRightTitleStr);

            mIvLeftNav.setImageResource(mLeftDrawble);
            mIvRightNav.setImageResource(mRighDrawble);

            mIvLeftNav.setPadding((int) textSize, 0, (int) textSize / 3, 0) ;
            mTvRightTitle.setPadding((int)textSize/3 , 0, (int) textSize,0) ;
            mIvRightNav.setPadding((int)textSize/3 , 0, (int) textSize,0) ;

            onLayoutFlag = true  ;
        }
    }

    /**
     * 设置左边nav的点击事件
     * @param onclicListener
     */
    public void setOnLeftNavClickListener(View.OnClickListener onclicListener){
        mIvLeftNav.setOnClickListener(onclicListener);
        mTvLeftTitle.setOnClickListener(onclicListener);
    }

    /**
     * 设置右边nav的点击事件
     * @param onclickListener
     */
    public void setOnRightNavClickListener(View.OnClickListener onclickListener){
        mTvRightTitle.setOnClickListener(onclickListener);
        mIvRightNav.setOnClickListener(onclickListener);
    }

    public void setTitleText(String title){

        if(null == title ){
            return ;
        }
        mTvTitleStr = title ;

        mTvTitle.setText(title);
    }

    public TextView getmTvLeftTitle(){
        return mTvLeftTitle ;
    }
    public ImageView getmIvLeftNav() {
        return mIvLeftNav ;
    }

    public TextView getmTvTitle(){
        return mTvTitle ;
    }
}
