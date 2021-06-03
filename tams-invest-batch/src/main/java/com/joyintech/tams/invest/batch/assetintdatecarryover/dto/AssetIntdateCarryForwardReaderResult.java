package com.joyintech.tams.invest.batch.assetintdatecarryover.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

public class AssetIntdateCarryForwardReaderResult extends NeedOperateEntity{
	
	/**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 金融产品类型
     */
    private String finprodType;
    
    /**
     * 待处理现金流计划
     */
    private List<AssetIntdateCarryForwardDto> overdueFinTradeProductForwardDtoList = new ArrayList<AssetIntdateCarryForwardDto>();


	public String getFinprodId() {
		return finprodId;
	}


	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}


	public String getFinprodType() {
		return finprodType;
	}


	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}


	public List<AssetIntdateCarryForwardDto> getOverdueFinTradeProductForwardDtoList() {
		return overdueFinTradeProductForwardDtoList;
	}


	public void setOverdueFinTradeProductForwardDtoList(
			List<AssetIntdateCarryForwardDto> overdueFinTradeProductForwardDtoList) {
		this.overdueFinTradeProductForwardDtoList = overdueFinTradeProductForwardDtoList;
	}
    
}
