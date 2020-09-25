/*
 * 文件名：TrdBondLendInvestTradeRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年06月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.bondlend.dto;

import java.util.List;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.bondlend.validator.TrdBondLendInvestTradeRevokeDtoValidator;


/**
 * 债券投资交易撤销用dto
 *
 * @author 裴家俊
 * @version 1.0 2019年06月25日
 * @see TrdBondLendInvestTradeRevokeDto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdBondLendInvestTradeRevokeDtoValidator.class)
public class TrdBondLendInvestTradeRevokeDto {

    /**
     * 交易编号
     */
    private String tradeId;

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

}
