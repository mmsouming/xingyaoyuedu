package com.shinread.StarPlan.Teacher.engin.net;

import android.app.Activity;
import android.text.TextUtils;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.FFOkHttpHelper;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookRequestVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BorrowedListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BorrowedListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BrrowingListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BrrowingListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CardClassReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ClassListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ClassListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CollectListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CollectListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CollectReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ExtensionReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ExtensionResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetMyInterestTagReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetMyInterestTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetRecomandBookReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetRecomandBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetTagReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetUserInfoReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LoginReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LoginResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MyCommentReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MyCommentResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.NewReplyNoResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterSmsCodeReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterSmsCodeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveRequestVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SettingDefaultStuReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SettingDefaultStuResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ShareWorkReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.StudentRegisterReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.StudentRegisterResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UnfinishWorkReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UnfinishWorkResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateInterestTagReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateInterestTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateStudentInfoReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateStudentInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateUserInfoReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateUserInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkGetReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkGetResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.request.RequestCall;
import com.fancyfamily.primarylibrary.commentlibrary.util.DialogUtil;
import com.google.gson.Gson;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.CardResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesAllListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.ClassesListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.MeResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.NewWorkReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.StudentListReg;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.StudentListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.StudentReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.TWorkReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UncommentedWorkNoResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UncommentedWorkResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.UpdataClassReg;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkMangerTimeReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionListResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionResponseVo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by janecer on 2016/3/9.
 * email:jxc@fancyf.cn
 * des:网络请求相关接口
 */
public class AppModel extends CommonAppModel{


