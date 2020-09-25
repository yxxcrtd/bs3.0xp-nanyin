package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttFactorTipsKey extends BaseEntity {
    /**
     * 因子代码
     */
    private String ftrId;

    /**
     * 参数代码
     */
    private String tipsCode;

    /**
     * 取得 因子代码
     * @return 因子代码
     */
    public String getFtrId() {
        return ftrId;
    }

    /**
     * 设置 因子代码
     * @param ftrId 因子代码
     */
    public void setFtrId(String ftrId) {
        this.ftrId = ftrId == null ? null : ftrId.trim();
    }

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getTipsCode() {
        return tipsCode;
    }

    /**
     * 设置 参数代码
     * @param tipsCode 参数代码
     */
    public void setTipsCode(String tipsCode) {
        this.tipsCode = tipsCode == null ? null : tipsCode.trim();
    }
}