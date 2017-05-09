package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by janecer on 2016/3/17.
 * email:jxc@fancyf.cn
 * des:
 */
public class CommonUtils {

    /**
     * 设置webVIew，在loadUrl之前调用
     * @param content
     * @param pay_web
     */
    public static void setWebView(Activity content ,WebView pay_web) {
        pay_web.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        pay_web.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        // pay_web.getSettings().setBuiltInZoomControls(true);
        // 扩大比例的缩放
        pay_web.getSettings().setUseWideViewPort(true);
        // pay_web.getSettings().setLoadWithOverviewMode(true);
        // 自适应屏幕
        pay_web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // pay_web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        pay_web.getSettings().setLoadWithOverviewMode(true);

        pay_web.setVerticalScrollBarEnabled(false);
        pay_web.setVerticalScrollbarOverlay(false);
        pay_web.setHorizontalScrollBarEnabled(false);
        pay_web.setHorizontalScrollbarOverlay(false);

        pay_web.setFocusable(false);
//        pay_web.addJavascriptInterface(new WebInterface(content),
//                WebInterface.Tag);
        String storePath = Environment.getExternalStorageDirectory().getPath()
                + "/fancyfamily/";
        pay_web.getSettings().setAppCachePath(storePath);
        pay_web.getSettings().setAppCacheEnabled(true);
        pay_web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        pay_web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();
            }
        });

    }
    /**
     * 校验手机号是否正确
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(14[6-7])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 解析异常
     * @param e
     * @return
     */
    public static String parseException2String(Exception e) {
        String msg = FFApplication.instance.getString(R.string.networkerror) ;
        if(e instanceof SocketTimeoutException){
            msg = "网络连接超时" ;
        }
        return msg;
    } ;

    /**
     * 0-255
     * @param context
     * @param brightness
     */
    public static  void setWindowBrightness(Activity context, int brightness) {
        Window window = context.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightness / 255.0f;
        window.setAttributes(lp);
    }

    /**
     * 设置dialog从window下面显示出来
     * @param parameDialog
     */
    public static void setDialogWindowAttr(Window parameDialog){
        parameDialog.setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams lp = parameDialog.getAttributes();

        lp.width= DimensionUtil.getWidth(FFApplication.instance.getApplicationContext());

        parameDialog.setAttributes(lp);

        parameDialog.setWindowAnimations(R.style.dialogWindowAnim);
    }



    public static void Mylog(String tag, String msg) {
        if (Constants.IS_DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void Tlog(String tag, String msg) {
        // if (null != msg && !msg.equals(""))
        if (Constants.IS_DEBUG) {
            Log.i(tag, msg);
        }

    }


    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }



    public static void ToastError(Context context) {
        // Toast.makeText(context, "(～￣▽￣)～网络不给力，请稍后再试", 300).show();
    }

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };
    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

    public static void ToastError(Activity context, String msg) {
        if (context == null) {
            return;
        }
//        LayoutInflater inflater = context.getLayoutInflater();
//        View toastView = inflater.inflate(R.layout.ff_toast, null);
//
//        TextView toast_txt_title = (TextView) toastView
//                .findViewById(R.id.toast_txt_title);
//        toast_txt_title.setText(msg);
//        LinearLayout toast_txt_layout = (LinearLayout) toastView
//                .findViewById(R.id.toast_txt_layout);
//        toast_txt_layout.setBackgroundResource(R.drawable.shape_toast_orange);
//        Toast toast = new Toast(context);
//
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(toastView);
//        toast.show();
        showToast(context, msg, 2000);
    }




    public static void ToastSuccess(Activity context, String msg) {
        // Toast.makeText(context, msg, 300).show();

//        LayoutInflater inflater = context.getLayoutInflater();
//        View toastView = inflater.inflate(R.layout.ff_toast, null);
//
//        TextView toast_txt_title = (TextView) toastView
//                .findViewById(R.id.toast_txt_title);
//        toast_txt_title.setText(msg);
//        LinearLayout toast_txt_layout = (LinearLayout) toastView
//                .findViewById(R.id.toast_txt_layout);
//        // toast_txt_layout.setBackgroundResource(R.id.);
//        Toast toast = new Toast(context);
//
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(toastView);
//        toast.show();
        showToast(context, msg, 2000);
    }

    public static String formatMoney(int money) {
        return "￥" + String.valueOf(((float) money) / 100);
    }

