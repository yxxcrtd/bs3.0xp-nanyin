package com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
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
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service.BussFinProductPayPlanService;
import com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.service.BussFinProductPlanService;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.DateCalService;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 更新计划组件实现类
 * 
 */
@Service
public class BussFinProductPlanImplService extends BaseService<BussNewFinOpenPlanDto> implements BussFinProductPlanService {

   
    @Autowired
    private DateCalService dateCalService;

    /**
     * 更新开放计划
     */
    @Override
    public List<NewFinOpenPlanDto> genOpenPlanList(BussNewFinOpenPlanDto inputDto) {
    	List<NewFinOpenPlanDto> resultList = new ArrayList<>();
    	//
    	// 交易日历取得
    	List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(DateUtil.addDay(inputDto.getOpenDateStr(), -30),
    	DateUtil.addDay(inputDto.getOpenMdate(), 30), inputDto.getCalendarId());
    	NewFinOpenPlanDto resultDto = new NewFinOpenPlanDto();
    	resultDto.setCashId(inputDto.getCashId());
    	resultDto.setOpenType(inputDto.getOpenType());
    	resultDto.setOpenDateEndOld(inputDto.getOpenDateEnd());
    	//判断开放开始日
    	Date openDateStr = inputDto.getOpenDateStr();
    	String openDateStr1 = DateUtil.format(inputDto.getOpenDateStr());
    	boolean isHoliday1 = dateCalService.isDealCalendarHoliday(openDateStr1, inputDto.getCalendarId()); 
    	
    	if(isHoliday1) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getTraRule())) {
    			openDateStr = dateCalService.calculationTradingDay(openDateStr, 1, dealCalendarHolidayList, "1");      	
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getTraRule())){
    			openDateStr = dateCalService.calculationTradingDay1(openDateStr, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setOpenDateStr(openDateStr);
    	resultDto.setOpenDateStrUn(openDateStr);
    	
    	//判断开放结束日
    	Date openDateEnd = inputDto.getOpenDateEnd();
    	String openDateEnd1 = DateUtil.format(inputDto.getOpenDateEnd());
    	boolean isHoliday2 = dateCalService.isDealCalendarHoliday(openDateEnd1, inputDto.getCalendarId()); 
    	if(isHoliday2) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getTraRule())) {
    		openDateEnd = dateCalService.calculationTradingDay(openDateEnd, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getTraRule())){
    		openDateEnd = dateCalService.calculationTradingDay1(openDateEnd, 1, dealCalendarHolidayList, "1"); 
    		}
    	}
    	resultDto.setOpenDateEnd(openDateEnd);
    	resultDto.setOpenDateEndUn(openDateEnd);
    	
    	//判断起息日
    	Date openVdate = inputDto.getOpenVdate();
    	String openVdate1 = DateUtil.format(inputDto.getOpenVdate());
    	boolean isHoliday3 = dateCalService.isDealCalendarHoliday(openVdate1, inputDto.getCalendarId()); 
    	if(isHoliday3) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getTraRule())) {
    		openVdate = dateCalService.calculationTradingDay(openVdate, 1, dealCalendarHolidayList, "1");
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getTraRule())){
    	    openVdate = dateCalService.calculationTradingDay1(openVdate, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setOpenVdate(openVdate);
    	resultDto.setOpenVdateUn(openVdate);
    	
    	//判断到期日
    	Date openMdate = inputDto.getOpenMdate();
    	String openMdate1 = DateUtil.format(inputDto.getOpenMdate());
    	boolean isHoliday4 = dateCalService.isDealCalendarHoliday(openMdate1, inputDto.getCalendarId());
    	if(isHoliday4) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getTraRule())) {
    		openMdate = dateCalService.calculationTradingDay(openMdate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getTraRule())){
    		openMdate = dateCalService.calculationTradingDay1(openMdate, 1, dealCalendarHolidayList, "1");	
    		}
    	}
    	resultDto.setOpenMdate(openMdate);
    	resultDto.setOpenMdateUn(openMdate);
    	
    	//判断净值日
    	Date openNdate = inputDto.getOpenNdate();
    	String openNdate1 = DateUtil.format(inputDto.getOpenNdate());
    	boolean isHoliday5 = dateCalService.isDealCalendarHoliday(openNdate1, inputDto.getCalendarId()); 
    	if(isHoliday5) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getTraRule())) {
    		openNdate = dateCalService.calculationTradingDay(openNdate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getTraRule())){
    		openNdate = dateCalService.calculationTradingDay1(openNdate, 1, dealCalendarHolidayList, "1");  	
    		}
    	}
    	resultDto.setOpenNdate(openNdate);
    	
    	resultList.add(resultDto);
    	
    	return resultList;
    }
    
    /**
     * 更新分红/付息计划
     */
    @Override
    public List<NewFinCashPlanDto> genCashPlanList(BussNewFinCashPlanDto inputDto) {
    	List<NewFinCashPlanDto> resultList = new ArrayList<>();
    	//
    	// 交易日历取得
    	List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(DateUtil.addDay(inputDto.getVdate(), -30),
    	DateUtil.addDay(inputDto.getMdate(), 30), inputDto.getCalendarId());
    	NewFinCashPlanDto resultDto = new NewFinCashPlanDto();
    	resultDto.setCashId(inputDto.getCashId());
    	resultDto.setMdateOld(inputDto.getMdate());
    	//判断未调整的计算开始日
    	Date vdateUnadjust = inputDto.getVdateUnadjust();
    	String vdateUnadjust1 = DateUtil.format(inputDto.getVdateUnadjust());
    	boolean isHoliday1 = dateCalService.isDealCalendarHoliday(vdateUnadjust1, inputDto.getCalendarId()); 
    	
    	if(isHoliday1) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			vdateUnadjust = dateCalService.calculationTradingDay(vdateUnadjust, 1, dealCalendarHolidayList, "1");      	
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			vdateUnadjust = dateCalService.calculationTradingDay1(vdateUnadjust, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setVdateUnadjust(vdateUnadjust);
    	
    	//判断未调整的计算结束日
    	Date mdateUnadjust = inputDto.getMdateUnadjust();
    	String mdateUnadjust1 = DateUtil.format(inputDto.getMdateUnadjust());
    	boolean isHoliday2 = dateCalService.isDealCalendarHoliday(mdateUnadjust1, inputDto.getCalendarId()); 
    	if(isHoliday2) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			mdateUnadjust = dateCalService.calculationTradingDay(mdateUnadjust, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			mdateUnadjust = dateCalService.calculationTradingDay1(mdateUnadjust, 1, dealCalendarHolidayList, "1"); 
    		}
    	}
    	resultDto.setMdateUnadjust(mdateUnadjust);
    	
    	//判断未调整的计划支付日
    	Date payDateUnadjust = inputDto.getPayDateUnadjust();
    	String payDateUnadjust1 = DateUtil.format(inputDto.getPayDateUnadjust());
    	boolean isHoliday3 = dateCalService.isDealCalendarHoliday(payDateUnadjust1, inputDto.getCalendarId()); 
    	if(isHoliday3) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getPayTdayRule())) {
    			payDateUnadjust = dateCalService.calculationTradingDay(payDateUnadjust, 1, dealCalendarHolidayList, "1");
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getPayTdayRule())){
    			payDateUnadjust = dateCalService.calculationTradingDay1(payDateUnadjust, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setPayDateUnadjust(payDateUnadjust);
    	
    	//判断计息区间开始日
    	Date vdate = inputDto.getVdate();
    	String vdate1 = DateUtil.format(inputDto.getVdate());
    	boolean isHoliday4 = dateCalService.isDealCalendarHoliday(vdate1, inputDto.getCalendarId());
    	if(isHoliday4) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			vdate = dateCalService.calculationTradingDay(vdate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			vdate = dateCalService.calculationTradingDay1(vdate, 1, dealCalendarHolidayList, "1");	
    		}
    	}
    	resultDto.setVdate(vdate);
    	
    	//判断计息区间结束日
    	Date mdate = inputDto.getMdate();
    	String mdate1 = DateUtil.format(inputDto.getMdate());
    	boolean isHoliday5 = dateCalService.isDealCalendarHoliday(mdate1, inputDto.getCalendarId()); 
    	if(isHoliday5) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			mdate = dateCalService.calculationTradingDay(mdate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			mdate = dateCalService.calculationTradingDay1(mdate, 1, dealCalendarHolidayList, "1");  	
    		}
    	}
    	resultDto.setMdate(mdate);
    	
    	//判断支付日
    	Date payDate = inputDto.getPayDate();
    	String payDate1 = DateUtil.format(inputDto.getPayDate());
    	boolean isHoliday6 = dateCalService.isDealCalendarHoliday(payDate1, inputDto.getCalendarId()); 
    	if(isHoliday6) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getPayTdayRule())) {
    			payDate = dateCalService.calculationTradingDay(payDate, 1, dealCalendarHolidayList, "1");
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getPayTdayRule())){
    			payDate = dateCalService.calculationTradingDay1(payDate, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setPayDate(payDate);
    	   	
    	resultList.add(resultDto);
    	
    	return resultList;
    }
    
    /**
     * 更新付费计划
     */
    @Override
    public List<NewFinPayPlanDto> genPayPlanList(BussNewFinPayPlanDto inputDto) {
    	List<NewFinPayPlanDto> resultList = new ArrayList<>();
    	//
    	// 交易日历取得
    	List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(DateUtil.addDay(inputDto.getVdate(), -30),
    	DateUtil.addDay(inputDto.getMdate(), 30), inputDto.getCalendarId());
    	NewFinPayPlanDto resultDto = new NewFinPayPlanDto();
    	resultDto.setCashId(inputDto.getCashId());
    	resultDto.setMdateOld(inputDto.getMdate());
    	//判断未调整的计算开始日
    	Date vdateUnadjust = inputDto.getVdateUnadjust();
    	String vdateUnadjust1 = DateUtil.format(inputDto.getVdateUnadjust());
    	boolean isHoliday1 = dateCalService.isDealCalendarHoliday(vdateUnadjust1, inputDto.getCalendarId()); 
    	
    	if(isHoliday1) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			vdateUnadjust = dateCalService.calculationTradingDay(vdateUnadjust, 1, dealCalendarHolidayList, "1");      	
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			vdateUnadjust = dateCalService.calculationTradingDay1(vdateUnadjust, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setVdateUnadjust(vdateUnadjust);
    	
    	//判断未调整的计算结束日
    	Date mdateUnadjust = inputDto.getMdateUnadjust();
    	String mdateUnadjust1 = DateUtil.format(inputDto.getMdateUnadjust());
    	boolean isHoliday2 = dateCalService.isDealCalendarHoliday(mdateUnadjust1, inputDto.getCalendarId()); 
    	if(isHoliday2) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			mdateUnadjust = dateCalService.calculationTradingDay(mdateUnadjust, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			mdateUnadjust = dateCalService.calculationTradingDay1(mdateUnadjust, 1, dealCalendarHolidayList, "1"); 
    		}
    	}
    	resultDto.setMdateUnadjust(mdateUnadjust);
    	
    	//判断未调整的计划支付日
    	Date payDateUnadjust = inputDto.getPayDateUnadjust();
    	String payDateUnadjust1 = DateUtil.format(inputDto.getPayDateUnadjust());
    	boolean isHoliday3 = dateCalService.isDealCalendarHoliday(payDateUnadjust1, inputDto.getCalendarId()); 
    	if(isHoliday3) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getPayTdayRule())) {
    			payDateUnadjust = dateCalService.calculationTradingDay(payDateUnadjust, 1, dealCalendarHolidayList, "1");
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getPayTdayRule())){
    			payDateUnadjust = dateCalService.calculationTradingDay1(payDateUnadjust, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setPayDateUnadjust(payDateUnadjust);
    	
    	//判断付费开始日
    	Date vdate = inputDto.getVdate();
    	String vdate1 = DateUtil.format(inputDto.getVdate());
    	boolean isHoliday4 = dateCalService.isDealCalendarHoliday(vdate1, inputDto.getCalendarId());
    	if(isHoliday4) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			vdate = dateCalService.calculationTradingDay(vdate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			vdate = dateCalService.calculationTradingDay1(vdate, 1, dealCalendarHolidayList, "1");	
    		}
    	}
    	resultDto.setVdate(vdate);
    	
    	//判断付费结束日
    	Date mdate = inputDto.getMdate();
    	String mdate1 = DateUtil.format(inputDto.getMdate());
    	boolean isHoliday5 = dateCalService.isDealCalendarHoliday(mdate1, inputDto.getCalendarId()); 
    	if(isHoliday5) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getEndTdayRule())) {
    			mdate = dateCalService.calculationTradingDay(mdate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getEndTdayRule())){
    			mdate = dateCalService.calculationTradingDay1(mdate, 1, dealCalendarHolidayList, "1");  	
    		}
    	}
    	resultDto.setMdate(mdate);
    	
    	//判断支付日
    	Date payDate = inputDto.getPayDate();
    	String payDate1 = DateUtil.format(inputDto.getPayDate());
    	boolean isHoliday6 = dateCalService.isDealCalendarHoliday(payDate1, inputDto.getCalendarId()); 
    	if(isHoliday6) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getPayTdayRule())) {
    			payDate = dateCalService.calculationTradingDay(payDate, 1, dealCalendarHolidayList, "1");
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getPayTdayRule())){
    			payDate = dateCalService.calculationTradingDay1(payDate, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setPayDate(payDate);
    	   	
    	resultList.add(resultDto);
    	
    	return resultList;
    }
    
    /**
     * 更新业绩报酬计提计划
     */
    @Override
    public List<NewFinFloatPlanDto> genFloatPlanList(BussNewFinFloatPlanDto inputDto) {
    	List<NewFinFloatPlanDto> resultList = new ArrayList<>();
    	//
    	// 交易日历取得
    	List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(DateUtil.addDay(inputDto.getAccrStrDate(), -30),
    	DateUtil.addDay(inputDto.getAccrEndDate(), 30), inputDto.getCalendarId());
    	NewFinFloatPlanDto resultDto = new NewFinFloatPlanDto();
    	resultDto.setCashId(inputDto.getCashId());
    	resultDto.setAccrDateOld(inputDto.getAccrDate());
    	//判断计提日
    	Date accrDate = inputDto.getAccrDate();
    	String accrDate1 = DateUtil.format(inputDto.getAccrDate());
    	boolean isHoliday1 = dateCalService.isDealCalendarHoliday(accrDate1, inputDto.getCalendarId()); 
    	
    	if(isHoliday1) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			accrDate = dateCalService.calculationTradingDay(accrDate, 1, dealCalendarHolidayList, "1");      	
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			accrDate = dateCalService.calculationTradingDay1(accrDate, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setAccrDate(accrDate);
    	
    	//判断区间开始日
    	Date accrStrDate = inputDto.getAccrStrDate();
    	String accrStrDate1 = DateUtil.format(inputDto.getAccrStrDate());
    	boolean isHoliday2 = dateCalService.isDealCalendarHoliday(accrStrDate1, inputDto.getCalendarId()); 
    	if(isHoliday2) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			accrStrDate = dateCalService.calculationTradingDay(accrStrDate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			accrStrDate = dateCalService.calculationTradingDay1(accrStrDate, 1, dealCalendarHolidayList, "1"); 
    		}
    	}
    	resultDto.setAccrStrDate(accrStrDate);
    	
    	//判断区间结束日
    	Date accrEndDate = inputDto.getAccrEndDate();
    	String accrEndDate1 = DateUtil.format(inputDto.getAccrEndDate());
    	boolean isHoliday3 = dateCalService.isDealCalendarHoliday(accrEndDate1, inputDto.getCalendarId()); 
    	if(isHoliday3) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			accrEndDate = dateCalService.calculationTradingDay(accrEndDate, 1, dealCalendarHolidayList, "1");
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			accrEndDate = dateCalService.calculationTradingDay1(accrEndDate, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setAccrEndDate(accrEndDate);
    	
    	//判断调整前结束日
    	Date valDate = inputDto.getValDate();
    	String valDate1 = DateUtil.format(inputDto.getValDate());
    	boolean isHoliday4 = dateCalService.isDealCalendarHoliday(valDate1, inputDto.getCalendarId());
    	if(isHoliday4) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			valDate = dateCalService.calculationTradingDay(valDate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			valDate = dateCalService.calculationTradingDay1(valDate, 1, dealCalendarHolidayList, "1");	
    		}
    	}
    	resultDto.setValDate(valDate);   	    	
    	
    	resultList.add(resultDto);
    	
    	return resultList;
    }
    
    /**
     * 更新回补计划
     */
    @Override
    public List<NewFinAnapleroticPlanDto> genAnapleroticPlanList(BussNewFinAnapleroticPlanDto inputDto) {
    	List<NewFinAnapleroticPlanDto> resultList = new ArrayList<>();
    	//
    	// 交易日历取得
    	List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(DateUtil.addDay(inputDto.getAccrStrDate(), -30),
    	DateUtil.addDay(inputDto.getAccrEndDate(), 30), inputDto.getCalendarId());
    	NewFinAnapleroticPlanDto resultDto = new NewFinAnapleroticPlanDto();
    	resultDto.setCashId(inputDto.getCashId());
    	resultDto.setAccrDateOld(inputDto.getAccrDate());
    	//判断回补日
    	Date accrDate = inputDto.getAccrDate();
    	String accrDate1 = DateUtil.format(inputDto.getAccrDate());
    	boolean isHoliday1 = dateCalService.isDealCalendarHoliday(accrDate1, inputDto.getCalendarId()); 
    	
    	if(isHoliday1) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			accrDate = dateCalService.calculationTradingDay(accrDate, 1, dealCalendarHolidayList, "1");      	
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			accrDate = dateCalService.calculationTradingDay1(accrDate, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setAccrDate(accrDate);
    	
    	//判断回补周期开始日
    	Date accrStrDate = inputDto.getAccrStrDate();
    	String accrStrDate1 = DateUtil.format(inputDto.getAccrStrDate());
    	boolean isHoliday2 = dateCalService.isDealCalendarHoliday(accrStrDate1, inputDto.getCalendarId()); 
    	if(isHoliday2) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			accrStrDate = dateCalService.calculationTradingDay(accrStrDate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			accrStrDate = dateCalService.calculationTradingDay1(accrStrDate, 1, dealCalendarHolidayList, "1"); 
    		}
    	}
    	resultDto.setAccrStrDate(accrStrDate);
    	
    	//判断回补周期结束日
    	Date accrEndDate = inputDto.getAccrEndDate();
    	String accrEndDate1 = DateUtil.format(inputDto.getAccrEndDate());
    	boolean isHoliday3 = dateCalService.isDealCalendarHoliday(accrEndDate1, inputDto.getCalendarId()); 
    	if(isHoliday3) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			accrEndDate = dateCalService.calculationTradingDay(accrEndDate, 1, dealCalendarHolidayList, "1");
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			accrEndDate = dateCalService.calculationTradingDay1(accrEndDate, 1, dealCalendarHolidayList, "1");
    		}
    	}
    	resultDto.setAccrEndDate(accrEndDate);
    	
    	//判断调整前结束日
    	Date valDate = inputDto.getValDate();
    	String valDate1 = DateUtil.format(inputDto.getValDate());
    	boolean isHoliday4 = dateCalService.isDealCalendarHoliday(valDate1, inputDto.getCalendarId());
    	if(isHoliday4) {
    		if(StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), inputDto.getAccrTadyRule())) {
    			valDate = dateCalService.calculationTradingDay(valDate, 1, dealCalendarHolidayList, "1");  
    		}else if(StringUtil.equals(DictCodeList.营业日准则_向前调整.getValue(), inputDto.getAccrTadyRule())){
    			valDate = dateCalService.calculationTradingDay1(valDate, 1, dealCalendarHolidayList, "1");	
    		}
    	}
    	resultDto.setValDate(valDate);   	    	
    	
    	resultList.add(resultDto);
    	
    	return resultList;
    }
    
    

}
