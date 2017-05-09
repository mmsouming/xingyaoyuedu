package com.shinread.StarPlan.Parent.engin.net;

import android.app.Activity;
import android.text.TextUtils;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.FFOkHttpHelper;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CardClassReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CardClassResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ClassListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ClassListResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MyCommentResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.NewReplyNoResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ReplyNoticeListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ScoreResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SettingDefaultStuReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.SettingDefaultStuResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.ShareWorkReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.StudentRegisterReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.StudentRegisterResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UnfinishWorkReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UnfinishWorkResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateStudentInfoReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.UpdateStudentInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkGetReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkGetResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkListResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.WorkResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.request.RequestCall;
import com.fancyfamily.primarylibrary.commentlibrary.util.DialogUtil;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by janecer on 2016/3/9.
 * email:jxc@fancyf.cn
 * des:网络请求相关接口
 */
public class AppModel extends CommonAppModel{





    /**
     * 根据code 获取学校班级信息
     * @param code
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall schoolClassGetByCode(String code , String requestTag , HttpResultListener<CardClassResponseVo> listener) {
        CardClassReq req = new CardClassReq() ;
        req.password = code ;
        return FFOkHttpHelper.get(UrlManager.URL_SCHOOL_CLASS_GET, requestTag, req, CardClassResponseVo.class, listener) ;
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
     * 3.5.7	获取我的评论
     * @param listener
     * @return
     */
    public static RequestCall myComment (BasePageReq req, HttpResultListener<MyCommentResponseVo> listener) {
        return FFOkHttpHelper.get(UrlManager.URL_MYCOMMENT ,"", req,MyCommentResponseVo.class ,listener) ;
    }

    /**
     * 3.5.8	获取新回复数
     * @param requestTag
     * @param listener
     * @return
     */
    public static RequestCall newReplyNo( String requestTag , HttpResultListener<NewReplyNoResponseVo> listener) {
        BaseReq req = new BaseReq();
        return FFOkHttpHelper.get(UrlManager.URL_NEWREPLYNO ,requestTag, req,NewReplyNoResponseVo.class ,listener) ;
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
     * @param listener
     * @return
     */
    public static RequestCall workList (BasePageReq req , HttpResultListener<WorkListResponseVo> listener) {

        return FFOkHttpHelper.get(UrlManager.URL_WORKLIST ,"", req,WorkListResponseVo.class ,listener) ;
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
     * 3.6.5分享作业
     * @param workId
     * @param listener
     * @return
     */
    public static RequestCall shareWork(Activity context,Long workId , HttpResultListener<BaseResponseVo> listener) {
        ShareWorkReq req = new ShareWorkReq() ;
        req.id = workId ;
        return FFOkHttpHelper.post(UrlManager.URL_WORK_SHOW ,req,BaseResponseVo.class ,listener, DialogUtil.creatRequestDialog(context,"正在提交")) ;
    }

    /**
     * 3.2.11获取积分记录
     */
    public static RequestCall score (BasePageReq req , HttpResultListener<ScoreResponseVo> listener) {

        return FFOkHttpHelper.get(UrlManager.URL_SCORE ,"", req,ScoreResponseVo.class ,listener) ;
    }
}
