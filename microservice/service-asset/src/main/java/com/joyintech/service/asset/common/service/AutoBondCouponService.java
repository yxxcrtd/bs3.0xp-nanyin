package com.joyintech.service.asset.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.common.dao.AutoBondCouponMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.dao.FinExercisePlanMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecinfo;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecoption;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecschedule;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

@Service
public class AutoBondCouponService extends BaseService {

    @Autowired
    private AutoBondCouponMapper autoBondCouponMapper;

    @Autowired
    private FinExercisePlanMapper  finExercisePlanMapper;

    @Autowired
    private  FinCashPlanMapper finCashPlanMapper;
    
    @Autowired
    private IPlatformDate iPlateFormDate;
    
    public void update(){
        Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        List<BaseFinProductBaseInfoDto> resultList = new ArrayList<BaseFinProductBaseInfoDto>();
        List<String> list = autoBondCouponMapper.selectSecIdForUpdate();
        List<FinProduct> secIdList = autoBondCouponMapper.getIsUpdateFinprodMarketId(list);
        // 平台日期
        Date plateFormDate = iPlateFormDate.getSysDate();
        
        if(null!=secIdList&&!secIdList.isEmpty()){
            for(FinProduct finProduct : secIdList){
                BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
                String finprodId = finProduct.getFinprodId();
                Date maxTradeDate =  autoBondCouponMapper.getMaxTradeDate(finProduct.getFinprodId());
                //WIND_债券基本信息
                InterfPayhsrcsecinfo interfPayhsrcsecinfo = autoBondCouponMapper.selectWindBondAssetBySecId(finProduct.getFinprodMarketId());
                if (interfPayhsrcsecinfo == null) {
                	continue;
                }
                //WIND_债券行权信息数据
                List<InterfPayhsrcsecoption> payhsrcsecoptionList = autoBondCouponMapper.selectWindBondExercise(finProduct.getFinprodMarketId());
                //WIND_债券付息计划数据（只需取交易日之后的计划）
                List<InterfPayhsrcsecschedule> interfPayhsrcsecscheduleList = autoBondCouponMapper.selectWindBondSchedule(finProduct.getFinprodMarketId(), null);
                //报价信息由接口直接更新了业务表  不需要单独处理
                //WIND_浮息债报价表
                /*List<InterfFloatcouprate> interfFloatingcouponsrateList = autoBondCouponMapper.selectInterfFloatingcouponsrate(secid);*/
                //业务表付息计划
                List<FinCashPlan> 付息计划 = autoBondCouponMapper.selectFinCashPlan(BussinessDictCodeList.现金流类型_中间收息.getValue(),BussinessDictCodeList.现金流类型_到期收息.getValue(), null, finProduct.getFinprodId());
                //业务表还本计划
                List<FinCashPlan> 还本计划 = autoBondCouponMapper.selectFinCashPlan(BussinessDictCodeList.现金流类型_中间还本.getValue(),BussinessDictCodeList.现金流类型_到期还本.getValue(), null, finProduct.getFinprodId());
                //new标的类金融产品
                finProduct.setMdate(interfPayhsrcsecinfo.getMdate());
                baseFinProductBaseInfoDto.setFinProduct(finProduct);
                //现金流支付计划
                List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
                for(InterfPayhsrcsecschedule interfPayhsrcsecschedule:interfPayhsrcsecscheduleList){
                	
                	// 付息计划实际付息结束日 <= 平台日期,不更新
                	if (plateFormDate.compareTo(interfPayhsrcsecschedule.getInterestendday()) >= 0) {
                		continue;
                	}
                	
                    /**--modify 2019-04-17 start--**/
                    //如果为"03"或者"02"-还本，则设置本金的现金流代码,否则为"04","01"-收息，设置收息的现金流代码
                    //支付本金
                    if("03".equals(interfPayhsrcsecschedule.getSchedutype()) || "02".equals(interfPayhsrcsecschedule.getSchedutype())){
                        FinCashPlan finCashPlan = new FinCashPlan();
                        String cashId;
                        if (CollectionUtils.isEmpty(还本计划)) {
                        	cashId = CommonUtil.generateCashFlowCode(finprodId, DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, DictCodeListExt1.现金流二级类型_本金.getValue());
                        } else {
                        	cashId = 还本计划.get(0).getCashId();
                        }
                        finCashPlan.setCashId(cashId);
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
                        finCashPlan.setIntAmt(BigDecimal.ZERO);
                        finCashPlan.setIsPayInt(BussinessDictCodeList.是否_是.getValue());
                        finCashPlan.setCashAmt(interfPayhsrcsecschedule.getPrinamtpay());
                        finCashPlan.setCashBaseamt(new BigDecimal(100));
                        finCashPlan.setMdateUnadjust(interfPayhsrcsecschedule.getInterestendday());
                        finCashPlan.setPayDateUnadjust(interfPayhsrcsecschedule.getInterestpayday());
                        finCashPlan.setMdate(interfPayhsrcsecschedule.getInterestendday());
                        finCashPlan.setPayDate(interfPayhsrcsecschedule.getInterestpayday());
                        finCashPlan.setCashUnitType(BussinessDictCodeList.现金流单位_百元法.getValue());
                        finCashPlan.setFinprodId(finprodId);
                        finCashPlan.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
                        finCashPlan.setBranch(branch);
                        finCashPlan.setPayType(BussinessDictCodeList.分红方式_现金分红.getValue());
                        finCashPlanList.add(finCashPlan);
                    }else if("04".equals(interfPayhsrcsecschedule.getSchedutype()) || "01".equals(interfPayhsrcsecschedule.getSchedutype())){
                        FinCashPlan finCashPlan = new FinCashPlan();
                        //如果资讯_债券付息计划数据Entity.实际付息结束日 = 资讯_债券基本信息数据Entity.到期日，则为到期还本
                        String cashId;
                        if (CollectionUtils.isEmpty(付息计划)) {
                        	cashId = CommonUtil.generateCashFlowCode(finprodId, DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, DictCodeListExt1.现金流二级类型_利息.getValue());
                        } else {
                        	cashId = 付息计划.get(0).getCashId();
                        }
                        finCashPlan.setCashId(cashId);
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
                        finCashPlan.setIntPrinAmt(interfPayhsrcsecschedule.getPrinamt());
                        finCashPlan.setIntAmt(interfPayhsrcsecschedule.getAccrueintamt());
                        finCashPlan.setCashAmt(interfPayhsrcsecschedule.getAccrueintamt());
                        finCashPlan.setCashBaseamt(new BigDecimal(100));
                        finCashPlan.setMdateUnadjust(interfPayhsrcsecschedule.getInterestendday());
                        finCashPlan.setPayDateUnadjust(interfPayhsrcsecschedule.getInterestpayday());
                        finCashPlan.setMdate(interfPayhsrcsecschedule.getInterestendday());
                        finCashPlan.setPayDate(interfPayhsrcsecschedule.getInterestpayday());
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
                // finCashPlanList.addAll(付息计划);finCashPlanList.addAll(还本计划);
                baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);
                
                //new行权信息
                List<FinExercisePlan> finExercisePlanList = new ArrayList<FinExercisePlan>();
                for(InterfPayhsrcsecoption payhsrcsecoption:payhsrcsecoptionList){
                	
                	// 行权日 <= 平台日期,不更新
                	if (plateFormDate.compareTo(payhsrcsecoption.getOptionpayday()) >= 0) {
                		continue;
                	}
                	
                    FinExercisePlan finExercisePlan = new FinExercisePlan();
                    String cashId;
                    if (CollectionUtils.isEmpty(还本计划)) {
                    	cashId = CommonUtil.generateCashFlowCode(finprodId, DictCodeListExt1.现金流业务类型_金融产品.getValue(), branch, DictCodeListExt1.现金流二级类型_本金.getValue());
                    } else {
                    	cashId = 还本计划.get(0).getCashId();
                    }
                    finExercisePlan.setCashId(cashId);
                    finExercisePlan.setExerciseType(payhsrcsecoption.getOptiontype());
                    finExercisePlan.setExerciseStatus(DictCodeList.行权状态_未行权.getValue());
                    finExercisePlan.setVdate(payhsrcsecoption.getOptionstartday());
                    finExercisePlan.setMdate(payhsrcsecoption.getOptionendday());
                    finExercisePlan.setActExerciseDate(payhsrcsecoption.getOptionpayday());//实际行权日
                    finExercisePlan.setExerciseDate(payhsrcsecoption.getTheoryoptionpayday());//行权信息表理论行权日
                    finExercisePlan.setExercisePrice(payhsrcsecoption.getOptionprice());
                    finExercisePlan.setExerciseRemark(payhsrcsecoption.getOptionclause());//行权信息表行权条款
                    finExercisePlan.setFinprodId(finprodId);
                    finExercisePlan.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                    finExercisePlan.setBranch(branch);
                    finExercisePlanList.add(finExercisePlan);
                }
                baseFinProductBaseInfoDto.setFinExercisePlanList(finExercisePlanList);
                resultList.add(baseFinProductBaseInfoDto);
            }
        }
        入库(resultList, plateFormDate);
     }

	private void 入库(List<BaseFinProductBaseInfoDto> resultList, Date plateFormDate){
        for(BaseFinProductBaseInfoDto dto : resultList){
        	//更新债的到期日
        	if (dto.getFinProduct().getMdate() != null) {
        		autoBondCouponMapper.updateMdate(dto.getFinProduct());
        	}
            //更新行权计划
            autoBondCouponMapper.deleteFinExercisePlan(dto.getFinProduct().getFinprodId(), plateFormDate);
            dto.getFinExercisePlanList().forEach(item ->{
                finExercisePlanMapper.insert(item);
            });
            //更新付息计划
            autoBondCouponMapper.deleteFinCashPlan(dto.getFinProduct().getFinprodId(), plateFormDate);
            dto.getFinCashPlanList().forEach(item ->{
                finCashPlanMapper.insert(item);
            });
        }
     }
}
