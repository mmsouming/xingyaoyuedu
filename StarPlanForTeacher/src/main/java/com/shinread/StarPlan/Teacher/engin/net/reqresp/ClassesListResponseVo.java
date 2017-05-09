package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.shinread.StarPlan.Teacher.bean.SchoolListVo;

import java.util.List;

/**
 *
 * 5.3.3    获取执教班级列表
 * */
public class ClassesListResponseVo extends BaseResponseVo {

    private List<SchoolListVo> schoolListVoArr;// 所属学校（手机端只处理第一个学校）

    private List<Long> idArr;// 执教班级



    public List<SchoolListVo> getSchoolListVoArr() {
        return schoolListVoArr;
    }

    public void setSchoolListVoArr(List<SchoolListVo> schoolListVoArr) {
        this.schoolListVoArr = schoolListVoArr;
    }

    public List<Long> getIdArr() {
        return idArr;
    }

    public void setIdArr(List<Long> idArr) {
        this.idArr = idArr;
    }

}
