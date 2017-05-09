package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkQuestionVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

public class WorkGetResponseVo extends BaseResponseVo {
    private WorkVo work;
    private WorkQuestionVo workQuestionVo;
     

	public WorkGetResponseVo(Integer statusCode, String statusMsg) {
	}

	public WorkGetResponseVo(WorkVo work,WorkQuestionVo workQuestionVo) {
        this.work = work;
        this.workQuestionVo = workQuestionVo;
	}

    /**
     * @return the work
     */
    public WorkVo getWork() {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(WorkVo work) {
        this.work = work;
    }

    /**
     * @return the workQuestionVo
     */
    public WorkQuestionVo getWorkQuestionVo() {
        return workQuestionVo;
    }

    /**
     * @param workQuestionVo the workQuestionVo to set
     */
    public void setWorkQuestionVo(WorkQuestionVo workQuestionVo) {
        this.workQuestionVo = workQuestionVo;
    }
	
	 
}
