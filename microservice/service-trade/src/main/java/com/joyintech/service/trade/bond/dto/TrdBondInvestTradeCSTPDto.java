package com.joyintech.service.trade.bond.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.InterfTradSecdeal;

import java.util.Date;


public class TrdBondInvestTradeCSTPDto extends InterfTradSecdeal {
    
    /**
     * 组合代码
     */
    private String portfolioId;

    private String dealDateStr;

    private String settDateStr;

    private String entrustId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 投资目的
     */
    private String invAim;
    
    /**
     * 是否有报价信息（01：是，02：否）
     */
    private String isOfferFlag;


    /**
     * 
     */
    private static final long serialVersionUID = 4604880420262612215L;

    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public void setDealDateStr(){
     this.dealDateStr =  DateUtil.format(super.getDealdate());
    }
    public void setSettDateStr(){
      this.settDateStr =   DateUtil.format(super.getSettdate());
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
