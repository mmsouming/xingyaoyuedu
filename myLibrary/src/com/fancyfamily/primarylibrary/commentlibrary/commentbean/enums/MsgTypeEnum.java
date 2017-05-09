package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息状态
 * 
 * @author Bin
 *
 */
public enum MsgTypeEnum implements BaseEnum {
    /**
     * 静默消息
     */
    SILENCE_MSG(1, "静默消息"),
    /**
     * 弹屏消息
     */
    SCREEN_MSG(2, "弹屏消息"),

    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, MsgTypeEnum> noMap = new HashMap<Integer, MsgTypeEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (MsgTypeEnum e : MsgTypeEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, MsgTypeEnum> nameMap = new HashMap<String, MsgTypeEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (MsgTypeEnum e : MsgTypeEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, MsgTypeEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, MsgTypeEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private MsgTypeEnum(Integer no, String name) {
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
