package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 通知栏消息类型
 * 
 * @author Bin
 *
 */
public enum PushMsgTypeEnum implements BaseEnum {
    /**
     * 系统消息
     */
    SYSTEM_MSG(1, "系统消息"),
    /**
     * 回复通知
     */
    REPLY_NOTICE(2, " 回复通知"),
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, PushMsgTypeEnum> noMap = new HashMap<Integer, PushMsgTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (PushMsgTypeEnum e : PushMsgTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, PushMsgTypeEnum> nameMap = new HashMap<String, PushMsgTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (PushMsgTypeEnum e : PushMsgTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, PushMsgTypeEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, PushMsgTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private PushMsgTypeEnum(Integer no, String name) {
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
