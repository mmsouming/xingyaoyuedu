package com.shinread.StarPlan.Parent.ui.activity.test;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Toast;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LoginResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseWorkFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.FileUtil;
import com.fancyfamily.primarylibrary.commentlibrary.widget.LoadTipLayout;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.shinyread.StarPlan.Parent.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;

public class MainActivity extends BaseWorkFragmentActivity implements View.OnClickListener{

    private LoadTipLayout loadTipLayout ;
    private LinearLayoutCompat rlContent ;
    private SwipeRefreshLayout srf ;

    ViewPager mPager ;
    CirclePageIndicator mIndicator ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        rlContent = (LinearLayoutCompat) findViewById(R.id.rl_content);
        loadTipLayout = (LoadTipLayout) findViewById(R.id.load_tip) ;
        findViewById(R.id.button).setOnClickListener(this);

        loadTipLayout.setOnReloadClickListner(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadTipLayout.showLoadding();
                sendEmptyBackgroundMessage(0);
            }
        });
        loadTipLayout.showLoadException();


        TitleBar titleBar = (TitleBar) findViewById(R.id.tb);
        titleBar.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "left nav ", Toast.LENGTH_SHORT).show();
            }
        });
        titleBar.setOnRightNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "right nav ", Toast.LENGTH_SHORT).show();
            }
        });

        FileUtil.writeLog2Sdcard("你好，log记录系统1" , "data" +File.separator +"data"   + File.separator + getPackageName() + File.separator +"log.txt");
        FileUtil.writeException2Sdcard(new NullPointerException("helle nullPointException "), "data" + File.separator + "data" + File.separator + getPackageName() + File.separator + "log.txt");
        FileUtil.writeLog2Sdcard("你好，log记录系统2", "data" + File.separator + "data" + File.separator + getPackageName() + File.separator + "log.txt");

        srf = (SwipeRefreshLayout) findViewById(R.id.srf);
        //设置刷新时动画的颜色，可以设置4个
        srf.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Logger.msg(TAG , " onRefresh() " );
                sendEmptyUiMessageDelayed(0 , 3000);
            }
        });


//        mPager = (ViewPager)findViewById(R.id.pager);
//        mPager.setAdapter(mAdapter);
//
//        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
//        mIndicator.setViewPager(mPager);
    }



    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    public void onClick(View view){
        switch (view.getId() ) {
            case R.id.button:
                AppModel.userLogin("13660826966", "xiaocai",TAG , new HttpResultListener<LoginResponseVo>() {
                    @Override
                    public void onSuccess(LoginResponseVo resp) {

                    }

                    @Override
                    public void onFailed(Exception e,String msg) {

                    }

                }) ;
//                LoginReq loginReq = new LoginReq() ;
//                loginReq.account = "13660826966" ;
//                loginReq.password = "xiaocai" ;
//                String jsonContent = new Gson().toJson(loginReq) ;
//                NameValuePair nameValuePair = new BasicNameValuePair("data",
//                        jsonContent);// 键值对
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                nameValuePairs.add(nameValuePair);// 将键值对放入到列表中
//                try {
//                    HttpEntity entity = new UrlEncodedFormEntity(nameValuePairs,
//                            "utf-8");// 对参数进行编码操
//
//                    new AsyncHttpClient().get(this, UrlManager.URL_LOGIN ,entity ,null,new AsyncHttpResponseHandler(){
//                        @Override
//                        public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
//                            Logger.msg("onSuccess : " + statusCode + "  result ：" + new String(responseBody));
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
//                            Logger.msg("onFailure " + statusCode);
//                        }
//                    });
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }

                break ;
            case R.id.button2 :
                break ;
        }
    }

    @Override
    public void handleBackgroundMsg(Message msg) {
        super.handleBackgroundMsg(msg);
        try {
            Thread.currentThread().sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendEmptyUiMessage(0);
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        loadTipLayout.showLoadSuccess();
        srf.setRefreshing(false);
    }
}
