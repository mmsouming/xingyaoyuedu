package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理状态
 * 
 *
 */
public enum DisposeTypeEnum implements BaseEnum {
	/**
	 * 未处理
	 */
	UN_DISPOSE(1, "未处理"),
	/**
	 * 己处理
	 */
	HAS_DISPOSE(2, "己处理");

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, DisposeTypeEnum> noMap = new HashMap<Integer, DisposeTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (DisposeTypeEnum e : DisposeTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, DisposeTypeEnum> nameMap = new HashMap<String, DisposeTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (DisposeTypeEnum e : DisposeTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, DisposeTypeEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, DisposeTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private DisposeTypeEnum(Integer no, String name) {
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
