package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbEqutotSubHis extends ChbEqutotSubHisKey {
    /**
     * 产品代码
     */
    private String account;

    /**
     * 业务种类
     */
    private String busiType;

    /**
     * 明细
     */
    private Long detail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报送状态
     */
    private String submitStatus;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 报送截止日
     */
    private Date submitDeadline;

    /**
     * 报送反馈状态
     */
    private String submitFeedbackStatus;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shares;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 产品代码
     * @param account 产品代码
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 业务种类
     * @return 业务种类
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务种类
     * @param busiType 业务种类
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 明细
     * @return 明细
     */
    public Long getDetail() {
        return detail;
    }

    /**
     * 设置 明细
     * @param detail 明细
     */
    public void setDetail(Long detail) {
        this.detail = detail;
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
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSubmitStatus() {
        return submitStatus;
    }

    /**
     * 设置 报送状态
     * @param submitStatus 报送状态
     */
    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus == null ? null : submitStatus.trim();
    }

    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送日期
     * @param submitTime 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 报送截止日
     * @return 报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 报送截止日
     * @param submitDeadline 报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 取得 报送反馈状态
     * @return 报送反馈状态
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 设置 报送反馈状态
     * @param submitFeedbackStatus 报送反馈状态
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
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
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 份额
     * @return 份额
     */
    public BigDecimal getShares() {
        return shares;
    }

    /**
     * 设置 份额
     * @param shares 份额
     */
    public void setShares(BigDecimal shares) {
        this.shares = shares;
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

    /**
     * 取得 中债报送截止日
     * @return 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 设置 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }
}