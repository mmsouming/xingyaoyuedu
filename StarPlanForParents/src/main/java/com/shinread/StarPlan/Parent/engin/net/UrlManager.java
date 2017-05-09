package com.shinread.StarPlan.Parent.engin.net;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonUrlManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;


public class UrlManager extends CommonUrlManager{


    public static final String COMMON_URL_BASE = "120.25.89.216";//网络
    public static final String APP = "shinyParentsServer";//客户端
    public static final String NET_HTTPS_COM = ":10443";//客户端
    public static final String NET_HTTP_COM = "";//端口
    public static void init(){
        CommonUrlManager.APP = APP;
        CommonUrlManager.HTTPS_COM = NET_HTTPS_COM;
        CommonUrlManager.HTTP_COM = NET_HTTP_COM;
        CommonUrlManager.COMMON_URL_BASE = COMMON_URL_BASE;
        UserInfoManager.getInstance().setCurrentUser(UserInfoManager.PARENTUSER);
    }

    /* 3.6.1获取未完成作业 */
    public final static String URL_UNFINISH_WORK = "/app/wk/unfinishWork";

    /* 3.6.2获取作业列表 */
    public final static String URL_WORKLIST = "/app/wk/workList";

    /* 3.6.3获取作业 */
    public final static String URL_WORK = "/app/wk/work";

    /* 3.6.4提交作业 */
    public final static String URL_WORK_COMMIT = "/app/wk/work";

    /* 3.6.5分享作业 */
    public final static String URL_WORK_SHOW = "/app/wk/show";

    /* 3.2.11获取积分记录 */
    public final static String URL_SCORE = "/app/act/score";
}
