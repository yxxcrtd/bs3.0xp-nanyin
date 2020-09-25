package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class ChbSubmitStatus extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 批次号
     */
    private String submitNum;

    /**
     * 报送类型
     */
    private String submitType;

    /**
     * 报送反馈状态
     */
    private String submitFeedbackStatus;

    /**
     * 报送编码
     */
    private String reportNo;

    /**
     * 报送反馈消息
     */
    private String submitFeedbackMsg;

    /**
     * 唯一标识
     */
    private String uniqueIdenti;

    /**
     * 业务确认日
     */
    private Date equityConfDate;
    
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
     * 取得 批次号
     * @return 批次号
     */
    public String getSubmitNum() {
        return submitNum;
    }

    /**
     * 设置 批次号
     * @param submitNum 批次号
     */
    public void setSubmitNum(String submitNum) {
        this.submitNum = submitNum == null ? null : submitNum.trim();
    }

    /**
     * 取得 报送类型
     * @return 报送类型
     */
    public String getSubmitType() {
        return submitType;
    }

    /**
     * 设置 报送类型
     * @param submitType 报送类型
     */
    public void setSubmitType(String submitType) {
        this.submitType = submitType == null ? null : submitType.trim();
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
     * 取得 报送编码
     * @return 报送编码
     */
    public String getReportNo() {
        return reportNo;
    }

    /**
     * 设置 报送编码
     * @param reportNo 报送编码
     */
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
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
     * 取得 唯一标识
     * @return 唯一标识
     */
    public String getUniqueIdenti() {
        return uniqueIdenti;
    }

    /**
     * 设置 唯一标识
     * @param uniqueIdenti 唯一标识
     */
    public void setUniqueIdenti(String uniqueIdenti) {
        this.uniqueIdenti = uniqueIdenti == null ? null : uniqueIdenti.trim();
    }

	public Date getEquityConfDate() {
		return equityConfDate;
	}

	public void setEquityConfDate(Date equityConfDate) {
		this.equityConfDate = equityConfDate;
	}
}