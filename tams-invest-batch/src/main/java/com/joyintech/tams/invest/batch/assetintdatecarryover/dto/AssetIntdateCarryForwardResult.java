package com.joyintech.tams.invest.batch.assetintdatecarryover.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.FinTradeProduct;

public class AssetIntdateCarryForwardResult extends NeedToWriteEntity{

	/**
	 * 交易类金融产品List
	 */
	private List<FinTradeProduct> finTradeProductList = new ArrayList<FinTradeProduct>();

	public List<FinTradeProduct> getFinTradeProductList() {
		return finTradeProductList;
	}

	public void setFinTradeProductList(List<FinTradeProduct> finTradeProductList) {
		this.finTradeProductList = finTradeProductList;
	}
	
	
	
}
