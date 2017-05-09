package com.shinread.StarPlan.Teacher.ui.widget;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shinyread.StarPlan.Teacher.R;

import java.util.List;

/**
 * Created by lizehong on 2016/5/9.
 */
public class AddLayout {

    public void addLayout( Activity context,View view, List<String> class_name_string) {
        LinearLayout   groupView = (LinearLayout)view.findViewById(R.id.pers_photo_layout);


        if (class_name_string.size() > 0) {
            //tv_class_name.setVisibility(View.GONE);
            TextView tx;
            groupView.removeAllViews();
            for (int i = 0; i < class_name_string.size(); i++) {
                // 每行都有一个linearlayout
                tx = new TextView(context);
                LinearLayout lLayout = new LinearLayout(context);
                lLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams lLayoutlayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lLayout.setLayoutParams(lLayoutlayoutParams);
                tx.setLayoutParams(lLayoutlayoutParams); // image的布局方式
                tx.setPadding(10, 2, 4, 2);
                String txtname = class_name_string.get(i);
                tx.setText(txtname);
                groupView.addView(tx);
            }
           // img_add.setVisibility(View.VISIBLE);
        } else if (class_name_string.size() == 0) {
//            img_add.setVisibility(View.GONE);
//            tv_class_name.setVisibility(View.VISIBLE);
            groupView.removeAllViews();
        }
    }

}
