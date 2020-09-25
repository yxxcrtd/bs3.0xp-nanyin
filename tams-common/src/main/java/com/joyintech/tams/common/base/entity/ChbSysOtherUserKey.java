package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysOtherUserKey extends BaseEntity {
    /**
     * 用户代码
     */
    private String userId;

    /**
     * 它系统用户代码
     */
    private String othSysUserId;

    /**
     * 系统类型 参照数据字典
     */
    private String sysType;

    /**
     * 取得 用户代码
     * @return 用户代码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 用户代码
     * @param userId 用户代码
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 它系统用户代码
     * @return 它系统用户代码
     */
    public String getOthSysUserId() {
        return othSysUserId;
    }

    /**
     * 设置 它系统用户代码
     * @param othSysUserId 它系统用户代码
     */
    public void setOthSysUserId(String othSysUserId) {
        this.othSysUserId = othSysUserId == null ? null : othSysUserId.trim();
    }

    /**
     * 取得 系统类型 参照数据字典
     * @return 系统类型 参照数据字典
     */
    public String getSysType() {
        return sysType;
    }

    /**
     * 设置 系统类型 参照数据字典
     * @param sysType 系统类型 参照数据字典
     */
    public void setSysType(String sysType) {
        this.sysType = sysType == null ? null : sysType.trim();
    }
}