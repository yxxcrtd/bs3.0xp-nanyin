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
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussFinProductPayPlanDto;
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
	 * 生成开发计划规则
	 * @param popuDto
	 * @return
	 */
	public BussFinProductOpenPlanDto executeDto(PrdOpenPlanUpdateDto popuDto){
		BussFinProductOpenPlanDto bussFinProductOpenPlanDto = new BussFinProductOpenPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussFinProductOpenPlanDto, popuDto);
		bussFinProductOpenPlanDto.setVdate(popuDto.getOpenVdate());
		bussFinProductOpenPlanDto.setMdate(popuDto.getOpenMdate());
		bussFinProductOpenPlanDto.setOperationType(popuDto.getOpenMode());
		bussFinProductOpenPlanDto.setTradCalender(popuDto.getCalendarId());
		bussFinProductOpenPlanDto.setCalcMethod(popuDto.getCalcMethod());
		if(StringUtil.isNotEmpty(popuDto.getOpenCycle())) {
			bussFinProductOpenPlanDto.setOpenCycle(Long.parseLong(popuDto.getOpenCycle()));
		}
		bussFinProductOpenPlanDto.setOpenCycleUnit(popuDto.getOpenCycleUnit());
		if (DictCodeListExt1.日期类型_周几.getValue().equals(popuDto.getDayType())) {
			bussFinProductOpenPlanDto.setDayOfWeek(popuDto.getDayValue());
		}
		if (DictCodeListExt1.日期类型_第几自然日.getValue().equals(popuDto.getDayType())) {
			bussFinProductOpenPlanDto.setDayOfMonth(popuDto.getDayValue());
		}
		if (DictCodeListExt1.日期类型_第几工作日.getValue().equals(popuDto.getDayType())) {
			bussFinProductOpenPlanDto.setWorkdayOfMonth(popuDto.getDayValue());
		}
		if (DictCodeListExt1.日期类型_第几周周几.getValue().equals(popuDto.getDayType())) {
			bussFinProductOpenPlanDto.setWeekOfMonth(popuDto.getDayValue().split(":")[0]);
		}
		if (DictCodeListExt1.日期类型_第几周周几.getValue().equals(popuDto.getDayType())) {
			bussFinProductOpenPlanDto.setWorkdayOfWeek(popuDto.getDayValue().split(":")[1]);
		}
		bussFinProductOpenPlanDto.setTradedayRule(popuDto.getTradedayRule());
		bussFinProductOpenPlanDto.setFirstOpenDate(popuDto.getFirstOpenDate());
		bussFinProductOpenPlanDto.setOpenDaysType(popuDto.getOpenDaysType());
		if(StringUtil.isNotEmpty(popuDto.getOpenDays())) {
			bussFinProductOpenPlanDto.setOpenDays(Long.parseLong(popuDto.getOpenDays()));
		}
		bussFinProductOpenPlanDto.setCpriceRule(popuDto.getCpriceRule());
		bussFinProductOpenPlanDto.setIsCycle(popuDto.getIsCycle());
		if(StringUtil.isNotEmpty(popuDto.getCloseDays())) {
			bussFinProductOpenPlanDto.setCloseTerm(Integer.parseInt(popuDto.getCloseDays()));
		}
		bussFinProductOpenPlanDto.setDealMode(popuDto.getDealMode());

		return bussFinProductOpenPlanDto;
	}
	
	/**
	 * 处理更新后的开发计划
	 * @param finOpenPlanList
	 * @param popuDto
	 * @return
	 * @throws Exception
	 */
	public List<FinOpenPlan> executeList(List<FinOpenPlan> finOpenPlanList, PrdOpenPlanUpdateDto popuDto) throws Exception {
		// 经过处理的List<开放计划>
		List<FinOpenPlan> newFinOpenPlanList = new ArrayList<FinOpenPlan>();

		for (FinOpenPlan finOpenPlan : finOpenPlanList) {
            finOpenPlan.setOpenVdateUn(finOpenPlan.getOpenVdate());
			if (finOpenPlan.getOpenVdateUn().compareTo(popuDto.getOpenVdate()) < 0 ) { // 前者小于后者
				continue;
			}else if(finOpenPlan.getOpenDateEnd().compareTo(platformDateService.getSysDate()) <1){ // 开放结束日小于平台日
				popuDto.setOpenDateEnd(DateUtil.addDay(popuDto.getOpenDateEnd(), 1));
				continue;
			} else {
                finOpenPlan.setOpenDateStrUn(finOpenPlan.getOpenDateStr());
                finOpenPlan.setOpenDateEndUn(finOpenPlan.getOpenDateEnd());
                finOpenPlan.setOpenMdateUn(finOpenPlan.getOpenMdate());
				finOpenPlan.setCashId(popuDto.getCashId()); // 现金流代码
				finOpenPlan.setOpenType(popuDto.getOpenType());
				finOpenPlan.setFinprodId(popuDto.getFinprodId());
				finOpenPlan.setBranch(0L);
				finOpenPlan.setOpenStatus(DictCodeList.开放状态_开放.getValue());
				newFinOpenPlanList.add(finOpenPlan);
			}

		}
		return newFinOpenPlanList;
	}
	
	/**
	 * 生成付息计划规则
	 * @param popuDto
	 * @return
	 */
	public BussFinProductPayPlanDto executePayDto(PrdOpenPlanUpdateDto popuDto){
		BussFinProductPayPlanDto bussFinProductPayPlanDto = new BussFinProductPayPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussFinProductPayPlanDto, popuDto);
		
		bussFinProductPayPlanDto.setVdate(popuDto.getPayVdate());
		bussFinProductPayPlanDto.setMdate(popuDto.getPayMdate());

		return bussFinProductPayPlanDto;
	}
	
	/**
	 * 处理更新后的付息计划
	 * @param finOpenPlanList
	 * @param popuDto
	 * @return
	 * @throws Exception
	 */
	public List<FinCashPlan> executePayList(List<FinCashPlan> finCashPlanList, PrdOpenPlanUpdateDto popuDto) throws Exception {
		// 经过处理的List<付息计划>
		List<FinCashPlan> newFinCashPlanList = new ArrayList<FinCashPlan>();

		//根据产品代码查询已支付的付息计划的最大计提结束日
		Date maxDealMdate = prdOpenPlanUpdateMapper.queryMaxDealMdate(popuDto.getFinprodId());
		for (int i = 0; i < finCashPlanList.size(); i++ ) {
			FinCashPlan finCashPlan = finCashPlanList.get(i);
			if (finCashPlan.getVdate().compareTo(popuDto.getPayVdate()) < 0 ){ // 前者小于后者
				continue;
			}else if(StringUtil.isNotEmpty(maxDealMdate) && maxDealMdate.compareTo(finCashPlan.getVdate()) > 0) { //已交易的计提结束日>计提开始日
				popuDto.setCashNum(popuDto.getCashNum()+1);
				popuDto.setPayFPMdate(DateUtil.addDay(popuDto.getPayFPMdate(), 1));
				continue;
			}else {
                finCashPlan.setCashId(popuDto.getCashId());
                finCashPlan.setCashNum(new Long(popuDto.getCashNum() + i));
                finCashPlan.setCashType(DictCodeList.现金流类型_中间收息.getValue());
                finCashPlan.setVdateUnadjust(finCashPlan.getVdate());
                finCashPlan.setMdateUnadjust(finCashPlan.getMdate());
                finCashPlan.setPayDateUnadjust(finCashPlan.getPayDate());
                finCashPlan.setFinprodId(popuDto.getFinprodId());
                finCashPlan.setFinprodType("F16");
                finCashPlan.setFinprodType2("F16");
                finCashPlan.setBranch(0L);
				newFinCashPlanList.add(finCashPlan);
			}

		}
		return newFinCashPlanList;
	}
	
}
