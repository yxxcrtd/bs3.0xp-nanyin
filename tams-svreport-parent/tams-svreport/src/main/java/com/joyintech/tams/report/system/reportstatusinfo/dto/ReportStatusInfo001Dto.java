package com.joyintech.tams.report.system.reportstatusinfo.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbFeedbackFile;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

public class ReportStatusInfo001Dto {

    /**
     * 登记序列
     */
    private String registSeqNo;

    /**
     * 报送类型
     */
    private String repType;

    /**
     * 唯一标识符
     */
    private String uniqueCode;

    /**
     * 报送流水号
     */
    private String repSeqNo;

    /**
     * 报送日期
     */
    private Date repDate;

    /**
     * 报送状态
     */
    private String repStatus;

    /**
     * 反馈状态
     */
    private String feedbackStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 反馈消息
     */
    private String feedbackMsg;

    public String getRegistSeqNo() {
        return registSeqNo;
    }

    public void setRegistSeqNo(String registSeqNo) {
        this.registSeqNo = registSeqNo;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getRepSeqNo() {
        return repSeqNo;
    }

    public void setRepSeqNo(String repSeqNo) {
        this.repSeqNo = repSeqNo;
    }

    public Date getRepDate() {
        return repDate;
    }

    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }

    public String getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(String repStatus) {
        this.repStatus = repStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedbackMsg() {
        return feedbackMsg;
    }

    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    /**
     * 报送类型
     * @return repType 报送类型
     */
    public String getRepTypeName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.报表名称, this.repType))?
                SysDataUtil.getNameFromDataDict(DictCode.报表名称, this.repType):this.repType;
    }

    /**
     * 报送状态
     * @return
     */
    public String getRepStatusName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.报送状态, this.repStatus))?
                SysDataUtil.getNameFromDataDict(DictCode.报送状态, this.repStatus):this.repStatus;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    /**
     * 报送反馈状态
     * @return
     */
    public String getFeedbackStatusName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.报送反馈状态, this.feedbackStatus))?
                SysDataUtil.getNameFromDataDict(DictCode.报送反馈状态, this.feedbackStatus):this.feedbackStatus;
    }

    /**
     * 报送数据状态
     * @return
     */
    public String getStatusName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.status))?
                SysDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.status):this.status;
    }
}
