/*
 * 文件名：PtlPositionAnalysis001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockProductDeal;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTest;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTestNetTrend;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTestRsk;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTestTaa;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTradeProduct;
import com.joyintech.tams.poc.portfolio.ptlmock.validate.PtlMockTestValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 模拟试算
 * 
 * @author yjm
 * @version 1.0 2020/04/13
 * @see PtlMockTest002Dto
 * @since 1.0
 */
@PageId("PtlMockTest002")
@ValidLogic(targetClass = PtlMockTestValidator.class, groups = ValidateGroupEdit.class)
public class PtlMockTest002Dto extends MockTest implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1176061163895502211L;
    
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 组合类型
     */
    private String portfolioType;

    /**
     * 收益类型，预期收益型/净值型/货币型
     */
    private String profitType;

    /**
     * 模拟交易（画面内容）
     */
    private List<PtlMockTestTradeDto> pageMockDealList;
    
    /**
     * 模拟交易（转化后）
     */
    private List<MockProductDeal> mockProductDealList;

    /**
     * 模拟交易对应资产
     */
    private List<MockTradeProduct> mockTradeProductList;

    /**
     * 模拟测算风险测算结果
     */
    private List<MockTestRsk> mockTestRskList;

    /**
     * 模拟测算资产配置数据(测算前（考虑当日成交）)
     */
    private List<PtlMockTestTaaDto> preMockTestTaaList;
    
    /**
     * 模拟测算资产配置数据(测算后)
     */
    private List<PtlMockTestTaaDto> afterMockTestTaaList;

    /**
     * 模拟测算净值数据
     */
    private List<MockTestNetTrend> mockTestNetTrendList;

    @Override
    @NotEmpty(fieldName = "PtlMockTest002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    @Override
    @NotEmpty(fieldName = "PtlMockTest002.againInvestRate", groups = {ValidateGroupEdit.class})
    public BigDecimal getAgainInvestRate() {
        return super.getAgainInvestRate();
    }

    @Override
    @NotEmpty(fieldName = "PtlMockTest002.finanCost", groups = {ValidateGroupEdit.class})
    public BigDecimal getFinanCost() {
        return super.getFinanCost();
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getMockId() {
        return super.getMockId();
    }

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     *
     * @return pageModel
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 pageMockDealList
     * @return pageMockDealList.
     */
    public List<PtlMockTestTradeDto> getPageMockDealList() {
        return pageMockDealList;
    }

    /**
     * 设置 pageMockDealList
     * @param pageMockDealList pageMockDealList
     */
    public void setPageMockDealList(List<PtlMockTestTradeDto> pageMockDealList) {
        this.pageMockDealList = pageMockDealList;
    }

    
    /**
     * 获取 mockProductDealList
     * @return mockProductDealList.
     */
    public List<MockProductDeal> getMockProductDealList() {
        return mockProductDealList;
    }

    
    /**
     * 设置 mockProductDealList
     * @param mockProductDealList mockProductDealList
     */
    public void setMockProductDealList(List<MockProductDeal> mockProductDealList) {
        this.mockProductDealList = mockProductDealList;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 portfolioType
     * 
     * @return portfolioType.
     */
    public String getPortfolioType() {
        return portfolioType;
    }

    /**
     * 设置 portfolioType
     * 
     * @param portfolioType portfolioType
     */
    public void setPortfolioType(String portfolioType) {
        this.portfolioType = portfolioType;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 mockTestRskList
     * 
     * @return mockTestRskList.
     */
    public List<MockTestRsk> getMockTestRskList() {
        return mockTestRskList;
    }

    /**
     * 设置 mockTestRskList
     * 
     * @param mockTestRskList mockTestRskList
     */
    public void setMockTestRskList(List<MockTestRsk> mockTestRskList) {
        this.mockTestRskList = mockTestRskList;
    }

    /**
     * 获取 preMockTestTaaList
     * @return preMockTestTaaList.
     */
    public List<PtlMockTestTaaDto> getPreMockTestTaaList() {
        return preMockTestTaaList;
    }

    /**
     * 设置 preMockTestTaaList
     * @param preMockTestTaaList preMockTestTaaList
     */
    public void setPreMockTestTaaList(List<PtlMockTestTaaDto> preMockTestTaaList) {
        this.preMockTestTaaList = preMockTestTaaList;
    }

    /**
     * 获取 afterMockTestTaaList
     * @return afterMockTestTaaList.
     */
    public List<PtlMockTestTaaDto> getAfterMockTestTaaList() {
        return afterMockTestTaaList;
    }

    /**
     * 设置 afterMockTestTaaList
     * @param afterMockTestTaaList afterMockTestTaaList
     */
    public void setAfterMockTestTaaList(List<PtlMockTestTaaDto> afterMockTestTaaList) {
        this.afterMockTestTaaList = afterMockTestTaaList;
    }

    /**
     * 获取 mockTestNetTrendList
     * 
     * @return mockTestNetTrendList.
     */
    public List<MockTestNetTrend> getMockTestNetTrendList() {
        return mockTestNetTrendList;
    }

    /**
     * 设置 mockTestNetTrendList
     * 
     * @param mockTestNetTrendList mockTestNetTrendList
     */
    public void setMockTestNetTrendList(List<MockTestNetTrend> mockTestNetTrendList) {
        this.mockTestNetTrendList = mockTestNetTrendList;
    }

    /**
     * 获取 mockTradeProductList
     * 
     * @return mockTradeProductList.
     */
    public List<MockTradeProduct> getMockTradeProductList() {
        return mockTradeProductList;
    }

    /**
     * 设置 mockTradeProductList
     * 
     * @param mockTradeProductList mockTradeProductList
     */
    public void setMockTradeProductList(List<MockTradeProduct> mockTradeProductList) {
        this.mockTradeProductList = mockTradeProductList;
    }

}
