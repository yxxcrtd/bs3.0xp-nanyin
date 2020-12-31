package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

/**
 * 描述:TRD_ACCOUNTMF_CONFIG表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2020-07-15
 */
public class TrdAccountmfConfigDto extends BaseEntity{
    /**
     * 流水号
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String seqNo;

    /**
     * 费用品种
     */
    private String feeType;

    /**
     * 证券账户
     */
    private String secAcctId;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 支付频率
     */
    private String payCycle;

    /**
     * 支付日（几号）
     */
    private String payDate;

    /**
     * 计提开始日
     */
    private Date positionStartDate;

    /**
     * 计提结束日
     */
    private Date positionEndDate;

    /**
     * 实际计提结束日
     */
    private Date actPositionEndDate;

    /**
     * 每季费用金额
     */
    private BigDecimal feeAmtTotal;

    /**
     * 交易状态
     */
    private String dealStatus;

    /**
     * 流水号
     * @return SEQ_NO 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 费用品种
     * @return FEE_TYPE 费用品种
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 费用品种
     * @param feeType 费用品种
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 证券账户
     * @return SEC_ACCT_ID 证券账户
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 证券账户
     * @param secAcctId 证券账户
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 登记托管机构
     * @return REGIST_ORG 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 支付频率
     * @return PAY_CYCLE 支付频率
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 支付频率
     * @param payCycle 支付频率
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle == null ? null : payCycle.trim();
    }

    /**
     * 支付日（几号）
     * @return PAY_DATE 支付日（几号）
     */
    public String getPayDate() {
        return payDate;
    }

    /**
     * 支付日（几号）
     * @param payDate 支付日（几号）
     */
    public void setPayDate(String payDate) {
        this.payDate = payDate == null ? null : payDate.trim();
    }

    /**
     * 计提开始日
     * @return POSITION_START_DATE 计提开始日
     */
    public Date getPositionStartDate() {
        return positionStartDate;
    }

    /**
     * 计提开始日
     * @param positionStartDate 计提开始日
     */
    public void setPositionStartDate(Date positionStartDate) {
        this.positionStartDate = positionStartDate;
    }

    /**
     * 计提结束日
     * @return POSITION_END_DATE 计提结束日
     */
    public Date getPositionEndDate() {
        return positionEndDate;
    }

    /**
     * 计提结束日
     * @param positionEndDate 计提结束日
     */
    public void setPositionEndDate(Date positionEndDate) {
        this.positionEndDate = positionEndDate;
    }

    /**
     * 实际计提结束日
     * @return ACT_POSITION_END_DATE 实际计提结束日
     */
    public Date getActPositionEndDate() {
        return actPositionEndDate;
    }

    /**
     * 实际计提结束日
     * @param actPositionEndDate 实际计提结束日
     */
    public void setActPositionEndDate(Date actPositionEndDate) {
        this.actPositionEndDate = actPositionEndDate;
    }

    /**
     * 每季费用金额
     * @return FEE_AMT_TOTAL 每季费用金额
     */
    public BigDecimal getFeeAmtTotal() {
        return feeAmtTotal;
    }

    /**
     * 每季费用金额
     * @param feeAmtTotal 每季费用金额
     */
    public void setFeeAmtTotal(BigDecimal feeAmtTotal) {
        this.feeAmtTotal = feeAmtTotal;
    }

    /**
     * 交易状态
     * @return DEAL_STATUS 交易状态
     */
    public String getDealStatus() {
        return dealStatus;
    }

    /**
     * 交易状态
     * @param dealStatus 交易状态
     */
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus == null ? null : dealStatus.trim();
    }

}