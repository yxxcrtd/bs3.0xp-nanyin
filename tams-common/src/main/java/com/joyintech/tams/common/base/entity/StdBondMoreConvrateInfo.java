package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class StdBondMoreConvrateInfo extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 券商代码
     */
    private String brokerId;

    /**
     * 资金账号
     */
    private String acctNo;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 再折算率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal moreConvrate;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 序列号
     * @param seqNo 序列号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 券商代码
     * @return 券商代码
     */
    public String getBrokerId() {
        return brokerId;
    }

    /**
     * 设置 券商代码
     * @param brokerId 券商代码
     */
    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId == null ? null : brokerId.trim();
    }

    /**
     * 取得 资金账号
     * @return 资金账号
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 资金账号
     * @param acctNo 资金账号
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 资产代码
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 开始日期
     * @return 开始日期
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置 开始日期
     * @param startDate 开始日期
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    /**
     * 取得 结束日期
     * @return 结束日期
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置 结束日期
     * @param endDate 结束日期
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    /**
     * 取得 再折算率
     * @return 再折算率
     */
    public BigDecimal getMoreConvrate() {
        return moreConvrate;
    }

    /**
     * 设置 再折算率
     * @param moreConvrate 再折算率
     */
    public void setMoreConvrate(BigDecimal moreConvrate) {
        this.moreConvrate = moreConvrate;
    }
}