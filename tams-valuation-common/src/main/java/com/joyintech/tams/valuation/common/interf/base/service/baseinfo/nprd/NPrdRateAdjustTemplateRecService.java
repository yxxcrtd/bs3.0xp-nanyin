/*
 * 文件名：NPrdBaseinfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdRateAdjustN;

/**
 * 净值型产品费率调整信息表(Val_PRD_RATE_ADJUST) 〈功能详细描述〉
 * 
 * @author 吴德宝
 * @version 1.0 2018年6月22日
 * @see NPrdRateAdjustTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdRateAdjustTemplateRecService {

    /**
     * 净值型产品基本信息模板处理service <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public NPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        NPrdBaseInfoValCommonResult result = new NPrdBaseInfoValCommonResult();
        List<ValPrdRateAdjustN> listValPrdRateAdjustN = new ArrayList<ValPrdRateAdjustN>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdRateAdjustN = returnResult.getT(ValPrdRateAdjustN.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 现金流计算规则表List
        List<FinCashCalcRule> listValCashCalcRule = handleValPrdRateAdjustN(listValPrdRateAdjustN);
        result.setListValCashCalcRule(listValCashCalcRule);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果
        return result;
    }

    /**
     * 09-费率调整信息
     * 
     * @param listValPrdRateAdjustN 净值型产品费率调整信息List
     * @see
     * @since 1.0
     */
    private List<FinCashCalcRule> handleValPrdRateAdjustN(List<ValPrdRateAdjustN> listValPrdRateAdjustN) {
        // 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
        for (ValPrdRateAdjustN dto : listValPrdRateAdjustN) {
            // 现金流计算规则
            FinCashCalcRule valCashCalcRule = this.getValCashCalcRule(dto);
            listValCashCalcRule.add(valCashCalcRule);
        }
        return listValCashCalcRule;
    }

    /**
     * 现金流计算规则表 接收
     * 
     * @param dto 09-费率调整信息
     * @return 现金流计算规则
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValPrdRateAdjustN dto) {
        // 现金流计算规则
        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = I.产品代码+I.费用ID
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }   
        valCashCalcRule.setCashId(cashId);
        // 生效日期 = I.生效日
        valCashCalcRule.setEffDate(dto.getEffDate());
        // 计算类型 = 01：固定利率
        valCashCalcRule.setCalcType(DictCodeListExt1.计算类型_固定利率.getValue());
        // 生效利率 = I.费率
        valCashCalcRule.setYield(dto.getFeeRate());
        // 计算基础 = I.计费基础
        valCashCalcRule.setBasis(dto.getChargeBase());
        // 基数类型 = I.计费基数
        valCashCalcRule.setBaseType(dto.getChargeBasis());
        // 基数日期类型 = I.计费基数日期类型
        valCashCalcRule.setBaseDateType(dto.getBaseDateType());
        // 金融产品代码 = I.产品代码
        valCashCalcRule.setFinprodId(dto.getProdId());
        // 金融产品类型 = F16：理财产品
        valCashCalcRule.setFinprodType(dto.getFinprodType());
        // 分支序号 = 设置全局变量（默认为0）
        Long branch = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
        } else {
            branch = InterfCommonConstant.分支序号_默认;
        }
        valCashCalcRule.setBranch(branch);
        return valCashCalcRule;
    }

}
