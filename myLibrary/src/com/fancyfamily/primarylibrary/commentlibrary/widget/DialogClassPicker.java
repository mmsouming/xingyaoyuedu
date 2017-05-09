package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GradeVo;
import com.fancyfamily.primarylibrary.commentlibrary.lib.wheelview.OnWheelScrollListener;
import com.fancyfamily.primarylibrary.commentlibrary.lib.wheelview.WheelView;
import com.fancyfamily.primarylibrary.commentlibrary.lib.wheelview.adapter.AbstractWheelTextAdapter;

import java.util.ArrayList;
import java.util.List;


public class DialogClassPicker extends Dialog implements
        View.OnClickListener {

    public interface OnDialogClassPicker {
        public void ChooseResult(GradeVo gradeVo, GenericListVo classVo);
    }

    OnDialogClassPicker listenser;

    public DialogClassPicker setListenser(OnDialogClassPicker listenser) {
        this.listenser = listenser;
        return this;
    }

    Activity mContext;
    TextView btn_cancel;
    TextView btn_sure;

    WheelView wv_1, wv_2;
    List<GradeVo> mGradeVos;

    public DialogClassPicker(Activity context, List<GradeVo> gradeVos) {
        super(context, R.style.customDialog);
        setContentView(R.layout.cc_dialog_wheel_picker);
        mContext = context;
        mGradeVos = gradeVos;
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        window.setGravity(Gravity.BOTTOM);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lp.width = width;
        //lp.height =  height;
        window.setWindowAnimations(R.style.dialogWindowAnim);
        wv_1 = (WheelView) findViewById(R.id.wv_1);
        wv_1.setVisibility(View.VISIBLE);
        wv_2 = (WheelView) findViewById(R.id.wv_2);
        wv_2.setVisibility(View.VISIBLE);
        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        btn_sure = (TextView) findViewById(R.id.btn_sure);
        btn_cancel.setOnClickListener(this);
        btn_sure.setOnClickListener(this);

        wv_1.setViewAdapter(new LevelAdapter(mContext, mGradeVos));
        wv_1.setCyclic(false);//是否可循环滑动
        wv_1.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                currentGrade = mGradeVos.get(wheel.getCurrentItem());
                List<GenericListVo> classList = currentGrade.getClassesVoArr();
                if (classList.size() > 0) {
                    currentClass = classList.get(0);
                }
                wv_2.setViewAdapter(new ClassAdapter(mContext, currentGrade.getClassesVoArr()));
            }
        });


        if (mGradeVos.size() > 0) {
            currentGrade = mGradeVos.get(0);
            if (currentGrade.getClassesVoArr().size() > 0) {
                currentClass = currentGrade.getClassesVoArr().get(0);
            }
            wv_2.setViewAdapter(new ClassAdapter(mContext, currentGrade.getClassesVoArr()));
        } else {
            wv_2.setViewAdapter(new ClassAdapter(mContext, null));
        }

        wv_2.setCyclic(false);
        wv_2.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {

                if (currentGrade != null) {
                    currentClass = currentGrade.getClassesVoArr().get(wheel.getCurrentItem());
                }
            }
        });
        setCancelable(true);

    }

    private GradeVo currentGrade;
    private GenericListVo currentClass;

    class LevelAdapter extends AbstractWheelTextAdapter {

        private List<GradeVo> gradeVos;

        /**
         * Constructor
         *
         * @param context the current context
         */
        public LevelAdapter(Context context, List<GradeVo> gradeVos) {
            super(context);

            if (gradeVos == null) {
                this.gradeVos = new ArrayList<GradeVo>();
            } else {
                this.gradeVos = gradeVos;
            }


        }

        @Override
        protected CharSequence getItemText(int index) {
            GradeVo vo = gradeVos.get(index);
            return vo.getName();
        }

        @Override
        public int getItemsCount() {
            return gradeVos.size();
        }
    }

    class ClassAdapter extends AbstractWheelTextAdapter {

        private List<GenericListVo> classesVoArr;

        /**
         * Constructor
         *
         * @param context the current context
         */
        public ClassAdapter(Context context, List<GenericListVo> classesVoArr) {
            super(context);

            if (classesVoArr == null) {
                this.classesVoArr = new ArrayList<GenericListVo>();
            } else {
                this.classesVoArr = classesVoArr;
            }

        }

        @Override
        protected CharSequence getItemText(int index) {
            GenericListVo vo = classesVoArr.get(index);
            return vo.getName();
        }

        @Override
        public int getItemsCount() {
            return classesVoArr.size();
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v.getId() == R.id.btn_sure) {

            if (listenser != null) {
                listenser.ChooseResult(currentGrade, currentClass);
            }
        }

        dismiss();
    }


}
