/*
 * 文件名：CPrdBaseinfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongjun
 * 修改时间：2019年2月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinClawbackCalcRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.CPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.PrdClawbackCalcRule;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdCawlbackCalcRuleC;

/**
 * 货币型产品业绩回补规则 (VAL_PRD_CAWLBACK_CALC_RULE_C)
 * 
 * @author dongjun
 * @version 1.0 2019年2月19日
 * @see CPrdClawbackCalcRuleTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPrdClawbackCalcRuleTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @Autowired
    private InterfCommonService interfCommonService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        CPrdBaseInfoValCommonResult result = new CPrdBaseInfoValCommonResult();
        List<ValPrdCawlbackCalcRuleC> listValPrdClawbackCalcRuleC = new ArrayList<ValPrdCawlbackCalcRuleC>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdClawbackCalcRuleC = returnResult.getT(ValPrdCawlbackCalcRuleC.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> resultMap = handleValPrdCawlbackCalcRuleC(listValPrdClawbackCalcRuleC);

        // 判断返回结果不为空
        if (resultMap != null) {
            // 3.1、业绩回补规则
            List<FinClawbackCalcRule> listFinClawbackCalcRule = (List<FinClawbackCalcRule>)resultMap.get(
                "listFinClawbackCalcRule");
            if (!StringUtil.isEmptyList(listFinClawbackCalcRule)) {
                result.setListFinCawlbackCalcRule(listFinClawbackCalcRule);
            }
            // 3.2、现金流信息
            List<FinCashInfo> listFinCashInfo = (List<FinCashInfo>)resultMap.get(
                "listFinCashInfo");
            if (!StringUtil.isEmptyList(listFinCashInfo)) {
                result.setListValCashInfo(listFinCashInfo);
            }

        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;

    }

    /**
     * @param listValPrdCawlbackCalcRuleC
     * @return
     */
    private Map<String, Object> handleValPrdCawlbackCalcRuleC(List<ValPrdCawlbackCalcRuleC> listValPrdCawlbackCalcRuleC) {
        Map<String, Object> resultMap = new HashMap<>();
        // 业绩回补规则List
        List<FinClawbackCalcRule> listFinClawbackCalcRule = new ArrayList<FinClawbackCalcRule>();

        // 现金流信息List
        List<FinCashInfo> listFinCashInfo = new ArrayList<>();

        // 业绩回补规则接口List
        List<PrdClawbackCalcRule> listPrdCawlbackCalcRule = new ArrayList<>();

        for (ValPrdCawlbackCalcRuleC dto : listValPrdCawlbackCalcRuleC) {
            FinClawbackCalcRule finClawbackCalcRule = getValPrdCawlbackCalcRuleC(dto);
            listFinClawbackCalcRule.add(finClawbackCalcRule);

            PrdClawbackCalcRule prdCawlbackCalcRule = new PrdClawbackCalcRule();
            prdCawlbackCalcRule.setProdId(dto.getProdId());
            prdCawlbackCalcRule.setFinprodType(dto.getFinprodType());
            prdCawlbackCalcRule.setEffDate(dto.getEffDate());
            prdCawlbackCalcRule.setDataActionSign(dto.getDataActionSign());
            prdCawlbackCalcRule.setProdVdate(dto.getProdVdate());
            prdCawlbackCalcRule.setCawlbackRule(dto.getCawlbackRule());
            prdCawlbackCalcRule.setCawlbackType(dto.getCawlbackType());
            prdCawlbackCalcRule.setChargeBase(dto.getChargeBase());
            listPrdCawlbackCalcRule.add(prdCawlbackCalcRule);
        }

        listFinCashInfo = interfCommonService.initPrdClawbackCashInfo(listPrdCawlbackCalcRule);

        resultMap.put("listFinClawbackCalcRule", listFinClawbackCalcRule);
        resultMap.put("listFinCashInfo", listFinCashInfo);
        return resultMap;
    }

    /**
     * 业绩回补规则接收
     * 
     * @param dto
     * @return
     */
    private FinClawbackCalcRule getValPrdCawlbackCalcRuleC(ValPrdCawlbackCalcRuleC dto) {

        // 业绩基准信息
        FinClawbackCalcRule valFinClawbackCalcRule = new FinClawbackCalcRule();
        String cashId = null;
        if (StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())) {
            cashId = InterfHelper.getMd5CashId_(
                dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                DictCodeListExt1.现金流二级类型_回补.getValue());
        }
        else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId(),
                DictCodeListExt1.现金流二级类型_回补.getValue());
        }
        valFinClawbackCalcRule.setCashId(cashId);
        valFinClawbackCalcRule.setEffDate(dto.getEffDate());
        valFinClawbackCalcRule.setCawlbackRule(dto.getCawlbackRule());
        valFinClawbackCalcRule.setCawlbackType(dto.getCawlbackType());
        valFinClawbackCalcRule.setBasis(dto.getChargeBase());
        valFinClawbackCalcRule.setActRule(dto.getActRule());
        return valFinClawbackCalcRule;
    }

}
