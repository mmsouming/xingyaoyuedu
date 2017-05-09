package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet;


import android.content.pm.PackageManager;
import android.os.Build;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.util.GetImsi;
import com.fancyfamily.primarylibrary.commentlibrary.util.SharePrefUtil;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by janecer on 2016/3/9.
 * email:jxc@fancyf.cn
 * des: 构建所有的网络请求基础此类
 */
public class BaseReq {

    private static final String TAG = BaseReq.class.getSimpleName() ;

    public Long id	;//是	Long	是	评论id
    public String token ;
    public String platForm = "1"  ;
    public int  version;
    public String uniqueId ;
    public String phoneMark ;
    public String systemMark ;
    public long time ;

    public BaseReq(){
        try {
            this.version =  FFApplication.instance.getPackageManager().getPackageInfo(FFApplication.instance.getApplicationContext().getPackageName() , PackageManager.GET_CONFIGURATIONS).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        this.uniqueId = GetImsi.getImei(FFApplication.instance.getApplicationContext()) ;
        this.phoneMark = Build.MODEL ;
        this.systemMark = Build.VERSION.SDK ;
        this.time = System.currentTimeMillis() ;
        this.token = SharePrefUtil.getString(FFApplication.instance.getApplicationContext() , SharePrefUtil.KEY.function_token , "") ;
    }

    public HashMap<String,String> toHashMaps(){
        Class<?> clazz = this.getClass() ;
        Field[] fields = clazz.getFields() ;
        if(null != fields) {
            HashMap<String,String> maps = new HashMap<>() ;
            int length = fields.length ;
            Logger.msg(TAG , " length : " + length);
            for(int i = 0 ; i<length ; i++) {
                String key = fields[i].getName() ;
                String val = null ;
                try {
                   val = fields[i].get(this) + "";
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Logger.msg(TAG ,"key : " + key +"   val:" + val );
                if(!"null" .equals(val) ) {
                    maps.put(key , val) ;
                }
            }
            return maps ;
        }
        return null ;
    } ;
}
