package com.joyintech.tams.invest.common.dto.product.addreduce;

import java.math.BigDecimal;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.product.productduration.addreduce.dto
 * @Description: 强增强减管理检索的grid数据模型
 * @date Date : 2018年09月07日 9:19
 */
public class FinProductAddReduceDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7513622217138603374L;

    /**
     * 业务编号
     */
    private String busiCode;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 产品代码
     */
    private String finProdId;

    /**
     * 产品简称
     */
    private String finprodAbbr;

    /**
     * 产品代码
     */
    private String finprodMarketId;

    /**
     * 销售部门
     */
    private String saleChannel;

    /**
     * 交易类型
     */
    private String ps;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 分行
     */
    private String entrusterName;

    /**
     * 确认日
     */
    private String settleDate;

    /**
     * 金额
     */
    private BigDecimal tradeAmt;

    /**
     * 份额
     */
    private BigDecimal shareAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 有效状态
     */
    private String effectStatus;
    
    /**
     * 获取 busiCode
     *
     * @return busiCode
     */
    public String getBusiCode() {
        return busiCode;
    }

    /**
     * 设置 busiCode
     *
     * @param busiCode busiCode
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 finProdId
     *
     * @return finProdId
     */
    public String getFinProdId() {
        return finProdId;
    }

    /**
     * 设置 finProdId
     *
     * @param finProdId finProdId
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    /**
     * 获取 finprodAbbr
     *
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     *
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 saleChannel
     *
     * @return saleChannel
     */
    public String getSaleChannel() {
        return saleChannel;
    }

    /**
     * 设置 saleChannel
     *
     * @param saleChannel saleChannel
     */
    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    /**
     * 获取 ps
     *
     * @return ps
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 settleDate
     *
     * @return settleDate
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     *
     * @param settleDate settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 tradeAmt
     *
     * @return tradeAmt
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     *
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 shareAmt
     *
     * @return shareAmt
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 shareAmt
     *
     * @param shareAmt shareAmt
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 profitType
     *
     * @return profitType
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 entrusterName
     *
     * @return entrusterName
     */
    public String getEntrusterName() {
        return entrusterName;
    }

    /**
     * 设置 entrusterName
     *
     * @param entrusterName entrusterName
     */
    public void setEntrusterName(String entrusterName) {
        this.entrusterName = entrusterName;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        if (finprodMarketId == null || "".equals(finprodMarketId)) {
            return "";
        }
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getEffectStatus() {
    	return effectStatus;
	}

	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}
	
}
