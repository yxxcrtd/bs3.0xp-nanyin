/*
 * 文件名：MockQuotationInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.basicdata;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 行情DTO
 * 
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see MockQuotationInfoDto
 * @since 1.0
 */
public class MockQuotationInfoDto {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 市价
     */
    private BigDecimal marketPrice;

    /**
     * 全价
     */
    private BigDecimal dirtyPrice;

    /**
     * 利率久期
     */
    private BigDecimal interestDuration;

    /**
     * 麦考利久期
     */
    private BigDecimal mduration;

    /**
     * 利差久期
     */
    private BigDecimal sduration;
    
    /**
     * 估价久期
     */
    private BigDecimal marketPriceDuration;

    /**
     * 日期
     */
    private Date quotationDate;

    /**
     * 万份收益
     */
    private BigDecimal pyield;

    /**
     * 七天年化收益率
     */
    private BigDecimal sevenDayYearYield;
    
    /**
     * 估值来源
     */
    private String valueSource;
    
    

    public String getValueSource() {
		return valueSource;
	}

	public void setValueSource(String valueSource) {
		this.valueSource = valueSource;
	}

	/**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 marketPrice
     * 
     * @return marketPrice.
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置 marketPrice
     * 
     * @param marketPrice marketPrice
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取 dirtyPrice
     * 
     * @return dirtyPrice.
     */
    public BigDecimal getDirtyPrice() {
        return dirtyPrice;
    }

    /**
     * 设置 dirtyPrice
     * 
     * @param dirtyPrice dirtyPrice
     */
    public void setDirtyPrice(BigDecimal dirtyPrice) {
        this.dirtyPrice = dirtyPrice;
    }

    /**
     * 获取 interestDuration
     * 
     * @return interestDuration.
     */
    public BigDecimal getInterestDuration() {
        return interestDuration;
    }

    /**
     * 设置 interestDuration
     * 
     * @param interestDuration interestDuration
     */
    public void setInterestDuration(BigDecimal interestDuration) {
        this.interestDuration = interestDuration;
    }

    /**
     * 获取 mduration
     * 
     * @return mduration.
     */
    public BigDecimal getMduration() {
        return mduration;
    }

    /**
     * 设置 mduration
     * 
     * @param mduration mduration
     */
    public void setMduration(BigDecimal mduration) {
        this.mduration = mduration;
    }

    /**
     * 获取 sduration
     * 
     * @return sduration.
     */
    public BigDecimal getSduration() {
        return sduration;
    }

    /**
     * 设置 sduration
     * 
     * @param sduration sduration
     */
    public void setSduration(BigDecimal sduration) {
        this.sduration = sduration;
    }

    /**
     * 获取 quotationDate
     * 
     * @return quotationDate.
     */
    public Date getQuotationDate() {
        return quotationDate;
    }

    /**
     * 设置 quotationDate
     * 
     * @param quotationDate quotationDate
     */
    public void setQuotationDate(Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    /**
     * 获取 pyield
     * 
     * @return pyield.
     */
    public BigDecimal getPyield() {
        return pyield;
    }

    /**
     * 设置 pyield
     * 
     * @param pyield pyield
     */
    public void setPyield(BigDecimal pyield) {
        this.pyield = pyield;
    }

    /**
     * 获取 sevenDayYearYield
     * 
     * @return sevenDayYearYield.
     */
    public BigDecimal getSevenDayYearYield() {
        return sevenDayYearYield;
    }

    /**
     * 设置 sevenDayYearYield
     * 
     * @param sevenDayYearYield sevenDayYearYield
     */
    public void setSevenDayYearYield(BigDecimal sevenDayYearYield) {
        this.sevenDayYearYield = sevenDayYearYield;
    }

    /**
     * 获取 marketPriceDuration
     * @return marketPriceDuration.
     */
    public BigDecimal getMarketPriceDuration() {
        return marketPriceDuration;
    }

    /**
     * 设置 marketPriceDuration
     * @param marketPriceDuration marketPriceDuration
     */
    public void setMarketPriceDuration(BigDecimal marketPriceDuration) {
        this.marketPriceDuration = marketPriceDuration;
    }
    
}
