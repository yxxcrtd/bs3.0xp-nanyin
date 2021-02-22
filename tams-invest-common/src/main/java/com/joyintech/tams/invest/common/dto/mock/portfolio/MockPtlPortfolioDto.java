/*
 * 文件名：MockPtlPortfolioDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.mock.portfolio;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.PaPtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlPortfolio;

import java.math.BigDecimal;
import java.util.List;

/**
 * 组合基本信息dto
 * 
 * @author etliu
 * @version 1.0 2020年4月13日
 * @see MockPtlPortfolioDto
 * @since 1.0
 */
public class MockPtlPortfolioDto extends PtlPortfolio {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6767093144584570240L;

    /**
     * 业绩基准
     */
    private BigDecimal finBaseRuleYield;

    /**
     * 管理费率
     */
    private BigDecimal manageFeeRate;

    /**
     * 分红规则
     */
    private String bonusPayCycle;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 开放计划
     */
    private List<FinOpenPlan> finOpenPlanList;
    
    /**
     * 组合扩展表数据
     */
    private PaPtlPortfolio paPtlPortfolio;

    /**
     * 获取 finBaseRuleYield
     * 
     * @return finBaseRuleYield.
     */
    public BigDecimal getFinBaseRuleYield() {
        return finBaseRuleYield;
    }

    /**
     * 设置 finBaseRuleYield
     * 
     * @param finBaseRuleYield finBaseRuleYield
     */
    public void setFinBaseRuleYield(BigDecimal finBaseRuleYield) {
        this.finBaseRuleYield = finBaseRuleYield;
    }

    /**
     * 获取 manageFeeRate
     * 
     * @return manageFeeRate.
     */
    public BigDecimal getManageFeeRate() {
        return manageFeeRate;
    }

    /**
     * 设置 manageFeeRate
     * 
     * @param manageFeeRate manageFeeRate
     */
    public void setManageFeeRate(BigDecimal manageFeeRate) {
        this.manageFeeRate = manageFeeRate;
    }

    /**
     * 获取 bonusPayCycle
     * 
     * @return bonusPayCycle.
     */
    public String getBonusPayCycle() {
        return bonusPayCycle;
    }

    /**
     * 设置 bonusPayCycle
     * 
     * @param bonusPayCycle bonusPayCycle
     */
    public void setBonusPayCycle(String bonusPayCycle) {
        this.bonusPayCycle = bonusPayCycle;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 finOpenPlanList
     * 
     * @return finOpenPlanList.
     */
    public List<FinOpenPlan> getFinOpenPlanList() {
        return finOpenPlanList;
    }

    /**
     * 设置 finOpenPlanList
     * 
     * @param finOpenPlanList finOpenPlanList
     */
    public void setFinOpenPlanList(List<FinOpenPlan> finOpenPlanList) {
        this.finOpenPlanList = finOpenPlanList;
    }

    /**
     * 获取 paPtlPortfolio
     * @return paPtlPortfolio.
     */
    public PaPtlPortfolio getPaPtlPortfolio() {
        return paPtlPortfolio;
    }

    /**
     * 设置 paPtlPortfolio
     * @param paPtlPortfolio paPtlPortfolio
     */
    public void setPaPtlPortfolio(PaPtlPortfolio paPtlPortfolio) {
        this.paPtlPortfolio = paPtlPortfolio;
    }
    
}
