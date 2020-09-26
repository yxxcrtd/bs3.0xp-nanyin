/*
 * 文件名：DepositeAssetBaseInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset;

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
import com.joyintech.tams.valuation.common.interf.base.dto.DepositeAssetBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstRateAdjust;

/**
 * 存款类资产-利率调整 模板处理Service(VAL_BOT_AST_RATE_ADJUST)
 * 
 * @author weipeng
 * @version 1.0 2018年6月27日
 * @see DepositeAssetRateAdjustTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetRateAdjustTemplateRecService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    /**
     * 存款类资产-利率调整 接口模板处理
     * 
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return 净值型产品交易 接口模板处理返回共通结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public DepositeAssetBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                        TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        DepositeAssetBaseInfoValCommonResult result = new DepositeAssetBaseInfoValCommonResult();
        List<ValBotAstRateAdjust> listValBotAstRateAdjust = new ArrayList<ValBotAstRateAdjust>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValBotAstRateAdjust = returnResult.getT(ValBotAstRateAdjust.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = handleValBotAstRateAdjust(
            listValBotAstRateAdjust);
        result.setListValCashCalcRule(listValCashCalcRule);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    private List<FinCashCalcRule> handleValBotAstRateAdjust(List<ValBotAstRateAdjust> listValBotAstRateAdjust) {
        // 定义 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();

        for (ValBotAstRateAdjust dto : listValBotAstRateAdjust) {
            // 现金流计算规则
            FinCashCalcRule valCashCalcRule = getValCashCalcRule(dto);
            listValCashCalcRule.add(valCashCalcRule);
        }
        return listValCashCalcRule;
    }

    /**
     * T4 现金流计算规则
     * 
     * @param dto 02-利率调整
     * @return 现金流计算规则
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValBotAstRateAdjust dto) {
        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();

        // 现金流代码 = B.资产代码+数据字典：金融产品类型.存款+数据字典：现金流二级类型.利息
        String cashId = InterfHelper.getMd5CashId_(dto.getProdId(),DictCodeListExt1.金融产品类型_存款.getValue(),
        		DictCodeListExt1.现金流二级类型_利息.getValue());
        valCashCalcRule.setCashId(cashId);
        
        //共通查询首条现金流计算规则信息
//        FinCashCalcRule cashCalcRule = interfCommonService.queryCashCalcRule(cashId,DictCodeListExt1.金融产品类型_存款.getValue(),dto.getProdId());
        
        // 生效日期 = B.生效日
        valCashCalcRule.setEffDate(dto.getEffDate());
        // 计算类型 = 根据资产代码、现金流代码查询计算类型信息
        valCashCalcRule.setCalcType(dto.getIntType());
        //浮动利率编号
//        valCashCalcRule.setIntRateId(null);
        // 生效利率 = B.利率
        valCashCalcRule.setYield(dto.getRate());
        // 计算基础 = 根据资产代码、现金流代码查询计算基础信息
        valCashCalcRule.setBasis(dto.getBasis());
        // 金融产品代码 = B.资产代码
        valCashCalcRule.setFinprodId(dto.getProdId());
        // 金融产品类型 = F07 存款
        valCashCalcRule.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        valCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
        // 基数类型 = 数据字典：基数类型.实收资本
        valCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
        // 基数日期类型 = 数据字典：基数日期类型.当日
        valCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        return valCashCalcRule;
    }

}
