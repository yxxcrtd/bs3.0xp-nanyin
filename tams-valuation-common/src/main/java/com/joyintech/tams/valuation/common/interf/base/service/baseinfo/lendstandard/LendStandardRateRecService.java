/*
 * 文件名：LendStandardRateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年11月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.lendstandard;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.LendStandardCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlTargetRateAdjust;

/**
 * 拆借利率调整
 * 
 * @author dongjun
 * @version 1.0 2018年11月27日
 * @see LendStandardRateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendStandardRateRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public LendStandardCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                            TemplateOrderInfoReaderResult acctEntity) {
        LendStandardCommonResult result = new LendStandardCommonResult();
        List<ValDlTargetRateAdjust> listValDlTargetRateAdjust = new ArrayList<ValDlTargetRateAdjust>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValDlTargetRateAdjust = returnResult.getT(ValDlTargetRateAdjust.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 现金流计算规则表
        List<FinCashCalcRule> listValCashCalcRule = handleValDlTargetRateAdjust(listValDlTargetRateAdjust);
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
    private List<FinCashCalcRule> handleValDlTargetRateAdjust(List<ValDlTargetRateAdjust> listValDlTargetRateAdjust) {
        // 现金流计划 list
        List<FinCashCalcRule> listValCashPlan = new ArrayList<FinCashCalcRule>();
        for (ValDlTargetRateAdjust dto : listValDlTargetRateAdjust) {
            // 现金流计划
            FinCashCalcRule finCashCalcRule = this.getValDlTargetRateAdjust(dto);
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
    private FinCashCalcRule getValDlTargetRateAdjust(ValDlTargetRateAdjust dto) {
        FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = InterfHelper.getMd5CashId_(D.资产代码,D.业务类型,数据字典：现金流二级类型.利息）
        finCashCalcRule.setCashId(InterfHelper.getMd5CashId_(dto.getProdId(), DictCodeListExt1.现金流二级类型_利息.getValue()));
        // 生效日期 = D.生效日
        finCashCalcRule.setEffDate(dto.getEffDate());
        // 计算类型 = D.利率类型
        finCashCalcRule.setCalcType(dto.getIntType());
        // 生效利率 = D.利率
        finCashCalcRule.setYield(dto.getRate());
        // 计算基础 = D.计息基础
        finCashCalcRule.setBasis(dto.getBasis());
        // 基数类型 = 数据字典：计费基数类型.实收资本
        finCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
        //基数日期类型  = 数据字典：计费基数日期类型.当日
        finCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        // 金融产品代码 = D.资产代码
        finCashCalcRule.setFinprodId(dto.getProdId());
        // 金融产品类型 = D.业务类型
        finCashCalcRule.setFinprodType(dto.getBusiType());
        // 分支序号 = 设置全局变量(默认为0）
        finCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
        return finCashCalcRule;
    }
}
