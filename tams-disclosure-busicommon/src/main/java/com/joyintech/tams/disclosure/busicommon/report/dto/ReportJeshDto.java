
package com.joyintech.tams.disclosure.busicommon.report.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * 巨额赎回模板
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2022年07月14日
 * @see ReportJeshDto
 * @since 1.0
 */
public class ReportJeshDto extends BaseEntity {
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
     * 募集方式
     */
    private String raiseType;

    /**
     * 内部销售代码
     */
    private String saleCode;

    /**
     * 触发的情形说明文字
     */
    private String redRemark;

    /**
     * 拒绝赎回说明（文字）
     */
    private String refRemark;

    /**
     * 触发时间
     */
    private Date redDate;

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

    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public String getRedRemark() {
        return redRemark;
    }

    public void setRedRemark(String redRemark) {
        this.redRemark = redRemark;
    }

    public String getRefRemark() {
        return refRemark;
    }

    public void setRefRemark(String refRemark) {
        this.refRemark = refRemark;
    }

    public Date getRedDate() {
        return redDate;
    }

    public void setRedDate(Date redDate) {
        this.redDate = redDate;
    }
}
