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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.asset.BussFinRepaymentService;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.component.business.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.common.dto.FinAssetRateAdjDto;
import com.joyintech.tams.invest.asset.common.dto.FinPayPlanDto;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.interf.trade.fromxp.service.InterfServiceLogService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 支付计划维护
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月23日
 * @see FinPayPlanService
 * @since 1.0
 */
@Service
public class FinPayPlanService extends CommonExportService<FinPayPlanDto> implements BaseProcessService<FinPayPlanDto>{
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(FinPayPlanService.class);

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

    @Autowired
    private InterfServiceLogService interfServiceLogService;

    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinPayPlanDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        //处理付息计划
        entity = encapFinPayPlanDto(entity);
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
            //对前台传入的还本计划，是否还本不带息，进行重新赋值
            String isPayIntS =  "";
            if(!StringUtil.isEmptyList(scai.getInvRepayList()) && scai.getInvRepayList().size()>0){
                 for(FinCashPlanDto it:scai.getInvRepayList()){
                     if(StringUtil.isNotEmpty(it.getIsPayInt()) && it.getIsPayInt().equals(DictCodeList.是否_否.getValue())){
                         isPayIntS=DictCodeList.是否_否.getValue();
                         break;
                     }
                 }
                 if(StringUtil.isNotEmpty(isPayIntS) && isPayIntS.equals(DictCodeList.是否_否.getValue())){
                     for(FinCashPlanDto it:scai.getInvRepayList()){
                         if(StringUtil.isEmpty(it.getIsPayInt())){
                             it.setIsPayInt(isPayIntS);
                         }
                     }
                 }
            }

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
                entity2.setIsPayInt(DictCodeList.是否_是.getValue());
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
            logger.error(e.getMessage(),e);
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


    /**
     * 资产还本计划(从信评接口接收)
     * @param id
     * @param msg
     */
    public void executeInterf(String id,String msg){
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        FinPayPlanDto entity  = new FinPayPlanDto();
        //FinPayInterestStdInfoDto finPayInterestStdInfoDto = new FinPayInterestStdInfoDto();
        BussFinRepaymentDto bussFinRepaymentDto = new BussFinRepaymentDto();
        try{
            JSONObject jsonObject = JSONObject.parseObject(msg);
            String finprodMarketId = jsonObject.getString("finprod_market_id");
            String finprodName = jsonObject.getString("finprod_name");
            //构造还本付息计划，模拟前台录入，便于调用共通
            entity.setFinprodMarketId(finprodMarketId);
            entity.setFinprodName(finprodName);
            packFinproduct(entity);
            JSONArray payList = JSONObject.parseArray(jsonObject.getString("pay_list"));
            if(null!=payList&&payList.size()>0){
                String finprodId = entity.getFinprodId();
                String cashIdHb = commonBusinessService.generateCashFlowCode(finprodId, entity.getFinprodType2(), branch, DictCodeListExt1.现金流二级类型_本金.getValue());
                //String cashIdFx = commonBusinessService.generateCashFlowCode(finprodId, DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, DictCodeListExt1.现金流二级类型_利息.getValue());
                //还本计划
                List<FinCashPlanDto> finCashPlanDtoHb = new ArrayList<>();
                for (int i = 0; i < payList.size(); i++) {
                    FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                    JSONObject jso = (JSONObject)payList.get(i);
                    String payType = jso.getString("pay_type");
                    int payDate = jso.getInteger ("pay_date");
                    BigDecimal prinAmt = jso.getBigDecimal("prin_amt");
                    BigDecimal prinPer = jso.getBigDecimal("prin_per");
                    Date mdate = DateUtil.parse(Integer.toString(payDate));
                    finCashPlanDto.setMdate(mdate);
                    finCashPlanDto.setPayDate(mdate);
                    finCashPlanDto.setPrinAmt(prinAmt);
                    finCashPlanDto.setCashId(cashIdHb);
                    finCashPlanDtoHb.add(finCashPlanDto);
                }
                finCashPlanDtoHb = finCashPlanDtoHb.stream().sorted((o1,o2) -> {return DateUtil.compareDate(o1.getMdate(),o2.getMdate());}).collect(Collectors.toList());
                //付息计划
                /*List<FinCashPlanDto> finCashPlanDtoFx = new ArrayList<>();
                for (int i = 0; i < finCashPlanDtoHb.size(); i++) {
                    FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                    FinCashPlanDto item = finCashPlanDtoHb.get(i);
                    finCashPlanDto.setCashId(cashIdFx);
                    finCashPlanDto.setFinprodId(finprodId);
                    if(i==0){
                        finCashPlanDto.setVdate(entity.getVdate());
                        finCashPlanDto.setMdate(item.getMdate());
                        finCashPlanDtoFx.add(finCashPlanDto);
                        continue;
                    }
                    finCashPlanDto.setVdate(finCashPlanDtoHb.get(i-1).getMdate());
                    finCashPlanDto.setMdate(item.getMdate());
                    finCashPlanDtoFx.add(finCashPlanDto);
                }
                finPayInterestStdInfoDto.setFinCashPlanList(finCashPlanDtoFx);*/
                bussFinRepaymentDto.setFinCashPlanList(finCashPlanDtoHb);
                entity.setBussFinRepaymentDto(bussFinRepaymentDto);
                //entity.setFinPayInterestStdInfoDto(finPayInterestStdInfoDto);
                doComplete(entity, OperationType.NEW);
            }
            interfServiceLogService.updateInterfServiceLog(id, true, "资产还本计划接口数据接入成功！");
        }catch (Exception e){
            interfServiceLogService.updateInterfServiceLog(id, false, "资产还本计划接口数据接入失败:"+e.getMessage());
        }
    }

    public void packFinproduct(FinPayPlanDto entity){
        FinProduct finProduct = assetCommonService.getAsset(entity.getFinprodMarketId(),entity.getFinprodName());
        BeanUtil.copyPropertiesIgnoreNull(entity,finProduct);
    }


    
}
