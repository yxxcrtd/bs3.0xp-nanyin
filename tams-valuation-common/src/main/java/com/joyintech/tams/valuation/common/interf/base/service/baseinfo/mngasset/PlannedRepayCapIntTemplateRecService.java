/*
 * 文件名：PlannedRepayCapIntTemplateRecService.java
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

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.PlannedAssetBaseInfoRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngRepayCapInt;

/**
 * 计划类资产（利率型）还本付息规则模板处理
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年6月25日
 * @see PlannedRepayCapIntTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlannedRepayCapIntTemplateRecService {
    
    /**
     *基本信息模板处理service
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
        List<ValMngRepayCapInt> listValMngRepayCapInt = new ArrayList<ValMngRepayCapInt>(); 
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
                
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMngRepayCapInt = returnResult.getT(ValMngRepayCapInt.class);
        
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> resultMap = handleListValMngRepayCapInt(listValMngRepayCapInt);
        // 判断返回结果不为空
        if (resultMap != null) {
            List<FinCashInfo> listValCashInfo = (List<FinCashInfo>)resultMap.get("ValCashInfo");
            List<FinCashPayRule> listValCashPayRule = (List<FinCashPayRule>)resultMap.get("ValCashPayRule");
            result.setListValCashInfo(listValCashInfo);
            result.setListValCashPayRule(listValCashPayRule);
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 03-还本付息规则
     * 
     * @param listValMngRepayCapInt 计划类资产（利率型）还本付息规则信息
     * @return 现金流信息+现金流支付 规则
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValMngRepayCapInt(List<ValMngRepayCapInt> listValMngRepayCapInt) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinCashInfo> valCashInfoList = new ArrayList<FinCashInfo>();
        List<FinCashPayRule> valCashPayRuleList = new ArrayList<FinCashPayRule>();
        // 获取还本付息信息
        for (ValMngRepayCapInt dto : listValMngRepayCapInt) {
            // 现金流信息 处理
            FinCashInfo valCashInfo = getValCashInfo(dto);
            valCashInfoList.add(valCashInfo);
            // 现金流支付规则信息  处理
            FinCashPayRule valCashPayRule = getValCashPayRule(dto);
            valCashPayRuleList.add(valCashPayRule);
        }
        resultMap.put("ValCashInfo", valCashInfoList);
        resultMap.put("ValCashPayRule", valCashPayRuleList);
        return resultMap;
    }
    
    /**
     * 现金流信息表 接收
     * 
     * @param dto 01-基础信息
     * @return 现金流信息
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValMngRepayCapInt dto) {
        FinCashInfo valCashInfo = new FinCashInfo();
        //交易类型判断
        if(StringUtil.isNotEmpty(dto.getDealType())){
            //还本
            if(DictCodeList.交易方向_还本.getValue().equals(dto.getDealType())){
                // 现金流一级类型= 01:本金
                valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
                // 现金流二级类型= C01:本金
                valCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
                
                // 现金流代码 = C.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.本金
                String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue()
                		, DictCodeListExt1.现金流二级类型_本金.getValue());
                valCashInfo.setCashId(cashId);
            }
            //收息
            else if(DictCodeList.交易方向_收息.getValue().equals(dto.getDealType())){
                // 现金流一级类型= 02:利息
                valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
                // 现金流二级类型= C02：利息
                valCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
                
                // 现金流代码 = C.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.利息
                String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue()
                		,DictCodeListExt1.现金流二级类型_利息.getValue());
                valCashInfo.setCashId(cashId);
            }
        }
        // 金融产品代码= C.资产名称
        valCashInfo.setFinprodId(dto.getAssetId());
        // 金融产品类型= F17:债权类资产
        valCashInfo.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号= 设置全局变量(默认为0）
        valCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
        return valCashInfo;
    }
    
    /**
     * 现金流支付规则表 接收
     * 
     * @param dto 01-基础信息
     * @return 现金流支付规则
     * @see
     * @since 1.0
     */
    private FinCashPayRule getValCashPayRule(ValMngRepayCapInt dto) {
        FinCashPayRule valCashPayRule = new FinCashPayRule();
        //交易类型判断
        if(StringUtil.isNotEmpty(dto.getDealType())){
            //还本
            if(DictCodeList.交易方向_还本.getValue().equals(dto.getDealType())){
                // 支付频率= C.还本频率
                valCashPayRule.setPayCycle(dto.getCapCycle());
                
                // 现金流代码 = C.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.本金
                String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue()
                		,DictCodeListExt1.现金流二级类型_本金.getValue());
                valCashPayRule.setCashId(cashId);
                
            }
            //收息
            else if(DictCodeList.交易方向_收息.getValue().equals(dto.getDealType())){
                // 支付频率= C.付息频率
                valCashPayRule.setPayCycle(dto.getPayCycle());
                // 现金流代码 = C.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.利息
                String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue()
                		,DictCodeListExt1.现金流二级类型_利息.getValue());
                valCashPayRule.setCashId(cashId);
            }
        }
        //TODO 生效日期 = C.生效日期
        /**
         * modify by wujie on 20180925 start remark:生效日期设值问题
         valCashPayRule.setEffDate(dto.getCreateTime());
         **/
        valCashPayRule.setEffDate(dto.getEffDate());
        /**modify by wujie on 20180925 end**/
        // 营业日准则= C.营业日准则
//        valCashPayRule.setTdayRule(dto.getTdayRule());
        
        /**add by wujie on 20210223 start**/
        //支付日营业准则 = C.营业日准则
        valCashPayRule.setPayTdayRule(dto.getTdayRule());
        
        /**add by wujie on 20210223 end**/
        
        // 推算方式= C.推算方式
        valCashPayRule.setCalcRule(dto.getCalcRule());
        // 首次支付日= C.首次支付日
        valCashPayRule.setFirstPayDate(dto.getFirstPayDate());
        // 现金流单位= 02：总额
        valCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码= C.资产代码
        valCashPayRule.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 固定值(F17：债权类资产)
        valCashPayRule.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 支付方式 = 02:现金
        valCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
        // 分支序号= 设置全局变量(默认为0）
        valCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
        return valCashPayRule;
    }
}
