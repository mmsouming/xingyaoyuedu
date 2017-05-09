package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import java.util.List;

public class WorkQuestionVo {
    private Long id;// 作业题目Id

    private String  name;// 作业题目

    private String content;// 作业内容
    
    private String effectiveTime;//开始时间

    private String invalidTime;// 结束时间

    private String classesName;// 班级名称
    
    private List<BookListVo> bookListVoArr;//作业图书范围
    
    private Integer disposeProgress;//处理进度
     

    public WorkQuestionVo() {
        // TODO Auto-generated constructor stub
    }


    public WorkQuestionVo(Long id, String name, String content,
            String effectiveTime, String invalidTime, String classesName,
            List<BookListVo> bookListVoArr, Integer disposeProgress) {
        super();
        this.id = id;
        this.name = name;
        this.content = content;
        this.effectiveTime = effectiveTime;
        this.invalidTime = invalidTime;
        this.classesName = classesName;
        this.bookListVoArr = bookListVoArr;
        this.disposeProgress = disposeProgress;
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
     * @return the effectiveTime
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }


    /**
     * @param effectiveTime the effectiveTime to set
     */
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }


    /**
     * @return the invalidTime
     */
    public String getInvalidTime() {
        return invalidTime;
    }


    /**
     * @param invalidTime the invalidTime to set
     */
    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
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
     * @return the bookListVoArr
     */
    public List<BookListVo> getBookListVoArr() {
        return bookListVoArr;
    }


    /**
     * @param bookListVoArr the bookListVoArr to set
     */
    public void setBookListVoArr(List<BookListVo> bookListVoArr) {
        this.bookListVoArr = bookListVoArr;
    }


    public Integer getDisposeProgress() {
        return disposeProgress;
    }
 
    public void setDisposeProgress(Integer disposeProgress) {
        this.disposeProgress = disposeProgress;
    }
  
}
