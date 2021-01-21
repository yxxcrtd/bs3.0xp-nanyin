package com.joyintech.tams.invest.batch.prdopenplanupdate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dao.PrdOpenPlanUpdateMapper;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinAnapleroticPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinCashPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinFloatPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussNewFinPayPlanDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.BussFinProductOpenPlanDto;

/**
 * processor相关业务处理
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class PrdOpenPlanUpdateProcessorService extends BaseService{

	@Autowired
	private PrdOpenPlanUpdateMapper prdOpenPlanUpdateMapper;
	/**
     * 平台日期接口
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;
    
	/**
	 * 开放计划
	 * @param popuDto
	 * @return
	 */
	public BussNewFinOpenPlanDto executeOpenPlanDto(PrdOpenPlanUpdateDto popuDto){
		BussNewFinOpenPlanDto bussNewFinOpenPlanDto = new BussNewFinOpenPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussNewFinOpenPlanDto, popuDto);
		bussNewFinOpenPlanDto.setOpenDateStrUn(popuDto.getOpenDateStr());
		bussNewFinOpenPlanDto.setOpenDateEndUn(popuDto.getOpenDateEnd());
		bussNewFinOpenPlanDto.setOpenVdateUn(popuDto.getOpenVdate());
		bussNewFinOpenPlanDto.setOpenMdateUn(popuDto.getOpenMdate());
		return bussNewFinOpenPlanDto;
	}

	/**
	 * 分红/付息计划
	 * @param popuDto
	 * @return
	 */
	public BussNewFinCashPlanDto executeCashPlanDto(PrdOpenPlanUpdateDto popuDto){
		BussNewFinCashPlanDto bussNewFinCashPlanDto = new BussNewFinCashPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussNewFinCashPlanDto, popuDto);
		return bussNewFinCashPlanDto;
	}
	
	/**
	 * 付费计划
	 * @param popuDto
	 * @return
	 */
	public BussNewFinPayPlanDto executePayPlanDto(PrdOpenPlanUpdateDto popuDto){
		BussNewFinPayPlanDto bussNewFinPayPlanDto = new BussNewFinPayPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussNewFinPayPlanDto, popuDto);
		return bussNewFinPayPlanDto;
	}
	
	/**
	 * 业绩报酬计提计划
	 * @param popuDto
	 * @return
	 */
	public BussNewFinFloatPlanDto executeFloatPlanDto(PrdOpenPlanUpdateDto popuDto){
		BussNewFinFloatPlanDto bussNewFinFloatPlanDto = new BussNewFinFloatPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussNewFinFloatPlanDto, popuDto);
		return bussNewFinFloatPlanDto;
	}
	
	/**
	 * 回补计划
	 * @param popuDto
	 * @return
	 */
	public BussNewFinAnapleroticPlanDto executeAnapleroticPlanDto(PrdOpenPlanUpdateDto popuDto){
		BussNewFinAnapleroticPlanDto bussNewFinAnapleroticPlanDto = new BussNewFinAnapleroticPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussNewFinAnapleroticPlanDto, popuDto);
		return bussNewFinAnapleroticPlanDto;
	}
	
}
