/*
 * 文件名：PtlPositionAnalysis001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.poc.portfolio.ptlmock.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockProductDeal;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTest;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestNetTrend;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestRsk;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestTaa;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTradeProduct;

/**
 * 模拟试算
 * 
 * @author yjm
 * @version 1.0 2020/04/13
 * @see PtlMockTestDto
 * @since 1.0
 */
public class PtlMockTestDto extends MockTest implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 253780656402358531L;

    /**
     * 选择数据
     */
    private List<PtlMockTestDto> selectedItem;

    /**
     * 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    private String portfolioType;

    /**
     * 收益类型，预期收益型/净值型/货币型
     */
    private String profitType;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 试算日期开始日
     */
    private Date dataDateStart;

    /**
     * 试算日期结束日
     */
    private Date dataDateEnd;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 模拟交易（画面内容）
     */
    private List<PtlMockTestTradeInfoDto> pageMockDealList;
    
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
    private List<MockTestTaa> preMockTestTaaList;

    /**
     * 模拟测算资产配置数据(测算后)
     */
    private List<MockTestTaa> afterMockTestTaaList;

    /**
     * 模拟测算净值数据
     */
    private List<MockTestNetTrend> mockTestNetTrendList;

    /**
     * 取得 组合类型，管理组合、投资组合、资金单元、过渡组合
     * 
     * @return 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    public String getPortfolioType() {
        return portfolioType;
    }

    /**
     * 设置 组合类型，管理组合、投资组合、资金单元、过渡组合
     * 
     * @param portfolioType 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    public void setPortfolioType(String portfolioType) {
        this.portfolioType = portfolioType == null ? null : portfolioType.trim();
    }

    /**
     * 取得 组合名称
     * 
     * @return 组合名称
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 组合名称
     * 
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName == null ? null : portfolioName.trim();
    }

    /**
     * 取得 收益类型，预期收益型/净值型/货币型
     * 
     * @return 收益类型，预期收益型/净值型/货币型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型，预期收益型/净值型/货币型
     * 
     * @param profitType 收益类型，预期收益型/净值型/货币型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String getCreateUser() {
        return null;
    }

    @Override
    public void setCreateUser(String s) {

    }

    @Override
    public String getCreateDept() {
        return null;
    }

    @Override
    public void setCreateDept(String s) {

    }

    @Override
    public String getRowId() {
        return null;
    }

    @Override
    public void setRowId(String rowId) {
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setOrder(int order) {
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public void setParentId(String parentId) {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void setLeaf(boolean leaf) {
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
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<PtlMockTestDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<PtlMockTestDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 mockProductDealList
     * 
     * @return mockProductDealList.
     */
    public List<MockProductDeal> getMockProductDealList() {
        return mockProductDealList;
    }

    /**
     * 设置 mockProductDealList
     * 
     * @param mockProductDealList mockProductDealList
     */
    public void setMockProductDealList(List<MockProductDeal> mockProductDealList) {
        this.mockProductDealList = mockProductDealList;
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

    /**
     * 获取 dataDateStart
     * 
     * @return dataDateStart.
     */
    public Date getDataDateStart() {
        return dataDateStart;
    }

    /**
     * 设置 dataDateStart
     * 
     * @param dataDateStart dataDateStart
     */
    public void setDataDateStart(Date dataDateStart) {
        this.dataDateStart = dataDateStart;
    }

    /**
     * 获取 dataDateEnd
     * 
     * @return dataDateEnd.
     */
    public Date getDataDateEnd() {
        return dataDateEnd;
    }

    /**
     * 设置 dataDateEnd
     * 
     * @param dataDateEnd dataDateEnd
     */
    public void setDataDateEnd(Date dataDateEnd) {
        this.dataDateEnd = dataDateEnd;
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
     * 
     * @return preMockTestTaaList.
     */
    public List<MockTestTaa> getPreMockTestTaaList() {
        return preMockTestTaaList;
    }

    /**
     * 设置 preMockTestTaaList
     * 
     * @param preMockTestTaaList preMockTestTaaList
     */
    public void setPreMockTestTaaList(List<MockTestTaa> preMockTestTaaList) {
        this.preMockTestTaaList = preMockTestTaaList;
    }

    /**
     * 获取 afterMockTestTaaList
     * 
     * @return afterMockTestTaaList.
     */
    public List<MockTestTaa> getAfterMockTestTaaList() {
        return afterMockTestTaaList;
    }

    /**
     * 设置 afterMockTestTaaList
     * 
     * @param afterMockTestTaaList afterMockTestTaaList
     */
    public void setAfterMockTestTaaList(List<MockTestTaa> afterMockTestTaaList) {
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
     * 获取 pageMockDealList
     * @return pageMockDealList.
     */
    public List<PtlMockTestTradeInfoDto> getPageMockDealList() {
        return pageMockDealList;
    }

    /**
     * 设置 pageMockDealList
     * @param pageMockDealList pageMockDealList
     */
    public void setPageMockDealList(List<PtlMockTestTradeInfoDto> pageMockDealList) {
        this.pageMockDealList = pageMockDealList;
    }

}