    /**
     * 获取消息列表
     * @param pageRecordNo
     * @param time
     * @param rowSize
     * @param readStatus
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall msgList(Integer pageRecordNo, Long time, Integer rowSize , int readStatus, String requestTag , HttpResultListener<MsgListResp> listener){
        MsgListReq req = new MsgListReq() ;
        req.pageRecordNo = pageRecordNo ;
        req.timestamp = time ;
        //req.rowSize = rowSize ;
        req.readStatus = readStatus ;
        return FFOkHttpHelper.get(UrlManager.URL_MSG_LIST ,requestTag , req ,MsgListResp.class ,listener);
    }

    /**
     * 注册或者忘记吗获取短信验证码
     * @param isRegister true表示注册获取验证码
     * @param username
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall userRegisterOrUpdatePwdCodeSmsGet(int isRegister , String username , String requestTag , HttpResultListener<RegisterSmsCodeResponseVo> listener){
        RegisterSmsCodeReq req = new RegisterSmsCodeReq() ;
        req.account = username ;

        String url = null ;
        if(isRegister == 0 ) {
            url = UrlManager.URL_REGISTER_SMS_CODE ;
        } else if (isRegister == 1) {
            url = UrlManager.URL_FORGETPWD_SMS_CODE ;
        } else if (isRegister == 2) {
            url = UrlManager.URL_FORGETPWD_SMS_CODE ;
        }
        return FFOkHttpHelper.post(url,requestTag , req ,RegisterSmsCodeResponseVo.class ,listener);
    }

    /**
     * 注册或者修改密码
     * @param isRegister true 表示注册
     * @param username
     * @param pwd
     * @param code
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall userRegisterOrUpdatepwd(int isRegister ,String username ,String pwd ,String code , String requestTag , HttpResultListener<RegisterResponseVo> listener){
        RegisterReq req = new RegisterReq() ;
        req.account = username ;
        req.password = pwd ;
        req.checkCode = code ;

        String url = null ;
        if(isRegister == 0 ) {
            url = UrlManager.URL_REGISTER ;
        } else if (isRegister == 1) {
            url = UrlManager.URL_PWD_UPDATE ;
        } else if (isRegister == 2) {
            url = UrlManager.URL_PWD_UPDATE ;
        }
        return FFOkHttpHelper.post(url ,requestTag , req ,RegisterResponseVo.class ,listener);
    }

    public static RequestCall userLogin (String username , String pwd, String requestTag , HttpResultListener<LoginResponseVo> listener) {
        LoginReq loginReq = new LoginReq() ;
        loginReq.account = username ;
        loginReq.password = pwd ;
        return FFOkHttpHelper.post(UrlManager.URL_LOGIN ,requestTag , loginReq ,LoginResponseVo.class ,listener);
    }

    /**
     * 根据code 获取学校班级信息
     * @param code
     * @param listener
     * @return
     */
    public static RequestCall card(String code , HttpResultListener<CardResponseVo> listener) {
        CardClassReq req = new CardClassReq() ;
        req.password = code ;
        return FFOkHttpHelper.post(UrlManager.URL_SET_CARD, "", req, CardResponseVo.class, listener) ;
    }
    /**
     * 学生登记
     * @param cardpwd
     * @param classId
     * @param name
     * @param sex
     * @param birthday
     * @param relationType
     * @param height
     * @param weight
     * @param headpic
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall studentRegister(String cardpwd, long classId ,String name ,int sex,String birthday,int relationType,int height,int weight,String headpic ,String requestTag , HttpResultListener<StudentRegisterResponseVo> listener){
        StudentRegisterReq req = new StudentRegisterReq() ;
        req.password = cardpwd;
        req.id = classId ;
        req.name = name ;
        req.sexType = sex ;
        req.birthday = TextUtils.isEmpty(birthday) ? null : birthday  ;
        req.relationType = relationType ;
        req.height = height ;
        req.weight = weight ;
        req.headPicture = headpic ;
        return FFOkHttpHelper.post(UrlManager.URL_STUDENT_REGISTER, requestTag, req, StudentRegisterResponseVo.class, listener);
    }

    /**
     * 设置默认孩子信息
     * @param stuId
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall setDefaultStuInfo(long stuId,String requestTag , HttpResultListener<SettingDefaultStuResp> listener){
        SettingDefaultStuReq req = new SettingDefaultStuReq();
        req.id = stuId;
        return FFOkHttpHelper.post(UrlManager.URL_SETTING_KINDS_DEFAULT,requestTag , req ,SettingDefaultStuResp.class ,listener);
    }

    /**
     * 头像上传
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall uploadHeadIcon(List<String> headers , String requestTag , HttpResultListener<HeadUploadResponseVo> resultListener) {

        HeadUploadReq req = new HeadUploadReq() ;
        HashMap<String ,List<String>> maps = new HashMap<>() ;
        maps.put("pictureArr" , headers) ;
        return FFOkHttpHelper.postFile(UrlManager.URL_HEAD_UPLOAD, requestTag, maps, req, HeadUploadResponseVo.class, resultListener) ;
    }

    /**
     * 获取个人信息
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall userInfoGets(String requestTag, HttpResultListener<MeResponseVo> resultListener) {
        GetUserInfoReq req = new GetUserInfoReq() ;
        return FFOkHttpHelper.get(UrlManager.URL_GET_USERINFO, requestTag, req, MeResponseVo.class, resultListener) ;
    }

    /**
     * 修改个人信息
     * @param nickName
     * @param sexType
     * @param birthday
     * @param picUrl
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall updateUserInfo(String nickName,int sexType,String birthday,String picUrl,String requestTag, HttpResultListener<UpdateUserInfoResp> resultListener) {
        UpdateUserInfoReq req = new UpdateUserInfoReq();
        req.nickname = nickName ;
        req.birthday = TextUtils.isEmpty(birthday) ? null : birthday ;
        req.pictureUrl = picUrl ;
        req.sexType = sexType ;
        return FFOkHttpHelper.post(UrlManager.URL_USERINFO_UPDATE, requestTag, req, UpdateUserInfoResp.class, resultListener) ;
    }

    /**
     * 3.3.6 根据学生Id获取班级列表
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall getClassList(long id ,String requestTag, HttpResultListener<ClassListResp> resultListener) {
        ClassListReq req = new ClassListReq() ;
        req.id = id ;
        return FFOkHttpHelper.get(UrlManager.URL_CLASSES_LIST, requestTag, req, ClassListResp.class, resultListener) ;
    }


    /**
     * 3.3.7修改学生信息
     * @param id
     * @param classId
     * @param name
     * @param sexType
     * @param birthday
     * @param relation
     * @param height
     * @param weight
     * @param picUrl
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall updateStudentInfo(long id ,long classId ,String name ,int sexType,String birthday,int relation,int height,int weight,String picUrl,String requestTag, HttpResultListener<UpdateStudentInfoResp> resultListener) {
        UpdateStudentInfoReq req = new UpdateStudentInfoReq() ;
        req.id = id ;
        req.classesId = classId ;
        req.name = name ;
        req.sexType = sexType ;
        req.birthday = birthday ;
        req.relationType = relation ;
        req.height = height ;
        req.weight = weight ;
        req.pictureUrl = picUrl ;
        return FFOkHttpHelper.post(UrlManager.URL_UPDATE_STUDENT_INFO, requestTag, req, UpdateStudentInfoResp.class, resultListener) ;
    }


    /**
     * 3.4.1 图书搜索
     * @param name
     * @param sortId
     * @param barCode
     * @param pageNo
     * @param rowSize
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall searchBooks(String name,Long sortId,String barCode,Integer pageNo,Integer rowSize,String requestTag,HttpResultListener<SearchBookResp> resultListener) {
        SearchBookReq req = new SearchBookReq();

        if( -1 == sortId) {
            sortId = null ;
        }

        if(null != name ) {
            req.name = name ;
        } if(null != sortId) {

            req.labelId = sortId ;
        } if(null != barCode){
            req.barcode = barCode ;
        }
        req.pageNo = pageNo ;
        req.rowSize = pageNo ;
        return FFOkHttpHelper.get(UrlManager.URL_LIBRARY_SEARCH, requestTag, req, SearchBookResp.class, resultListener) ;
    }
    /**
     * 3.4.2	图书查看
     * @param id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getBook(long id , String requestTag , HttpResultListener<BookResponseVo> listener) {
        BookRequestVo req = new BookRequestVo() ;
        req.id = id ;
        return FFOkHttpHelper.get(UrlManager.URL_BOOK, requestTag, req, BookResponseVo.class, listener);
    }


    /**
     * 3.4.4 获取图书的标签
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getLibraryTag(String requestTag , HttpResultListener<GetTagResp> listener) {
        GetTagReq req = new GetTagReq() ;
        return FFOkHttpHelper.get(UrlManager.URL_LIBRARY_TAG, requestTag, req, GetTagResp.class, listener);
    }
    /**
     * 3.4.4 获取感兴趣的标签
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getMyInterestTag(String requestTag , HttpResultListener<GetMyInterestTagResp> listener) {
        GetMyInterestTagReq req = new GetMyInterestTagReq() ;
        return FFOkHttpHelper.get(UrlManager.URL_LIBRARY_INTERERST_TAG, requestTag, req, GetMyInterestTagResp.class, listener);
    }

    /**
     * 3.4.4 更新感兴趣的标签
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall updateMyInterestTag(List<Long> tags,String requestTag , HttpResultListener<UpdateInterestTagResp> listener) {
        UpdateInterestTagReq req = new UpdateInterestTagReq() ;

        req.idArr =  new Gson().toJson(tags) ;
        return FFOkHttpHelper.post(UrlManager.URL_UPDATE_INTERERST_TAG,requestTag, req , UpdateInterestTagResp.class, listener);
    }

    /**
     * 3. .	收藏
     * @param id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall collect(Long id ,int type, String requestTag , HttpResultListener<BookResponseVo> listener) {
        CollectReq req = new CollectReq() ;
        req.id = id ;
        req.collectType = type ;
        return FFOkHttpHelper.post(UrlManager.URL_COLLECT, requestTag, req, BookResponseVo.class, listener);
    }


    /**
     * 3.4.9	获取收藏列表
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall collectList(String requestTag , HttpResultListener<CollectListResp> listener) {
        CollectListReq req = new CollectListReq() ;
        return FFOkHttpHelper.get(UrlManager.URL_COLLECT, requestTag, req, CollectListResp.class, listener);
    }

    /**
     * 3.4.6	预借
     * @param id 图书id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall reserve(long id , String requestTag , HttpResultListener<ReserveResponseVo> listener) {
        ReserveRequestVo req = new ReserveRequestVo() ;
        req.id = id ;
        return FFOkHttpHelper.post(UrlManager.URL_RESERVE,requestTag , req ,ReserveResponseVo.class ,listener);
    }

    /**
     * 3.4.7	预借列表
     * @param id 学生id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall reserveList(long id , String requestTag , HttpResultListener<ReserveListResp> listener) {
        ReserveListReq req = new ReserveListReq() ;
        req.id = id ;
        return FFOkHttpHelper.get(UrlManager.URL_RESERVE,requestTag , req ,ReserveListResp.class ,listener);
    }

    /**
     * 3.4.10获取借阅列表
     * @param id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall borrowingList(long id , String requestTag , HttpResultListener<BrrowingListResp> listener) {
        BrrowingListReq req = new BrrowingListReq() ;
        req.id = id ;
        return FFOkHttpHelper.get(UrlManager.URL_BRROWING,requestTag , req ,BrrowingListResp.class ,listener);
    }

    /**
     * 3.4.11获取借阅历史
     * @param id
     * @param pageReNo
     * @param time
     * @param rowSize
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall borrowedList(long id ,Integer pageReNo,Long time,Integer rowSize, String requestTag , HttpResultListener<BorrowedListResp> listener) {
        BorrowedListReq req = new BorrowedListReq() ;
        req.id = id ;
        req.pageRecordNo = pageReNo ;
        req.timestamp = time ;
        req.rowSize = rowSize ;
        return FFOkHttpHelper.get(UrlManager.URL_BRROWED,requestTag , req ,BorrowedListResp.class ,listener);
    }


    /**
     * 3.4.12借阅延期
     * @param id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall borrowExtension(long id , String requestTag , HttpResultListener<ExtensionResp> listener) {
        ExtensionReq req = new ExtensionReq() ;
        req.id = id ;
        return FFOkHttpHelper.post(UrlManager.URL_BRROW_EXTENSION,requestTag , req ,ExtensionResp.class ,listener);
    }

    /**
     * 3.4.13 获取推荐图书列表
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getRecomandBooks (String requestTag ,HttpResultListener<GetRecomandBookResp> listener) {
        GetRecomandBookReq req = new GetRecomandBookReq() ;
        return FFOkHttpHelper.get(UrlManager.URL_INTEREST_LIBRARY_LISTS ,requestTag, req,GetRecomandBookResp.class ,listener) ;
    }



    /**
     * 3.5.7	获取我的评论
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall myComment (MyCommentReq req, String requestTag , HttpResultListener<MyCommentResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_MYCOMMENT ,requestTag, req,MyCommentResponseVo.class ,listener) ;
    }

    /**
     * 3.5.8	获取新回复数
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall newReplyNo ( String requestTag , HttpResultListener<NewReplyNoResponseVo> listener) {
        BaseReq req = new BaseReq();
        return FFOkHttpHelper.get(UrlManager.URL_NEWREPLYNO ,requestTag, req,NewReplyNoResponseVo.class ,listener) ;
    }

    /**
     * 3.5.9	获取回复通知列表
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall replyNoticeList (ReplyNoticeListReq req, String requestTag , HttpResultListener<ReplyListResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_REPLYNOTICELIST ,requestTag, req,ReplyListResponseVo.class ,listener) ;
    }

    /**
     * 3.6.1获取未完成作业
     * @param stuId 学生id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall unFinishWorkList (Long stuId ,String requestTag , HttpResultListener<UnfinishWorkResponseVo> listener) {
        UnfinishWorkReq req = new UnfinishWorkReq() ;
        req.id = stuId ;
        return FFOkHttpHelper.get(UrlManager.URL_UNFINISH_WORK ,requestTag, req,UnfinishWorkResponseVo.class ,listener) ;
    }

    /**
     * 3.6.2获取作业列表
     * @param stuId
     * @param pageNo
     * @param timestap
     * @param rowSize
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall workList (Long stuId ,int pageNo,Long timestap,Integer rowSize ,String requestTag , HttpResultListener<WorkListResponseVo> listener) {
        WorkListReq req = new WorkListReq() ;
        req.id = stuId ;
        req.pageRecordNo = pageNo ;
        req.timestamp = timestap ;
        req.rowSize = rowSize ;
        return FFOkHttpHelper.get(UrlManager.URL_WORKLIST ,requestTag, req,WorkListResponseVo.class ,listener) ;
    }

    /**
     * 3.6.3获取作业
     * @param workId
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getWorkById (Long workId ,String requestTag , HttpResultListener<WorkGetResponseVo> listener) {
        WorkGetReq req = new WorkGetReq() ;
        req.id = workId ;
        return FFOkHttpHelper.get(UrlManager.URL_WORK ,requestTag, req,WorkGetResponseVo.class ,listener) ;
    }


    /**
     * 3.6.4提交作业
     * @param workId
     * @param bookId
     * @param pics
     * @param content
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall commitWork (Long workId ,Long bookId,List<String> pics ,String content,String requestTag , HttpResultListener<WorkResponseVo> listener) {
        WorkReq req = new WorkReq() ;
        req.id = workId ;
        req.bookId = bookId ;
        req.pictureUrlArr = new Gson().toJson(pics) ;
        req.content = content ;
        return FFOkHttpHelper.post(UrlManager.URL_WORK_COMMIT ,requestTag, req,WorkResponseVo.class ,listener) ;
    }

    /**
     * 分享作业
     * @param workId
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall shareWork(Long workId ,String requestTag , HttpResultListener<BaseResponseVo> listener) {
        ShareWorkReq req = new ShareWorkReq() ;
        req.id = workId ;
        return FFOkHttpHelper.post(UrlManager.URL_WORK_SHOW ,requestTag, req,BaseResponseVo.class ,listener) ;
    }


    /**5.6.1	布置作业（作业题目）
     *
     * @param
     * @param listener
     * @return
     */
    public static RequestCall workQuestion(Activity context,String name,String content,Long effectiveTimestamp,Long invalidTimestamp,List<Long>idArr,List<Long>bookidArr, HttpResultListener<WorkQuestionResponseVo> listener) {
        WorkQuestionReq req=new WorkQuestionReq();
        req.name=name;
        req.content=content;
        req.effectiveTimestamp=effectiveTimestamp;
        req.invalidTimestamp=invalidTimestamp;
        req.idArr=new Gson().toJson(idArr);
        req.bookIdArr=new Gson().toJson(bookidArr);
        Logger.msg("shangchuan", "开始时间:" + effectiveTimestamp+""+ "完成时间:" + invalidTimestamp+""  );
        return FFOkHttpHelper.post(UrlManager.URL_WORKQUESTION ,req,WorkQuestionResponseVo.class ,listener, DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }

    /**5.6.2	获取未点评作业数量
     *
     * @param
     * @param listener
     * @return
     */
    public static RequestCall uncommentedWorkNo(String requestTag, HttpResultListener<UncommentedWorkNoResponseVo> listener) {
        BaseReq req=new BaseReq();
        return FFOkHttpHelper.get(UrlManager.URL_UNCOMMENTEDWORKNO,"",req,UncommentedWorkNoResponseVo.class ,listener) ;
    }

    /**5.6.3	获取未点评作业列表
     *
     * @param req
     * @param listener
     * @return
     */
    public static RequestCall uncommentedWork(BasePageReq req , HttpResultListener<UncommentedWorkResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_UNCOMMENTEDWORK,"",req,UncommentedWorkResponseVo.class ,listener) ;
    }

