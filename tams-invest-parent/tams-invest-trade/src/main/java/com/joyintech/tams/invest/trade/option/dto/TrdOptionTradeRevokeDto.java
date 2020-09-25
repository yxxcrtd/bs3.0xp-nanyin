/*
 * 文件名：TrdLendInvestTradeRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.option.dto;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.option.validator.TrdOptionTradeRevokeDtoValidator;

import java.util.Date;
import java.util.List;

/**
 * 拆借投资交易撤销用dto
 *
 * @author 刘二通
 * @version 1.0 2018年11月12日
 * @see TrdOptionTradeRevokeDto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdOptionTradeRevokeDtoValidator.class)
public class TrdOptionTradeRevokeDto {

    /**
     * 交易编号
     */
    private String totleTradeId;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 工作流代码
     */
    private String workflowId;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 获取 riskTrialRetDto
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }
    
    /**
     * 设置 riskTrialRetDto
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 operateType
     *
     * @return operateType.
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
     * 获取 交易编号
     *
     * @return totleTradeId 交易编号
     */
    public String getTotleTradeId() {
        return this.totleTradeId;
    }

    /**
     * 设置 交易编号
     *
     * @param totleTradeId 交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 交易日期
     *
     * @return tradeDate 交易日期
     */
    public Date getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日期
     *
     * @param tradeDate 交易日期
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 工作流代码
     *
     * @return workflowId 工作流代码
     */
    public String getWorkflowId() {
        return this.workflowId;
    }

    /**
     * 设置 工作流代码
     *
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
}
