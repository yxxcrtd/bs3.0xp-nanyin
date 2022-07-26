
package com.joyintech.tams.disclosure.information.report.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发行公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2022年07月14日
 * @see ReportFxggDto
 * @since 1.0
 */
public class ReportFxggDto extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品登记编码
     */
    private String regCode;
    
    /**
     * 成立日
     */
    private Date setDate;
    
    /**
     * 募集金额
     */
    private BigDecimal raiseAmt;

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

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public BigDecimal getRaiseAmt() {
        return raiseAmt;
    }

    public void setRaiseAmt(BigDecimal raiseAmt) {
        this.raiseAmt = raiseAmt;
    }
}
