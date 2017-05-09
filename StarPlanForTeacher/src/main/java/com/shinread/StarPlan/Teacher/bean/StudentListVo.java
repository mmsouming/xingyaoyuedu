package com.shinread.StarPlan.Teacher.bean;

//学生列表
public class StudentListVo {
    private Long id;// 学生id

    private String name;// 学生名

    private String headUrl;// 头像url

    private Integer certificateStatus;// 认证状态

    private Integer distributionNo;// 分发数

    private Integer submitNo;// 提交数
    
    private Integer borrowNo;//借阅量
    
    private Integer finishNo;//完成度（0~100）
    private boolean choosed = false;//checkbox是否选择

    public StudentListVo(Long id, String name, String headUrl, Integer certificateStatus, Integer distributionNo, Integer submitNo, Integer borrowNo, Integer finishNo, boolean choosed) {
        this.id = id;
        this.name = name;
        this.headUrl = headUrl;
        this.certificateStatus = certificateStatus;
        this.distributionNo = distributionNo;
        this.submitNo = submitNo;
        this.borrowNo = borrowNo;
        this.finishNo = finishNo;
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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(Integer certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public Integer getDistributionNo() {
        return distributionNo;
    }

    public void setDistributionNo(Integer distributionNo) {
        this.distributionNo = distributionNo;
    }

    public Integer getSubmitNo() {
        return submitNo;
    }

    public void setSubmitNo(Integer submitNo) {
        this.submitNo = submitNo;
    }

    public Integer getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(Integer borrowNo) {
        this.borrowNo = borrowNo;
    }

    public Integer getFinishNo() {
        return finishNo;
    }

    public void setFinishNo(Integer finishNo) {
        this.finishNo = finishNo;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }
}
