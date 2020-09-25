/*
 * 文件名：MockCalcAssetValuationResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.LiabilitiesChanging;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockSingleAssetChanging;

/**
 * 
 * 产品估值方法返回值
 * 〈功能详细描述〉
 * @author admin
 * @version 1.0 2020年4月21日
 * @see MockCalcAssetValuationResult
 * @since 1.0
 */
public class MockCalcAssetValuationResult {
	
	/**
	 * 组合资产估值拓展
	 */
	private List<MockPtlSecValuationDto> mockPtlSecValuationDtoList;
	
	/**
	 * 组合费用头寸拓展
	 */
	private List<MockPtlFeePositionDto> mockPtlFeePositionDtoList;

    /**
     * T+N资产变化
     */
    private Map<MockSingleAssetChangingKey, Map<String,MockSingleAssetChanging>> assetChanging;

	/**
	 *  单资产变化
	 */
	private Map<String,MockSingleAssetChanging> singleAssetChanging;
    
    /**
     * T+N现金变化
     */
    private Map<MockCashChangingKey, BigDecimal> cashChanging;
    
    /**
     * T+N负债变化
     */
    private Map<String, LiabilitiesChanging> liabilitiesChanging;
    
    /**
     * 组合现金
     */
    private Map<String, BigDecimal> mockPtlCashPositionMap;
    
	/**
	 * 组合估值列表
	 */
	private List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList;

	/**
	 * 获取 mockPtlSecValuationDtoList
	 * @return mockPtlSecValuationDtoList.
	 */
	public List<MockPtlSecValuationDto> getMockPtlSecValuationDtoList() {
		return mockPtlSecValuationDtoList;
	}

	/**
	 * 设置 mockPtlSecValuationDtoList
	 * @param mockPtlSecValuationDtoList mockPtlSecValuationDtoList
	 */
	public void setMockPtlSecValuationDtoList(List<MockPtlSecValuationDto> mockPtlSecValuationDtoList) {
		this.mockPtlSecValuationDtoList = mockPtlSecValuationDtoList;
	}

	/**
	 * 获取 mockPtlFeePositionDtoList
	 * @return mockPtlFeePositionDtoList.
	 */
	public List<MockPtlFeePositionDto> getMockPtlFeePositionDtoList() {
		return mockPtlFeePositionDtoList;
	}

	/**
	 * 设置 mockPtlFeePositionDtoList
	 * @param mockPtlFeePositionDtoList mockPtlFeePositionDtoList
	 */
	public void setMockPtlFeePositionDtoList(List<MockPtlFeePositionDto> mockPtlFeePositionDtoList) {
		this.mockPtlFeePositionDtoList = mockPtlFeePositionDtoList;
	}

	 

	/**
	 * 获取 cashChanging
	 * @return cashChanging.
	 */
	public Map<MockCashChangingKey, BigDecimal> getCashChanging() {
		return cashChanging;
	}

	/**
	 * 设置 cashChanging
	 * @param cashChanging cashChanging
	 */
	public void setCashChanging(Map<MockCashChangingKey, BigDecimal> cashChanging) {
		this.cashChanging = cashChanging;
	}

	/**
	 * 获取 liabilitiesChanging
	 * @return liabilitiesChanging.
	 */
	public Map<String, LiabilitiesChanging> getLiabilitiesChanging() {
		return liabilitiesChanging;
	}

	/**
	 * 设置 liabilitiesChanging
	 * @param liabilitiesChanging liabilitiesChanging
	 */
	public void setLiabilitiesChanging(Map<String, LiabilitiesChanging> liabilitiesChanging) {
		this.liabilitiesChanging = liabilitiesChanging;
	}

	/**
	 * 获取 mockPtlCashPositionMap
	 * @return mockPtlCashPositionMap.
	 */
	public Map<String, BigDecimal> getMockPtlCashPositionMap() {
		return mockPtlCashPositionMap;
	}

	/**
	 * 设置 mockPtlCashPositionMap
	 * @param mockPtlCashPositionMap mockPtlCashPositionMap
	 */
	public void setMockPtlCashPositionMap(Map<String, BigDecimal> mockPtlCashPositionMap) {
		this.mockPtlCashPositionMap = mockPtlCashPositionMap;
	}

	/**
	 * 获取 mockPtlPortfolioValuationDtoList
	 * @return mockPtlPortfolioValuationDtoList.
	 */
	public List<MockPtlPortfolioValuationDto> getMockPtlPortfolioValuationDtoList() {
		return mockPtlPortfolioValuationDtoList;
	}

	/**
	 * 设置 mockPtlPortfolioValuationDtoList
	 * @param mockPtlPortfolioValuationDtoList mockPtlPortfolioValuationDtoList
	 */
	public void setMockPtlPortfolioValuationDtoList(List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList) {
		this.mockPtlPortfolioValuationDtoList = mockPtlPortfolioValuationDtoList;
	}

	/**
	 * 获取 T_N资产变化
	 * @return assetChanging.
	 */
    public Map<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> getAssetChanging() {
        return assetChanging;
    }
    
	/**
	 * 设置 T_N资产变化
	 * @param assetChanging T_N资产变化
	 */
    public void setAssetChanging(Map<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> assetChanging) {
        this.assetChanging = assetChanging;
    }

	/**
	 * 获取 单资产变化
	 * @return singleAssetChanging.
	 */
	public Map<String, MockSingleAssetChanging> getSingleAssetChanging() {
		return singleAssetChanging;
	}
	
	/**
	 * 设置 单资产变化
	 * @param singleAssetChanging 单资产变化
	 */
	public void setSingleAssetChanging(Map<String, MockSingleAssetChanging> singleAssetChanging) {
		this.singleAssetChanging = singleAssetChanging;
	}
}
