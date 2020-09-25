package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_PRD_OPENSCH表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class ChbPrdOpensch extends BaseEntity{
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 计划类型 01:申购计划 02：赎回计划
     */
    private String planType;

    /**
     * 开放周期起始日
     */
    private Date openStart;

    /**
     * 开放周期结束日
     */
    private Date openEnd;

    /**
     * 开放周期起息日
     */
    private Date openVdate;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 产品名称
     * @return PROD_NAME 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

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
     * 计划类型 01:申购计划 02：赎回计划
     * @return PLAN_TYPE 计划类型 01:申购计划 02：赎回计划
     */
    public String getPlanType() {
        return planType;
    }

    /**
     * 计划类型 01:申购计划 02：赎回计划
     * @param planType 计划类型 01:申购计划 02：赎回计划
     */
    public void setPlanType(String planType) {
        this.planType = planType == null ? null : planType.trim();
    }

    /**
     * 开放周期起始日
     * @return OPEN_START 开放周期起始日
     */
    public Date getOpenStart() {
        return openStart;
    }

    /**
     * 开放周期起始日
     * @param openStart 开放周期起始日
     */
    public void setOpenStart(Date openStart) {
        this.openStart = openStart;
    }

    /**
     * 开放周期结束日
     * @return OPEN_END 开放周期结束日
     */
    public Date getOpenEnd() {
        return openEnd;
    }

    /**
     * 开放周期结束日
     * @param openEnd 开放周期结束日
     */
    public void setOpenEnd(Date openEnd) {
        this.openEnd = openEnd;
    }

    /**
     * 开放周期起息日
     * @return OPEN_VDATE 开放周期起息日
     */
    public Date getOpenVdate() {
        return openVdate;
    }

    /**
     * 开放周期起息日
     * @param openVdate 开放周期起息日
     */
    public void setOpenVdate(Date openVdate) {
        this.openVdate = openVdate;
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
     * 数据来源
     * @return DATA_SOURCE 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
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
     * 撤销日
     * @return REV_DATE 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 撤销日
     * @param revDate 撤销日
     */
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
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
}