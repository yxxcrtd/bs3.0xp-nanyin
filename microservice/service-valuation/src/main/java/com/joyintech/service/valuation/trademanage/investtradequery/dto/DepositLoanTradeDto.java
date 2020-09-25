package com.joyintech.service.valuation.trademanage.investtradequery.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 存借款交易查询实体类
 * 
 * @author zhangzhao
 * @version 1.0 2018年7月28日
 * @see DepositLoanTradeDto
 * @since 1.0
 */
@SuppressWarnings("unused")
public class DepositLoanTradeDto extends InvestTradeDto{

	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**存款类型*/
	private String depositType;
	
	/**交割日*/
	private String settleDate;
    
    /**交易对手*/  
    private String counterId;
	
	/**重写交易方向*/
	private String ps;
	private String psCode;
	
	/**通道*/
	private String pass;
	
	/**币种名称*/
    private String ccyName;


	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPs() {
		return this.ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}
    
    /**
     * 获取 psCode
     * @return psCode.
     */
    public String getPsCode() {
        return psCode;
    }

    
    /**
     * 设置 psCode
     * @param psCode psCode
     */
    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }

    /**
     * 获取 counterId
     * @return counterId.
     */
    public String getCounterId() {
        return counterId;
    }

    
    /**
     * 设置 counterId
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    
    /**
     * 获取 ccyName
     * @return ccyName.
     */
    public String getCcyName() {
        return this.ccyName;
    }

    
    /**
     * 设置 ccyName
     * @param ccyName ccyName
     */
    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }
	
	
	
}
