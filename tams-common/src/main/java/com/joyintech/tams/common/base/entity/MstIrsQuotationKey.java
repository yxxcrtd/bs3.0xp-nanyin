package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstIrsQuotationKey extends BaseEntity {
    /**
     * 曲线代码
     */
    private String curveCode;

    /**
     * 曲线日期
     */
    private Date curveDate;

    /**
     * 期限品种（利率互换）
     */
    private String termTypeIrs;

    /**
     * 取得 曲线代码
     * @return 曲线代码
     */
    public String getCurveCode() {
        return curveCode;
    }

    /**
     * 设置 曲线代码
     * @param curveCode 曲线代码
     */
    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode == null ? null : curveCode.trim();
    }

    /**
     * 取得 曲线日期
     * @return 曲线日期
     */
    public Date getCurveDate() {
        return curveDate;
    }

    /**
     * 设置 曲线日期
     * @param curveDate 曲线日期
     */
    public void setCurveDate(Date curveDate) {
        this.curveDate = curveDate;
    }

    /**
     * 取得 期限品种（利率互换）
     * @return 期限品种（利率互换）
     */
    public String getTermTypeIrs() {
        return termTypeIrs;
    }

    /**
     * 设置 期限品种（利率互换）
     * @param termTypeIrs 期限品种（利率互换）
     */
    public void setTermTypeIrs(String termTypeIrs) {
        this.termTypeIrs = termTypeIrs == null ? null : termTypeIrs.trim();
    }
}