package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.shinread.StarPlan.Teacher.bean.WorkQuestionListVo;

import java.util.List;


public class WorkQuestionListResponseVo extends BaseResponseVo {
 
    private List<WorkQuestionListVo> workQuestionListVoArr;
     
    private Integer pageNo;

    /**
     * @return the workQuestionListVoArr
     */
    public List<WorkQuestionListVo> getWorkQuestionListVoArr() {
        return workQuestionListVoArr;
    }

    /**
     * @param workQuestionListVoArr the workQuestionListVoArr to set
     */
    public void setWorkQuestionListVoArr(
            List<WorkQuestionListVo> workQuestionListVoArr) {
        this.workQuestionListVoArr = workQuestionListVoArr;
    }

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

    
}