//    public static void loadImage(Context context, ImageView view, String url) {
//        if (url == null) {
//            return;
//        }
//        Glide.with(context).load(toBrowserCode(url))
//                .into(view);
//        // ImageLoader.getInstance().displayImage(url, view);
//        // FFApp.getInstance().getImageLoader().DisplayImage(url, view);
//    }

    public static void loadImage(ImageView view, String url) {
        if (url == null) {
            return;
        }
        Glide.with(FFApplication.instance).load(toBrowserCode(url)).placeholder(R.drawable.cc_book_no).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
        // FFApp.getInstance().getImageLoader().DisplayImage(url, view);
    }
    public static void loadImage(ImageView view, String url,int placeholderId) {
        if (url == null) {
            return;
        }
        Glide.with(FFApplication.instance).load(toBrowserCode(url)).placeholder(placeholderId).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
        //ImageLoader.getInstance().displayImage(url, view);
        // FFApp.getInstance().getImageLoader().DisplayImage(url, view);
    }
    private static final String HEX_STRING = "0123456789ABCDEF";

    /**
     * 把中文字符转换为带百分号的浏览器编码
     *
     * @param word
     * @return
     */
    public static String toBrowserCode(String word) {
        byte[] bytes = word.getBytes();

        // 不包含中文，不做处理
        if (bytes.length == word.length())
            return word;

        StringBuilder browserUrl = new StringBuilder();
        String tempStr = "";

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            // 不需要处理
            if ((int) currentChar <= 256) {

                if (tempStr.length() > 0) {
                    byte[] cBytes = tempStr.getBytes();

                    for (int j = 0; j < cBytes.length; j++) {
                        browserUrl.append('%');
                        browserUrl.append(
                                HEX_STRING.charAt((cBytes[j] & 0xf0) >> 4));
                        browserUrl.append(
                                HEX_STRING.charAt((cBytes[j] & 0x0f) >> 0));
                    }
                    tempStr = "";
                }

                browserUrl.append(currentChar);
            } else {
                // 把要处理的字符，添加到队列中
                tempStr += currentChar;
            }
        }
        return browserUrl.toString();
    }

    public static void loadImageLocal(Context context, ImageView view,
                                      String url) {
        Glide.with(context).load(new File(url))

                .centerCrop().into(view);
    }

    public static void loadImageLocal(Context context, ImageView view,
                                      File url) {
        Glide.with(context).load(url)

                .centerCrop().into(view);
    }

    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    public static float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager manager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            manager.getDefaultDisplay().getMetrics(dm);
            return dm.density;
        } catch (Exception ex) {

        }
        return 1.0f;
    }

    public static int dip2pix(Context context, int dip) {

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        return (int) (dm.density * (float) dip);

    }

    public static boolean isMobileNO(String mobiles) {
        String reg = "^[1][\\d]{10}";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    /**
     * 电话号码格式验证
     *
     * @param phone
     *            电话号码格式
     * @return 电话号码格式是否合法
     */
    public static boolean isPhoneNO(String phone) {
        Pattern p = Pattern.compile(
                "^((\\+?[0-9]{2,4}\\-[0-9]{3,4}\\-)|([0-9]{3,4}\\-))?([0-9]{7,8})(\\-[0-9]+)?$");
        Matcher m = p.matcher(phone);
        // return m.matches();
        return true;
    }

    public static boolean isTelNo(String telNo) {
        Pattern p = Pattern.compile("^[1]([358][0-9]{1}|59|58|88|89)[0-9]{8}$");
        Matcher m = p.matcher(telNo);
        // return m.find();
        return true;
    }

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    /**
     * 把价格转成小数点后两位
     *
     * @param data
     * @return
     */
    public static String FormatPrice(String data) {
        String temp = "";
        try {
            DecimalFormat df = new DecimalFormat();
            String style = "0.00";// 定义要显示的数字的格式
            df.applyPattern(style);// 将格式应用于格式化器
            temp = df.format(Double.parseDouble(data));
            System.out.println("采用style: " + style + "格式化之后: " + temp);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return temp;
    }

    /**
     * 判断手机网络是否可用
     *
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Description：<code> 处理微博过去时间显示效果 </code>
     *
     * @param createAt
     *            Data 类型 @return { String } @throws
     */
    public static String getInterval(Date createAt) {
        // 定义最终返回的结果字符串。
        String interval = null;

        long millisecond = new Date().getTime() - createAt.getTime();

        long second = millisecond / 1000;

        if (second <= 0) {
            second = 0;
        }

        if (second == 0) {
            interval = "刚刚";
        } else if (second < 30) {
            interval = second + "秒以前";
        } else if (second >= 30 && second < 60) {
            interval = "半分钟前";
        } else if (second >= 60 && second < 60 * 60) {
            long minute = second / 60;
            interval = minute + "分钟前";
        } else if (second >= 60 * 60 && second < 60 * 60 * 24) {
            long hour = (second / 60) / 60;
            if (hour <= 3) {
                interval = hour + "小时前";
            } else {
                interval = "今天" + getFormatTime(createAt, "hh:mm");
            }
        } else if (second >= 60 * 60 * 24 && second <= 60 * 60 * 24 * 2) {
            interval = "昨天" + getFormatTime(createAt, "hh:mm");
        } else if (second >= 60 * 60 * 24 * 2 && second <= 60 * 60 * 24 * 7) {
            long day = ((second / 60) / 60) / 24;
            interval = day + "天前";
        } else if (second >= 60 * 60 * 24 * 7) {
            interval = getFormatTime(createAt, "MM-dd hh:mm");
        } else if (second >= 60 * 60 * 24 * 365) {
            interval = getFormatTime(createAt, "YYYY-MM-dd hh:mm");
        } else {
            interval = "0";
        }
        // 最后返回处理后的结果。
        return interval;
    }

    /**
     * Description：<code> 返回指定格式的Date </code> String
     *
     * @param date
     * @param Sdf
     *            @return @throws
     */
    public static String getFormatTime(Date date, String Sdf) {
        return (new SimpleDateFormat(Sdf)).format(date);
    }

    public static String getFormatTimeStr(Long time) {

        // java.util.Locale locale=java.util.Locale.CHINA;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date d1 = new Date(time);
        return format.format(d1);

    }
    public static String getFormatDateStr(Long time) {

        // java.util.Locale locale=java.util.Locale.CHINA;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date d1 = new Date(time);
        return format.format(d1);

    }




    /**
     * 判断是否有网络连接
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     * 获取当前网络连接的类型信息
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }


}
