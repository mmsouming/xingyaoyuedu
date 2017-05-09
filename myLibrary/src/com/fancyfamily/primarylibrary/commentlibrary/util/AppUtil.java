package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.fancyfamily.primarylibrary.R;


/**
 * author:janecer on 2016/4/6 20:34
 * email:janecer@sina.cn
 */

public class AppUtil {

    /**
     2  * 获取版本号
     3  * @return 当前应用的版本号
     4  */
     public static String getVersion(Context ctx) {
      try {
              PackageManager manager = ctx.getPackageManager();
              PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
              String version = info.versionName;
              return version;
           } catch (Exception e) {
                 e.printStackTrace();
                return ctx.getString(R.string.version_get_exception);
          }
     }


}
