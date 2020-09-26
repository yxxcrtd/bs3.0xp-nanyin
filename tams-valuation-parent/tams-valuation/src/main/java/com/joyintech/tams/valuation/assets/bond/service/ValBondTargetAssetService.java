/*
 * 文件名：ValBondTargetAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.assets.bond.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
//import com.joyintech.tams.invest.asset.common.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
//import com.joyintech.tams.component.business.common.service.CommonAutoCouponService;
//import com.joyintech.tams.component.business.core.dto.FileCommonDto;
//import com.joyintech.tams.component.business.core.service.MstFileCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.assets.bond.dto.ValBondTargetAssetDto;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.common.calculator.AssetInterestCal;
import com.joyintech.tams.valuation.component.common.constant.BussinessConstant;
import com.joyintech.tams.valuation.component.common.constant.BussinessDictCodeList;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.component.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import com.joyintech.tams.valuation.component.common.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.tams.valuation.component.common.dto.XmlRelationInputParaDto;
import com.joyintech.tams.valuation.component.common.service.ValCommonBusinessService;
import com.joyintech.tams.valuation.component.common.service.ValDateCalService;
import com.joyintech.tams.valuation.component.element.dto.ValBusiFinExercisePlanDto;
import com.joyintech.tams.valuation.component.element.dto.ValBussFinRepaymentDto;
import com.joyintech.tams.valuation.component.element.dto.ValFinInterestFactorDto;
import com.joyintech.tams.valuation.component.element.dto.ValFinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.element.interf.ValBusiFinExercisePlanService;
import com.joyintech.tams.valuation.component.element.interf.ValBussFinRepaymentService;
import com.joyintech.tams.valuation.component.element.interf.ValFinInterestFactorService;
import com.joyintech.tams.valuation.component.element.interf.ValFinPayInterestStdInfoService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 债券标的信息新建
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see ValBondTargetAssetService
 * @since 1.0
 */
@Service
public class ValBondTargetAssetService extends CommonExportService<ValBondTargetAssetDto>
implements BaseProcessService<ValBondTargetAssetDto>{
//    @Autowired
//    private CommonAutoCouponService commonAutoService;
    
    @Autowired
    private ValDateCalService dateCalService;

    @Autowired
    private ValCommonBusinessService commonBusinessService;

    /**
     * 共通--资产
     */
//    @Autowired
//    private AssetCommonService assetCommonService;

    /**
     * 业务组件--计息要素
     */
    @Autowired(required = false)
    private ValFinInterestFactorService finInterestFactorService;

    /**
     * 业务--付息信息
     */
    @Autowired(required = false)
    private ValFinPayInterestStdInfoService finPayInterestStdInfoService;

    /**
     * 业务--还本信息
     */
    @Autowired(required = false)
    private ValBussFinRepaymentService bussFinRepaymentService;
    /**
     * 业务--行权计划
     */
    @Autowired(required = false)
    private ValBusiFinExercisePlanService busiFinExercisePlanService;
    /**
     * 附件
     */
//    @Autowired(required = false)
//    private MstFileCommonService fileCommonService;

    // 债券新建新增快捷录入功能 add by renping 20191010 start
    /**
     * FileAttachmentProcess
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    // 债券新建新增快捷录入功能 add by renping 20191010 end
//    /**
//     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
//     *
//     * @param pageParam pageParam
//     * @return Page类 Page
//     * @throws Exception 异常信息
//     */
//    public Page<FinBondTargetAssetDto> selectForPage(Map<String, Object> pageParam) {
//        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
//        pageParam.put("cashType",DictCodeListExt1.现金流二级类型_利息.getValue());
//        pageParam.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());//现金流业务类型
//        if (StringUtil.isNotEmpty(pageParam.get("type4"))) {
//            pageParam.put("type4", StringUtil.splitToList(null, pageParam.get("type4").toString()));
//        }else{
//            pageParam.remove("type4");
//        }
//        Page<FinBondTargetAssetDto> page = restTemplate.postForPage(
//                ServiceApplication.VALUATION_SERVICE_NAME + "/FinBondTargetAsset/findPage",
//                pageParam, FinBondTargetAssetDto.class);
//        // type9-11 created by wangguiqi on 2019/7/19 -end
//        List<FinBondTargetAssetDto> finBondTargetAssetDtoList = page.getRoot();
//        List<FinBondTargetAssetDto> finBondTargetAssetDtoListTemp = new ArrayList<>();
//        for(FinBondTargetAssetDto dto : finBondTargetAssetDtoList ){
//            Map<String,Object> param = new HashMap<String,Object>();
//            param.put("oCode", dto.getIssuer());
//            param.put("oType", DictCodeList.评级类型_主体评级.getValue());
//            Map<String,Object> zxparam = new HashMap<String,Object>();
//            zxparam.put("oCode", dto.getFinprodMarketId());
//            zxparam.put("oType", DictCodeList.评级类型_债项评级.getValue());
//            finBondTargetAssetDtoListTemp.add(dto);
//        }
//        page.setRoot(finBondTargetAssetDtoListTemp);
//        return page;
//    }

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValBondTargetAssetDto> findPage(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/findPage",
                params, ValBondTargetAssetDto.class);
    }
    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(ValBondTargetAssetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        entity = encapBaseFinProductBaseInfoList(entity);
        String basis = StringUtil.EMPTY;
        String basisStyle = entity.getFinInterestFactorDto().getBasisStyle();
        if(StringUtil.equals(basisStyle, DictCodeListExt1.利息分配方式_平均分配.getValue())){
            basis = DictCodeList.计息基础_A_A_平均分配.getValue();
        }else if(StringUtil.equals(basisStyle, DictCodeListExt1.利息分配方式_按照实际天数分配.getValue())){
            basis = DictCodeList.计息基础_A_A_按实际天数分配.getValue();
        }else{
            basis = entity.getFinInterestFactorDto().getBasis();
        }
        for(BaseFinProductBaseInfoDto baseFinProductBaseInfoEntity:entity.getBaseFinProductBaseInfoList()){
            for(FinRateInfo finRateInfo:baseFinProductBaseInfoEntity.getFinRateInfoList()){
                finRateInfo.setBasis(basis);
            }
            for(FinCashCalcRule finCashCalcRule:baseFinProductBaseInfoEntity.getFinCashCalcRuleList()){
                finCashCalcRule.setBasis(basis);
            }
        }
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }

        // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
        if (!StringUtil.isEmptyList(entity.getBaseFinProductBaseInfoList())) {
        	for (BaseFinProductBaseInfoDto productBaseInfoDto : entity.getBaseFinProductBaseInfoList()) {
				if (!StringUtil.isEmptyList(productBaseInfoDto.getFinCashPayRuleList())) {
					for (FinCashPayRule finCashPayRule : productBaseInfoDto.getFinCashPayRuleList()) {
						if (StringUtil.isBlank(finCashPayRule.getPayCycle())) {
							finCashPayRule.setPayCycle(BussinessConstant.DEFAULT_PAY_CYCLE);
						}
					}
				}
			}
        }

        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/" + action, entity,
            Integer.class);

    }

    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public ValBondTargetAssetDto getBasicInfo(String finprodId){
        return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME
            + "/ValBondTargetAsset/getBasicInfo?finprodId="
            + finprodId,
            ValBondTargetAssetDto.class);
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
    public ValFinInterestFactorDto encapFinInterestFactor(FinProduct dto,ValFinInterestFactorDto finInterestFactorDto,FinCashInfo finCashInfoInterest,FinCashInfo finCashInfoRefer,Long branch){
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
    public ValFinPayInterestStdInfoDto encapFinPayInterestStdInfo(FinProduct dto,ValFinPayInterestStdInfoDto finPayInterestStdInfoDto,FinCashInfo finCashInfoPrincipal,FinCashInfo finCashInfoInterest,Long branch){
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
        };
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
    public ValBussFinRepaymentDto encapBussFinRepayment(FinProduct dto,ValBussFinRepaymentDto bussFinRepaymentDto,FinCashInfo finCashInfoPrincipal,Long branch){
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
   public ValBusiFinExercisePlanDto encapBusiFinExercisePlan(FinProduct dto,ValBusiFinExercisePlanDto busiFinExercisePlanDto,FinCashInfo finCashInfoPrincipal,Long branch){
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
     * 封装基础组件-标的类金融产品需要的DTO
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ValBondTargetAssetDto encapBaseFinProductBaseInfoList(ValBondTargetAssetDto dto){
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        //现金流信息
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        //现金流支付计划
        List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
        //标的类金融产品entity
        FinProduct finProduct = new FinProduct();
        BeanUtil.copyPropertiesIgnoreNull(finProduct, dto);
        //期限
        finProduct.setTermDays(DateUtil.getBetweenDayNumber(dto.getVdate(),dto.getMdate()));
        finProduct.setIssueAmt(ArithmeticUtil.multiply(dto.getIssueAmt(),new BigDecimal("10000")));
        // 一体化对应etliu start
        // 债券新建画面，入库时，标的类金融产品表的含权债摊销日字段，当含权债时，登数据字典【含权债摊销日.最近行权日】
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), finProduct.getIsRight())) {
            finProduct.setExeDate(CacheUtil.getSysParam("ISSECTOEXEDATE") == null ?
                    DictCodeList.含权债摊销日_最近行权日.getValue() : CacheUtil.getSysParam("ISSECTOEXEDATE"));
        }
        // 实际到期日:先存成到期日
        finProduct.setActmdate(finProduct.getMdate());
        // 一体化对应etliu end

        //金融产品分类表entity
        FinProductType finProductType = new FinProductType();
        BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);finProductType.setBranch(branch);
        finProductType.setType6(dto.getType4());finProductType.setType7(dto.getType5());
        finProductType.setType8(DictCodeList.平安资产类型_债券.getValue());
        //金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        //----add by jjpei 20190727 添加投资性质----start----
        if(StringUtil.isNotBlank(dto.getType4())&&DictCodeList.WIND一级分类_含债券一级分类等_优先股.getValue().equals(dto.getType4())){
            finProductAdd.setInvestNature(DictCodeList.投资性质_权益类.getValue());
        }
        else{
            finProductAdd.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
        }
        //----add by jjpei 20190727 添加投资性质----end----
        //现金流信息Entity-本金
        FinCashInfo finCashInfoPrincipal = encapFinCashInfo(dto,DictCodeListExt1.现金流一级类型_本金.getValue(),DictCodeListExt1.现金流二级类型_本金.getValue(),branch);
        //现金流信息Entity-利息
        FinCashInfo finCashInfoInterest = encapFinCashInfo(dto,DictCodeListExt1.现金流一级类型_利息.getValue(),DictCodeListExt1.现金流二级类型_利息.getValue(),branch);
        finCashInfoList.add(finCashInfoPrincipal);finCashInfoList.add(finCashInfoInterest);
        //计息要素DTO 现金流利率信息  现金流计算规则
        if(null!=finInterestFactorService){
            if(null!=dto.getFinInterestFactorDto()){
                ValFinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorDto(dto.getFinInterestFactorDto());
                finInterestFactorDto = encapFinInterestFactor(finProduct,finInterestFactorDto,finCashInfoInterest,null, branch);
                //息票品种
                finProduct.setCouponSpecies(finInterestFactorDto.getCouponSpecies());
                List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
                finRateInfoList.addAll(finInterestFactorDto.getRateList());
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                finCashCalcRuleList.addAll(finInterestFactorDto.getCalcRuleList());
                baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
            }
        }

        //付息信息DTO 现金流支付规则 现金流支付支付计划
        if(null!=finPayInterestStdInfoService){
            ValFinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoDto(dto.getFinPayInterestStdInfoDto());
            finPayInterestStdInfoDto = encapFinPayInterestStdInfo(finProduct,finPayInterestStdInfoDto,finCashInfoPrincipal, finCashInfoInterest, branch);
            baseFinProductBaseInfoDto.setFinCashPayRuleList(finPayInterestStdInfoDto.getFinCashPayRuleList());
            finCashPlanList.addAll(finPayInterestStdInfoDto.getFinCashPlan2List());
        }
        //还本信息DTO 现金流支付支付计划
        if(null!=bussFinRepaymentService){
            ValBussFinRepaymentDto bussFinRepaymentDto = encapBussFinRepayment(finProduct, dto.getBussFinRepaymentDto(), finCashInfoPrincipal, branch);
            finCashPlanList.addAll(bussFinRepaymentDto.getFinCashPlan2List());
        }
        //行权计划DTO 行权计划
        if(null!=busiFinExercisePlanService){
            if(null!=dto.getBusiFinExercisePlanDto()){
                ValBusiFinExercisePlanDto busiFinExercisePlanDto= encapBusiFinExercisePlan(finProduct, dto.getBusiFinExercisePlanDto(), finCashInfoPrincipal, branch);
                baseFinProductBaseInfoDto.setFinExercisePlanList(busiFinExercisePlanDto.getFinExercisePlanList());
            }
        }
        //评级信息
        //主体评级
        String gradeResult = "";
