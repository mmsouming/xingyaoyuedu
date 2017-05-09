package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

import java.io.Serializable;

public class AccountVo implements Serializable {
    public String headUrl;// 头像url

    public Integer sexType;// 性别

    public String birthday;// 生日（yyyy年MM月dd日）

    public String nickname;// 昵称

    public Integer accountType;// 用户类型

    public AccountVo() {
        // TODO Auto-generated constructor stub
    }

    public AccountVo(String headUrl, Integer sexType, String birthday,
            String nickname, Integer accountType) {
        this.headUrl = headUrl;
        this.sexType = sexType;
        this.birthday = birthday;
        this.nickname = nickname;
        this.accountType = accountType;
    }


}
