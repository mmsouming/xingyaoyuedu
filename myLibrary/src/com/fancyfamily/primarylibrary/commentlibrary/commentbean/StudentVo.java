package com.fancyfamily.primarylibrary.commentlibrary.commentbean;


import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DefaultMarkEnum;

import java.io.Serializable;

/**
 * 学生vo
 * */
public class StudentVo implements Serializable {
    public Long id;// 学生id

    public String name;// 学生名

    public String headUrl;// 学生头像url

    public Long schoolId;//学校ID

    public String schoolName;// 在读学校名

    public Long classesId;// 班级id

    public String classesName;// 班级信息

    public Integer sexType;// 性别

    public String birthday;// 生日（yyyy年MM月dd日）

    public Integer height;// 身高

    public Integer weight;// 体重

    public Integer defaultMark;// 是否默认
    public boolean isChooose;// 是否选中
    public Integer certificateStatus;// 认证状态

    public Integer score;//积分

    public Integer studentLevelType;//学生等级

    public Integer relationType;//关系

    public String shinySn;//璀璨序号


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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getSexType() {
        return sexType;
    }

    public void setSexType(Integer sexType) {
        this.sexType = sexType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(Integer certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStudentLevelType() {
        return studentLevelType;
    }

    public void setStudentLevelType(Integer studentLevelType) {
        this.studentLevelType = studentLevelType;
    }

    public Integer getDefaultMark() {
        return defaultMark;
    }

    public void setDefaultMark(Integer defaultMark) {
        this.defaultMark = defaultMark;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public Long getClassesId() {
        return classesId;
    }

    public void setClassesId(Long classesId) {
        this.classesId = classesId;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }


    public boolean isDefaultMark () {
        return defaultMark == DefaultMarkEnum.DEFULT.getNo();
    }
}
