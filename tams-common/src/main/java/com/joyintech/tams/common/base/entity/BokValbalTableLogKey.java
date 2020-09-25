package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class BokValbalTableLogKey extends BaseEntity {
    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 估值表余额表标志
     */
    private String valBalFlag;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 产品代码
     * @param finprodId 产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 估值表余额表标志
     * @return 估值表余额表标志
     */
    public String getValBalFlag() {
        return valBalFlag;
    }

    /**
     * 设置 估值表余额表标志
     * @param valBalFlag 估值表余额表标志
     */
    public void setValBalFlag(String valBalFlag) {
        this.valBalFlag = valBalFlag == null ? null : valBalFlag.trim();
    }
}