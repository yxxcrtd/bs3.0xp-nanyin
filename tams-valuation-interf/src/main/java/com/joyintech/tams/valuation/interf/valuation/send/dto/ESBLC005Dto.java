package com.joyintech.tams.valuation.interf.valuation.send.dto;

import com.joyintech.framework.base.BaseEntity;

public class ESBLC005Dto extends BaseEntity {
	 
	 /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
	 
    /**
     * 持有产品天数区间
     */
    private String holdDaysInterval;
    /**
     * 购买金额区间
     */
    private String buyAmtInterval;
    /**
     * 客户等级区间
     */
    private String clientLevelInterval;
    /**
     * 分层收益率
     */
    private String bedYield;
    /**
     * 年计息天数
     */
    private String yearInterestDays;
    /**
     * 产品代码
     */
    private String productCode;
    /**
     * 产品成立日期
     */
    private String establishDate;
    /**
     * 到期日
     */
    private String expiryDate;
    /**
     * 销售费率
     */
    private String saleFeeRate;

    /**
     *  取得 持有产品天数区间
     *  @return 持有产品天数区间
     */
    public String getHoldDaysInterval() {
       return holdDaysInterval;
    }

    /**
     *  设置 持有产品天数区间
     *  @param holdDaysInterval 持有产品天数区间
     */
    public void setHoldDaysInterval( String holdDaysInterval)  {
       this.holdDaysInterval = holdDaysInterval;
    }

    /**
     *  取得 购买金额区间
     *  @return 购买金额区间
     */
    public String getBuyAmtInterval() {
       return buyAmtInterval;
    }

    /**
     *  设置 购买金额区间
     *  @param buyAmtInterval 购买金额区间
     */
    public void setBuyAmtInterval( String buyAmtInterval)  {
       this.buyAmtInterval = buyAmtInterval;
    }

    /**
     *  取得 客户等级区间
     *  @return 客户等级区间
     */
    public String getClientLevelInterval() {
       return clientLevelInterval;
    }

    /**
     *  设置 客户等级区间
     *  @param clientLevelInterval 客户等级区间
     */
    public void setClientLevelInterval( String clientLevelInterval)  {
       this.clientLevelInterval = clientLevelInterval;
    }

    /**
     *  取得 分层收益率
     *  @return 分层收益率
     */
    public String getBedYield() {
       return bedYield;
    }

    /**
     *  设置 分层收益率
     *  @param bedYield 分层收益率
     */
    public void setBedYield( String bedYield)  {
       this.bedYield = bedYield;
    }

    /**
     *  取得 年计息天数
     *  @return 年计息天数
     */
    public String getYearInterestDays() {
       return yearInterestDays;
    }

    /**
     *  设置 年计息天数
     *  @param yearInterestDays 年计息天数
     */
    public void setYearInterestDays( String yearInterestDays)  {
       this.yearInterestDays = yearInterestDays;
    }

    /**
     *  取得 产品代码
     *  @return 产品代码
     */
    public String getProductCode() {
       return productCode;
    }

    /**
     *  设置 产品代码
     *  @param productCode 产品代码
     */
    public void setProductCode( String productCode)  {
       this.productCode = productCode;
    }

    /**
     *  取得 产品成立日期
     *  @return 产品成立日期
     */
    public String getEstablishDate() {
       return establishDate;
    }

    /**
     *  设置 产品成立日期
     *  @param establishDate 产品成立日期
     */
    public void setEstablishDate( String establishDate)  {
       this.establishDate = establishDate;
    }

    /**
     *  取得 到期日
     *  @return 到期日
     */
    public String getExpiryDate() {
       return expiryDate;
    }

    /**
     *  设置 到期日
     *  @param expiryDate 到期日
     */
    public void setExpiryDate( String expiryDate)  {
       this.expiryDate = expiryDate;
    }

    /**
     *  取得 销售费率
     *  @return 销售费率
     */
    public String getSaleFeeRate() {
       return saleFeeRate;
    }

    /**
     *  设置 销售费率
     *  @param saleFeeRate 销售费率
     */
    public void setSaleFeeRate( String saleFeeRate)  {
       this.saleFeeRate = saleFeeRate;
    }
	 
}
