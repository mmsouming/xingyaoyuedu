package com.shinread.StarPlan.Teacher.engin.net;


import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonUrlManager;
import com.fancyfamily.primarylibrary.commentlibrary.util.UserInfoManager;

/**
 * Created by janecer on 2016/3/9.
 * email:jxc@fancyf.cn
 * des:网络请求的url
 */
public class UrlManager extends CommonUrlManager{


    public static final String COMMON_URL_BASE = "120.25.89.216";//网络
    public static final String APP = "shinyTeacherServer";//客户端
    public static final String NET_HTTPS_COM = ":11443";//端口
    public static final String NET_HTTP_COM = "";//端口

    public static void init(){
        CommonUrlManager.APP = APP;
        CommonUrlManager.HTTPS_COM = NET_HTTPS_COM;
        CommonUrlManager.COMMON_URL_BASE = COMMON_URL_BASE;
        CommonUrlManager.HTTP_COM = NET_HTTP_COM;
        UserInfoManager.getInstance().setCurrentUser(UserInfoManager.TEACHERUSER);
    }

    /* 获取班级列表 */
    public final static String URL_SCHOOL_CLASS_GET = "/app/std/card";

    /* 5.7.1更改璀璨卡 */
    public final static String URL_SET_CARD = "/app/un/card";


    /* 修改个人信息 */
    public final static String URL_USERINFO_UPDATE = "/app/act/parent";

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
    public final static String URL_REPLYNOTICELIST = "/app/sm/replyNoticeList";

    /* 3.4.13	获取兴趣推荐图书*/
    public final static String URL_INTEREST_LIBRARY_LISTS = "/app/bk/interestBook";

    /* 3.6.1获取未完成作业 */
    public final static String URL_UNFINISH_WORK = "/app/wk/unfinishWork";

    /* 3.6.4提交作业 */
    public final static String URL_WORK_COMMIT = "/app/wk/work";

    /* 3.6.5分享作业 */
    public final static String URL_WORK_SHOW = "/app/wk/show";


    /** --------------------------------------------------------------教师端其他接口-------------------------------------------------------------------- **/
    /* 5.7.1更改璀璨卡*/
    public final static String URL_UPDATE_CARD = "/app/un/card" ;

    /* 5.6.4获取我布置的作业列表（题目） */
    public final static String URL_GET_TAST_LIST = "/app/wk/workQuestionList" ;

//    /* 5.6.1	布置作业（作业题目）post*
    public final static String URL_WORKQUESTION = "/app/wk/workQuestion" ;
    /* 5.6.2	获取未点评作业数量*/
    public final static String URL_UNCOMMENTEDWORKNO = "/app/wk/uncommentedWorkNo" ;

    /* 5.6.3	获取未点评作业列表*/
    public final static String URL_UNCOMMENTEDWORK = "/app/wk/uncommentedWork" ;

    /* 5.6.4	获取我布置的作业列表（题目）*/
    public final static String URL_WORKQUESTIONLIST = "/app/wk/workQuestionList" ;

    /* 5.6.5	获取作业题目get*/
    //public final static String URL_WORKQUESTION = "/app/wk/workQuestion" ;

    /* 5.6.6	获取作业列表*/
    public final static String URL_WORKLIST = "/app/wk/workList" ;

    /* 5.6.7	作业点评*/
    public final static String URL_WORK = "/app/wk/work" ;

    /* 5.6.8	失效作业（题目）*/
    public final static String URL_INVALID = "/app/wk/invalid" ;

    /* 5.6.9	修改作业（题目）结束时间*/
    public final static String URL_WORKQUESTIONTIME = "/app/wk/workQuestionTime" ;

    /* 5.6.10	基于学生的作业列表*/
    public final static String URL_WORKLISTBYSTUDENT = "/app/wk/workListByStudent" ;

    /* 5.6.11	获取未结束题目*/
    public final static String URL_UNFINISHEDQUESTIONLIST = "/app/wk/unfinishedQuestionList" ;



    /* 5.6.5获取作业题目 */
    public final static String URL_GET_TASKQUETION = "/app/wk/workQuestion" ;

    /* 5.6.8失效作业（题目） */
    public final static String URL_TASK_INVALID= "/app/wk/invalid" ;

    /* 5.6.9修改作业（题目）结束时间 */
    public final static String URL_TASK_COMPLETE_TIME= "/app/wk/workQuestionTime" ;


    /* 5.3.1	获取全部班级列表 */
    public final static String URL_CLASSESALLLIST= "/app/cls/classesAllList" ;

    /*5.3.2	设置执教班级 5.3.3	获取执教班级列表*/
    public final static String URL_CLASSESLIST= "/app/cls/classesList" ;

    /*5.3.4	获取学生列表 */
    public final static String URL_STUDENTLIST= "/app/cls/studentList" ;

    /*5.3.5	学生管理 */
    public final static String URL_STUDENT= "/app/cls/student" ;



}
