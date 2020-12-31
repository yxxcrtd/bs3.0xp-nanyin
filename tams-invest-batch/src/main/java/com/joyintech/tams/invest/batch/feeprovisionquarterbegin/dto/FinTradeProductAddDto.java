package com.joyintech.tams.invest.batch.feeprovisionquarterbegin.dto;

import com.joyintech.tams.common.base.entity.FinTradeProductAdd;

/**
 * 交易类金融产品附属表 dto
 * @author hpp
 *
 */
public class FinTradeProductAddDto extends FinTradeProductAdd{
	//证券账户
	private String secAcctId;

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}
	
}
