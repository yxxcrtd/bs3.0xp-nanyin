package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepPosition extends RepPositionKey {
    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 产品登记编码
     */
    private String repFinprodRegId;

    /**
     * 持仓类别
     */
    private String repPositionType;

    /**
     * 币种
     */
    private String repCcy;

    /**
     * 余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBalance;

    /**
     * 折算人民币余额（元/人民币）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBalanceCny;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCount;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 产品终止日期
     */
    private Date repPosRegDate;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 取得 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @return 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @param repIssuePartyId 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId == null ? null : repIssuePartyId.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRepFinprodRegId() {
        return repFinprodRegId;
    }

    /**
     * 设置 产品登记编码
     * @param repFinprodRegId 产品登记编码
     */
    public void setRepFinprodRegId(String repFinprodRegId) {
        this.repFinprodRegId = repFinprodRegId == null ? null : repFinprodRegId.trim();
    }

    /**
     * 取得 持仓类别
     * @return 持仓类别
     */
    public String getRepPositionType() {
        return repPositionType;
    }

    /**
     * 设置 持仓类别
     * @param repPositionType 持仓类别
     */
    public void setRepPositionType(String repPositionType) {
        this.repPositionType = repPositionType == null ? null : repPositionType.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getRepCcy() {
        return repCcy;
    }

    /**
     * 设置 币种
     * @param repCcy 币种
     */
    public void setRepCcy(String repCcy) {
        this.repCcy = repCcy == null ? null : repCcy.trim();
    }

    /**
     * 取得 余额
     * @return 余额
     */
    public BigDecimal getRepBalance() {
        return repBalance;
    }

    /**
     * 设置 余额
     * @param repBalance 余额
     */
    public void setRepBalance(BigDecimal repBalance) {
        this.repBalance = repBalance;
    }

    /**
     * 取得 折算人民币余额（元/人民币）
     * @return 折算人民币余额（元/人民币）
     */
    public BigDecimal getRepBalanceCny() {
        return repBalanceCny;
    }

    /**
     * 设置 折算人民币余额（元/人民币）
     * @param repBalanceCny 折算人民币余额（元/人民币）
     */
    public void setRepBalanceCny(BigDecimal repBalanceCny) {
        this.repBalanceCny = repBalanceCny;
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getRepCount() {
        return repCount;
    }

    /**
     * 设置 数量
     * @param repCount 数量
     */
    public void setRepCount(BigDecimal repCount) {
        this.repCount = repCount;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }

    /**
     * 取得 产品终止日期
     * @return 产品终止日期
     */
    public Date getRepPosRegDate() {
        return repPosRegDate;
    }

    /**
     * 设置 产品终止日期
     * @param repPosRegDate 产品终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepPosRegDate(Date repPosRegDate) {
        this.repPosRegDate = repPosRegDate;
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getRepSendStatus() {
        return repSendStatus;
    }

    /**
     * 设置 报送状态
     * @param repSendStatus 报送状态
     */
    public void setRepSendStatus(String repSendStatus) {
        this.repSendStatus = repSendStatus == null ? null : repSendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRepStatusDate() {
        return repStatusDate;
    }

    /**
     * 设置 数据日期
     * @param repStatusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepStatusDate(Date repStatusDate) {
        this.repStatusDate = repStatusDate;
    }
}