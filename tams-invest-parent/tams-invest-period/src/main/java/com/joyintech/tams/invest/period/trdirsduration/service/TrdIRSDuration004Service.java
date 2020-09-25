package com.joyintech.tams.invest.period.trdirsduration.service;

import com.alibaba.fastjson.JSON;
import com.jixu.common.util.JsonUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinCashPlanEntity;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.autoperiod.irs.AutoPeriodIRSCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PaymentPlanEditDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration004ProcessDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 利率互换存续期->利率互换收付息计划维护
 *
 * @author : xkni
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.service
 * @Description: 利率互换存续期->利率互换收付息计划维护
 * @date Date : 2019年08月15日
 */
@Service
public class TrdIRSDuration004Service extends BaseService<TrdIRSDuration004ProcessDto>
        implements BaseProcessService<TrdIRSDuration004ProcessDto> {

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;
    @Autowired
    private AutoPeriodIRSCommonService autoPeriodIRSCommonService;


    @Override
    public void doComplete(TrdIRSDuration004ProcessDto entity, OperationType operationType) {
        entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());

        //审核通过保存数据到现金流计划表
        paymentPlanEdit(entity);
    }


    /**
     * 调用交易微服务-利率互换交易-利率互换交易查询接口，将数据显示到页面上。传入参数：交易编号
     *  页面初始化列表数据加载
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<FinCashPlanEntity>  getPaymentPlanList(String tradeId) {

        return restTemplate.getForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdIRSTrade/getCashPlanList/"+tradeId,
                FinCashPlanEntity.class);

    }
    //调用交易微服务-利率互换存续期交易-利率互换付息计划修改（生效）
    public void paymentPlanEdit(TrdIRSDuration004ProcessDto dto)  {

        PaymentPlanEditDto paymentPlanEditDto=new PaymentPlanEditDto();
        List<FinCashPlan> fixedFinCashPlanList=new ArrayList<>();
        List<FinCashPlan> floatFinCashPlanList=new ArrayList<>();
        List<FinCashPlanEntity> collectlExitsList=new ArrayList<>();
        Integer fixedFinCashCount=0;
        Integer floatFinCashCount=0;
        // 分支序号
        String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
        Long branchLong = Long.parseLong(branch);

        if(!StringUtils.isEmptyOrWhitespace( dto.getPaymentPlanListMsg())) {
            try {
                dto.setPaymentPlanList(JsonUtils.json2List(dto.getPaymentPlanListMsg(), FinCashPlanEntity.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //循环固息端付息计划，构建现金流计划Entity，并加入到利率互换付息计划维护DTO.List<现金流计划Entity>
        for( TrdIRSDurationDto fixedItem: dto.getFixedPaymentPlanTable()) {
            FinCashPlan fixedFinCashPlan=new FinCashPlan();
            fixedFinCashPlan.setCashId(dto.getFixedCashId());//现金流代码-》画面.固息端现金流代码
            fixedFinCashPlan.setCashNum(fixedFinCashCount.longValue()); //现金流序号，从1开始
            //判断当前数据数据库中是否存在

            if(dto.getPaymentPlanList()!=null && dto.getPaymentPlanList().size()>0) {

                collectlExitsList = dto.getPaymentPlanList().stream().filter(s -> s.getCashId().equals(dto.getFixedCashId()) && s.getFinprodId().equals(dto.getFinprodId()) && s.getVdate().getTime() == fixedItem.getFixVdate().getTime() && s.getMdate().getTime() == fixedItem.getFixMdate().getTime()).collect(Collectors.toList());

            }
            if(collectlExitsList!=null &&collectlExitsList.size()>0)
            {
                fixedFinCashPlan.setBranch(collectlExitsList.get(0).getBranch());
                fixedFinCashPlan.setTermdays(collectlExitsList.get(0).getTermdays());
                fixedFinCashPlan.setVdateY(collectlExitsList.get(0).getVdateY());
                fixedFinCashPlan.setVdateTerm(collectlExitsList.get(0).getVdateTerm());
                fixedFinCashPlan.setMdateTerm(collectlExitsList.get(0).getMdateTerm());
                fixedFinCashPlan.setPrinAmt(collectlExitsList.get(0).getPrinAmt());
                fixedFinCashPlan.setIntPrinAmt(collectlExitsList.get(0).getIntPrinAmt());
                fixedFinCashPlan.setIntAmt(collectlExitsList.get(0).getIntAmt());
                fixedFinCashPlan.setCashAmt(collectlExitsList.get(0).getCashAmt());
                fixedFinCashPlan.setCashBaseamt(collectlExitsList.get(0).getCashBaseamt());
                fixedFinCashPlan.setCashUnitType(collectlExitsList.get(0).getCashUnitType());
                fixedFinCashPlan.setCalcFunction(collectlExitsList.get(0).getCalcFunction());
                fixedFinCashPlan.setFrequency(collectlExitsList.get(0).getFrequency());
                fixedFinCashPlan.setFinprodType(collectlExitsList.get(0).getFinprodType());
                fixedFinCashPlan.setFinprodType2(collectlExitsList.get(0).getFinprodType2());
                fixedFinCashPlan.setRangeYield(collectlExitsList.get(0).getRangeYield());
                fixedFinCashPlan.setCreateTime(collectlExitsList.get(0).getCreate_time());
                fixedFinCashPlan.setCreateUser(collectlExitsList.get(0).getCreate_user());
                fixedFinCashPlan.setCreateDept(collectlExitsList.get(0).getCreate_dept());
                fixedFinCashPlan.setUpdateUser(collectlExitsList.get(0).getUpdate_user());
                fixedFinCashPlan.setUpdateTime(collectlExitsList.get(0).getUpdate_time());

            }
            else
            {
                fixedFinCashPlan.setCreateTime(dto.getCreateTime());
                fixedFinCashPlan.setCreateUser(dto.getCreateUser());
                fixedFinCashPlan.setCreateDept(dto.getCreateDept());
                fixedFinCashPlan.setUpdateUser(dto.getUpdateUser());
                fixedFinCashPlan.setUpdateTime(dto.getUpdateTime());
            }

            if(fixedFinCashCount==dto.getFixedPaymentPlanTable().size()-1) {
                //最后一条
                fixedFinCashPlan.setCashType(DictCodeList.现金流类型_到期收息.getValue());//数据字典缓存.现金流类型.中间收息，对于最后一条为到期收息
            }
            else
            {
                fixedFinCashPlan.setCashType(DictCodeList.现金流类型_中间收息.getValue());//数据字典缓存.现金流类型.中间收息，对于最后一条为到期收息
            }
            fixedFinCashPlan.setVdateUnadjust(fixedItem.getFixVdate()); //未调整的计算开始日->画面.计息开始日
            fixedFinCashPlan.setMdateUnadjust(fixedItem.getFixMdate());//未调整的计算结束日->画面.计息结束日
            fixedFinCashPlan.setPayDateUnadjust(fixedItem.getFixPayDate());//未调整的计划支付日->画面.理论支付日
            fixedFinCashPlan.setVdate(fixedItem.getFixVdate()); //调整后的计算开始日->画面.计息开始日
            fixedFinCashPlan.setMdate(fixedItem.getFixMdate());//调整后的计算结束日->画面.计息结束日
            fixedFinCashPlan.setPayDate(fixedItem.getFixPayDate());//调整后的计划支付日->画面.理论支付日
            fixedFinCashPlan.setTermdaysTerm(com.joyintech.framework.util.DateUtil.getBetweenDayNumber(fixedItem.getFixVdate(),fixedItem.getFixMdate()));//完整周期天数 画面.计息结束日 - 画面.计息开始日
            fixedFinCashPlan.setFinprodId(dto.getFinprodId());//金融产品代码
            fixedFinCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue()); //金融产品类型（估值核算）
            fixedFinCashPlan.setPayType(DictCodeList.分红方式_现金分红.getValue());//支付方式，现金、红利再投等
            fixedFinCashPlan.setRemark(fixedItem.getRemark());//备注
            fixedFinCashPlan.setBranch(branchLong);//分支号
           // fixedFinCashPlan.setIsPayInt(fixedItem.getStatusValid().equals("已付息")?DictCodeList.是否_是.getValue():DictCodeList.是否_否.getValue());
            fixedFinCashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
            fixedFinCashCount++;
            fixedFinCashPlanList.add(fixedFinCashPlan);
        }

        //循环浮息端付息计划，构建现金流计划Entity，并加入到利率互换付息计划维护DTO.List<现金流计划Entity>
        for( TrdIRSDurationDto floatItem: dto.getFloatPaymentPlanTable()) {
            FinCashPlan floatFinCashPlan=new FinCashPlan();
            floatFinCashPlan.setCashId(dto.getFloatCashId());//现金流代码-》画面.浮息端现金流代码
            floatFinCashPlan.setCashNum(floatFinCashCount.longValue()); //现金流序号，从1开始
            //判断当前数据数据库中是否存在
            if(dto.getPaymentPlanList()!=null && dto.getPaymentPlanList().size()>0) {
                collectlExitsList = dto.getPaymentPlanList().stream().filter(s -> s.getCashId().equals(dto.getFloatCashId()) && s.getFinprodId().equals(dto.getFinprodId()) && s.getVdate().getTime() == floatItem.getFloatVdate().getTime() && s.getMdate().getTime() == floatItem.getFloatMdate().getTime()).collect(Collectors.toList());
            }
            if(collectlExitsList!=null &&collectlExitsList.size()>0)
            {
                floatFinCashPlan.setBranch(collectlExitsList.get(0).getBranch());
                floatFinCashPlan.setTermdays(collectlExitsList.get(0).getTermdays());
                floatFinCashPlan.setVdateY(collectlExitsList.get(0).getVdateY());
                floatFinCashPlan.setVdateTerm(collectlExitsList.get(0).getVdateTerm());
                floatFinCashPlan.setMdateTerm(collectlExitsList.get(0).getMdateTerm());
                floatFinCashPlan.setPrinAmt(collectlExitsList.get(0).getPrinAmt());
                floatFinCashPlan.setIntPrinAmt(collectlExitsList.get(0).getIntPrinAmt());
                floatFinCashPlan.setIntAmt(collectlExitsList.get(0).getIntAmt());
                floatFinCashPlan.setCashAmt(collectlExitsList.get(0).getCashAmt());
                floatFinCashPlan.setCashBaseamt(collectlExitsList.get(0).getCashBaseamt());
                floatFinCashPlan.setCashUnitType(collectlExitsList.get(0).getCashUnitType());
                floatFinCashPlan.setCalcFunction(collectlExitsList.get(0).getCalcFunction());
                floatFinCashPlan.setFrequency(collectlExitsList.get(0).getFrequency());
                floatFinCashPlan.setFinprodType(collectlExitsList.get(0).getFinprodType());
                floatFinCashPlan.setFinprodType2(collectlExitsList.get(0).getFinprodType2());
                floatFinCashPlan.setRangeYield(collectlExitsList.get(0).getRangeYield());
                floatFinCashPlan.setCreateTime(collectlExitsList.get(0).getCreate_time());
                floatFinCashPlan.setCreateUser(collectlExitsList.get(0).getCreate_user());
                floatFinCashPlan.setCreateDept(collectlExitsList.get(0).getCreate_dept());
                floatFinCashPlan.setUpdateUser(collectlExitsList.get(0).getUpdate_user());
                floatFinCashPlan.setUpdateTime(collectlExitsList.get(0).getUpdate_time());

            }
            else
            {
                floatFinCashPlan.setCreateTime(dto.getCreateTime());
                floatFinCashPlan.setCreateUser(dto.getCreateUser());
                floatFinCashPlan.setCreateDept(dto.getCreateDept());
                floatFinCashPlan.setUpdateUser(dto.getUpdateUser());
                floatFinCashPlan.setUpdateTime(dto.getUpdateTime());
            }


            if(floatFinCashCount==dto.getFloatPaymentPlanTable().size()-1) {
                //最后一条
                floatFinCashPlan.setCashType(DictCodeList.现金流类型_到期收息.getValue());//数据字典缓存.现金流类型.中间收息，对于最后一条为到期收息
            }
            else
            {
                floatFinCashPlan.setCashType(DictCodeList.现金流类型_中间收息.getValue());//数据字典缓存.现金流类型.中间收息，对于最后一条为到期收息
            }
            floatFinCashPlan.setVdateUnadjust(floatItem.getFloatVdate()); //未调整的计算开始日->画面.计息开始日
            floatFinCashPlan.setMdateUnadjust(floatItem.getFloatMdate());//未调整的计算结束日->画面.计息结束日
            floatFinCashPlan.setPayDateUnadjust(floatItem.getFloatPayDate());//未调整的计划支付日->画面.理论支付日
            floatFinCashPlan.setVdate(floatItem.getFloatVdate()); //调整后的计算开始日->画面.计息开始日
            floatFinCashPlan.setMdate(floatItem.getFloatMdate());//调整后的计算结束日->画面.计息结束日
            floatFinCashPlan.setPayDate(floatItem.getFloatPayDate());//调整后的计划支付日->画面.理论支付日
            floatFinCashPlan.setTermdaysTerm(com.joyintech.framework.util.DateUtil.getBetweenDayNumber(floatItem.getFloatVdate(),floatItem.getFloatMdate()));//完整周期天数 画面.计息结束日 - 画面.计息开始日
            floatFinCashPlan.setFinprodId(dto.getFinprodId());//金融产品代码
            floatFinCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue()); //金融产品类型（估值核算）
            floatFinCashPlan.setPayType(DictCodeList.分红方式_现金分红.getValue());//支付方式，现金、红利再投等
            floatFinCashPlan.setRemark(floatItem.getRemark());//备注
           // floatFinCashPlan.setIsPayInt(floatItem.getStatusValid().equals("已付息")?DictCodeList.是否_是.getValue():DictCodeList.是否_否.getValue());
            floatFinCashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
            floatFinCashPlan.setBranch(branchLong);//分支号
            floatFinCashCount++;
            floatFinCashPlanList.add(floatFinCashPlan);
        }

        paymentPlanEditDto.setFixedFinCashPlanList(fixedFinCashPlanList);
        paymentPlanEditDto.setFloatFinCashPlanList(floatFinCashPlanList);
        restTemplate.postForEntity(
                ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/paymentPlanEdit", (Object) paymentPlanEditDto,
                Boolean.class);
    }

    public BaseTotalTradeInfoDto genFinProductTradeDto2(TrdIRSDuration004ProcessDto entity) {
        BaseTotalTradeInfoDto result = autoPeriodIRSCommonService.genFinProductTradeDto(entity);

        return result;
    }
}
