package com.shinread.StarPlan.Teacher.engin.net;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseReq;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.FFOkHttpHelper;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.BasePageReq;
import com.fancyfamily.primarylibrary.commentlibrary.lib.zhy.http.okhttp.request.RequestCall;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.CardReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.CardResponseVo;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionCompleteTimeReq;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionInvalidataResp;
import com.shinread.StarPlan.Teacher.engin.net.reqresp.WorkQuestionListResponseVo;

/**
 * Created by janecer on 2016/4/13.
 * email:jxc@fancyf.cn
 * des:
 */
public class TeacherModel {


    /**
     * 激活教师端
     * @param password
     * @param tag
     * @param listener
     * @return
     */
    public static RequestCall cardActivate(String password , String tag , HttpResultListener<CardResponseVo> listener) {
        CardReq req = new CardReq() ;
        req.password = password ;
        return FFOkHttpHelper.post(UrlManager.URL_UPDATE_CARD,tag ,req,CardResponseVo.class,listener) ;
    }


    /**
     * 5.6.4获取我布置的作业列表（题目）
     * @param pageNo
     * @param rowSize
     * @param tag
     * @param listener
     * @return
     */
    public static RequestCall getTaskList(Integer pageNo , Integer rowSize , String tag , HttpResultListener<WorkQuestionListResponseVo> listener) {
        BasePageReq req = new BasePageReq() ;
        req.pageNo = pageNo ;
        req.rowSize = rowSize ;
        return FFOkHttpHelper.get(UrlManager.URL_GET_TAST_LIST,tag ,req,WorkQuestionListResponseVo.class,listener) ;
    }

    /**
     * 5.6.5获取作业（题目）
     * @param id
     * @param tag
     * @param listener
     * @return
     */
    public static RequestCall getTaskById(long id , String tag , HttpResultListener<WorkQuestionInvalidataResp> listener) {
        BaseReq req = new BaseReq() ;
        req.id = id ;
        return FFOkHttpHelper.get(UrlManager.URL_GET_TASKQUETION,tag ,req,WorkQuestionInvalidataResp.class,listener) ;
    }

    /**
     * 5.6.8失效作业（题目）
     * @param id
     * @param tag
     * @param listener
     * @return
     */
    public static RequestCall invalidateTask(long id , String tag , HttpResultListener<WorkQuestionInvalidataResp> listener) {
        BaseReq req = new BaseReq() ;
        req.id = id ;
        return FFOkHttpHelper.post(UrlManager.URL_TASK_INVALID,tag ,req,WorkQuestionInvalidataResp.class,listener) ;
    }

    /**
     * 5.6.9修改作业（题目）结束时间
     * @param id
     * @param tag
     * @param listener
     * @return
     */
    public static RequestCall updateCompleteTimeTask(long id ,long time , String tag , HttpResultListener<WorkQuestionInvalidataResp> listener) {
        WorkQuestionCompleteTimeReq req = new WorkQuestionCompleteTimeReq() ;
        req.id = id ;
        req.invalidTimestamp = time ;
        return FFOkHttpHelper.post(UrlManager.URL_TASK_COMPLETE_TIME,tag ,req,WorkQuestionInvalidataResp.class,listener) ;
    }


}
