/*
 * 文件名：FinAssetRateAdj001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年10月11日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.component.asset.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.component.asset.dto.FinAssetRateAdjDto;
import com.joyintech.tams.valuation.component.asset.dto.FinCashPayRuleDto;
import com.joyintech.tams.valuation.component.common.calculator.InterestScheduleCalReset;
import com.joyintech.tams.valuation.component.common.calculator.RepayScheduleCal;
import com.joyintech.tams.valuation.component.common.calculator.WeightedInterestRateCal;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDetailDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 利率调整 〈功能详细描述〉
 *
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see FinAssetRateAdj001Service
 * @since 1.0
 */
@Service
public class FinAssetRateAdj001Service extends CommonExportService<FinAssetRateAdjDto> implements BaseProcessService<FinAssetRateAdjDto> {

    @Autowired
    private BaseCommonService baseCommonService;

//    @Autowired
//    private RateAdjService rateAdjService;

    /**
     * 初始化信息--前台分页
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public List<FinAssetRateAdjDto> getfinAssetRateAdjByProductId(Map<String, Object> paramMap) {
        String vdateString = paramMap.get("vdateString") == null ? "" : paramMap.get("vdateString").toString();
        List<FinAssetRateAdjDto> finAssetRateAdjList = restTemplate.postForList(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/getfinAssetRateAdjByProductId", paramMap,
            FinAssetRateAdjDto.class);
        for (FinAssetRateAdjDto entity : finAssetRateAdjList) {
            String updateFlg = StringUtil.EMPTY;
            if (StringUtil.equals(entity.getIsInitial(), DictCodeList.是否_是.getValue())) {
                updateFlg = "02";
            }
            else if (StringUtil.equals(DateUtil.format(entity.getEffDate()), vdateString)) {
                updateFlg = "02";
            }
            else if (DateUtil.compareDate(entity.getEffDate(), baseCommonService.getSysDate()) == -1) {
                updateFlg = "02";
            }
            else {
                updateFlg = "01";
            }
            entity.setUpdateFlg(updateFlg);
        }
        return finAssetRateAdjList;
    }

    /**
     * 组装数据
     * @param dto
     * @return
     */
    public FinAssetRateAdjDto encapFinAssetRateAdjDto(FinAssetRateAdjDto dto) {
        // 重新查询计算规则、计算规则扩展初始化信息
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", dto.getFinprodId());
        //param.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        param.put("finprodType", dto.getFinprodType());
        param.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        List<FinAssetRateAdjDto> finAssetRateAdjList = selectInitAssetRateAdj(param);
        String cashId = finAssetRateAdjList.get(0).getCashId();
        String calcType = finAssetRateAdjList.get(0).getCalcType();
        String baseType = finAssetRateAdjList.get(0).getBaseType();
        String baseDateType = finAssetRateAdjList.get(0).getBaseDateType();
        String basis = finAssetRateAdjList.get(0).getBasis();
        /* String isInitial = finAssetRateAdjList.get(0).getIsInitial(); */
        BigDecimal issueAmt = finAssetRateAdjList.get(0).getIssueAmt();
        Long branch = finAssetRateAdjList.get(0).getBranch();
        String finprodType2 =  finAssetRateAdjList.get(0).getFinprodType2();
        String finprodType =  finAssetRateAdjList.get(0).getFinprodType();
        /* BigDecimal buyOutRate = finAssetRateAdjList.get(0).getBuyOutRate(); */
        BigDecimal investRate = finAssetRateAdjList.get(0).getInvestRate();
        /* BigDecimal referRate = finAssetRateAdjList.get(0).getReferRate(); */
        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        dto.getSelectedItem().forEach(entity -> {
            // 生效利率
            BigDecimal yield = ArithmeticUtil.divide(entity.getYield(), new BigDecimal(100), 14);
            FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
            BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, entity);
            finCashCalcRule.setFinprodType2(finprodType2);
            finCashCalcRule.setFinprodType(finprodType);
            finCashCalcRule.setCashId(cashId);
            finCashCalcRule.setCalcType(calcType);
            finCashCalcRule.setBaseType(baseType);
            finCashCalcRule.setBaseDateType(baseDateType);
            finCashCalcRule.setBasis(basis);
            finCashCalcRule.setBranch(branch);
            finCashCalcRule.setYield(yield);
            finCashCalcRuleList.add(finCashCalcRule);
        });
        dto.setIssueAmt(issueAmt);
        dto.setCashId(cashId);
        dto.setBasis(basis);
        dto.setFinCashCalcRuleList(finCashCalcRuleList);
        return dto;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinAssetRateAdjDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // 重新计算现金流计划
        entity = encapFinAssetRateAdjDto(entity);
        if (null != entity.getSelectedItem() && !entity.getSelectedItem().isEmpty()) {
            try {
                FinAssetRateAdjDto rateAdjDto = new FinAssetRateAdjDto();
                BeanUtil.copyPropertiesIgnoreNull(rateAdjDto, entity);
                rateAdjDto.setFinCashCalcRuleList(entity.getFinCashCalcRuleList());
                //rateAdjDto.setNowSystemDate(baseCommonService.getSysDate());
                //与批处理调用用一个逻辑
                rateAdjDto = this.againCalCashPlan(rateAdjDto);
                if (rateAdjDto != null) {
                    entity.setFinCashPlanList(rateAdjDto.getFinCashPlanList());
                }
                //entity = againCalCashPlan(entity);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        if (OperationType.DELETE.equals(type)) {
//            action = "delete";
//        }
//        else if (OperationType.NEW.equals(type)) {
//            action = "insert";
//        }
//        else {
//            action = "update";
//        }
        restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/insert", entity, Integer.class);
    }


    /**
     * 获取初始化利率数据
     * @param param
     * @return
     */
    public List<FinAssetRateAdjDto> selectInitAssetRateAdj(Map<String,Object> param){
        return restTemplate.postForList(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/selectInitAssetRateAdj",
            param, FinAssetRateAdjDto.class);
    }

    /**
     * 利率调整后重新计算计划
     * <功能详细描述>
     * @param entity
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public  FinAssetRateAdjDto againCalCashPlan(FinAssetRateAdjDto entity) throws ParseException{
        //分支序号
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE)) ? 0:
                Long.parseLong(CacheUtil.getSysParam(ValComponentConstant.NOBRANCHNOVALUE));
        // 资产公共DTO
        AssetCommonInfoDto scai =  getAssetBasicInfoByFinprodId(entity.getFinprodId());
        if (scai == null) {
            return null;
        }
        // 利息计划
        List<FinCashPlan> finCashPlanList = new ArrayList<>();
        // 还本计划
        List<FinCashPlanDto> invRepayList = new ArrayList<>();
        // 参与计算付息计划
        List<FinCashPlanDto> scheList = new ArrayList<>();
        // 报价信息
        List<FinAssetRateAdjDto> rateList = new ArrayList<FinAssetRateAdjDto>();
        for (FinCashCalcRule finCashCalcRule : entity.getFinCashCalcRuleList()) {
            FinAssetRateAdjDto invRateQuo = new FinAssetRateAdjDto();
            invRateQuo.setEffDate(finCashCalcRule.getEffDate());
            invRateQuo.setYield(finCashCalcRule.getYield());
            invRateQuo.setBasis(scai.getBasis());
            rateList.add(invRateQuo);
        }
//        scheList = getFinCashPlanByProductId(entity.getFinprodId(),DictCodeListExt1.现金流二级类型_利息.getValue(),
//                DictCodeListExt1.现金流业务类型_金融产品.getValue(), null);
        scheList = getFinCashPlanByProductId(entity.getFinprodId(),DictCodeListExt1.现金流二级类型_利息.getValue(),
                entity.getFinprodType(), null);
//        invRepayList = getFinCashPlanByProductId(entity.getFinprodId(),
//                DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),null);
        invRepayList = getFinCashPlanByProductId(entity.getFinprodId(),
                DictCodeListExt1.现金流二级类型_本金.getValue(), entity.getFinprodType(),null);
        // 计算付息区间利息
        if(StringUtil.equals(scai.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
            scai.setAmount(new BigDecimal("100"));
        }else{
            scai.setAmount(scai.getIssueAmt());
        }
        //平台日
        scai.setSysDate(baseCommonService.getSysDate());
        scai.setInvList(scheList);
        scai.setInvRepayList(invRepayList);
        scai = againCalCashPlan(scai, rateList);
        finCashPlanList.clear();
        if(scai.getInvList() != null && !scai.getInvList().isEmpty()) {
            //付息计划
            for(FinCashPlan entity1:scai.getInvList()){
                entity1.setBranch(branch);
                entity1.setFinprodId(scai.getFinprodId());
                entity1.setFinprodType(scai.getFinprodType());
                entity1.setFinprodType2(scai.getFinprodType2());
                entity1.setCashBaseamt(scai.getIssueAmt());
                entity1.setCashAmt(entity1.getIntAmt());
                entity1.setVdateUnadjust(entity1.getVdate());
                entity1.setMdateUnadjust(entity1.getMdate());
                entity1.setPayDateUnadjust(entity1.getPayDate());
                entity1.setPayType(DictCodeList.分红方式_现金分红.getValue());
                //项目类资产理论开始日、理论结束日不参与计算逻辑
                entity1.setVdateTerm(entity1.getVdate());
                entity1.setMdateTerm(entity1.getMdateTerm());
                if(DateUtil.compareDate(scai.getMdate(), entity1.getMdate())==0){
                    entity1.setCashType(DictCodeList.现金流类型_到期收息.getValue());
                }else{
                    entity1.setCashType(DictCodeList.现金流类型_中间收息.getValue());
                }
                entity1.setPayType(DictCodeList.分红方式_现金分红.getValue());
                if(StringUtil.equals(scai.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())
                        || StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                        || StringUtil.equals(scai.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
                    //计算区间净投资收益率%(加权利率)
                    BigDecimal rangeYield = WeightedInterestRateCal.cal(rateList, entity1.getVdate(), entity1.getMdate());
                    entity1.setRangeYield(rangeYield);
                }
                finCashPlanList.add(entity1);
            }
        }
        //还本计划
        for(FinCashPlan entity2:scai.getInvRepayList()){
            entity2.setBranch(branch);
            entity2.setFinprodId(scai.getFinprodId());
            entity2.setFinprodType(scai.getFinprodType());
            entity2.setFinprodType2(scai.getFinprodType2());
            entity2.setVdateUnadjust(entity2.getVdate());
            entity2.setMdateUnadjust(entity2.getMdate());
            entity2.setPayDateUnadjust(entity2.getMdate());
            //项目类资产理论开始日、理论结束日不参与计算逻辑
            entity2.setVdateTerm(entity2.getVdate());
            entity2.setMdateTerm(entity2.getMdateTerm());
            entity2.setIsPayInt(DictCodeList.是否_是.getValue());
            entity2.setCashBaseamt(scai.getIssueAmt());
            entity2.setCashUnitType(DictCodeList.现金流单位_百元法.getValue());
            if(DateUtil.compareDate(entity2.getMdate(), scai.getMdate())==0){
                entity2.setCashType(DictCodeList.现金流类型_到期还本.getValue());
            }else{
                entity2.setCashType(DictCodeList.现金流类型_中间还本.getValue());
            }
            if(StringUtil.equals(scai.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())
                    ||StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                    ||StringUtil.equals(scai.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
                //计算区间净投资收益率%(加权利率)
                BigDecimal rangeYield = WeightedInterestRateCal.cal(rateList, entity2.getVdate(), entity2.getMdate());
                entity2.setRangeYield(rangeYield);
            }
            finCashPlanList.add(entity2);
        }
        entity.setFinCashPlanList(finCashPlanList);
        return entity;
    }


    /**
     * 计算现金流
     * <功能详细描述>
     * @param scai
     * @param finAssetRateAdjList
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public  AssetCommonInfoDto againCalCashPlan(AssetCommonInfoDto scai,List<FinAssetRateAdjDto> finAssetRateAdjList) throws ParseException{
        // 付息计划(return)
        List<FinCashPlan> finCashPlanList = new ArrayList<>();
        // 还本计划(return)
        List<FinCashPlan> finCashPlanRepayList = new ArrayList<>();
        // 参与计算还本计划
        List<FinCashPlanDto> invRepayList = new ArrayList<>();
        // 参与计算付息计划
        List<FinCashPlanDto> scheList = new ArrayList<>();
        // 付息规则
        FinCashPayRule is = new FinCashPayRule();
        //获取节假日
        List<String> holidayList = getDealCalendarHoliday(scai.getVdate(),scai.getMdate(),scai.getCalendarId());
        // 平台日期
        Date platDate = scai.getSysDate();
        // 计息基础
        //债券
        if(StringUtil.equals(scai.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
            if(StringUtil.equals(scai.getBasis(), DictCodeList.计息基础_A_A_平均分配.getValue())){
                scai.setBasis(DictCodeList.计息基础_A_A.getValue());
                scai.setIntpayRule(DictCodeListExt1.利息分配方式_平均分配.getValue());
            }else if(StringUtil.equals(scai.getBasis(), DictCodeList.计息基础_A_A_按实际天数分配.getValue())){
                scai.setBasis(DictCodeList.计息基础_A_A.getValue());
                scai.setIntpayRule(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
            }
        }
        for (FinCashCalcRule finCashCalcRule : finAssetRateAdjList) {
            if(DateUtil.compareDate(scai.getVdate(), finCashCalcRule.getEffDate())==0){
                scai.setRate(finCashCalcRule.getYield());
            }
        }
        //付息规则
//        FinCashPayRuleDto finPayInterestStdInfoDto = getFinPayInterestStdInfoByProductId(
//                scai.getFinprodId(), DateUtil.format(scai.getVdate()), DictCodeListExt1.现金流二级类型_利息.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue());
        FinCashPayRuleDto finPayInterestStdInfoDto = getFinPayInterestStdInfoByProductId(
                scai.getFinprodId(), DateUtil.format(scai.getVdate()), DictCodeListExt1.现金流二级类型_利息.getValue(),
                scai.getFinprodType());

        if(null!=finPayInterestStdInfoDto){
            BeanUtil.copyPropertiesIgnoreNull(is, finPayInterestStdInfoDto);
            scai.setIntpayRule(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
            //付息信息
            scheList = scai.getInvList();
            //还本信息
            invRepayList  = scai.getInvRepayList();
            //利息--现金流代码
            String intCashId = finPayInterestStdInfoDto.getCashId();
            //本金--现金流代码
            String repayCashId = StringUtil.EMPTY;
            //根据付息计划求出还本计划的计息开始日(注:还本计划计息截止日所在付息区间的计息开始日作为还本计划的计息开始日)
            if(null!=invRepayList&&!invRepayList.isEmpty()){
                repayCashId = invRepayList.get(0).getCashId();
                for(FinCashPlan entity:invRepayList){
                    FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                    BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto, entity);
                    if(null!=scheList&&!scheList.isEmpty()){
                        for(FinCashPlan entity2:scheList){
                            if(DateUtil.compareDate(entity.getMdate(), entity2.getVdate())==1
                                    &&DateUtil.compareDate(entity.getMdate(), entity2.getMdate())==-1){
                                entity.setVdate(entity2.getVdate());
                                break;
                            }else if(DateUtil.compareDate(finCashPlanDto.getMdate(), entity2.getMdate())==0){
                                entity.setVdate(entity2.getMdate());
                                break;
                            }
                        }
                    }
                }
            }
            //1.重新计算还本区间利息
            invRepayList = RepayScheduleCal.cal(scai, invRepayList, is, finAssetRateAdjList, scai.getAmount(), FinCashPlanDto.class, FinCashPlanDetailDto.class);
            finCashPlanList.clear();
            finCashPlanRepayList.clear();
            //2.重新计算付息区间利息
            scheList = InterestScheduleCalReset.interestScheduleCalA(scheList, scai, invRepayList, null,
                    finAssetRateAdjList, is, holidayList,platDate, null, FinCashPlanDetailDto.class);
            // 计算完整周期天数，年付息次数
            for (FinCashPlanDto entity2 : scheList) {
                entity2.setCashId(intCashId);
                entity2.setTermdays(DateUtil.getBetweenDayNumber(entity2.getVdate(), entity2.getMdate()));
                entity2.setFrequency((long)scheList.size());
            }
            for (FinCashPlanDto entity3 : invRepayList) {
                entity3.setCashId(repayCashId);
                entity3.setTermdays(DateUtil.getBetweenDayNumber(entity3.getVdate(), entity3.getMdate()));
                entity3.setFrequency((long)scheList.size());
            }
        }
        scai.setInvList(scheList);
        scai.setInvRepayList(invRepayList);
        return scai;
    }

    /**
     * GET资产公共信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public AssetCommonInfoDto getAssetBasicInfoByFinprodId(String finprodId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("finprodId", finprodId);
        map.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/getAssetBasicInfoByFinprodId",
                map, AssetCommonInfoDto.class);
    }

    /**
     * GET资产现金流信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finprodType, String finprodType2){
        Map<String,Object> param = new HashMap<>();
        param.put("finprodId", finprodId);
        param.put("cashType", cashType);
        param.put("finprodType", finprodType);
        param.put("finprodType2", finprodType2);
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/getFinCashPlanByProductId",
                param, FinCashPlanDto.class);
    }

    /**
     * GET现金流支付规则
     * <功能详细描述>
     * @param finprodId
     * @param vdate
     * @param cashType
     * @param finprodType
     * @return
     * @see
     * @since 1.0
     */
    public FinCashPayRuleDto getFinPayInterestStdInfoByProductId(String finprodId,String vdate,String cashType,String finprodType){
        Map<String,Object> param = new HashMap<>();
        param.put("finprodId", finprodId);
        param.put("vdate", vdate);
        param.put("cashType", cashType);
        param.put("finprodType", finprodType);
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/getFinPayInterestStdInfoByProductId",
                param, FinCashPayRuleDto.class);
    }

    /**
     * 节假日
     * <功能详细描述>
     * @param startDate
     * @param endDate
     * @param dealCalId
     * @return
     * @see
     * @since 1.0
     */
    public List<String> getDealCalendarHoliday(Date startDate, Date endDate, String dealCalId) {
        Map<String, Object> paraMap = new HashMap<>();
        int days = StringUtil.toInt(CacheUtil.getSysParam(ValComponentConstant.HOLIDAY_POSTPONE_DAYS));
        paraMap.put("startDate", DateUtil.format(DateUtil.addDay(startDate, -days)));
        paraMap.put("endDate", DateUtil.format(DateUtil.addDay(endDate, days)));
        paraMap.put("dealCalId", dealCalId);
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/getDealCalendarHoliday",
                paraMap, String.class);
    }

}
