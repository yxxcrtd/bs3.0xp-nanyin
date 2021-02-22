package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskBusinessRedisConfigKey extends BaseEntity {
    /**
     * 共通业务类型
     */
    private String commonBusinessType;

    /**
     * REDIS模块
     */
    private String paraCode;

    /**
     * 取得 共通业务类型
     * @return 共通业务类型
     */
    public String getCommonBusinessType() {
        return commonBusinessType;
    }

    /**
     * 设置 共通业务类型
     * @param commonBusinessType 共通业务类型
     */
    public void setCommonBusinessType(String commonBusinessType) {
        this.commonBusinessType = commonBusinessType == null ? null : commonBusinessType.trim();
    }

    /**
     * 取得 REDIS模块
     * @return REDIS模块
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 REDIS模块
     * @param paraCode REDIS模块
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }
}