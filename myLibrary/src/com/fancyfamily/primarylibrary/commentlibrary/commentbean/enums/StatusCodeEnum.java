package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 操作状态码
 * 
 */
public enum StatusCodeEnum implements BaseEnum {
	//基础状态代码[1, 100000)
    /**
	 * 操作成功
	 */
    OPERATION_SUCCESS (1, "1"),
    /**
     * 操作成功（但未登录）
     */
    OPERATION_SUCCESS_NOT_LOGIN(2, "2"),
    /**
     * 未登录
     */
    NOT_LOGIN(10, "10"),
    /**
     * 通用失败
     */
    GENERAL_FAILURE(20, "20"),
    
    //通用错误状态代码[100000, 300000)
    /**
     * 访问过频
     */
    ACCESS_OVER(100000, "100000"),
    /**
     * 参数有误
     */
    PARAMETER_ERROR(100001, "100001"),
    /**
     * 无权访问
     */
    UNAUTHORIZED_ACCESS(100002, "100002"),
    /**
     * 内容不存在
     */
    CONTENT_NOT_EXIST(100003, "100003"),
    
    //接口错误状态代码[300000, 600000)
    /**
     * 家长登录错误状态代码(不再尝试自动登录)
     * */
    LOGIN_ERROR(3020300,""),
    /**
     * 查询璀璨卡的有效性错误状态代码(无效果卡密)
     * */
    CARD_ERROR_NONE_100(3030100,""),
    /**
     * 查询璀璨卡的有效性错误状态代码(该卡己使用)
     * */
    CARD_ERROR_UESD_101(3030101,""),
    /**
     * 学生登记错误状态代码(无效果卡密)
     * */
    CARD_ERROR_NONE_200(3030200,""),
    /**
     * 学生登记错误状态代码(该卡己使用)
     * */
    CARD_ERROR_UESD_201(3030201,""),
    /**
     * 学生登记错误状态代码(璀璨卡与学校不匹配)
     * */
    CARD_SCHOOL_ERROR_202(3030202,""),
    /**
     * 更改璀璨卡错误状态代码(无效果卡密)
     * */
    CARD_ERROR_NONE_800(3030800,""),
    /**
     * 更改璀璨卡错误状态代码(该卡己使用)
     * */
    CARD_ERROR_UESD_801(3030801,""),
    /**
     * 参数不足(查询图书)
     * */
    INSUFFICIENT_PARAMETER(3040100,""),
    
    /**
     * 己到预借上限
     * */
    RESERVE_BORROW_LIMIT(3040600,""),
    
    
    /**
     * 该作业己提交
     * */
    WORK_HAD_COMMIT(3060400,""),
    
    /**
     * 该作业己结束
     * */
    WORK_HAD_END(3060401,""),
	;

	private Integer no;// 编号
	private String name;// 名字
	/**
	 * 编号转换Map
	 */
	private final static Map<Integer, StatusCodeEnum> noMap = new HashMap<Integer, StatusCodeEnum>() {
		private static final long serialVersionUID = 4844087599684960991L;

		{
			for (StatusCodeEnum e : StatusCodeEnum.values()) {
				put(e.getNo(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 */
	private final static Map<String, StatusCodeEnum> nameMap = new HashMap<String, StatusCodeEnum>() {
		private static final long serialVersionUID = 4844099684969108759L;

		{
			for (StatusCodeEnum e : StatusCodeEnum.values()) {
				put(e.getName(), e);
			}
		}
	};

	/**
	 * 编号转换Map
	 * 
	 * @return
	 */
	public static Map<Integer, StatusCodeEnum> getNoMap() {
		return noMap;
	}

	/**
	 * 名称转换Map
	 * 
	 * @return
	 */
	public static Map<String, StatusCodeEnum> getNameMap() {
		return nameMap;
	}

	/**
	 * 
	 * @param no
	 *            编号
	 * @param name
	 *            名字
	 */
	private StatusCodeEnum(Integer no, String name) {
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
