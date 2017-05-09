package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.content.Context;
import android.text.TextUtils;

import com.fancyfamily.primarylibrary.commentlibrary.FFApplication;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.AccountVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.StudentVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.TeacherSchoolVo;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DefaultMarkEnum;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.CommonAppModel;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.GetUserInfoResp;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.MeResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by janecer on 2016/4/19.
 * email:jxc@fancyf.cn
 * des:
 */
public class UserInfoManager {

    private Gson mGson;
    private Context mContext;
    private static UserInfoManager instance;


    public static final int PARENTUSER = 0;
    public static final int TEACHERUSER = 1;

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    private int currentUser;

    public Long getDefaultID() {
        if (currentUser == PARENTUSER){
            return getDefaultStudent().getId();
        }else {
            return getDefaultTeacherSchool().getId();
        }

    }

    interface KEY {
        String KEY_LOGIN_ACCOUNT = "KEY_LOGIN_ACCOUNT";
        String KEY_TEACHERSCHOOL_VO = "KEY_TEACHERSCHOOL_VO";
        String KEY_STUDENTVO_VO = "KEY_STUDENTVO_VO";

        String KEY_MSG_VO = "KEY_MSG_VO";
    }

    private UserInfoManager(Context ctx) {
        this.mContext = ctx;
        this.mGson = new Gson();
    }

    ;

    public static UserInfoManager getInstance() {
        if (null == instance) {
            instance = new UserInfoManager(FFApplication.instance.getApplicationContext());
        }
        return instance;
    }


    public AccountVo getLoginAccountVos() {
        String jsonStr = SharePrefUtil.getString(mContext, KEY.KEY_LOGIN_ACCOUNT, "");
        return mGson.fromJson(jsonStr, AccountVo.class);
    }


    public void saveLoginAccountVos(AccountVo accountVo) {
        SharePrefUtil.saveString(mContext, KEY.KEY_LOGIN_ACCOUNT, mGson.toJson(accountVo));
    }


    public List<StudentVo> getStudentVos() {
        String jsonStr = SharePrefUtil.getString(mContext, KEY.KEY_STUDENTVO_VO, "");
        return mGson.fromJson(jsonStr, new TypeToken<List<StudentVo>>() {
        }.getType());
    }

    /**
     * 获取默认学生
     *
     * @return
     */
    public StudentVo getDefaultStudent() {
        List<StudentVo> studentVos = getStudentVos();
        StudentVo defaultStudent = null;
        for (int i = 0; i < studentVos.size(); i++) {
            StudentVo temp = studentVos.get(i);
            if (temp.isDefaultMark()) {
                defaultStudent = temp;
            }
        }
        return defaultStudent;
    }


    public void loadParentUserInfo(final HttpResultListener<GetUserInfoResp> resultListener) {

        CommonAppModel.userInfoGet("", new HttpResultListener<GetUserInfoResp>() {
            @Override
            public void onSuccess(GetUserInfoResp resp) {
                if (resp.isSuccess()) {
                    UserInfoManager.getInstance().saveLoginAccountVos(resp.accountVo);
                    UserInfoManager.getInstance().saveStudentVos(resp.studentVoArr);

                }
                if (resultListener != null) {
                    resultListener.onSuccess(resp);
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {
                if (resultListener != null) {
                    resultListener.onFailed(e, msg);
                }
            }
        });
    }


    /**
     * 是否激活璀璨卡
     */
    public boolean isParentActivate() {
        List<StudentVo> studentVos = getStudentVos();
        if (null == studentVos || studentVos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void saveStudentVos(List<StudentVo> teacherSchoolVos) {
        String mGsonStr = mGson.toJson(teacherSchoolVos);
        Logger.msg(mGsonStr);
        SharePrefUtil.saveString(mContext, KEY.KEY_STUDENTVO_VO, mGsonStr);
    }

//    public void saveDefautStudent(StudentVo student) {
//        List<StudentVo> studentVos = getStudentVos();
//
//        for (int i = 0; i < studentVos.size(); i++) {
//            StudentVo temp = studentVos.get(i);
//            temp.isChooose = false;
//            if (temp.id == student.id) {
//                temp = student;
//            }
//        }
//        saveStudentVos(studentVos);
//    }
//
//    public void addStudent(StudentVo student) {
//        List<StudentVo> studentVos = getStudentVos();
//        studentVos.add(student);
//        saveStudentVos(studentVos);
//    }
//
//    public List<MsgVo> getMsgVos() {
//        String jsonStr = SharePrefUtil.getString(mContext, KEY.KEY_MSG_VO, "");
//        return mGson.fromJson(jsonStr, new TypeToken<List<MsgVo>>() {
//        }.getType());
//    }
//
//    public void saveMsgVos(List<MsgVo> msgVos) {
//        String mGsonStr = mGson.toJson(msgVos);
//        Logger.msg(mGsonStr);
//        SharePrefUtil.saveString(mContext, KEY.KEY_MSG_VO, mGsonStr);
//    }

//**************************************教师端***************************************************//
    public void loadTeacherUserInfo(final HttpResultListener<MeResponseVo> resultListener) {
        CommonAppModel.teacherInfoGets("", new HttpResultListener<MeResponseVo>() {
            @Override
            public void onSuccess(MeResponseVo resp) {
                if (resp.isSuccess()) {
                    UserInfoManager.getInstance().saveLoginAccountVos(resp.getAccountVo());
                    UserInfoManager.getInstance().saveTeacherSchools(resp.getTeacherSchoolVoArr());
                }
                if (resultListener != null) {
                    resultListener.onSuccess(resp);
                }
            }
            @Override
            public void onFailed(Exception e, String msg) {
                if (resultListener != null) {
                    resultListener.onFailed(e, msg);
                }
            }
        });
    }


    /**
     * 是否激活璀璨卡
     */
    public boolean isTeacherActivate() {
        boolean flag = false;
        List<TeacherSchoolVo> teacherSchoolVos = getTeacherSchools();
        for (TeacherSchoolVo vo : teacherSchoolVos ) {
            if (!TextUtils.isEmpty(vo.getShinySn())){
                flag =  true;
            }
        }
        return flag;
    }

    /**
     * 获取默认学校
     *
     * @return
     */
    public TeacherSchoolVo getDefaultTeacherSchool() {
        List<TeacherSchoolVo> studentVos = getTeacherSchools();
        TeacherSchoolVo defaultStudent = null;
        for (int i = 0; i < studentVos.size(); i++) {
            TeacherSchoolVo temp = studentVos.get(i);
            if (temp.getDefaultMark() == DefaultMarkEnum.DEFULT.getNo()) {
                defaultStudent = temp;
            }
        }
        return defaultStudent;
    }

    public List<TeacherSchoolVo> getTeacherSchools() {
        String jsonStr = SharePrefUtil.getString(mContext, KEY.KEY_TEACHERSCHOOL_VO, "");
        return mGson.fromJson(jsonStr, new TypeToken<List<TeacherSchoolVo>>() {
        }.getType());
    }

    public void saveTeacherSchools(List<TeacherSchoolVo> teacherSchoolVos) {
        String mGsonStr = mGson.toJson(teacherSchoolVos);
        Logger.msg(mGsonStr);
        SharePrefUtil.saveString(mContext, KEY.KEY_TEACHERSCHOOL_VO, mGsonStr);
    }

}
