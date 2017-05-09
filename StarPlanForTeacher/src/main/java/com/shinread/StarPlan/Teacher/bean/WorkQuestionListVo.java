package com.shinread.StarPlan.Teacher.bean;

import java.io.Serializable;

public class WorkQuestionListVo implements Serializable{
    private Long id;// 作业题目Id

    private String name;// 作业题目

    private String effectiveTime;// 开始时间

    private Integer disposeProgress;// 处理进度1、未开始2、进行中10、己到期20、己结束30、己取消

    private String classesName;// 班级名称

    private Integer distributionNo;// 分发数

    private Integer submitNo;// 提交数

    private Integer commentNo;// 点评数

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the effectiveTime
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * @param effectiveTime
     *            the effectiveTime to set
     */
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * @return the classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * @param classesName
     *            the classesName to set
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * @return the distributionNo
     */
    public Integer getDistributionNo() {
        return distributionNo;
    }

    /**
     * @param distributionNo
     *            the distributionNo to set
     */
    public void setDistributionNo(Integer distributionNo) {
        this.distributionNo = distributionNo;
    }

    /**
     * @return the submitNo
     */
    public Integer getSubmitNo() {
        return submitNo;
    }

    /**
     * @param submitNo
     *            the submitNo to set
     */
    public void setSubmitNo(Integer submitNo) {
        this.submitNo = submitNo;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public Integer getDisposeProgress() {
        return disposeProgress;
    }

    public void setDisposeProgress(Integer disposeProgress) {
        this.disposeProgress = disposeProgress;
    }

}
