package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 举报类型
 */
public enum InformantTypeEnum implements BaseEnum {
	/**
	 * 色情
	 */
    EROTIC(1, "色情"),
	/**
	 * 暴力
	 */
    VIOLENCE(2, "暴力"),
    /**
     * 其他（默认）
     * */
    OTHER(99,"其他（默认）");

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, InformantTypeEnum> noMap = new HashMap<Integer, InformantTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (InformantTypeEnum e : InformantTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, InformantTypeEnum> nameMap = new HashMap<String, InformantTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (InformantTypeEnum e : InformantTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, InformantTypeEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, InformantTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private InformantTypeEnum(Integer no, String name) {
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
