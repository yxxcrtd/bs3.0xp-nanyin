/*
 * 文件名：TrdSpotexInvestTradeRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.spotex.dto;

import java.util.List;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.spotex.validator.TrdSpotexInvestTradeRevokeDtoValidator;

/**
 * 外汇即期交易撤销用Dto
 * @author wangguoxian
 * @version 1.0 2019年7月11日
 * @see TrdSpotexInvestTradeRevokeDto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdSpotexInvestTradeRevokeDtoValidator.class)
public class TrdSpotexInvestTradeRevokeDto {

    /**
     * 交易编号
     */
    private String tradeId;

    private List<TrdSpotexInvestTradeDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;
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
     * 获取 tradeId
     *
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 tradeId
     *
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
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
     * 获取选中的数据
     * @return 选中的数据
     */
    public List<TrdSpotexInvestTradeDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置选中的数据
     * @param selectedItem 选中的数据
     */
    public void setSelectedItem(List<TrdSpotexInvestTradeDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
