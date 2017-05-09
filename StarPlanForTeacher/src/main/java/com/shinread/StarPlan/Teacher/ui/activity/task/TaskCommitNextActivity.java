package com.shinread.StarPlan.Teacher.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.lib.multi_image_selector.MultiImageSelectorActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.CommonAdapter;
import com.fancyfamily.primarylibrary.commentlibrary.util.adapter.ViewHolder;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.shinread.StarPlan.Teacher.engin.net.AppModel;
import com.shinyread.StarPlan.Teacher.R;

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
    private ArrayList<String> mSelectPath;

    private GridView gvTasks ;
    private TitleBar mTb ;

    private EditText etComment ;

    private long workId ;

    private CommonAdapter<String> picTaskAdapter ;

    private DisplayImageOptions options ;
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

        options = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY)
                .showImageForEmptyUri(R.drawable.img_task_camera)
                .showImageOnFail(R.drawable.img_task_camera)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(6))
                .build();

        initViews() ;
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

        Logger.msg("  id : " + book.getId() + "   rating : " + book.getRecommend());

        ImageLoader.getInstance().displayImage(book.getCoverUrl(),ivBook);
        tvBook.setText(book.name);
        tvAuthor.setText(book.getAuthor());
        rbStar.setNumStars(5);
        rbStar.setMax(100);
        rbStar.setRating(book.getRecommend()) ;//book.getRecommend());
        tvDes.setText(book.getIntroduction());


        mSelectPath = new ArrayList<>() ;
        mSelectPath.add("");//表示最后一张为 选择图片按钮

        picTaskAdapter = new CommonAdapter<String>(this,mSelectPath,R.layout.gv_item_select_books) {
            @Override
            protected void convert(ViewHolder vh, String item) {
                ImageView ivBook = vh.getView(R.id.iv_book_ico) ;
                ImageLoader.getInstance().displayImage("file://"+item, ivBook,options);
            }
        } ;

        gvTasks.setAdapter(picTaskAdapter);
        gvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(TextUtils.isEmpty(mSelectPath.get(position))){
                    onChoose(TaskCommitNextActivity.this);
                } else {
                    Intent intent = new Intent(TaskCommitNextActivity.this,TaskPicShowsActivity.class) ;
                    mSelectPath.remove(mSelectPath.size()-1) ;
                    intent.putStringArrayListExtra("selectPics",mSelectPath) ;
                    intent.putExtra("currentIndex",position) ;
                    startActivityForResult(intent,REQ_PIC_DELETE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(
                        MultiImageSelectorActivity.EXTRA_RESULT);
                if (mSelectPath != null && mSelectPath.size() > 0) {
                    mSelectPath.add(mSelectPath.size()  ,"");
                    picTaskAdapter.setDataChange(mSelectPath);
                } else {
                    mSelectPath = new ArrayList<>() ;
                    mSelectPath.add("");//表示最后一张为 选择图片按钮
                    picTaskAdapter.setDataChange(mSelectPath);
                }
            }
        } else if(requestCode == REQ_PIC_DELETE){
            if(resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra("selectPics") ;
                mSelectPath.add(mSelectPath.size()  ,"");
                picTaskAdapter.setDataChange(mSelectPath);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send :
                if(TextUtils.isEmpty(etComment.getText().toString())){
                    ToastUtil.showMsg("请输入评语");
                    return ;
                }
                if(null == mSelectPath || mSelectPath.size() == 1) {
                    ToastUtil.showMsg("请选择需要上传的图片");
                    return ;
                }
                uploadIcos();
                break ;
        }
    }

    public void uploadIcos() {
        showLoaddingDialog();
        List<String> uploads = new ArrayList<>() ;
        for(String str : mSelectPath) {
            uploads.add(str) ;
        }
        uploads.remove(uploads.size() -1) ;
        AppModel.uploadHeadIcon(uploads, TAG, new HttpResultListener<HeadUploadResponseVo>() {
            @Override
            public void onSuccess(HeadUploadResponseVo resp) {
                if(resp.isSuccess()){
                    if(null == resp.pictureUrlArr || resp.pictureUrlArr.size() == 0 ) {
                        ToastUtil.showMsg(getString(R.string.operate_fail));
                        dismissLoaddingDialog();
                        return ;
                    }
                    commitTask(resp.pictureUrlArr);
                    return ;
                }
                ToastUtil.showMsg(resp.getMsg());
                dismissLoaddingDialog();
            }

            @Override
            public void onFailed(Exception e, String msg) {
                dismissLoaddingDialog();
                ToastUtil.showMsg(msg);
            }
        }) ;
    }

    public void commitTask(List<String> pics) {
        AppModel.commitWork(workId ,book.getId(),pics,etComment.getText().toString(),TAG, new HttpResultListener<WorkResponseVo>() {
            @Override
            public void onSuccess(WorkResponseVo resp) {
                dismissLoaddingDialog();
                ToastUtil.showMsg(resp.getMsg());
                if(resp.isSuccess()) {
                    WorkVo workVo = resp.getWorkVo() ;
                    Intent intent = new Intent(TaskCommitNextActivity.this,TaskCommitDetailActivity.class) ;
                    intent.putExtra("workId" ,workVo.getId()) ;
                    startActivity(intent);
                    return ;
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                ToastUtil.showMsg(msg);
                dismissLoaddingDialog();
            }
        }) ;
    }

    public void onChoose(BaseActivity context) {

        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
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
}
