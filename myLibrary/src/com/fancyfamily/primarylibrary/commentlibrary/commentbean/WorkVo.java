package com.fancyfamily.primarylibrary.commentlibrary.commentbean;


import java.util.List;

public class WorkVo {
    private Long id;// 作业Id

    private String  name;//作业题目

    private Long studentId;//学生id
    
    private String studentName;//学生名
    
    private String headUrl;//头像URL
    
    private String classesName;//班级名
    
    public List<String> pictureUrlArr;//图片url数组
    
    private String content;// 作业内容
    
    private BookListVo bookListVo;//作业图书

    private String time;//提交时间

    public Long timestamp;// 提交时间戳
    
    private Integer workLevel;//作业评级
    
    private String comment;//点评
    
    private Integer workStatus;//作业状态

    public boolean isExtend;

    public WorkVo() {
        // TODO Auto-generated constructor stub
    }


    public WorkVo(Long id, String name, Long studentId, String studentName,
            String headUrl, String classesName, List<String> pictureUrlArr,
            String content, BookListVo bookListVo, String time, Long timestamp,
            Integer workLevel, String comment,
            Integer workStatus) {
        super();
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.studentName = studentName;
        this.headUrl = headUrl;
        this.classesName = classesName;
        this.pictureUrlArr = pictureUrlArr;
        this.content = content;
        this.bookListVo = bookListVo;
        this.time = time;
        this.timestamp = timestamp; 
        this.workLevel = workLevel;
        this.comment = comment;
        this.workStatus = workStatus;
    }


    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }


    /**
     * @param id the id to set
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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the studentId
     */
    public Long getStudentId() {
        return studentId;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }


    /**
     * @return the studentName
     */
    public String getStudentName() {
        return studentName;
    }


    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    /**
     * @return the headUrl
     */
    public String getHeadUrl() {
        return headUrl;
    }


    /**
     * @param headUrl the headUrl to set
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    /**
     * @return the classesName
     */
    public String getClassesName() {
        return classesName;
    }


    /**
     * @param classesName the classesName to set
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }


     


	/**
     * @return the content
     */
    public String getContent() {
        return content;
    }


    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * @return the bookListVo
     */
    public BookListVo getBookListVo() {
        return bookListVo;
    }


    /**
     * @param bookListVo the bookListVo to set
     */
    public void setBookListVo(BookListVo bookListVo) {
        this.bookListVo = bookListVo;
    }


    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }


    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }


    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }


    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
 


    /**
     * @return the workLevel
     */
    public Integer getWorkLevel() {
        return workLevel;
    }


    /**
     * @param workLevel the workLevel to set
     */
    public void setWorkLevel(Integer workLevel) {
        this.workLevel = workLevel;
    }


    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }


    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


    /**
     * @return the workStatus
     */
    public Integer getWorkStatus() {
        return workStatus;
    }


    /**
     * @param workStatus the workStatus to set
     */
    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }


    
  
}
