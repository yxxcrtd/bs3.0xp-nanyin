package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class ChbSecRepayInfoKey extends BaseEntity {
    /**
     * 债券代码
     */
    private String secId;

    /**
     * 还本日期
     */
    private Date repayDt;

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 债券代码
     * @param secId 债券代码
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 还本日期
     * @return 还本日期
     */
    public Date getRepayDt() {
        return repayDt;
    }

    /**
     * 设置 还本日期
     * @param repayDt 还本日期
     */
    public void setRepayDt(Date repayDt) {
        this.repayDt = repayDt;
    }
}