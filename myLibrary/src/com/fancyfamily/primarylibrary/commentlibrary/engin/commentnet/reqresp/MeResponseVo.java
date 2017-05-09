package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.TeacherSchoolVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * 获取【我】页面的信息
 * 
 * @author qhfang
 * */
public class MeResponseVo extends BaseResponseVo {

    private AccountVo accountVo;

    private List<TeacherSchoolVo> teacherSchoolVoArr;


    public AccountVo getAccountVo() {
        return accountVo;
    }

    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }

    public List<TeacherSchoolVo> getTeacherSchoolVoArr() {
        return teacherSchoolVoArr;
    }

    public void setTeacherSchoolVoArr(List<TeacherSchoolVo> teacherSchoolVoArr) {
        this.teacherSchoolVoArr = teacherSchoolVoArr;
    }

}
