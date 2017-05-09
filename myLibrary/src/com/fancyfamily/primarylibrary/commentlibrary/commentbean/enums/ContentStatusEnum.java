package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 内容状态
 */
public enum ContentStatusEnum implements BaseEnum {
	/**
	 * 正常
	 */
    NORMAL(1, "正常"),
	/**
	 * 用户删除
	 */
    USER_DELETE(2, "用户删除"),
	/**
	 * 举报删除
	 */
    REPORT_DELETION(3, "举报删除"),
	/**
	 * 帖子删除
	 */
    POST_DELETE(4, "帖子删除")
	;
	
	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, ContentStatusEnum> noMap = new HashMap<Integer, ContentStatusEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (ContentStatusEnum e : ContentStatusEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, ContentStatusEnum> nameMap = new HashMap<String, ContentStatusEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (ContentStatusEnum e : ContentStatusEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, ContentStatusEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, ContentStatusEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private ContentStatusEnum(Integer no, String name) {
		this.no = no;
		this.name = name;
	}

	@Override
	public Integer getNo() {
		return no;
	}

	@Override
	public String getName() {
		return name;
	}

}