//        //附件信息
//        if(null!=fileCommonService){
//            if(null!=dto.getFileCommonList()){
//                FileCommonDto fileCommonDto = assetCommonService.encapFileCommon(finProduct,dto.getFileCommonList());
//                dto.setMstFileInputParameter(fileCommonDto.getMstFileInputParameter());
//            }
//        }
        //转债信息
        if(StringUtil.equals(dto.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())
                ||StringUtil.equals(dto.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue())){
            dto.getFinProductConvertible().setFinprodId(dto.getFinprodId());
        }
        baseFinProductBaseInfoDto.setFinProduct(finProduct);
        baseFinProductBaseInfoDto.setFinProductType(finProductType);
        baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
        baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
        return dto;
    }

    /**
     * 组装现金流信息
     * @see
     * @since 1.0
     */
    public FinCashInfo encapFinCashInfo(ValBondTargetAssetDto dto,String cashType1,String cashType2,Long branch){
        FinCashInfo finCashInfo = new FinCashInfo();
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, cashType2);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        return  finCashInfo;
    }


    /**
     * 生成金融产品代码/市场代码
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ValBondTargetAssetDto getFinprodId(ValBondTargetAssetDto dto){
        String finprodId = StringUtil.EMPTY;
        String  finprodMarketId = dto.getFinprodMarketId();
        finprodId = commonBusinessService!=null?commonBusinessService.getFinProdId(finprodMarketId, DictCodeListExt1.金融产品类型_投管用_债券.getValue(), dto.getChlFinprodId()):IDGenerator.generate();
        dto.setFinprodId(finprodId);
        return dto;
    }

    /**
     *  市场代码是否重复
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public int selectCountByfinprodMarketId(String finprodMarketId){
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectCountByfinprodMarketId?finprodMarketId="+finprodMarketId,
            Integer.class);
    }

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.getSelectParam().put("finprodType2", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
		String isShowType = (String) exportParam.getSelectParam().get("isShowType");
		if (StringUtil.equals(isShowType, "01")) {
			exportParam.config("/com/joyintech/tams/invest/asset/bond/conf/export_conf.xml")
					.dataSource(DataSourceType.MASERT);
		} else {
			exportParam.config("/com/joyintech/tams/invest/asset/bond/conf/export_conf002.xml")
					.dataSource(DataSourceType.MASERT);
		}
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ValBondTargetAssetDto> selectPageData(Map<String, Object> selectParam) {
        try {
			return findPage(selectParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 获取wind资产信息(债券)
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public InterfPayhsrcsecinfoDto selectWindBondAssetBySecId(String secid){
        return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectWindBondAssetBySecId?secid="+secid,
            InterfPayhsrcsecinfoDto.class);
    }
    
    /**
     * wind债券行权计划
     * <功能详细描述>
     * @param secid 债券id
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfPayhsrcsecoption> selectWindBondExercise(String secid){
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectWindBondExercise?secid="+secid,
        		InterfPayhsrcsecoption.class);
    }
    
    /**
     * wind债券付息计划
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfPayhsrcsecschedule> selectWindBondSchedule(String secid){
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectWindBondSchedule?secid="+secid,
            InterfPayhsrcsecschedule.class);
    }
    
    /**
     * WIND_浮息债报价表
     * <功能详细描述>
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfFloatcouprate> selectInterfFloatingcouponsrate(String sInfoWindcode){
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectInterfFloatingcouponsrate?sInfoWindcode="+sInfoWindcode,
        		InterfFloatcouprate.class);
    }
    
    /**
     * 中国债券基本资料
     * <功能详细描述>
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    public InterfCbonddescription selectInterfCbonddescription(String sInfoWindcode){
        return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectInterfCbonddescription?sInfoWindcode=" + sInfoWindcode,
            InterfCbonddescription.class);
    }
    
    /**
     * 日历信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private List<MstCalendarInfo> getMstCalendarInfo(){
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME
            + "/ValBondTargetAsset/getMstCalendarInfo",MstCalendarInfo.class);
    }
    
    /**
     * 根据外部代码1获取客户代码
     * <功能详细描述>
     * @param linkId1
     * @param type 类型
     * @return
     * @see
     * @since 1.0
     */
    private String getIssuer(String linkId1, String type){
        return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME
            + "/ValBondTargetAsset/getIssuer?linkId1="+linkId1 + "&type=" + type,String.class);
    }
    
    /**
     *  现金流信息
     * <功能详细描述>
     * @param finprodId
     * @param cashType1
     * @param cashType2
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    private FinCashInfo encapFinCashInfo(String finprodId,String cashType1,String cashType2,Long branch){
        FinCashInfo finCashInfo = new FinCashInfo();
        //现金流ID
        String cashId = commonBusinessService.generateCashFlowCode(finprodId, DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, cashType2);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        finCashInfo.setFinprodId(finprodId);
        finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        return  finCashInfo;
    }
    
    /**
     * 用起息日向后模式推算复习计划，做判断，如果日期
     * @param interfPayhsrcsecinfo
     * @param finCashPayRule
     * @return
     */
    public Date getFirstMdate(InterfPayhsrcsecinfoDto interfPayhsrcsecinfo, FinCashPayRule finCashPayRule){
        ValFinPayInterestStdInfoDto finPayInterestStdInfoDto = new ValFinPayInterestStdInfoDto();
        //资产公共DTO
        AssetCommonInfoDto scai = new AssetCommonInfoDto();
        BeanUtil.copyPropertiesIgnoreNull(scai, interfPayhsrcsecinfo); //复制资产信息
        //到期日+15天
        Date  mdate = DateUtil.parse(DateUtil.plusDays(DateUtil.format(scai.getMdate()), 15));
        //获取节假日
        List<String> holidayList = dateCalService.getDealCalendarHoliday(scai.getVdate(),mdate,scai.getCalendarId());        
        //如果首次付息日=第一条计划的支付日，推算方式为起息日向后
        finCashPayRule.setCalcRule(DictCodeList.推算方式_起息日向后.getValue());
        List<FinCashPlanDto>  invList = AssetInterestCal.cal(finCashPayRule, scai, holidayList, false,FinCashPlanDto.class); 
        //排序
        Collections.sort(invList, new Comparator<FinCashPlanDto>() {
            @Override
            public int compare(FinCashPlanDto o1, FinCashPlanDto o2) {
                return o1.getVdate().compareTo(o2.getVdate());
            }
        });
        if (StringUtil.isNotEmpty(invList) && invList.size() > 0){
            return invList.get(0).getMdate();
        }
        return null;
    }
    
	/**
	 * 查询资讯_债券评级中主体评级结果
	 * @param secid
     * @param date
	 * @return
	 */
	public String selectRating(String secid,String date){
		List<InterfPayhsrcsecrating> rateList = restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectRating?secid="+secid+"&date="+date,
				InterfPayhsrcsecrating.class);
		return rateList.size()!=0?rateList.get(0).getRating():null;
	}
	
	/**
	 * 查询客户信息附属表list-债券中债
	 * @param customerid
	 * @return
	 */
	public List<MstCustomerInfoAdd> selectMstCustomerInfoAdd(String customerid){
		return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectMstCustomerInfoAdd?customerid="+customerid,
				MstCustomerInfoAdd.class);
	}
	
	/**
	 * 查询资讯_债券评级信息表
	 * @param secid
	 * @return
	 */
	public List<InterfPayhsrcsecrating> selectInterfPayhsrcsecrating(String secid){	    
		return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/selectInterfPayhsrcsecrating?secid="+secid,
				InterfPayhsrcsecrating.class);
	}
    
    /**
     * 
     * 自动加券(债券) 封装dto
     * @param dto
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public InterfPayhsrcsecinfoDto autoBondCoupon(InterfPayhsrcsecinfoDto dto,String finprodMarketId){

        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(null!=dto.getSecidList()&&!dto.getSecidList().isEmpty()){
            for(String secid:dto.getSecidList()){
                BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
                //WIND_债券基本信息数据 
                InterfPayhsrcsecinfoDto interfPayhsrcsecinfo = selectWindBondAssetBySecId(secid);
                //WIND_债券行权信息数据
                List<InterfPayhsrcsecoption> payhsrcsecoptionList = selectWindBondExercise(secid);
                //WIND_债券付息计划数据
                List<InterfPayhsrcsecschedule> interfPayhsrcsecscheduleList = selectWindBondSchedule(secid);
                //WIND_中国债券特殊条款
                //InterfCbondspecialconditions interfCbondspecialconditions = selectInterfCbondspecialconditions(secid);//中国债券特殊条款注释
                //WIND_浮息债报价表
                List<InterfFloatcouprate> interfFloatingcouponsrateList = selectInterfFloatingcouponsrate(secid);
                //Wind 债券分类板块(注释  modify by shenchen 2019-04-19)
                //InterfCbondindustrywind interfCbondindustrywind = selectInterfCbondindustrywind(secid);
                //中国债券基本资料
                InterfCbonddescription interfCbonddescription = selectInterfCbonddescription(secid);
                if (!StringUtil.isNotEmpty(interfPayhsrcsecinfo)) {
                    return null;
                }
              //交易日历
                List<MstCalendarInfo> mstCalendarInfoList =  getMstCalendarInfo();
                //new标的类金融产品
                FinProduct finProduct = new FinProduct();
                XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
                String finprodId = commonBusinessService.getFinProdId(interfPayhsrcsecinfo.getSecid(), BussinessDictCodeList.金融产品类型_投管用_债券.getValue(),StringUtil.EMPTY);
                finProduct.setFinprodId(finprodId);
                finProduct.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                finProduct.setFinprodAbbr(interfPayhsrcsecinfo.getSecname());
                finProduct.setFinprodName(interfPayhsrcsecinfo.getSecfullname());
                finProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
                /**--息票品种modify 2019-04-15 start--**/
