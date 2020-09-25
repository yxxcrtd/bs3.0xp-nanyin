/*
 * 文件名：FinProductFeePlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.MstFeeinfo;

/**
 * 产品费用信息业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月11日
 * @see FinProductFeeInfoDto
 * @since 1.0
 */
public class FinProductFeeInfoDto {

	/**
	 * 现金流基本信息dto
	 */
	private FinCashInfo finCashInfoDto;
	
	/**
	 * 现金流计算规则dto
	 */
	private FinCashCalcRule finCashCalcRuleDto;
	
	/**
	 * 现金流支付规则dto
	 */
	private FinCashPayRule finCashPayRuleDto;
	
	/**
	 * 现金流计划dtoList
	 */
	private transient List<FinCashPlan> finCashPlanDtoList;
	
	/**
	 * 现金流利率信息dto
	 */
	private FinRateInfo  finRateInfoDto;
	
	/**
	 * 费用品种信息dto
	 */
	private MstFeeinfo MstFeeinfoDto;

	/**
	 * @return the finCashInfoDto
	 */
	public FinCashInfo getFinCashInfoDto() {
		return finCashInfoDto;
	}

	/**
	 * @param finCashInfoDto the finCashInfoDto to set
	 */
	public void setFinCashInfoDto(FinCashInfo finCashInfoDto) {
		this.finCashInfoDto = finCashInfoDto;
	}

	/**
	 * @return the finCashCalcRuleDto
	 */
	public FinCashCalcRule getFinCashCalcRuleDto() {
		return finCashCalcRuleDto;
	}

	/**
	 * @param finCashCalcRuleDto the finCashCalcRuleDto to set
	 */
	public void setFinCashCalcRuleDto(FinCashCalcRule finCashCalcRuleDto) {
		this.finCashCalcRuleDto = finCashCalcRuleDto;
	}

	/**
	 * @return the finCashPayRuleDto
	 */
	public FinCashPayRule getFinCashPayRuleDto() {
		return finCashPayRuleDto;
	}

	/**
	 * @param finCashPayRuleDto the finCashPayRuleDto to set
	 */
	public void setFinCashPayRuleDto(FinCashPayRule finCashPayRuleDto) {
		this.finCashPayRuleDto = finCashPayRuleDto;
	}

	/**
	 * @return the finCashPlanDtoList
	 */
	public List<FinCashPlan> getFinCashPlanDtoList() {
		return finCashPlanDtoList;
	}

	/**
	 * @param finCashPlanDtoList the finCashPlanDtoList to set
	 */
	public void setFinCashPlanDtoList(List<FinCashPlan> finCashPlanDtoList) {
		this.finCashPlanDtoList = finCashPlanDtoList;
	}

	/**
	 * @return the finRateInfoDto
	 */
	public FinRateInfo getFinRateInfoDto() {
		return finRateInfoDto;
	}

	/**
	 * @param finRateInfoDto the finRateInfoDto to set
	 */
	public void setFinRateInfoDto(FinRateInfo finRateInfoDto) {
		this.finRateInfoDto = finRateInfoDto;
	}

	/**
	 * @return the mstFeeinfoDto
	 */
	public MstFeeinfo getMstFeeinfoDto() {
		return MstFeeinfoDto;
	}

	/**
	 * @param mstFeeinfoDto the mstFeeinfoDto to set
	 */
	public void setMstFeeinfoDto(MstFeeinfo mstFeeinfoDto) {
		MstFeeinfoDto = mstFeeinfoDto;
	}
	
}
