/*
 * 文件名：ResetBaseRuleInfoReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.susprdfutureopenplangen.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dao.SusPrdFutureOpenPlanGenMapper;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.SusPrdFutureOpenPlanGenDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.BussFinProductAnapleroticPlanDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.BussFinProductOpenPlanDto;
import com.joyintech.tams.invest.common.CommonUtil;

/**
 * reader相关业务处理
 * 
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class SusPrdFutureOpenPlanGenProcesserService extends BaseService {
	
	@Autowired
	private SusPrdFutureOpenPlanGenMapper susPrdFutureOpenPlanGenMapper;

	/**
	 * 
	 * @param susprdfutureopenplangendto
	 * @param years 推迟年数
	 */
	public BussFinProductOpenPlanDto executeDto(SusPrdFutureOpenPlanGenDto susprdfutureopenplangendto,int years)
			throws Exception {

		BussFinProductOpenPlanDto bussFinProductOpenPlanDto = new BussFinProductOpenPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussFinProductOpenPlanDto, susprdfutureopenplangendto);
		bussFinProductOpenPlanDto.setVdate(susprdfutureopenplangendto.getOpenVdate());
		bussFinProductOpenPlanDto.setMdate(susprdfutureopenplangendto.getMdate());
		bussFinProductOpenPlanDto.setOperationType(susprdfutureopenplangendto.getOpenMode());
		bussFinProductOpenPlanDto.setTradCalender(susprdfutureopenplangendto.getCalendarId());
		bussFinProductOpenPlanDto.setCalcMethod(susprdfutureopenplangendto.getCalcMethod());
		if(StringUtil.isNotEmpty(susprdfutureopenplangendto.getOpenCycle())) {
			bussFinProductOpenPlanDto.setOpenCycle(Long.parseLong(susprdfutureopenplangendto.getOpenCycle()));
		}
		bussFinProductOpenPlanDto.setOpenCycleUnit(susprdfutureopenplangendto.getOpenCycleUnit());
		if (DictCodeListExt1.日期类型_周几.getValue().equals(susprdfutureopenplangendto.getDayType())) {
			bussFinProductOpenPlanDto.setDayOfWeek(susprdfutureopenplangendto.getDayValue());
		}
		if (DictCodeListExt1.日期类型_第几自然日.getValue().equals(susprdfutureopenplangendto.getDayType())) {
			bussFinProductOpenPlanDto.setDayOfMonth(susprdfutureopenplangendto.getDayValue());
		}
		if (DictCodeListExt1.日期类型_第几工作日.getValue().equals(susprdfutureopenplangendto.getDayType())) {
			bussFinProductOpenPlanDto.setWorkdayOfMonth(susprdfutureopenplangendto.getDayValue());
		}
		if (DictCodeListExt1.日期类型_第几周周几.getValue().equals(susprdfutureopenplangendto.getDayType())) {
			bussFinProductOpenPlanDto.setWeekOfMonth(susprdfutureopenplangendto.getDayValue().split(";")[0]);
		}
		if (DictCodeListExt1.日期类型_第几周周几.getValue().equals(susprdfutureopenplangendto.getDayType())) {
			bussFinProductOpenPlanDto.setWorkdayOfWeek(susprdfutureopenplangendto.getDayValue().split(";")[1]);
		}
		bussFinProductOpenPlanDto.setTradedayRule(susprdfutureopenplangendto.getTradedayRule());
		bussFinProductOpenPlanDto.setFirstOpenDate(susprdfutureopenplangendto.getFirstOpenDate());
		bussFinProductOpenPlanDto.setOpenDaysType(susprdfutureopenplangendto.getOpenDaysType());
		if(StringUtil.isNotEmpty(susprdfutureopenplangendto.getOpenDays())) {
			bussFinProductOpenPlanDto.setOpenDays(Long.parseLong(susprdfutureopenplangendto.getOpenDays()));
		}
		bussFinProductOpenPlanDto.setCpriceRule(susprdfutureopenplangendto.getCpriceRule());
		bussFinProductOpenPlanDto.setIsCycle(susprdfutureopenplangendto.getIsCycle());
		if(StringUtil.isNotEmpty(susprdfutureopenplangendto.getCloseDays())) {
			bussFinProductOpenPlanDto.setCloseTerm(Integer.parseInt(susprdfutureopenplangendto.getCloseDays()));
		}
		bussFinProductOpenPlanDto.setDealMode(susprdfutureopenplangendto.getDealMode());

		return bussFinProductOpenPlanDto;
	}

	public List<FinOpenPlan> executeList(List<FinOpenPlan> finOpenPlanList,
			SusPrdFutureOpenPlanGenDto susprdfutureopenplangendto) throws Exception {
		// 经过处理的List<开放计划>
		List<FinOpenPlan> newFinOpenPlanList = new ArrayList<FinOpenPlan>();

		for (FinOpenPlan finOpenPlan : finOpenPlanList) {
            finOpenPlan.setOpenVdateUn(finOpenPlan.getOpenVdate());
			if (finOpenPlan.getOpenVdateUn().compareTo(susprdfutureopenplangendto.getOpenVdate()) < 1) { // 前者小于等于后者
				continue;
			} else {
                finOpenPlan.setOpenDateStrUn(finOpenPlan.getOpenDateStr());
                finOpenPlan.setOpenDateEndUn(finOpenPlan.getOpenDateEnd());
                finOpenPlan.setOpenMdateUn(finOpenPlan.getOpenMdate());
				finOpenPlan.setCashId(susprdfutureopenplangendto.getCashId()); // 现金流代码
				finOpenPlan.setOpenType(susprdfutureopenplangendto.getOpenType());
				finOpenPlan.setFinprodId(susprdfutureopenplangendto.getFinprodId());
				finOpenPlan.setBranch(0L);
				finOpenPlan.setOpenStatus(DictCodeList.开放状态_开放.getValue());
				newFinOpenPlanList.add(finOpenPlan);
			}

		}
		return newFinOpenPlanList;
	}
	
	
	/**
	 * 生成业绩回补计划规则
	 * @param popuDto
	 * @return
	 */
	public BussFinProductAnapleroticPlanDto executeAnapleroticDto(SusPrdFutureOpenPlanGenDto popuDto){
		BussFinProductAnapleroticPlanDto bussFinProductAnapleroticPlanDto = new BussFinProductAnapleroticPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussFinProductAnapleroticPlanDto, popuDto);
		return bussFinProductAnapleroticPlanDto;
	}
	
	/**
	 * 生成付息计划规则
	 * @param popuDto
	 * @return
	 */
	public BussFinProductPayPlanDto executePayDto(SusPrdFutureOpenPlanGenDto popuDto){
		BussFinProductPayPlanDto bussFinProductPayPlanDto = new BussFinProductPayPlanDto();
		BeanUtil.copyPropertiesIgnoreNull(bussFinProductPayPlanDto, popuDto);
		
		bussFinProductPayPlanDto.setVdate(popuDto.getPayVdate());
		bussFinProductPayPlanDto.setMdate(popuDto.getMdate());

		return bussFinProductPayPlanDto;
	}
	
	/**
	 * 处理更新后的付息计划
	 * @param finCashPlanList
	 * @param popuDto
	 * @return
	 * @throws Exception
	 */
	public List<FinCashPlan> executePayList(List<FinCashPlan> finCashPlanList, SusPrdFutureOpenPlanGenDto popuDto) throws Exception {
		// 经过处理的List<付息计划>
		List<FinCashPlan> newFinCashPlanList = new ArrayList<FinCashPlan>();

		//根据产品代码查询已支付的付息计划的最大计提结束日
		Date maxDealMdate = susPrdFutureOpenPlanGenMapper.queryMaxDealMdate(popuDto.getFinprodId());
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
	
	/**
	 * 两年后的今天的日期
	 */
	public static Date datePlusYears(Date nowDate,int years) {
		Date twoYearLaterDate = new Date();
		try {
			twoYearLaterDate = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(nowDate)).plus(years, ChronoUnit.YEARS).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoYearLaterDate;
	}
	
	/**
	 * 判断字符串不为空
	 */
	public static boolean isNotNull(String str) {
		if(null!=str && !"".equals(str)) {
			return true;
		}else {
			return false;
		}
	}
}
