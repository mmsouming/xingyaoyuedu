package com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信类型
 *
 */
public enum SmsTypeEnum implements BaseEnum {
    /**
     * 注册验证
     */
    LOGIN(1, "注册验证"),
    /**
     * 重置密码验证
     */
    RESET_PASSWORD(2, "重置密码验证"),

    ;

    private Integer no;// 编号
    private String name;// 名字
    /**
     * 编号转换Map
     */
    private final static Map<Integer, SmsTypeEnum> noMap = new HashMap<Integer, SmsTypeEnum>() {
        private static final long serialVersionUID = 4844087599684960991L;

        {
            for (SmsTypeEnum e : SmsTypeEnum.values()) {
                put(e.getNo(), e);
            }
        }
    };

    /**
     * 编号转换Map
     */
    private final static Map<String, SmsTypeEnum> nameMap = new HashMap<String, SmsTypeEnum>() {
        private static final long serialVersionUID = -4844099684969108759L;
        {
            for (SmsTypeEnum e : SmsTypeEnum.values()) {
                put(e.getName(), e);
            }
        }
    };

    /**
     * 编号转换Map
     * 
     * @return
     */
    public static Map<Integer, SmsTypeEnum> getNoMap() {
        return noMap;
    }

    /**
     * 名称转换Map
     * 
     * @return
     */
    public static Map<String, SmsTypeEnum> getNameMap() {
        return nameMap;
    }

    /**
     * 
     * @param no
     *            编号
     * @param name
     *            名字
     */
    private SmsTypeEnum(Integer no, String name) {
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
