package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet;

import android.app.Activity;
import android.text.TextUtils;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookLikeReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookRequestVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BookResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BorrowedListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BrrowingListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CancelReserveResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CollectListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CollectReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CommentResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ExtensionReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ExtensionResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetMyInterestTagReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetMyInterestTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetRecomandBookReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetRecomandBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetTagReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetUserInfoReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetUserInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.HeadUploadResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.JpushReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LaunchScreenResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LoginReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.LoginResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MsgListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MyCommentResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.NewReplyNoResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterSmsCodeReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.RegisterSmsCodeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReportCommentReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveRequestVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReserveResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SearchBookResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateInterestTagReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateInterestTagResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateUserInfoReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateUserInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.VersionResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.request.RequestCall;
import com.fancyfamily.primarylibrary.commentlibrary.util.DialogUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;


public class CommonAppModel {

    /**
     * 3.5.9	获取回复通知列表
     *
     * @param listener
     * @return
     */
    public static RequestCall replyNoticeList (ReplyNoticeListReq req , HttpResultListener<ReplyNoticeListResponseVo> listener) {
        return FFOkHttpHelper.get(CommonUrlManager.URL_REPLYNOTICELIST ,"", req,ReplyNoticeListResponseVo.class ,listener) ;
    }
    public static RequestCall userLogin (String username , String pwd, String requestTag , HttpResultListener<LoginResponseVo> listener) {
        LoginReq loginReq = new LoginReq() ;
        loginReq.account = username ;
        loginReq.password = pwd ;
        return FFOkHttpHelper.post(CommonUrlManager.URL_LOGIN ,requestTag , loginReq ,LoginResponseVo.class ,listener);
    }
    /**
     * 获取个人信息
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall teacherInfoGets(String requestTag, HttpResultListener<MeResponseVo> resultListener) {
        GetUserInfoReq req = new GetUserInfoReq() ;
        return FFOkHttpHelper.get(CommonUrlManager.URL_GET_USERINFO, requestTag, req, MeResponseVo.class, resultListener) ;
    }

    /**
     * 家长端获取个人信息
     * @param requestTag
     * @param resultListener
     * @return
     */
    public static RequestCall userInfoGet(String requestTag, HttpResultListener<GetUserInfoResp> resultListener) {
        GetUserInfoReq req = new GetUserInfoReq() ;
        return FFOkHttpHelper.get(CommonUrlManager.URL_GET_USERINFO, requestTag, req, GetUserInfoResp.class, resultListener) ;
    }
    /**
     * 3.1.1获取版本信息
     * @return
     */
    public static RequestCall version(HttpResultListener<VersionResponseVo> listener){
        return FFOkHttpHelper.get(CommonUrlManager.URL_VERSION ,"" , new BaseReq() ,VersionResponseVo.class ,listener);
    }

    /**
     * 3.1.2上传异常日志
     * @return
     */
//    public static RequestCall errLog(BaseReq req, HttpResultListener<VersionResponseVo> listener){
//        return FFOkHttpHelper.get(CommonUrlManager.URL_VERSION ,"" , req ,VersionResponseVo.class ,listener);
//    }

    /**
     * 3.1.3获取配置信息
     * @return
     */
//    public static RequestCall config(BaseReq req, HttpResultListener<VersionResponseVo> listener){
//        return FFOkHttpHelper.get(CommonUrlManager.URL_CONFIG ,"" , req ,VersionResponseVo.class ,listener);
//    }

    /**
     * 3.1.5关联极光ID
     * @return
     */
    public static RequestCall jpushId(JpushReq req, HttpResultListener<BaseResponseVo> listener){
        return FFOkHttpHelper.post(CommonUrlManager.URL_JPUSHID ,"" , req ,BaseResponseVo.class ,listener);
    }

