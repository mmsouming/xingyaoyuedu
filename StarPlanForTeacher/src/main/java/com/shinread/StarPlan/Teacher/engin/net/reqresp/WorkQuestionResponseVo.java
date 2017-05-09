package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkQuestionVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;

import java.util.List;


public class WorkQuestionResponseVo extends BaseResponseVo {
 
    private WorkQuestionVo workQuestionVo;//作业题目vo 5.6.5   获取作业题目 专属
    
    private List<WorkQuestionListVo> workQuestionListVoArr;//作业题目列表 5.6.1    布置作业（作业题目）专属
     



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

    public List<WorkQuestionListVo> getWorkQuestionListVoArr() {
        return workQuestionListVoArr;
    }

    public void setWorkQuestionListVoArr(List<WorkQuestionListVo> workQuestionListVoArr) {
        this.workQuestionListVoArr = workQuestionListVoArr;
    }
	
	 
}
