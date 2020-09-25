package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_AST_VAL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-02-15
 */
public class ChbAstValue  extends ChbAstValueKey{


    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 单位净价
     */
    private BigDecimal unitValNet;

    /**
     * 单位全价
     */
    private BigDecimal unitValTotal;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据来源
     */
    private String datasource;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 行内资产/负债编码
     * @return ASSET_CODE 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 估值日期
     * @return VAL_DATE 估值日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 估值日期
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 单位净价
     * @return UNIT_VAL_NET 单位净价
     */
    public BigDecimal getUnitValNet() {
        return unitValNet;
    }

    /**
     * 单位净价
     * @param unitValNet 单位净价
     */
    public void setUnitValNet(BigDecimal unitValNet) {
        this.unitValNet = unitValNet;
    }

    /**
     * 单位全价
     * @return UNIT_VAL_TOTAL 单位全价
     */
    public BigDecimal getUnitValTotal() {
        return unitValTotal;
    }

    /**
     * 单位全价
     * @param unitValTotal 单位全价
     */
    public void setUnitValTotal(BigDecimal unitValTotal) {
        this.unitValTotal = unitValTotal;
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 数据来源
     * @return DATASOURCE 数据来源
     */
    public String getDatasource() {
        return datasource;
    }

    /**
     * 数据来源
     * @param datasource 数据来源
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    /**
     * 数据状态
     * @return STATUS 数据状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 工作流代码
     * @return WORKFLOW_ID 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}