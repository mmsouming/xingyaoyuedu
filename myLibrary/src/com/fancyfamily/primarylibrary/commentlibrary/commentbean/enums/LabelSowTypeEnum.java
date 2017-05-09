package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 显示类型
 *
 */
public enum LabelSowTypeEnum implements BaseEnum {
	/**
	 * 图片
	 */
	PICTURES(1, "图片"),
	/**
	 * 文字
	 */
	WORDS(2, "文字"),
	/**
	 * 图上文下
	 * */
	FIGURE_BELOW(10, "图上文下"),
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, LabelSowTypeEnum> noMap = new HashMap<Integer, LabelSowTypeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (LabelSowTypeEnum e : LabelSowTypeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, LabelSowTypeEnum> nameMap = new HashMap<String, LabelSowTypeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (LabelSowTypeEnum e : LabelSowTypeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, LabelSowTypeEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, LabelSowTypeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private LabelSowTypeEnum(Integer no, String name) {
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
