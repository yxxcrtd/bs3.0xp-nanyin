
package com.joyintech.tams.disclosure.busicommon.report.dto;

import com.joyintech.framework.base.BaseEntity;

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
    private String preFeerate;
    /**
     * 优惠后费率
     */
    private String afterFeerate;
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
    private String thisPreFeerate;
    /**
     * 本期优惠后费率
     */
    private String thisAfterFeerate;
    /**
     * 本次优惠结束后费率
     */
    private String thisEndFeerate;

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

    public String getPreFeerate() {
        return preFeerate;
    }

    public void setPreFeerate(String preFeerate) {
        this.preFeerate = preFeerate;
    }

    public String getAfterFeerate() {
        return afterFeerate;
    }

    public void setAfterFeerate(String afterFeerate) {
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

    public String getThisPreFeerate() {
        return thisPreFeerate;
    }

    public void setThisPreFeerate(String thisPreFeerate) {
        this.thisPreFeerate = thisPreFeerate;
    }

    public String getThisAfterFeerate() {
        return thisAfterFeerate;
    }

    public void setThisAfterFeerate(String thisAfterFeerate) {
        this.thisAfterFeerate = thisAfterFeerate;
    }

    public String getThisEndFeerate() {
        return thisEndFeerate;
    }

    public void setThisEndFeerate(String thisEndFeerate) {
        this.thisEndFeerate = thisEndFeerate;
    }
}
