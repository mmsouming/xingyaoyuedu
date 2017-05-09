package com.fancyfamily.primarylibrary.commentlibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelSortVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookLabelVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GenericListVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateInterestTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.adapter.AllTagsAdapter;

import java.util.ArrayList;
import java.util.List;


public class DialogRecomandTagsPicker extends Dialog implements
        View.OnClickListener {

    public interface OnDialogRecomandTagsPicker {
        void ChooseResult(List<GenericListVo> mInterestTags);
    }

    OnDialogRecomandTagsPicker listenser;

    public DialogRecomandTagsPicker setListenser(OnDialogRecomandTagsPicker listenser) {
        this.listenser = listenser;
        return this;
    }
    private AllTagsAdapter allTagsAdapter;//感兴趣标签集合适配
    Activity mContext;

    List<GenericListVo> mInterestTags = new ArrayList<>();
    List<BookLabelSortVo> allTags;
    ListView lvTags;
    public DialogRecomandTagsPicker(Activity context, List<GenericListVo> interestTags ) {
        super(context, R.style.customDialog);
        setContentView(R.layout.dialog_recomand_interest_select);
        mContext = context;
        if (interestTags != null){
            mInterestTags = interestTags;
        }

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        window.setGravity(Gravity.CENTER);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lp.width = (int) (width * 0.9);
        lp.height = height / 2;


        lvTags= (ListView)findViewById(R.id.lvs);
        allTagsAdapter = new AllTagsAdapter(mContext);

        lvTags.setAdapter(allTagsAdapter);
        findViewById(R.id.btn_sure).setOnClickListener(this);

        loadTags();

        setCancelable(true);
    }

    public void loadTags() {
        CommonAppModel.getLibraryTag("", new HttpResultListener<GetTagResp>() {
            @Override
            public void onSuccess(GetTagResp resp) {
                if (resp.isSuccess()) {
                    allTags = resp.bookLabelSortVoArr;

                    for (int k = 0;k<mInterestTags.size();k++) {

                        GenericListVo gvo = mInterestTags.get(k);

                        for (int i = 0; i < allTags.size(); i++) {
                            BookLabelSortVo obj = allTags.get(i);
                            List<BookLabelVo> bookLabelVoArr = obj.getBookLabelVoArr();
                            for (int j = 0; j < bookLabelVoArr.size(); j++) {
                                BookLabelVo vo = bookLabelVoArr.get(j);
                                if (vo.getId() == gvo.getId()) {
                                    vo.isSelect = true;
                                }
                            }
                        }
                    }

                    allTagsAdapter.setObjects(allTags);
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
            }
        });
    }


    public void updateInterestTag() {

        CommonAppModel.updateMyInterestTag(mContext, allTagsAdapter.getSelectList(), new HttpResultListener<UpdateInterestTagResp>() {
            @Override
            public void onSuccess(UpdateInterestTagResp resp) {
                if (resp.isSuccess()) {
                    listenser.ChooseResult(resp.bookLabelVoArr);
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v.getId() == R.id.btn_sure) {

            if (listenser != null) {
                updateInterestTag();
            }
        }

        dismiss();
    }


}
