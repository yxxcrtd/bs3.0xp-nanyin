package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.basicdata.MockQuotationInfoDto;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTradeProduct;

/**
 * 资产基本信息DTO
 * 
 * @author wl
 * @version 1.0 2020年4月14日
 * @see MockAssetInfoDto
 * @since 1.0
 */
public class MockAssetInfoDto {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 投管资产大类 虚拟的投管资产大类固定：MOCK
     */
    private String tgAssetType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 标的类金融产品Entity
     */
    private FinProduct finProduct;

    /**
     * 标的类金融产品附属表Entity
     */
    private FinProductAdd finProductAdd;

    /**
     * 交易类金融产品Entity
     */
    private FinTradeProduct finTradeProduct;

    /**
     * 交易类金融产品附属Entity
     */
    private FinTradeProductAdd finTradeProductAdd;

    /**
     * 模拟交易类金融产品Entity
     */
    private MockTradeProduct mockTradeProduct;

    /**
     * 金融产品分类表Entity
     */
    private FinProductType finProductType;

    /**
     * 现金流计算规则表Entity列表
     */
    private List<FinCashCalcRule> finCashCalcRuleList;

    /**
     * 现金流计划表扩展列表
     */
    private List<MockFinCashPlanDto> mockFinCashPlanDtoList;

    /**
     * 行情DTO列表
     */
    private List<MockQuotationInfoDto> mockQuotationInfoDtoList;

    /**
     * 行权计划表Entity
     */
    private List<FinExercisePlan> finExercisePlanList;

    /**
     * 回售权下一行权计划列表
     */
    private List<FinExercisePlan> resaleFinExercisePlanList;

    /**
     * 现金流支付规则批量查询
     */
    private List<FinCashPayRule> finCashPayRuleList;

    /**
     * 基金开放计划
     */
    private List<FinOpenPlan> fundOpenPlanList;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 tgAssetType
     * 
     * @return tgAssetType.
     */
    public String getTgAssetType() {
        return tgAssetType;
    }

    /**
     * 设置 tgAssetType
     * 
     * @param tgAssetType tgAssetType
     */
    public void setTgAssetType(String tgAssetType) {
        this.tgAssetType = tgAssetType;
    }

    /**
     * 获取 branch
     * 
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     * 
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 finProduct
     * 
     * @return finProduct.
     */
    public FinProduct getFinProduct() {
        return finProduct;
    }

    /**
     * 设置 finProduct
     * 
     * @param finProduct finProduct
     */
    public void setFinProduct(FinProduct finProduct) {
        this.finProduct = finProduct;
    }

    /**
     * 获取 finProductAdd
     * 
     * @return finProductAdd.
     */
    public FinProductAdd getFinProductAdd() {
        return finProductAdd;
    }

    /**
     * 设置 finProductAdd
     * 
     * @param finProductAdd finProductAdd
     */
    public void setFinProductAdd(FinProductAdd finProductAdd) {
        this.finProductAdd = finProductAdd;
    }

    /**
     * 获取 finTradeProduct
     * 
     * @return finTradeProduct.
     */
    public FinTradeProduct getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 设置 finTradeProduct
     * 
     * @param finTradeProduct finTradeProduct
     */
    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 获取 mockTradeProduct
     * 
     * @return mockTradeProduct.
     */
    public MockTradeProduct getMockTradeProduct() {
        return mockTradeProduct;
    }

    /**
     * 设置 mockTradeProduct
     * 
     * @param mockTradeProduct mockTradeProduct
     */
    public void setMockTradeProduct(MockTradeProduct mockTradeProduct) {
        this.mockTradeProduct = mockTradeProduct;
    }

    /**
     * 获取 finProductType
     * 
     * @return finProductType.
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 finProductType
     * 
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    /**
     * 获取 finCashCalcRuleList
     * 
     * @return finCashCalcRuleList.
     */
    public List<FinCashCalcRule> getFinCashCalcRuleList() {
        return finCashCalcRuleList;
    }

