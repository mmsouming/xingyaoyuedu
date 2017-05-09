package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkListVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;


public class WorkListResponseVo extends BaseResponseVo {
    private List<WorkListVo> workListVoArr;
    
    private Integer pageRecordNo;

	public WorkListResponseVo(Integer statusCode, String statusMsg) {
	}

	public WorkListResponseVo(List<WorkListVo> workListVoArr) {
        this.workListVoArr = workListVoArr;
	}
	
	public WorkListResponseVo(List<WorkListVo> workListVoArr,Integer pageRecordNo) {
        this.workListVoArr = workListVoArr;
        this.pageRecordNo = pageRecordNo;
    }

	public List<WorkListVo> getWorkListVoArr() {
		return workListVoArr;
	}

	public void setWorkListVoArr(List<WorkListVo> workListVoArr) {
		this.workListVoArr = workListVoArr;
	}

     
    public Integer getPageRecordNo() {
        return pageRecordNo;
    }

     
    public void setPageRecordNo(Integer pageRecordNo) {
        this.pageRecordNo = pageRecordNo;
    } 
    
}
