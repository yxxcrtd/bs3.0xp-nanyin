package com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussFinProductPayPlanDto;

/**
 * 付息计划组件接口
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月3日
 * @see BussFinProductPayPlanService
 * @since 1.0
 */
public interface BussFinProductPayPlanService {
	/**
     * 付息计划计算
     *
     * @param inputDto 付息计划计算
     * @return 付息计划结果list
     */
    List<FinCashPlan> getProductInterestPlanData(BussFinProductPayPlanDto inputDto);
}
