package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 内容类型
 */
public enum ContentTypeEnum implements BaseEnum {
	/**
	 * 书评
	 */
	BOOK_COMMENT(1, "BOOK_COMMENT")
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, ContentTypeEnum> noMap = new HashMap<Integer, ContentTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (ContentTypeEnum e : ContentTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, ContentTypeEnum> nameMap = new HashMap<String, ContentTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (ContentTypeEnum e : ContentTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, ContentTypeEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, ContentTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private ContentTypeEnum(Integer no, String name) {
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
