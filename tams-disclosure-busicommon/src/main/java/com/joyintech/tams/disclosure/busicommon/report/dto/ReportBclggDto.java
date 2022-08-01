
package com.joyintech.tams.disclosure.busicommon.report.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 不成立公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2022年07月14日
 * @see ReportBclggDto
 * @since 1.0
 */
public class ReportBclggDto extends BaseEntity {
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
     * 是否有代销机构
     */
    private String isSalesAgency;

    /**
     * 不成立原因
     */
    private String nocreateReason;

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

    public String getIsSalesAgency() {
        return isSalesAgency;
    }

    public void setIsSalesAgency(String isSalesAgency) {
        this.isSalesAgency = isSalesAgency;
    }

    public String getNocreateReason() {
        return nocreateReason;
    }

    public void setNocreateReason(String nocreateReason) {
        this.nocreateReason = nocreateReason;
    }
}
