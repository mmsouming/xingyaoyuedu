
package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.RadioButton;

/**
 * 描述:带消息提醒的radiobutton
 * 
 * @author janecer
 * @since 2015-6-8 上午10:34:35
 */
public class CustomRadioButton extends RadioButton {

    private int radius = 10 ;

    private Paint paint ; //, paintText;

    private boolean firstCreate = true;

    private boolean showRedpoint = false;

    Rect t = new Rect();

    int height;

    int width;

    DisplayMetrics dm = getResources().getDisplayMetrics();

    float value = dm.scaledDensity;

    int textSize = 8;

    public CustomRadioButton(Context context) {
        this(context, null);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
    }

    private void setupPaint() {
        paint = new Paint();
        // 设置抗锯齿效果
        paint.setAntiAlias(true);
        // 设置画刷的颜色
        paint.setColor(Color.RED);

//        paintText = new Paint() ;
//        paintText.setAntiAlias(true);
//        paintText.setColor(Color.WHITE);
//        paintText.setTextSize(textSize);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(textSize * value);
        if (firstCreate) {
            canvas.getClipBounds(t);
            firstCreate = false;
            height = getHeight();
            width = getWidth();
        }

        if (showRedpoint) {
            Drawable[] drawables = getCompoundDrawables();
            Rect rect = new Rect();
            for (int i = 0; i < drawables.length; i++) {
                if (drawables[i] != null) {
                    rect = drawables[i].getBounds();
                    break;
                }
            }
            int offset = (getWidth() - rect.right) / 2;
            if(offset < radius) {
                offset = radius ;
            }

            int offsetTop =  (getHeight() - rect.bottom)/2 ;
            if(offsetTop < radius){
                offsetTop = radius ;
            }
            canvas.drawCircle(t.right - offset, t.top + offsetTop, radius, paint);
            //canvas.drawText("12" ,t.right - offset - radius, t.top + offsetTop, paintText);
        }

    }

    public void setShowRedPoint(boolean bool) {
        showRedpoint = bool;
        invalidate();
    }

    /**
     * 现在是否显示着红点
     * @return
     */
    public boolean redPointIsShowing() {
        return showRedpoint;
    }

}
