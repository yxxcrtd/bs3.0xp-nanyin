package com.joyintech.tams.invest.common.risktrial.dto;

import com.joyintech.framework.base.BaseEntity;

public class CreditLinesDetail extends BaseEntity{
	
	/**
	 * 平安银行授信个性化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 类别
     */
    private String credittype;

    /**
     * 校验标准（或范围）
     */
    private String checkscope;
    
    /**
     * 存量值
     */
    private String stockvalue;

    /**
     * 预占用
     */
    private String prehapamt;
    
    /**
     * 本次交易
     */
    private String thistrans;
    
    /**
     * 校验结果
     */
    private String checkresult;
    //sjj add 20190409平安需求变更风险试算 start
    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 总结果
     */
    private String totalResult;
    
    public String getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(String totalResult) {
		this.totalResult = totalResult;
	} 
	/**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }
    //sjj add 20190409平安需求变更风险试算 end
	public String getCredittype() {
		return credittype;
	}

	public void setCredittype(String credittype) {
		this.credittype = credittype;
	}

	public String getCheckscope() {
		return checkscope;
	}

	public void setCheckscope(String checkscope) {
		this.checkscope = checkscope;
	}

	public String getStockvalue() {
		return stockvalue;
	}

	public void setStockvalue(String stockvalue) {
		this.stockvalue = stockvalue;
	}

	public String getPrehapamt() {
		return prehapamt;
	}

	public void setPrehapamt(String prehapamt) {
		this.prehapamt = prehapamt;
	}

	public String getThistrans() {
		return thistrans;
	}

	public void setThistrans(String thistrans) {
		this.thistrans = thistrans;
	}

	public String getCheckresult() {
		return checkresult;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
	}

}
