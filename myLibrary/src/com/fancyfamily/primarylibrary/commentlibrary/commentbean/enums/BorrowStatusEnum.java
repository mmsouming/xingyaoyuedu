package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 图书状态
 * 
 * @author Bin
 *
 */
public enum BorrowStatusEnum implements BaseEnum {
    /**
     * 外借中
     */
    BORROW(1, "外借中"),
    /**
     * 已逾期
     */
    OVERDUE(2, "已逾期"),
    /**
     * 已归还
     */
    RETURN(3, "已归还"),
    /**
     * 逾期归还
     */
    OVERDUE_RETURN(4, "逾期归还"),
    /**
     * 己报损
     */
    HAS_LOSS(5, "己报损"),
    /**
     * 标签报损
     */
    LABEL_LOSS(6, "标签报损"),
    /**
     * 己报失
     */
    HAS_MISS(7, "己报失"),

    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, BorrowStatusEnum> noMap = new HashMap<Integer, BorrowStatusEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (BorrowStatusEnum e : BorrowStatusEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, BorrowStatusEnum> nameMap = new HashMap<String, BorrowStatusEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (BorrowStatusEnum e : BorrowStatusEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, BorrowStatusEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, BorrowStatusEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private BorrowStatusEnum(Integer no, String name) {
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
