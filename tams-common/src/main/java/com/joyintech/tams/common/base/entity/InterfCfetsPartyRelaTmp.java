package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfCfetsPartyRelaTmp extends BaseEntity {
    /**
     * 关系代码
     */
    private String relaId;

    /**
     * 主交易机构ID，6位编号
     */
    private String mpartyCustomerId;

    /**
     * 从交易机构ID，6位编号
     */
    private String spartyCustomerId;

    /**
     * 取得 关系代码
     * @return 关系代码
     */
    public String getRelaId() {
        return relaId;
    }

    /**
     * 设置 关系代码
     * @param relaId 关系代码
     */
    public void setRelaId(String relaId) {
        this.relaId = relaId == null ? null : relaId.trim();
    }

    /**
     * 取得 主交易机构ID，6位编号
     * @return 主交易机构ID，6位编号
     */
    public String getMpartyCustomerId() {
        return mpartyCustomerId;
    }

    /**
     * 设置 主交易机构ID，6位编号
     * @param mpartyCustomerId 主交易机构ID，6位编号
     */
    public void setMpartyCustomerId(String mpartyCustomerId) {
        this.mpartyCustomerId = mpartyCustomerId == null ? null : mpartyCustomerId.trim();
    }

    /**
     * 取得 从交易机构ID，6位编号
     * @return 从交易机构ID，6位编号
     */
    public String getSpartyCustomerId() {
        return spartyCustomerId;
    }

    /**
     * 设置 从交易机构ID，6位编号
     * @param spartyCustomerId 从交易机构ID，6位编号
     */
    public void setSpartyCustomerId(String spartyCustomerId) {
        this.spartyCustomerId = spartyCustomerId == null ? null : spartyCustomerId.trim();
    }
}