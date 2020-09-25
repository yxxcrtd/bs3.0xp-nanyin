/*
 * 文件名：Interf3005OutArrEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i3005.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口3005-输出-交易信息数组
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月13日
 * @see Interf3005OutArrEntity
 * @since 1.0
 */
public class Interf3005OutArrEntity {

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 付款人账号
     */
    private String payerAcctNo;

    /**
     * 收款人账号
     */
    private String payeeAcctNo;

    /**
     * 交易金额
     */
    private String tranAmt;

    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 业务流水号
     */
    private String bussSeqNo;

    /**
     * 主机流水号
     */
    private String hostSeqNo;

    /**
     * 主机日期
     */
    private String hostDate;

    /**
     * 设置 流水号
     * 
     * @param seqNo 流水号
     */
    @EsbName(value = "SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Required
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 设置 付款人账号
     * 
     * @param payerAcctNo 付款人账号
     */
    @EsbName(value = "PAYER_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setPayerAcctNo(String payerAcctNo) {
        this.payerAcctNo = payerAcctNo;
    }

    /**
     * 设置 收款人账号
     * 
     * @param payeeAcctNo 收款人账号
     */
    @EsbName(value = "PAYEE_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setPayeeAcctNo(String payeeAcctNo) {
        this.payeeAcctNo = payeeAcctNo;
    }

    /**
     * 设置 交易金额
     * 
     * @param tranAmt 交易金额
     */
    @EsbName(value = "TRAN_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    /**
     * 设置 状态描述
     * 
     * @param statusDesc 状态描述
     */
    @EsbName(value = "STATUS_DESC")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Required
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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
     * 设置 主机流水号
     * 
     * @param hostSeqNo 主机流水号
     */
    @EsbName(value = "HOST_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setHostSeqNo(String hostSeqNo) {
        this.hostSeqNo = hostSeqNo;
    }

    /**
     * 设置 主机日期
     * 
     * @param hostDate 主机日期
     */
    @EsbName(value = "HOST_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setHostDate(String hostDate) {
        this.hostDate = hostDate;
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
     * 取得 付款人账号
     * 
     * @return 付款人账号
     */
    public String getPayerAcctNo() {
        return payerAcctNo;
    }

    /**
     * 取得 收款人账号
     * 
     * @return 收款人账号
     */
    public String getPayeeAcctNo() {
        return payeeAcctNo;
    }

    /**
     * 取得 交易金额
     * 
     * @return 交易金额
     */
    public String getTranAmt() {
        return tranAmt;
    }

    /**
     * 取得 状态描述
     * 
     * @return 状态描述
     */
    public String getStatusDesc() {
        return statusDesc;
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
     * 取得 主机流水号
     * 
     * @return 主机流水号
     */
    public String getHostSeqNo() {
        return hostSeqNo;
    }

    /**
     * 取得 主机日期
     * 
     * @return 主机日期
     */
    public String getHostDate() {
        return hostDate;
    }
}
