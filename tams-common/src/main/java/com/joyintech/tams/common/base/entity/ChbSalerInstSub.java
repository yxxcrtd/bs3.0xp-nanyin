package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSalerInstSub extends BaseEntity {
    /**
     * 登记序列 
     */
    private String registSeqno;

    /**
     * 销售机构代码 
     */
    private String salerInstCode;

    /**
     * 销售机构名称 
     */
    private String salerInstName;

    /**
     * 总部或分支机构编码 
     */
    private String salerOrgCode;

    /**
     * 总部或分支机构名称 
     */
    private String salerOrgName;

    /**
     * 机构层级 
     */
    private String salerOrgLevel;

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