    /**
     * 3.1.6获取开机广告
     * @return
     */
    public static RequestCall launchScreen(BaseReq req, HttpResultListener<LaunchScreenResponseVo> listener){
        return FFOkHttpHelper.get(CommonUrlManager.URL_LAUNCHSCREEN ,"" , req ,LaunchScreenResponseVo.class ,listener);
    }

    /**
     * 3.1.7获取新消息数
     * @return
     */
    public static RequestCall newMsgNo(HttpResultListener<NewMsgNoResponseVo> listener){
        return FFOkHttpHelper.get(CommonUrlManager.URL_NEWMSGNO ,"" , new BaseReq() ,NewMsgNoResponseVo.class ,listener);
    }
    /**
     * 3.5.8获取新回复数
     * @return
     */
    public static RequestCall newReplyNo(HttpResultListener<NewReplyNoResponseVo> listener){
        return FFOkHttpHelper.get(CommonUrlManager.URL_NEWREPLYNO ,"" , new BaseReq() ,NewReplyNoResponseVo.class ,listener);
    }
    /**
     * 获取消息列表
     * @param listener
     * @return
     */
    public static RequestCall msgList(MsgListReq req , HttpResultListener<MsgListResp> listener){
        return FFOkHttpHelper.get(CommonUrlManager.URL_MSG_LIST ,"" , req ,MsgListResp.class ,listener);
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
            url = CommonUrlManager.URL_REGISTER_SMS_CODE ;
        } else if (isRegister == 1) {
            url = CommonUrlManager.URL_FORGETPWD_SMS_CODE ;
        } else if (isRegister == 2) {
            url = CommonUrlManager.URL_FORGETPWD_SMS_CODE ;
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
            url = CommonUrlManager.URL_REGISTER ;
        } else if (isRegister == 1) {
            url = CommonUrlManager.URL_PWD_UPDATE ;
        } else if (isRegister == 2) {
            url = CommonUrlManager.URL_PWD_UPDATE ;
        }
        return FFOkHttpHelper.post(url ,requestTag , req ,RegisterResponseVo.class ,listener);
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
        return FFOkHttpHelper.post(CommonUrlManager.URL_USERINFO_UPDATE, requestTag, req, UpdateUserInfoResp.class, resultListener) ;
    }
    /**
     * 3.5.1	发表评论
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall postComment (long id,int commentType,List<String> picArr,String content,String requestTag ,HttpResultListener<CommentResponseVo> listener) {
        CommentReq req = new CommentReq() ;
        req.id = id ;
        req.contentType = commentType ;
        req.pictureUrlArr = new Gson().toJson(picArr) ;
        req.content = content ;
        return FFOkHttpHelper.post( CommonUrlManager.URL_COMMENT,requestTag, req,CommentResponseVo.class ,listener) ;
    }


    /**
     * 3.5.2	获取评论
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getCommentById (long id,int type,String requestTag ,HttpResultListener<CommentResponseVo> listener) {
        CommentReq req = new CommentReq() ;
        req.id = id ;
        req.contentType = type ;
        return FFOkHttpHelper.get( CommonUrlManager.URL_COMMENT,requestTag, req,CommentResponseVo.class ,listener) ;
    }

    /**
     * 3.5.3	获取评论列表
     *
     * @param listener
     * @return
     */
    public static RequestCall commentList (CommentListReq req , HttpResultListener<CommentListResponseVo> listener) {

        return FFOkHttpHelper.get(CommonUrlManager.URL_COMMENTLIST,"", req,CommentListResponseVo.class ,listener) ;
    }

    /**
     * 3.5.4	评论点赞
     *
     * @param listener
     * @return
     */
    public static RequestCall bookLike (Activity context, BookLikeReq req, HttpResultListener<BookResponseVo> listener) {
        return FFOkHttpHelper.post( CommonUrlManager.URL_COMMENTLIKE, req, BookResponseVo.class, listener,DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }

    /**
     * 3.5.5	评论回复
     *
     * @param listener
     * @return
     */
    public static RequestCall reply (Activity context, ReplyReq req , HttpResultListener<ReplyResponseVo> listener) {
        return FFOkHttpHelper.post( CommonUrlManager.URL_REPLY, req, ReplyResponseVo.class, listener, DialogUtil.creatRequestDialog(context,"正在提交"));
    }

    /**
     * 3.5.6	获取评论回复列表
     * @param
     * @param listener
     * @return
     */
    public static RequestCall replyList (ReplyListReq req, HttpResultListener<ReplyListResponseVo> listener) {
        return FFOkHttpHelper.get( CommonUrlManager.URL_REPLYLIST,"", req,ReplyListResponseVo.class ,listener) ;
    }

    /**
     * 3.5.10评论删除
     * @param
     * @param listener
     * @return
     */
    public static RequestCall deleteComment (Activity context,CommentReq req, HttpResultListener<BaseResponseVo> listener) {
        return FFOkHttpHelper.post( CommonUrlManager.URL_DELETECOMMENT, req,BaseResponseVo.class ,listener,DialogUtil.creatRequestDialog(context, "正在提交")) ;
    }

    /**
     * 3.5.12评论举报
     * @param
     * @param listener
     * @return
     */
    public static RequestCall reportComment (Activity context, ReportCommentReq req, HttpResultListener<BaseResponseVo> listener) {
        return FFOkHttpHelper.post( CommonUrlManager.URL_REPORTCOMMENT, req,BaseResponseVo.class ,listener,DialogUtil.creatRequestDialog(context, "正在提交")) ;
    }

    /**
     * 3.5.13回复举报
     * @param
     * @param listener
     * @return
     */
    public static RequestCall reportReply(Activity context, ReportCommentReq req, HttpResultListener<BaseResponseVo> listener) {
        return FFOkHttpHelper.post( CommonUrlManager.URL_REPORTREPLY, req,BaseResponseVo.class ,listener,DialogUtil.creatRequestDialog(context, "正在提交")) ;
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
        return FFOkHttpHelper.postFile(CommonUrlManager.URL_HEAD_UPLOAD, requestTag, maps, req, HeadUploadResponseVo.class, resultListener) ;
    }

    /**
     * 3.4.1 图书搜索
     * @param resultListener
     * @return
     */
    public static RequestCall searchBooks(SearchBookReq req,HttpResultListener<SearchBookResp> resultListener) {
        return FFOkHttpHelper.get(CommonUrlManager.URL_LIBRARY_SEARCH, "", req, SearchBookResp.class, resultListener) ;
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
        return FFOkHttpHelper.get(CommonUrlManager.URL_BOOK, requestTag, req, BookResponseVo.class, listener);
    }


    /**
     * 3.4.4 获取图书的标签
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getLibraryTag(String requestTag , HttpResultListener<GetTagResp> listener) {
        GetTagReq req = new GetTagReq() ;
        return FFOkHttpHelper.get(CommonUrlManager.URL_LIBRARY_TAG, requestTag, req, GetTagResp.class, listener);
    }
    /**
     * 3.4.4 获取感兴趣的标签
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getMyInterestTag(String requestTag , HttpResultListener<GetMyInterestTagResp> listener) {
        GetMyInterestTagReq req = new GetMyInterestTagReq() ;
        return FFOkHttpHelper.get(CommonUrlManager.URL_LIBRARY_INTERERST_TAG, requestTag, req, GetMyInterestTagResp.class, listener);
    }

    /**
     * 3.4.4 更新感兴趣的标签
     * @param listener
     * @return
     */
    public static RequestCall updateMyInterestTag(Activity context,List<Long> tags, HttpResultListener<UpdateInterestTagResp> listener) {
        UpdateInterestTagReq req = new UpdateInterestTagReq() ;
        req.idArr =  new Gson().toJson(tags) ;
        return FFOkHttpHelper.post(CommonUrlManager.URL_UPDATE_INTERERST_TAG, req , UpdateInterestTagResp.class, listener,DialogUtil.creatRequestDialog(context, "正在提交"));
    }

    /**
     * 3.4.8	收藏
     * @param id
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall collect(Long id ,int type, String requestTag , HttpResultListener<BookResponseVo> listener) {
        CollectReq req = new CollectReq() ;
        req.id = id ;
        req.collectType = type ;
        return FFOkHttpHelper.post(CommonUrlManager.URL_COLLECT, requestTag, req, BookResponseVo.class, listener);
    }


    /**
     * 3.4.9	获取收藏列表
     * @param listener
     * @return
     */
    public static RequestCall collectList(BasePageReq req, HttpResultListener<CollectListResp> listener) {

        return FFOkHttpHelper.get(CommonUrlManager.URL_COLLECT, "", req, CollectListResp.class, listener);
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
        return FFOkHttpHelper.post(CommonUrlManager.URL_RESERVE,requestTag , req ,ReserveResponseVo.class ,listener);
    }

    /**
     * 3.4.7	预借列表
     * @param listener
     * @return
     */
    public static RequestCall reserveList(BasePageReq req, HttpResultListener<ReserveListResp> listener) {

        return FFOkHttpHelper.get(CommonUrlManager.URL_RESERVE,"" , req ,ReserveListResp.class ,listener);
    }

    /**
     * 3.4.10获取借阅列表
     * @param listener
     * @return
     */
    public static RequestCall borrowingList(BasePageReq req , HttpResultListener<BrrowingListResp> listener) {
        return FFOkHttpHelper.get(CommonUrlManager.URL_BRROWING,"" , req ,BrrowingListResp.class ,listener);
    }

    /**
     * 3.4.11获取借阅历史
     * @param listener
     * @return
     */
    public static RequestCall borrowedList(BasePageReq req , HttpResultListener<BorrowedListResp> listener) {

        return FFOkHttpHelper.get(CommonUrlManager.URL_BRROWED,"" , req ,BorrowedListResp.class ,listener);
    }


    /**
     * 3.4.12借阅延期
     * @param id
     * @param listener
     * @return
     */
    public static RequestCall borrowExtension(Activity context,long id ,HttpResultListener<ExtensionResp> listener) {
        ExtensionReq req = new ExtensionReq() ;
        req.id = id ;
        return FFOkHttpHelper.post(CommonUrlManager.URL_BRROW_EXTENSION , req ,ExtensionResp.class ,listener,DialogUtil.creatRequestDialog(context, "正在提交"));
    }

    /**
     * 3.4.13 获取推荐图书列表
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall getRecomandBooks (String requestTag ,HttpResultListener<GetRecomandBookResp> listener) {
        GetRecomandBookReq req = new GetRecomandBookReq() ;
        return FFOkHttpHelper.get(CommonUrlManager.URL_INTEREST_LIBRARY_LISTS ,requestTag, req,GetRecomandBookResp.class ,listener) ;
    }

    /**
     * 3.4.14取消预借
     * @param id 预借id
     * @param listener
     * @return
     */
    public static RequestCall unReserve(Activity context,long id, HttpResultListener<CancelReserveResp> listener) {
        BaseReq req = new BaseReq() ;
        req.id = id ;
        return FFOkHttpHelper.post(CommonUrlManager.URL_CANCELRESERVE, req ,CancelReserveResp.class ,listener,DialogUtil.creatRequestDialog(context, "正在提交"));
    }

    /**
     * 3.5.7	获取我的评论
     * @param listener
     * @return
     */
    public static RequestCall myComment (BasePageReq req, HttpResultListener<MyCommentResponseVo> listener) {
        return FFOkHttpHelper.get(CommonUrlManager.URL_MYCOMMENT ,"", req,MyCommentResponseVo.class ,listener) ;
    }
}
