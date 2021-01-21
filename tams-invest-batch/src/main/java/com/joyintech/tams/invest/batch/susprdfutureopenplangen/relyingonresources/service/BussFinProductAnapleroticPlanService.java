package com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.service;

import java.util.List;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.SusPrdFutureOpenPlanGenDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.BussFinProductAnapleroticPlanDto;

public interface BussFinProductAnapleroticPlanService {
	
	  /**
     * 业绩回补计划计算
     *
     * @param bussFinProductAnapleroticPlanDto 业绩回补计划计算
     * @return 业绩回补计划结果list
     */
    List<FinFloatAccrualSche> genAnapleroticPlanList(BussFinProductAnapleroticPlanDto bussFinProductAnapleroticPlanDto);
}
