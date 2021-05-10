package com.joyintech.tams.valuation.basicdata.asset.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.asset.dto.BusiFinExercisePlanDto;
import com.joyintech.tams.valuation.component.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.valuation.component.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ValAssetCommonService
 * @Description: 资产共通service
 * @Author yanya
 * @Date 2021/3/5 0005 
 * @Version V1.0
 **/
@Service
public class ValAssetCommonService extends BaseService {

    /**
     * 封装计息要素DTO
     * @param dto
     * @param finCashInfoInterest
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    public FinInterestFactorDto encapFinInterestFactor(FinProduct dto, FinInterestFactorDto finInterestFactorDto, FinCashInfo finCashInfoInterest, FinCashInfo finCashInfoRefer, Long branch){
        //利率信息List
        List<FinRateInfo> rateList = new ArrayList<FinRateInfo>();
        //计算规则List
        List<FinCashCalcRule> calcRuleList = new ArrayList<FinCashCalcRule>();
        //现金流利率信息
        FinRateInfo finRateInfo  = finInterestFactorDto.getFinRateInfo();
        //计算规则
        FinCashCalcRule finCashCalcRule = finInterestFactorDto.getFinCashCalcRule();
        //现金流计算规则扩展
        PaFinCashCalcRule paFinCashCalcRule = finInterestFactorDto.getPaFinCashCalcRule();
        String baseType = DictCodeList.基数类型_实收资本.getValue();
        //浮动利率系列参数处理---利率上限、下限、利差
        // 一体化对应etliu
//        if(StringUtil.equals(finInterestFactorDto.getRateType(), DictCodeList.利率类型_浮动利率.getValue())){
        // 一体化对应etliu
//            if(null!=finInterestFactorDto.getRateMax()){
        if(null!=finInterestFactorDto.getHighestRate()){
            // 一体化对应etliu
//                BigDecimal rateMax = ArithmeticUtil.divide(finInterestFactorDto.getRateMax(), new BigDecimal(100), 14);
            BigDecimal rateMax = ArithmeticUtil.divide(finInterestFactorDto.getHighestRate(), new BigDecimal(100), 14);
            // 一体化对应etliu
//                finRateInfo.setRateMax(rateMax);
            finRateInfo.setHighestRate(rateMax);
        }
        // 一体化对应etliu
//            if(null!=finInterestFactorDto.getRateMin()){
        if(null!=finInterestFactorDto.getLowestRate()){
            // 一体化对应etliu
//                BigDecimal rateMin = ArithmeticUtil.divide(finInterestFactorDto.getRateMin(), new BigDecimal(100), 14);
            BigDecimal rateMin = ArithmeticUtil.divide(finInterestFactorDto.getLowestRate(), new BigDecimal(100), 14);
            // 一体化对应etliu
//                finRateInfo.setRateMin(rateMin);
            finRateInfo.setLowestRate(rateMin);
        }
        if(null!=finInterestFactorDto.getSpreadRate()){
            BigDecimal spreadRate = ArithmeticUtil.divide(finInterestFactorDto.getSpreadRate(), new BigDecimal(100), 14);
            finRateInfo.setSpreadRate(spreadRate);
        }
        /*现金流利率信息*/
        finRateInfo.setCashId(finCashInfoInterest.getCashId());
        finRateInfo.setEffDate(dto.getVdate());
        finRateInfo.setFinprodId(dto.getFinprodId());
        finRateInfo.setFinprodType(dto.getFinprodType());
        finRateInfo.setFinprodType2(dto.getFinprodType2());
        finRateInfo.setBranch(branch);
        /*计算规则*/
        finCashCalcRule.setFinprodId(dto.getFinprodId());
        finCashCalcRule.setEffDate(dto.getVdate());
        // 一体化对应etliu
//        finCashCalcRule.setRelaeseDate(dto.getVdate());
        finCashCalcRule.setBranch(branch);
        finCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        finCashCalcRule.setIsInitial(DictCodeList.是否_是.getValue());
        finCashCalcRule.setFinprodId(dto.getFinprodId());
        finCashCalcRule.setFinprodType(dto.getFinprodType());
        // 一体化对应etliu start
//        finCashCalcRule.setFinprodType(dto.getFinprodType2());
        finCashCalcRule.setFinprodType2(dto.getFinprodType2());
        finCashCalcRule.setCalcType(finRateInfo.getIntType());
        // 一体化对应etliu end
        finCashCalcRule.setBaseType(baseType);
        BigDecimal rate = BigDecimal.ZERO;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue()) || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())){
            BigDecimal loadRate = ArithmeticUtil.divide(finInterestFactorDto.getLoadRate(), new BigDecimal(100), 14);
            BigDecimal investRate = ArithmeticUtil.divide(finInterestFactorDto.getInvestRate(), new BigDecimal(100), 14);
            paFinCashCalcRule.setLoadRate(loadRate);
            paFinCashCalcRule.setInvestRate(investRate);
            String isCalLoadRate =  CacheUtil.getSysParam("IS_CAL_LOADRATE");
            if(StringUtil.equals(isCalLoadRate,"01")){
                rate = loadRate;
            }
            else{
                if(StringUtil.equals(finInterestFactorDto.getCalRateType(), DictCodeListExt3.计息利率方式_买断利率计提.getValue())){
                    rate = ArithmeticUtil.divide(finInterestFactorDto.getBuyOutRate(), new BigDecimal(100), 14);
                }else{
                    rate = investRate;
                }
            }
        }else if(StringUtil.equals(dto.getFinprodType(),DictCodeListExt1.金融产品类型_债券.getValue())){
            rate = ArithmeticUtil.divide(finInterestFactorDto.getRate(), new BigDecimal(100), 14);
            finRateInfo.setRate(rate);
            finCashCalcRule.setYield(rate);
        }else if(StringUtil.equals(dto.getFinprodType(),DictCodeListExt1.金融产品类型_计划类资产.getValue())){
            BigDecimal loadRate = ArithmeticUtil.divide(finInterestFactorDto.getLoadRate(), new BigDecimal(100), 14);
            BigDecimal investRate = ArithmeticUtil.divide(finInterestFactorDto.getInvestRate(), new BigDecimal(100), 14);
            paFinCashCalcRule.setLoadRate(loadRate);
            paFinCashCalcRule.setInvestRate(investRate);
            if(StringUtil.equals(finInterestFactorDto.getCalRateType(), DictCodeListExt3.计息利率方式_买断利率计提.getValue())){
                rate = ArithmeticUtil.divide(finInterestFactorDto.getBuyOutRate(), new BigDecimal(100), 14);
            }else{
                rate = investRate;
            }
        }else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            BigDecimal investRate = ArithmeticUtil.divide(finInterestFactorDto.getInvestRate(), new BigDecimal(100), 14);
            BigDecimal loadRate = ArithmeticUtil.divide(finInterestFactorDto.getLoadRate(), new BigDecimal(100), 14);
            paFinCashCalcRule.setLoadRate(loadRate);
            paFinCashCalcRule.setInvestRate(investRate);
            if(StringUtil.equals(finInterestFactorDto.getCalRateType(), DictCodeListExt3.计息利率方式_买断利率计提.getValue())){
                rate = ArithmeticUtil.divide(finInterestFactorDto.getBuyOutRate(), new BigDecimal(100), 14);
            }else{
                rate = investRate;
            }
        }
        finRateInfo.setRate(rate);
        finCashCalcRule.setYield(rate);
        //利率信息
        //利息--利率信息
        FinRateInfo finRateInfoInterest = new FinRateInfo();
        BeanUtil.copyPropertiesIgnoreNull(finRateInfoInterest, finRateInfo);
        finRateInfoInterest.setCashId(finCashInfoInterest.getCashId());
        rateList.add(finRateInfoInterest);
        //计算规则
        //利息--计算规则
        FinCashCalcRule finCashCalcRuleInterest = new FinCashCalcRule();
        BeanUtil.copyPropertiesIgnoreNull(finCashCalcRuleInterest, finCashCalcRule);
        finCashCalcRuleInterest.setCashId(finCashInfoInterest.getCashId());
        calcRuleList.add(finCashCalcRuleInterest);
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue()) || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())){
            //资产推荐费率
            BigDecimal referRate = ArithmeticUtil.divide(finInterestFactorDto.getReferRate(), new BigDecimal(100), 14);
            //推荐费--利率信息
            FinRateInfo finRateInfoRefer = new FinRateInfo();
            BeanUtil.copyPropertiesIgnoreNull(finRateInfoRefer, finRateInfo);
            finRateInfoRefer.setCashId(finCashInfoRefer.getCashId());
            finRateInfoRefer.setRate(ArithmeticUtil.divide(finInterestFactorDto.getReferRate(), new BigDecimal(100), 14));
            rateList.add(finRateInfoRefer);
            //推荐费--计算规则
            FinCashCalcRule finCashCalcRuleRefer = new FinCashCalcRule();
            BeanUtil.copyPropertiesIgnoreNull(finCashCalcRuleRefer, finCashCalcRule);
            finCashCalcRuleRefer.setYield(referRate);
            finCashCalcRuleRefer.setCashId(finCashInfoRefer.getCashId());
            calcRuleList.add(finCashCalcRuleRefer);
        }
        /*现金流计算规则扩展*/
        paFinCashCalcRule.setCashId(finCashInfoInterest.getCashId());
        paFinCashCalcRule.setEffDate(dto.getVdate());
        finInterestFactorDto.setRateList(rateList);finInterestFactorDto.setCalcRuleList(calcRuleList);
        finInterestFactorDto.setPaFinCashCalcRule(paFinCashCalcRule);
        return finInterestFactorDto;
    }

    /**
     * 封装付息信息DTO
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param finCashInfoPrincipal
     * @param finCashInfoInterest
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    public FinPayInterestStdInfoDto encapFinPayInterestStdInfo(FinProduct dto, FinPayInterestStdInfoDto finPayInterestStdInfoDto, FinCashInfo finCashInfoPrincipal, FinCashInfo finCashInfoInterest, Long branch){
        List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        //现金流基数
        BigDecimal cashBaseamt = BigDecimal.ZERO;
        //现金流单位
        String cashUnitType = StringUtil.EMPTY;
        //现金流支付规则
        FinCashPayRule finCashPayRule = finPayInterestStdInfoDto.getFinCashPayRule();
        finCashPayRule.setCashId(finCashInfoInterest.getCashId());
        finCashPayRule.setEffDate(dto.getVdate());
        finCashPayRule.setFinprodId(dto.getFinprodId());
        finCashPayRule.setFinprodType(dto.getFinprodType());
        finCashPayRule.setFinprodType2(dto.getFinprodType2());
        finCashPayRule.setPayType(DictCodeList.分红方式_现金分红.getValue());
        finCashPayRule.setBranch(branch);
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue()) || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())){
            finCashPayRule.setEndTdayRule(DictCodeList.营业日准则_不调整.getValue());
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }else if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
            cashBaseamt = new BigDecimal("100");
            cashUnitType = DictCodeList.现金流单位_百元法.getValue();
        }else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }
        finCashPayRule.setCashUnitType(cashUnitType);

        // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
        if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
            finCashPayRule.setPayCycle(ValComponentConstant.DEFAULT_PAY_CYCLE);
        }
//        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())
//                &&(StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_零息.getValue())
//                ||StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_贴现.getValue()))){
//
//        }
//        else
            if(null!=finPayInterestStdInfoDto.getFinCashPlanList()){
            for(FinCashPlan finCashPlanEntity:finPayInterestStdInfoDto.getFinCashPlanList()){
                FinCashPlan finCashPlan = new FinCashPlan();
                BeanUtil.copyPropertiesIgnoreNull(finCashPlan, finCashPlanEntity);
                Date vdate = finCashPlanEntity.getVdate();
                Date mdate = finCashPlanEntity.getMdate();
                long termdays  = DateUtil.getBetweenDayNumber(DateUtil.format(vdate),DateUtil.format(mdate));
                finCashPlan.setTermdays(termdays);
                if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                        ||StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())
                        ||StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
                    BigDecimal rangeYield = ArithmeticUtil.divide(finCashPlanEntity.getRangeYield(), new BigDecimal(100), 14);
                    finCashPlan.setRangeYield(rangeYield);
                    finCashPlan.setRepayWithoutInt(finCashPlanEntity.getRepayWithoutInt());//区间还本未付利息
                }
                if(DateUtil.compareDate(dto.getMdate(), finCashPlanEntity.getMdate())==0){
                    finCashPlan.setCashType(DictCodeList.现金流类型_到期收息.getValue());
                }else{
                    finCashPlan.setCashType(DictCodeList.现金流类型_中间收息.getValue());
                }
                finCashPlan.setCashBaseamt(cashBaseamt);
                finCashPlan.setCashAmt(finCashPlanEntity.getIntAmt());
                finCashPlan.setVdateUnadjust(finCashPlanEntity.getVdate());
                finCashPlan.setMdateUnadjust(finCashPlanEntity.getMdate());
                finCashPlan.setPayDateUnadjust(finCashPlanEntity.getPayDate());
                finCashPlan.setCashId(finCashInfoInterest.getCashId());
                finCashPlan.setFinprodId(dto.getFinprodId());
                finCashPlan.setFinprodType(dto.getFinprodType());
                finCashPlan.setFinprodType2(dto.getFinprodType2());
                finCashPlan.setPayType(DictCodeList.分红方式_现金分红.getValue());
                finCashPlan.setCashUnitType(cashUnitType);
                finCashPlan.setBranch(branch);
                finCashPlanList.add(finCashPlan);
            }
        }
        ;
        //本金计划
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue()) || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())){
            //现金流计划表Entity-本金（一条数据）
            //最后一条付息计划--针对存款类资产--构建一条还本信息
            FinCashPlan finCashPlanLast = new FinCashPlan();
            if(null!=finCashPlanList&&!finCashPlanList.isEmpty()){
                finCashPlanLast = finCashPlanList.get(finCashPlanList.size()-1);
            }
            FinCashPlan finCashPlanPrincipal = new FinCashPlan();
            BeanUtil.copyPropertiesIgnoreNull(finCashPlanPrincipal, finCashPlanLast);
            finCashPlanPrincipal.setVdate(finCashPlanLast.getMdate());
            finCashPlanPrincipal.setVdateUnadjust(finCashPlanLast.getMdate());
            finCashPlanPrincipal.setCashId(finCashInfoPrincipal.getCashId());
            finCashPlanPrincipal.setCashType(DictCodeList.现金流类型_到期还本.getValue());
            finCashPlanPrincipal.setPrinAmt(dto.getIssueAmt());
            finCashPlanPrincipal.setIntPrinAmt(dto.getIssueAmt());
            finCashPlanPrincipal.setIntAmt(BigDecimal.ZERO);
            finCashPlanPrincipal.setIsPayInt(DictCodeList.是否_是.getValue());
            finCashPlanPrincipal.setCashAmt(dto.getIssueAmt());
            finCashPlanPrincipal.setCashUnitType(cashUnitType);
            finCashPlanList.add(finCashPlanPrincipal);
        }
        finCashPayRuleList.add(finCashPayRule);
        finPayInterestStdInfoDto.setFinCashPlan2List(finCashPlanList);
        finPayInterestStdInfoDto.setFinCashPayRuleList(finCashPayRuleList);
        return finPayInterestStdInfoDto;
    }


    /**
     * 封装还本信息DTO
     * <功能详细描述>
     * @param dto
     * @param bussFinRepaymentDto
     * @param finCashInfoPrincipal
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    public BussFinRepaymentDto encapBussFinRepayment(FinProduct dto, BussFinRepaymentDto bussFinRepaymentDto, FinCashInfo finCashInfoPrincipal, Long branch){
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        //现金流基数
        BigDecimal cashBaseamt = BigDecimal.ZERO;
        //现金流单位
        String cashUnitType = StringUtil.EMPTY;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue()) || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())){
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }else if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
            cashBaseamt = new BigDecimal("100");
            cashUnitType = DictCodeList.现金流单位_百元法.getValue();
        }else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                ||StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())){
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }
        //增加一条本金的支付规则(针对项目类资产--还本周期、还本周期说明)
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                ||StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())){
            FinCashPayRule finCashPayRule = new FinCashPayRule();
            finCashPayRule.setCashId(finCashInfoPrincipal.getCashId());
            finCashPayRule.setEffDate(dto.getVdate());
            finCashPayRule.setFinprodId(dto.getFinprodId());
            finCashPayRule.setFinprodType(dto.getFinprodType());
            finCashPayRule.setFinprodType2(dto.getFinprodType2());
            finCashPayRule.setCashUnitType(cashUnitType);
            finCashPayRule.setPayType(DictCodeList.分红方式_现金分红.getValue());
            finCashPayRule.setPayCycle(bussFinRepaymentDto.getPayCycle());
            finCashPayRule.setRemark(bussFinRepaymentDto.getRemark());
            finCashPayRule.setBranch(branch);

            // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
            if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
                finCashPayRule.setPayCycle(ValComponentConstant.DEFAULT_PAY_CYCLE);
            }

            bussFinRepaymentDto.setFinCashPayRule(finCashPayRule);
        }
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())
                &&(StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_零息.getValue())
                ||StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_贴现.getValue()))){
            FinCashPlan finCashPlanPrincipal = new FinCashPlan();
            finCashPlanPrincipal.setCashId(finCashInfoPrincipal.getCashId());
            finCashPlanPrincipal.setVdateUnadjust(dto.getVdate());
            finCashPlanPrincipal.setMdateUnadjust(dto.getMdate());
            finCashPlanPrincipal.setVdate(dto.getVdate());
            finCashPlanPrincipal.setMdate(dto.getMdate());
            finCashPlanPrincipal.setPayDate(dto.getMdate());
            if(!StringUtil.isEmptyList(bussFinRepaymentDto.getFinCashPlanList())
                    && bussFinRepaymentDto.getFinCashPlanList().get(0).getPayDate() != null) {
                finCashPlanPrincipal.setPayDate(bussFinRepaymentDto.getFinCashPlanList().get(0).getPayDate());
            }
            finCashPlanPrincipal.setCashType(DictCodeList.现金流类型_到期还本.getValue());
            finCashPlanPrincipal.setPrinAmt(new BigDecimal("100"));
            finCashPlanPrincipal.setIntPrinAmt(new BigDecimal("100"));
            finCashPlanPrincipal.setCashBaseamt(cashBaseamt);
            finCashPlanPrincipal.setIntAmt(BigDecimal.ZERO);
            finCashPlanPrincipal.setIsPayInt(DictCodeList.是否_是.getValue());
            finCashPlanPrincipal.setCashAmt(cashBaseamt);
            finCashPlanPrincipal.setCashUnitType(cashUnitType);
            finCashPlanPrincipal.setFinprodId(dto.getFinprodId());
            finCashPlanPrincipal.setFinprodType(dto.getFinprodType());
            finCashPlanPrincipal.setFinprodType2(dto.getFinprodType2());
            finCashPlanPrincipal.setBranch(branch);
            finCashPlanList.add(finCashPlanPrincipal);
        }else if(null!=bussFinRepaymentDto.getFinCashPlanList()){
            List<FinCashPlanDto> intiFinCashPlanList = bussFinRepaymentDto.getFinCashPlanList();
            for(int i=0;i<intiFinCashPlanList.size();i++){
                FinCashPlan finCashPlan = new FinCashPlan();
                FinCashPlan initFinCashPlan = intiFinCashPlanList.get(i);
                BeanUtil.copyPropertiesIgnoreNull(finCashPlan, initFinCashPlan);
                finCashPlan.setCashId(finCashInfoPrincipal.getCashId());
                Date vdate = initFinCashPlan.getVdate();Date mdate = initFinCashPlan.getMdate();
                long termdays  = DateUtil.getBetweenDayNumber(DateUtil.format(vdate),DateUtil.format(mdate));
                finCashPlan.setTermdays(termdays);
                finCashPlan.setVdateUnadjust(initFinCashPlan.getVdate());
                finCashPlan.setMdateUnadjust(initFinCashPlan.getMdate());
                finCashPlan.setPayDateUnadjust(initFinCashPlan.getMdate());
                if(bussFinRepaymentDto.getIsPayIntFlag().equals(DictCodeList.是否_是.getValue())) {//是否还本不带息为是-本金不带息
                    finCashPlan.setIsPayInt(DictCodeList.是否_否.getValue());
                    finCashPlan.setIntAmt(initFinCashPlan.getRepayWithoutInt());//还本不带息的利息金额
                }else{//是否还本不带息为否-本金带息
                    finCashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
                }
                finCashPlan.setCashBaseamt(cashBaseamt);
                finCashPlan.setCashUnitType(cashUnitType);
                finCashPlan.setFinprodId(dto.getFinprodId());
                finCashPlan.setFinprodType(dto.getFinprodType());
                finCashPlan.setFinprodType2(dto.getFinprodType2());
                finCashPlan.setBranch(branch);
                //最后一条---现金流类型“到期还本”
                if(DateUtil.compareDate(initFinCashPlan.getMdate(), dto.getMdate())==0){
                    finCashPlan.setCashType(DictCodeList.现金流类型_到期还本.getValue());
                }else{
                    finCashPlan.setCashType(DictCodeList.现金流类型_中间还本.getValue());
                }
                finCashPlanList.add(finCashPlan);
            }
        }
        bussFinRepaymentDto.setFinCashPlan2List(finCashPlanList);
        return bussFinRepaymentDto;
    }

    /**
     *
     * 封装行权计划信息
     * <功能详细描述>
     * @param dto
     * @param busiFinExercisePlanDto
     * @param finCashInfoPrincipal
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    public BusiFinExercisePlanDto encapBusiFinExercisePlan(FinProduct dto, BusiFinExercisePlanDto busiFinExercisePlanDto, FinCashInfo finCashInfoPrincipal, Long branch){
        List<FinExercisePlan> finExercisePlanList = new ArrayList<FinExercisePlan>();
        if(null!=busiFinExercisePlanDto.getFinExercisePlanList()&&!busiFinExercisePlanDto.getFinExercisePlanList().isEmpty()){
            busiFinExercisePlanDto.getFinExercisePlanList().forEach(finExercisePlanEntity->{
                finExercisePlanEntity.setFinprodId(dto.getFinprodId());
                finExercisePlanEntity.setFinprodType(dto.getFinprodType());
                finExercisePlanEntity.setFinprodType2(dto.getFinprodType2());
                finExercisePlanEntity.setCashId(finCashInfoPrincipal.getCashId());//本金现金流ID
                finExercisePlanEntity.setBranch(branch);
                finExercisePlanList.add(finExercisePlanEntity);
            });
        }
        busiFinExercisePlanDto.setFinExercisePlanList(finExercisePlanList);
        return busiFinExercisePlanDto;
    }

    /**
     * 获取所有资产id 分页
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getAssetIdOption(Map<String, Object> map) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValAssetCommon/getAssetIdOption",
                map, SelectOptionDto.class);
    }

    /**
     * 获取所有资产id
     * @param search
     * @param finprodType
     * @param profitType
     * @return
     */
    public List<SuggestDto> getAssetId(String search, String finprodType, String profitType){
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValAssetCommon/getAssetId?search="
                        + search+"&finprodType="+finprodType+"&profitType="+profitType+"&child="+ RequestUtil.getParam("child"),
                SuggestDto.class);
    }

    /**
     * 发行人联想框
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getIssuerId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValAssetCommon/getIssuer", map,
                SelectOptionDto.class);
    }

    /**
     * 发行人联想框
     * @param map
     * @return
     */
    public Page<SelectOptionDto> selectCalendarIdForPge(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValAssetCommon/selectCalendarIdForPge", map,
                SelectOptionDto.class);
    }

    /**
     * 核算分类
     * @param finProductClass
     * @return
     */
    public String getFinanceProductClass(FinProductClass finProductClass) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValAssetCommon/getFinanceProductClass", finProductClass,
                String.class);
    }

    /**
     * 查询税务分类
     * @param finProductClass
     * @return
     */
    public String getFinanceProductTaxClass(FinProductClass finProductClass) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/ValAssetCommon/getFinanceProductTaxClass", finProductClass,
                String.class);
    }
}
