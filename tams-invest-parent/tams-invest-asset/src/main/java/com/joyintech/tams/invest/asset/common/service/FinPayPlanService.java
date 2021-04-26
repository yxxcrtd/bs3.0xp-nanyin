/*
 * 文件名：FinPayInterestPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.common.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.asset.BussFinRepaymentService;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.invest.asset.common.dto.FinAssetRateAdjDto;
import com.joyintech.tams.invest.asset.common.dto.FinPayPlanDto;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;


/**
 * 支付计划维护
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月23日
 * @see FinPayInterestPlanService
 * @since 1.0
 */
@Service
public class FinPayPlanService extends CommonExportService<FinPayPlanDto> implements BaseProcessService<FinPayPlanDto>{

    @Autowired
    private AssetCommonService assetCommonService;

    @Autowired
    private DateCalService dateCalService;


    /**
     * 利率调整信息(报价信息)
     */
    @Autowired
    private FinAssetRateAdj001Service finAssetRateAdj001Service;

    @Autowired(required = false)
    private BussFinRepaymentService bussFinRepaymentService;

    @Autowired(required = false)
    private FinPayInterestStdInfoService finPayInterestStdInfoService;
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinPayPlanDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        //处理付息计划
        entity = encapFinPayPlanDto(entity);
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
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinPayPlan/" + action, entity, Integer.class);
    }

    private FinPayPlanDto encapFinPayPlanDto(FinPayPlanDto entity){
        //分支序号
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        //报价信息
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", entity.getFinprodId());
        param.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        param.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        List<FinAssetRateAdjDto> finAssetRateAdjList = finAssetRateAdj001Service.selectInitAssetRateAdj(param);
        //数据库取出的利率需要除以100参与计算
        if(null!=finAssetRateAdjList&&!finAssetRateAdjList.isEmpty()){
            for(FinAssetRateAdjDto finAssetRateAdjDto : finAssetRateAdjList){
                BigDecimal yield = ArithmeticUtil.divide(finAssetRateAdjDto.getYield(), new BigDecimal(100), 14);
                finAssetRateAdjDto.setYield(yield);
            }
        }
        // 重新计算现金流计划
        AssetCommonInfoDto scai = new AssetCommonInfoDto();
        scai.setFinprodId(entity.getFinprodId());
        scai.setFinprodType2(entity.getFinprodType2());
        scai.setVdate(entity.getVdate());
        scai.setMdate(entity.getMdate());
        scai.setAmount(entity.getIssueAmt());
        scai.setIssueAmt(entity.getIssueAmt());
        scai.setBasis(entity.getBasis());
        //到期日+15天
        Date  mdate = DateUtil.parse(DateUtil.plusDays(DateUtil.format(scai.getMdate()), 15));
        //获取节假日
        List<String> holidayList = dateCalService.getDealCalendarHoliday(scai.getVdate(),mdate,scai.getCalendarId());
        //前台付息计划
        if(null==entity.getFinPayInterestStdInfoDto()){
          //查询数据库付息计划
            scai.setInvList(finPayInterestStdInfoService.getFinCashPlanByProductId(scai.getFinprodId(), DictCodeListExt1.现金流二级类型_利息.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),null));
        }else{
            //前台带入
            scai.setInvList(entity.getFinPayInterestStdInfoDto().getFinCashPlanList());
        }
        //前台还本计划
        if(null==entity.getBussFinRepaymentDto()){
            //查询数据库还本计划
            scai.setInvRepayList(bussFinRepaymentService.getFinCashPlanByProductId(scai.getFinprodId(), DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),null));
        }else{
            scai.setInvRepayList(entity.getBussFinRepaymentDto().getFinCashPlanList());
        }
        try {
            scai = assetCommonService.againCalCashPlan(scai,finAssetRateAdjList);
            //付息计划
            List<FinCashPlan> lis = new ArrayList<FinCashPlan>();
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
                entity1.setMdateTerm(entity1.getMdate());
                if(null!=entity1.getRangeYield()){
                    entity1.setRangeYield(ArithmeticUtil.divide(entity1.getRangeYield(), new BigDecimal("100")));
                }
                if(DateUtil.compareDate(entity.getMdate(), entity1.getMdate())==0){
                    entity1.setCashType(DictCodeList.现金流类型_到期收息.getValue());
                }else{
                    entity1.setCashType(DictCodeList.现金流类型_中间收息.getValue());
                }
                entity1.setPayType(DictCodeList.分红方式_现金分红.getValue());
                entity1.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
                //计算支付日
                if(null==entity1.getPayDate()){
                    if(!DateUtil.isSameDate(entity.getMdate(), entity1.getMdate())){
                        String payDate =  AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(entity1.getMdate()), entity.getPayTdayRule(), holidayList);
                        entity1.setPayDate(DateUtil.parse(payDate));
                    }else{
                        entity1.setPayDate(entity.getMdate());
                    }
                }
                lis.add(entity1);
            }
            //还本计划
            List<FinCashPlan> lirs = new ArrayList<FinCashPlan>();
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
                entity2.setMdateTerm(entity2.getMdate());
                if(StringUtil.isNotEmpty(entity2.getIsPayInt()) && entity2.getIsPayInt().equals(DictCodeList.是否_否.getValue())){
                    entity2.setIsPayInt(entity2.getIsPayInt());
                }else{
                    entity2.setIsPayInt(DictCodeList.是否_是.getValue());
                }
                entity2.setCashBaseamt(scai.getIssueAmt());
                entity2.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
                if(DateUtil.compareDate(entity2.getMdate(), scai.getMdate())==0){
                    entity2.setCashType(DictCodeList.现金流类型_到期还本.getValue());
                }else{
                    entity2.setCashType(DictCodeList.现金流类型_中间还本.getValue());
                }
            
                //计算支付日
                if(null==entity2.getPayDate()){
                    if(!DateUtil.isSameDate(entity.getMdate(), entity2.getMdate())){
                    	/*modify by hxy 20200320 start 还本计划时，不知道支付日营业日准则，从付息计划中查询 ZGBUG-417*/
                    	if(StringUtil.isEmpty(entity.getPayTdayRule())){
                            Map<String,Object> paramMap = new HashMap<String,Object>();
                            paramMap.put("finprodId", entity.getFinprodId());
                            paramMap.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
                            paramMap.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
                            paramMap.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
                           //根据主键获取业务实体--标的金融产品基本信息
                            FinPayPlanDto dto=getBasicInfo(param);
                            if(StringUtil.isNotEmpty(dto)){
                            	entity.setPayTdayRule(dto.getPayTdayRule());	
                            }
                            
                    	}
                    	/*modify by hxy 20200320 end 还本计划时，不知道支付日营业日准则，从付息计划中查询 ZGBUG-417*/
                    	
                    	String payDate =  AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(entity2.getMdate()), entity.getPayTdayRule(), holidayList);
                        entity2.setPayDate(DateUtil.parse(payDate));
                    }else{
                        entity2.setPayDate(entity.getMdate());
                    }
                }
                lirs.add(entity2);
            }
            //计算现金流基数
            FinProduct finProduct = new FinProduct();
            finProduct.setMdate(scai.getMdate());
            finProduct.setIssueAmt(scai.getIssueAmt());
            entity.setFinCashPlanList(assetCommonService.calCashBaseamt(finProduct, lis, lirs));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return entity;
    }
    private FinPayPlanDto encapFinCashPlanIntAmt(FinPayPlanDto entity){
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
     * 获取基本信息
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public FinPayPlanDto getBasicInfo(Map<String,Object> paramMap){
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/FinPayPlan/getBasicInfo",paramMap,FinPayPlanDto.class);
    }

    /**
     * 获取基本信息
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public boolean isNotIng(Map<String,Object> paramMap){
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/FinPayPlan/isNotIng", paramMap, boolean.class);
    }
    
}
