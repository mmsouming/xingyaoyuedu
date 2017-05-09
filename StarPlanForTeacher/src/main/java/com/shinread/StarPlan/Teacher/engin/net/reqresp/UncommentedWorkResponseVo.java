package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.WorkVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;


public class UncommentedWorkResponseVo extends BaseResponseVo {
 
    private List<WorkVo> workVoArr;
    
    private Integer pageRecordNo;
     


    /**
     * @return the workVoArr
     */
    public List<WorkVo> getWorkVoArr() {
        return workVoArr;
    }

    /**
     * @param workVoArr the workVoArr to set
     */
    public void setWorkVoArr(List<WorkVo> workVoArr) {
        this.workVoArr = workVoArr;
    }

    /**
     * @return the pageRecordNo
     */
    public Integer getPageRecordNo() {
        return pageRecordNo;
    }

    /**
     * @param pageRecordNo the pageRecordNo to set
     */
    public void setPageRecordNo(Integer pageRecordNo) {
        this.pageRecordNo = pageRecordNo;
    }

    
      
}
