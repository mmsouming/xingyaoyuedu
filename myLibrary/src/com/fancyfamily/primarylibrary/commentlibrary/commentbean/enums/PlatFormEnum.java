package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 手机类型
 * 
 */
public enum PlatFormEnum implements BaseEnum {
	/**
	 * 安卓
	 */
    ANDROID(1, "ANDROID"),
    /**
     * 苹果
     */
    IOS(2, "IOS")

	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, PlatFormEnum> noMap = new HashMap<Integer, PlatFormEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (PlatFormEnum e : PlatFormEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, PlatFormEnum> nameMap = new HashMap<String, PlatFormEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (PlatFormEnum e : PlatFormEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, PlatFormEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, PlatFormEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private PlatFormEnum(Integer no, String name) {
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
