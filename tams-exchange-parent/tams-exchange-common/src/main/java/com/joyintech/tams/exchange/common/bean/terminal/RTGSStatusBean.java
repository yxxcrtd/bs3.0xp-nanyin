package com.joyintech.tams.exchange.common.bean.terminal;

import java.io.Serializable;

/**
 * @Author: think
 * @Description: 终端返回RTGS交收状态结构
 * @Date: Created in 15:19 2020/12/10
 */
public class RTGSStatusBean implements Serializable {
    private String accountId;   //账户编号
    private String entrustDate; //委托日期
    private String reportNo;    //报单编号、合同序号
    private String errorCode;   //错误码
    private String errorMsg;    //交收失败原因
    private String status;      //交收状态

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
