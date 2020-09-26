package com.joyintech.tams.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 逾期罚息交易实体类
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2019年12月20日
 * @see OverduePenaltyDto
 * @since 1.0
 */
public class OverduePenaltyDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 核算主体代码
     */
    private String prodId;

    
    /**
     * 核算主体名称
     */
    private String prodAbbr;


    /**
     * 业务类型Code
     */
    private String busiTypeCode;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产简称
     */
    private String assetAbbr;

    /**
     * 交易方向Code
     */
    private String psCode;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易场所Code
     */
    private String tradeMarketCode;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 投资目的Code
     */
    private String invAimCode;

    /**
     * 发生日 - 取交易日期
     */
    private String tradeDate;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 交易币种Code
     */
    private String ccyCode;
    
    /**
     * 交易本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    
    /**
     * 交易利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 交易金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 交易状态Code
     */
    private String tradeStatusCode;

    /**
     * 交易状态
     */
    private String tradeStatus;
    
    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    
    public String getProdAbbr() {
        return prodAbbr;
    }

    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr;
    }
    
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
    
    public String getBusiType() {
        return this.busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getBusiTypeCode() {
        return busiTypeCode;
    }

    public void setBusiTypeCode(String busiTypeCode) {
        this.busiTypeCode = busiTypeCode;
    }
    
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetAbbr() {
        return assetAbbr;
    }

    public void setAssetAbbr(String assetAbbr) {
        this.assetAbbr = assetAbbr;
    }
    
    public String getPs() {
        return this.ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPsCode() {
        return psCode;
    }

    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }
    
    public String getTradeMarket() {
        return this.tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradeMarketCode() {
        return tradeMarketCode;
    }

    public void setTradeMarketCode(String tradeMarketCode) {
        this.tradeMarketCode = tradeMarketCode;
    }
    
    public String getInvAim() {
        return this.invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getInvAimCode() {
        return invAimCode;
    }

    public void setInvAimCode(String invAimCode) {
        this.invAimCode = invAimCode;
    }
    
    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    
    public String getCcy() {
        return this.ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }
    
    public String getTradeStatus() {
        return this.tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeStatusCode() {
        return tradeStatusCode;
    }

    public void setTradeStatusCode(String tradeStatusCode) {
        this.tradeStatusCode = tradeStatusCode;
    }
    
    public BigDecimal getPrinAmt() {
        return null!=prinAmt?prinAmt:BigDecimal.ZERO;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }
    
    public BigDecimal getIntAmt() {
        return null!=intAmt?intAmt:BigDecimal.ZERO;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }
    
    public BigDecimal getTradeAmt() {
        return null!=tradeAmt?tradeAmt:BigDecimal.ZERO;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }
}
