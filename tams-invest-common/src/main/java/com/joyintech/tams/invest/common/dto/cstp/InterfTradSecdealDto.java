package com.joyintech.tams.invest.common.dto.cstp;

import com.joyintech.tams.common.base.entity.InterfTradSecdeal;

/**
 * @author zhangnian
 * @ClassName InterfTradSecdealDto
 * @description TODO
 * @date 2021/1/26 0026 11:31
 **/
public class InterfTradSecdealDto extends InterfTradSecdeal {

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 清算方式
    */
    private String clearingmethod;

    /**
     * 结算方式
     */
    private String deliverytype;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    private String entrustId;

    /**
     * 投资目的
     */
    private String invAim;
    
    /**
     * 是否有报价信息（01：是，02：否）
     */
    private String isOfferFlag;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getClearingmethod() {
        return clearingmethod;
    }

    public void setClearingmethod(String clearingmethod) {
        this.clearingmethod = clearingmethod;
    }

    public String getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

	public String getIsOfferFlag() {
		return isOfferFlag;
	}

	public void setIsOfferFlag(String isOfferFlag) {
		this.isOfferFlag = isOfferFlag;
	}
    
    
}

