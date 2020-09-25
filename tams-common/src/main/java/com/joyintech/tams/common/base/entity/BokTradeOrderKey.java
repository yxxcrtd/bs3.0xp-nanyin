package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokTradeOrderKey extends BaseEntity {
    /**
     * 会计准则
     */
    private String acctStandard;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 取得 会计准则
     * @return 会计准则
     */
    public String getAcctStandard() {
        return acctStandard;
    }

    /**
     * 设置 会计准则
     * @param acctStandard 会计准则
     */
    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard == null ? null : acctStandard.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }
}