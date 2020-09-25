package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinTradeProductAdd extends FinTradeProductAddKey {
    /**
     * ?????????????????????????????????????
     */
    private String finprodType;

    /**
     * ?????????????????????????????????????
     */
    private String finprodType2;

    /**
     * ??????
     */
    private String registOrg;

    /**
     * ????
     */
    private Long pledgeSecNum;

    /**
     * 质押劵置换安排
     */
    private String exchangePledge;

    /**
     * 补充条款
     */
    private String supplyClause;

    /**
     * 是否计提罚息
     */
    private String isAccrPenaltyInt;

    /**
     * 罚息计提基数
     */
    private String penaltyBaseType;

    /**
     * 期权费率类型
     */
    private String optFeePayType;

    /**
     * 期权费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optFeeRate;

    /**
     * 期权费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optFeeAmt;

    /**
     * 取得 ?????????????????????????????????????
     * @return ?????????????????????????????????????
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 ?????????????????????????????????????
     * @param finprodType ?????????????????????????????????????
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 ?????????????????????????????????????
     * @return ?????????????????????????????????????
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 ?????????????????????????????????????
     * @param finprodType2 ?????????????????????????????????????
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 ??????
     * @param registOrg ??????
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public Long getPledgeSecNum() {
        return pledgeSecNum;
    }

    /**
     * 设置 ????
     * @param pledgeSecNum ????
     */
    public void setPledgeSecNum(Long pledgeSecNum) {
        this.pledgeSecNum = pledgeSecNum;
    }

    /**
     * 取得 质押劵置换安排
     * @return 质押劵置换安排
     */
    public String getExchangePledge() {
        return exchangePledge;
    }

    /**
     * 设置 质押劵置换安排
     * @param exchangePledge 质押劵置换安排
     */
    public void setExchangePledge(String exchangePledge) {
        this.exchangePledge = exchangePledge == null ? null : exchangePledge.trim();
    }

    /**
     * 取得 补充条款
     * @return 补充条款
     */
    public String getSupplyClause() {
        return supplyClause;
    }

    /**
     * 设置 补充条款
     * @param supplyClause 补充条款
     */
    public void setSupplyClause(String supplyClause) {
        this.supplyClause = supplyClause == null ? null : supplyClause.trim();
    }

    /**
     * 取得 是否计提罚息
     * @return 是否计提罚息
     */
    public String getIsAccrPenaltyInt() {
        return isAccrPenaltyInt;
    }

    /**
     * 设置 是否计提罚息
     * @param isAccrPenaltyInt 是否计提罚息
     */
    public void setIsAccrPenaltyInt(String isAccrPenaltyInt) {
        this.isAccrPenaltyInt = isAccrPenaltyInt == null ? null : isAccrPenaltyInt.trim();
    }

    /**
     * 取得 罚息计提基数
     * @return 罚息计提基数
     */
    public String getPenaltyBaseType() {
        return penaltyBaseType;
    }

    /**
     * 设置 罚息计提基数
     * @param penaltyBaseType 罚息计提基数
     */
    public void setPenaltyBaseType(String penaltyBaseType) {
        this.penaltyBaseType = penaltyBaseType == null ? null : penaltyBaseType.trim();
    }

    /**
     * 取得 期权费率类型
     * @return 期权费率类型
     */
    public String getOptFeePayType() {
        return optFeePayType;
    }

    /**
     * 设置 期权费率类型
     * @param optFeePayType 期权费率类型
     */
    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType == null ? null : optFeePayType.trim();
    }

    /**
     * 取得 期权费率
     * @return 期权费率
     */
    public BigDecimal getOptFeeRate() {
        return optFeeRate;
    }

    /**
     * 设置 期权费率
     * @param optFeeRate 期权费率
     */
    public void setOptFeeRate(BigDecimal optFeeRate) {
        this.optFeeRate = optFeeRate;
    }

    /**
     * 取得 期权费
     * @return 期权费
     */
    public BigDecimal getOptFeeAmt() {
        return optFeeAmt;
    }

    /**
     * 设置 期权费
     * @param optFeeAmt 期权费
     */
    public void setOptFeeAmt(BigDecimal optFeeAmt) {
        this.optFeeAmt = optFeeAmt;
    }
}