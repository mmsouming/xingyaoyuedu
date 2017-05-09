package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author:janecer on 2016/3/27 10:35
 * email:janecer@sina.cn
 */

public class ScrollViewGridView extends GridView {

    public ScrollViewGridView(Context context) {
        super(context);
    }

    public ScrollViewGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
