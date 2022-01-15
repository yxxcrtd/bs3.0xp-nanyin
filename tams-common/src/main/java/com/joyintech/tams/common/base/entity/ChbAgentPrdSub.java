package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbAgentPrdSub extends BaseEntity {
    /**
     * 登记序列 
     */
    private String registSeqno;

    /**
     * 代销机构代码 
     */
    private String agentInstitutionCode;

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
     * 取得 业务主键
     */
    public String getKey(){
        return this.getAgentInstitutionCode() + "_" + this.getProdRegistCode();
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