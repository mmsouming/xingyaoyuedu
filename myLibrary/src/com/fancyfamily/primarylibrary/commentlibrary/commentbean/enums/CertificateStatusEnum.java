package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证状态
 *
 */
public enum CertificateStatusEnum implements BaseEnum {
    /**
     * 未认证
     */
    NO_CERTIFICATE(1, "未认证"),
    /**
     * 已认证
     */
    HAS_CERTIFICATE(2, "已认证"),
	/**
     * 踢除
     */
	ELIMINATE(3, "踢除"),
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, CertificateStatusEnum> noMap = new HashMap<Integer, CertificateStatusEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (CertificateStatusEnum e : CertificateStatusEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};
	
	/**
	 * 编号转换Map
	 */
	private final static Map<String, CertificateStatusEnum> nameMap = new HashMap<String, CertificateStatusEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (CertificateStatusEnum e : CertificateStatusEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, CertificateStatusEnum> getNoMap() {
		return noMap;
	}
	
	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, CertificateStatusEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private CertificateStatusEnum(Integer no, String name) {
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
