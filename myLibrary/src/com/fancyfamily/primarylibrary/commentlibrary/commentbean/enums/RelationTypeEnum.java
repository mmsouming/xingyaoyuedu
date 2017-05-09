package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 关系
 *
 */
public enum RelationTypeEnum implements BaseEnum {
	/**
	 * 爸爸
	 */
	DAD(1, "爸爸"),
	/**
	 * 妈妈
	 */
	MOM(2, "妈妈"),
	/**
     * 其他
     */
    OTHER(100, "其他")
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, RelationTypeEnum> noMap = new HashMap<Integer, RelationTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (RelationTypeEnum e : RelationTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, RelationTypeEnum> nameMap = new HashMap<String, RelationTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (RelationTypeEnum e : RelationTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, RelationTypeEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, RelationTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private RelationTypeEnum(Integer no, String name) {
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
