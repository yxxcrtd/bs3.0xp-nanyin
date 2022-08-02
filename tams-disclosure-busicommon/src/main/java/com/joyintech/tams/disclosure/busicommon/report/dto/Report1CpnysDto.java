
package com.joyintech.tams.disclosure.busicommon.report.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 一产多要素调整公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2022年07月14日
 * @see Report1CpnysDto
 * @since 1.0
 */
public class Report1CpnysDto extends BaseEntity {
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
     * 调整原因
     */
    private String adjustReason;

    /**
     * 调整补充说明1
     */
    private String remark1;

    /**
     * 调整补充说明2
     */
    private String remark2;

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

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
}
