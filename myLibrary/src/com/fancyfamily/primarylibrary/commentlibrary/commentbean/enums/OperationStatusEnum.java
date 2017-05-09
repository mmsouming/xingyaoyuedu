package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作状态
 *
 */
public enum OperationStatusEnum implements BaseEnum {
	/**
	 * 己操作
	 */
	ALREADY_OPERATE(1, "己操作"),
	/**
	 * 未操作/取消操作
	 */
	CANCEL_OPERATION(2, "未操作/取消操作"),
	 
	;
	
	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, OperationStatusEnum> noMap = new HashMap<Integer, OperationStatusEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (OperationStatusEnum e : OperationStatusEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, OperationStatusEnum> nameMap = new HashMap<String, OperationStatusEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (OperationStatusEnum e : OperationStatusEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, OperationStatusEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, OperationStatusEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private OperationStatusEnum(Integer no, String name) {
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
