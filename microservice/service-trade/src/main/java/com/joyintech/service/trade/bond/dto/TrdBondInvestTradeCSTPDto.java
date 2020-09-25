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

}