//              inputParaDto.setDictCode(BussinessDictCode.wind息票品种.getValue());
//              inputParaDto.setItemCode(interfPayhsrcsecinfo.getCouponspecies());
//              finProduct.setCouponSpecies(windCovCom(inputParaDto));
                finProduct.setCouponSpecies(interfPayhsrcsecinfo.getCouponspecies());
              /**--息票品种modify 2019-04-15 end--**/
                finProduct.setFinprodMarketId(interfPayhsrcsecinfo.getSecid());
                finProduct.setIssuePrice(interfPayhsrcsecinfo.getIssueprice());
                //发行金额除以万元
               
                if (StringUtil.isNotEmpty(interfPayhsrcsecinfo.getIssueamt())){
                finProduct.setIssueAmt(interfPayhsrcsecinfo.getIssueamt().divide(new BigDecimal(10000)));
                }else{
                    finProduct.setIssueAmt(null);
                }
                
                finProduct.setCcy(interfPayhsrcsecinfo.getCcy());
                finProduct.setBlnArea(interfPayhsrcsecinfo.getBlnarea());//境内外
                /**--交易场所modify 2019-04-15 start--**/
//              inputParaDto.setDictCode(DictCode.交易场所_WIND.getValue());
//              inputParaDto.setItemCode(interfPayhsrcsecinfo.getMarket());
//              finProduct.setTradeMarket(windCovCom(inputParaDto));
                finProduct.setTradeMarket(interfPayhsrcsecinfo.getMarket()); 
              /**--交易场所modify 2019-04-15 end--**/ 
              	finProduct.setIssuer(getIssuer(interfPayhsrcsecinfo.getIssuershort(), "IS_ISSUER"));
                //2019年5月27日  交易日历
              	if (StringUtil.equals(DictCodeListExt1.交易场所_银行间.getValue(),interfPayhsrcsecinfo.getMarket())) {
              	   finProduct.setCalendarId("03");//银行间
                }else if(StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(),interfPayhsrcsecinfo.getMarket())) {
                   finProduct.setCalendarId("04");//上交所
                }else if(StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(),interfPayhsrcsecinfo.getMarket())) {
                   finProduct.setCalendarId("05");//深交所
                }else if(StringUtil.equals(DictCodeListExt1.交易场所_机构间私募系统.getValue(),interfPayhsrcsecinfo.getMarket())) {
                    finProduct.setCalendarId("01");//中国法定节假日
                }
                for(MstCalendarInfo mstCalendarInfo : mstCalendarInfoList){
                    //第三方日历编号等于市场  -对应的日历编号
                    if(StringUtil.equals(mstCalendarInfo.getOtherId(),interfPayhsrcsecinfo.getMarket())){
                        finProduct.setCalendarId(mstCalendarInfo.getCalendarId());
                        break;
                    }
                }
                
                //资产支持证券:默认非保  其他债券类型默认保本
                /*if(BussinessDictCodeList.债券一级分类_资产支持证券.getValue().equals(interfPayhsrcsecinfo.getPbocsectype())){
                    finProduct.setCapiIncomeFeature(DictCodeListExt1.本金收益特征_非保本.getValue());//保本标志
                }else{
                    finProduct.setCapiIncomeFeature(DictCodeList.产品收益标识_保证收益.getValue());
                }*/
