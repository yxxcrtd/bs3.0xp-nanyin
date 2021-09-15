package com.joyintech.service.common.core.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.InterfStockInfo;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;

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
	
    

}
