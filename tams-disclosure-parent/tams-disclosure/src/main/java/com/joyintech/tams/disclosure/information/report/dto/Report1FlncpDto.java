
package com.joyintech.tams.disclosure.information.report.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 一费多产费率优化公告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2022年07月14日
 * @see Report1FlncpDto
 * @since 1.0
 */
public class Report1FlncpDto extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 说明
     */
    private String remark;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }
}
