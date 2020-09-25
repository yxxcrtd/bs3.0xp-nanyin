package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepBottomAsset extends RepBottomAssetKey {
    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 资管及委外资产当前总折算人民币发行金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSumMoney;

    /**
     * 资管及委外资产当前总数量

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSumAmount;

    /**
     * 资管及委外资产未投资头寸（元）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repUninvestCash;

    /**
     * 底层资产行内资产/负债编码
     */
    private String repBottomCode2;

    /**
     * 底层资产持仓数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBottomAssetAmount;

    /**
     * 底层资产折算人民币市值（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBottomAssetSmv;

    /**
     * 登记日期
     */
    private Date repRegisDate;

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
     * 取得 资管及委外资产当前总折算人民币发行金额（元）
     * @return 资管及委外资产当前总折算人民币发行金额（元）
     */
    public BigDecimal getRepSumMoney() {
        return repSumMoney;
    }

    /**
     * 设置 资管及委外资产当前总折算人民币发行金额（元）
     * @param repSumMoney 资管及委外资产当前总折算人民币发行金额（元）
     */
    public void setRepSumMoney(BigDecimal repSumMoney) {
        this.repSumMoney = repSumMoney;
    }

    /**
     * 取得 资管及委外资产当前总数量

     * @return 资管及委外资产当前总数量

     */
    public BigDecimal getRepSumAmount() {
        return repSumAmount;
    }

    /**
     * 设置 资管及委外资产当前总数量

     * @param repSumAmount 资管及委外资产当前总数量

     */
    public void setRepSumAmount(BigDecimal repSumAmount) {
        this.repSumAmount = repSumAmount;
    }

    /**
     * 取得 资管及委外资产未投资头寸（元）

     * @return 资管及委外资产未投资头寸（元）

     */
    public BigDecimal getRepUninvestCash() {
        return repUninvestCash;
    }

    /**
     * 设置 资管及委外资产未投资头寸（元）

     * @param repUninvestCash 资管及委外资产未投资头寸（元）

     */
    public void setRepUninvestCash(BigDecimal repUninvestCash) {
        this.repUninvestCash = repUninvestCash;
    }

    /**
     * 取得 底层资产行内资产/负债编码
     * @return 底层资产行内资产/负债编码
     */
    public String getRepBottomCode2() {
        return repBottomCode2;
    }

    /**
     * 设置 底层资产行内资产/负债编码
     * @param repBottomCode2 底层资产行内资产/负债编码
     */
    public void setRepBottomCode2(String repBottomCode2) {
        this.repBottomCode2 = repBottomCode2 == null ? null : repBottomCode2.trim();
    }

    /**
     * 取得 底层资产持仓数量
     * @return 底层资产持仓数量
     */
    public BigDecimal getRepBottomAssetAmount() {
        return repBottomAssetAmount;
    }

    /**
     * 设置 底层资产持仓数量
     * @param repBottomAssetAmount 底层资产持仓数量
     */
    public void setRepBottomAssetAmount(BigDecimal repBottomAssetAmount) {
        this.repBottomAssetAmount = repBottomAssetAmount;
    }

    /**
     * 取得 底层资产折算人民币市值（元）
     * @return 底层资产折算人民币市值（元）
     */
    public BigDecimal getRepBottomAssetSmv() {
        return repBottomAssetSmv;
    }

    /**
     * 设置 底层资产折算人民币市值（元）
     * @param repBottomAssetSmv 底层资产折算人民币市值（元）
     */
    public void setRepBottomAssetSmv(BigDecimal repBottomAssetSmv) {
        this.repBottomAssetSmv = repBottomAssetSmv;
    }

    /**
     * 取得 登记日期
     * @return 登记日期
     */
    public Date getRepRegisDate() {
        return repRegisDate;
    }

    /**
     * 设置 登记日期
     * @param repRegisDate 登记日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepRegisDate(Date repRegisDate) {
        this.repRegisDate = repRegisDate;
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