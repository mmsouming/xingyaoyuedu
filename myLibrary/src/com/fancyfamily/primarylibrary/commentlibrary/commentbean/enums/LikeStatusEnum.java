package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 点赞状态
 *
 */
public enum LikeStatusEnum implements BaseEnum {
	/**
	 * 己赞
	 */
	HAS_LIKE(1, "己赞"),
	/**
	 * 未赞
	 */
	UN_LIKE(2, "未赞")
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, LikeStatusEnum> noMap = new HashMap<Integer, LikeStatusEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (LikeStatusEnum e : LikeStatusEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, LikeStatusEnum> nameMap = new HashMap<String, LikeStatusEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (LikeStatusEnum e : LikeStatusEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, LikeStatusEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, LikeStatusEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private LikeStatusEnum(Integer no, String name) {
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
