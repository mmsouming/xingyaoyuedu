package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 帐号状态
 *
 */
public enum SexTypeEnum implements BaseEnum {
	/**
	 * 男
	 */
	MALE(1, "男"),
	/**
	 * 女
	 */
	FEMALE(2, "女")

	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, SexTypeEnum> noMap = new HashMap<Integer, SexTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (SexTypeEnum e : SexTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, SexTypeEnum> nameMap = new HashMap<String, SexTypeEnum>() {
		private static final long serialVersionUID = -4844099684969108759L;
		{
			for (SexTypeEnum e : SexTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, SexTypeEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, SexTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private SexTypeEnum(Integer no, String name) {
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
