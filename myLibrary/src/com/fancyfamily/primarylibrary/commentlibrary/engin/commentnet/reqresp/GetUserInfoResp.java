package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.StudentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DefaultMarkEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * Created by janecer on 2016/3/31.
 * email:jxc@fancyf.cn
 * des:
 */
public class GetUserInfoResp extends BaseResponseVo{

    public AccountVo accountVo ;

    public List<StudentVo> studentVoArr ;


    public List<StudentVo> getSortByDefaultList() {
       if(null == studentVoArr) {
           return null ;
       }
       int size = studentVoArr.size() ;
       for(int i = 0 ; i < size ; i++) {
           if(studentVoArr.get(i).defaultMark == DefaultMarkEnum.DEFULT.getNo()){
               studentVoArr.add(0,studentVoArr.get(i));
               studentVoArr.remove(i+1) ;
               return studentVoArr ;
           }
       }
        return studentVoArr ;
    } ;

}
