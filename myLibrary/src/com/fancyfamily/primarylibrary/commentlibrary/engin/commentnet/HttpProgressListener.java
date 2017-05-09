package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet;

/**
 * Created by janecer on 2016/3/11.
 * email:jxc@fancyf.cn
 * des:
 */
interface  HttpProgressListener<T extends BaseResponseVo> extends HttpResultListener {

    void onWaiting();

    void onStarted();

    void onLoading(long total, long current, boolean isDownloading);

}
