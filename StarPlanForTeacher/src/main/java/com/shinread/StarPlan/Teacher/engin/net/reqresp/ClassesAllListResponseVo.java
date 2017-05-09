package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.shinread.StarPlan.Teacher.bean.SchoolVo;

import java.util.List;

/**
 * 5.3.1    获取全部班级列表
 * */
public class ClassesAllListResponseVo extends BaseResponseVo {
    private List<SchoolVo> schoolVoArr;//所属学校（手机端只处理第一个学校）

    private List<Long> idArr;//执教班级



    public List<SchoolVo> getSchoolVoArr() {
        return schoolVoArr;
    }

    public void setSchoolVoArr(List<SchoolVo> schoolVoArr) {
        this.schoolVoArr = schoolVoArr;
    }

    public List<Long> getIdArr() {
        return idArr;
    }

    public void setIdArr(List<Long> idArr) {
        this.idArr = idArr;
    }

}
