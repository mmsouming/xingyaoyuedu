package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 评论类型
 * 
 */
public enum CommentTypeEnum implements BaseEnum {
	/**
	 * 图书
	 */
    BOOK(1, "BOOK"),

	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, CommentTypeEnum> noMap = new HashMap<Integer, CommentTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (CommentTypeEnum e : CommentTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, CommentTypeEnum> nameMap = new HashMap<String, CommentTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (CommentTypeEnum e : CommentTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, CommentTypeEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, CommentTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private CommentTypeEnum(Integer no, String name) {
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
