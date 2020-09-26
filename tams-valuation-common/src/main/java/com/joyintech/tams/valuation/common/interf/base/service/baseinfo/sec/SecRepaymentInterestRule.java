/*
 * 文件名：SecRepaymentInterestRule.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.SecBaseInfoRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecPayRateRule;

/**
 * 债券还本付息规则
 * 
 * @author HuangJian
 * @version 1.0 2018年6月28日
 * @see SecRepaymentInterestRule
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecRepaymentInterestRule {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SecBaseInfoRecProcessorRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                          TemplateOrderInfoReaderResult acctEntity) {
        SecBaseInfoRecProcessorRecCommonResult result = new SecBaseInfoRecProcessorRecCommonResult();
        List<ValSecPayRateRule> listValSecPayRateRule = new ArrayList<ValSecPayRateRule>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSecPayRateRule = returnResult.getT(ValSecPayRateRule.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValSecPayRateRule(listValSecPayRateRule);
        if (!map.isEmpty()) {
            result.setListValCashInfo((List<FinCashInfo>)map.get("listValCashInfo"));
            result.setListValCashPayRule((List<FinCashPayRule>)map.get("listValCashPayRule"));
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValSecPayRateRule List<ValSecPayRateRule>
     * @return Map<String, Object>
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValSecPayRateRule(List<ValSecPayRateRule> listValSecPayRateRule) {
        Map<String, Object> result = new HashMap<>();
        List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();
        List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
        for (ValSecPayRateRule item : listValSecPayRateRule) {
            FinCashInfo valCashInfo = this.getValCashInfo(item);
            listValCashInfo.add(valCashInfo);
            FinCashPayRule valCashPayRule = this.getValCashPayRule(item,valCashInfo);
            listValCashPayRule.add(valCashPayRule);
        }
        result.put("listValCashInfo", listValCashInfo);
        result.put("listValCashPayRule", listValCashPayRule);
        return result;
    }

    /**
     * 现金流信息表 T3
     * 
     * @param dto ValSecPayRateRule
     * @return ValCashInfo
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValSecPayRateRule dto) {
        FinCashInfo result = new FinCashInfo();
        // 现金流代码 = C.资产代码+C.交易类型
        String cashId = "";
        // C.交易类型为还本--> 01 本金
        if (DictCodeList.交易方向_还本.getValue().equals(dto.getTradeType())) {
            result.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
            result.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
            
            // 现金流代码 = C.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.本金
            cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
            		,DictCodeListExt1.现金流二级类型_本金.getValue());
        }
        // C.交易类型为收息--> 02 利息
        if (DictCodeList.交易方向_收息.getValue().equals(dto.getTradeType())) {
            result.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
            result.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
            
            // 现金流代码 = C.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.利息
            cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
            		,DictCodeListExt1.现金流二级类型_利息.getValue());
        }
        result.setCashId(cashId);
        // 金融产品代码 = C.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F01 债券
        result.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        result.setBranch(InterfCommonConstant.分支序号_默认);
        return result;
    }

    /**
     * 现金流支付规则 T5
     * 
     * @param dto ValSecPayRateRule
     * @return ValCashPayRule
     * @see
     * @since 1.0
     */
    private FinCashPayRule getValCashPayRule(ValSecPayRateRule dto,FinCashInfo finCashInfo) {
        FinCashPayRule result = new FinCashPayRule();
        // 现金流代码 = C.资产代码+C.交易类型
        result.setCashId(finCashInfo.getCashId());
        // 生效日期 = C.生效日
        result.setEffDate(dto.getEffDate());
        // 支付频率 = C.付息频率
        result.setPayCycle(dto.getPayCycle());
        // 营业日准则 = C.营业日准则
//        result.setTdayRule(dto.getTdayRule());
        // 推算方式 = C.推算方式
        result.setCalcRule(dto.getCalcRule());
        // 首次支付日 = C.首次付息日
        result.setFirstPayDate(dto.getFirstPayDate());
        // 现金流单位 = 02：总额
        result.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = C.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F01 债券
        result.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 支付方式 = 02:现金
        result.setPayType(DictCodeList.支付方式_现金.getValue());
        // 分支序号 = 设置全局变量(默认为0） 
        result.setBranch(InterfCommonConstant.分支序号_默认);
        return result;
    }
}
