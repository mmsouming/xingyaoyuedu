package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 报损类型
 * 
 * @author Bin
 *
 */
public enum LossTypeEnum implements BaseEnum {
    /**
     * 归还报损
     */
    LOSS_RETURN(1, "归还报损"),
    /**
     * 园区报损
     */
    LOSS_SCHOOL(2, "园区报损"),
 

    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, LossTypeEnum> noMap = new HashMap<Integer, LossTypeEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (LossTypeEnum e : LossTypeEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, LossTypeEnum> nameMap = new HashMap<String, LossTypeEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (LossTypeEnum e : LossTypeEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, LossTypeEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, LossTypeEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private LossTypeEnum(Integer no, String name) {
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
