package com.fancyfamily.primarylibrary.commentlibrary.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.CommonUtils;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;

public class WebActivity extends BaseFragmentActivity {

    public static final String WEB_URL = "URL";
    WebView fullWebView;
    NavBarManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.activity_web);

        fullWebView = (WebView) findViewById(R.id.fullWebView);
        CommonUtils.setWebView(this, fullWebView);
        manager = new NavBarManager(this);
        manager.txt_title.setText("");
        manager.btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        fullWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                manager.txt_title.setText(title);
            }
        });

        String url = getIntent().getStringExtra(WEB_URL);

//        上面这个类的方法名上面要加annotation
//        @JavascriptInterface
//        public void shouQuan(){
//        ......
//        }
//        表忘了导包import android.webkit.JavascriptInterface;
//       addJavascriptInterface() 必须在loadUrl()之前调用，否则无法调用
        if (url != null && !url.equals("")) {
            fullWebView.loadUrl(url);
            // pay_web.loadUrl("file:///android_asset/moon.html");
        }

        fullWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && fullWebView.canGoBack()) { // 表示按返回键 时的操作
                        fullWebView.goBack(); // 后退
                        // webview.goForward();//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (fullWebView.canGoBack()) {
            fullWebView.goBack();
        } else {
            finish();
        }
    }
}
