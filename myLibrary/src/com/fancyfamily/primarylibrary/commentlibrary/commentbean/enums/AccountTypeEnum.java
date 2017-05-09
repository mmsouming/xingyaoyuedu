package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 账号类型
 *
 */
public enum AccountTypeEnum implements BaseEnum {
	/**
	 * 平台账号
	 */
	PLATFORM_USER(1, "平台账号"),
	/**
	 * 家长账号
	 */
	PARENTS(2, "家长账号"),
	/**
	 * 教师账号
	 */
	TEACHER(3, "教师账号"),
	/**
	 * 图书馆账号
	 */
	LIB_USER(4, "图书馆账号"),
	/**
	 * 学生账号
	 */
	STUDENT(5, "学生账号")
	;
	
	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, AccountTypeEnum> noMap = new HashMap<Integer, AccountTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (AccountTypeEnum e : AccountTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, AccountTypeEnum> nameMap = new HashMap<String, AccountTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (AccountTypeEnum e : AccountTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, AccountTypeEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, AccountTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private AccountTypeEnum(Integer no, String name) {
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
