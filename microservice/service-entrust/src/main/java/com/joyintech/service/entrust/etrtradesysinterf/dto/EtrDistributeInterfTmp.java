package com.joyintech.service.entrust.etrtradesysinterf.dto;

import com.joyintech.framework.base.BaseEntity;

public class EtrDistributeInterfTmp extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 委托代码
     */
    private String entrustId;

    /**
     * 指定交易员
     */
    private String trader;
    
    /**
     * 交易主管，交易主管分发指令时记录主管的用户代码
     */
    private String tradingHeader;

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 指定交易员
     * @return 指定交易员
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 指定交易员
     * @param trader 指定交易员
     */
    public void setTrader(String trader) {
        this.trader = trader == null ? null : trader.trim();
    }

	public String getTradingHeader() {
		return tradingHeader;
	}

	public void setTradingHeader(String tradingHeader) {
		this.tradingHeader = tradingHeader;
	}
    
    
}