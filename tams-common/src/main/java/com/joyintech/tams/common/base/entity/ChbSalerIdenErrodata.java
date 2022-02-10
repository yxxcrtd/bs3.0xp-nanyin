package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSalerIdenErrodata extends BaseEntity {
    /**
     * 内部编号
     */
    private String salerInsNo;

    /**
     * 所属总部或分支机构编码
     */
    private String salerOrgCode;

    /**
     * 姓名 
     */
    private String salerName;

    /**
     * 性别 
     */
    private String salerSex;

    /**
     * 证件类别 
     */
    private String salerCredentType;

    /**
     * 数据日期 
     */
    private Date dataDate;

    /**
     * 工作流代码 
     */
    private String workflowId;

    /**
     * 机构代码 
     */
    private String orgCode;

    /**
     * 取得 内部编号
     * @return 内部编号
     */
    public String getSalerInsNo() {
        return salerInsNo;
    }

    /**
     * 设置 内部编号
     * @param salerInsNo 内部编号
     */
    public void setSalerInsNo(String salerInsNo) {
        this.salerInsNo = salerInsNo == null ? null : salerInsNo.trim();
    }

    /**
     * 取得 所属总部或分支机构编码
     * @return 所属总部或分支机构编码
     */
    public String getSalerOrgCode() {
        return salerOrgCode;
    }

    /**
     * 设置 所属总部或分支机构编码
     * @param salerOrgCode 所属总部或分支机构编码
     */
    public void setSalerOrgCode(String salerOrgCode) {
        this.salerOrgCode = salerOrgCode == null ? null : salerOrgCode.trim();
    }

    /**
     * 取得 姓名 
     * @return 姓名 
     */
    public String getSalerName() {
        return salerName;
    }

    /**
     * 设置 姓名 
     * @param salerName 姓名 
     */
    public void setSalerName(String salerName) {
        this.salerName = salerName == null ? null : salerName.trim();
    }

    /**
     * 取得 性别 
     * @return 性别 
     */
    public String getSalerSex() {
        return salerSex;
    }

    /**
     * 设置 性别 
     * @param salerSex 性别 
     */
    public void setSalerSex(String salerSex) {
        this.salerSex = salerSex == null ? null : salerSex.trim();
    }

    /**
     * 取得 证件类别 
     * @return 证件类别 
     */
    public String getSalerCredentType() {
        return salerCredentType;
    }

    /**
     * 设置 证件类别 
     * @param salerCredentType 证件类别 
     */
    public void setSalerCredentType(String salerCredentType) {
        this.salerCredentType = salerCredentType == null ? null : salerCredentType.trim();
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
     * 取得 工作流代码 
     * @return 工作流代码 
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码 
     * @param workflowId 工作流代码 
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 机构代码 
     * @return 机构代码 
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码 
     * @param orgCode 机构代码 
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}