package com.joyintech.service.valuation.basedata.marketdata.fundmarketinfo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ValFundMarketInfo001dto
 * @since 1.0
 */
public class ValFundMarketInfo001dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金名称
     */
    private String fundName;

    /**
     * 基金类型
     */
    private String fundType;

    /**
     * 金融产品收益类型
     */
    private String finprodType;

    /**
     * 估值来源
     */
    private String valueSource;

    /**
     * 估值日期
     */
    private String valueDate;

    /**
     * 估值日期开始
     */
    private Date startDate;

    /**
     * 估值日期结束
     */
    private Date endDate;

    /**
     * 最高价
     */
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    private BigDecimal minPrice;

    /**
     * 收盘价
     */
    private BigDecimal closePrice;

    /**
     * 平均价
     */
    private BigDecimal averagePrice;

    /**
     * 买一价
     */
    private BigDecimal buyOnePrice;

    /**
     * 卖一价
     */
    private BigDecimal sellOnePrice;

    /**
     * 获取 fundCode
     * 
     * @return fundCode.
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 设置 fundCode
     * 
     * @param fundCode fundCode
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    /**
     * 获取 fundName
     * 
     * @return fundName.
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * 设置 fundName
     * 
     * @param fundName fundName
     */
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    /**
     * 获取 fundType
     * 
     * @return fundType.
     */
    public String getFundType() {
        return fundType;
    }

    
    /**
     * 设置 fundType
     * 
     * @param fundType fundType
     */
    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    /**
     * 获取 valueSource
     * 
     * @return valueSource.
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
     * 设置 valueSource
     * 
     * @param valueSource valueSource
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    /**
     * 获取 valueDate
     * 
     * @return valueDate.
     */
    public String getValueDate() {
        return valueDate;
    }

    /**
     * 设置 valueDate
     * 
     * @param valueDate valueDate
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 获取 maxPrice
     * 
     * @return maxPrice.
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置 maxPrice
     * 
     * @param maxPrice maxPrice
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 获取 minPrice
     * 
     * @return minPrice.
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * 设置 minPrice
     * 
     * @param minPrice minPrice
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 获取 closePrice
     * 
     * @return closePrice.
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    /**
     * 设置 closePrice
     * 
     * @param closePrice closePrice
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    /**
     * 获取 averagePrice
     * 
     * @return averagePrice.
     */
    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    /**
     * 设置 averagePrice
     * 
     * @param averagePrice averagePrice
     */
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    /**
     * 获取 buy_onePrice
     * 
     * @return buy_onePrice.
     */

    /**
     * 获取 sellOnePrice
     * 
     * @return sellOnePrice.
     */
    public BigDecimal getSellOnePrice() {
        return sellOnePrice;
    }

    /**
     * 设置 sellOnePrice
     * 
     * @param sellOnePrice sellOnePrice
     */
    public void setSellOnePrice(BigDecimal sellOnePrice) {
        this.sellOnePrice = sellOnePrice;
    }

    /**
     * 获取 buyOnePrice
     * 
     * @return buyOnePrice.
     */
    public BigDecimal getBuyOnePrice() {
        return buyOnePrice;
    }

    /**
     * 设置 buyOnePrice
     * 
     * @param buyOnePrice buyOnePrice
     */
    public void setBuyOnePrice(BigDecimal buyOnePrice) {
        this.buyOnePrice = buyOnePrice;
    }


    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    
    /**
     * 获取 startDate
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    
    /**
     * 设置 startDate
     * @param startDate startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = DateUtil.parse(startDate);
    }

    
    /**
     * 获取 endDate
     * @return endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    
    /**
     * 设置 endDate
     * @param endDate endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = DateUtil.parse(endDate);
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    

}
