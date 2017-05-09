package com.fancyfamily.primarylibrary.commentlibrary.commentbean;


/**
 * 借阅vo
 * 
 * @author stsu
 * */
public class BorrowVo {
	
	private Long borrowId;
	
    private Long id;// 书目id

    private String name;// 书目名

    private String coverUrl;// 封面URL

    private Integer extensionNo;// 可延期次数

    private String date;// 借阅到期日期/归还日期

	public Long timestamp;//时间戳

    private Integer borrowStatus;// 借阅状态

    private Integer freeStatus;// 冻结状态
 
    private String borrowDate;//借阅日期 
     
    
    public BorrowVo() {
    }
    
    
    public BorrowVo(Long borrowId,Long id, String name, String coverUrl, 
            Integer extensionNo, String date, Integer borrowStatus,
            Integer freeStatus,String borrowDate) {
    	this.borrowId = borrowId;
        this.id = id;
        this.name = name;
        this.coverUrl = coverUrl; 
        this.extensionNo = extensionNo;
        this.date = date;
        this.borrowStatus = borrowStatus;
        this.freeStatus = freeStatus;
        this.borrowDate = borrowDate;
        
    }


	public Long getBorrowId() {
		return borrowId;
	}


	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
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


	public String getCoverUrl() {
		return coverUrl;
	}


	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}


	public Integer getExtensionNo() {
		return extensionNo;
	}


	public void setExtensionNo(Integer extensionNo) {
		this.extensionNo = extensionNo;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Integer getBorrowStatus() {
		return borrowStatus;
	}


	public void setBorrowStatus(Integer borrowStatus) {
		this.borrowStatus = borrowStatus;
	}


	public Integer getFreeStatus() {
		return freeStatus;
	}


	public void setFreeStatus(Integer freeStatus) {
		this.freeStatus = freeStatus;
	}


	public String getBorrowDate() {
		return borrowDate;
	}


	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

     
}
