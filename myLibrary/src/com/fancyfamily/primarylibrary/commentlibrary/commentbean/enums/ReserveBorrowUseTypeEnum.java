package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 评论状态
 * 
 * @author Bin
 *
 */
public enum ReserveBorrowUseTypeEnum implements BaseEnum {
	/**
	 * 借阅
	 */
	BORROW(1, "借阅"),
	/**
	 * 取消
	 */
	CANCEL(2, "取消"),
	/**
	 * 过期
	 */
	EXPIRATION(3, "过期"),
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, ReserveBorrowUseTypeEnum> noMap = new HashMap<Integer, ReserveBorrowUseTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (ReserveBorrowUseTypeEnum e : ReserveBorrowUseTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, ReserveBorrowUseTypeEnum> nameMap = new HashMap<String, ReserveBorrowUseTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (ReserveBorrowUseTypeEnum e : ReserveBorrowUseTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, ReserveBorrowUseTypeEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, ReserveBorrowUseTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private ReserveBorrowUseTypeEnum(Integer no, String name) {
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
