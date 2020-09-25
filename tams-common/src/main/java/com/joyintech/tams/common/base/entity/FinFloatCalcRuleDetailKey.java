package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinFloatCalcRuleDetailKey extends BaseEntity {
    /**
     * 规则代码
     */
    private String cashId;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 层级
     */
    private Long lev;

    /**
     * 取得 规则代码
     * @return 规则代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 规则代码
     * @param cashId 规则代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 层级
     * @return 层级
     */
    public Long getLev() {
        return lev;
    }

    /**
     * 设置 层级
     * @param lev 层级
     */
    public void setLev(Long lev) {
        this.lev = lev;
    }
}