    /**
     * 设置 finCashCalcRuleList
     * 
     * @param finCashCalcRuleList finCashCalcRuleList
     */
    public void setFinCashCalcRuleList(List<FinCashCalcRule> finCashCalcRuleList) {
        this.finCashCalcRuleList = finCashCalcRuleList;
    }

    /**
     * 获取 mockFinCashPlanDtoList
     * 
     * @return mockFinCashPlanDtoList.
     */
    public List<MockFinCashPlanDto> getMockFinCashPlanDtoList() {
        return mockFinCashPlanDtoList;
    }

    /**
     * 设置 mockFinCashPlanDtoList
     * 
     * @param mockFinCashPlanDtoList mockFinCashPlanDtoList
     */
    public void setMockFinCashPlanDtoList(List<MockFinCashPlanDto> mockFinCashPlanDtoList) {
        this.mockFinCashPlanDtoList = mockFinCashPlanDtoList;
    }

    /**
     * 获取 mockQuotationInfoDtoList
     * 
     * @return mockQuotationInfoDtoList.
     */
    public List<MockQuotationInfoDto> getMockQuotationInfoDtoList() {
        return mockQuotationInfoDtoList;
    }

    /**
     * 设置 mockQuotationInfoDtoList
     * 
     * @param mockQuotationInfoDtoList mockQuotationInfoDtoList
     */
    public void setMockQuotationInfoDtoList(List<MockQuotationInfoDto> mockQuotationInfoDtoList) {
        this.mockQuotationInfoDtoList = mockQuotationInfoDtoList;
    }

    /**
     * 获取 resaleFinExercisePlanList
     * 
     * @return resaleFinExercisePlanList.
     */
    public List<FinExercisePlan> getResaleFinExercisePlanList() {
        return resaleFinExercisePlanList;
    }

    /**
     * 设置 resaleFinExercisePlanList
     * 
     * @param resaleFinExercisePlanList resaleFinExercisePlanList
     */
    public void setResaleFinExercisePlanList(List<FinExercisePlan> resaleFinExercisePlanList) {
        this.resaleFinExercisePlanList = resaleFinExercisePlanList;
    }

    /**
     * 获取 fundOpenPlanList
     * 
     * @return fundOpenPlanList.
     */
    public List<FinOpenPlan> getFundOpenPlanList() {
        return fundOpenPlanList;
    }

    /**
     * 设置 fundOpenPlanList
     * 
     * @param fundOpenPlanList fundOpenPlanList
     */
    public void setFundOpenPlanList(List<FinOpenPlan> fundOpenPlanList) {
        this.fundOpenPlanList = fundOpenPlanList;
    }

    /**
     * 获取 finTradeProductAdd
     * 
     * @return finTradeProductAdd.
     */
    public FinTradeProductAdd getFinTradeProductAdd() {
        return finTradeProductAdd;
    }

    /**
     * 设置 finTradeProductAdd
     * 
     * @param finTradeProductAdd finTradeProductAdd
     */
    public void setFinTradeProductAdd(FinTradeProductAdd finTradeProductAdd) {
        this.finTradeProductAdd = finTradeProductAdd;
    }

    /**
     * 获取 finExercisePlanList
     * 
     * @return finExercisePlanList.
     */
    public List<FinExercisePlan> getFinExercisePlanList() {
        return finExercisePlanList;
    }

    /**
     * 设置 finExercisePlanList
     * 
     * @param finExercisePlanList finExercisePlanList
     */
    public void setFinExercisePlanList(List<FinExercisePlan> finExercisePlanList) {
        this.finExercisePlanList = finExercisePlanList;
    }

    /**
     * 获取 finCashPayRuleList
     * 
     * @return finCashPayRuleList.
     */
    public List<FinCashPayRule> getFinCashPayRuleList() {
        return finCashPayRuleList;
    }

    /**
     * 设置 finCashPayRuleList
     * 
     * @param finCashPayRuleList finCashPayRuleList
     */
    public void setFinCashPayRuleList(List<FinCashPayRule> finCashPayRuleList) {
        this.finCashPayRuleList = finCashPayRuleList;
    }

}