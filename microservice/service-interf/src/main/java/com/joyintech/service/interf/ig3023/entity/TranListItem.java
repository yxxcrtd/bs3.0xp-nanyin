/*
 * 文件名：TranListItem.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig3023.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * G3023交易明细数组
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月3日
 * @see TranListItem
 * @since 1.0
 */
public class TranListItem {

    /**
     * 机构(分行)号
     */
    private String branchId;

    /**
     * 柜员号
     */
    private String tellerNo;

    /**
     * 交易日期
     */
    private String tranDate;

    /**
     * 业务流水号
     */
    private String bussSeqNo;

    /**
     * 交易流水号
     */
    private String tranSeqNo;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 交易码
     */
    private String tranCode;

    /**
     * 交易状态
     */
    private String tranSatus;

    /**
     * 标志
     */
    private String flag;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 授权柜员(审核库管员)
     */
    private String authTellerNo;

    /**
     * 对账单号
     */
    private String statementNo;

    /**
     * 取得 机构(分行)号
     * 
     * @return 机构(分行)号
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 取得 柜员号
     * 
     * @return 柜员号
     */
    public String getTellerNo() {
        return tellerNo;
    }

    /**
     * 取得 交易日期
     * 
     * @return 交易日期
     */
    public String getTranDate() {
        return tranDate;
    }

    /**
     * 取得 业务流水号
     * 
     * @return 业务流水号
     */
    public String getBussSeqNo() {
        return bussSeqNo;
    }

    /**
     * 取得 交易流水号
     * 
     * @return 交易流水号
     */
    public String getTranSeqNo() {
        return tranSeqNo;
    }

    /**
     * 取得 流水号
     * 
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 取得 交易码
     * 
     * @return 交易码
     */
    public String getTranCode() {
        return tranCode;
    }

    /**
     * 取得 交易状态
     * 
     * @return 交易状态
     */
    public String getTranSatus() {
        return tranSatus;
    }

    /**
     * 取得 标志
     * 
     * @return 标志
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 取得 错误码
     * 
     * @return 错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 取得 错误信息
     * 
     * @return 错误信息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 取得 授权柜员(审核库管员)
     * 
     * @return 授权柜员(审核库管员)
     */
    public String getAuthTellerNo() {
        return authTellerNo;
    }

    /**
     * 取得 对账单号
     * 
     * @return 对账单号
     */
    public String getStatementNo() {
        return statementNo;
    }

    /**
     * 设置 机构(分行)号
     * 
     * @param branchId 机构(分行)号
     */
    @EsbName(value = "BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * 设置 柜员号
     * 
     * @param tellerNo 柜员号
     */
    @EsbName(value = "TELLER_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTellerNo(String tellerNo) {
        this.tellerNo = tellerNo;
    }

    /**
     * 设置 交易日期
     * 
     * @param tranDate 交易日期
     */
    @EsbName(value = "TRAN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * 设置 业务流水号
     * 
     * @param bussSeqNo 业务流水号
     */
    @EsbName(value = "BUSS_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setBussSeqNo(String bussSeqNo) {
        this.bussSeqNo = bussSeqNo;
    }

    /**
     * 设置 交易流水号
     * 
     * @param tranSeqNo 交易流水号
     */
    @EsbName(value = "TRAN_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTranSeqNo(String tranSeqNo) {
        this.tranSeqNo = tranSeqNo;
    }

    /**
     * 设置 流水号
     * 
     * @param seqNo 流水号
     */
    @EsbName(value = "SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 设置 交易码
     * 
     * @param tranCode 交易码
     */
    @EsbName(value = "TRAN_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    /**
     * 设置 交易状态
     * 
     * @param tranSatus 交易状态
     */
    @EsbName(value = "TRAN_STATUS")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTranSatus(String tranSatus) {
        this.tranSatus = tranSatus;
    }

    /**
     * 设置 标志
     * 
     * @param flag 标志
     */
    @EsbName(value = "FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 设置 错误码
     * 
     * @param errorCode 错误码
     */
    @EsbName(value = "ERROR_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 设置 错误信息
     * 
     * @param errorMsg 错误信息
     */
    @EsbName(value = "ERROR_MSG")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 设置 授权柜员(审核库管员)
     * 
     * @param authTellerNo 授权柜员(审核库管员)
     */
    @EsbName(value = "AUTH_TELLER_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setAuthTellerNo(String authTellerNo) {
        this.authTellerNo = authTellerNo;
    }

    /**
     * 设置 对账单号
     * 
     * @param statementNo 对账单号
     */
    @EsbName(value = "STATEMENT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setStatementNo(String statementNo) {
        this.statementNo = statementNo;
    }
}
