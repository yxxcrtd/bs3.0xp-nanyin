
package com.joyintech.tams.disclosure.information.report.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费率优惠公告费用优惠明细
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2022年07月14日
 * @see ReportProdfeeDto
 * @since 1.0
 */
public class ReportProdfeeDto extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 费用名称
     */
    private String feename;
    /**
     * 优惠先费率
     */
    private BigDecimal preFeerate;
    /**
     * 优惠后费率
     */
    private BigDecimal afterFeerate;
    /**
     * 优惠开始时间
     */
    private Date startDate;
    /**
     * 优惠结束时间
     */
    private Date endDate;
    /**
     * 本次优惠前费率
     */
    private BigDecimal thisPreFeerate;
    /**
     * 本期优惠后费率
     */
    private BigDecimal thisAfterFeerate;
    /**
     * 本次优惠结束后费率
     */
    private BigDecimal thisEndFeerate;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getFeename() {
        return feename;
    }

    public void setFeename(String feename) {
        this.feename = feename;
    }

    public BigDecimal getPreFeerate() {
        return preFeerate;
    }

    public void setPreFeerate(BigDecimal preFeerate) {
        this.preFeerate = preFeerate;
    }

    public BigDecimal getAfterFeerate() {
        return afterFeerate;
    }

    public void setAfterFeerate(BigDecimal afterFeerate) {
        this.afterFeerate = afterFeerate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getThisPreFeerate() {
        return thisPreFeerate;
    }

    public void setThisPreFeerate(BigDecimal thisPreFeerate) {
        this.thisPreFeerate = thisPreFeerate;
    }

    public BigDecimal getThisAfterFeerate() {
        return thisAfterFeerate;
    }

    public void setThisAfterFeerate(BigDecimal thisAfterFeerate) {
        this.thisAfterFeerate = thisAfterFeerate;
    }

    public BigDecimal getThisEndFeerate() {
        return thisEndFeerate;
    }

    public void setThisEndFeerate(BigDecimal thisEndFeerate) {
        this.thisEndFeerate = thisEndFeerate;
    }
}
