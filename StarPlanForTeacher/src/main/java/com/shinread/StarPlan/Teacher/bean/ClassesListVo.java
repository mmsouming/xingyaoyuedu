package com.shinread.StarPlan.Teacher.bean;
/**
 * 班级显示vo
 * @author qhfang
 * */
public class ClassesListVo {
    private Long id;//

    private String name;// 班级名

    private Integer studentNo;// 学生数

    private Integer authNo;// 认证数
    private boolean choosed = false;//checkbox是否选择

    public ClassesListVo(Long id, String name, Integer studentNo, Integer authNo, boolean choosed) {
        this.id = id;
        this.name = name;
        this.studentNo = studentNo;
        this.authNo = authNo;
        this.choosed = choosed;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Integer studentNo) {
        this.studentNo = studentNo;
    }

    public Integer getAuthNo() {
        return authNo;
    }

    public void setAuthNo(Integer authNo) {
        this.authNo = authNo;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }
}
