package com.joyintech.tams.valuation.batch.outrightrepoprecalacctreat.dto;

import com.joyintech.tams.common.base.entity.FinDealPledge;

public class FinDealPledgeOutRightPro extends FinDealPledge {

	private String tradeMarket;

	private String pledgeFinprodType;
	
	private String finprodType;

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getPledgeFinprodType() {
		return pledgeFinprodType;
	}

	public void setPledgeFinprodType(String pledgeFinprodType) {
		this.pledgeFinprodType = pledgeFinprodType;
	}

    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

}
