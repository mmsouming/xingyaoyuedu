package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 冻结状态
 * 
 * @author Bin
 *
 */
public enum FreeStatusEnum implements BaseEnum {
    /**
     * 解冻
     */
    UNFROZEN(1, "未冻结"),
    /**
     * 冻结
     */
    FROZEN(2, "已冻结"),


    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, FreeStatusEnum> noMap = new HashMap<Integer, FreeStatusEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (FreeStatusEnum e : FreeStatusEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, FreeStatusEnum> nameMap = new HashMap<String, FreeStatusEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (FreeStatusEnum e : FreeStatusEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, FreeStatusEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, FreeStatusEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private FreeStatusEnum(Integer no, String name) {
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
