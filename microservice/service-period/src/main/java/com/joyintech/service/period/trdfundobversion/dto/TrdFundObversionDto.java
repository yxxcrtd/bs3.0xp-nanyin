/*
 * 文件名：TrdFundDurationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdfundobversion.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.trdfundduration.TrdFundDurationCommonDto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangxuelei
 * @version 1.0 2019年11月10
 * @see TrdFundObversionDto
 * @since 1.0
 */
public class TrdFundObversionDto extends TrdFundDurationCommonDto implements ITableRow{
    /**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -3453994650137045893L;
	/**
     * 发行人
     */
    private String issuer;
    /**
     * 业务类型
     */
    private String repIssuePartyId;
    /**
     * 业务类型
     */
    private String type;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 控件Id
     */
    private String elementId;

    /**
     * 有效状态
     */
    private String statusValid;
    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 基金类型
     */
    private String assetType;
    /**
     * 资产名称
     */
    private String finprodMarketName;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 分红类型
     */
    private String bonusMode;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 份额折算日
     */
    private Date conversionTime;

    private BigDecimal totalAmount;
    /**
     * 权益登记日
     */
    private Date registDate;
    /**
     * 除权日
     */
    private Date exRightsDate;
    /**
     * 分红到账日
     */
    private Date arriveDate;
    /**
     * 红利再投日
     */
    private Date reInvesDate;
    /**
     * 权益登记日份额
     */
    private BigDecimal registShare;
    /**
     * 单位分红净值
     */
    private BigDecimal unitCprice;
    /**
     * 分红金额
     */
    private BigDecimal tradeAmt;
    /**
     * 分红份额
     */
    private BigDecimal qty;

    // 通道交易 新增 强成西 2019/03/26 start
    /**
     * 通道
     */
    private String chlId;

    // 多级法人改造 add by caiqun 20200615
    /**
     * 机构名称
     */
    private String institutionName;
    // 多级法人改造 add by caiqun 20200615
    /**
     * 折算前单位净值
     */
    @Amount(maxDecBit=2,fieldName = "TrdFundDuration001.bconUnitCprice",groups= {ValidateGroupEdit.class})
    private BigDecimal bconUnitCprice;
    /**
     * 折算后单位净值
     */
    @Amount(maxDecBit=2,fieldName = "TrdFundDuration001.afterUnitCprice",groups= {ValidateGroupEdit.class})
    private BigDecimal afterUnitCprice;

    /**
     * 份额折算类型
     */
    @NotEmpty(fieldName = "TrdFundObversion001.conversionType",groups= {ValidateGroupEdit.class})
    private String conversionType;

    //折算比例
    private BigDecimal conversionRat;


    public String getConversionType() {
        return conversionType;
    }

    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    public BigDecimal getConversionRat() {
        return conversionRat;
    }

    public void setConversionRat(BigDecimal conversionRat) {
        this.conversionRat = conversionRat;
    }

    /**
     * 获取 chlId
     *
     * @return chlId
     */
    @Override
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     *
     * @param chlId chlId
     */
    @Override
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }
//    通道交易 新增 强成西 2019/03/26 end

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getFinprodMarketName() {
        return finprodMarketName;
    }

    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getBonusMode() {
        return bonusMode;
    }

    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getExRightsDate() {
        return exRightsDate;
    }

    public void setExRightsDate(Date exRightsDate) {
        this.exRightsDate = exRightsDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Date getReInvesDate() {
        return reInvesDate;
    }

    public void setReInvesDate(Date reInvesDate) {
        this.reInvesDate = reInvesDate;
    }

    public BigDecimal getRegistShare() {
        return registShare;
    }

    public void setRegistShare(BigDecimal registShare) {
        this.registShare = registShare;
    }

    @Override
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    @Override
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    @Override
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    @Override
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId;
    }

    public String getIssuer() {
        return issuer;
    }
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Date getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBconUnitCprice() {
        return bconUnitCprice;
    }

    public void setBconUnitCprice(BigDecimal bconUnitCprice) {
        this.bconUnitCprice = bconUnitCprice;
    }

    public BigDecimal getAfterUnitCprice() {
        return afterUnitCprice;
    }

    public void setAfterUnitCprice(BigDecimal afterUnitCprice) {
        this.afterUnitCprice = afterUnitCprice;
    }
    // 多级法人改造 add by caiqun 20200615
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
    // 多级法人改造 add by caiqun 20200615
}
