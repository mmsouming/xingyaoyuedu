package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet;

import android.app.Dialog;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.OkHttpUtils;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.builder.PostFormBuilder;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.callback.StringCallback;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.request.RequestCall;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.google.gson.Gson;
import com.fancyfamily.primarylibrary.commentlibrary.util.NetworkStatUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;


public class FFOkHttpHelper {

    public static final String GET = "get";
    public static final String POST = "post";
    private static Gson mGson = new Gson() ;
    public static <Req extends BaseReq, Resp extends BaseResponseVo> RequestCall get(final String url, String requestTag , Req req, final Class<Resp> respClass , final HttpResultListener<Resp> resultListener) {
        return connect(url,GET,requestTag,req,respClass,resultListener) ;
    }

    public static <Req extends BaseReq, Resp extends BaseResponseVo> RequestCall post(final String url, String requestTag , Req req, final Class<Resp> respClass , final HttpResultListener<Resp> resultListener) {
        return  connect(url,POST,requestTag,req,respClass,resultListener) ;
    } ;

    public static <Req extends BaseReq, Resp extends BaseResponseVo> RequestCall post( String url , Req req, final Class<Resp> respClass , final HttpResultListener<Resp> resultListener,final Dialog dialog) {
        if (dialog!= null){
            dialog.show();
        }
        return connect(url,POST,"", req, respClass, new HttpResultListener<Resp>() {
            @Override
            public void onSuccess(Resp resp) {
                if (dialog!= null){
                    dialog.dismiss();
                }
                resultListener.onSuccess(resp);
            }

            @Override
            public void onFailed(Exception e, String msg) {
                if (dialog!= null){
                    dialog.dismiss();
                }
                resultListener.onFailed(e,msg);
            }
        });
    }


    public static <Req extends BaseReq, Resp extends BaseResponseVo> RequestCall connect(final String url,String methed,String requestTag , Req req, final Class<Resp> respClass , final HttpResultListener<Resp> resultListener) {
        if(!NetworkStatUtils.isNetworkAvailable(FFApplication.instance.getApplicationContext())){
            resultListener.onFailed(null ,FFApplication.instance.getString(R.string.networkerror));
            return null;
        }
        String path = CommonUrlManager.getCommonUrl(url);
        Map<String ,String> params = req.toHashMaps() ;
        Logger.msg("ffnet",Logger.V,"post  request  " + path +"  params : "  + params.toString());
        RequestCall call;
        if (methed.equals(GET)){
            call =  OkHttpUtils
                    .get()
                    .url(path).params(params)
                    //.tag(requestTag) //用来取消指定的网络请求
                    .build() ;
        }else{
            call =  OkHttpUtils
                    .post()
                    .url(path).params(params)
                    //.tag(requestTag) //用来取消指定的网络请求
                    .build() ;
        }

        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Logger.msg("ffnet",Logger.E,url +"onError  :\n " + e.toString());
                String msg = CommonUtils.parseException2String(e);
                ToastUtil.showMsg("网络不给力");
                resultListener.onFailed(e , msg);
            }

            @Override
            public void onResponse(String response) {

                Logger.msg("ffnet",Logger.V,url+" onresponse :\n " + response);
                Resp result = mGson.fromJson(response , respClass) ;
                if (!result.isSuccess()){
                    ToastUtil.showMsg(result.getMsg());
                }
                resultListener.onSuccess(result);
            }
        });
        return call ;
    } ;

    public static <Req extends BaseReq, Resp extends BaseResponseVo> RequestCall postFile(final String url, String requestTag , Map<String ,List<String>> pics , Req req, final Class<Resp> respClass , final HttpResultListener<Resp> resultListener) {
        if(!NetworkStatUtils.isNetworkAvailable(FFApplication.instance.getApplicationContext())){
            resultListener.onFailed(null ,FFApplication.instance.getString(R.string.networkerror));
            return null;
        }
        String path = CommonUrlManager.getCommonUrl(url);
        Map<String ,String> params = req.toHashMaps() ;
        Logger.msg("ffnet" ,"request  " + path +"  params : "  + params.toString());
        PostFormBuilder builder =  OkHttpUtils.post();
        builder.params(params);

        Set<String> keys =pics.keySet();


        List<String> list = null;
        int length  = 0 ;
        for(String key : keys) {
            list = pics.get(key) ;
            length = list.size() ;
            for(int j = 0 ; j < length; j++){
                File  files = new File(list.get(j)) ;
                if(files.exists()) {
                    builder.addFile(key, list.get(j), files);
                }
            }
        }
        RequestCall call = builder .url(path)
                .tag(requestTag) //用来取消指定的网络请求
                .build() ;
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Logger.msg("onError ; " + e.toString());
                String msg = CommonUtils.parseException2String(e);
                resultListener.onFailed(e , msg);
            }

            @Override
            public void onResponse(String response) {
                Logger.msg("onresponse ; " + response);
                Resp result = mGson.fromJson(response , respClass) ;
                resultListener.onSuccess(result);
            }
        });
        return call;
    } ;


}
