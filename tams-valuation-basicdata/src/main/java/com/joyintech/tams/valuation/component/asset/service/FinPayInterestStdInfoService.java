package com.joyintech.tams.valuation.component.asset.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.business.service.DateCalService;
import com.joyintech.tams.valuation.component.common.calculator.*;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInvRateAdjustDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDetailDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import com.joyintech.tams.valuation.component.common.service.CommonRateAdjustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FinPayInterestStdInfoService
 * @Description: 付息 Service
 * @Author yanya
 * @Date 2021/3/4
 * @Version V1.0
 **/
@Service
public class FinPayInterestStdInfoService extends CommonExportService<FinPayInterestStdInfoDto> {

    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Service
     */
    @Autowired
    private DateCalService dateCalService;

    @Autowired
    private BaseCommonService baseCommonService;

    @Autowired
    private CommonRateAdjustService commonRateAdjustService;

    /**
     * 重构信息
     * @param dto
     * @return
     */
    public FinPayInterestStdInfoDto getFinPayInterestStdInfoDto(FinPayInterestStdInfoDto dto){
        FinCashPayRule finCashPayRule = new FinCashPayRule();
        BeanUtil.copyPropertiesIgnoreNull(finCashPayRule, dto);
        dto.setFinCashPayRule(finCashPayRule);
        return dto;
    }


    /**
     * 初始化支付规则
     * {@inheritDoc}
     */
    public FinPayInterestStdInfoDto getFinPayInterestStdInfoByProductId(String finprodId,String vdate,String cashType,String finprodType){
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinPayInterestStdInfo/getFinPayInterestStdInfoByProductId?finprodId="
                        + finprodId+"&vdate="+vdate
                        +"&cashType="+cashType
                        +"&finprodType="+finprodType,
                FinPayInterestStdInfoDto.class);
    }

    /**
     * 初始化付息计划
     * {@inheritDoc}
     */
    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finCashBusinessType, String finprodType){
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinPayInterestStdInfo/getFinCashPlanByProductId?finprodId="
                        + finprodId+"&cashType="+cashType
                        +"&finCashBusinessType="+finCashBusinessType+"&finprodType="+finprodType,
                FinCashPlanDto.class);
    }

    /**
     * 生成付息计划
     * {@inheritDoc}
     * @throws ParseException
     */
    public List<FinCashPlanDto> createAssetPayInteresPlan(FinPayInterestStdInfoDto dto) throws ParseException {
        //还本计划
        List<FinCashPlanDto> invRepayList = new ArrayList<FinCashPlanDto>();
        //资产公共DTO
        AssetCommonInfoDto scai = new AssetCommonInfoDto();
        BeanUtil.copyPropertiesIgnoreNull(scai, dto); //复制资产信息
        scai.setFinRateInfo(dto.getFinInterestFactorDto());
        //付息规则
        FinCashPayRule is = new FinCashPayRule();
        BeanUtil.copyPropertiesIgnoreNull(is, dto); //复制付息规则
        //到期日+15天
        Date mdate = DateUtil.parse(DateUtil.plusDays(DateUtil.format(scai.getMdate()), 15));
        //获取节假日
        List<String> holidayList = dateCalService.getDealCalendarHoliday(scai.getVdate(),mdate,scai.getCalendarId());
        //报价信息
        List<AssetCommonInvRateAdjustDto> rateList = new ArrayList<AssetCommonInvRateAdjustDto>();

        //画面还本计划为空时  构造一条还本计划
        invRepayList = createInvRepayList(scai,dto);
        //存款类资产取借款利率、计划类资产取净投资收益率
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue())
                || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())
        ){
            String isCalLoadRate =  CacheUtil.getSysParam("IS_CAL_LOADRATE");
            if(StringUtil.equals(isCalLoadRate,"01")){
                scai.setRate(ArithmeticUtil.divide(dto.getFinInterestFactorDto().getLoadRate(), new BigDecimal(100), 14));
            }
            else{
                if(StringUtil.equals(dto.getFinInterestFactorDto().getCalRateType(), DictCodeListExt3.计息利率方式_买断利率计提.getValue())){
                    scai.setRate(ArithmeticUtil.divide(dto.getFinInterestFactorDto().getBuyOutRate(), new BigDecimal(100), 14));
                }else{
                    scai.setRate(ArithmeticUtil.divide(dto.getFinInterestFactorDto().getInvestRate(), new BigDecimal(100), 14));
                }
            }
        }else if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())){
            if(StringUtil.equals(dto.getFinInterestFactorDto().getCalRateType(), DictCodeListExt3.计息利率方式_买断利率计提.getValue())){
                scai.setRate(ArithmeticUtil.divide(dto.getFinInterestFactorDto().getBuyOutRate(), new BigDecimal(100), 14));
            }else{
                scai.setRate(ArithmeticUtil.divide(dto.getFinInterestFactorDto().getInvestRate(), new BigDecimal(100), 14));
            }
        }else{
            scai.setRate(ArithmeticUtil.divide(dto.getFinInterestFactorDto().getRate(), new BigDecimal(100), 14));
        }
        //计息基础
        scai.setBasis(dto.getFinInterestFactorDto().getBasis());
        // 一体化对应etliu
