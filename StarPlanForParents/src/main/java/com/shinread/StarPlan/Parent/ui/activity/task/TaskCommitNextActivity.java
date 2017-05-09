package com.shinread.StarPlan.Parent.ui.activity.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.BookListVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.lib.multi_image_selector.MultiImageSelectorActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.ImageBDInfo;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.ImageInfo;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.PicBrowserActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.comment.adapter.PicChooseGridAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.UploadPicUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.shinyread.StarPlan.Parent.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by janecer on 2016/4/9.
 * email:jxc@fancyf.cn
 * des:
 */
public class TaskCommitNextActivity extends BaseFragmentActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE = BASE_CODE + 1 ;
    private static final int REQ_PIC_DELETE = REQUEST_IMAGE + 1;


    private BookListVo book;

    private GridView gvTasks ;
    private TitleBar mTb ;

    private EditText etComment ;

    private long workId ;

    private ArrayList<String> pics = new ArrayList<String>();
    private ArrayList<ImageInfo> data = new ArrayList<ImageInfo>();
    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_commit_next);
        workId = getIntent().getLongExtra("workId", 0) ;
        book = getIntent().getParcelableExtra("book") ;
        if(null == book) {
            ToastUtil.showMsg(getString(R.string.operate_fail));
            finish();
            return ;
        }


        initViews() ;
    }

    private myOnItemClickListener ilistener = new myOnItemClickListener();

    private class myOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            // TODO Auto-generated method stub

            if (pics.get(arg2).equals("")) {
                onChoose(TaskCommitNextActivity.this);
            } else {
                Intent intent = new Intent(TaskCommitNextActivity.this,
                        PicBrowserActivity.class);

                ImageBDInfo bdInfo = new ImageBDInfo();
                int[] location = new int[2];
                View v = arg1.findViewById(com.fancyfamily.primarylibrary.R.id.camera_img);
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
                startActivityForResult(intent, REQ_PIC_DELETE);

            }

        }

    }
    private void initViews() {
        mTb = (TitleBar) findViewById(R.id.tb) ;
        mTb.setOnLeftNavClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etComment = (EditText) findViewById(R.id.et_comment) ;
        findViewById(R.id.btn_send).setOnClickListener(this);
        gvTasks = (GridView) findViewById(R.id.gv_comments);
        ImageView ivBook = (ImageView) findViewById(R.id.iv_book);
        TextView tvBook = (TextView) findViewById(R.id.tv_book);
        TextView tvAuthor = (TextView) findViewById(R.id.tv_author);
        RatingBar rbStar = (RatingBar) findViewById(R.id.rb_star);
        TextView tvDes = (TextView) findViewById(R.id.tv_des);
        CommonUtils.loadImage(ivBook,book.getCoverUrl());
        tvBook.setText(book.name);
        tvAuthor.setText(book.getAuthor());
        rbStar.setRating(book.getRecommend()/20) ;//book.getRecommend());
        tvDes.setText(book.getIntroduction());
        uploadPicUtil = new UploadPicUtil(this);
        pics.add("");
        adapter = new PicChooseGridAdapter(this, pics);
        gvTasks.setAdapter(adapter);

        gvTasks.setOnItemClickListener(ilistener);
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

        if (requestCode == REQ_PIC_DELETE && resultCode == RESULT_OK) {
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

    UploadPicUtil uploadPicUtil;
    PicChooseGridAdapter adapter;

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_send) {
            String content = etComment.getText().toString().trim();
            if (content.equals("") && pics.size() == 1) {
                ToastUtil.showMsg("请说点什么");
                return;
            }


            if (pics.size() > 1) {

                uploadPicUtil.startUploadPic(pics, new HttpResultListener<HeadUploadResponseVo>() {
                    @Override
                    public void onSuccess(HeadUploadResponseVo resp) {
                        if (resp.isSuccess()) {
                            if (resp.pictureUrlArr != null && resp.pictureUrlArr.size() > 0) {
                                commitTask(resp.pictureUrlArr);
                            }
                        }
                    }

                    @Override
                    public void onFailed(Exception e, String msg) {
                        commitTask(new ArrayList<String>());
                    }
                });

            } else {

                uploadPicUtil.show();
                commitTask(new ArrayList<String>());
            }
        }

    }


    public void commitTask(List<String> pics) {
        AppModel.commitWork(workId ,book.getId(),pics,etComment.getText().toString().trim(),TAG, new HttpResultListener<WorkResponseVo>() {
            @Override
            public void onSuccess(WorkResponseVo resp) {
                uploadPicUtil.finishUpload();
                if(resp.isSuccess()) {
                    WorkVo workVo = resp.getWorkVo() ;
                    Intent intent = new Intent(TaskCommitNextActivity.this,TaskCommitDetailActivity.class) ;
                    intent.putExtra("workId" ,workVo.getId()) ;
                    startActivity(intent);
                    finish();
                }else{
                    ToastUtil.showMsg(resp.getMsg());
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                uploadPicUtil.finishUpload();
            }
        }) ;
    }

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

        startActivityForResult(intent, REQUEST_IMAGE);

    }
}
