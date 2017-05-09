package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 点击处理类型
 *
 */
public enum ClickTypeEnum implements BaseEnum {
	/**
	 * 跳h5
	 */
	UP_H5(1, "跳h5"),
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, ClickTypeEnum> noMap = new HashMap<Integer, ClickTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (ClickTypeEnum e : ClickTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, ClickTypeEnum> nameMap = new HashMap<String, ClickTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (ClickTypeEnum e : ClickTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, ClickTypeEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, ClickTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private ClickTypeEnum(Integer no, String name) {
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
