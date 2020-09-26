/*
 * 文件名：PenaltyInterestRateInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年11月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.penaltyinterest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.PenaltyInterestCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFxTargetRateAdjust;

/**
 * 拆借利率调整
 * 
 * @author dongjun
 * @version 1.0 2018年11月27日
 * @see PenaltyInterestRateInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PenaltyInterestRateInfoRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public PenaltyInterestCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        PenaltyInterestCommonResult result = new PenaltyInterestCommonResult();
        List<ValFxTargetRateAdjust> listValFxTargetRateAdjust = new ArrayList<ValFxTargetRateAdjust>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFxTargetRateAdjust = returnResult.getT(ValFxTargetRateAdjust.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 现金流计算规则表
        List<FinCashCalcRule> listValCashCalcRule = handleValFxTargetRateAdjust(
            listValFxTargetRateAdjust);
        result.setListValCashCalcRule(listValCashCalcRule);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValCashPlan
     * @return 现金流计算规则表 list
     */
    private List<FinCashCalcRule> handleValFxTargetRateAdjust(List<ValFxTargetRateAdjust> listValFxTargetRateAdjust) {
        // 现金流计划 list
        List<FinCashCalcRule> listValCashPlan = new ArrayList<FinCashCalcRule>();
        for (ValFxTargetRateAdjust dto : listValFxTargetRateAdjust) {
            // 现金流计划
            FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(dto);
            listValCashPlan.add(finCashCalcRule);
        }
        return listValCashPlan;
    }

    /**
     * 现金流计算规则表 接收
     * 
     * @param dto
     * @return FinCashCalcRule
     */
    private FinCashCalcRule getFinCashCalcRule(ValFxTargetRateAdjust dto) {
        FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = InterfHelper.getMd5CashId_(D.资产代码,D.业务类型,数据字典：现金流二级类型.利息）
        finCashCalcRule.setCashId(InterfHelper.getMd5CashId_(dto.getAssetCode(),dto.getFinprodType(),
            String.valueOf(dto.getBranch()), DictCodeListExt1.现金流二级类型_罚息.getValue()));
        // 生效日期 = D.生效日
        finCashCalcRule.setEffDate(dto.getEffDate());
        // 计算类型 = D.利率类型
        finCashCalcRule.setCalcType(dto.getIntType());
        // 生效利率 = D.利率
        finCashCalcRule.setYield(dto.getRate());
        // 计算基础 = D.计息基础
        finCashCalcRule.setBasis(dto.getBasis());
        // 金融产品代码 = D.资产代码
        finCashCalcRule.setFinprodId(dto.getAssetCode());
        // 金融产品类型 = D.业务类型
        finCashCalcRule.setFinprodType(dto.getFinprodType());
        // 分支序号 = D.分支序号
        finCashCalcRule.setBranch(dto.getBranch());
        return finCashCalcRule;
    }
}
