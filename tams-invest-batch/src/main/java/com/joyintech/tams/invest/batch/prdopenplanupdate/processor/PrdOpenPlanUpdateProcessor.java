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
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service.BussFinProductPayPlanService;
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
     * 开放计划组件
     */
    @Autowired
	private BussFinProductOpenPlanService bussfinproductopenplanService;
    /**
     * 付息计划组件
     */
    @Autowired(required = false)
	@Qualifier("batchBussFinProductPayPlanImplService")
    private BussFinProductPayPlanService bussFinProductPayPlanService;
    
	@Override
	public FinOpenPlanDto process(NeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值
		PrdOpenPlanUpdateDto prdOpenPlanUpdateDto = (PrdOpenPlanUpdateDto) item;	
		FinOpenPlanDto finOpenPlanDto = new FinOpenPlanDto();
		if(StringUtil.equals("开放计划", prdOpenPlanUpdateDto.getPlanType())){
			// 公共方法生成的List<开放计划>
			List<FinOpenPlan> finOpenPlanList = bussfinproductopenplanService
					.genOpenPlanList(prdOpenPlanUpdateProcessorService.executeDto(prdOpenPlanUpdateDto));

			// 更新后的开放计划
			List<FinOpenPlan> newFinOpenPlanList = prdOpenPlanUpdateProcessorService.executeList(finOpenPlanList,
					prdOpenPlanUpdateDto);
			
			finOpenPlanDto.setFinOpenPlanList(newFinOpenPlanList);
		}else if(StringUtil.equals("付息计划", prdOpenPlanUpdateDto.getPlanType())){
			// 公共方法生成的List<付息计划>
			List<FinCashPlan> finCashPlanList = bussFinProductPayPlanService.getProductInterestPlanData(
					prdOpenPlanUpdateProcessorService.executePayDto(prdOpenPlanUpdateDto));
			
			// 更新后的付息计划
			List<FinCashPlan> newFinCashPlanList = prdOpenPlanUpdateProcessorService.executePayList(finCashPlanList,
					prdOpenPlanUpdateDto);
			finOpenPlanDto.setFinCashPlanList(newFinCashPlanList);
		}
		
		finOpenPlanDto.setPrdOpenPlanUpdateDto(prdOpenPlanUpdateDto);
		return finOpenPlanDto;
	}

}
