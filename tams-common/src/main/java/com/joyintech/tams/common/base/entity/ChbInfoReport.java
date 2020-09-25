package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbInfoReport extends BaseEntity {
    /**
     * 报送编码
     */
    private String reportNo;

    /**
     * 报送报文编码
     */
    private String repMesNo;

    /**
     * 报送结果码
     */
    private String uploadResult;

    /**
     * 报送结果信息
     */
    private String uploadResultMsg;

    /**
     * 报送结果文件代码
     */
    private String uploadFileId;

    /**
     * 确认文件代码
     */
    private String checkFileId;

    /**
     * 确认结果码
     */
    private String checkResult;

    /**
     * 确认结果消息
     */
    private String checkResultMsg;

    /**
     * 确认文件名(中债消息获取API返回值)
     */
    private String checkFileName;

    /**
     * 确认总件数
     */
    private Integer checkTotalCount;

    /**
     * 确认成功件数
     */
    private Integer checkSuccessCount;

    /**
     * 确认失败件数
     */
    private Integer checkFailedCount;

    /**
     * 内部文件代码
     */
    private String insideFileId;

    /**
     * 报送日期
     */
    private Date sendTime;

    /**
     * 确认时间
     */
    private Date replyTime;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送总数
     */
    private Integer submitCount;
    /**
     * 报送类型
     */
    private String submitType;

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
     * 取得 报送报文编码
     * @return 报送报文编码
     */
    public String getRepMesNo() {
        return repMesNo;
    }

    /**
     * 设置 报送报文编码
     * @param repMesNo 报送报文编码
     */
    public void setRepMesNo(String repMesNo) {
        this.repMesNo = repMesNo == null ? null : repMesNo.trim();
    }

    /**
     * 取得 报送结果码
     * @return 报送结果码
     */
    public String getUploadResult() {
        return uploadResult;
    }

    /**
     * 设置 报送结果码
     * @param uploadResult 报送结果码
     */
    public void setUploadResult(String uploadResult) {
        this.uploadResult = uploadResult == null ? null : uploadResult.trim();
    }

    /**
     * 取得 报送结果信息
     * @return 报送结果信息
     */
    public String getUploadResultMsg() {
        return uploadResultMsg;
    }

    /**
     * 设置 报送结果信息
     * @param uploadResultMsg 报送结果信息
     */
    public void setUploadResultMsg(String uploadResultMsg) {
        this.uploadResultMsg = uploadResultMsg == null ? null : uploadResultMsg.trim();
    }

    /**
     * 取得 报送结果文件代码
     * @return 报送结果文件代码
     */
    public String getUploadFileId() {
        return uploadFileId;
    }

    /**
     * 设置 报送结果文件代码
     * @param uploadFileId 报送结果文件代码
     */
    public void setUploadFileId(String uploadFileId) {
        this.uploadFileId = uploadFileId == null ? null : uploadFileId.trim();
    }

    /**
     * 取得 确认文件代码
     * @return 确认文件代码
     */
    public String getCheckFileId() {
        return checkFileId;
    }

    /**
     * 设置 确认文件代码
     * @param checkFileId 确认文件代码
     */
    public void setCheckFileId(String checkFileId) {
        this.checkFileId = checkFileId == null ? null : checkFileId.trim();
    }

    /**
     * 取得 确认结果码
     * @return 确认结果码
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * 设置 确认结果码
     * @param checkResult 确认结果码
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    /**
     * 取得 确认结果消息
     * @return 确认结果消息
     */
    public String getCheckResultMsg() {
        return checkResultMsg;
    }

    /**
     * 设置 确认结果消息
     * @param checkResultMsg 确认结果消息
     */
    public void setCheckResultMsg(String checkResultMsg) {
        this.checkResultMsg = checkResultMsg == null ? null : checkResultMsg.trim();
    }

    /**
     * 取得 确认文件名(中债消息获取API返回值)
     * @return 确认文件名(中债消息获取API返回值)
     */
    public String getCheckFileName() {
        return checkFileName;
    }

    /**
     * 设置 确认文件名(中债消息获取API返回值)
     * @param checkFileName 确认文件名(中债消息获取API返回值)
     */
    public void setCheckFileName(String checkFileName) {
        this.checkFileName = checkFileName == null ? null : checkFileName.trim();
    }

    /**
     * 取得 确认总件数
     * @return 确认总件数
     */
    public Integer getCheckTotalCount() {
        return checkTotalCount;
    }

    /**
     * 设置 确认总件数
     * @param checkTotalCount 确认总件数
     */
    public void setCheckTotalCount(Integer checkTotalCount) {
        this.checkTotalCount = checkTotalCount;
    }

    /**
     * 取得 确认成功件数
     * @return 确认成功件数
     */
    public Integer getCheckSuccessCount() {
        return checkSuccessCount;
    }

    /**
     * 设置 确认成功件数
     * @param checkSuccessCount 确认成功件数
     */
    public void setCheckSuccessCount(Integer checkSuccessCount) {
        this.checkSuccessCount = checkSuccessCount;
    }

    /**
     * 取得 确认失败件数
     * @return 确认失败件数
     */
    public Integer getCheckFailedCount() {
        return checkFailedCount;
    }

    /**
     * 设置 确认失败件数
     * @param checkFailedCount 确认失败件数
     */
    public void setCheckFailedCount(Integer checkFailedCount) {
        this.checkFailedCount = checkFailedCount;
    }

    /**
     * 取得 内部文件代码
     * @return 内部文件代码
     */
    public String getInsideFileId() {
        return insideFileId;
    }

    /**
     * 设置 内部文件代码
     * @param insideFileId 内部文件代码
     */
    public void setInsideFileId(String insideFileId) {
        this.insideFileId = insideFileId == null ? null : insideFileId.trim();
    }

    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置 报送日期
     * @param sendTime 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 取得 确认时间
     * @return 确认时间
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * 设置 确认时间
     * @param replyTime 确认时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
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
     * 取得 报送总数
     * @return 报送总数
     */
    public Integer getSubmitCount() {
        return submitCount;
    }

    /**
     * 设置 报送总数
     * @param submitCount 报送总数
     */
    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
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
}