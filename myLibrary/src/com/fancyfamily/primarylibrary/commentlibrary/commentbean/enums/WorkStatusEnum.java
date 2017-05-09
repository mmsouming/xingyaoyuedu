package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 作业状态
 *
 * @author Stsu
 *
 */
public enum WorkStatusEnum implements BaseEnum {
    /**
     * 未提交
     */
    UNCOMMITTED(1, "未提交"),
    /**
     * 被打回
     */
    REPULSE(2, "被打回"),
    /**
     * 己提交
     */
    SUBMITTED(10, "己提交"),
    /**
     * 教师己点评（可分享）
     */
    HAS_COMMENTED(20, "己点评"),

    /**
     *教师己点评（不可分享）
     */
    HAS_COMMENTED_NOT_SHERE(21, "己点评"),

    /**
     *己分享
     */
    HAS_SHARE(30, "己分享"),
    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, WorkStatusEnum> noMap = new HashMap<Integer, WorkStatusEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (WorkStatusEnum e : WorkStatusEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, WorkStatusEnum> nameMap = new HashMap<String, WorkStatusEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (WorkStatusEnum e : WorkStatusEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     *
     * @return
     */
    public static Map<Integer, WorkStatusEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     *
     * @return
     */
    public static Map<String, WorkStatusEnum> getNameMap() {
        return nameMap;
    }

    /**
     *
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private WorkStatusEnum(Integer no, String name) {
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
