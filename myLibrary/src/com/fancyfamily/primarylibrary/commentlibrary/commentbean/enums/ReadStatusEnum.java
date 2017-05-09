package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 阅读状态
 *
 */
public enum ReadStatusEnum implements BaseEnum {
    /**
     * 未读
     */
    NO_READ_MSG(1, "未读"),
    /**
     * 己读
     */
    HAS_READ_MSG(2, "己读")
    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, ReadStatusEnum> noMap = new HashMap<Integer, ReadStatusEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (ReadStatusEnum e : ReadStatusEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };
    
    /**
     * 编号转换Map
     */
    private final static Map<String, ReadStatusEnum> nameMap = new HashMap<String, ReadStatusEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (ReadStatusEnum e : ReadStatusEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, ReadStatusEnum> getNoMap() {
        return noMap;
    }
    
    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, ReadStatusEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private ReadStatusEnum(Integer no, String name) {
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
