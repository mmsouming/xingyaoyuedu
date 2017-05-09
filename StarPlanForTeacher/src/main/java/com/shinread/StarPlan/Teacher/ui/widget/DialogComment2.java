package com.shinread.StarPlan.Teacher.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkLevelEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkStatusEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.widget.CustomRadioButton;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.TWorkReq;
import com.shinyread.StarPlan.Teacher.R;


public class DialogComment2 extends Dialog implements
        View.OnClickListener {

    public interface OnDialogComment {
        public void ChooseResult(WorkVo work);
    }

    OnDialogComment listenser;

    public DialogComment2 setListenser(OnDialogComment listenser) {
        this.listenser = listenser;
        return this;
    }

    Activity mContext;

    private Long id;
    public DialogComment2(Activity context,Long id) {
        super(context, R.style.dialog);
        setContentView(R.layout.layout_comment_t);
        mContext = context;
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
        this.id = id;
        initRes();


    }


    private RadioGroup tbg_level;
    private CustomRadioButton rb_a;
    private  CustomRadioButton rb_b;
    private  CustomRadioButton rb_c;
    private  CustomRadioButton rb_d;
    private  CustomRadioButton rb_e;
    private  CustomRadioButton rb_back;
    private TextView btn_comfirm;
    private EditText book_comment;

    private void initRes() {
        tbg_level = (RadioGroup) findViewById(R.id.tbg_level);
        rb_a = ( CustomRadioButton) findViewById(R.id.rb_a);
        rb_b = ( CustomRadioButton) findViewById(R.id.rb_b);
        rb_c = ( CustomRadioButton) findViewById(R.id.rb_c);
        rb_d = ( CustomRadioButton) findViewById(R.id.rb_d);
        rb_e = ( CustomRadioButton) findViewById(R.id.rb_e);
        rb_back = ( CustomRadioButton) findViewById(R.id.rb_back);
        btn_comfirm = (TextView) findViewById(R.id.btn_comfirm);
        book_comment = (EditText) findViewById(R.id.book_comment);

        btn_comfirm.setOnClickListener(this);
        req = new TWorkReq();
        req.id = id;
        setRbId(R.id.rb_a);

        tbg_level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int rbId = group.getCheckedRadioButtonId();
                setRbId(rbId);
            }
        });
    }

    private void setRbId(int rbId){
        switch (rbId){
            case R.id.rb_a:
                req.workLevel = WorkLevelEnum.LEVEL_A.getNo();
                break;
            case R.id.rb_b:
                req.workLevel = WorkLevelEnum.LEVEL_B.getNo();
                break;
            case R.id.rb_c:
                req.workLevel = WorkLevelEnum.LEVEL_C.getNo();
                break;
            case R.id.rb_d:
                req.workLevel = WorkLevelEnum.LEVEL_D.getNo();
                break;
            case R.id.rb_e:
                req.workLevel = WorkLevelEnum.LEVEL_E.getNo();
                break;

        }
        if (rbId ==  R.id.rb_back) {
            req.workStatus = WorkStatusEnum.REPULSE.getNo();
        }else{
            req.workStatus = WorkStatusEnum.HAS_COMMENTED.getNo();
        }
    }
    TWorkReq req;
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v.getId() == R.id.btn_comfirm) {
            postComment();
        }

        dismiss();
    }

    private void postComment(){

        req.comment = book_comment.getText().toString().trim();

        AppModel.work(mContext, req, new HttpResultListener<WorkResponseVo>() {
            @Override
            public void onSuccess(WorkResponseVo resp) {

                if (resp.isSuccess()){
                    if (listenser != null) {
                        listenser.ChooseResult(resp.getWorkVo());
                    }
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }
}
