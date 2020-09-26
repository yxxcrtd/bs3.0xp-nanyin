package com.joyintech.tams.valuation.assets.posit.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 付息计划表
 * 
 * @author wangtingting
 * @version 1.0 2018年7月27日
 * @see ValPayIntPlanDto
 * @since 1.0
 */
public class ValPayIntPlanDto extends FinProduct {

    private static final long serialVersionUID = 1L;

    /**
     * 计息开始日
     */
    private Date firstJxDate;

    /**
     * 计息结束日
     */
    private Date lastJxDate;

    /**
     * 理论支付日
     */
    private Date payDate;

    /**
     * 应计利息
     */
    private String intAmt;

    /**
     * 获取 firstJxDate
     * 
     * @return firstJxDate.
     */
    public Date getFirstJxDate() {
        return firstJxDate;
    }

    /**
     * 设置 firstJxDate
     * 
     * @param firstJxDate firstJxDate
     */
    public void setFirstJxDate(Date firstJxDate) {
        this.firstJxDate = firstJxDate;
    }

    /**
     * 获取 lastJxDate
     * 
     * @return lastJxDate.
     */
    public Date getLastJxDate() {
        return lastJxDate;
    }

    /**
     * 设置 lastJxDate
     * 
     * @param lastJxDate lastJxDate
     */
    public void setLastJxDate(Date lastJxDate) {
        this.lastJxDate = lastJxDate;
    }

    /**
     * 获取 payDate
     * 
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     * 
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 intAmt
     * 
     * @return intAmt.
     */
    public String getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     * 
     * @param intAmt intAmt
     */
    public void setIntAmt(String intAmt) {
        this.intAmt = intAmt;
    }

}
