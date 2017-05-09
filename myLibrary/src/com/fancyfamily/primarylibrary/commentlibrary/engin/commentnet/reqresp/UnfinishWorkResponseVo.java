package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkListVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;


public class UnfinishWorkResponseVo extends BaseResponseVo {
    private List<WorkListVo> workListVoArr;
     

	public UnfinishWorkResponseVo(Integer statusCode, String statusMsg) {
	}

	public UnfinishWorkResponseVo(List<WorkListVo> workListVoArr) {
        this.workListVoArr = workListVoArr;
	}
	
	 

	public List<WorkListVo> getWorkListVoArr() {
		return workListVoArr;
	}

	public void setWorkListVoArr(List<WorkListVo> workListVoArr) {
		this.workListVoArr = workListVoArr;
	}
 
}