    /**5.6.4	获取我布置的作业列表（题目）
     *
     * @param req
     * @param listener
     * @return
     */
    public static RequestCall workQuestionList(BasePageReq req , HttpResultListener<WorkQuestionListResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_WORKQUESTIONLIST ,"",req,WorkQuestionListResponseVo.class ,listener) ;
    }

    /**5.6.5	获取作业题目
     *
     * @param req
     * @param listener
     * @return
     */
    public static RequestCall workQuestion(BaseReq req , HttpResultListener<WorkQuestionResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_WORKQUESTION ,"",req,WorkQuestionResponseVo.class ,listener) ;
    }

    /**5.6.6	获取作业列表
     *
     * @param req
     * @param listener
     * @return
     */
    public static RequestCall workList(BasePageReq req , HttpResultListener<UncommentedWorkResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_WORKLIST ,"",req,UncommentedWorkResponseVo.class ,listener) ;
    }

    /**5.6.7	作业点评
     *
     * @param req
     * @param listener
     * @return
     */
    public static RequestCall work(Activity context, TWorkReq req , HttpResultListener<WorkResponseVo> listener) {
        return FFOkHttpHelper.post(UrlManager.URL_WORK,req,WorkResponseVo.class ,listener, DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }

    /**5.6.8	失效作业（题目）
     *
     * @param
     * @param listener
     * @return
     */
    public static RequestCall invalid(Activity context,long id , HttpResultListener<WorkQuestionResponseVo> listener) {
        BaseReq req = new BaseReq() ;
        req.id = id ;

        return FFOkHttpHelper.post(UrlManager.URL_INVALID,req,WorkQuestionResponseVo.class ,listener, DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }

    /**5.6.9	修改作业（题目）结束时间
     *
     * @param
     * @param listener
     * @return
     */
    public static RequestCall workQuestionTime(Activity context, Long id,Long invalidTimestamp, HttpResultListener<WorkQuestionResponseVo> listener) {
        WorkMangerTimeReq req=new WorkMangerTimeReq();
        req.id=id;
        req.invalidTimestamp=invalidTimestamp;
        return FFOkHttpHelper.post(UrlManager.URL_INVALID,req,WorkQuestionResponseVo.class ,listener, DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }

    /**5.6.10	基于学生的作业列表
     *
     * @param req
     * @param listener
     * @return
     */
    public static RequestCall workListByStudent(BasePageReq req , HttpResultListener<UncommentedWorkResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_WORKLISTBYSTUDENT ,"",req,UncommentedWorkResponseVo.class ,listener) ;
    }

    /**5.6.11	获取未结束题目
     *
     * @param req
     * @param listener
     * @return
     */
    public static RequestCall unfinishedQuestionList(BasePageReq req , HttpResultListener<WorkQuestionListResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_UNFINISHEDQUESTIONLIST ,"",req,WorkQuestionListResponseVo.class ,listener) ;
    }


    /**5.3.1	获取全部班级列表
     *
     * @param listener
     * @return
     */
    public static RequestCall classesAllList(HttpResultListener<ClassesAllListResponseVo> listener) {
        BaseReq req = new BasePageReq();
        return FFOkHttpHelper.get(UrlManager.URL_CLASSESALLLIST ,"",req,ClassesAllListResponseVo.class ,listener) ;
    }

    /**
     * 3.4.4 更新感兴趣的标签
     * @param requestTag
     * @param listener
     * @return
     */
//public static RequestCall updateMyInterestTag(List<Long> tags,String requestTag , HttpResultListener<UpdateInterestTagResp> listener) {
//    UpdateInterestTagReq req = new UpdateInterestTagReq() ;
//
//    req.idArr = tags ;
//    return FFOkHttpHelper.post(UrlManager.URL_UPDATE_INTERERST_TAG,requestTag, req , UpdateInterestTagResp.class, listener);
//}

    /**5.3.2	设置执教班级
     *
     * @param
     * @param listener
     * @return
     */
    public static RequestCall postClassesList(Activity context, List<String> tags , HttpResultListener<ClassesListResponseVo> listener) {

        UpdataClassReg  req=new     UpdataClassReg();
        req.idArr=new Gson().toJson(tags);
        return FFOkHttpHelper.post(UrlManager.URL_CLASSESLIST,req,ClassesListResponseVo.class ,listener, DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }

    /**5.3.3	获取执教班级列表
     *
     * @param listener
     * @return
     */
    public static RequestCall getClassesList(HttpResultListener<ClassesListResponseVo> listener) {
        BaseReq req = new BasePageReq();
        return FFOkHttpHelper.get(UrlManager.URL_CLASSESLIST,"",req,ClassesListResponseVo.class ,listener) ;
    }

    /**5.3.4	获取学生列表
     *
     * @param listener
     * @return
     */
    public static RequestCall studentList(Long studentId , HttpResultListener<StudentListResponseVo> listener) {
        StudentListReg req=new StudentListReg();
        req.id=studentId;
        return FFOkHttpHelper.get(UrlManager.URL_STUDENTLIST,"",req,StudentListResponseVo.class ,listener) ;
    }

    /**5.3.5	学生管理
     *
     * @param listener
     * @return
     */
    public static RequestCall studentanger(Activity context, List<String> tags,int certiStatus, HttpResultListener<StudentListResponseVo> listener) {
        StudentReq req=new StudentReq();
        req.idArr=new Gson().toJson(tags);
        req.certificateStatus=certiStatus;
        return FFOkHttpHelper.post(UrlManager.URL_STUDENT,req,StudentListResponseVo.class ,listener, DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }


}