//                if(null!=interfPayhsrcsecinfo && null!=interfPayhsrcsecinfo.getIssuetype()){
//                    if(interfPayhsrcsecinfo.getIssuetype().toString().startsWith("43901")){
//                        finProduct.setIssueType(BussinessDictCodeList.发行方式_公开发行.getValue()); 
//                    }else{
//                        finProduct.setIssueType(BussinessDictCodeList.发行方式_非公开发行.getValue());
//                    }
//                }
                finProduct.setIssueType(interfPayhsrcsecinfo.getIssuetype());//发行方式
                /*--发行方式--*/
                finProduct.setVdate(interfPayhsrcsecinfo.getVdate());
                finProduct.setMdate(interfPayhsrcsecinfo.getMdate());
                /*---发行截止日 addby shenchen 2019-05-05----*/
                finProduct.setIdate(interfPayhsrcsecinfo.getPubenddate());
                /*---发行截止日 addby shenchen 2019-05-05----*/
                finProduct.setTermDays(DateUtil.getBetweenDayNumber(DateUtil.format(interfPayhsrcsecinfo.getVdate()),DateUtil.format(interfPayhsrcsecinfo.getMdate())));
                finProduct.setIsChl(DictCodeList.是否_否.getValue());
                /**--add by shenchen 2019-04-10--*/
               // finProduct.setIsSus(interfPayhsrcsecinfo.getIssus());//是否永续
                /**--add by shenchen 2019-04-10--*/
                /**--add by shenchen 2019-04-10--*/
//                if(StringUtil.equals(interfPayhsrcsecinfo.getIsredemption(), BussinessDictCodeList.是否_是.getValue())
//                    ||StringUtil.equals(interfPayhsrcsecinfo.getIsprecash(), BussinessDictCodeList.是否_是.getValue())){
                if(StringUtil.equals(interfPayhsrcsecinfo.getCalloption(), DictCodeList.是否_是.getValue())
                        ||StringUtil.equals(interfPayhsrcsecinfo.getPutoption(), DictCodeList.是否_是.getValue())){
                	/**--add by shenchen 2019-04-10--*/
                    finProduct.setIsRight(DictCodeList.是否_是.getValue());
                }else{
                    finProduct.setIsRight(DictCodeList.是否_否.getValue());
                }
                finProduct.setRegistOrg(interfPayhsrcsecinfo.getInstitution());
                // add by lizhi 20190603 是否永续债,Y是N否 start
                finProduct.setIsSus(interfPayhsrcsecinfo.getIssus());
                // add by lizhi 20190603 是否永续债,Y是N否 end
                baseFinProductBaseInfoDto.setFinProduct(finProduct);
                //new标的类金融产品附属
                FinProductAdd finProductAdd = new FinProductAdd();
                finProductAdd.setFinprodId(finprodId);
                finProductAdd.setWindId(secid);
                finProductAdd.setIsAutoUpdate(DictCodeList.是否_是.getValue());
                // add by lizhi 20190603 面值 start
                finProductAdd.setFaceValue(interfPayhsrcsecinfo.getFacevalue());
                // add by lizhi 20190603 面值 end
                //是否次级债
                finProductAdd.setIsSecBond(interfPayhsrcsecinfo.getIssecbond());
                baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
                //new金融产品分类
                FinProductType finProductType = new FinProductType();
                /**--add by shenchen 2019-04-10--*/
                //finProductType.setType4(interfCbondindustrywind.getsInfoIndustrycode());//债券分类板块注释
                //finProductType.setType5(interfCbondindustrywind.getsInfoIndustrycode2());
                finProductType.setType4(interfPayhsrcsecinfo.getSectype());//取债券资料表系统债券类型
                finProductType.setType5(interfPayhsrcsecinfo.getSectype2());//取债券资料表系统债券类型2
                finProductType.setType6(interfPayhsrcsecinfo.getSectype());//取债券资料表系统债券类型
                finProductType.setType7(interfPayhsrcsecinfo.getSectype2());//取债券资料表系统债券类型2
               
                FinProductTypeConfig finProductTypeConfig = new FinProductTypeConfig();
                finProductTypeConfig.setInputValue1(DictCodeListExt1.金融产品类型_债券.getValue());
                finProductTypeConfig.setInputValue2(interfPayhsrcsecinfo.getSectype());
                finProductTypeConfig.setInputValue3(interfPayhsrcsecinfo.getSectype2());
                //金融债的时候判断是否次级债
                if (StringUtil.equals(interfPayhsrcsecinfo.getSectype(),
                        DictCodeList.WIND一级分类_含债券一级分类等_金融债.getValue() )){
                    finProductTypeConfig.setInputValue4(interfPayhsrcsecinfo.getIssecbond());
                }
                //同业存单的时候五级是否他行
                if(StringUtil.equals(interfPayhsrcsecinfo.getSectype(),
                        DictCodeList.WIND一级分类_含债券一级分类等_同业存单.getValue() )){
                    finProductTypeConfig.setInputValue4(DictCodeList.是否_是.getValue());
                }
                finProductTypeConfig = restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/getAssetThreeType",
                        finProductTypeConfig, FinProductTypeConfig.class);
                finProductType.setType9(finProductTypeConfig.getInputValue2());//取债券资料表系统债券类型
                finProductType.setType10(finProductTypeConfig.getInputValue3());//取债券资料表系统债券类型2
                finProductType.setType11(finProductTypeConfig.getInputValue4());
                finProductType.setType12(finProductTypeConfig.getInputValue5());
                 
                
                finProductType.setType8(DictCodeListExt1.金融产品类型_债券.getValue());//type8
                /**--add by shenchen 2019-04-10--*/
                finProductType.setFinprodId(finprodId);
                finProductType.setBranch(branch);
                baseFinProductBaseInfoDto.setFinProductType(finProductType);
                //new现金流信息
                List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
                //现金流信息Entity-本金
                FinCashInfo finCashInfoPrincipal = encapFinCashInfo(finprodId, DictCodeListExt1.现金流一级类型_本金.getValue(),DictCodeListExt1.现金流二级类型_本金.getValue(),branch);
                //现金流信息Entity-利息
                FinCashInfo finCashInfoInterest = encapFinCashInfo(finprodId,DictCodeListExt1.现金流一级类型_利息.getValue(),DictCodeListExt1.现金流二级类型_利息.getValue(),branch);
                // add by lizhi 20190603 币种 start
                finCashInfoPrincipal.setCcy(interfPayhsrcsecinfo.getCcy());
                finCashInfoInterest.setCcy(interfPayhsrcsecinfo.getCcy());
                // add by lizhi 20190603 币种 end
                finCashInfoList.add(finCashInfoPrincipal);
                finCashInfoList.add(finCashInfoInterest);
                baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
                //new现金流计算规则
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                for(InterfFloatcouprate interfFloatingcouponsrate : interfFloatingcouponsrateList){
	                FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
	                finCashCalcRule.setCashId(finCashInfoInterest.getCashId());
	                finCashCalcRule.setEffDate(interfFloatingcouponsrate.getChangeDt());//变动日期
	                finCashCalcRule.setCalcType(interfPayhsrcsecinfo.getInterestrate());
	                finCashCalcRule.setBaseType(BussinessDictCodeList.基数类型_存续本金.getValue());
	                finCashCalcRule.setBaseDateType(BussinessDictCodeList.基数日期类型_当日.getValue());
	                /**--modify by shenchen 2019-04-19--**/
	//              inputParaDto.setDictCode(BussinessDictCode.wind计息基础.getValue());
	//              inputParaDto.setItemCode(interfPayhsrcsecinfo.getRatebasic());
	                finCashCalcRule.setBasis(interfPayhsrcsecinfo.getRatebasic());
	                    finCashCalcRule.setYield(ArithmeticUtil.divide(interfFloatingcouponsrate.getRate(), new BigDecimal(100)));
	                    if(DateUtil.compareDate(interfFloatingcouponsrate.getChangeDt(), interfPayhsrcsecinfo.getVdate())==0){
	                        finCashCalcRule.setIsInitial(DictCodeList.是否_是.getValue());
	                    }else{
	                        finCashCalcRule.setIsInitial(DictCodeList.是否_否.getValue());
	                    }
	                finCashCalcRule.setFinprodId(finprodId);
	                finCashCalcRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
	                finCashCalcRule.setBranch(branch);
	                finCashCalcRuleList.add(finCashCalcRule);
                }
                baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
                //new现金流利率信息
                List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
                FinRateInfo finRateInfo = new FinRateInfo();
                finRateInfo.setCashId(finCashInfoInterest.getCashId());
                finRateInfo.setEffDate(interfPayhsrcsecinfo.getVdate());
                // 一体化对应etliu start
//                finRateInfo.setRateType(interfPayhsrcsecinfo.getInterestrate());
                finRateInfo.setIntType(interfPayhsrcsecinfo.getInterestrate());
                // 一体化对应etliu end
                finRateInfo.setBasis(interfPayhsrcsecinfo.getRatebasic());
                finRateInfo.setResetType(DictCodeList.重置类型_基准利率调整规则_付息后下一计息期生效.getValue());
                finRateInfo.setRate(interfPayhsrcsecinfo.getPaperir());
                finRateInfo.setCoefficient(interfPayhsrcsecinfo.getCoefficient());
                finRateInfo.setSpreadRate(interfPayhsrcsecinfo.getSpreadrate8());
                // 一体化对应etliu start
