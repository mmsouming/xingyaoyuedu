package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 关联类型
 *
 */
public enum LinkTypeEnum implements BaseEnum {
	/**
	 * 关联
	 */
	LINK(1, "关联"),
	/**
	 * 取消关联
	 */
	CANCEL_LINK(2, "取消关联");

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, LinkTypeEnum> noMap = new HashMap<Integer, LinkTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (LinkTypeEnum e : LinkTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, LinkTypeEnum> nameMap = new HashMap<String, LinkTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (LinkTypeEnum e : LinkTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, LinkTypeEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, LinkTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private LinkTypeEnum(Integer no, String name) {
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
