package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息跳转
 * 
 * @author Bin
 *
 */
public enum MsgJumpEnum implements BaseEnum {

    /**
     * 无跳转
     */
	NO_JUMP(1, "无跳转"),
    /**
     * 借书记录
     */
	BORROW(2, "借书记录"),
	/**
     * 作业详情页
     */
    WORK_DETAIL(3,"作业详情页"),
    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, MsgJumpEnum> noMap = new HashMap<Integer, MsgJumpEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (MsgJumpEnum e : MsgJumpEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, MsgJumpEnum> nameMap = new HashMap<String, MsgJumpEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (MsgJumpEnum e : MsgJumpEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, MsgJumpEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, MsgJumpEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private MsgJumpEnum(Integer no, String name) {
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