//                finRateInfo.setRateMax(interfPayhsrcsecinfo.getToprate());
//                finRateInfo.setRateMin(interfPayhsrcsecinfo.getBottomrate());
//                finRateInfo.setIndexId(interfPayhsrcsecinfo.getRatecode());
//                finRateInfo.setIndexSource(DictCodeList.行情类型_利率.getValue());
                finRateInfo.setHighestRate(interfPayhsrcsecinfo.getToprate());    
                finRateInfo.setLowestRate(interfPayhsrcsecinfo.getBottomrate());
                finRateInfo.setBenchmarkId(interfPayhsrcsecinfo.getRatecode());
                finRateInfo.setBenchmarkType(DictCodeList.行情类型_利率.getValue());
                // 一体化对应etliu end
                finRateInfo.setFinprodId(finprodId);
                finRateInfo.setBranch(branch);
                finRateInfo.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                //金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
                finRateInfo.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
                //add by zengchengfa start 2019-07-02
                finRateInfoList.add(finRateInfo);
                baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);
                //new支付规则
                List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
                FinCashPayRule finCashPayRule = new FinCashPayRule();
                finCashPayRule.setCashId(finCashInfoInterest.getCashId());
                finCashPayRule.setEffDate(interfPayhsrcsecinfo.getVdate());
                finCashPayRule.setPayCycleVal(Long.valueOf(1));
                finCashPayRule.setPayCycle(interfPayhsrcsecinfo.getPaycycle());
                /*--自动加券 支付频率不再用判断 modify by shenchen 2019-04-10--*/
//                if(interfPayhsrcsecinfo.getInterestfrequency()==1){
//                    // 一体化对应etliu start
////                    finCashPayRule.setPayCycle(DictCodeList.支付频率_付息频率_年付.getValue());
//                    finCashPayRule.setPayCycle(DictCodeList.频率_年.getValue());
//                 // 一体化对应etliu end
//                }else if(interfPayhsrcsecinfo.getInterestfrequency()==2){
//                    // 一体化对应etliu start
////                    finCashPayRule.setPayCycle(DictCodeList.支付频率_付息频率_半年付.getValue());
//                    finCashPayRule.setPayCycle(DictCodeList.频率_半年.getValue());
//                 // 一体化对应etliu end
//                }else if(interfPayhsrcsecinfo.getInterestfrequency()==4){
//                    // 一体化对应etliu start
////                    finCashPayRule.setPayCycle(DictCodeList.支付频率_付息频率_季付.getValue());
//                    finCashPayRule.setPayCycle(DictCodeList.频率_季.getValue());
//                 // 一体化对应etliu end
//                }else if(interfPayhsrcsecinfo.getInterestfrequency()==12){
//                    // 一体化对应etliu start
////                    finCashPayRule.setPayCycle(DictCodeList.支付频率_付息频率_月付.getValue());
//                    finCashPayRule.setPayCycle(DictCodeList.频率_月.getValue());
//                 // 一体化对应etliu end
//                }else if(StringUtil.equals(interfPayhsrcsecinfo.getCouponspecies(), "DIS")
//                    ||StringUtil.equals(interfPayhsrcsecinfo.getCouponspecies(), "IAM")
//                    ||StringUtil.equals(interfPayhsrcsecinfo.getCouponspecies(), "ZCO")){
//                    // 一体化对应etliu start
////                        finCashPayRule.setPayCycle(DictCodeList.支付频率_付息频率_到期支付.getValue());
//                    finCashPayRule.setPayCycle(DictCodeList.频率_到期一次.getValue());
//                     // 一体化对应etliu end
//                }
                finCashPayRule.setPayCycle(interfPayhsrcsecinfo.getPaycycle());//支付频率
                /*--自动加券 支付频率不再用判断 modify by shenchen 2019-04-10--*/
                finCashPayRule.setEndTdayRule(DictCodeList.营业日准则_不调整.getValue());
//              inputParaDto.setDictCode(BussinessDictCode.wind营业日准则.getValue());
//              inputParaDto.setItemCode(interfPayhsrcsecinfo.getWorkdayrule());
//              finCashPayRule.setPayTdayRule(windCovCom(inputParaDto));
                finCashPayRule.setPayTdayRule(interfPayhsrcsecinfo.getWorkdayrule());
                /**--息票品种modify 2019-04-15 end--**/
                /**--推算方式modify 2019-04-15 start--**/
