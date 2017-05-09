package com.fancyfamily.primarylibrary.commentlibrary.ui.comment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.CommentTypeEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.lib.multi_image_selector.MultiImageSelectorActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter.PicChooseGridAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UploadPicUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.MeasureGridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CommentPicActivity extends BaseActivity
        implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pic_comment);
        initRes();

    }

    private Long bookId;
    private ArrayList<String> pics = new ArrayList<String>();
    private ArrayList<ImageInfo> data = new ArrayList<ImageInfo>();
    private EditText txt_comment;
    private MeasureGridView txt_pic;

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    PicChooseGridAdapter adapter;

    public void initRes() {
        bookId = getIntent().getLongExtra("bookId", 0);

        NavBarManager navBarManager = new NavBarManager(this);

        navBarManager.setRight("发送");
        txt_comment = (EditText) findViewById(R.id.txt_comment);
        txt_pic = (MeasureGridView) findViewById(
                R.id.txt_pic);
        pics.add("");
        adapter = new PicChooseGridAdapter(this, pics);
        txt_pic.setAdapter(adapter);
        uploadPicUtil = new UploadPicUtil(this);
        navBarManager.btn_right.setOnClickListener(this);
        txt_pic.setOnItemClickListener(ilistener);

    }


    private myOnItemClickListener ilistener = new myOnItemClickListener();

    private class myOnItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            // TODO Auto-generated method stub

            if (pics.get(arg2).equals("")) {
                onChoose(CommentPicActivity.this);
            } else {
                Intent intent = new Intent(CommentPicActivity.this,
                        PicBrowserActivity.class);

                ImageBDInfo bdInfo = new ImageBDInfo();
                int[] location = new int[2];
                View v = arg1.findViewById(R.id.camera_img);
                v.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                bdInfo.x = x;
                bdInfo.y = y;
                bdInfo.width = v.getWidth();
                bdInfo.height = v.getHeight();

                intent.putExtra("data", (Serializable) data);
                intent.putExtra("bdinfo", bdInfo);
                intent.putExtra("index", arg2);
                intent.putExtra("type", 3);
                startActivityForResult(intent, Edit_IMAGE);

            }

        }

    }


    UploadPicUtil uploadPicUtil;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v.getId() == R.id.btn_right) {
            String content = txt_comment.getText().toString().trim();
            if (content.equals("") && pics.size() == 1) {
                ToastUtil.showMsg("请输入评论");
                return;
            }
            if (pics.size() > 1) {
                uploadPicUtil.startUploadPic(pics, new HttpResultListener<HeadUploadResponseVo>() {
                    @Override
                    public void onSuccess(HeadUploadResponseVo resp) {
                        if (resp.isSuccess()) {
                            if (resp.pictureUrlArr != null && resp.pictureUrlArr.size() > 0) {
                                postComment(CommentPicActivity.this, resp.pictureUrlArr);
                            }
                        }
                    }
                    @Override
                    public void onFailed(Exception e, String msg) {
                    }
                });

            } else {
                uploadPicUtil.show();
                postComment(this, new ArrayList<String>());
            }
        }

    }

    private static final int Edit_IMAGE = 1;
    private static final int REQUEST_IMAGE = 2;

    public void onChoose(Activity context) {

        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT,
                10 - pics.size());
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                selectedMode);
        // 默认选择
        // if(mSelectPath != null && mSelectPath.size()>0){
        // intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
        // mSelectPath);
        // }
        startActivityForResult(intent, REQUEST_IMAGE);

    }

    private void setList() {
        data.clear();
        for (int i = 0; i < pics.size(); i++) {
            if (!pics.get(i).equals("")) {
                ImageInfo info = new ImageInfo();
                info.setUrlandType(pics.get(i), 0);
                data.add(info);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {

                ArrayList<String> list = data.getStringArrayListExtra(
                        MultiImageSelectorActivity.EXTRA_RESULT);

                pics.addAll(0, list);

                if (pics.size() > 9) {
                    pics.remove(pics.size() - 1);
                }
                setList();
                adapter.notifyDataSetChanged();


            }
        }

        if (requestCode == Edit_IMAGE && resultCode == RESULT_OK) {
            ArrayList<ImageInfo> ImageInfos = (ArrayList<ImageInfo>) data.getSerializableExtra("ImageInfos");
            if (ImageInfos != null) {
                pics.clear();
                for (int i = 0; i < ImageInfos.size(); i++) {
                    pics.add(ImageInfos.get(i).souceurl);
                }
                pics.add("");
                if (pics.size() > 9) {
                    pics.remove(pics.size() - 1);
                }
                setList();
                adapter.notifyDataSetChanged();
            }

        }

    }



    private void postComment(final Activity context, List<String> list) {
        String content = txt_comment.getText().toString().trim();
        CommonAppModel.postComment(bookId, CommentTypeEnum.BOOK.getNo(), list, content, "", new HttpResultListener<CommentResponseVo>() {
            @Override
            public void onSuccess(CommentResponseVo resp) {
                uploadPicUtil.finishUpload();
                if (resp.isSuccess()) {

                    finish();
                } else {

                    ToastUtil.showMsg(resp.getMsg());
                }

            }

            @Override
            public void onFailed(Exception e, String msg) {
                uploadPicUtil.finishUpload();
            }
        });

    }


}
