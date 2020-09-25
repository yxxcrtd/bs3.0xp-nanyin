package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbRegInfoStatus extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 业务标识
     */
    private String busiMark;

    /**
     * 报表类型
     */
    private String reportType;

    /**
     * 数据日期
     */
    private Date dataDate;

    /**
     * 报送状态
     */
    private String submitStatus;

    /**
     * 报送日期
     */
    private Date submitDate;

    /**
     * 报送截止日
     */
    private Date submitEndDate;

    /**
     * 反馈状态
     */
    private String feedbackStatus;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
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
     * 取得 业务标识
     * @return 业务标识
     */
    public String getBusiMark() {
        return busiMark;
    }

    /**
     * 设置 业务标识
     * @param busiMark 业务标识
     */
    public void setBusiMark(String busiMark) {
        this.busiMark = busiMark == null ? null : busiMark.trim();
    }

    /**
     * 取得 报表类型
     * @return 报表类型
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * 设置 报表类型
     * @param reportType 报表类型
     */
    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
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
    public Date getSubmitDate() {
        return submitDate;
    }

    /**
     * 设置 报送日期
     * @param submitDate 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    /**
     * 取得 报送截止日
     * @return 报送截止日
     */
    public Date getSubmitEndDate() {
        return submitEndDate;
    }

    /**
     * 设置 报送截止日
     * @param submitEndDate 报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitEndDate(Date submitEndDate) {
        this.submitEndDate = submitEndDate;
    }

    /**
     * 取得 反馈状态
     * @return 反馈状态
     */
    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    /**
     * 设置 反馈状态
     * @param feedbackStatus 反馈状态
     */
    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus == null ? null : feedbackStatus.trim();
    }

    /**
     * 取得 数据类型
     * @return 数据类型
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置 数据类型
     * @param dataType 数据类型
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
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
}