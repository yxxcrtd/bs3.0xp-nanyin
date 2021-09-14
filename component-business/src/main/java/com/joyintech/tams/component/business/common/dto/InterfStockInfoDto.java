package com.joyintech.tams.component.business.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.InterfStockInfo;

/**
 * wind股票资产DTO
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2021年5月10日
 * @see InterfStockInfoDto
 * @since 1.0
 */
public class InterfStockInfoDto extends InterfStockInfo{
	
	/**
     * 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    private String finprodId;
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     *资产代码list
     */
    private List<String> stockCodeList;
    
    /**
     * 金融产品信息List
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList; 
    
    /**
     *存放中债校验不通过的股票信息
     */
    private List<String> errorStockList;
    

	public List<String> getStockCodeList() {
		return stockCodeList;
	}

	public void setStockCodeList(List<String> stockCodeList) {
		this.stockCodeList = stockCodeList;
	}

	public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
		return baseFinProductBaseInfoList;
	}

	public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
		this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
	}

	public List<String> getErrorStockList() {
		return errorStockList;
	}

	public void setErrorStockList(List<String> errorStockList) {
		this.errorStockList = errorStockList;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	
}
