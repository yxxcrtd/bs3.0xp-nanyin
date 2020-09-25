/*
 * 文件名：InterfG1003OutTransferEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1003.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 新核心记账接口G1003转账数组Entity
 * 
 * @author changwenbin
 * @version 1.0 2018年12月3日
 * @see InterfG1003OutTransferEntity
 * @since 1.0
 */
public class InterfG1003OutTransferEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1292117806588452272L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 交易金额
     */
    private BigDecimal tranAmt;

    /**
     * 转出客户号
     */
    private String outClientNo;

    /**
     * 可用余额
     */
    private BigDecimal availBalance;

    /**
     * 转出挂帐单编号
     */
    private String outSuspendNo;

    /**
     * 转出行号
     */
    private String outBranchId;

    /**
     * 转出账户余额
     */
    private BigDecimal outAcctBalance;

    /**
     * 转出账户用途
     */
    private String outAcctUsage;

    /**
     * 转入客户号
     */
    private String inClientNo;

    /**
     * 转入方账户可用余额
     */
    private BigDecimal inAcctAvailBalance;

    /**
     * 转入账户余额
     */
    private BigDecimal inAcctBalance;

    /**
     * 转入挂帐单编号
     */
    private String inSuspendNo;

    /**
     * 冻结编号
     */
    private String freezeNo;

    /**
     * 转入行号
     */
    private String inBranchId;

    /**
     * 转入账户用途
     */
    private String inAcctUsage;

    /**
     * 授权标志
     */
    private BigDecimal authFlag;

    /**
     * 手续费list
     */
    private List<InterfG1003OutCommissionEntity> commissionList;

    /**
     * 获取 流水号
     * 
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

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
     * 获取 交易金额
     * 
     * @return 交易金额
     */
    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    /**
     * 设置 交易金额
     * 
     * @param tranAmt 交易金额
     */
    @EsbName(value = "TRAN_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    /**
     * 获取 转出客户号
     * 
     * @return 转出客户号
     */
    public String getOutClientNo() {
        return outClientNo;
    }

    /**
     * 设置 转出客户号
     * 
     * @param outClientNo 转出客户号
     */
    @EsbName(value = "OUT_CLIENT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setOutClientNo(String outClientNo) {
        this.outClientNo = outClientNo;
    }

    /**
     * 获取 可用余额
     * 
     * @return 可用余额
     */
    public BigDecimal getAvailBalance() {
        return availBalance;
    }

    /**
     * 设置 可用余额
     * 
     * @param availBalance 可用余额
     */
    @EsbName(value = "AVAIL_BALANCE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setAvailBalance(BigDecimal availBalance) {
        this.availBalance = availBalance;
    }

    /**
     * 获取 转出挂帐单编号
     * 
     * @return 转出挂帐单编号
     */
    public String getOutSuspendNo() {
        return outSuspendNo;
    }

    /**
     * 设置 转出挂帐单编号
     * 
     * @param outSuspendNo 转出挂帐单编号
     */
    @EsbName(value = "OUT_SUSPEND_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setOutSuspendNo(String outSuspendNo) {
        this.outSuspendNo = outSuspendNo;
    }

    /**
     * 获取 转出行号
     * 
     * @return 转出行号
     */
    public String getOutBranchId() {
        return outBranchId;
    }

    /**
     * 设置 转出行号
     * 
     * @param outBranchId 转出行号
     */
    @EsbName(value = "OUT_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setOutBranchId(String outBranchId) {
        this.outBranchId = outBranchId;
    }

    /**
     * 获取 转出账户余额
     * 
     * @return 转出账户余额
     */
    public BigDecimal getOutAcctBalance() {
        return outAcctBalance;
    }

    /**
     * 设置 转出账户余额
     * 
     * @param outAcctBalance 转出账户余额
     */
    @EsbName(value = "OUT_ACCT_BALANCE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setOutAcctBalance(BigDecimal outAcctBalance) {
        this.outAcctBalance = outAcctBalance;
    }

    /**
     * 获取 转出账户用途
     * 
     * @return 转出账户用途
     */
    public String getOutAcctUsage() {
        return outAcctUsage;
    }

    /**
     * 设置 转出账户用途
     * 
     * @param outAcctUsage 转出账户用途
     */
    @EsbName(value = "OUT_ACCT_USAGE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setOutAcctUsage(String outAcctUsage) {
        this.outAcctUsage = outAcctUsage;
    }

    /**
     * 获取 转入客户号
     * 
     * @return 转入客户号
     */
    public String getInClientNo() {
        return inClientNo;
    }

    /**
     * 设置 转入客户号
     * 
     * @param inClientNo 转入客户号
     */
    @EsbName(value = "IN_CLIENT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setInClientNo(String inClientNo) {
        this.inClientNo = inClientNo;
    }

    /**
     * 获取 转入方账户可用余额
     * 
     * @return 转入方账户可用余额
     */
    public BigDecimal getInAcctAvailBalance() {
        return inAcctAvailBalance;
    }

    /**
     * 设置 转入方账户可用余额
     * 
     * @param inAcctAvailBalance 转入方账户可用余额
     */
    @EsbName(value = "IN_ACCT_AVAIL_BALANCE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setInAcctAvailBalance(BigDecimal inAcctAvailBalance) {
        this.inAcctAvailBalance = inAcctAvailBalance;
    }

    /**
     * 获取 转入账户余额
     * 
     * @return 转入账户余额
     */
    public BigDecimal getInAcctBalance() {
        return inAcctBalance;
    }

    /**
     * 设置 转入账户余额
     * 
     * @param inAcctBalance 转入账户余额
     */
    @EsbName(value = "IN_ACCT_BALANCE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setInAcctBalance(BigDecimal inAcctBalance) {
        this.inAcctBalance = inAcctBalance;
    }

    /**
     * 获取 转入挂帐单编号
     * 
     * @return 转入挂帐单编号
     */
    public String getInSuspendNo() {
        return inSuspendNo;
    }

    /**
     * 设置 转入挂帐单编号
     * 
     * @param inSuspendNo 转入挂帐单编号
     */
    @EsbName(value = "IN_SUSPEND_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setInSuspendNo(String inSuspendNo) {
        this.inSuspendNo = inSuspendNo;
    }

    /**
     * 获取 冻结编号
     * 
     * @return 冻结编号
     */
    public String getFreezeNo() {
        return freezeNo;
    }

    /**
     * 设置 冻结编号
     * 
     * @param freezeNo 冻结编号
     */
    @EsbName(value = "FREEZE_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setFreezeNo(String freezeNo) {
        this.freezeNo = freezeNo;
    }

    /**
     * 获取 转入行号
     * 
     * @return 转入行号
     */
    public String getInBranchId() {
        return inBranchId;
    }

    /**
     * 设置 转入行号
     * 
     * @param inBranchId 转入行号
     */
    @EsbName(value = "IN_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setInBranchId(String inBranchId) {
        this.inBranchId = inBranchId;
    }

    /**
     * 获取 转入账户用途
     * 
     * @return 转入账户用途
     */
    public String getInAcctUsage() {
        return inAcctUsage;
    }

    /**
     * 设置 转入账户用途
     * 
     * @param inAcctUsage 转入账户用途
     */
    @EsbName(value = "IN_ACCT_USAGE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setInAcctUsage(String inAcctUsage) {
        this.inAcctUsage = inAcctUsage;
    }

    /**
     * 获取 授权标志
     * 
     * @return 授权标志
     */
    public BigDecimal getAuthFlag() {
        return authFlag;
    }

    /**
     * 设置 授权标志
     * 
     * @param authFlag 授权标志
     */
    @EsbName(value = "AUTH_FLAG")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setAuthFlag(BigDecimal authFlag) {
        this.authFlag = authFlag;
    }

    /**
     * 获取 手续费list
     * 
     * @return 手续费list
     */
    public List<InterfG1003OutCommissionEntity> getCommissionList() {
        return commissionList;
    }

    /**
     * 设置 手续费list
     * 
     * @param commissionList 手续费list
     */
    @EsbName(value = "COMMISSION_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setCommissionList(List<InterfG1003OutCommissionEntity> commissionList) {
        this.commissionList = commissionList;
    }

}
