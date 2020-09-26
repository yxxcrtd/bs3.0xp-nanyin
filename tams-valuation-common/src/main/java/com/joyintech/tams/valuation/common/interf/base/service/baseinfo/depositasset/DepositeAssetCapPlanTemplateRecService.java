/*
 * 文件名：DepositeAssetCapPlanTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.DepositeAssetBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstCapPlan;

/**
 * 存款类资产-还本计划模板处理Service
 * @author wangqidong
 * @version 1.0 2018年10月23日
 * @see DepositeAssetCapPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetCapPlanTemplateRecService {
    
    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 存款类资产-还本计划 接口模板处理
     * @param templateSourceFlgDTO templateSourceFlgDTO
     * @param acctEntity acctEntity
     * @return DepositeAssetBaseInfoValCommonResult
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public DepositeAssetBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                        TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        DepositeAssetBaseInfoValCommonResult result = new DepositeAssetBaseInfoValCommonResult();
        List<ValBotAstCapPlan> listValBotAstCapPlan = new ArrayList<ValBotAstCapPlan>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValBotAstCapPlan = returnResult.getT(ValBotAstCapPlan.class);
        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 现金流计划List
        List<FinCashPlan> listValCashPlan = handleValBotAstRatePlan(listValBotAstCapPlan);
        result.setListValCashPlan(listValCashPlan);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValBotAstCapPlan
     * @return
     * @see
     * @since 1.0
     */
    private List<FinCashPlan> handleValBotAstRatePlan(List<ValBotAstCapPlan> listValBotAstCapPlan) {
        // 定义 现金流计划List
        List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
        for (ValBotAstCapPlan dto : listValBotAstCapPlan) {
            // 现金流计划
            FinCashPlan ValCashPlan = getValCashPlan(dto);
            listValCashPlan.add(ValCashPlan);
        }
        return listValCashPlan;
    }

    /**
     * T6 现金流计划
     * @param dto ValBotAstCapPlan 
     * @return FinCashPlan
     * @see
     * @since 1.0
     */
    private FinCashPlan getValCashPlan(ValBotAstCapPlan dto) {
        // 定义 现金流计划
        FinCashPlan valFinCashPlan = new FinCashPlan();
        // 现金流代码 = InterfHelper.getMd5CashId_（E.资产代码,数据字典：金融产品类型.存款,数据字典：现金流二级类型.本金)
        valFinCashPlan.setCashId(InterfHelper.getMd5CashId_(dto.getAssetId(),
            DictCodeListExt1.金融产品类型_存款.getValue(),DictCodeListExt1.现金流二级类型_本金.getValue()));
        // 现金流序号 = E.现金流序号
        valFinCashPlan.setCashNum(dto.getOrderNo().longValue());
        // 未调整的计算结束日 = E.还本日
        valFinCashPlan.setMdateUnadjust(dto.getRepayDate());
        // 未调整的计划支付日 = E.支付日
        valFinCashPlan.setPayDateUnadjust(dto.getPayDate());
        // 调整后的计算结束日 = E.还本日
        valFinCashPlan.setMdate(dto.getRepayDate());
        // 调整后的计划支付日 = E.支付日
        valFinCashPlan.setPayDate(dto.getPayDate());
        // 计划支付本金  = E.还本金额
        valFinCashPlan.setPrinAmt(dto.getRepayAmt());
        // 计息本金 = E.还本金额
        valFinCashPlan.setIntPrinAmt(dto.getRepayAmt());
        // 是否支付利息 = 利息金额>0 --> 是
                    // 利息金额<=0 --> 否
        if(dto.getIntAmt().compareTo(BigDecimal.ZERO) > 0 ){
            valFinCashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
        }else if(dto.getIntAmt().compareTo(BigDecimal.ZERO) <= 0){
            valFinCashPlan.setIsPayInt(DictCodeList.是否_否.getValue());
        }
        // 计划支付利息 = E.利息金额
        valFinCashPlan.setIntAmt(dto.getIntAmt());
        // 计划支付现金流 = E.还本金额+E.利息金额        
        valFinCashPlan.setCashAmt(ArithmeticUtil.doAdd(2,RoundingMode.UP, dto.getRepayAmt(),dto.getIntAmt()));
//        valFinCashPlan.setCashAmt(ArithmeticUtil.round(
//            ArithmeticUtil.doAdd(dto.getRepayAmt(), dto.getIntAmt()),NumberEnum.MONEY));
        // 现金流单位 = 固定值  02：总额
        valFinCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = E.资产代码
        valFinCashPlan.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 数据字典：金融产品类型.存款
        valFinCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        // 分支序号 = 固定值   设置全局变量(默认为0）
        valFinCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
        return valFinCashPlan;
    }

}
