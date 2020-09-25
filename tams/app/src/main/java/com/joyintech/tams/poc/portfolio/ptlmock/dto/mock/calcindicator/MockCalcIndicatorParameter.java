/*
 * 文件名：MockCalcIndicatorParameter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.calcindicator;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlCashPositionDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlSecValuationDto;

/**
 * 组合实时估值服务-计算指标 参数dto
 * 
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see MockCalcIndicatorParameter
 * @since 1.0
 */
public class MockCalcIndicatorParameter {

    /**
     * 组合信息
     */
    private MockPtlPortfolioDto mockPtlPortfolioDto;

    /**
     * 组合资产估值列表
     */
    private List<MockPtlSecValuationDto> mockPtlSecValuationDtoList;

    /**
     * 组合估值列表
     */
    private List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList;

    /**
     * 组合剩余现金
     */
    private MockPtlCashPositionDto mockPtlCashPositionDto;

    /**
     * 资产基本信息列表
     */
    private List<MockAssetInfoDto> mockAssetInfoDtoList;

    /**
     * 汇率行情列表
     */
    private List<MockMstExchargeQuoteDto> exchargeQuoteList;

    /**
     * 指标名称列表
     */
    private List<IndicatorName> indicatorNameList;

    /**
     * 计算日期
     */
    private Date calcDate;

    /**
     * 计算日期起始日
     */
    private Date calcDateStart;

    /**
     * 计算日期终止日
     */
    private Date calcDateEnd;

    /**
     * 获取 mockPtlPortfolioDto
     * 
     * @return mockPtlPortfolioDto.
     */
    public MockPtlPortfolioDto getMockPtlPortfolioDto() {
        return mockPtlPortfolioDto;
    }

    /**
     * 设置 mockPtlPortfolioDto
     * 
     * @param mockPtlPortfolioDto mockPtlPortfolioDto
     */
    public void setMockPtlPortfolioDto(MockPtlPortfolioDto mockPtlPortfolioDto) {
        this.mockPtlPortfolioDto = mockPtlPortfolioDto;
    }

    /**
     * 获取 mockPtlSecValuationDtoList
     * 
     * @return mockPtlSecValuationDtoList.
     */
    public List<MockPtlSecValuationDto> getMockPtlSecValuationDtoList() {
        return mockPtlSecValuationDtoList;
    }

    /**
     * 设置 mockPtlSecValuationDtoList
     * 
     * @param mockPtlSecValuationDtoList mockPtlSecValuationDtoList
     */
    public void setMockPtlSecValuationDtoList(List<MockPtlSecValuationDto> mockPtlSecValuationDtoList) {
        this.mockPtlSecValuationDtoList = mockPtlSecValuationDtoList;
    }

    /**
     * 获取 mockPtlPortfolioValuationDtoList
     * 
     * @return mockPtlPortfolioValuationDtoList.
     */
    public List<MockPtlPortfolioValuationDto> getMockPtlPortfolioValuationDtoList() {
        return mockPtlPortfolioValuationDtoList;
    }

    /**
     * 设置 mockPtlPortfolioValuationDtoList
     * 
     * @param mockPtlPortfolioValuationDtoList mockPtlPortfolioValuationDtoList
     */
    public void setMockPtlPortfolioValuationDtoList(List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList) {
        this.mockPtlPortfolioValuationDtoList = mockPtlPortfolioValuationDtoList;
    }

    /**
     * 获取 mockPtlCashPositionDto
     * 
     * @return mockPtlCashPositionDto.
     */
    public MockPtlCashPositionDto getMockPtlCashPositionDto() {
        return mockPtlCashPositionDto;
    }

    /**
     * 设置 mockPtlCashPositionDto
     * 
     * @param mockPtlCashPositionDto mockPtlCashPositionDto
     */
    public void setMockPtlCashPositionDto(MockPtlCashPositionDto mockPtlCashPositionDto) {
        this.mockPtlCashPositionDto = mockPtlCashPositionDto;
    }

    /**
     * 获取 mockAssetInfoDtoList
     * 
     * @return mockAssetInfoDtoList.
     */
    public List<MockAssetInfoDto> getMockAssetInfoDtoList() {
        return mockAssetInfoDtoList;
    }

    /**
     * 设置 mockAssetInfoDtoList
     * 
     * @param mockAssetInfoDtoList mockAssetInfoDtoList
     */
    public void setMockAssetInfoDtoList(List<MockAssetInfoDto> mockAssetInfoDtoList) {
        this.mockAssetInfoDtoList = mockAssetInfoDtoList;
    }

    /**
     * 获取 indicatorNameList
     * 
     * @return indicatorNameList.
     */
    public List<IndicatorName> getIndicatorNameList() {
        return indicatorNameList;
    }

    /**
     * 设置 indicatorNameList
     * 
     * @param indicatorNameList indicatorNameList
     */
    public void setIndicatorNameList(List<IndicatorName> indicatorNameList) {
        this.indicatorNameList = indicatorNameList;
    }

    /**
     * 获取 calcDate
     * 
     * @return calcDate.
     */
    public Date getCalcDate() {
        return calcDate;
    }

    /**
     * 设置 calcDate
     * 
     * @param calcDate calcDate
     */
    public void setCalcDate(Date calcDate) {
        this.calcDate = calcDate;
    }

    /**
     * 获取 calcDateStart
     * 
     * @return calcDateStart.
     */
    public Date getCalcDateStart() {
        return calcDateStart;
    }

    /**
     * 设置 calcDateStart
     * 
     * @param calcDateStart calcDateStart
     */
    public void setCalcDateStart(Date calcDateStart) {
        this.calcDateStart = calcDateStart;
    }

    /**
     * 获取 calcDateEnd
     * 
     * @return calcDateEnd.
     */
    public Date getCalcDateEnd() {
        return calcDateEnd;
    }

    /**
     * 设置 calcDateEnd
     * 
     * @param calcDateEnd calcDateEnd
     */
    public void setCalcDateEnd(Date calcDateEnd) {
        this.calcDateEnd = calcDateEnd;
    }

    /**
     * 获取 exchargeQuoteList
     * 
     * @return exchargeQuoteList.
     */
    public List<MockMstExchargeQuoteDto> getExchargeQuoteList() {
        return exchargeQuoteList;
    }

    /**
     * 设置 exchargeQuoteList
     * 
     * @param exchargeQuoteList exchargeQuoteList
     */
    public void setExchargeQuoteList(List<MockMstExchargeQuoteDto> exchargeQuoteList) {
        this.exchargeQuoteList = exchargeQuoteList;
    }

}
