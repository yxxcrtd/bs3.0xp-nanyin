package com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.DateCalService;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.BussFinProductAnapleroticPlanDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.PlanDateDivisionDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.service.BussFinProductAnapleroticPlanService;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;



/**
 * 产品业绩回补计划实现类
 *
 */
@Service
public class BussFinProductAnapleroticPlanImplService extends BaseService<BussFinProductAnapleroticPlanDto>
        implements BussFinProductAnapleroticPlanService{

	/**
	 * {@linkplain}
	 */
	@Autowired
	private DateCalService dateCalService;
	 /**
     * 生成回补计划
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
	@Override
	public List<FinFloatAccrualSche> genAnapleroticPlanList(BussFinProductAnapleroticPlanDto inputDto) {
		List<FinFloatAccrualSche> resultList = new ArrayList<>();
		if (inputDto == null) {
			return resultList;
		}
		 if(!StringUtil.equals(DictCodeListExt1.频率单位_浮动管理费同频.getValue(),inputDto.getAnapleroticFrequency())){
			 resultList = this.calcProductAnapleroticPlan(inputDto);
	     } 
		 return resultList;
	}
		
    /**
     * 计算生成回补计划
     * 
     * @param inputDto 画面输入dto
     * @return 回补计划计划列表
     */
    public List<FinFloatAccrualSche> calcProductAnapleroticPlan(BussFinProductAnapleroticPlanDto inputDto) {
               
       List<FinFloatAccrualSche> resultList = new ArrayList<FinFloatAccrualSche>();
       Date startDate = inputDto.getFirstAnapleroticCycleBegDate();  
       if(null == startDate){
           return new ArrayList<>();
       }
       // 分割周期
       String cycle = "";
       // 分割周期单位
       String cycleUnit = "";

      
       int year = StringUtil.toInt(CacheUtil.getSysParam(BussinessConstant.MAX_AFTER_NOW_YEAR));
       // 区间开始日
       // 区间结束日
       Date endDate = inputDto.getAnapleroticEndDate();
       if (endDate == null) {
           // 实际到期日为空时，起息日+n年
           endDate = DateUtil.addYear(inputDto.getFirstAnapleroticDate(), year);
           //如果endDate 大于 回补计提结束日 endDate = 回补计提结束日
           if(DateUtil.compareDate(endDate, inputDto.geteAccrDate())>0) {
        	   endDate = inputDto.geteAccrDate();
           }
       }
       inputDto.setmDate(endDate);
       // 交易日历取得
      // List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(startDate,
          // DateUtil.addDay(endDate, 8), inputDto.getCalendarId());
       // 获取节假日 到期日前后顺延30天
       List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(
           DateUtil.addDay(startDate, -30),
           DateUtil.addDay(endDate, 30),
           inputDto.getCalendarId());
       // 首次计提日
       String vdate = DateUtil.format(inputDto.getFirstAnapleroticDate());
       // 实际到期日
       String tmdate = DateUtil.format(inputDto.getmDate());
       // 首次回补周期结束日
       String firstenddate = DateUtil.format(inputDto.getFirstAnapleroticCycleEndDate());
       // 回补规则
       String feedbackrule = inputDto.getAnapleroticFrequency();
       if(StringUtil.equals(feedbackrule,
           DictCodeList.频率_周.getValue())){
           cycle = "1";
           cycleUnit = DictCodeListExt1.频率单位_周.getValue();
       }
       else if (StringUtil.equals(feedbackrule,
           DictCodeList.频率_月.getValue())) {
           cycle = "1";
           cycleUnit = DictCodeListExt1.频率单位_月.getValue();
       }
       else if (StringUtil.equals(feedbackrule,
           DictCodeList.频率_季.getValue())) {
           cycle = "3";
           cycleUnit = DictCodeListExt1.频率单位_月.getValue();
       }
       else if (StringUtil.equals(feedbackrule,
           DictCodeList.频率_半年.getValue())) {
           cycle = "6";
           cycleUnit = DictCodeListExt1.频率单位_月.getValue();
       }
       else if (StringUtil.equals(feedbackrule,
           DictCodeList.频率_年.getValue())) {
           cycle = "1";
           cycleUnit = DictCodeListExt1.频率单位_年.getValue();
       }
       else if (StringUtil.equals(feedbackrule,
           DictCodeList.频率_到期一次.getValue())) {
           cycle = "0";
       }
       
       String period = "1";
       String intPayUnit = feedbackrule;
       // 日期区间分割
       List<PlanDateDivisionDto> dateIntervalDivisionList = this.dateIntervalDivision(startDate,
               inputDto.getmDate(), cycle, cycleUnit, inputDto.getFirstAnapleroticDate());


       // 根据调整规则调整
       for (int count = 0; count < dateIntervalDivisionList.size(); count++ ) {
           PlanDateDivisionDto dto = dateIntervalDivisionList.get(count);
           // add by yangjiaming 20180314 关于产品付费计划的计算方法调整 start
           /*
            * 如果不是最后一条付费计划: 浮动管理费时： 计划中计提截止日 = 区间结束日,计划支付日 = 区间结束日的下一工作日 非浮动管理费时：计划中计提截止日 =
            * 区间结束日,计划支付日 = 区间结束日根据营业日准则调整
            */

           // 计提结束日不会根据营业日准则顺延
           Date startDate1 = dto.getStartDate();
           Date endDate1 = dto.getEndDate();
           Date tradePaymentDay = dto.getEndDate();
           if(count>0 && startDate1.compareTo(inputDto.getFirstAnapleroticDate()) != 0 &&  startDate1.compareTo(startDate) != 0){
        	   startDate1 = DateUtil.parse(
                   AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(startDate1),
                		   inputDto.getCycleTdayRule(), dealCalendarHolidayList));
           }
           if(endDate1.compareTo(startDate) != 0){
               endDate1 = DateUtil.parse(
                   AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(endDate1),
                		   inputDto.getCycleTdayRule(), dealCalendarHolidayList));
           }
           

           // 支付日根据营业日准则进行调整
           tradePaymentDay = DateUtil.parse(
               AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(endDate1),
            		   inputDto.getCycleTdayRule(), dealCalendarHolidayList));

           //update zhangweiqi 2019-3-28 开始时间和结束时间一样的 排除 begin
           if(startDate1.compareTo(endDate1)==0){
               if(count>0&&startDate1.compareTo(resultList.get(count-1).getAccrEndDate())==0){
                   continue;
               }
           }
           //update zhangweiqi 2019-3-28 开始时间和结束时间一样的 排除 end
           FinFloatAccrualSche resultDto = new FinFloatAccrualSche();
           resultDto.setCashId(inputDto.getAnapleroticId());
           // 回补区间开始日
           resultDto.setAccrStrDate(startDate1);
           // 区间结束日
           resultDto.setAccrEndDate(endDate1);
           //调整前结束日
           resultDto.setValDate(endDate1);
           // 回补日
           Date anapleroticDate = resultDto.getAccrEndDate();
          if(StringUtil.equals(BussinessDictCodeList.营业日准则_向后调整.getValue(), inputDto.getCycleTdayRule())){
               // 回补日
               String anapleroticDateStr = AdjustedTradeDayCal.getAdjustedTradeDay(
                   DateUtil.format(anapleroticDate), inputDto.getCycleTdayRule(),
                   dealCalendarHolidayList); 
               if(StringUtil.isNotEmpty(anapleroticDate)){
                   anapleroticDate = DateUtil.parse(anapleroticDateStr);
               }
           }
           // 回补日
           resultDto.setAccrDate(tradePaymentDay);
           resultList.add(resultDto);
       }
       return resultList;
     }
    
    /**
     * 日期区间分割
     *
     * @param paraMap 计算参数map
     * @return 分割后开始日结束日的DTO列表
     * @throws Exception 例外
     * @see
     * @since 1.0
     */
    public List<PlanDateDivisionDto> dateIntervalDivision(Date startDate, Date endDate, String cycle,
                                                      String cycleUnit,Date firstCalEndDate) {
        List<PlanDateDivisionDto> list = new ArrayList<PlanDateDivisionDto>();

        Date startDateTemp = startDate;
        //update zhangweiqi 首次计费结束日 begin
        if(firstCalEndDate!=null){
             PlanDateDivisionDto dto = new PlanDateDivisionDto();
             dto.setStartDate(startDateTemp);
             dto.setEndDate(firstCalEndDate);
             list.add(dto);
             startDateTemp=firstCalEndDate;
             startDate=firstCalEndDate;
        }
       //update zhangweiqi 首次计费结束日 end
        // 如果cycle为‘0’，则表示为到期一次性付费
        //选择产品代码   报系统异常 cycle="" 519行报错 add by wangcheng 20191029 
        if(StringUtil.isNotEmpty(cycle)){
        	if (StringUtil.equals("0", cycle)) {
        		PlanDateDivisionDto dto = new PlanDateDivisionDto();
        		dto.setStartDate(startDate);
        		dto.setEndDate(endDate);
        		list.add(dto);
        	}
        	// 其他情况按周期分割
        	else {
        		int count = 1;
        		if (DateUtil.compareDate(startDateTemp, endDate) < 0) {
        			while (true) {
        				Date calcDate = dateCalService.naturalDateCalc(startDate, cycleUnit,
        						Integer.parseInt(cycle) * count);
        				if (DateUtil.compareDate(calcDate, endDate) < 0) {
        					PlanDateDivisionDto dto = new PlanDateDivisionDto();
        					dto.setStartDate(startDateTemp);
        					dto.setEndDate(calcDate);
        					list.add(dto);
        					startDateTemp = calcDate;
        					
        					count++ ;
        				}
        				else {
        					PlanDateDivisionDto dto = new PlanDateDivisionDto();
        					dto.setStartDate(startDateTemp);
        					dto.setEndDate(endDate);
        					list.add(dto);
        					break;
        				}
        			}
        			
        		}
        		// 按区间结束日升序排序
        		Collections.sort(list);
        	}
        }

        return list;
    }

}
