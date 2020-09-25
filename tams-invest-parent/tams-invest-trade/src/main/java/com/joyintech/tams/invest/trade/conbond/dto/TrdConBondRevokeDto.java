/*
 * 文件名：TrdConBondRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.dto;

import java.util.List;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.conbond.validator.TrdConBondRevokeDtoValidator;

/**
 * 可转债撤销用dto
 *
 * @author wangpeng
 * @version 1.0 2019年11月14日
 * @see TrdConBondRevokeDto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdConBondRevokeDtoValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class TrdConBondRevokeDto {

    /**
     * 交易编号
     */
    private String busiCode;
    
    /**
     * 数据状态
     */
    private String statusValid;

    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 交易方向
     */
    private String ps;
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

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getStatusValid() {
		return statusValid;
	}

	public void setStatusValid(String statusValid) {
		this.statusValid = statusValid;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

}
