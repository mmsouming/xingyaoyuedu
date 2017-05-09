package com.shinread.StarPlan.Teacher.bean;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.GradeVo;

import java.util.List;

/**
 * 学校vo
 * */
public class SchoolVo {
    private String name;// 学校名

    private List<GradeVo> gradeVoArr;// 班级列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GradeVo> getGradeVoArr() {
        return gradeVoArr;
    }

    public void setGradeVoArr(List<GradeVo> gradeVoArr) {
        this.gradeVoArr = gradeVoArr;
    }
}
