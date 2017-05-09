package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 回复通知类型
 *
 */
public enum ReplyNoticeTypeEnum implements BaseEnum {
	/**
	 * 回复
	 */
    REPLY(1, "REPLY"),
    /**
     * 点赞
     */
    LIKE(2, "LIKE"),
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, ReplyNoticeTypeEnum> noMap = new HashMap<Integer, ReplyNoticeTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (ReplyNoticeTypeEnum e : ReplyNoticeTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, ReplyNoticeTypeEnum> nameMap = new HashMap<String, ReplyNoticeTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (ReplyNoticeTypeEnum e : ReplyNoticeTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, ReplyNoticeTypeEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, ReplyNoticeTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private ReplyNoticeTypeEnum(Integer no, String name) {
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
