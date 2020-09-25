/*
 * 文件名：MockCalcAssetValuationParameter.java
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

import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.LiabilitiesChanging;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.trade.MockTrdProductDealDto;

/**
 * 
 * 模拟测算估值参数dto
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2020年4月21日
 * @see MockCalcAssetValuationParameter
 * @since 1.0
 */
public class MockCalcAssetValuationParameter {

	/**
	 * 资产基本信息列表         数据结构：List<资产基本信息DTO> 参考【数据结构定义】定义
	 */
	private List<MockAssetInfoDto> mockAssetInfoDtoList;
	/**
	 *  组合基本信息DTO         数据结构：参考【数据结构定义】定义
	 */
    private MockPtlPortfolioDto mockPtlPortfolioDto;
    /**
     * 组合资产估值列表         数据结构：List<组合资产估值扩展Entity>
     */
    private List<MockPtlSecValuationDto> mockPtlSecValuationDtoList;
    /**
     * 组合估值列表         数据结构：List<组合估值DTO> 参考【数据结构定义】定义
     */
    private List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList;
    /**
     * 组合费用头寸扩展列表         数据结构：List<组合费用头寸Entity>
     */
    private List<MockPtlFeePositionDto> mockPtlFeePositionDtoList;
    /**
     * 资产端交易列表         数据结构：List<金融产品交易扩展>
     */
    private List<MockTrdProductDealDto> assetTradeList;
    /**
     * 负债端交易列表         数据结构：List<金融产品交易扩展>
     */
    private List<MockTrdProductDealDto> liabilitiesTradeList;
    /**
     * 费用交易列表         数据结构：List<金融产品交易扩展>
     */
    private List<MockTrdProductDealDto> feeTradeList;
    /**
     * 资产端T+N交易列表         数据结构：List<金融产品交易扩展>             说明：查询对象交易日<平台日 且 交割日>= 平台日
     */
    private List<MockTrdProductDealDto> assetTdayPlusNdayTradeList;
    /**
     * 负债端T+N交易列表         数据结构：List<金融产品交易扩展>             说明：查询对象交易日<平台日 且 交割日>= 平台日
     */
    private List<MockTrdProductDealDto> liabilitiesTdayPlusNdayTradeList;
    /**
     * 组合现金DTO         数据结构：参考【数据结构定义】定义             说明：不含本次传入金融产品交易 对现金的影响
     */
    private Map<String, BigDecimal> mockPtlCashPositionMap;
    /**
     * 测算开始日
     */
    private String mockStartDate;
    /**
     * 测算结束日
     */
    private String mockEndDate;
    /**
     * 基准日期
     */
    private String baseDate;
    /**
     * 汇率行情列表
     */
    private List<MockMstExchargeQuoteDto> mstExchargeQuoteList;
    /**
     * 融资成本
     */
    private BigDecimal financingCost;
    /**
     * 再投收益率
     */
    private BigDecimal reinvestmentRate;
    /**
     * 综合费率
     */
    private BigDecimal compositeRate;
    /**
     * T+N资产变化
     */
    private Map<MockSingleAssetChangingKey, Map<String,MockSingleAssetChanging>> singleAssetChanging;
    /**
     * T+N现金变化
     */
    private Map<MockCashChangingKey, BigDecimal> cashChanging;
    /**
     * T+N负债变化
     */
    private Map<String, LiabilitiesChanging> liabilitiesChanging;
	/**
	 * 获取 mockAssetInfoDtoList
	 * @return mockAssetInfoDtoList.
	 */
	public List<MockAssetInfoDto> getMockAssetInfoDtoList() {
		return mockAssetInfoDtoList;
	}
	/**
	 * 设置 mockAssetInfoDtoList
	 * @param mockAssetInfoDtoList mockAssetInfoDtoList
	 */
	public void setMockAssetInfoDtoList(List<MockAssetInfoDto> mockAssetInfoDtoList) {
		this.mockAssetInfoDtoList = mockAssetInfoDtoList;
	}
	/**
	 * 获取 mockPtlPortfolioDto
	 * @return mockPtlPortfolioDto.
	 */
	public MockPtlPortfolioDto getMockPtlPortfolioDto() {
		return mockPtlPortfolioDto;
	}
	/**
	 * 设置 mockPtlPortfolioDto
	 * @param mockPtlPortfolioDto mockPtlPortfolioDto
	 */
	public void setMockPtlPortfolioDto(MockPtlPortfolioDto mockPtlPortfolioDto) {
		this.mockPtlPortfolioDto = mockPtlPortfolioDto;
	}
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
	 * 获取 assetTradeList
	 * @return assetTradeList.
	 */
	public List<MockTrdProductDealDto> getAssetTradeList() {
		return assetTradeList;
	}
	/**
	 * 设置 assetTradeList
	 * @param assetTradeList assetTradeList
	 */
	public void setAssetTradeList(List<MockTrdProductDealDto> assetTradeList) {
		this.assetTradeList = assetTradeList;
	}
	/**
	 * 获取 liabilitiesTradeList
	 * @return liabilitiesTradeList.
	 */
	public List<MockTrdProductDealDto> getLiabilitiesTradeList() {
		return liabilitiesTradeList;
	}
	/**
	 * 设置 liabilitiesTradeList
	 * @param liabilitiesTradeList liabilitiesTradeList
	 */
	public void setLiabilitiesTradeList(List<MockTrdProductDealDto> liabilitiesTradeList) {
		this.liabilitiesTradeList = liabilitiesTradeList;
	}
	/**
	 * 获取 feeTradeList
	 * @return feeTradeList.
	 */
	public List<MockTrdProductDealDto> getFeeTradeList() {
		return feeTradeList;
	}
	/**
	 * 设置 feeTradeList
	 * @param feeTradeList feeTradeList
	 */
	public void setFeeTradeList(List<MockTrdProductDealDto> feeTradeList) {
		this.feeTradeList = feeTradeList;
	}
	/**
	 * 获取 assetTdayPlusNdayTradeList
	 * @return assetTdayPlusNdayTradeList.
	 */
	public List<MockTrdProductDealDto> getAssetTdayPlusNdayTradeList() {
		return assetTdayPlusNdayTradeList;
	}
	/**
	 * 设置 assetTdayPlusNdayTradeList
	 * @param assetTdayPlusNdayTradeList assetTdayPlusNdayTradeList
	 */
	public void setAssetTdayPlusNdayTradeList(List<MockTrdProductDealDto> assetTdayPlusNdayTradeList) {
		this.assetTdayPlusNdayTradeList = assetTdayPlusNdayTradeList;
	}
	/**
	 * 获取 liabilitiesTdayPlusNdayTradeList
	 * @return liabilitiesTdayPlusNdayTradeList.
	 */
	public List<MockTrdProductDealDto> getLiabilitiesTdayPlusNdayTradeList() {
		return liabilitiesTdayPlusNdayTradeList;
	}
	/**
	 * 设置 liabilitiesTdayPlusNdayTradeList
	 * @param liabilitiesTdayPlusNdayTradeList liabilitiesTdayPlusNdayTradeList
	 */
	public void setLiabilitiesTdayPlusNdayTradeList(List<MockTrdProductDealDto> liabilitiesTdayPlusNdayTradeList) {
		this.liabilitiesTdayPlusNdayTradeList = liabilitiesTdayPlusNdayTradeList;
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
	 * 获取 mockStartDate
	 * @return mockStartDate.
	 */
	public String getMockStartDate() {
		return mockStartDate;
	}
	/**
	 * 设置 mockStartDate
	 * @param mockStartDate mockStartDate
	 */
	public void setMockStartDate(String mockStartDate) {
		this.mockStartDate = mockStartDate;
	}
	/**
	 * 获取 mockEndDate
	 * @return mockEndDate.
	 */
	public String getMockEndDate() {
		return mockEndDate;
	}
	/**
	 * 设置 mockEndDate
	 * @param mockEndDate mockEndDate
	 */
	public void setMockEndDate(String mockEndDate) {
		this.mockEndDate = mockEndDate;
	}
	/**
	 * 获取 baseDate
	 * @return baseDate.
	 */
	public String getBaseDate() {
		return baseDate;
	}
	/**
	 * 设置 baseDate
	 * @param baseDate baseDate
	 */
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	/**
	 * 获取 mstExchargeQuoteList
	 * @return mstExchargeQuoteList.
	 */
	public List<MockMstExchargeQuoteDto> getMstExchargeQuoteList() {
		return mstExchargeQuoteList;
	}
	/**
	 * 设置 mstExchargeQuoteList
	 * @param mstExchargeQuoteList mstExchargeQuoteList
	 */
	public void setMstExchargeQuoteList(List<MockMstExchargeQuoteDto> mstExchargeQuoteList) {
		this.mstExchargeQuoteList = mstExchargeQuoteList;
	}
	/**
	 * 获取 financingCost
	 * @return financingCost.
	 */
	public BigDecimal getFinancingCost() {
		return financingCost;
	}
	/**
	 * 设置 financingCost
	 * @param financingCost financingCost
	 */
	public void setFinancingCost(BigDecimal financingCost) {
		this.financingCost = financingCost;
	}
	/**
	 * 获取 reinvestmentRate
	 * @return reinvestmentRate.
	 */
	public BigDecimal getReinvestmentRate() {
		return reinvestmentRate;
	}
	/**
	 * 设置 reinvestmentRate
	 * @param reinvestmentRate reinvestmentRate
	 */
	public void setReinvestmentRate(BigDecimal reinvestmentRate) {
		this.reinvestmentRate = reinvestmentRate;
	}
	/**
	 * 获取 compositeRate
	 * @return compositeRate.
	 */
	public BigDecimal getCompositeRate() {
		return compositeRate;
	}
	/**
	 * 设置 compositeRate
	 * @param compositeRate compositeRate
	 */
	public void setCompositeRate(BigDecimal compositeRate) {
		this.compositeRate = compositeRate;
	}
	/**
	 * 获取 singleAssetChanging
	 * @return singleAssetChanging.
	 */
	public Map<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> getSingleAssetChanging() {
		return singleAssetChanging;
	}
	/**
	 * 设置 singleAssetChanging
	 * @param singleAssetChanging singleAssetChanging
	 */
	public void setSingleAssetChanging(
			Map<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> singleAssetChanging) {
		this.singleAssetChanging = singleAssetChanging;
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
    
    
    
}
