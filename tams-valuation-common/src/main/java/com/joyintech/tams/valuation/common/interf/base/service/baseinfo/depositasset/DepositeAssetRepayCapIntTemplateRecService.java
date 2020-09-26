/*
 * 文件名：DepositeAssetRepayCapIntTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset;

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
import com.joyintech.tams.valuation.common.interf.base.dto.DepositeAssetBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstRepayCapInt;

/**
 * 存款类资产-还本付息规则模板处理Service
 * @author wangqidong
 * @version 1.0 2018年10月23日
 * @see DepositeAssetRepayCapIntTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetRepayCapIntTemplateRecService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 存款类资产-还本付息规则 接口模板处理
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return 存款类资产还本付息规则   接口模板处理返回共通结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public DepositeAssetBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                        TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        DepositeAssetBaseInfoValCommonResult result = new DepositeAssetBaseInfoValCommonResult();
        List<ValBotAstRepayCapInt> listValBotAstRepayCapInt = new ArrayList<ValBotAstRepayCapInt>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValBotAstRepayCapInt = returnResult.getT(ValBotAstRepayCapInt.class);
        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValBotAstRepayCapInt(listValBotAstRepayCapInt);
        // 现金流信息List
        List<FinCashInfo> listValCashInfo = (List<FinCashInfo>)map.get("ValCashInfo");
        result.setListValCashInfo(listValCashInfo);
        // 现金流支付规则List
        List<FinCashPayRule> listValCashPayRule = (List<FinCashPayRule>)map.get("ValCashPayRule");
        result.setListValCashPayRule(listValCashPayRule);   
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValBotAstRepayCapInt listValBotAstRepayCapInt
     * @return Map<String, Object>
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValBotAstRepayCapInt(List<ValBotAstRepayCapInt> listValBotAstRepayCapInt) {
        Map<String, Object> result = new HashMap<>();
        // 定义 现金流信息List
        List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();
        // 定义 现金流支付规则List
        List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
        
        for (ValBotAstRepayCapInt dto : listValBotAstRepayCapInt) {
            // 现金流信息
            FinCashInfo valCashInfo = getValCashInfo(dto);
            listValCashInfo.add(valCashInfo);              
            // 现金流支付规则
            FinCashPayRule valCashPayRule = getValCashPayRule(dto, valCashInfo);
            listValCashPayRule.add(valCashPayRule);
        }
        result.put("ValCashInfo", listValCashInfo);
        result.put("ValCashPayRule", listValCashPayRule);
        return result;
    }

    /**
     * T3 现金流信息表
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValBotAstRepayCapInt dto) {
        // 定义现金流信息表
        FinCashInfo valFinCashInfo = new FinCashInfo();
        // 现金流代码 = C.交易类型为还本-->InterfHelper.getMd5CashId_（C.资产代码,数据字典：金融产品类型.存款,数据字典：现金流二级类型.本金)
                  // C.交易类型为收息-->InterfHelper.getMd5CashId_（C.资产代码,数据字典：金融产品类型.存款,数据字典：现金流二级类型.利息)
        // 现金流一级类型
                // C.交易类型为还本--> 数据字典：现金流一级类型.本金
                // C.交易类型为收息--> 数据字典：现金流一级类型.利息
        // 现金流二级类型
                // C.交易类型为还本--> 数据字典：现金流二级类型.本金
                // C.交易类型为收息--> 数据字典：现金流二级类型.利息
        if(DictCodeList.交易方向_还本.getValue().equals(dto.getDealType())){
            valFinCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAssetId(),
                    DictCodeListExt1.金融产品类型_存款.getValue(),DictCodeListExt1.现金流二级类型_本金.getValue()));
            valFinCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
            valFinCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
        }else if(DictCodeList.交易方向_收息.getValue().equals(dto.getDealType())){
            valFinCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAssetId(),
                DictCodeListExt1.金融产品类型_存款.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue()));
            valFinCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
            valFinCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        }
        // 金融产品代码 = C.资产代码
        valFinCashInfo.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 数据字典：数据字典：金融产品类型.存款
        valFinCashInfo.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        // 分支序号 = 固定值  设置全局变量(默认为0）
        valFinCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
        
        // 返回结果
        return valFinCashInfo;
    }
    
    /**
     * T5 现金流支付规则表
     * @param dto
     * @param valCashInfo
     * @return
     * @see
     * @since 1.0
     */
    private FinCashPayRule getValCashPayRule(ValBotAstRepayCapInt dto, FinCashInfo T3) {
        // 定义现金流支付规则表
        FinCashPayRule valFinCashPayRule = new FinCashPayRule();
        // 现金流代码 = T3.现金流代码
        valFinCashPayRule.setCashId(T3.getCashId());
        // 生效日期  = C.生效日
        valFinCashPayRule.setEffDate(dto.getEffDate());
        // 支付频率 =  C.交易类型为还本--> C.还本频率
                 // C.交易类型为收息--> C.付息频率
        if(DictCodeList.交易方向_还本.getValue().equals(dto.getDealType())){
            valFinCashPayRule.setPayCycle(dto.getCapCycle());
        }else if(DictCodeList.交易方向_收息.getValue().equals(dto.getDealType())){
            valFinCashPayRule.setPayCycle(dto.getPayCycle());
        }
        // 营业日准则 = C.营业日准则
//        valFinCashPayRule.setTdayRule(dto.getTdayRule());
        // 推算方式 = C.推算方式
        valFinCashPayRule.setCalcRule(dto.getCalcRule());
        // 首次支付日 = C.首次付息日
        valFinCashPayRule.setFirstPayDate(dto.getFirstPayDate());
        // 现金流单位 = 固定值 02：总额
        valFinCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = C.资产代码
        valFinCashPayRule.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 数据字典：金融产品类型.存款
        valFinCashPayRule.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        // 支付方式 = 固定值  02:现金
        valFinCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
        // 分支序号 = 固定值  设置全局变量(默认为0） 
        valFinCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
    
        return valFinCashPayRule;
    }

    
    
}
