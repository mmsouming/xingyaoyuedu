package com.fancyfamily.primarylibrary.commentlibrary.util;

/**
 * Created by janecer on 2016/3/31.
 * email:jxc@fancyf.cn
 * des:适后台业务int转换String工具类
 */
public class Int2StrUtil {



    public static String relation2Str(int type) {
        if(type == 1 ){
            return "爸爸";
        } if(type == 2) {
            return "妈妈";
        }
        return "其他" ;
    } ;

    public static int str2Relation(String relation) {
        if("爸爸".equals(relation)){
            return 1 ;
        }if("妈妈".equals(relation)){
            return 2 ;
        }if("其他".equals(relation)){
            return 100 ;
        }
        return 100 ;
    }

    public static String sex2Str(int type) {
        if(type == 1) {
            return "男" ;
        }
        return "女" ;
    }

    public static int str2Sex(String sex){
        if("男".equals(sex)){
            return 1 ;
        }
        return 2 ;
    } ;

}
