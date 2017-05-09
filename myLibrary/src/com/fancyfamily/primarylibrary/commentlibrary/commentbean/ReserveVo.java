package com.fancyfamily.primarylibrary.commentlibrary.commentbean;
/**
 * 预借vo
 * */
public class ReserveVo {
    private Long id;// 书目id
    
    private Long reserveId;//预借id

    private String name;// 书目名

    private String coverUrl;// 封面URL

    private String locationTags;// 定位标签

    private String date;// 预借到期日期

    public ReserveVo() {
        // TODO Auto-generated constructor stub
    }

    public ReserveVo(Long id,Long reserveId, String name, String coverUrl,
    		String locationTags, String date) {
        this.id = id;
        this.reserveId = reserveId;
        this.name = name;
        this.coverUrl = coverUrl;
        this.locationTags = locationTags;
        this.date = date;
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

	public String getLocationTags() {
		return locationTags;
	}

	public void setLocationTags(String locationTags) {
		this.locationTags = locationTags;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

   
}
