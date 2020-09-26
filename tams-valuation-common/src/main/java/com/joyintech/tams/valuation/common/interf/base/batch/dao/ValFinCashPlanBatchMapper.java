/*
 * 文件名：ValFinCashPlanBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPlan;

@Repository
public interface ValFinCashPlanBatchMapper {
	
	int insertBatch(List<FinCashPlan> list);

	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param rstValFinProductType
	 * @see
	 * @since 1.0
	 */
	int deleteBatch(List<FinCashPlan> list);

}
