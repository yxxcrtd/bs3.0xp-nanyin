/*
 * 文件名：ValBokClawbackInfoQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：李俊
 * 修改时间：2019年4月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.anaplerotic.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.dto.DateDivisionDto;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticInfoDto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticPlanDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 回补信息查询
 * 
 * @author 李俊
 * @version 1.0 2019年4月22日
 * @see FinProductAnapleroticInfo002Service
 * @since 1.0
 */
@Service
public class FinProductAnapleroticInfo002Service extends BaseService<FinProductAnapleroticInfoDto> implements BaseProcessService<FinProductAnapleroticInfoDto>{

    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Service
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
    public List<FinProductAnapleroticPlanDto> getProductAnapleroticPlanData(FinProductAnapleroticInfoDto Dto){
        if(!StringUtil.equals(DictCodeListExt1.频率单位_浮动管理费同频.getValue(),Dto.getAnapleroticFrequency())){
            return this.calcProductAnapleroticPlan(Dto);
        } 
        return null;
    }
    
    /**
     * 保存业绩回补信息
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    public void insertInfo(FinProductAnapleroticInfoDto Dto){
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/insertAnapleroticInfo", Dto,
            Integer.class);
    }
    
    /**
     * 产品代码联动查询生效日，首次回补日，回补结束日
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinProductAnapleroticInfoDto queryAnapAndProdInfo(String finprodId,String dealMode){
        FinProductAnapleroticInfoDto anapleroticInfoDto = restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/queryAnapAndProdInfo?finprodId=" + finprodId + "&dealMode=" + dealMode,
                FinProductAnapleroticInfoDto.class);

        anapleroticInfoDto.setOpenMode( getOpenMode(finprodId) );
        return anapleroticInfoDto;
    }
    /**
     * 产品代码联动查询生效日，首次回补日，回补结束日
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinProductAnapleroticInfoDto queryAnapAndProdStyleInfo(String finprodId, String dealMode){
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/queryAnapAndProdStyleInfo?finprodId="+finprodId+"&dealMode="+dealMode,
            FinProductAnapleroticInfoDto.class);
    }
    /**
     * 产品代码查询回补现金流代码
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByFinprodId(String finprodId){
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/queryAnapleroticInfoByFinprodId?finprodId="+finprodId,
            FinProductAnapleroticInfoDto.class);
    }
    
    public AjaxResult deleteByPrimaryKey(Map<String, Object> map){
        restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinProductAnaplerotic/deleteByPrimaryKey",map,Integer.class);
         return new AjaxResult();
     }
    /**
     * 修改业绩回补信息
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    public void updateInfo(FinProductAnapleroticInfoDto Dto){
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/updateAnapleroticInfo", Dto,
            Integer.class);
    }
    
    /**
     * 回补信息查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinProductAnapleroticInfoDto queryAnapleroticInfoById(String anapleroticId ,String effDateStr){
        Map<String,Object> param = new HashMap<>();
        param.put("anapleroticId", anapleroticId);
        param.put("effDate", effDateStr);
        List<FinProductAnapleroticInfoDto> list = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/queryAnapleroticInfoById", param,
            FinProductAnapleroticInfoDto.class);
        if(StringUtil.isEmptyList(list)){
            return new FinProductAnapleroticInfoDto();
        }
        return list.get(0).getFinProductAnapleroticInfoDto();
    }
    /**
     * 计算生成回补计划
     * 
     * @param inputDto 画面输入dto
     * @return 回补计划计划列表
     */
    public List<FinProductAnapleroticPlanDto> calcProductAnapleroticPlan(FinProductAnapleroticInfoDto inputDto) {
        
 /*       Date startDate = inputDto.getFirstAnapleroticCycleEndDate();
        if(null == startDate){
            return new ArrayList<>();
        }
        // 定义需要返回的list
        List<FinProductAnapleroticPlanDto> resultList = new ArrayList<>();
        int year = StringUtil.toInt(CacheUtil.getSysParam(BussinessConstant.MAX_AFTER_NOW_YEAR));
        // 区间开始日
        // 区间结束日
        Date endDate = inputDto.getAnapleroticEndDate();
        if (endDate == null) {
            // 实际到期日为空时，起息日+n年
            endDate = DateUtil.addYear(inputDto.getFirstAnapleroticDate(), year);
        }
        inputDto.setmDate(endDate);
        // 交易日历取得
        List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(startDate,
            DateUtil.addDay(endDate, 8), inputDto.getCalendarId());
        // 首次计提日
        String vdate = DateUtil.format(inputDto.getFirstAnapleroticDate());
        // 实际到期日
        String tmdate = DateUtil.format(inputDto.getmDate());
        // 首次回补周期结束日
        String firstenddate = DateUtil.format(inputDto.getFirstAnapleroticCycleEndDate());
        // 回补规则
        String feedbackrule = inputDto.getAnapleroticFrequency();
        String period = "1";
        String intPayUnit = feedbackrule;
        if (DictCodeList.频率单位_季.getValue().equals(feedbackrule)) {// 季
            period = "3";
            intPayUnit = DictCodeList.频率单位_月.getValue();
        }
        if (DictCodeList.频率单位_半年.getValue().equals(feedbackrule)) {// 半年
            period = "6";
            intPayUnit = DictCodeList.频率单位_月.getValue();
        }
        if (DictCodeList.频率单位_到期一次.getValue().equals(feedbackrule)) {// 到期
            period = DateUtil.getBetweenDayNumber(vdate, tmdate)+"";
            intPayUnit = DictCodeList.频率单位_日.getValue();
        }
        //日期区间分割
        List<Date> tempDivisionList = dateCalService.dateIntervalNodeList(startDate,
            inputDto.getmDate(), BussinessDictCodeList.营业日准则_向后调整.getValue(), period,
            intPayUnit);
        
        if (DateUtil.compareDate(tmdate, firstenddate) > 0) {
            FinProductAnapleroticPlanDto dt = new FinProductAnapleroticPlanDto();
            // 回补区间开始日
            dt.setAnapleroticCycleStartDate(inputDto.getFirstAnapleroticDate());
            // 回补区间结束日
            dt.setAnapleroticCycleEndDate(startDate);
            // 回补日
            if (StringUtil.equals(BussinessDictCodeList.营业日准则_向后调整.getValue(),
                inputDto.getCycleTdayRule())) {
                String anapleroticDateStr = AdjustedTradeDayCal.getAdjustedTradeDay(
                    DateUtil.format(startDate), inputDto.getCycleTdayRule(),
                    dealCalendarHolidayList);
                dt.setAnapleroticDate(DateUtil.parse(anapleroticDateStr));
            }
            else {
                dt.setAnapleroticDate(startDate);
            }
            resultList.add(dt);
            for (int i = 0; i < tempDivisionList.size(); i++) {
                DateUtil.format(tempDivisionList.get(i));
                if (i != tempDivisionList.size() - 1) {
                    FinProductAnapleroticPlanDto dto = new FinProductAnapleroticPlanDto();
                    // 回补区间开始日
                    dto.setAnapleroticCycleStartDate(tempDivisionList.get(i));
                    // 回补区间结束日
                    dto.setAnapleroticCycleEndDate(tempDivisionList.get(i+1));
                    // 回补日
                    Date anapleroticDate = tempDivisionList.get(i+1);
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
                    dto.setAnapleroticDate(anapleroticDate);
                    resultList.add(dto);
                }
            }
        } else {// 新增,当首次回补周期结束日大于tmdate，只出一条
            FinProductAnapleroticPlanDto dto = new FinProductAnapleroticPlanDto();
         // 回补区间开始日
            dto.setAnapleroticCycleStartDate(inputDto.getFirstAnapleroticDate());
            // 区间结束日
            dto.setAnapleroticCycleEndDate(DateUtil.parse(tmdate));
            // 回补日
            Date anapleroticDate = dto.getAnapleroticCycleEndDate();
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
            dto.setAnapleroticDate(anapleroticDate);
            resultList.add(dto);
        }
        return resultList;
        */
        

        // 上记以外的情况，代表是画面上点了【预览】按钮触发的事件，需要重新计算付费计划
       List<FinProductAnapleroticPlanDto> resultList = new ArrayList<FinProductAnapleroticPlanDto>();
       Date startDate = inputDto.getFirstAnapleroticCycleBegDate();  //getFirstAnapleroticDate();//    .getFirstAnapleroticCycleEndDate();
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
       List<DateDivisionDto> dateIntervalDivisionList = this.dateIntervalDivision(startDate,
               inputDto.getmDate(), cycle, cycleUnit, inputDto.getFirstAnapleroticDate());
           

  
       //List<DateDivisionDto> dateIntervalDivisionList = this.dateIntervalDivision(
            //   finProductFloatFeeDto.getVdate(), finProductFloatFeeDto.getMdate(), cycle, cycleUnit,finProductFloatFeeDto.getFirstPayDate());//zhangwweiqi 首次付费结束日

       // 根据调整规则调整
       for (int count = 0; count < dateIntervalDivisionList.size(); count++ ) {
           DateDivisionDto dto = dateIntervalDivisionList.get(count);
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
               if(count>0&&startDate1.compareTo(resultList.get(count-1).getAnapleroticCycleEndDate())==0){
                   continue;
               }
           }
           //update zhangweiqi 2019-3-28 开始时间和结束时间一样的 排除 end
           FinProductAnapleroticPlanDto resultDto = new FinProductAnapleroticPlanDto();
           // 回补区间开始日
           resultDto.setAnapleroticCycleStartDate(startDate1);
           // 区间结束日
           resultDto.setAnapleroticCycleEndDate(endDate1);
           // 回补日
           Date anapleroticDate = resultDto.getAnapleroticCycleEndDate();
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
           resultDto.setAnapleroticDate(tradePaymentDay);
          /* resultDto.setCashNum((long)(count + 1));
           resultDto.setCashType(DictCodeListExt1.现金流一级类型_费用.getValue());
           resultDto.setVdate(startDate1);
           resultDto.setMdate(endDate1);
           resultDto.setPayDate(tradePaymentDay);
           resultDto.setVdateUnadjust(startDate1);
           resultDto.setMdateUnadjust(endDate1);
           resultDto.setPayDateUnadjust(tradePaymentDay);*/
           resultList.add(resultDto);
       }
       return resultList;
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public String checkForSubmit(FlowEntity arg0, boolean arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductAnapleroticInfoDto entity, OperationType type) {
        if(type == OperationType.MODIFY){
            this.updateInfo(entity);
        }else if(type == OperationType.NEW){
            this.insertInfo(entity);
        } else{
            // TODO Auto-generated method stub
            Map<String,Object> map = new HashMap<>();
            map.put("anapleroticId", entity.getAnapleroticId());
            map.put("effDateStr", entity.getEffDateStr());
            map.put("finprodId", entity.getFinprodId());
            this.deleteByPrimaryKey(map);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDelete(String arg0) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doInsert(FinProductAnapleroticInfoDto arg0, OperationType arg1) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinProductAnapleroticInfoDto arg0) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark arg0, String arg1)
        throws WorkFlowException {
        // TODO Auto-generated method stub
        return null;
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
    public List<DateDivisionDto> dateIntervalDivision(Date startDate, Date endDate, String cycle,
                                                      String cycleUnit,Date firstCalEndDate) {
        List<DateDivisionDto> list = new ArrayList<DateDivisionDto>();

        Date startDateTemp = startDate;
        //update zhangweiqi 首次计费结束日 begin
        if(firstCalEndDate!=null){
             DateDivisionDto dto = new DateDivisionDto();
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
        		DateDivisionDto dto = new DateDivisionDto();
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
        					DateDivisionDto dto = new DateDivisionDto();
        					dto.setStartDate(startDateTemp);
        					dto.setEndDate(calcDate);
        					list.add(dto);
        					startDateTemp = calcDate;
        					
        					count++ ;
        				}
        				else {
        					DateDivisionDto dto = new DateDivisionDto();
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


    //判断是否高频开放
    public String getOpenMode(String finprodId) {
        return  restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                        + "/FinProductBuild/getOpenMode?finprodId="
                        + finprodId,
                String.class);
    }
	
	/**
     * 产品代码查询产品开放计划
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinOpenPlan> queryOpenPlanListByFinprodId(String finprodId){
        return restTemplate.getForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductOpenPlan/queryOpenPlanListByFinprodId?finprodId="+finprodId,
                FinOpenPlan.class);
    }
    
    /**
     * 
     * <获得产品回补计划>
     * <功能详细描述>
     * @param anapleroticId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinProductAnapleroticPlanDto> selectAnapleroticPlanByFinprodId(String finprodId){
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAnaplerotic/selectAnapleroticPlanByFinprodId?finprodId=" + finprodId, FinProductAnapleroticPlanDto.class);
    }
}
