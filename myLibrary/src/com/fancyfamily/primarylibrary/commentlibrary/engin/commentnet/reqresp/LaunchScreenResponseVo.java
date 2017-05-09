package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;

import com.fancyfamily.primarylibrary.commentlibrary.commentbean.ClickContainerVo;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

import java.util.List;

/**
 * 获取开机广告
 * */
public class LaunchScreenResponseVo extends BaseResponseVo {
    private List<ClickContainerVo> launchScreenVoArr;// 开机广告列表（下次启动才用）



    public List<ClickContainerVo> getLaunchScreenVoArr() {
        return launchScreenVoArr;
    }

    public void setLaunchScreenVoArr(List<ClickContainerVo> launchScreenVoArr) {
        this.launchScreenVoArr = launchScreenVoArr;
    }

}
