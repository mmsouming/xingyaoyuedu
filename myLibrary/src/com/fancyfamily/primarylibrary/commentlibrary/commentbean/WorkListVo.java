package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

public class WorkListVo {
    private Long id;// 作业Id

    private String  name;// 作业题目

    private String effectiveTime;// 开始时间

    private String invalidTime;// 结束时间

    private Long timestamp;// 开始时间戳
    
    private Integer workStatus;//作业状态
    
    private Integer workLevel;//作业评级

	private Integer disposeProgress;//处理进度

    public WorkListVo() {
        // TODO Auto-generated constructor stub
    }
 
    public WorkListVo(Long id, String name, String effectiveTime,
			String invalidTime, Long timestamp, Integer workStatus,
			Integer workLevel) {
		super();
		this.id = id;
		this.name = name;
		this.effectiveTime = effectiveTime;
		this.invalidTime = invalidTime;
		this.timestamp = timestamp;
		this.workStatus = workStatus;
		this.workLevel = workLevel;
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



	public String getEffectiveTime() {
		return effectiveTime;
	}



	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}



	public String getInvalidTime() {
		return invalidTime;
	}



	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}



	public Long getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}



	public Integer getWorkStatus() {
		return workStatus;
	}



	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}



	public Integer getWorkLevel() {
		return workLevel;
	}



	public void setWorkLevel(Integer workLevel) {
		this.workLevel = workLevel;
	}


	public Integer getDisposeProgress() {
		return disposeProgress;
	}

	public void setDisposeProgress(Integer disposeProgress) {
		this.disposeProgress = disposeProgress;
	}
	 
}
