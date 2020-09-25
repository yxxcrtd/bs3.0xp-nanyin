/*
 * 文件名：TrdIRSTradeRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.irs.dto;

import java.util.List;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.irs.validator.TrdIRSTradeRevokeValidator;

@ValidLogic(targetClass = TrdIRSTradeRevokeValidator.class)
public class TrdIRSTradeRevokeDto {
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
     * 交易日
     */
    private String tradeDate;
    
	/**
	 * 获取 tradeId
	 * @return tradeId.
	 */
	public String getTradeId() {
		return tradeId;
	}
	/**
	 * 设置 tradeId
	 * @param tradeId tradeId
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * 获取 operateType
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}
	/**
	 * 设置 operateType
	 * @param operateType operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
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
	 * @return the tradeDate
	 */
	public String getTradeDate() {
		return tradeDate;
	}
	/**
	 * @param tradeDate the tradeDate to set
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
    
    
}
