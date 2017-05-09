package com.shinread.StarPlan.Teacher.engin.net.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;
import com.shinread.StarPlan.Teacher.bean.StudentListVo;

import java.util.List;

/**
 * 5.3.4    获取学生列表
 * */
public class StudentListResponseVo extends BaseResponseVo {
    private List<StudentListVo> studentListVoArr;//学生列表
    
    private String name;//班级显示名



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentListVo> getStudentListVoArr() {
        return studentListVoArr;
    }

    public void setStudentListVoArr(List<StudentListVo> studentListVoArr) {
        this.studentListVoArr = studentListVoArr;
    }

}
