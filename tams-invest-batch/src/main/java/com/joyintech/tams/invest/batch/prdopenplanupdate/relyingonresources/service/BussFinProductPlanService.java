package com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinAnapleroticPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinCashPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinFloatPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinPayPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinAnapleroticPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinCashPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinFloatPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinPayPlanDto;

/**
 * 更新计划组件接口
 */
public interface BussFinProductPlanService {
	
	/**
     * 开放计划更新
     *
     * @param inputDto 开放计划信息
     * @return 开放计划结果list
     */
    List<NewFinOpenPlanDto> genOpenPlanList(BussNewFinOpenPlanDto inputDto);
    /**
     * 分红/付息计划更新
     *
     * @param inputDto 分红/付息计划信息
     * @return 分红/付息计划结果list
     */
    List<NewFinCashPlanDto> genCashPlanList(BussNewFinCashPlanDto inputDto);
    /**
     *付费计划更新
     *
     * @param inputDto 付费计划信息
     * @return 付费计划结果list
     */
    List<NewFinPayPlanDto> genPayPlanList(BussNewFinPayPlanDto inputDto);
    /**
     *业绩报酬计提计划更新
     *
     * @param inputDto 付费计划信息
     * @return 付费计划结果list
     */
    List<NewFinFloatPlanDto> genFloatPlanList(BussNewFinFloatPlanDto inputDto);
    /**
     *回补计划更新
     *
     * @param inputDto 付费计划信息
     * @return 付费计划结果list
     */
    List<NewFinAnapleroticPlanDto> genAnapleroticPlanList(BussNewFinAnapleroticPlanDto inputDto);
}

