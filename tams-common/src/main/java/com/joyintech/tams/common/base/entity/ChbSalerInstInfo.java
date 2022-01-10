package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSalerInstInfo extends BaseEntity {
    /**
     * 总部或分支机构编码 
     */
    private String salerOrgCode;

    /**
     * 销售机构代码 
     */
    private String salerInstCode;

    /**
     * 销售机构名称 
     */
    private String salerInstName;

    /**
     * 总部或分支机构名称 
     */
    private String salerOrgName;

    /**
     * 机构层级 
     */
    private String salerOrgLevel;

    /**
     * 数据日期 
     */
    private Date dataDate;

    /**
     * 备注 
     */
    private String remark;

    /**
     * 取得 总部或分支机构编码 
     * @return 总部或分支机构编码 
     */
    public String getSalerOrgCode() {
        return salerOrgCode;
    }

    /**
     * 设置 总部或分支机构编码 
     * @param salerOrgCode 总部或分支机构编码 
     */
    public void setSalerOrgCode(String salerOrgCode) {
        this.salerOrgCode = salerOrgCode == null ? null : salerOrgCode.trim();
    }

    /**
     * 取得 销售机构代码 
     * @return 销售机构代码 
     */
    public String getSalerInstCode() {
        return salerInstCode;
    }

    /**
     * 设置 销售机构代码 
     * @param salerInstCode 销售机构代码 
     */
    public void setSalerInstCode(String salerInstCode) {
        this.salerInstCode = salerInstCode == null ? null : salerInstCode.trim();
    }

    /**
     * 取得 销售机构名称 
     * @return 销售机构名称 
     */
    public String getSalerInstName() {
        return salerInstName;
    }

    /**
     * 设置 销售机构名称 
     * @param salerInstName 销售机构名称 
     */
    public void setSalerInstName(String salerInstName) {
        this.salerInstName = salerInstName == null ? null : salerInstName.trim();
    }

    /**
     * 取得 总部或分支机构名称 
     * @return 总部或分支机构名称 
     */
    public String getSalerOrgName() {
        return salerOrgName;
    }

    /**
     * 设置 总部或分支机构名称 
     * @param salerOrgName 总部或分支机构名称 
     */
    public void setSalerOrgName(String salerOrgName) {
        this.salerOrgName = salerOrgName == null ? null : salerOrgName.trim();
    }

    /**
     * 取得 机构层级 
     * @return 机构层级 
     */
    public String getSalerOrgLevel() {
        return salerOrgLevel;
    }

    /**
     * 设置 机构层级 
     * @param salerOrgLevel 机构层级 
     */
    public void setSalerOrgLevel(String salerOrgLevel) {
        this.salerOrgLevel = salerOrgLevel == null ? null : salerOrgLevel.trim();
    }

    /**
     * 取得 数据日期 
     * @return 数据日期 
     */
    public Date getDataDate() {
        return dataDate;
    }

    /**
     * 设置 数据日期 
     * @param dataDate 数据日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    /**
     * 取得 备注 
     * @return 备注 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注 
     * @param remark 备注 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}