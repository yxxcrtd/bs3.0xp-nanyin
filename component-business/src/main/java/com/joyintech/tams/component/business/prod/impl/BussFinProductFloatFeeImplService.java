/*
 * 文件名：BussFinProductFloatFeeImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleKey;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.dto.DateDivisionDto;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.component.business.prod.dto.FinProductFloatFeeDto;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 浮动管理费实现类
 *
 * @author zhangliang
 * @version 1.0 2018年10月11日
 * @see BussFinProductFloatFeeImplService
 * @since 1.0
 */
@Service
public class BussFinProductFloatFeeImplService extends BaseService<MstFeeinfo> implements BussFinProductFloatFeeService {

    /**
     * bussRepFundInfoService
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Service
     */
    @Autowired
    private DateCalService dateCalService;

    @Override
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map) {
        map.put("floatFeeTypeFlag", DictCodeList.是否_是.getValue());
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductFeeInfo/queryFeeInfoList", map,
            SelectOptionDto.class);
    }

    @Override
    public MstFeeinfo queryMstFeeinfoByFeeId(String feeId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductFeeInfo/queryMstFeeinfoByFeeId?feeId="
                                         + feeId,
            MstFeeinfo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String cashId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductFeeInfo/delete?cashId=" + cashId,
            Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteByPrimaryKey(FinFloatCalcRuleKey key) {
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductFeeInfo/deleteByPrimaryKey", key,
            Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussFinProductFloatFeeDto productFloatFeeInfoQueryByWflId(String workflowId,
                                                                     String entityName) {
        // 获取业务entity
        return mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId, entityName,
            BussFinProductFloatFeeDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinProductFloatFeeDto> queryFloatFeeDetailByFinprodId(String finprodId,
                                                                       String effDate) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductFloatFee/queryFloatFeeDetailByFinprodId?finprodId="
                                       + StringUtil.defaultString(finprodId) + "&effDate="
                                       + StringUtil.defaultString(effDate),
                                       FinProductFloatFeeDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussFinProductFloatFeeDto queryFloatFeeInfoByFinprodId(String finprodId,
                                                                  String effDate) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductFloatFee/queryFloatFeeInfoByFinprodId?finprodId="
                                         + StringUtil.defaultString(finprodId) + "&effDate="
                                         + StringUtil.defaultString(effDate),
            BussFinProductFloatFeeDto.class);
    }

    @Override
    public List<FinCashPlan> generateFloatFeePlan(BussFinProductFloatFeeDto finProductFloatFeeDto) {
         // 上记以外的情况，代表是画面上点了【预览】按钮触发的事件，需要重新计算付费计划
        List<FinCashPlan> resultList = new ArrayList<FinCashPlan>();

        // 分割周期
        String cycle = "";
        // 分割周期单位
        String cycleUnit = "";

        // 根据付费规则判断分割周期、分割周期单位
     // 一体化对应etliu start
//        if (StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
//            DictCodeList.付费规则_月.getValue())) {
//            cycle = "1";
//            cycleUnit = DictCodeListExt1.周期单位_月.getValue();
//        }
//        else if (StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
//            DictCodeList.付费规则_季.getValue())) {
//            cycle = "3";
//            cycleUnit = DictCodeListExt1.周期单位_月.getValue();
//        }
//        else if (StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
//            DictCodeList.付费规则_半年.getValue())) {
//            cycle = "6";
//            cycleUnit = DictCodeListExt1.周期单位_月.getValue();
//        }
//        else if (StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
//            DictCodeList.付费规则_年.getValue())) {
//            cycle = "1";
//            cycleUnit = DictCodeListExt1.周期单位_年.getValue();
//        }
//        else if (StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
//            DictCodeList.付费规则_到期.getValue())) {
//            cycle = "0";
//        }
        if(StringUtil.equals(finProductFloatFeeDto.getPayCycle(),
            DictCodeList.频率_周.getValue())){
            cycle = "1";
            cycleUnit = DictCodeListExt1.周期单位_周.getValue();
        }
        else if (StringUtil.equals(finProductFloatFeeDto.getPayCycle(),
            DictCodeList.频率_月.getValue())) {
            cycle = "1";
            cycleUnit = DictCodeListExt1.周期单位_月.getValue();
        }
        else if (StringUtil.equals(finProductFloatFeeDto.getPayCycle(),
            DictCodeList.频率_季.getValue())) {
            cycle = "3";
            cycleUnit = DictCodeListExt1.周期单位_月.getValue();
        }
        else if (StringUtil.equals(finProductFloatFeeDto.getPayCycle(),
            DictCodeList.频率_半年.getValue())) {
            cycle = "6";
            cycleUnit = DictCodeListExt1.周期单位_月.getValue();
        }
        else if (StringUtil.equals(finProductFloatFeeDto.getPayCycle(),
            DictCodeList.频率_年.getValue())) {
            cycle = "1";
            cycleUnit = DictCodeListExt1.周期单位_年.getValue();
        }
        else if (StringUtil.equals(finProductFloatFeeDto.getPayCycle(),
            DictCodeList.频率_到期一次.getValue())) {
            cycle = "0";
        }
        //add by zlp 20190318 start cycle为空  浮动管理费付费规则为不规则的情况不生成费用计划
        if(StringUtil.isNotEmpty(cycle)) {
        //add by zlp 20190318 end

     // 一体化对应etliu end

//        if (StringUtil.isNotEmpty(finProductFloatFeeDto.getProvisionFirstDay())) {
//            finProductFloatFeeDto.setVdate(finProductFloatFeeDto.getProvisionFirstDay());
//        }

        // 获取节假日 到期日前后顺延30天
        List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(
            DateUtil.addDay(finProductFloatFeeDto.getVdate(), -30),
            DateUtil.addDay(finProductFloatFeeDto.getMdate(), 30),
            finProductFloatFeeDto.getDealCalId());
        
        //因为计提结束日也计提,划分区间时需要 计提结束日+1 add by chendijun 2019/10/28
        Date adjustEAccrDate = DateUtil.parse(AdjustedTradeDayCal.getAfterOrBeforeDay(
            DateUtil.format(finProductFloatFeeDto.geteAccrDate()),1,true));
        // 日期区间分割
        List<DateDivisionDto> dateIntervalDivisionList = this.dateIntervalDivision(
                finProductFloatFeeDto.getVdate(), adjustEAccrDate, cycle, cycleUnit,finProductFloatFeeDto.getFirstPayDate());//zhangwweiqi 首次付费结束日

        // 根据调整规则调整
        for (int count = 0; count < dateIntervalDivisionList.size(); count++ ) {
            DateDivisionDto dto = dateIntervalDivisionList.get(count);
            // add by yangjiaming 20180314 关于产品付费计划的计算方法调整 start
            /*
             * 如果不是最后一条付费计划: 浮动管理费时： 计划中计提截止日 = 区间结束日,计划支付日 = 区间结束日的下一工作日 非浮动管理费时：计划中计提截止日 =
             * 区间结束日,计划支付日 = 区间结束日根据营业日准则调整
             */

            // 计提结束日不会根据营业日准则顺延
            Date startDate = dto.getStartDate();
            Date endDate = dto.getEndDate();
            Date tradePaymentDay = dto.getEndDate();
            if(count>0 && startDate.compareTo(finProductFloatFeeDto.getVdate()) != 0 &&  startDate.compareTo(finProductFloatFeeDto.getFirstPayDate()) != 0){
                startDate = DateUtil.parse(
                    AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(startDate),
                            finProductFloatFeeDto.getEndTdayRule(), dealCalendarHolidayList));
            }
            if(endDate.compareTo(finProductFloatFeeDto.getFirstPayDate()) != 0){
                endDate = DateUtil.parse(
                    AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(endDate),
                            finProductFloatFeeDto.getEndTdayRule(), dealCalendarHolidayList));
            }
            

            // 支付日根据营业日准则进行调整
            tradePaymentDay = DateUtil.parse(
                AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(endDate),
                        finProductFloatFeeDto.getPayTdayRule(), dealCalendarHolidayList));

            //update zhangweiqi 2019-3-28 开始时间和结束时间一样的 排除 begin
            if(startDate.compareTo(endDate)==0){
                if(count>0&&startDate.compareTo(resultList.get(count-1).getMdate())==0){
                    continue;
                }
            }
            //update zhangweiqi 2019-3-28 开始时间和结束时间一样的 排除 end
            FinCashPlan resultDto = new FinCashPlan();
            resultDto.setCashNum((long)(count + 1));
            resultDto.setCashType(DictCodeListExt1.现金流一级类型_费用.getValue());
            resultDto.setVdate(startDate);
            resultDto.setMdate(endDate);
            resultDto.setPayDate(tradePaymentDay);
            resultDto.setVdateUnadjust(startDate);
            resultDto.setMdateUnadjust(endDate);
            resultDto.setPayDateUnadjust(tradePaymentDay);
            resultList.add(resultDto);
        }
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

       return list;
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussFinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId(String finprodId, String effDate) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinProductFloatFee/queryLayerFloatFeeInfoByFinprodId?finprodId="
            + StringUtil.defaultString(finprodId) + "&effDate="
            + StringUtil.defaultString(effDate),
            BussFinProductFloatFeeDto.class);
    }
}
