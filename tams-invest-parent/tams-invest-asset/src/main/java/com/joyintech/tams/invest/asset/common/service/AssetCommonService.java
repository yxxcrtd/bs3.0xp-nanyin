/*
 * 文件名：AssetCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.MstCounterPartyMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.component.business.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.component.business.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.component.business.bond.dto.BusiFinBondRatingInfoDto;
import com.joyintech.tams.component.business.bond.dto.BusiFinExercisePlanDto;
import com.joyintech.tams.component.business.common.dto.AssetCommonInvRateAdjustDto;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.project.dto.BussFinProductCreditDto;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.component.business.report.asset.bond.dto.BussFinBondRepInfoDto;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDepositInfoDto;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDlRepoInfoDto;
import com.joyintech.tams.component.business.report.asset.fund.dto.BussRepFundInfoDto;
import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfo;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepAssetAstBillInfoDto;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepAssetCreditNonstdInfoDto;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepEquityInfoDto;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepOtherInvestTargetInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.common.dto.*;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDetailDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.calculator.InterestScheduleCalReset;
import com.joyintech.tams.invest.common.calculator.RepayScheduleCal;
import com.joyintech.tams.invest.common.calculator.WeightedInterestRateCal;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 资产共通service
 * @author zhangnian
 * @version 1.0 2018年9月21日
 * @see AssetCommonService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class AssetCommonService extends BaseService{

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 付息信息
     */
    @Autowired
    private FinPayInterestStdInfoService finPayInterestStdInfoService;


    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Service
     */
    @Autowired
    private DateCalService dateCalService;

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private MstCounterPartyMapper mstCounterPartyMapper;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    /**
     * BaseGetBusinessId
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    public AssetCommonInfoDto getAssetInfoToRateAdj(Map<String,Object> map){
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/AssetCommon/getAssetInfoToRateAdj",map,
            AssetCommonInfoDto.class);
    }
    /**
     * 获取所有资产id
     * <功能详细描述>
     * @param search
     * @param finprodType2
     * @param profitType
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetId(String search,String finprodType2,String profitType){
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
            + "/AssetCommon/getAssetId?search="
            + search+"&finprodType2="+finprodType2+"&profitType="+profitType+"&child="+ RequestUtil.getParam("child"),
            SuggestDto.class);
    }

    public Page<SelectOptionDto> getAssetIdOption( Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getAssetIdOption",
            map, SelectOptionDto.class);
    }

    public Page<SelectOptionDto> getSysDictCode(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getSysDictCode", map,
                SelectOptionDto.class);
    }

    /**
     * 交易日历下拉框查询
     *
     * @param map  参数
     * @return 交易日历
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryCalendarInfoList( Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/queryCalendarInfoList",
            map, SelectOptionDto.class);
    }

    /**
     *  资产提供行
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getTradeOpponent( Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getTradeOpponent",
            map, SelectOptionDto.class);
    }

    /**
     *  客户信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCustomerName( Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getCustomerName",
            map, SelectOptionDto.class);
    }

    /**
     *  市场机构客户信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getMarketInstitutionCustomerName( Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getMarketInstitutionCustomerName",
            map, SelectOptionDto.class);
    }

    public MstCustomerInfoAdd getRepInfoByIssuer( Map<String, Object> map){
        return restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getRepInfoByIssuer",
            map, MstCustomerInfoAdd.class);
    }

    /**
     * 封装计息要素DTO
     * @param dto
     * @param finCashInfoInterest
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    public FinInterestFactorDto encapFinInterestFactor(FinProduct dto,FinInterestFactorDto finInterestFactorDto,FinCashInfo finCashInfoInterest,FinCashInfo finCashInfoRefer,Long branch){
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
        if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
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
        }else if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            rate = ArithmeticUtil.divide(finInterestFactorDto.getRate(), new BigDecimal(100), 14);
            finRateInfo.setRate(rate);
            finCashCalcRule.setYield(rate);
        }else if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
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
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
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
    public FinPayInterestStdInfoDto encapFinPayInterestStdInfo(FinProduct dto,FinPayInterestStdInfoDto finPayInterestStdInfoDto,FinCashInfo finCashInfoPrincipal,FinCashInfo finCashInfoInterest,Long branch){
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
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
            finCashPayRule.setEndTdayRule(DictCodeList.营业日准则_不调整.getValue());
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            cashBaseamt = new BigDecimal("100");
            cashUnitType = DictCodeList.现金流单位_百元法.getValue();
        }else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }
        finCashPayRule.setCashUnitType(cashUnitType);

        // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
        if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
			finCashPayRule.setPayCycle(BussinessConstant.DEFAULT_PAY_CYCLE);
		}
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                &&(StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_零息.getValue())
                ||StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_贴现.getValue()))){
        }else if(null!=finPayInterestStdInfoDto.getFinCashPlanList()){
            for(FinCashPlan finCashPlanEntity:finPayInterestStdInfoDto.getFinCashPlanList()){
                FinCashPlan finCashPlan = new FinCashPlan();
                BeanUtil.copyPropertiesIgnoreNull(finCashPlan, finCashPlanEntity);
                Date vdate = finCashPlanEntity.getVdate();Date mdate = finCashPlanEntity.getMdate();
                long termdays  = DateUtil.getBetweenDayNumber(DateUtil.format(vdate),DateUtil.format(mdate));
                finCashPlan.setTermdays(termdays);
                if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                        ||StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                        ||StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
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
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
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
    public BussFinRepaymentDto encapBussFinRepayment(FinProduct dto,BussFinRepaymentDto bussFinRepaymentDto,FinCashInfo finCashInfoPrincipal,Long branch){
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        //现金流基数
        BigDecimal cashBaseamt = BigDecimal.ZERO;
        //现金流单位
        String cashUnitType = StringUtil.EMPTY;
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            cashBaseamt = new BigDecimal("100");
            cashUnitType = DictCodeList.现金流单位_百元法.getValue();
        }else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
        ||StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
            cashBaseamt = dto.getIssueAmt();
            cashUnitType = DictCodeList.现金流单位_总额.getValue();
        }
        //增加一条本金的支付规则(针对项目类资产--还本周期、还本周期说明)
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
        ||StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
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
				finCashPayRule.setPayCycle(BussinessConstant.DEFAULT_PAY_CYCLE);
			}

            bussFinRepaymentDto.setFinCashPayRule(finCashPayRule);
        }
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
            &&(StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_零息.getValue())
                ||StringUtil.equals(dto.getCouponSpecies(), DictCodeList.息票品种_贴现.getValue()))){
            FinCashPlan finCashPlanPrincipal = new FinCashPlan();
            finCashPlanPrincipal.setCashId(finCashInfoPrincipal.getCashId());
            finCashPlanPrincipal.setVdateUnadjust(dto.getVdate());
            finCashPlanPrincipal.setMdateUnadjust(dto.getMdate());
            finCashPlanPrincipal.setVdate(dto.getVdate());
            finCashPlanPrincipal.setMdate(dto.getMdate());
            finCashPlanPrincipal.setPayDate(dto.getMdate());
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
    public BusiFinExercisePlanDto encapBusiFinExercisePlan(FinProduct dto,BusiFinExercisePlanDto busiFinExercisePlanDto,FinCashInfo finCashInfoPrincipal,Long branch){
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
     * 封装评级信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param busiFinBondRatingInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BusiFinBondRatingInfoDto encapBusiFinBondRatingInfo(FinProduct dto,BusiFinBondRatingInfoDto busiFinBondRatingInfoDto){
        List<MstBondCreditRating> mstBondCreditRatingList = new ArrayList<MstBondCreditRating>();
        if(null!=busiFinBondRatingInfoDto.getMstBondCreditRatingList()&&!busiFinBondRatingInfoDto.getMstBondCreditRatingList().isEmpty()){
            busiFinBondRatingInfoDto.getMstBondCreditRatingList().forEach(mstBondCreditRatingEntity->{
                mstBondCreditRatingEntity.setoCode(dto.getFinprodMarketId());
                mstBondCreditRatingList.add(mstBondCreditRatingEntity);
            });
        }
        busiFinBondRatingInfoDto.setMstBondCreditRatingList(mstBondCreditRatingList);
        return busiFinBondRatingInfoDto;
    }

    /**
     * 封装附件信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FileCommonDto encapFileCommon(FinProduct dto,List<FileCommonDto>  list){
        FileCommonDto fileCommonDto = new FileCommonDto();
        //文件上传输入参数
          MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
          List<SysUploadFileInfo> fileList  = new ArrayList<>();
          for(FileCommonDto filecCommonDto:list){
                //根据文件路径转换成对应文件
                SysUploadFileInfo file = new SysUploadFileInfo();
                file.setSeqNo(filecCommonDto.getFileNo());
                fileList.add(file);
          }
          mstFileInputParameter.setFileList(fileList);
          mstFileInputParameter.setBussinessId(dto.getFinprodId());
         fileCommonDto.setMstFileInputParameter(mstFileInputParameter);
          return fileCommonDto;
      }

    /**
     * 封装存款类资产报送信息
     * <功能详细描述>
     * @param finProduct
     * @param bussRepDepositInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepDespositAssetInfoDto encapbaseRepDespositAssetInfoDto(FinProduct finProduct,BussRepDepositInfoDto bussRepDepositInfoDto,BigDecimal rate,String basis){
        BaseRepDespositAssetInfoDto baseRepDespositAssetInfoDto = new BaseRepDespositAssetInfoDto();
        RepAssetBankDeposit repAssetBankDeposit = new RepAssetBankDeposit();
        repAssetBankDeposit.setRepFinprodId(finProduct.getFinprodId());
        // 资金存入银行
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("search", finProduct.getIssuer());
        List<SelectOptionDto> list = restTemplate.postForList(ServiceApplication.ASSET_SERVICE_NAME
                + "/AssetCommon/getCustomerName",map,
                SelectOptionDto.class);
        if (list != null && list.size() > 0) {
        	repAssetBankDeposit.setRepBankName(list.get(0).getText());
        }
        repAssetBankDeposit.setRepPrinAmt(finProduct.getIssueAmt());
        repAssetBankDeposit.setRepVdate(finProduct.getVdate());
        repAssetBankDeposit.setRepMdate(finProduct.getMdate());
        repAssetBankDeposit.setRepRateYear(rate.multiply(new BigDecimal(100)));
        if (StringUtil.equals(basis, DictCodeList.计息基础_A_360.getValue())) {
        	repAssetBankDeposit.setRepBasis(DictCodeListExt2.计息基础_中债_ACT_360.getValue());
        } else {
        	repAssetBankDeposit.setRepBasis(DictCodeListExt2.计息基础_中债_ACT_365.getValue());
        }
        if (StringUtil.equals(bussRepDepositInfoDto.getRepDepoType(), DictCodeListExt1.存款类型_结构性存款.getValue())) {
            repAssetBankDeposit.setRepIsStruct(DictCodeList.是否_中债_是.getValue());
        } else {
        	repAssetBankDeposit.setRepIsStruct(DictCodeList.是否_中债_否.getValue());
        }
        //增加存款类型字段 20200402
        repAssetBankDeposit.setRepDepoType(bussRepDepositInfoDto.getRepDepoType());
        repAssetBankDeposit.setRepTargetType(bussRepDepositInfoDto.getRepTargetType());
        repAssetBankDeposit.setRepTarget(bussRepDepositInfoDto.getRepTarget());
        repAssetBankDeposit.setRepRemark(bussRepDepositInfoDto.getRepRemark());
        List<RepAssetBankDeposit> repAssetBankDepositList = new ArrayList<RepAssetBankDeposit>();
        List<String> repFinprodIdList = new ArrayList<String>();
        repAssetBankDepositList.add(repAssetBankDeposit);repFinprodIdList.add(finProduct.getFinprodId());
        baseRepDespositAssetInfoDto.setRepAssetBankDepositList(repAssetBankDepositList);
        baseRepDespositAssetInfoDto.setRepFinprodIdList(repFinprodIdList);
        return baseRepDespositAssetInfoDto;
    }
    /**
     * 封装拆放同业及买入返售报送信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepDlRepoInfoDto encapBussRepDlRepoInfoDto(FinProduct finProduct,BussRepDlRepoInfoDto bussRepDlRepoInfoDto,BigDecimal rate,String basis){
        BaseRepDlRepoInfoDto baseRepDlRepoInfoDto = new BaseRepDlRepoInfoDto();
        RepAssetInterLend repAssetInterLend = new RepAssetInterLend();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepAssetInterLend> repAssetInterLendList = new ArrayList<RepAssetInterLend>();
        repAssetInterLend.setRepFinprodId(finProduct.getFinprodId());
        repAssetInterLend.setRepVdate(finProduct.getVdate());
        repAssetInterLend.setRepMdate(finProduct.getMdate());
        // 对手方
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("search", finProduct.getIssuer());
        List<SelectOptionDto> list = restTemplate.postForList(ServiceApplication.ASSET_SERVICE_NAME
                + "/AssetCommon/getCustomerName",map,
                SelectOptionDto.class);
        if (list != null && list.size() > 0) {
        	repAssetInterLend.setRepTradeTarget(list.get(0).getText());
        }
        repAssetInterLend.setRepRateYear(rate.multiply(new BigDecimal(100)));
        repAssetInterLend.setRepBasis(basis);
        repAssetInterLend.setRepRemark(bussRepDlRepoInfoDto.getRepRemark());
        repFinprodIdList.add(finProduct.getFinprodId());repAssetInterLendList.add(repAssetInterLend);
        baseRepDlRepoInfoDto.setRepDlRepoInfoList(repAssetInterLendList);baseRepDlRepoInfoDto.setRepFinprodIdList(repFinprodIdList);
        return baseRepDlRepoInfoDto;
    }

    /**
     *  封装债券资产中债报送信息
     * <功能详细描述>
     * @param finProduct
     * @param bussFinBondRepInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepBondAssetInfoDto encapBaseRepBondAssetInfoDto(FinProduct finProduct,BussFinBondRepInfoDto bussFinBondRepInfoDto){
        BaseRepBondAssetInfoDto baseRepBondAssetInfoDto = new BaseRepBondAssetInfoDto();
        RepAssetBond repAssetBond = new RepAssetBond();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepAssetBond> repAssetBondList = new ArrayList<RepAssetBond>();
        BeanUtil.copyPropertiesIgnoreNull(repAssetBond, bussFinBondRepInfoDto);
        repAssetBond.setRepFinprodId(finProduct.getFinprodId());
        repAssetBond.setSecId(finProduct.getFinprodMarketId());
        repAssetBond.setRepIssueMode(finProduct.getIssueType());
        repAssetBondList.add(repAssetBond);
        repFinprodIdList.add(finProduct.getFinprodId());
        baseRepBondAssetInfoDto.setRepFinprodIdList(repFinprodIdList);baseRepBondAssetInfoDto.setRepAssetBondList(repAssetBondList);
        return baseRepBondAssetInfoDto;
    }

    /**
     * 封装非标资产(票据或信用)报送信息
     * <功能详细描述>
     * @param finProduct
     * @param bussRepAssetAstBillInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepAssetAstBillInfoDto encapBussRepAssetAstBillInfoDto(FinProduct finProduct,BussRepAssetAstBillInfoDto bussRepAssetAstBillInfoDto){
        BaseRepAssetAstBillInfoDto baseRepAssetAstBillInfoDto = new BaseRepAssetAstBillInfoDto();
        RepAssetAstBill  repAssetAstBill = new RepAssetAstBill();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepAssetAstBill> repAssetAstBillList = new ArrayList<RepAssetAstBill>();
        BeanUtil.copyPropertiesIgnoreNull(repAssetAstBill, bussRepAssetAstBillInfoDto);
        repAssetAstBill.setRepFinprodId(finProduct.getFinprodId());
        //起息日
        repAssetAstBill.setRepVdate(finProduct.getVdate());
        //到期日
        repAssetAstBill.setRepMdate(finProduct.getMdate());
        //金额
        repAssetAstBill.setRepAmtSum(finProduct.getIssueAmt());         
        repAssetAstBillList.add(repAssetAstBill);
        repFinprodIdList.add(finProduct.getFinprodId());
        baseRepAssetAstBillInfoDto.setRepAssetAstBillList(repAssetAstBillList);
        baseRepAssetAstBillInfoDto.setRepFinprodIdList(repFinprodIdList);
        return baseRepAssetAstBillInfoDto;
    }

    /**
     * 封装信贷及非标资产中债报送
     * <功能详细描述>
     * @param finProduct
     * @param bussRepAssetCreditNonstdInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepAssetCreditNonstdInfoDto encapBaseRepAssetCreditNonstdInfoDto(FinProduct finProduct,BussRepAssetCreditNonstdInfoDto bussRepAssetCreditNonstdInfoDto){
        BaseRepAssetCreditNonstdInfoDto baseRepAssetCreditNonstdInfoDto = new BaseRepAssetCreditNonstdInfoDto();
        RepAssetCreditNonstd repAssetCreditNonstd = new RepAssetCreditNonstd();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepAssetCreditNonstd> repAssetCreditNonstdList = new ArrayList<RepAssetCreditNonstd>();
        BeanUtil.copyPropertiesIgnoreNull(repAssetCreditNonstd, bussRepAssetCreditNonstdInfoDto);
        repAssetCreditNonstd.setRepFinprodId(finProduct.getFinprodId());
        //起息日
        repAssetCreditNonstd.setRepVdate(finProduct.getVdate());
        //到期日
        repAssetCreditNonstd.setRepMdate(finProduct.getMdate());
        //金额
        repAssetCreditNonstd.setRepAmt(finProduct.getIssueAmt());
        //项目期限
        if (StringUtil.isNotEmpty(finProduct.getTermDays())){
            repAssetCreditNonstd.setRepTerm(finProduct.getTermDays().intValue());
        }
        
        repAssetCreditNonstdList.add(repAssetCreditNonstd);
        repFinprodIdList.add(finProduct.getFinprodId());
        baseRepAssetCreditNonstdInfoDto.setRepAssetCreditNonstdList(repAssetCreditNonstdList);
        baseRepAssetCreditNonstdInfoDto.setRepFinprodIdList(repFinprodIdList);
        return baseRepAssetCreditNonstdInfoDto;
    }

    /**
     * 封装权益类资产中债报送
     * <一句话功能简述>
     * <功能详细描述>
     * @param finProduct
     * @param bussRepEquityInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepAssetEquityInfoDto encapBaseRepAssetEquityInfoDto(FinProduct finProduct,BussRepEquityInfoDto bussRepEquityInfoDto){
        BaseRepAssetEquityInfoDto baseRepAssetEquityInfoDto = new BaseRepAssetEquityInfoDto();
        RepAssetEquity repAssetEquity = new RepAssetEquity();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepAssetEquity> repAssetEquityList = new ArrayList<RepAssetEquity>();
        BeanUtil.copyPropertiesIgnoreNull(repAssetEquity, bussRepEquityInfoDto);
        repAssetEquity.setRepFinprodId(finProduct.getFinprodId());
        repAssetEquityList.add(repAssetEquity);
        repFinprodIdList.add(finProduct.getFinprodId());
        baseRepAssetEquityInfoDto.setRepAssetEquityList(repAssetEquityList);
        baseRepAssetEquityInfoDto.setRepFinprodIdList(repFinprodIdList);
        return baseRepAssetEquityInfoDto;
    }

    /**
     * 封装其他资产报送信息
     * <功能详细描述>
     * @param finProduct
     * @param bussRepOtherInvestTargetInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepOtherInvestTargetInfoDto encapBaseRepOtherInvestTargetInfoDto(FinProduct finProduct,BussRepOtherInvestTargetInfoDto bussRepOtherInvestTargetInfoDto){
        BaseRepOtherInvestTargetInfoDto baseRepOtherInvestTargetInfoDto = new BaseRepOtherInvestTargetInfoDto();
        RepOtherInvestTarget repOtherInvestTarget = new RepOtherInvestTarget();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepOtherInvestTarget> repOtherInvestTargetList = new ArrayList<RepOtherInvestTarget>();
        BeanUtil.copyPropertiesIgnoreNull(repOtherInvestTarget, bussRepOtherInvestTargetInfoDto);
        //项目类资产，各类资产其他项，REP_OTHER_INVEST_TARGET，金额，起息日，到期日，项目收益率没有存值 yjm start 20190813
        repOtherInvestTarget.setRepMoney(finProduct.getIssueAmt());
        repOtherInvestTarget.setRepValueDate(finProduct.getVdate());
        repOtherInvestTarget.setRepFinprodId(finProduct.getFinprodId());
        repOtherInvestTarget.setRepExpiringDate(finProduct.getMdate());
        //项目类资产，各类资产其他项，REP_OTHER_INVEST_TARGET，金额，起息日，到期日，项目收益率没有存值 yjm end 20190813
        repOtherInvestTargetList.add(repOtherInvestTarget);
        repFinprodIdList.add(finProduct.getFinprodId());
        baseRepOtherInvestTargetInfoDto.setRepOtherInvestTargetList(repOtherInvestTargetList);
        baseRepOtherInvestTargetInfoDto.setRepFinprodIdList(repFinprodIdList);
        return baseRepOtherInvestTargetInfoDto;
    }
    /**
     * 封装基金类资产报送信息
     * <功能详细描述>
     * @param finProduct
     * @param bussRepFundInfoDto
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepFundAssetInfoDto encapBaseRepFundAssetInfoDto(FinProduct finProduct,BussRepFundInfoDto bussRepFundInfoDto){
        BaseRepFundAssetInfoDto baseRepFundAssetInfoDto = new BaseRepFundAssetInfoDto();
        RepFund repFund= new RepFund();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepFund> repFundList = new ArrayList<RepFund>();
        BeanUtil.copyPropertiesIgnoreNull(repFund, bussRepFundInfoDto);
        repFund.setRepFinprodId(finProduct.getFinprodId());
        repFundList.add(repFund);
        repFinprodIdList.add(finProduct.getFinprodId());
        baseRepFundAssetInfoDto.setRepFundList(repFundList);
        baseRepFundAssetInfoDto.setRepFinprodIdList(repFinprodIdList);
        return baseRepFundAssetInfoDto;
    }

    /**
     *  封装计划类资产中债报送信息
     * <功能详细描述>
     * @param finProduct
     * @param bussRepAssetMngProdInfo
     * @return
     * @see
     * @since 1.0
     */
    public BaseRepMngAssetPlanInfoDto encapBaseRepMngAssetPlanInfoDto(FinProduct finProduct, BussRepAssetMngProdInfo bussRepAssetMngProdInfo){
        BaseRepMngAssetPlanInfoDto baseRepMngAssetPlanInfoDto = new BaseRepMngAssetPlanInfoDto();
        RepAssetMngPlan repAssetMngPlan = new RepAssetMngPlan();
        List<String> repFinprodIdList = new ArrayList<String>();
        List<RepAssetMngPlan> repAssetMngPlanList = new ArrayList<RepAssetMngPlan>();
        BeanUtil.copyPropertiesIgnoreNull(repAssetMngPlan, bussRepAssetMngProdInfo);
        if(StringUtil.isNotEmpty(bussRepAssetMngProdInfo.getRepMaxRate())) {
            repAssetMngPlan.setRepMaxRate(bussRepAssetMngProdInfo.getRepMaxRate().divide(new BigDecimal(100)));
        }
        if(StringUtil.isNotEmpty(bussRepAssetMngProdInfo.getRepMinRate())) {
            repAssetMngPlan.setRepMinRate(bussRepAssetMngProdInfo.getRepMinRate().divide(new BigDecimal(100)));
        }
        repAssetMngPlan.setRepMgrFeeRate(bussRepAssetMngProdInfo.getRepMgrFeeRate().divide(new BigDecimal(100)));
        repAssetMngPlan.setRepTruFeeRate(bussRepAssetMngProdInfo.getRepTruFeeRate().divide(new BigDecimal(100)));
        repAssetMngPlan.setRepTradeFeeRate(bussRepAssetMngProdInfo.getRepTradeFeeRate().divide(new BigDecimal(100)));
        repAssetMngPlan.setRepAgencyFeeRate(bussRepAssetMngProdInfo.getRepAgencyFeeRate().divide(new BigDecimal(100)));
        repAssetMngPlan.setRepOtherFeeRate(bussRepAssetMngProdInfo.getRepOtherFeeRate().divide(new BigDecimal(100)));
        repAssetMngPlan.setRepFinprodId(finProduct.getFinprodId());
        repAssetMngPlan.setRepManagerId(finProduct.getIssuer());
        repAssetMngPlan.setRepTrusteeId(finProduct.getTrusteeId());
        repAssetMngPlan.setRepSetDate(finProduct.getVdate());
        repAssetMngPlan.setRepEndDate(finProduct.getMdate());
        repAssetMngPlanList.add(repAssetMngPlan);
        repFinprodIdList.add(finProduct.getFinprodId());
        baseRepMngAssetPlanInfoDto.setRepFinprodIdList(repFinprodIdList);baseRepMngAssetPlanInfoDto.setRepAssetMngPlanList(repAssetMngPlanList);
        return baseRepMngAssetPlanInfoDto;
    }

    /**
     * 封装中债新增信息
     */
    public BaseRepOtherDto encapBaseRepOtherDto(FinProduct finProduct,RepAssetOther repAssetOther){
        BaseRepOtherDto baseRepOtherDto = new BaseRepOtherDto();
        List<RepAssetOther> repAssetOtherList = new ArrayList<RepAssetOther>();
        List<String> finprodIdList = new ArrayList<String>();
        repAssetOther.setRepFinprodId(finProduct.getFinprodId());
        repAssetOtherList.add(repAssetOther);finprodIdList.add(finProduct.getFinprodId());
        baseRepOtherDto.setRepAssetOtherList(repAssetOtherList);
        baseRepOtherDto.setRepFinprodIdList(finprodIdList);
        return baseRepOtherDto;
    }

    /**
     * 封装负债信息
     */
    public BaseRepAssetDeptInfoDto encapbaseRepAssetDeptInfoDto(FinProduct finProduct,String repAssetType,String repTradeMarket){
        BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = new BaseRepAssetDeptInfoDto();
        List<RepAssetDept> repAssetDeptList = new ArrayList<RepAssetDept>();
        List<String> finprodIdList = new ArrayList<String>();
        RepAssetDept repAssetDept = new RepAssetDept();
        repAssetDept.setRepFinprodId(finProduct.getFinprodId());
        repAssetDept.setFinprodMarketId(finProduct.getFinprodMarketId());
        repAssetDept.setRepFinprodName(finProduct.getFinprodName());
        repAssetDept.setRepCcy(finProduct.getCcy());
        repAssetDept.setRepAssetType(repAssetType);
        repAssetDept.setRepTradeMarket(repTradeMarket);
        repAssetDept.setRepIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        repAssetDept.setIsRepTarget(DictCodeList.是否_否.getValue());//是否报送对象
        repAssetDeptList.add(repAssetDept);finprodIdList.add(finProduct.getFinprodId());
        baseRepAssetDeptInfoDto.setRepAssetDeptList(repAssetDeptList);
        baseRepAssetDeptInfoDto.setFinprodIdList(finprodIdList);
        return baseRepAssetDeptInfoDto;
    }

    /**
     * 封装参与方信息
     */
    public BussParticipantInfoDto enBussParticipantInfoDto(FinProduct finProduct,BussParticipantInfoDto bussParticipantInfoDto,Long branch){
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        Date vDate = finProduct.getVdate();
        for(BussParticipantInfoDto entity:bussParticipantInfoDto.getBussParticipantInfoDtoList()){
            String basis = entity.getBasis();
            Long chlLevel = entity.getChlLevel();
            String yes = DictCodeList.是否_是.getValue();
            String calcType = DictCodeList.利率类型_固定利率.getValue();
            String baseType = DictCodeList.基数类型_实收资本.getValue();
            String baseDateType = DictCodeList.基数日期类型_当日.getValue();
            String remark = entity.getRemark();
            BigDecimal managerYield = ArithmeticUtil.divide(entity.getManagerYield(), new BigDecimal(100), 14);
            BigDecimal  clientYield = ArithmeticUtil.divide(entity.getClientYield(), new BigDecimal(100), 14);
            BigDecimal otherYield =  ArithmeticUtil.divide(entity.getOtherYield(), new BigDecimal(100), 14);
            //交易对手对应的金融产品代码
            String chlFinprodId = entity.getCounterId();
            //托管费计算规则
            FinCashCalcRule finCashCalcRuleClient = new FinCashCalcRule();
            //管理费计算规则
            FinCashCalcRule finCashCalcRuleManager = new FinCashCalcRule();
            //其他费计算规则
            FinCashCalcRule finCashCalcRuleOther = new FinCashCalcRule();
            //通道托管费现金流
            FinCashInfo finCashInfoClient = encapFinCashInfo(finProduct,DictCodeListExt1.现金流业务类型_金融产品.getValue(),DictCodeListExt1.现金流一级类型_费用.getValue(),DictCodeListExt1.现金流二级类型_通道托管费.getValue(),branch,entity.getCounterId());
            //通道管理费现金流
            FinCashInfo finCashInfoManager = encapFinCashInfo(finProduct,DictCodeListExt1.现金流业务类型_金融产品.getValue(),DictCodeListExt1.现金流一级类型_费用.getValue(),DictCodeListExt1.现金流二级类型_通道管理费.getValue(),branch,entity.getCounterId());
            //通道其他费现金流
            FinCashInfo finCashInfoOther = encapFinCashInfo(finProduct,DictCodeListExt1.现金流业务类型_金融产品.getValue(),DictCodeListExt1.现金流一级类型_费用.getValue(),DictCodeListExt1.现金流二级类型_通道其他费.getValue(),branch,entity.getCounterId());
            finCashInfoClient.setChlLevel(chlLevel);
            //finCashInfoClient.setChlFeeRetuenDeduct(yes);
            finCashInfoClient.setChlFinprodId(chlFinprodId);
            finCashInfoManager.setChlLevel(chlLevel);
            //finCashInfoManager.setChlFeeRetuenDeduct(yes);
            finCashInfoManager.setChlFinprodId(chlFinprodId);
            finCashInfoOther.setChlLevel(chlLevel);
            //finCashInfoOther.setChlFeeRetuenDeduct(yes);
            finCashInfoOther.setChlFinprodId(chlFinprodId);
            BeanUtil.copyPropertiesIgnoreNull(finCashCalcRuleClient, finProduct);
            BeanUtil.copyPropertiesIgnoreNull(finCashCalcRuleManager, finProduct);
            BeanUtil.copyPropertiesIgnoreNull(finCashCalcRuleOther, finProduct);
            finCashCalcRuleClient.setCalcType(calcType);finCashCalcRuleClient.setBaseType(baseType);finCashCalcRuleClient.setBaseDateType(baseDateType);
            finCashCalcRuleClient.setYield(clientYield); finCashCalcRuleClient.setIsInitial(yes);finCashCalcRuleClient.setRemark(remark);finCashCalcRuleClient.setBasis(basis);
         // 一体化对应etliu
            finCashCalcRuleClient.setBranch(branch);/*finCashCalcRuleClient.setRelaeseDate(vDate);*/finCashCalcRuleClient.setEffDate(vDate);
            finCashCalcRuleClient.setCashId(finCashInfoClient.getCashId());
            finCashCalcRuleManager.setCalcType(calcType);finCashCalcRuleManager.setBaseType(baseType);finCashCalcRuleManager.setBaseDateType(baseDateType);
            finCashCalcRuleManager.setYield(managerYield);finCashCalcRuleManager.setIsInitial(yes);finCashCalcRuleManager.setRemark(remark);
         // 一体化对应etliu
            finCashCalcRuleManager.setBranch(branch);/*finCashCalcRuleManager.setRelaeseDate(vDate);*/finCashCalcRuleManager.setEffDate(vDate);finCashCalcRuleManager.setBasis(basis);
            finCashCalcRuleManager.setCashId(finCashInfoManager.getCashId());
            finCashCalcRuleOther.setCalcType(calcType);finCashCalcRuleOther.setBaseType(baseType);finCashCalcRuleOther.setBaseDateType(baseDateType);
            finCashCalcRuleOther.setYield(otherYield);finCashCalcRuleOther.setIsInitial(yes);finCashCalcRuleOther.setRemark(remark);
         // 一体化对应etliu
            finCashCalcRuleOther.setBranch(branch);/*finCashCalcRuleOther.setRelaeseDate(vDate);*/finCashCalcRuleOther.setEffDate(vDate);finCashCalcRuleOther.setBasis(basis);
            finCashCalcRuleOther.setCashId(finCashInfoOther.getCashId());
            finCashInfoList.add(finCashInfoClient);finCashInfoList.add(finCashInfoManager);finCashInfoList.add(finCashInfoOther);
            finCashCalcRuleList.add(finCashCalcRuleClient);finCashCalcRuleList.add(finCashCalcRuleManager);finCashCalcRuleList.add(finCashCalcRuleOther);
        }
        bussParticipantInfoDto.setFinCashInfoList(finCashInfoList);bussParticipantInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
        return bussParticipantInfoDto;
    }

    /**
     * 组装现金流信息
     * @see
     * @since 1.0
     */
    public FinCashInfo encapFinCashInfo(FinProduct dto,String commonBusinessType,String cashType1,String cashType2,Long branch,String counterId){
        FinCashInfo finCashInfo = new FinCashInfo();
        //工作流ID
        String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId()+counterId, commonBusinessType, branch, cashType2);
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        return  finCashInfo;
    }

   /**
    * 重新计算付息计划
    * <功能详细描述>
    * @param scai  资产公共信息
    * @param finAssetRateAdjList 报价信息
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
        List<String> holidayList = dateCalService.getDealCalendarHoliday(scai.getVdate(),scai.getMdate(),scai.getCalendarId());
        // 平台日期
        Date platDate = iPlatformDate.getSysDate();
        // 计息基础
        //债券
        if(StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            if(StringUtil.equals(scai.getBasis(), DictCodeList.计息基础_A_A_平均分配.getValue())){
                scai.setBasis(DictCodeList.计息基础_A_A.getValue());
                scai.setIntpayRule(DictCodeListExt1.利息分配方式_平均分配.getValue());
            }else if(StringUtil.equals(scai.getBasis(), DictCodeList.计息基础_A_A_按实际天数分配.getValue())){
                scai.setBasis(DictCodeList.计息基础_A_A.getValue());
                scai.setIntpayRule(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
            }
        }
        // 报价信息
        List<AssetCommonInvRateAdjustDto> rateList = new ArrayList<AssetCommonInvRateAdjustDto>();
        for (FinCashCalcRule finCashCalcRule : finAssetRateAdjList) {
            AssetCommonInvRateAdjustDto invRateQuo = new AssetCommonInvRateAdjustDto();
            invRateQuo.setEffDate(finCashCalcRule.getEffDate());
            invRateQuo.setYield(finCashCalcRule.getYield());
            invRateQuo.setBasis(scai.getBasis());
            if(DateUtil.compareDate(scai.getVdate(), finCashCalcRule.getEffDate())==0){
                scai.setRate(finCashCalcRule.getYield());
            }
            rateList.add(invRateQuo);
        }
        //付息规则
        FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
            scai.getFinprodId(), DateUtil.format(scai.getVdate()), DictCodeListExt1.现金流二级类型_利息.getValue(),
            DictCodeListExt1.现金流业务类型_金融产品.getValue());
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
        if(null!=invRepayList){
            repayCashId = invRepayList.get(0).getCashId();
            for(FinCashPlan entity:invRepayList){
                FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto, entity);
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
        //1.重新计算还本区间利息
        invRepayList = RepayScheduleCal.cal(scai, invRepayList, is, rateList, scai.getAmount(), FinCashPlanDto.class, FinCashPlanDetailDto.class);
        finCashPlanList.clear();finCashPlanRepayList.clear();
        //2.重新计算付息区间利息
        scheList = InterestScheduleCalReset.interestScheduleCalA(scheList, scai, invRepayList, null,
            rateList, is, holidayList,platDate, null, FinCashPlanDetailDto.class);
        // 计算完整周期天数，年付息次数
        String finprodType2 =  "";
        if(StringUtil.isNotEmpty(scheList)){
            for(FinCashPlanDto it:scheList){
                if(StringUtil.isNotEmpty(it.getFinprodType2())){
                    finprodType2=it.getFinprodType2();
                    break;
                }
            }
        }

        for (FinCashPlanDto entity2 : scheList) {
            entity2.setCashId(intCashId);
            entity2.setTermdays(DateUtil.getBetweenDayNumber(entity2.getVdate(), entity2.getMdate()));
            entity2.setFrequency((long)scheList.size());
            // yjm 添加计算净投资收益率 start
            if(StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                    ||StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                    ||StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                //计算区间净投资收益率%(加权利率)
                BigDecimal rangeYield = ArithmeticUtil.multiply(WeightedInterestRateCal.cal(rateList, entity2.getVdate(), entity2.getMdate()),new BigDecimal("100"));
                entity2.setRangeYield(rangeYield);
            }
            // yjm 添加计算净投资收益率 end
        }
        for (FinCashPlanDto entity3 : invRepayList) {
            entity3.setCashId(repayCashId);
            entity3.setTermdays(DateUtil.getBetweenDayNumber(entity3.getVdate(), entity3.getMdate()));
            entity3.setFrequency((long)scheList.size());
        }
        scai.setInvList(scheList);
        scai.setInvRepayList(invRepayList);
        return scai;
    }

    /**
     * 计算现金流基数
     * <功能详细描述>
     * @param finProduct 融资金额
     * @param lis  付息信息
     * @param lirs 还本信息
     * @return
     * @see
     * @since 1.0
     */
    public List<FinCashPlan> calCashBaseamt(FinProduct finProduct,List<FinCashPlan> lis,List<FinCashPlan> lirs){
        BigDecimal papi = finProduct.getIssueAmt();
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        //排序
        Collections.sort(lis, new Comparator<FinCashPlan>() {
            @Override
            public int compare(FinCashPlan o1, FinCashPlan o2) {
                return o1.getVdate().compareTo(o2.getVdate());
            }
        });
        Collections.sort(lirs, new Comparator<FinCashPlan>() {
            @Override
            public int compare(FinCashPlan o1, FinCashPlan o2) {
                return o1.getMdate().compareTo(o2.getMdate());
            }
        });
        for(FinCashPlan finCashPlanLis : lis){
            for(FinCashPlan finCashPlanLris : lirs){
                if(DateUtil.compareDate(finCashPlanLris.getMdate(), finProduct.getMdate())==0){
                    finCashPlanLris.setCashBaseamt(papi);
                }else{
                    if(DateUtil.compareDate(finCashPlanLis.getVdate(), finCashPlanLris.getMdate())<1
                        &&DateUtil.compareDate(finCashPlanLis.getMdate(), finCashPlanLris.getMdate())==1){
                        finCashPlanLris.setCashBaseamt(papi);
                        papi = ArithmeticUtil.subtract(papi, finCashPlanLris.getPrinAmt());
                    }
                }
            }
            finCashPlanLis.setCashBaseamt(papi);
        }
        finCashPlanList.addAll(lis);finCashPlanList.addAll(lirs);
        return finCashPlanList;
    }

    /**
     * 批复号查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPflshList(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.ASSET_SERVICE_NAME
            + "/AssetCommon/getPflshList",param,
            SelectOptionDto.class);
    }
    /**
     * 实际授信主体
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getActualCreditProvid(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.ASSET_SERVICE_NAME
            + "/AssetCommon/getActualCreditProvid",param,
            SelectOptionDto.class);
    }

    /**
     * 增信信息
     * @param dto
     * @param bussFinProductCreditDto
     * @return
     */
    public BussFinProductCreditDto encapBussFinProductCredit(FinProduct dto, BussFinProductCreditDto bussFinProductCreditDto,Long branch){
        List<FinProductCredit> FinProductCreditList = new ArrayList<FinProductCredit>();
        if(null!=bussFinProductCreditDto.getFinProductCreditList()&&!bussFinProductCreditDto.getFinProductCreditList().isEmpty()){
            bussFinProductCreditDto.getFinProductCreditList().forEach(finProductCredit->{
                finProductCredit.setFinprodId(dto.getFinprodId());
                finProductCredit.setBranch(branch);
                FinProductCreditList.add(finProductCredit);
            });
        }
        bussFinProductCreditDto.setFinProductCreditList(FinProductCreditList);
        return bussFinProductCreditDto;
    }

    /**
     * 获取资产到期日 
     * yjm 20191014
     * @param finprodId
     * @return
     */
    public AssetCommonInfoDto getMdateByFinproId(String finprodId){
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME + 
                "/AssetCommon/getMdateByFinproId?finprodId=" + finprodId,AssetCommonInfoDto.class);
    }
    /**
     * 自动创建管理人或发行人或融资人的交易对手 add by hxy 2020422
     */
    public void autoCreateTradeCounter(String counterId){
    	MstCounterParty mstCounterParty=new MstCounterParty();
		mstCounterParty=mstCounterPartyMapper.selectPType(counterId);
        if(null!=mstCounterParty && StringUtil.isNotEmpty(mstCounterParty.getCounterName())){
        	//判断交易对手表中是否存在
			int flag=trdFinProductDealTotalMapper.selectCounterName(mstCounterParty.getCounterName().toString());
			if(flag == 0){
				//根据管理人，查询机构基本性质
				BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
    	        bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_交易对手管理);
    	        mstCounterParty.setCounterId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
                mstCounterParty.setCreateUser(LoginUserUtil.getUserName());
	    		mstCounterParty.setCreateDept(LoginUserUtil.getDeptCode());
	    		mstCounterParty.setUpdateUser(LoginUserUtil.getUserName());
	    		mstCounterParty.setCreateTime(new Date());
	    		mstCounterParty.setUpdateTime(new Date());
	    		mstCounterPartyMapper.insertSelective(mstCounterParty);


                // 二级法人权限处理
                if(MultilevelDataAuthorityUtil.isEnabled()) {
                    //创建机构代码
                    String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(mstCounterParty.getCreateUser());
                    mstCounterParty.setCreateInst(createInst);
                    // 有查询权限的机构代码
                    List<String> searchOrgCodeList = null;
                    // 有编辑权限的机构代码
                    List<String> editOrgCodeList = null;
                    // 新建或修改时重新设置二级法人权限，删除时则清空
                        //获取所有父级机构。  拥有查看权限
                        searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                        // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                        editOrgCodeList = Arrays.asList(mstCounterParty.getCreateInst());

                    // 记录二级法人权限
                    insertDataAuthTypeInst(mstCounterParty.getCounterId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_交易对手管理.getValue());
                }

			}
        }
    }
}
