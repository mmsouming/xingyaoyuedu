package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 图书状态
 * 
 * @author Bin
 *
 */
public enum BookStatusEnum implements BaseEnum {
    /**
     * 未上架
     */
    NOT_LISTED(1, "未上架"),
    /**
     * 己上架
     */
    HAS_LISTED(2, "己上架"),
    /**
     * 己外借
     */
    HAS_BORROW(3, "己外借"),
    /**
     * 己报损
     */
    HAS_LOSS(4, "己报损"),
    /**
     * 固定图书
     */
    FIXED_BOOK(5, "固定图书"),
    /**
     * 己撤回
     */
    REVOKE(6, "己撤回"),

    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, BookStatusEnum> noMap = new HashMap<Integer, BookStatusEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (BookStatusEnum e : BookStatusEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, BookStatusEnum> nameMap = new HashMap<String, BookStatusEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (BookStatusEnum e : BookStatusEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, BookStatusEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, BookStatusEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private BookStatusEnum(Integer no, String name) {
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
