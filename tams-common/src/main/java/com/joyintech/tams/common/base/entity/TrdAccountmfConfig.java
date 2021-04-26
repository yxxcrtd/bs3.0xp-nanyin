package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TrdAccountmfConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 费用品种
     */
    private String feeType;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 支付频率
     */
    private String payCycle;

    /**
     * 计提开始日
     */
    private Date positionStartDate;

    /**
     * 计提结束日
     */
    private Date positionEndDate;

    /**
     * 区间费用金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmtTotal;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 费用品种
     * @return 费用品种
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用品种
     * @param feeType 费用品种
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 支付频率
     * @return 支付频率
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 支付频率
     * @param payCycle 支付频率
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle == null ? null : payCycle.trim();
    }

    /**
     * 取得 计提开始日
     * @return 计提开始日
     */
    public Date getPositionStartDate() {
        return positionStartDate;
    }

    /**
     * 设置 计提开始日
     * @param positionStartDate 计提开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPositionStartDate(Date positionStartDate) {
        this.positionStartDate = positionStartDate;
    }

    /**
     * 取得 计提结束日
     * @return 计提结束日
     */
    public Date getPositionEndDate() {
        return positionEndDate;
    }

    /**
     * 设置 计提结束日
     * @param positionEndDate 计提结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPositionEndDate(Date positionEndDate) {
        this.positionEndDate = positionEndDate;
    }

    /**
     * 取得 区间费用金额
     * @return 区间费用金额
     */
    public BigDecimal getFeeAmtTotal() {
        return feeAmtTotal;
    }

    /**
     * 设置 区间费用金额
     * @param feeAmtTotal 区间费用金额
     */
    public void setFeeAmtTotal(BigDecimal feeAmtTotal) {
        this.feeAmtTotal = feeAmtTotal;
    }
}