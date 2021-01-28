package com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussFinProductFeePlanDto;

public interface BussFinProductFeePlanService {
	/**
     * 付息计划计算
     *
     * @param inputDto 付息计划计算
     * @return 付息计划结果list
     */
    List<FinCashPlan> getProductFeePlanData(BussFinProductFeePlanDto inputDto);
}