//        scai.setRateType(dto.getFinInterestFactorDto().getRateType());
        scai.setIntType(dto.getFinInterestFactorDto().getIntType());
        scai.setAmount(dto.getIssueAmt());
        //息票品种
        String couponSpecies = dto.getCouponSpecies();
        //计息营业日规则
        String endTdayRule = dto.getEndTdayRule();
        if(StringUtil.isNotEmpty(endTdayRule)){
            is.setEndTdayRule(endTdayRule);
        }else{
            is.setEndTdayRule(DictCodeList.营业日准则_不调整.getValue());
        }
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
            if(!StringUtil.equals(couponSpecies, DictCodeList.息票品种_附息.getValue())){
                // 一体化对应etliu
//                is.setPayCycle(DictCodeList.支付频率_付息频率_到期支付.getValue());
                is.setPayCycle(DictCodeList.频率_到期一次.getValue());
                is.setPayTdayRule(dto.getPayTdayRule());
                // 一体化对应etliu
//                is.setCalcRule(DictCodeList.推算方法_起息日向后.getValue());
                is.setCalcRule(DictCodeList.推算方式_起息日向后.getValue());
            }
        }
        if(StringUtil.isEmpty(dto.getFinInterestFactorDto().getBasisStyle())){
            scai.setIntpayRule(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
        }else{
            scai.setIntpayRule(dto.getFinInterestFactorDto().getBasisStyle());
        }
        // 平台日期
        Date platDate = baseCommonService.getSysDate();
        // 获取付息区间列表
        List<FinCashPlanDto> scheList = new ArrayList<FinCashPlanDto>();
        //是否计划区间利息计算(计划明细查看)
        if(Boolean.parseBoolean(dto.getIsSubAmtCal())){
            if(Boolean.parseBoolean(dto.getIsAgainCalInt())){
                //画面所有付息计划传入
                List<FinCashPlanDto> finCashPlanList = dto.getFinCashPlanList();
                scheList = dto.getInvScheduleList();
                rateList = commonRateAdjustService.escapRateAdjust(scai,is,finCashPlanList,holidayList);
                //计息本金计算 计息本金=100-还本面额 yjm start 20190827
                BigDecimal intPrinAmt = scai.getAmount();
                for (FinCashPlanDto fdto: invRepayList) {
                    if(dto.getIsPayIntFlag().equals(DictCodeList.是否_是.getValue())){//是还本不带息的场景
                        fdto.setIsPayInt(DictCodeList.是否_否.getValue());//还本不带息
                    }
                    if (scheList.get(0).getVdate().compareTo(fdto.getMdate()) > 0 ) {
                        intPrinAmt = intPrinAmt.subtract(fdto.getPrinAmt());
                    }
                }
                //计息本金计算 计息本金=100-还本面额 yjm end 20190827
                scheList = InterestScheduleSubCal.interestScheduleSubCalA(scheList, scai, invRepayList, rateList, is,intPrinAmt , FinCashPlanDetailDto.class);
            }
            return scheList;
        }
        //前台传入，已有付息计划，重新计算利息
        if(Boolean.parseBoolean(dto.getIsAgainCalInt())){
            scheList = dto.getInvScheduleList();
            //计算支付日
            for(FinCashPlanDto FinCashPlanDto : scheList){
                String payDate = AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(FinCashPlanDto.getMdate()), is.getPayTdayRule(), holidayList);
                FinCashPlanDto.setPayDate(DateUtil.parse(payDate));
            }
            rateList = commonRateAdjustService.escapRateAdjust(scai,is,scheList,holidayList);
            // 仅计算付息区间利息
            scheList = InterestScheduleCalReset.interestScheduleCalA(scheList, scai, invRepayList, null,
                    rateList, is, holidayList,platDate, null, FinCashPlanDetailDto.class);
        }else{
            //重新生成计划
            //前台传入的交易中的付息计划
            List<FinCashPlanDto> oldScheList = new ArrayList<FinCashPlanDto>();
            if(null!=dto.getMaxTradeDate()){
                oldScheList = dto.getInvScheduleList();
                //筛选付息计划最大计息截止日(交易前计划不可变更,交易后计划可以重新按规则生成)
                if(null!=oldScheList&&!oldScheList.isEmpty()){
                    for(FinCashPlanDto finCashPlanDto:oldScheList){
                        if(DateUtil.compareDate(finCashPlanDto.getVdate(),dto.getMaxTradeDate())==-1
                                &&DateUtil.compareDate(finCashPlanDto.getMdate(), dto.getMaxTradeDate())>-1){
                            /*finCashPlanDto.setMdate(dto.getMaxTradeDate());*/
                            scai.setVdate(finCashPlanDto.getMdate());
                            break;
                        }
                    }
                    //交易中计划最后一条计息截止日作为起息日 生成剩下的付息计划
                    /*scai.setVdate(dto.getMaxTradeDate());*/
                }
            }
            if(DateUtil.compareDate(dto.getMdate(), scai.getVdate())<=0){
                scai.setVdate(dto.getVdate());
                scheList.addAll(oldScheList);
            } else {
                scheList = AssetInterestCal.cal(is, scai, holidayList, false,FinCashPlanDto.class);
                if(null!=dto.getMaxTradeDate()){
                    scai.setVdate(dto.getVdate());
                    scheList.addAll(oldScheList);
                }
            }
            rateList = commonRateAdjustService.escapRateAdjust(scai,is,scheList,holidayList);
            //计算付息区间利息
            scheList = InterestScheduleCal.interestScheduleCalA(scheList, scai, invRepayList, null,
                    rateList, is, holidayList, scai.getAmount(), FinCashPlanDetailDto.class);
        }
        //计算完整周期天数，年付息次数
        for(FinCashPlanDto entity : scheList){
            entity.setTermdays(DateUtil.getBetweenDayNumber(entity.getVdate(),entity.getMdate()));
            entity.setFrequency((long)scheList.size());
            entity.setYield(ArithmeticUtil.multiply(entity.getYield(),new BigDecimal("100")));
            if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())
                    ||StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
                //计算区间净投资收益率%(加权利率)
                BigDecimal rangeYield = ArithmeticUtil.multiply(WeightedInterestRateCal.cal(rateList, entity.getVdate(), entity.getMdate()),new BigDecimal("100"));
                entity.setRangeYield(rangeYield);
            }
        }
        return scheList;
    }

    /**
     * 构建还本计划
     * <功能详细描述>
     * @param scai
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private List<FinCashPlanDto>  createInvRepayList(AssetCommonInfoDto scai,FinPayInterestStdInfoDto dto ){
        List<FinCashPlanDto> invRepayList = new ArrayList<FinCashPlanDto>();
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue())
                || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())
            /*||StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())*/){
            FinCashPlanDto newFinCashPlan = new FinCashPlanDto();
            newFinCashPlan.setMdateUnadjust(scai.getMdate());
            newFinCashPlan.setPayDateUnadjust(scai.getMdate());
            newFinCashPlan.setMdate(scai.getMdate());
            newFinCashPlan.setPayDate(scai.getMdate());
            newFinCashPlan.setPrinAmt(dto.getIssueAmt());
            invRepayList.add(newFinCashPlan);
        }else{
            if(Boolean.parseBoolean(dto.getIsAgainCalInt())
                    ||(Boolean.parseBoolean(dto.getIscallbackPayByRepay()))){
                invRepayList.addAll(dto.getInvRepayList());

                for(FinCashPlanDto cashPlan:invRepayList){
                    if(dto.getIsPayIntFlag()!=null && dto.getIsPayIntFlag().equals(DictCodeList.是否_是.getValue())){
                        cashPlan.setIsPayInt(DictCodeList.是否_否.getValue());
                    }else{
                        cashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
                    }
                }
            }else{
                FinCashPlanDto newFinCashPlan = new FinCashPlanDto();
                newFinCashPlan.setMdateUnadjust(scai.getMdate());
                newFinCashPlan.setPayDateUnadjust(scai.getMdate());
                newFinCashPlan.setMdate(scai.getMdate());
                newFinCashPlan.setPayDate(scai.getMdate());
                newFinCashPlan.setPrinAmt(dto.getIssueAmt());
                if(dto.getIsPayIntFlag().equals(DictCodeList.是否_是.getValue())){//是还本不带息的场景
                    newFinCashPlan.setIsPayInt(DictCodeList.是否_否.getValue());//还本不带息
                }
                invRepayList.add(newFinCashPlan);
            }
        }
        return invRepayList;
    }
}
