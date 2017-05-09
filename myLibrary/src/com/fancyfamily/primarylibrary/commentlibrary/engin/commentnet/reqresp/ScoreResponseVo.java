package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ScoreVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;


public class ScoreResponseVo extends BaseResponseVo {
    private List<ScoreVo> scoreVoArr;//积分记录vo
    
    private Integer pageRecordNo;//当前记录页数

    public List<ScoreVo> getScoreVoArr() {
        return scoreVoArr;
    }
    public void setScoreVoArr(List<ScoreVo> scoreVoArr) {
        this.scoreVoArr = scoreVoArr;
    }
    public Integer getPageRecordNo() {
        return pageRecordNo;
    }
    public void setPageRecordNo(Integer pageRecordNo) {
        this.pageRecordNo = pageRecordNo;
    }

}
