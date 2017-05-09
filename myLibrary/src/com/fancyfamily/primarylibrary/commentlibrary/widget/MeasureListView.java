
package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.content.Context;
 
import android.widget.ListView; 

public class MeasureListView extends ListView {

	public MeasureListView(Context context) {  
        super(context);  
    }  
  
    public MeasureListView(Context context, android.util.AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
       int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    } 

}
