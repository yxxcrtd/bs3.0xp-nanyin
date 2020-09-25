package com.joyintech.tams.invest.trade.bond.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.InterfTradSecdeal;


public class TrdBondInvestTradeCSTPDto extends InterfTradSecdeal {
    
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 
     */
    private static final long serialVersionUID = 4604880420262612215L;
    
    /**
     * @return 交易日 yyyyMMdd
     */
    public String getDealdateStr(){
        return DateUtil.format(this.getDealdate());
    }
    
    /**
     * @return 交割日yyyyMMdd
     */
    public String getSettdateStr(){
        return DateUtil.format(this.getSettdate());
    }

    
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
}
