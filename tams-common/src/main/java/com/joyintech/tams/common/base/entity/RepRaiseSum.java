package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepRaiseSum extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 发行机构名称
     */
    private String repIssuePartyName;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 产品登记编码
     */
    private String repFinprodRegId;

    /**
     * 行内标识码/理财产品代码
     */
    private String repBankProdCode;

    /**
     * 个人投资者总数
     */
    private Integer repPerSum;

    /**
     * 法人投资者总数
     */
    private Integer repLegalpersonSum;

    /**
     * 非法人投资者总数
     */
    private Integer repNLegalpersonSum;

    /**
     * 认购币种
     */
    private String repSubscriptionCcy;

    /**
     * 认购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSubscriptionMon;

    /**
     * 折算人民币金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repConvertCny;

    /**
     * 产品销售区域
     */
    private String repSaleArea;

    /**
     * 区域募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAreaRaiseSum;

    /**
     * 实际募集金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repActRaiseSum;

    /**
     * 募集总份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repShareSum;

    /**
     * 是否有其他机构代销
     */
    private String repIsOtherSale;

    /**
     * 代销总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherSaleSum;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 发行机构名称
     * @return 发行机构名称
     */
    public String getRepIssuePartyName() {
        return repIssuePartyName;
    }

    /**
     * 设置 发行机构名称
     * @param repIssuePartyName 发行机构名称
     */
    public void setRepIssuePartyName(String repIssuePartyName) {
        this.repIssuePartyName = repIssuePartyName == null ? null : repIssuePartyName.trim();
    }

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
     * 取得 行内标识码/理财产品代码
     * @return 行内标识码/理财产品代码
     */
    public String getRepBankProdCode() {
        return repBankProdCode;
    }

    /**
     * 设置 行内标识码/理财产品代码
     * @param repBankProdCode 行内标识码/理财产品代码
     */
    public void setRepBankProdCode(String repBankProdCode) {
        this.repBankProdCode = repBankProdCode == null ? null : repBankProdCode.trim();
    }

    /**
     * 取得 个人投资者总数
     * @return 个人投资者总数
     */
    public Integer getRepPerSum() {
        return repPerSum;
    }

    /**
     * 设置 个人投资者总数
     * @param repPerSum 个人投资者总数
     */
    public void setRepPerSum(Integer repPerSum) {
        this.repPerSum = repPerSum;
    }

    /**
     * 取得 法人投资者总数
     * @return 法人投资者总数
     */
    public Integer getRepLegalpersonSum() {
        return repLegalpersonSum;
    }

    /**
     * 设置 法人投资者总数
     * @param repLegalpersonSum 法人投资者总数
     */
    public void setRepLegalpersonSum(Integer repLegalpersonSum) {
        this.repLegalpersonSum = repLegalpersonSum;
    }

    /**
     * 取得 非法人投资者总数
     * @return 非法人投资者总数
     */
    public Integer getRepNLegalpersonSum() {
        return repNLegalpersonSum;
    }

    /**
     * 设置 非法人投资者总数
     * @param repNLegalpersonSum 非法人投资者总数
     */
    public void setRepNLegalpersonSum(Integer repNLegalpersonSum) {
        this.repNLegalpersonSum = repNLegalpersonSum;
    }

    /**
     * 取得 认购币种
     * @return 认购币种
     */
    public String getRepSubscriptionCcy() {
        return repSubscriptionCcy;
    }

    /**
     * 设置 认购币种
     * @param repSubscriptionCcy 认购币种
     */
    public void setRepSubscriptionCcy(String repSubscriptionCcy) {
        this.repSubscriptionCcy = repSubscriptionCcy == null ? null : repSubscriptionCcy.trim();
    }

    /**
     * 取得 认购金额
     * @return 认购金额
     */
    public BigDecimal getRepSubscriptionMon() {
        return repSubscriptionMon;
    }

    /**
     * 设置 认购金额
     * @param repSubscriptionMon 认购金额
     */
    public void setRepSubscriptionMon(BigDecimal repSubscriptionMon) {
        this.repSubscriptionMon = repSubscriptionMon;
    }

    /**
     * 取得 折算人民币金额
     * @return 折算人民币金额
     */
    public BigDecimal getRepConvertCny() {
        return repConvertCny;
    }

    /**
     * 设置 折算人民币金额
     * @param repConvertCny 折算人民币金额
     */
    public void setRepConvertCny(BigDecimal repConvertCny) {
        this.repConvertCny = repConvertCny;
    }

    /**
     * 取得 产品销售区域
     * @return 产品销售区域
     */
    public String getRepSaleArea() {
        return repSaleArea;
    }

    /**
     * 设置 产品销售区域
     * @param repSaleArea 产品销售区域
     */
    public void setRepSaleArea(String repSaleArea) {
        this.repSaleArea = repSaleArea == null ? null : repSaleArea.trim();
    }

    /**
     * 取得 区域募集金额
     * @return 区域募集金额
     */
    public BigDecimal getRepAreaRaiseSum() {
        return repAreaRaiseSum;
    }

    /**
     * 设置 区域募集金额
     * @param repAreaRaiseSum 区域募集金额
     */
    public void setRepAreaRaiseSum(BigDecimal repAreaRaiseSum) {
        this.repAreaRaiseSum = repAreaRaiseSum;
    }

    /**
     * 取得 实际募集金额（元）
     * @return 实际募集金额（元）
     */
    public BigDecimal getRepActRaiseSum() {
        return repActRaiseSum;
    }

    /**
     * 设置 实际募集金额（元）
     * @param repActRaiseSum 实际募集金额（元）
     */
    public void setRepActRaiseSum(BigDecimal repActRaiseSum) {
        this.repActRaiseSum = repActRaiseSum;
    }

    /**
     * 取得 募集总份额
     * @return 募集总份额
     */
    public BigDecimal getRepShareSum() {
        return repShareSum;
    }

    /**
     * 设置 募集总份额
     * @param repShareSum 募集总份额
     */
    public void setRepShareSum(BigDecimal repShareSum) {
        this.repShareSum = repShareSum;
    }

    /**
     * 取得 是否有其他机构代销
     * @return 是否有其他机构代销
     */
    public String getRepIsOtherSale() {
        return repIsOtherSale;
    }

    /**
     * 设置 是否有其他机构代销
     * @param repIsOtherSale 是否有其他机构代销
     */
    public void setRepIsOtherSale(String repIsOtherSale) {
        this.repIsOtherSale = repIsOtherSale == null ? null : repIsOtherSale.trim();
    }

    /**
     * 取得 代销总金额
     * @return 代销总金额
     */
    public BigDecimal getRepOtherSaleSum() {
        return repOtherSaleSum;
    }

    /**
     * 设置 代销总金额
     * @param repOtherSaleSum 代销总金额
     */
    public void setRepOtherSaleSum(BigDecimal repOtherSaleSum) {
        this.repOtherSaleSum = repOtherSaleSum;
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