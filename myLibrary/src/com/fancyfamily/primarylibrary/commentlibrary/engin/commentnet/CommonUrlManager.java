package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet;

/**
 * Created by janecer on 2016/3/9.
 * email:jxc@fancyf.cn
 * des:网络请求的url
 */
public class CommonUrlManager {

    public static String APP = "";//客户端
    public static String HTTPS_COM ="";//端口
    public static String HTTP_COM ="";//端口
    public static String COMMON_URL_BASE = "120.25.89.216";//域名

    //public static String COMMON_URL_BASE = "http://172.22.56.56:8080";//秋华教师端本机
    //public static String COMMON_URL_BASE = "http://172.22.56.56:8088";//秋华家长端本机
    /**
     * 3.1.5关联极光ID
     */
    public final static String URL_JPUSHID = "/app/system/jpushId";


    /**
     * 3.1.6获取开机广告
     */
    public final static String URL_LAUNCHSCREEN = "/app/system/launchScreen";

    /**
     * 3.1.1获取版本信息
     */
    public final static String URL_VERSION = "/app/system/version";

    /**
     * 3.1.2上传异常日志
     */
    public final static String URL_ERRLOG = "/app/system/errLog";
    /**
     * 3.1.3获取配置信息
     */
    public final static String URL_CONFIG = "/app/system/config";

    /* 注册获取短信验证码 */
    public final static String URL_REGISTER_SMS_CODE = "/app/act/registerSms";

    /*  3.1.7获取新消息数 */
    public final static String URL_NEWMSGNO = "/app/system/newMsgNo";

    /*  3.1.8获取消息列表 */
    public final static String URL_MSG_LIST = "/app/system/msg";

    /* 注册 */
    public final static String URL_REGISTER = "/app/act/register";

    /* 忘记密码获取短信验证码 */
    public final static String URL_FORGETPWD_SMS_CODE = "/app/act/resetPasswordSms";
    /* 修改密码 */
    public final static String URL_PWD_UPDATE = "/app/act/resetPassword";

    /* 登陆接口 */
    public final static String URL_LOGIN = "/app/act/login";

    /* 获取班级列表 */
    public final static String URL_SCHOOL_CLASS_GET = "/app/std/card";

    /* 学生登记 */
    public final static String URL_STUDENT_REGISTER = "/app/std/register";


    /* 头像上传 */
    public final static String URL_HEAD_UPLOAD = "/app/system/uploadPicture";

    /* 获取个人信息 */
    public final static String URL_GET_USERINFO = "/app/act/me";


    /* 修改个人信息 */
    public final static String URL_USERINFO_UPDATE = "/app/act/parent";


    /* 设置默认孩子信息 */
    public final static String URL_SETTING_KINDS_DEFAULT = "/app/std/defaultStudent";

    /*  3.3.6 根据学生Id获取班级列表 */
    public final static String URL_CLASSES_LIST = "/app/std/classesList";

    /* 3.4.1 查询图书列表 */
    public final static String URL_LIBRARY_SEARCH = "/app/bk/bookList";

    /* 3.4.3 获取图书标签 */
    public final static String URL_LIBRARY_TAG = "/app/bk/bookLable";

    /* 3.4.4 获取我感兴趣的图书标签 */
    public final static String URL_LIBRARY_INTERERST_TAG = "/app/bk/interest";

    /* 3.4.5修改我的兴趣 */
    public final static String URL_UPDATE_INTERERST_TAG = "/app/bk/interest";

    /* 3.4.10获取借阅列表 */
    public final static String URL_BRROWING = "/app/bk/borrow";

    /* 3.4.11获取借阅历史 */
    public final static String URL_BRROWED = "/app/bk/borrowHistory";

    /* 3.4.12借阅延期 */
    public final static String URL_BRROW_EXTENSION = "/app/bk/extension";

    /* 3.3.7修改学生信息 */
    public final static String URL_UPDATE_STUDENT_INFO = "/app/std/student";

    /* 3.4.2	图书查看 */
    public final static String URL_BOOK = "/app/bk/book";

    /* 3.4.8	收藏 */
    public final static String URL_COLLECT = "/app/bk/collect";

    /* 3.4.6	预借*/
    public final static String URL_RESERVE = "/app/bk/reserve";

    /* 3.5.7	获取我的评论*/
    public final static String URL_MYCOMMENT = "/app/cm/myComment";

    /* 3.5.8	获取新回复数*/
    public final static String URL_NEWREPLYNO = "/app/cm/newReplyNo";

    /* 3.5.9	获取回复通知列表*/
    public final static String URL_REPLYNOTICELIST = "/app/cm/replyNoticeList";

    /* 3.4.13	获取兴趣推荐图书*/
    public final static String URL_INTEREST_LIBRARY_LISTS = "/app/bk/interestBook";

    /* 3.4.14取消预借 */
    public final static String URL_CANCELRESERVE = "/app/bk/cancelReserve";

    /* 3.5.1	发表评论 post  3.5.2	获取评论 get*/
    public final static String URL_COMMENT = "/app/cm/comment";

    /* 3.5.3	获取评论列表*/
    public final static String URL_COMMENTLIST = "/app/cm/commentList";

    /* 3.5.4	评论点赞*/
    public final static String URL_COMMENTLIKE = "/app/cm/commentLike";

    /* 3.5.5 评论回复*/
    public final static String URL_REPLY = "/app/cm/reply";

    /* 3.5.6	获取评论回复列表*/
    public final static String URL_REPLYLIST = "/app/cm/replyList";

    /* 3.5.10评论删除*/
    public final static String URL_DELETECOMMENT = "/app/cm/deleteComment";

    /* 3.5.12评论举报*/
    public final static String URL_REPORTCOMMENT = "/app/cm/reportComment";

    /*3.5.13回复举报*/
    public final static String URL_REPORTREPLY = "/app/cm/reportReply";

    public static String getCommonUrl(String url) {
        if (isHttps(url)){
            return "https://" + COMMON_URL_BASE + HTTPS_COM +"/"+ APP + url;
        }else {
            return "http://" + COMMON_URL_BASE + HTTP_COM +"/"+ APP + url;
        }
    }


    public static boolean isHttps(String url) {
        Boolean flag = false;

        for (int i = 0; i < HTTPSLIST.length; i++) {
            if(url.equals(HTTPSLIST[i])){
                flag = true;
            }
        }
        return flag;
    }

    public static String[] HTTPSLIST = {};
}
