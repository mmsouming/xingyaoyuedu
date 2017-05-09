package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 有效性
 *
 */
public enum ValidityEnum implements BaseEnum {
	/**
	 * 有效
	 */
	VALID(1, "有效"),
	/**
	 * 无效
	 */
	INVALID(2, "无效")
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, ValidityEnum> noMap = new HashMap<Integer, ValidityEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (ValidityEnum e : ValidityEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, ValidityEnum> nameMap = new HashMap<String, ValidityEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (ValidityEnum e : ValidityEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, ValidityEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, ValidityEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private ValidityEnum(Integer no, String name) {
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
