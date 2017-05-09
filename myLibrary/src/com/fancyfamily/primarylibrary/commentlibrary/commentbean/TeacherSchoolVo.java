package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

public class TeacherSchoolVo {
    private Long id;// 学校id

    private String name;// 学校名

    private String shinySn;// 璀璨序号

    private Integer defaultMark;// 默认标记
    
    private Integer isRelatedClass;//是否关联该校班级

    public TeacherSchoolVo() {
        // TODO Auto-generated constructor stub
    }
    public TeacherSchoolVo(Long id, String name, String shinySn,
            Integer defaultMark,Integer isRelatedClass) {
        this.id = id;
        this.name = name;
        this.shinySn = shinySn;
        this.defaultMark = defaultMark;
        this.isRelatedClass = isRelatedClass;
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

    public String getShinySn() {
        return shinySn;
    }

    public void setShinySn(String shinySn) {
        this.shinySn = shinySn;
    }

    public Integer getDefaultMark() {
        return defaultMark;
    }

    public void setDefaultMark(Integer defaultMark) {
        this.defaultMark = defaultMark;
    }

    public Integer getIsRelatedClass() {
        return isRelatedClass;
    }

    public void setIsRelatedClass(Integer isRelatedClass) {
        this.isRelatedClass = isRelatedClass;
    }

}
