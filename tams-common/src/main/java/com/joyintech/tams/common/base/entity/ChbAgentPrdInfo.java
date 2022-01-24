package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbAgentPrdInfo extends BaseEntity {
    /**
     * 代销机构代码 
     */
    private String agentInstitutionCode;

    /**
     * 代销机构名称
     */
    private String agentInstitutionName;

    /**
     * 发行机构代码 
     */
    private String issuePartyId;

    /**
     * 理财产品代码 
     */
    private String prodId;

    /**
     * 理财产品名称 
     */
    private String prodName;

    /**
     * 产品登记编码 
     */
    private String prodRegistCode;

    /**
     * 数据日期 
     */
    private Date dataDate;

    /**
     * 备注 
     */
    private String remark;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 代销机构代码 
     * @return 代销机构代码 
     */
    public String getAgentInstitutionCode() {
        return agentInstitutionCode;
    }

    /**
     * 设置 代销机构代码 
     * @param agentInstitutionCode 代销机构代码 
     */
    public void setAgentInstitutionCode(String agentInstitutionCode) {
        this.agentInstitutionCode = agentInstitutionCode == null ? null : agentInstitutionCode.trim();
    }

    /**
     * 取得 代销机构名称
     * @return 代销机构名称
     */
    public String getAgentInstitutionName() {
        return agentInstitutionName;
    }

    /**
     * 设置 代销机构名称
     * @param agentInstitutionName 代销机构名称
     */
    public void setAgentInstitutionName(String agentInstitutionName) {
        this.agentInstitutionName = agentInstitutionName == null ? null : agentInstitutionName.trim();
    }

    /**
     * 取得 发行机构代码 
     * @return 发行机构代码 
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码 
     * @param issuePartyId 发行机构代码 
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 理财产品代码 
     * @return 理财产品代码 
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品代码 
     * @param prodId 理财产品代码 
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 理财产品名称 
     * @return 理财产品名称 
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 理财产品名称 
     * @param prodName 理财产品名称 
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品登记编码 
     * @return 产品登记编码 
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码 
     * @param prodRegistCode 产品登记编码 
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
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