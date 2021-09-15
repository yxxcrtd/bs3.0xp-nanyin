package com.jixu.msg.dto;

import com.jixu.common.valid.IsDateYYYYMMDDJixu;
import com.jixu.common.valid.IsIntegerJixu;
import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;

public class TranBaseMsgDto extends BaseMsgDto{

	private static final long serialVersionUID = -3279077694915081527L;

	/**
	 * 投资组合代码
	 */
	@PrimaryKey(value=Field.PK_2)
	@NotEmptyJixu(keymsg="产品代码")
	private String port;
	
	/**
	 * 证券代码
	 */
	@PrimaryKey(value=Field.PK_3)
	@NotEmptyJixu(keymsg="证券代码")
	private String secid;
	
	/**
	 * 交易类型
	 */
	@IsIntegerJixu(keymsg="交易类型")
	private String tradeType;
	
	/**
	 * 交易编号tanum
	 */
	private String id;
	
	/**
	 * 交易日
	 */
	@IsDateYYYYMMDDJixu(keymsg="交易日")
	private String tradeDate;
	
	/**
	 * 结算日
	 */
	@IsDateYYYYMMDDJixu(keymsg="结算日")
	private String settleDate;
	
	/**
	 * 资产类型
	 */
	private String assetType;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSecid() {
		return secid;
	}

	/**
	 * @param secid 传入一个参数，需要secid,不是cusip主键
	 */
	public void setSecid(String secid) {
		String[] secids=secid.split("\\|");
		if(secids.length==3){
			this.secid=secids[1];
		}else{
			this.secid=secids[0];
		}
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
}
