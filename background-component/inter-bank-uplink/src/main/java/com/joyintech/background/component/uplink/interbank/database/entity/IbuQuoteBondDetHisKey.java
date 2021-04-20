package com.joyintech.background.component.uplink.interbank.database.entity;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

public class IbuQuoteBondDetHisKey extends BaseEntity {
    /**
     * 明细编号
     */
    private String detId;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 取得 明细编号
     * @return 明细编号
     */
    public String getDetId() {
        return detId;
    }

    /**
     * 设置 明细编号
     * @param detId 明细编号
     */
    public void setDetId(String detId) {
        this.detId = detId == null ? null : detId.trim();
    }

    /**
     * 取得 版本号
     * @return 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置 版本号
     * @param version 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}