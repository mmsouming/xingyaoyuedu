package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.TeacherSchoolVo;

/**
 * 教师学校
 * */
public class CardResponseVo extends BaseResponseVo {

    private TeacherSchoolVo teacherSchoolVo;//教师学校


    public TeacherSchoolVo getTeacherSchoolVo() {
        return teacherSchoolVo;
    }

    public void setTeacherSchoolVo(TeacherSchoolVo teacherSchoolVo) {
        this.teacherSchoolVo = teacherSchoolVo;
    }

}
