/*
 * 文件名：CPrdFloatFeeTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.valuation.common.interf.base.dto.CPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFloatCalcRuleC;
/**
 * 货币型产品浮动管理费计提规则表(Val_PRD_FLOAT_FEE_C) 〈功能详细描述〉
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年8月13日
 * @see CPrdFloatCalcRuleTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPrdFloatCalcRuleTemplateRecService {

    /**
     * 货币型产品基本信息模板处理service <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
    	CPrdBaseInfoValCommonResult result = new CPrdBaseInfoValCommonResult();
        List<ValFloatCalcRuleC> listValPrdFloatFeeC = new ArrayList<ValFloatCalcRuleC>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdFloatFeeC = returnResult.getT(ValFloatCalcRuleC.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValPrdFloatFeeC(listValPrdFloatFeeC);

        // 业绩报酬计算规则 List
        List<FinFloatCalcRule> listValFloatCalcRule = (List<FinFloatCalcRule>)map.get(
            "ValFloatCalcRule");
        result.setListValFloatCalcRule(listValFloatCalcRule);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }
    /**
     * 12-浮动管理费计算规则
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValPrdFloatFeeC
     * @return  业绩报酬计算规则 List
     */
	private Map<String, Object> handleValPrdFloatFeeC(List<ValFloatCalcRuleC> listValPrdFloatFeeC) {
		 Map<String, Object> resultMap = new HashMap<>();
	        // 业绩报酬计算规则 List
	        List<FinFloatCalcRule> listValFloatCalcRule = new ArrayList<FinFloatCalcRule>();
	        for (ValFloatCalcRuleC dto : listValPrdFloatFeeC) {
	            // 业绩报酬计算规则
	            FinFloatCalcRule valFloatCalcRule = this.getValFloatCalcRule(dto);
	            listValFloatCalcRule.add(valFloatCalcRule);
	        }
	        resultMap.put("ValFloatCalcRule", listValFloatCalcRule);
	        return resultMap;
	}
	/**
	 * 业绩报酬计算规则 接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinFloatCalcRule
	 */
	private FinFloatCalcRule getValFloatCalcRule(ValFloatCalcRuleC dto) {
		//业绩报酬计算规则
	    FinFloatCalcRule valFloatCalcRule = new FinFloatCalcRule();
	    String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }   
        valFloatCalcRule.setCashId(cashId);
        // 生效日期 = E.生效日
        valFloatCalcRule.setEffDate(dto.getEffDate());
        // 业绩基准规则 = E.业绩报酬计算规则
        valFloatCalcRule.setBaseRule(dto.getCalRule());
        // 资产净值日类型 = E.资产净值日
        valFloatCalcRule.setAstVdateType(dto.getAstVluDate());
        // 生效规则 = E.生效规则
        valFloatCalcRule.setActRule(dto.getActRule());

        /**add by wujie on 20210511 start**/

        // 计算基础 = E.计费基础
        valFloatCalcRule.setBasis(dto.getChargeBase());
        // 基数类型 = E.计费基数
        valFloatCalcRule.setBaseType(dto.getChargeBasis());
        // 基数日期类型 = E.计费基数日期类型
        valFloatCalcRule.setBaseDateType(dto.getBasisDate());

        /**add by wujie on 20210511 end**/

        return valFloatCalcRule;
    }	
}
