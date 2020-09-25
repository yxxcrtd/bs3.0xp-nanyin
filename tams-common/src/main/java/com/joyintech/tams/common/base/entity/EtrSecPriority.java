package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class EtrSecPriority extends BaseEntity {
    /**
     * 设置编号
     */
    private String configId;

    /**
     * 是否启用
     */
    private String yesOrNo;

    /**
     * 剩余期限优先级
     */
    private String lastPeriodPri;

    /**
     * 债项评级优先级
     */
    private String secGradePri;

    /**
     * 取得 设置编号
     * @return 设置编号
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 设置编号
     * @param configId 设置编号
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    /**
     * 取得 是否启用
     * @return 是否启用
     */
    public String getYesOrNo() {
        return yesOrNo;
    }

    /**
     * 设置 是否启用
     * @param yesOrNo 是否启用
     */
    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo == null ? null : yesOrNo.trim();
    }

    /**
     * 取得 剩余期限优先级
     * @return 剩余期限优先级
     */
    public String getLastPeriodPri() {
        return lastPeriodPri;
    }

    /**
     * 设置 剩余期限优先级
     * @param lastPeriodPri 剩余期限优先级
     */
    public void setLastPeriodPri(String lastPeriodPri) {
        this.lastPeriodPri = lastPeriodPri == null ? null : lastPeriodPri.trim();
    }

    /**
     * 取得 债项评级优先级
     * @return 债项评级优先级
     */
    public String getSecGradePri() {
        return secGradePri;
    }

    /**
     * 设置 债项评级优先级
     * @param secGradePri 债项评级优先级
     */
    public void setSecGradePri(String secGradePri) {
        this.secGradePri = secGradePri == null ? null : secGradePri.trim();
    }
}