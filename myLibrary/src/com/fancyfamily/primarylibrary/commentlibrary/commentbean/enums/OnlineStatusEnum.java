package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 在线状态
 * 
 *
 */
public enum OnlineStatusEnum implements BaseEnum {
	/**
	 * 在线
	 */
	ON_LINE(1, "在线"),
	/**
	 * 离线
	 */
	OFF_LINE(2, "离线");

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, OnlineStatusEnum> noMap = new HashMap<Integer, OnlineStatusEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (OnlineStatusEnum e : OnlineStatusEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, OnlineStatusEnum> nameMap = new HashMap<String, OnlineStatusEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (OnlineStatusEnum e : OnlineStatusEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, OnlineStatusEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, OnlineStatusEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private OnlineStatusEnum(Integer no, String name) {
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
