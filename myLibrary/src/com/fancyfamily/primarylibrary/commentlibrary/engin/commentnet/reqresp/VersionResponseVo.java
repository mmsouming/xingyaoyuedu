package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

/**
 * 获取版本信息
 * */
public class VersionResponseVo extends BaseResponseVo {
    /**
     * 最新外部版本号
     * */
    private String externalVersion;
    /**
     * 最新内部版本号
     * */
    private Integer internalVersion;
    /**
     * 支持最低内部版本号
     * */
    private Integer lowestVersion;
    /**
     * 最新版下载地址
     * */
    private String downloadUrl;
    /**
     * 更新原因
     * */
    private String updateReason;


    public String getExternalVersion() {
        return externalVersion;
    }

    public void setExternalVersion(String externalVersion) {
        this.externalVersion = externalVersion;
    }

    public Integer getInternalVersion() {
        return internalVersion;
    }

    public void setInternalVersion(Integer internalVersion) {
        this.internalVersion = internalVersion;
    }

    public Integer getLowestVersion() {
        return lowestVersion;
    }

    public void setLowestVersion(Integer lowestVersion) {
        this.lowestVersion = lowestVersion;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }

}
