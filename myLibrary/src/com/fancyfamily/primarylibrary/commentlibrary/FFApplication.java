package com.fancyfamily.primarylibrary.commentlibrary;

import android.app.Application;
import android.content.Context;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.OkHttpUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.Constants;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by janecer on 2016/3/8.
 */
public class FFApplication extends Application {

    public static FFApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        if(Constants.IS_DEBUG){
            //LeakCanary.install(this) ;//调试模式中，用来检测是否有内存泄露的情况
            //OkHttpUtils.getInstance().debug("testDebug");
        }

        OkHttpUtils.getInstance(new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).writeTimeout(30,TimeUnit.SECONDS).connectTimeout(30,TimeUnit.SECONDS).build());

        initImageLoader(getApplicationContext());

        initJpushConfig();
        this.instance = this ;

        JPushInterface.setDebugMode(Constants.IS_DEBUG); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化 JPush
    }


    public void initImageLoader(Context context) {

        DisplayImageOptions options = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY)
//                .showImageOnLoading(R.drawable.ic_stub)
//                .showImageForEmptyUri(R.drawable.ic_stub)
                .showImageOnFail(R.drawable.default_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(6))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(config);

    }

    /**
     * 极光推送组件初始化
     */
    public void initJpushConfig(){

        JPushInterface.setDebugMode(Constants.IS_DEBUG);
        JPushInterface.init(this);
    }
}
