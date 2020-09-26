/*
 * 文件名：PlannedRateAdjInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.tams.valuation.common.interf.base.dto.PlannedAssetBaseInfoRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngRateAdjInfo;

/**
 * 计划类资产（利率型）利率调整信息模板处理
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年6月25日
 * @see PlannedRateAdjInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlannedRateAdjInfoTemplateRecService {
    
    /**
     * 基本信息模板处理service
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public PlannedAssetBaseInfoRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                           TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
        PlannedAssetBaseInfoRecCommonResult result = new PlannedAssetBaseInfoRecCommonResult();
        List<ValMngRateAdjInfo> listValMngRateAdjInfo = new ArrayList<ValMngRateAdjInfo>(); 
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
            
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMngRateAdjInfo = returnResult.getT(ValMngRateAdjInfo.class);
    
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> resultMap = handleListValMngRateAdjInfo(listValMngRateAdjInfo) ;
        // 判断返回结果不为空
        if (resultMap != null) {
            List<FinCashCalcRule> listValCashCalcRule = (List<FinCashCalcRule>)resultMap.get("ValCashCalcRule");
            result.setListValCashCalcRule(listValCashCalcRule);
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 02-利率调整信息
     * 
     * @param listValMngRateAdjInfo 计划类资产（利率型）利率调整信息
     * @return 现金流计算 规则
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValMngRateAdjInfo(List<ValMngRateAdjInfo> listValMngRateAdjInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinCashCalcRule> valCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        // 获取利率调整信息
        for (ValMngRateAdjInfo dto : listValMngRateAdjInfo) {
            // 现金流计算规则信息  处理
            FinCashCalcRule valCashCalcRule = getValCashCalcRule(dto);
            valCashCalcRuleList.add(valCashCalcRule);
        }
        resultMap.put("ValCashCalcRule", valCashCalcRuleList);
        return resultMap;
    }
    
    /**
     * 现金流计算规则表 接收
     * 
     * @param dto 01-利率调整信息
     * @return 现金流计算规则
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValMngRateAdjInfo dto) {
        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = B.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.利息
        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue()
        		, DictCodeListExt1.现金流二级类型_利息.getValue());
        valCashCalcRule.setCashId(cashId);
        // 生效日期 = B.生效日
        valCashCalcRule.setEffDate(dto.getEffDate());
        valCashCalcRule.setCalcType(dto.getIntType());
        valCashCalcRule.setBasis(dto.getBasis());
        // 生效利率= B.利率
        valCashCalcRule.setYield(dto.getYield());
        // 金融产品代码= B.资产代码
        valCashCalcRule.setFinprodId(dto.getAssetId());
        // 金融产品类型= F17:债权类资产
        valCashCalcRule.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        valCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
        return valCashCalcRule;
    }
}
