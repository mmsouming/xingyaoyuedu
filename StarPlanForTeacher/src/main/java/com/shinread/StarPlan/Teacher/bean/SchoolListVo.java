package com.shinread.StarPlan.Teacher.bean;

import java.util.List;

public class SchoolListVo {
    private String name;// 学校名

    private List<ClassesListVo> classesListVoArr;// 班级列表

    public SchoolListVo(String name,List<ClassesListVo> classesListVoArr) {
        this.name = name;
        this.classesListVoArr = classesListVoArr;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassesListVo> getClassesListVoArr() {
        return classesListVoArr;
    }

    public void setClassesListVoArr(List<ClassesListVo> classesListVoArr) {
        this.classesListVoArr = classesListVoArr;
    }

}
