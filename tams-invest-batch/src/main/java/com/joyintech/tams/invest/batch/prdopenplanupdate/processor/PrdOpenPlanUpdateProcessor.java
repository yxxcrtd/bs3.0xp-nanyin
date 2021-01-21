package com.joyintech.tams.invest.batch.prdopenplanupdate.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.FinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinAnapleroticPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinCashPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinFloatPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinPayPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service.BussFinProductPayPlanService;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service.BussFinProductPlanService;
import com.joyintech.tams.invest.batch.prdopenplanupdate.service.PrdOpenPlanUpdateProcessorService;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.service.BussFinProductOpenPlanService;

/**
 *  批处理-市场日历接入后更新产品开放计划 processor
 */
@Service
@Scope("prototype")
public class PrdOpenPlanUpdateProcessor extends BaseProcessor<NeedOperateEntity, FinOpenPlanDto>{
	@Autowired
    private PrdOpenPlanUpdateProcessorService prdOpenPlanUpdateProcessorService;
	/**
     * 更新计划组件
     */
    @Autowired
	private BussFinProductPlanService bussfinproductplanService;
    
	@Override
	public FinOpenPlanDto process(NeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值
		PrdOpenPlanUpdateDto prdOpenPlanUpdateDto = (PrdOpenPlanUpdateDto) item;	
		FinOpenPlanDto finOpenPlanDto = new FinOpenPlanDto();
		if(StringUtil.equals("开放计划", prdOpenPlanUpdateDto.getPlanType())){
			// <开放计划>
			List<NewFinOpenPlanDto> newFinOpenPlanList = bussfinproductplanService
					.genOpenPlanList(prdOpenPlanUpdateProcessorService.executeOpenPlanDto(prdOpenPlanUpdateDto));			
			finOpenPlanDto.setFinOpenPlanList(newFinOpenPlanList);
		}
		else if(StringUtil.equals("分红/付息计划", prdOpenPlanUpdateDto.getPlanType())){
			// <分红/付息计划>
		    List<NewFinCashPlanDto> newFinCashPlanList = bussfinproductplanService
					.genCashPlanList(prdOpenPlanUpdateProcessorService.executeCashPlanDto(prdOpenPlanUpdateDto));			
		    finOpenPlanDto.setFinCashPlanList(newFinCashPlanList);
		}
		else if(StringUtil.equals("付费计划", prdOpenPlanUpdateDto.getPlanType())){
			// <付费计划>
		    List<NewFinPayPlanDto> newFinPayPlanList = bussfinproductplanService
					.genPayPlanList(prdOpenPlanUpdateProcessorService.executePayPlanDto(prdOpenPlanUpdateDto));			
		    finOpenPlanDto.setFinPayPlanList(newFinPayPlanList);
		}
		else if(StringUtil.equals("业绩报酬计提计划", prdOpenPlanUpdateDto.getPlanType())){
			// <业绩报酬计提计划>
		    List<NewFinFloatPlanDto> newFinFloatPlanList = bussfinproductplanService
					.genFloatPlanList(prdOpenPlanUpdateProcessorService.executeFloatPlanDto(prdOpenPlanUpdateDto));			
		    finOpenPlanDto.setFinFloatPlanList(newFinFloatPlanList);
		}
		else if(StringUtil.equals("回补计划", prdOpenPlanUpdateDto.getPlanType())){
			// <回补计划计划>
		    List<NewFinAnapleroticPlanDto> newFinAnapleroticPlanList = bussfinproductplanService
					.genAnapleroticPlanList(prdOpenPlanUpdateProcessorService.executeAnapleroticPlanDto(prdOpenPlanUpdateDto));			
		    finOpenPlanDto.setFinAnapleroticPlanList(newFinAnapleroticPlanList);
		}
		
		finOpenPlanDto.setPrdOpenPlanUpdateDto(prdOpenPlanUpdateDto);
		return finOpenPlanDto;
	}

}
