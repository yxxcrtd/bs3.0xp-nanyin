/*
 * 文件名：FinAssetRateAdj001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年10月11日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.common.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.common.dto.FinAssetRateAdjDto;
import com.joyintech.tams.invest.asset.common.dto.FinPayPlanDto;
import com.joyintech.tams.invest.common.asset.dto.RateAdjDto;
import com.joyintech.tams.invest.common.asset.service.RateAdjService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private IPlatformDate iPlatformDate;

    /*@Autowired
    private AssetCommonService assetCommonService;*/

    @Autowired
    private RateAdjService rateAdjService;

    /**
     * 付息信息
     */
    /*@Autowired(required = false)
    private FinPayInterestStdInfoService finPayInterestStdInfoService;*/

    /**
     * 初始化信息--前台分页 <功能详细描述>
     *
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */

    public List<FinAssetRateAdjDto> getfinAssetRateAdjByProductId(Map<String, Object> paramMap) {
        List<FinAssetRateAdjDto> finAssetRateAdjList = restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinAssetRateAdj/getfinAssetRateAdjByProductId", paramMap,
            FinAssetRateAdjDto.class);
        for (FinAssetRateAdjDto entity : finAssetRateAdjList) {
            String updateFlg = StringUtil.EMPTY;
            if (StringUtil.equals(entity.getIsInitial(), DictCodeList.是否_是.getValue())) {
                updateFlg = "02";
            }
            else if (DateUtil.compareDate(entity.getEffDate(), iPlatformDate.getSysDate()) == -1) {
                updateFlg = "02";
            }
            else {
                updateFlg = "01";
            }
            entity.setUpdateFlg(updateFlg);
        }
        return finAssetRateAdjList;
    }

    public Page<FinAssetRateAdjDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        pageParam.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        pageParam.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        Page<FinAssetRateAdjDto> pageList = restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinAssetRateAdj/findPage", pageParam, FinAssetRateAdjDto.class);
        for (FinAssetRateAdjDto entity : pageList.getRoot()) {
            String updateFlg = StringUtil.EMPTY;
            if (StringUtil.equals(entity.getIsInitial(), DictCodeList.是否_是.getValue())) {
                updateFlg = "02";
            }
            else if (DateUtil.compareDate(entity.getEffDate(), iPlatformDate.getSysDate()) == -1) {
                updateFlg = "02";
            }
            else {
                updateFlg = "01";
            }
            entity.setUpdateFlg(updateFlg);
        }
        return pageList;
    }

    public FinAssetRateAdjDto encapFinAssetRateAdjDto(FinAssetRateAdjDto dto) {
        // 重新查询计算规则、计算规则扩展初始化信息
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", dto.getFinprodId());
        param.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
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
        /* BigDecimal buyOutRate = finAssetRateAdjList.get(0).getBuyOutRate(); */
        BigDecimal investRate = finAssetRateAdjList.get(0).getInvestRate();
        /* BigDecimal referRate = finAssetRateAdjList.get(0).getReferRate(); */
        List<PaFinCashCalcRule> paFinCashCalcRuleList = new ArrayList<PaFinCashCalcRule>();
        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        dto.getSelectedItem().forEach(entity -> {
            // 生效利率
            BigDecimal yield = ArithmeticUtil.divide(entity.getYield(), new BigDecimal(100), 14);
            PaFinCashCalcRule paFinCashCalcRule = new PaFinCashCalcRule();
            FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
            BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, entity);
            finCashCalcRule.setFinprodType2(finprodType2);
            finCashCalcRule.setCashId(cashId);
            finCashCalcRule.setCalcType(calcType);
            finCashCalcRule.setBaseType(baseType);
            finCashCalcRule.setBaseDateType(baseDateType);
            finCashCalcRule.setBasis(basis);/* finCashCalcRule.setIsInitial(isInitial); */
            finCashCalcRule.setBranch(branch);
            finCashCalcRule.setYield(yield);
            paFinCashCalcRule.setCashId(cashId);
            paFinCashCalcRule.setEffDate(entity.getEffDate());
            /* paFinCashCalcRule.setBuyOutRate(buyOutRate); */
            paFinCashCalcRule.setInvestRate(investRate);
            paFinCashCalcRule.setLoadRate(yield);
            /* paFinCashCalcRule.setReferRate(referRate); */
            paFinCashCalcRuleList.add(paFinCashCalcRule);
            finCashCalcRuleList.add(finCashCalcRule);
        });
        dto.setIssueAmt(issueAmt);
        dto.setCashId(cashId);
        dto.setBasis(basis);
        dto.setPaFinCashCalcRuleList(paFinCashCalcRuleList);
        dto.setFinCashCalcRuleList(finCashCalcRuleList);
        return dto;
    }

    /**
     * 封装计算规则信息(通道)
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinAssetRateAdjDto encapFinAssetRateAdjDtoForChannel(FinAssetRateAdjDto dto) {
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        // 重新查询计算规则、初始化信息
        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        List<PaFinCashCalcRule> paFinCashCalcRuleList = new ArrayList<PaFinCashCalcRule>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", dto.getFinprodId());
        param.put("finprodType", DictCodeListExt1.金融产品类型_通道.getValue());
        List<FinAssetRateAdjDto> finAssetRateAdjList = selectInitAssetRateAdj(param);
        String cashId = finAssetRateAdjList.get(0).getCashId();
        String calcType = finAssetRateAdjList.get(0).getCalcType();
        String baseType = finAssetRateAdjList.get(0).getBaseType();
        String baseDateType = finAssetRateAdjList.get(0).getBaseDateType();
        String basis = finAssetRateAdjList.get(0).getBasis();
        BigDecimal issueAmt = finAssetRateAdjList.get(0).getIssueAmt();
        String finprodType2 =  finAssetRateAdjList.get(0).getFinprodType2();
        BigDecimal investRate = finAssetRateAdjList.get(0).getInvestRate();
		for (FinAssetRateAdjDto entity : dto.getSelectedItem()) {
			for (FinAssetRateAdjDto fin : finAssetRateAdjList) {
				if (fin.getFeeId().equals(entity.getFeeId())) {
					cashId = fin.getCashId();
					calcType = fin.getCalcType();
					baseType = fin.getBaseType();
					baseDateType = fin.getBaseDateType();
					basis = fin.getBasis();
					issueAmt = fin.getIssueAmt();
					finprodType2 = fin.getFinprodType2();
					investRate = fin.getInvestRate();
				}
			}
            // 生效利率
            BigDecimal yield = ArithmeticUtil.divide(entity.getYield(), new BigDecimal(100), 14);
            PaFinCashCalcRule paFinCashCalcRule = new PaFinCashCalcRule();
            FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
            BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, entity);
            finCashCalcRule.setFinprodId(dto.getFinprodId());
            finCashCalcRule.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
            finCashCalcRule.setFinprodType2(finprodType2);
            finCashCalcRule.setCashId(cashId);
            finCashCalcRule.setCalcType(calcType);
            finCashCalcRule.setBaseType(baseType);
            finCashCalcRule.setBaseDateType(baseDateType);
            finCashCalcRule.setBasis(basis);
            finCashCalcRule.setBranch(branch);
            finCashCalcRule.setYield(yield);
            paFinCashCalcRule.setCashId(cashId);
            paFinCashCalcRule.setEffDate(entity.getEffDate());
            paFinCashCalcRule.setInvestRate(investRate);
            paFinCashCalcRule.setLoadRate(yield);
            paFinCashCalcRuleList.add(paFinCashCalcRule);
            finCashCalcRuleList.add(finCashCalcRule);
        }
        dto.setIssueAmt(issueAmt);
        dto.setCashId(cashId);
        dto.setBasis(basis);
        dto.setPaFinCashCalcRuleList(paFinCashCalcRuleList);
        dto.setFinCashCalcRuleList(finCashCalcRuleList);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinAssetRateAdjDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        //通道
        if(StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_通道.getValue())){
            entity = encapFinAssetRateAdjDtoForChannel(entity);
        }else{
         // 重新计算现金流计划
            entity = encapFinAssetRateAdjDto(entity);
            if (null != entity.getSelectedItem() && !entity.getSelectedItem().isEmpty()) {
                try {
                    RateAdjDto rateAdjDto = new RateAdjDto();
                    BeanUtil.copyPropertiesIgnoreNull(rateAdjDto, entity);
                    rateAdjDto.setFinCashCalcRuleList(entity.getFinCashCalcRuleList());
                    rateAdjDto.setNowSystemDate(iPlatformDate.getSysDate());
                    //与批处理调用用一个逻辑
                    rateAdjDto = rateAdjService.againCalCashPlan(rateAdjDto);
                    if (rateAdjDto != null) {
                        entity.setFinCashPlanList(rateAdjDto.getFinCashPlanList());
                    }
                    //entity = againCalCashPlan(entity);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        //针对还本不付息资产，还本时，计划应付利息应等于区间还本未付利息
        entity =encapFinCashPlanIntAmt(entity);
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinAssetRateAdj/" + action, entity, Integer.class);
    }
    private FinAssetRateAdjDto encapFinCashPlanIntAmt(FinAssetRateAdjDto entity){
        if(!StringUtil.isEmptyList(entity.getFinCashPlanList()) && entity.getFinCashPlanList().size()>0){
            for(FinCashPlan plan:entity.getFinCashPlanList()){
                if(StringUtil.isNotEmpty(plan.getIsPayInt()) && StringUtil.equals(DictCodeList.是否_否.getValue(), plan.getIsPayInt())){//业务场景，是还本不带息的
                    plan.setIntAmt(plan.getRepayWithoutInt());
                }
            }
        }
        return entity;
    }
    /**
     * 重新计算现金流计划 <功能详细描述>
     *
     * @param entity
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    /*private FinAssetRateAdjDto againCalCashPlan(FinAssetRateAdjDto entity)
        throws ParseException {
        //分支序号
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        // 资产公共DTO
        AssetCommonInfoDto scai =  getAssetBasicInfoByFinprodId(entity.getFinprodId());
        // 利息计划
        List<FinCashPlan> finCashPlanList = new ArrayList<>();
        // 还本计划
        List<FinCashPlanDto> invRepayList = new ArrayList<>();
        // 参与计算付息计划
        List<FinCashPlanDto> scheList = new ArrayList<>();
        // 付息规则
        FinCashPayRule is = new FinCashPayRule();
        // 报价信息
        List<FinAssetRateAdjDto> rateList = new ArrayList<FinAssetRateAdjDto>();
        FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
            entity.getFinprodId(), DateUtil.format(entity.getVdate()), DictCodeListExt1.现金流二级类型_利息.getValue(),
            DictCodeListExt1.现金流业务类型_金融产品.getValue());
        BeanUtil.copyPropertiesIgnoreNull(is, finPayInterestStdInfoDto); // 复制付息规则
        for (FinCashCalcRule finCashCalcRule : entity.getFinCashCalcRuleList()) {
            FinAssetRateAdjDto invRateQuo = new FinAssetRateAdjDto();
            invRateQuo.setEffDate(finCashCalcRule.getEffDate());
            invRateQuo.setYield(finCashCalcRule.getYield());
            invRateQuo.setBasis(scai.getBasis());
            rateList.add(invRateQuo);
        }
        if (null != finPayInterestStdInfoService) {
            scheList = finPayInterestStdInfoService.getFinCashPlanByProductId(entity.getFinprodId(),
                DictCodeListExt1.现金流二级类型_利息.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                null);
            invRepayList = finPayInterestStdInfoService.getFinCashPlanByProductId(entity.getFinprodId(),
                DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),null);
        }
        // 计算付息区间利息
        if(StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            scai.setAmount(new BigDecimal("100"));
        }else{
            scai.setAmount(scai.getIssueAmt());
        }
        scai.setInvList(scheList);scai.setInvRepayList(invRepayList);
        scai = assetCommonService.againCalCashPlan(scai,rateList);
        scheList = InterestScheduleCal.interestScheduleCalA(scheList, scai, invRepayList, null, rateList, is, null,
            scai.getAmount(), FinCashPlanDetailDto.class);
        finCashPlanList.clear();
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
            if(StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                ||StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                ||StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                //计算区间净投资收益率%(加权利率)
                BigDecimal rangeYield = WeightedInterestRateCal.cal(rateList, entity1.getVdate(), entity1.getMdate());
                entity1.setRangeYield(rangeYield);
            }
            finCashPlanList.add(entity1);
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
            if(StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                ||StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                ||StringUtil.equals(scai.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                //计算区间净投资收益率%(加权利率)
                BigDecimal rangeYield = WeightedInterestRateCal.cal(rateList, entity2.getVdate(), entity2.getMdate());
                entity2.setRangeYield(rangeYield);
            }
            finCashPlanList.add(entity2);
        }
        entity.setFinCashPlanList(finCashPlanList);
        return entity;
    }*/

    /*private AssetCommonInfoDto getAssetBasicInfoByFinprodId(String finprodId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("finprodId", finprodId);
        map.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/FinAssetRateAdj/getAssetBasicInfoByFinprodId",map,
            AssetCommonInfoDto.class);
    }*/

    public List<FinAssetRateAdjDto> selectInitAssetRateAdj(Map<String,Object> param){
        return restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinAssetRateAdj/selectInitAssetRateAdj",
            param, FinAssetRateAdjDto.class);
    }

}
