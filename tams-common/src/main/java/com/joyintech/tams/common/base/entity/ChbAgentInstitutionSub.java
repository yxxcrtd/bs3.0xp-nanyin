package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbAgentInstitutionSub extends BaseEntity {
    /**
     * 登记序列 
     */
    private String registSeqno;

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
     * 报送流水号 
     */
    private String sumitSeq;

    /**
     * 中债报送截止日 
     */
    private Date chbSubmitDeadline;

    /**
     * 行内报送截止日 
     */
    private Date submitDeadline;

    /**
     * 报送状态 
     */
    private String sendStatus;

    /**
     * 报送时间 
     */
    private Date submitTime;

    /**
     * 反馈状态 
     */
    private String submitFeedbackStatus;

    /**
     * 报送反馈消息 
     */
    private String submitFeedbackMsg;

    /**
     * 附件代码
     */
    private String fileNo;

    /**
     * 附件个数 
     */
    private String enclosureNum;

    /**
     * 附件存放目录（包括文件名称）
     */
    private String enclosureAddress;

    /**
     * 附件修改标志
     */
    private String enclosureChangeFlag;

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
     * 取得 登记序列 
     * @return 登记序列 
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 登记序列 
     * @param registSeqno 登记序列 
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

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
     * 取得 报送流水号 
     * @return 报送流水号 
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 报送流水号 
     * @param sumitSeq 报送流水号 
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
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

    /**
     * 取得 行内报送截止日 
     * @return 行内报送截止日 
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 行内报送截止日 
     * @param submitDeadline 行内报送截止日 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 取得 报送状态 
     * @return 报送状态 
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态 
     * @param sendStatus 报送状态 
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 报送时间 
     * @return 报送时间 
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送时间 
     * @param submitTime 报送时间 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 反馈状态 
     * @return 反馈状态 
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 设置 反馈状态 
     * @param submitFeedbackStatus 反馈状态 
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 取得 报送反馈消息 
     * @return 报送反馈消息 
     */
    public String getSubmitFeedbackMsg() {
        return submitFeedbackMsg;
    }

    /**
     * 设置 报送反馈消息 
     * @param submitFeedbackMsg 报送反馈消息 
     */
    public void setSubmitFeedbackMsg(String submitFeedbackMsg) {
        this.submitFeedbackMsg = submitFeedbackMsg == null ? null : submitFeedbackMsg.trim();
    }

    /**
     * 取得 附件代码
     * @return 附件代码
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置 附件代码
     * @param fileNo 附件代码
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
    }

    /**
     * 取得 附件个数 
     * @return 附件个数 
     */
    public String getEnclosureNum() {
        return enclosureNum;
    }

    /**
     * 设置 附件个数 
     * @param enclosureNum 附件个数 
     */
    public void setEnclosureNum(String enclosureNum) {
        this.enclosureNum = enclosureNum == null ? null : enclosureNum.trim();
    }

    /**
     * 取得 附件存放目录（包括文件名称）
     * @return 附件存放目录（包括文件名称）
     */
    public String getEnclosureAddress() {
        return enclosureAddress;
    }

    /**
     * 设置 附件存放目录（包括文件名称）
     * @param enclosureAddress 附件存放目录（包括文件名称）
     */
    public void setEnclosureAddress(String enclosureAddress) {
        this.enclosureAddress = enclosureAddress == null ? null : enclosureAddress.trim();
    }

    /**
     * 取得 附件修改标志
     * @return 附件修改标志
     */
    public String getEnclosureChangeFlag() {
        return enclosureChangeFlag;
    }

    /**
     * 设置 附件修改标志
     * @param enclosureChangeFlag 附件修改标志
     */
    public void setEnclosureChangeFlag(String enclosureChangeFlag) {
        this.enclosureChangeFlag = enclosureChangeFlag == null ? null : enclosureChangeFlag.trim();
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