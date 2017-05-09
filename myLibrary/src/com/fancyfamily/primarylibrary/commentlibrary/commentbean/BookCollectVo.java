package com.fancyfamily.primarylibrary.commentlibrary.commentbean;


/**
 * 收藏ＶＯ
 * 
 * @author stsu
 * */
public class BookCollectVo {
    private Long id;// 书目id

    private String name;// 书目名

    private String coverUrl;// 封面URL 

    private Integer recommend;// 推荐指数(满分100)
    
    private String time ;//收藏时间
    
    private Long timestamp;//收藏时间戳

    private Integer commentNo;// 评论数 
    
    private String introduction;//简介
	private boolean choosed = false;
    
    public BookCollectVo() {
    }

	public BookCollectVo(Long id, String name, String coverUrl, Integer recommend, String time, Long timestamp, Integer commentNo, String introduction, boolean choosed) {
		this.id = id;
		this.name = name;
		this.coverUrl = coverUrl;
		this.recommend = recommend;
		this.time = time;
		this.timestamp = timestamp;
		this.commentNo = commentNo;
		this.introduction = introduction;
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

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
}