//              inputParaDto.setDictCode(BussinessDictCode.wind推算方式.getValue());
//              inputParaDto.setItemCode(interfPayhsrcsecinfo.getSchecalrule());
//              finCashPayRule.setCalcRule(windCovCom(inputParaDto));
                finCashPayRule.setCalcRule(interfPayhsrcsecinfo.getSchecalrule());                         
               
                /**--推算方式modify 2019-04-15 end--**/
                finCashPayRule.setFirstPayDate(interfPayhsrcsecinfo.getFirstrateday());
                finCashPayRule.setCashUnitType(DictCodeList.现金流单位_百元法.getValue());
                finCashPayRule.setFinprodId(finprodId);
                finCashPayRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                finCashPayRule.setBranch(branch);
                finCashPayRule.setPayType(DictCodeList.分红方式_现金分红.getValue());

                //为了特殊处理首次支付日是节假日的计算利息 start 20200108
                if (StringUtil.equals(interfPayhsrcsecinfo.getSchecalrule(), DictCodeList.推算方式_首次支付日.getValue())
                     && StringUtil.isNotEmpty(interfPayhsrcsecscheduleList) && interfPayhsrcsecscheduleList.size() > 0){
                    //计算付息计划 如果首次付息日=第一条计划的支付日，推算方式为起息日向后
                    Date mdate  = getFirstMdate(interfPayhsrcsecinfo, finCashPayRule);
                    if (StringUtil.equals("01", interfPayhsrcsecscheduleList.get(0).getSchedutype()) &&
                            DateUtil.compareDate(interfPayhsrcsecinfo.getFirstrateday(), mdate) == 0){
                        finCashPayRule.setCalcRule(DictCodeList.推算方式_起息日向后.getValue());
                    }else {
                        finCashPayRule.setCalcRule(interfPayhsrcsecinfo.getSchecalrule());
                    }
                }
                //为了特殊处理首次支付日是节假日的计算利息 end 20200108
                finCashPayRuleList.add(finCashPayRule);
                baseFinProductBaseInfoDto.setFinCashPayRuleList(finCashPayRuleList);
                //现金流支付计划
                List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
                for(InterfPayhsrcsecschedule interfPayhsrcsecschedule:interfPayhsrcsecscheduleList){                    
                    
                	/**--modify 2019-04-17 start--**/
                	//如果为"03"或者"02"-还本，则设置本金的现金流代码,否则为"04","01"-收息，设置收息的现金流代码
                    //支付本金
                	if("03".equals(interfPayhsrcsecschedule.getSchedutype()) || "02".equals(interfPayhsrcsecschedule.getSchedutype())){
                		FinCashPlan finCashPlan = new FinCashPlan();
                		finCashPlan.setCashId(finCashInfoPrincipal.getCashId());
                        //finCashPlan.setCashId(finCashInfoPrincipal.getCashId());注释，cashId根据类型判断
                    	//如果资讯_债券付息计划数据Entity.实际付息结束日 = 资讯_债券基本信息数据Entity.到期日，则为到期还本														
                    	if(DateUtil.compareDate(interfPayhsrcsecschedule.getInterestendday(),interfPayhsrcsecinfo.getMdate())!=0){
                            finCashPlan.setCashType(BussinessDictCodeList.现金流类型_中间还本.getValue());
                        }else{
                            finCashPlan.setCashType(BussinessDictCodeList.现金流类型_到期还本.getValue());
                        }
                        finCashPlan.setVdateUnadjust(interfPayhsrcsecschedule.getIntereststartday());
                        finCashPlan.setVdate(interfPayhsrcsecschedule.getIntereststartday());
                        finCashPlan.setTermdays(Long.valueOf(0));
                        finCashPlan.setVdateTerm(interfPayhsrcsecschedule.getTotalintereststartday());
                        finCashPlan.setMdateTerm(interfPayhsrcsecschedule.getTotalinterestendday());
                        finCashPlan.setTermdaysTerm(Long.valueOf(0));
                        finCashPlan.setPrinAmt(interfPayhsrcsecschedule.getPrinamtpay());
                        /**mdify by shenchen 2019-04-23 如果为本金，则计息本金取支付本金**/
                        finCashPlan.setIntPrinAmt(interfPayhsrcsecschedule.getPrinamtpay());
                        /**mdify by shenchen 2019-04-23**/
                        finCashPlan.setIntAmt(BigDecimal.ZERO);
                        finCashPlan.setIsPayInt(BussinessDictCodeList.是否_是.getValue());
                        finCashPlan.setCashAmt(interfPayhsrcsecschedule.getPrinamtpay());
                        finCashPlan.setCashBaseamt(new BigDecimal(100));
                        /**--modify 2019-04-17 end--**/
                        finCashPlan.setMdateUnadjust(interfPayhsrcsecschedule.getInterestendday());
                        finCashPlan.setPayDateUnadjust(interfPayhsrcsecschedule.getInterestpayday());
                        finCashPlan.setMdate(interfPayhsrcsecschedule.getInterestendday());
                        finCashPlan.setPayDate(interfPayhsrcsecschedule.getInterestpayday());
                        /**mdify by shenchen 2019-04-23**/
                        //finCashPlan.setIntPrinAmt(interfPayhsrcsecschedule.getPrinamtpay());
                        /**mdify by shenchen 2019-04-23**/
                        //finCashPlan.setFrequency(Long.valueOf(interfPayhsrcsecinfo.getInterestfrequency()));接口表无年付息次数字段
                        finCashPlan.setCashUnitType(BussinessDictCodeList.现金流单位_百元法.getValue());
                        finCashPlan.setFinprodId(finprodId);
                        finCashPlan.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
                        finCashPlan.setBranch(branch);
                        finCashPlan.setPayType(BussinessDictCodeList.分红方式_现金分红.getValue());   
                        finCashPlanList.add(finCashPlan);
                    }else if("04".equals(interfPayhsrcsecschedule.getSchedutype()) || "01".equals(interfPayhsrcsecschedule.getSchedutype())){                    	
//                    	if(null!=interfPayhsrcsecschedule.getPrinamtpay()&&interfPayhsrcsecschedule.getPrinamtpay().compareTo(BigDecimal.ZERO)>0){     
                    		FinCashPlan finCashPlan = new FinCashPlan();
	                    	//如果资讯_债券付息计划数据Entity.实际付息结束日 = 资讯_债券基本信息数据Entity.到期日，则为到期还本		
                    		finCashPlan.setCashId(finCashInfoInterest.getCashId());
	                        if(DateUtil.compareDate(interfPayhsrcsecschedule.getInterestendday(),interfPayhsrcsecinfo.getMdate())!=0){
	                            finCashPlan.setCashType(BussinessDictCodeList.现金流类型_中间收息.getValue());
	                        }else{
	                            finCashPlan.setCashType(BussinessDictCodeList.现金流类型_到期收息.getValue());
	                        }
	                        finCashPlan.setVdateUnadjust(interfPayhsrcsecschedule.getIntereststartday());
	                        finCashPlan.setVdate(interfPayhsrcsecschedule.getIntereststartday());
	                        finCashPlan.setTermdaysTerm(DateUtil.getBetweenDayNumber(DateUtil.format(interfPayhsrcsecschedule.getIntereststartday()),DateUtil.format(interfPayhsrcsecschedule.getInterestendday())));
	                        finCashPlan.setVdateTerm(interfPayhsrcsecschedule.getTotalintereststartday());
	                        finCashPlan.setMdateTerm(interfPayhsrcsecschedule.getTotalinterestendday());
	                        finCashPlan.setTermdaysTerm(DateUtil.getBetweenDayNumber(DateUtil.format(interfPayhsrcsecschedule.getTotalintereststartday()),DateUtil.format(interfPayhsrcsecschedule.getTotalinterestendday())));
	                        /**mdify by shenchen 2019-04-23 如果为本金，则计息本金取计息本金**/
	                        finCashPlan.setIntPrinAmt(interfPayhsrcsecschedule.getPrinamt());
	                        /**mdify by shenchen 2019-04-23**/
	                        finCashPlan.setIntAmt(interfPayhsrcsecschedule.getAccrueintamt());
	                        finCashPlan.setCashAmt(interfPayhsrcsecschedule.getAccrueintamt());
	                        finCashPlan.setCashBaseamt(new BigDecimal(100));
	                        /**--modify 2019-04-17 end--**/
	                        finCashPlan.setMdateUnadjust(interfPayhsrcsecschedule.getInterestendday());
	                        finCashPlan.setPayDateUnadjust(interfPayhsrcsecschedule.getInterestpayday());
	                        finCashPlan.setMdate(interfPayhsrcsecschedule.getInterestendday());
	                        finCashPlan.setPayDate(interfPayhsrcsecschedule.getInterestpayday());
	                        /**mdify by shenchen 2019-04-23**/
	                        //finCashPlan.setIntPrinAmt(interfPayhsrcsecschedule.getPrinamtpay());
	                        /**mdify by shenchen 2019-04-23**/
	                        //finCashPlan.setFrequency(Long.valueOf(interfPayhsrcsecinfo.getInterestfrequency()));接口表无年付息次数字段
	                        finCashPlan.setCashUnitType(BussinessDictCodeList.现金流单位_百元法.getValue());
	                        finCashPlan.setFinprodId(finprodId);
	                        finCashPlan.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
	                        finCashPlan.setBranch(branch);
	                        finCashPlan.setPayType(BussinessDictCodeList.分红方式_现金分红.getValue());	  
	                        finCashPlan.setRangeYield(interfPayhsrcsecschedule.getCouponrate());
	                        finCashPlanList.add(finCashPlan);
//                    	}
                    }

                }
                baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
                //new行权信息
                List<FinExercisePlan> finExercisePlanList = new ArrayList<FinExercisePlan>();
                for(InterfPayhsrcsecoption payhsrcsecoption:payhsrcsecoptionList){
                    FinExercisePlan finExercisePlan = new FinExercisePlan();
                    finExercisePlan.setCashId(finCashInfoPrincipal.getCashId());
                    /**--modify 2019-04-17 start--**/
//                  inputParaDto.setDictCode(BussinessDictCode.wind行权方式.getValue());
//                  inputParaDto.setItemCode(payhsrcsecoption.getOptiontype());
//                  finExercisePlan.setExerciseType(windCovCom(inputParaDto));
                  finExercisePlan.setExerciseType(payhsrcsecoption.getOptiontype());
                    finExercisePlan.setExerciseStatus(DictCodeList.行权状态_未行权.getValue());
                  /**--modify 2019-04-17 end--**/
                    finExercisePlan.setVdate(payhsrcsecoption.getOptionstartday());
                    finExercisePlan.setMdate(payhsrcsecoption.getOptionendday());
                    finExercisePlan.setActExerciseDate(payhsrcsecoption.getOptionpayday());//实际行权日                    
                    finExercisePlan.setExerciseDate(payhsrcsecoption.getTheoryoptionpayday());//行权信息表理论行权日
                    finExercisePlan.setExercisePrice(payhsrcsecoption.getOptionprice());
                    /**--现行权条款取行权信息表行权条款 add by shenchen 2019-04-11--**/
                    //finExercisePlan.setExerciseRemark(interfCbondspecialconditions.getbInfoContent());//中国债券特殊条款注释
                    finExercisePlan.setExerciseRemark(payhsrcsecoption.getOptionclause());//行权信息表行权条款
                    /**--现行权条款取行权信息表行权条款 add by shenchen 2019-04-11--**/
                    finExercisePlan.setFinprodId(finprodId);
                    finExercisePlan.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                    finExercisePlan.setBranch(branch);
                    finExercisePlanList.add(finExercisePlan);
                }
                baseFinProductBaseInfoDto.setFinExercisePlanList(finExercisePlanList);
                
                //查询资讯_债券信息评级list
                List<InterfPayhsrcsecrating> interfPayhsrcsecratingList = selectInterfPayhsrcsecrating(secid);
                //new债券信用评级
                List<MstBondCreditRating> mstBondCreditRatingList = new ArrayList<MstBondCreditRating>();
                if(null!=interfPayhsrcsecratingList && interfPayhsrcsecratingList.size()!=0){
                	for(InterfPayhsrcsecrating interfPayhsrcsecrating : interfPayhsrcsecratingList){
                		MstBondCreditRating mstBondCreditRating = new MstBondCreditRating();
                    	mstBondCreditRating.setoCode(interfPayhsrcsecrating.getSecid());//债券ID
                    	mstBondCreditRating.setGradeOrgId(interfPayhsrcsecrating.getRatingorg());//评级机构
                    	mstBondCreditRating.setGradeDate(interfPayhsrcsecrating.getPubdate());//评级日期取发布日期
                    	//mstBondCreditRating.setIsrecommand(interfPayhsrcsecrating.getIsrecommand());//是否推荐
                    	mstBondCreditRating.setoType(interfPayhsrcsecrating.getRatingtype());//评级类型
                    	mstBondCreditRating.setShortLongTerm(interfPayhsrcsecrating.getRatingsubtype());//长期短期
                    	mstBondCreditRating.setGradeResult(interfPayhsrcsecrating.getRating());//评级结果
                    	//mstBondCreditRating.setSecIssueId(getIssuer(interfPayhsrcsecinfo.getIssuershort(), "IS_ISSUER"));//标的类金融产品发行人
                    	mstBondCreditRating.setInputType(BussinessDictCodeList.录入方式_接口导入.getValue());//录入方式
                    	mstBondCreditRatingList.add(mstBondCreditRating);
                	}
                	baseFinProductBaseInfoDto.setMstBondCreditRatingList(mstBondCreditRatingList);//组装到baseInfoDto
                }
                //new中债报送信息
                List<MstCustomerInfoAdd> mstCustomerInfoAddList = selectMstCustomerInfoAdd(finProduct.getIssuer());//根据发行人查MST_CUSTOMER_INFO_ADD
                RepAssetBond repAssetBond = new RepAssetBond();//中债报送信息
                RepAssetDept repAssetDept = new RepAssetDept();
                //评级结果查询(主体评级,离系统日最近的日期)
                String rating = selectRating(secid,DateUtil.getSysDate()); 
                repAssetBond.setRepFinprodId(finprodId);//金融产品代码
        		repAssetBond.setSecId(interfPayhsrcsecinfo.getSecid());//债券代码
        		repAssetBond.setRepIssueMode(interfPayhsrcsecinfo.getIssuetype());//发行方式
        		repAssetBond.setRepMainGrade(rating);//主体评级
        		repAssetBond.setRepRegistOrg(interfPayhsrcsecinfo.getInstitution());//登记托管机构
        		repAssetBond.setRepRegistorgComment(interfPayhsrcsecinfo.getInstitutiontext());//登记托管机构说明
        		
        		repAssetDept.setRepFinprodId(finprodId);//金融产品代码
        		repAssetDept.setFinprodMarketId(secid);//债券代码
        		repAssetDept.setRepFinprodName(interfPayhsrcsecinfo.getSecname());//债券名称
        		repAssetDept.setRepIssuePartyId("C10816");//发行机构代码(参数值)
        		//repAssetDept.setRepAssetType(exchangeType(interfPayhsrcsecinfo.getChbsectype2()));//资产负债类别    
                repAssetDept.setRepAssetType(interfPayhsrcsecinfo.getAssetClass());      
        		repAssetDept.setRepCcy(interfPayhsrcsecinfo.getCcy());//币种
        		//repAssetBond.setRepSectype(repSectype);具体类别
        		repAssetBond.setRepSectype(interfPayhsrcsecinfo.getConcreteClass());//具体类别 add by lpl 2019-12-20
        		try {
        			repAssetDept.setRepStatusDate(sdf.parse(DateUtil.getSysDate()));//数据日期
        		} catch (ParseException e) {
					e.printStackTrace();
				}
        		repAssetDept.setRepTradeMarket(interfPayhsrcsecinfo.getMarket2());//交易流通场所
        		repAssetDept.setIsRepTarget(BussinessDictCodeList.是否_否.getValue());//默认否
        		
                repAssetBond.setRepPartyType1(interfPayhsrcsecinfo.getIssuerScaleType());//机构类型按规模分
                repAssetBond.setRepPartyType2(interfPayhsrcsecinfo.getIssuerEconomicType());//机构类型按经济类型分
                repAssetBond.setRepPartyType3(interfPayhsrcsecinfo.getIssuerTechnologyType());//机构类型按技术领域分
                repAssetBond.setRepIndustry(interfPayhsrcsecinfo.getIssuerInstitutions());//发行机构所属行业
               // repAssetBond.setRepIndustryLevel2(interfPayhsrcsecinfo.getIndustry2());//发行机构所属行业二级分类
                /*if(null!=mstCustomerInfoAddList && mstCustomerInfoAddList.size()!=0){
                	for(MstCustomerInfoAdd mstCustomerInfoAdd : mstCustomerInfoAddList){
	                		repAssetBond.setRepPartyType1(mstCustomerInfoAdd.getPartyType1());//机构类型按规模分
	                		repAssetBond.setRepPartyType2(mstCustomerInfoAdd.getPartyType2());//机构类型按经济类型分
	                		repAssetBond.setRepPartyType3(mstCustomerInfoAdd.getPartyType3());//机构类型按技术领域分
	                		repAssetBond.setRepIndustry(mstCustomerInfoAdd.getIndustry());//发行机构所属行业
	                		repAssetBond.setRepIndustryLevel2(mstCustomerInfoAdd.getIndustry2());//发行机构所属行业二级分类
                	}
                }*/
                baseFinProductBaseInfoDto.setRepAssetBond(repAssetBond);//存中债报送界面机构类型等字段
            	baseFinProductBaseInfoDto.setRepAssetDept(repAssetDept);//存中债报送界面资产负债类别
                baseFinProductBaseInfoDto.setRepAssetType(interfPayhsrcsecinfo.getAssetClass());
                baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
            }
        }
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
        return dto;      
    }

    /**
     * 查询接口表资产信息
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public ValBondTargetAssetDto getInterfBondDto(String finprodMarketId) {
        InterfPayhsrcsecinfoDto dto = new InterfPayhsrcsecinfoDto();
        List<String> list = new ArrayList<>();
        list.add(finprodMarketId);
        dto.setSecidList(list);
//        dto = commonAutoService.autoBondCoupon(dto, finprodMarketId);
        dto = autoBondCoupon(dto, finprodMarketId);
        BaseFinProductBaseInfoDto baseInfoDto = new BaseFinProductBaseInfoDto();
        if (StringUtil.isNotEmpty(dto)) {
            baseInfoDto =  dto.getBaseFinProductBaseInfoList().get(0);
        }else {
            return null;
        }
        //债券基本信息
        ValBondTargetAssetDto bondTargetDto = new ValBondTargetAssetDto();
        //金融产品基本信息表
        FinProduct finProduct =  baseInfoDto.getFinProduct();
        if (StringUtil.isNotEmpty(finProduct)) {
            BeanUtil.copyPropertiesIgnoreNull(bondTargetDto,finProduct);
        }
        bondTargetDto.setRepAssetType(baseInfoDto.getRepAssetType());
        //类型
        if (StringUtil.isNotEmpty(baseInfoDto.getFinProductType())) {
            bondTargetDto.setType4(baseInfoDto.getFinProductType().getType4());
            bondTargetDto.setType5(baseInfoDto.getFinProductType().getType5());
			bondTargetDto.setType9(baseInfoDto.getFinProductType().getType9());
			bondTargetDto.setType10(baseInfoDto.getFinProductType().getType10());
			bondTargetDto.setType11(baseInfoDto.getFinProductType().getType11());
			bondTargetDto.setType12(baseInfoDto.getFinProductType().getType12());	 
			
		}
        //附属表
        if (StringUtil.isNotEmpty(baseInfoDto.getFinProductAdd())) {
            bondTargetDto.setIsAutoUpdate(baseInfoDto.getFinProductAdd().getIsAutoUpdate());
        }
        //
        BaseFinProductBaseInfoDto baseInfoDto2 = new BaseFinProductBaseInfoDto();
        BeanUtil.copyPropertiesIgnoreNull(baseInfoDto2, baseInfoDto);
        List<BaseFinProductBaseInfoDto> baseList = new ArrayList<>();
        baseList.add(baseInfoDto2);
        bondTargetDto.setBaseFinProductBaseInfoList(baseList);
        //现金流利率信息表
        List<FinRateInfo> finRateInfos = baseInfoDto2.getFinRateInfoList();
        //计息要素业务
        if (StringUtil.isNotEmpty(finRateInfos)) {
            ValFinInterestFactorDto finInterestFactorDto = new ValFinInterestFactorDto();
            finInterestFactorDto.setCouponSpecies(finProduct.getCouponSpecies());
            BeanUtil.copyPropertiesIgnoreNull(finInterestFactorDto, finRateInfos.get(0));
            //计息基础数据转换 
            String basis =  finInterestFactorDto.getBasis();
            if (BussinessDictCodeList.计息基础_A_A_B_DAY.getValue().equals(basis)){
                finInterestFactorDto.setBasis(BussinessDictCodeList.计息基础_A_A.getValue());
                finInterestFactorDto.setBasisStyle(BussinessDictCodeList.利息分配方式_按照实际天数分配.getValue());
            }else if (BussinessDictCodeList.计息基础_A_A_B_COUP.getValue().equals(basis)){
                finInterestFactorDto.setBasis(BussinessDictCodeList.计息基础_A_A.getValue());
                finInterestFactorDto.setBasisStyle(BussinessDictCodeList.利息分配方式_平均分配.getValue());
            }else {
                finInterestFactorDto.setBasis(basis);
            }
            //update by leipeng 现状：选择债券代码，点击放大镜进行加券，接口文件中初始利率为0.035，加券时，初始利率为0.035%    期望：加券时，因前台初始利率已经是%号了，再加券时应乘以100
            if(null!=finInterestFactorDto.getRate()){
                finInterestFactorDto.setRate(finInterestFactorDto.getRate().multiply(new BigDecimal("100")));
            }
            
            bondTargetDto.setFinInterestFactorDto(finInterestFactorDto);
        }
        //现金流计算规则
        List<FinCashCalcRule>  finCashCalcRuleList = baseInfoDto.getFinCashCalcRuleList();

        //现金流支付规则
        List<FinCashPayRule> finCashPayRuleList = baseInfoDto2.getFinCashPayRuleList();

        if (StringUtil.isNotEmpty(finCashCalcRuleList)) {
          //付息信息业务
            ValFinPayInterestStdInfoDto finPayInterestStdInfoDto = new ValFinPayInterestStdInfoDto();
          //还本信息业务
            ValBussFinRepaymentDto bussFinRepaymentDto = new ValBussFinRepaymentDto();
            finPayInterestStdInfoDto.setCouponSpecies(finProduct.getCouponSpecies());
            if (StringUtil.isNotEmpty(finCashPayRuleList)){
                finPayInterestStdInfoDto.setPayCycle(finCashPayRuleList.get(0).getPayCycle());
                finPayInterestStdInfoDto.setCalcRule(finCashPayRuleList.get(0).getCalcRule());
                finPayInterestStdInfoDto.setFirstPayDate(finCashPayRuleList.get(0).getFirstPayDate());
            }
          //现金流计划表
            List<FinCashPlan> finCashPlanList = baseInfoDto2.getFinCashPlanList();
            if (StringUtil.isNotEmpty(finCashPlanList)) {
                //还本
                List<FinCashPlanDto> repPayDtos = new ArrayList<>();
                //付息
                List<FinCashPlanDto> payInvestDtos = new ArrayList<>();
                for (FinCashPlan finCashPlan : finCashPlanList) {
                    if (BussinessDictCodeList.现金流类型_中间还本.getValue().equals(finCashPlan.getCashType())
                    || BussinessDictCodeList.现金流类型_到期还本.getValue().equals(finCashPlan.getCashType())){
                        FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                        BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto,finCashPlan);
                        repPayDtos.add(finCashPlanDto);
                        
                    }else{
                    	if (!DictCodeList.息票品种_贴现.getValue().equals(bondTargetDto.getCouponSpecies())
                    			&& !DictCodeList.息票品种_零息.getValue().equals(bondTargetDto.getCouponSpecies())) {
                            FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                            BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto,finCashPlan);
                            finCashPlanDto.setRangeYield(ArithmeticUtil.multiply(finCashPlanDto.getRangeYield(), new BigDecimal(100)));
                            payInvestDtos.add(finCashPlanDto);
                    	}
                    }
                }
                bussFinRepaymentDto.setFinCashPlanList(repPayDtos);
                finPayInterestStdInfoDto.setFinCashPlanList(payInvestDtos);
            }

            bondTargetDto.setFinPayInterestStdInfoDto(finPayInterestStdInfoDto);
            bondTargetDto.setBussFinRepaymentDto(bussFinRepaymentDto);
        }

        // 行权计划
        ValBusiFinExercisePlanDto busiFinExercisePlanDto = new ValBusiFinExercisePlanDto();
        busiFinExercisePlanDto.setFinExercisePlanList(baseInfoDto2.getFinExercisePlanList());
        bondTargetDto.setBusiFinExercisePlanDto(busiFinExercisePlanDto);
      //查询债项
        Map<String, Object> zxmap = new HashMap<>();
        zxmap.put("oCode", finprodMarketId);
        //查询主体
        Map<String, Object> ztmap = new HashMap<>();
        ztmap.put("oCode", bondTargetDto.getIssuer());
        // 中债报送
        RepAssetBond repAssetBond = baseInfoDto.getRepAssetBond();
         bondTargetDto.setRepAssetBond(baseInfoDto.getRepAssetBond());
         bondTargetDto.setRepAssetDept(baseInfoDto.getRepAssetDept());
        return bondTargetDto;
    }

    // 债券新建新增快捷录入功能 add by renping 20191010 start
    /**
     * 查询业务表资产信息
     *
     * @param finprodMarketId
     * @return 资产信息
     * @see
     * @since 1.0
     */
    public ValBondTargetAssetDto getBondDto(String finprodMarketId) {
        //债券基本信息
        ValBondTargetAssetDto bondTargetDto = getBasicInfo(finprodMarketId);
        if (bondTargetDto == null) {
            return bondTargetDto;
        }
        bondTargetDto = restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/searchData",
            bondTargetDto, ValBondTargetAssetDto.class);

        if (null != fileAttachmentProcess && StringUtil.isNotEmpty(bondTargetDto.getFinprodId())) {
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(
                bondTargetDto.getFinprodId());
            bondTargetDto.setFileArray(files);
        }
        //查询债项
        Map<String, Object> zxmap = new HashMap<>();
        zxmap.put("oCode", finprodMarketId);
        //查询主体
        Map<String, Object> ztmap = new HashMap<>();
        ztmap.put("oCode", bondTargetDto.getIssuer());
        // 现金流计算规则
        // 根据主键获取业务实体--付息信息
        ValFinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
            bondTargetDto.getFinprodId(), bondTargetDto.getvdateStr(),
            DictCodeListExt1.现金流二级类型_利息.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue());
        bondTargetDto.setFinPayInterestStdInfoDto(finPayInterestStdInfoDto);
        // 现金流支付规则
        ValBussFinRepaymentDto bussFinRepaymentDto = new ValBussFinRepaymentDto();
        finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
            bondTargetDto.getFinprodId(), bondTargetDto.getvdateStr(),
            DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue());
        if (null != finPayInterestStdInfoDto) {
            bussFinRepaymentDto.setPayCycle(finPayInterestStdInfoDto.getPayCycle());
            bussFinRepaymentDto.setRemark(finPayInterestStdInfoDto.getRemark());
            bondTargetDto.setBussFinRepaymentDto(bussFinRepaymentDto);
        }
        // 行权计划
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", bondTargetDto.getFinprodId());
        param.put("psT03", DictCodeList.交易方向_行权_回售.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
        param.put("execType01", DictCodeList.行权状态_未行权.getValue());
        param.put("execType02", DictCodeList.行权状态_已行权.getValue());
        List<FinExercisePlan> finExercisePlanList = busiFinExercisePlanService.getfinExercisePlanByProductId(
            param);
        ValBusiFinExercisePlanDto busiFinExercisePlanDto = new ValBusiFinExercisePlanDto();
        busiFinExercisePlanDto.setFinExercisePlanList(finExercisePlanList);
        bondTargetDto.setBusiFinExercisePlanDto(busiFinExercisePlanDto);
        if (bondTargetDto != null && bondTargetDto.getFinInterestFactorDto() != null) {
            ValFinInterestFactorDto finInterestFactorDto = bondTargetDto.getFinInterestFactorDto();
            if (StringUtil.equals(finInterestFactorDto.getBasis(),
                DictCodeList.计息基础_A_A_平均分配.getValue())) {
                finInterestFactorDto.setBasisStyle(DictCodeListExt1.利息分配方式_平均分配.getValue());
                finInterestFactorDto.setBasis(DictCodeList.计息基础_A_A.getValue());
            }
            else if (StringUtil.equals(finInterestFactorDto.getBasis(),
                DictCodeList.计息基础_A_A_按实际天数分配.getValue())) {
                finInterestFactorDto.setBasisStyle(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
                finInterestFactorDto.setBasis(DictCodeList.计息基础_A_A.getValue());
            }
        }
        return bondTargetDto;
    }
    // 债券新建新增快捷录入功能 add by renping 20191010 end

    /**
     * 获取新建界面的以及市场投标债券代码联想下拉框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                                       + "/ValBondTargetAsset/getfinprodIdForAdd"
                                       , param,
            SelectOptionDto.class);

	}

	public int isHqTrade(String finprodId) {
		return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/isHqTrade?finprodId=" + finprodId,
	            Integer.class);
	}
	
    /**
     * 债券下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBondId(Map<String, Object> params) { 
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValBondTargetAsset/getBondId",
                params, SelectOptionDto.class);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getIssuerId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
            + "/ValBondTargetAsset/getIssuerId", map,
            SelectOptionDto.class);
    }
    /**
     * 交易日历下拉框查询
     * 
     * @param map 参数
     * @return 交易日历
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryCalendarInfoList(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondTargetAsset/queryCalendarInfoList",
            map, SelectOptionDto.class);
    }
}
