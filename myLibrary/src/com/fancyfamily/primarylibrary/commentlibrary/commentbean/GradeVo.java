package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import java.io.Serializable;
import java.util.List;

/**
 * 年级vo
 * */
public class GradeVo implements Serializable {
    public String name;// 年级名

    public List<GenericListVo> classesVoArr;// 班级列表


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GenericListVo> getClassesVoArr() {
        return classesVoArr;
    }

    public void setClassesVoArr(List<GenericListVo> classesVoArr) {
        this.classesVoArr = classesVoArr;
    }


}
