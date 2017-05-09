package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理进度
 * 
 * @author Stsu
 *
 */
public enum DisposeProgressEnum implements BaseEnum {
    /**
     * 未开始
     */
    NOT_STARTED(1, "未开始"),
    /**
     * 进行中
     */
    PROCESSING(2, "进行中"),
    /**
     * 己到期
     */
    HAS_EXPIRED(10, "己到期"),
    /**
     * 己结束
     */
    HAS_ENDED(20, "己结束"),
    
    /**
     * 已取消
     */
    HAS_CANCLE(30, "已取消"),
      
    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, DisposeProgressEnum> noMap = new HashMap<Integer, DisposeProgressEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (DisposeProgressEnum e : DisposeProgressEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, DisposeProgressEnum> nameMap = new HashMap<String, DisposeProgressEnum>() {
        private static final long serialVersionUID = 4844099684969108759L;

        {
            for (DisposeProgressEnum e : DisposeProgressEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, DisposeProgressEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, DisposeProgressEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private DisposeProgressEnum(Integer no, String name) {
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
