package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloud-pc on 2016/4/28.
 */
public class UploadPicUtil {

    public UploadPicUtil(Activity context) {
        mContext = context;

    }

    HttpResultListener<HeadUploadResponseVo> resultListener;
    private Dialog mDialog;
    Activity mContext;
    public static final String fileDir = "smallpic";

    public void startUploadPic(List<String> pics, HttpResultListener<HeadUploadResponseVo> resultListener) {
        if (pics == null || pics.size() == 0){
            resultListener.onFailed(new Exception(),"");
        }

        this.resultListener = resultListener;
        show();
        MyTask task = new MyTask();
        task.execute(pics);
    }

    public void show() {
        if (mDialog == null) {
            mDialog = DialogUtil.creatRequestDialog(mContext,
                    "正在发送");
        }
        mDialog.show();
    }

    public void finishUpload() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    private class MyTask extends AsyncTask<List<String>, Integer, List<String>> {
        // onPreExecute方法用于在执行后台任务前做一些UI操作


        @Override
        protected void onPreExecute() {

        }


        // doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected List<String> doInBackground(List<String>... params) {
            List<String> list = params[0];
            List<String> temps = new ArrayList<String>();
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).equals("")) {
                    FileCache cache = new FileCache(mContext,
                            fileDir);
                    File temp = cache.saveBitmapFile(list.get(i));
                    temps.add(temp.getAbsolutePath());
                }

            }
            //  pics.addAll(0, temps);

            return temps;
        }

        // onProgressUpdate方法用于更新进度信息
        @Override
        protected void onProgressUpdate(Integer... progresses) {

        }

        // onPostExecute方法用于在执行完后台任务后更新UI,显示结果
        @Override
        protected void onPostExecute(List<String> list) {

            uploadPicture(mContext, list);
        }

        // onCancelled方法用于在取消执行中的任务时更改UI
        @Override
        protected void onCancelled() {

        }
    }

    private void uploadPicture(final Activity context, List<String> piclist) {

        final FileCache cache = new FileCache(mContext,
                fileDir);

        CommonAppModel.uploadHeadIcon(piclist, "", new HttpResultListener<HeadUploadResponseVo>() {
            @Override
            public void onSuccess(HeadUploadResponseVo resp) {
                if (resp.isSuccess()) {
                    cache.clear();
                } else {
                    finishUpload();
                    ToastUtil.showMsg(resp.getMsg());
                }
                resultListener.onSuccess(resp);
            }

            @Override
            public void onFailed(Exception e, String msg) {
                finishUpload();
                resultListener.onFailed(e, msg);
            }
        });


    }
}
