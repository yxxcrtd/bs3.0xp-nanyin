package com.jixu.msg.dto;

import java.util.Date;

import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;


public class DelTranByOptionDto extends BaseMsgDto{

	private static final long serialVersionUID = 1L;
	
	@NotEmptyJixu(keymsg="资产类别")
	private String assettype;

	@NotEmptyJixu(keymsg="交易日期")
	private Date tradedate;

	public String getAssettype() {
		return assettype;
	}

	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}

	public Date getTradedate() {
		return tradedate;
	}

	public void setTradedate(Date tradedate) {
		this.tradedate = tradedate;
	}
	
}
