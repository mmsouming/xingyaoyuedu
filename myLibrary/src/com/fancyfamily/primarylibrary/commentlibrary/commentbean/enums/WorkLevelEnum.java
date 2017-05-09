package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 作业评级
 * 
 * @author Stsu
 *
 */
public enum WorkLevelEnum implements BaseEnum {
    /**
     * 未评
     */
    UNCOMMENT(0, "未评"),
    /**
     * A级
     */
    LEVEL_A(1, "A级"),
    /**
     * 被打回
     */
    LEVEL_B(2, "B级"),
    /**
     * 己提交
     */
    LEVEL_C(3, "C级"),
    /**
     * 己点评
     */
    LEVEL_D(4, "D级"),
    /**
     * 己点评
     */
    LEVEL_E(5, "E级"),
      
    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, WorkLevelEnum> noMap = new HashMap<Integer, WorkLevelEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (WorkLevelEnum e : WorkLevelEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, WorkLevelEnum> nameMap = new HashMap<String, WorkLevelEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (WorkLevelEnum e : WorkLevelEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, WorkLevelEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, WorkLevelEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private WorkLevelEnum(Integer no, String name) {
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
