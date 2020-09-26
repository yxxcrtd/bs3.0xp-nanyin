/*
A * 文件名：SecRatedjustment.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec;

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
import com.joyintech.tams.valuation.common.interf.base.dto.SecBaseInfoRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecRateAdjust;

/**
 * 债券利率调整信息
 * 
 * @author HuangJian
 * @version 1.0 2018年6月28日
 * @see SecRatedjustment
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecRateAdjustmentInfoRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SecBaseInfoRecProcessorRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                          TemplateOrderInfoReaderResult acctEntity) {
        SecBaseInfoRecProcessorRecCommonResult result = new SecBaseInfoRecProcessorRecCommonResult();
        List<ValSecRateAdjust> listValSecRateAdjust = new ArrayList<ValSecRateAdjust>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSecRateAdjust = returnResult.getT(ValSecRateAdjust.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        result.setListValCashCalcRule(this.getValCashCalcRule(listValSecRateAdjust));
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValSecRateAdjust List<ValSecRateAdjust>
     * @return List<ValCashCalcRule>
     * @see
     * @since 1.0
     */
    private List<FinCashCalcRule> getValCashCalcRule(List<ValSecRateAdjust> listValSecRateAdjust) {
        List<FinCashCalcRule> result = new ArrayList<FinCashCalcRule>();
        for (ValSecRateAdjust item : listValSecRateAdjust) {
            FinCashCalcRule valCashCalcRule = new FinCashCalcRule();
            // 现金流代码 = B.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.利息
            String cashId = InterfHelper.getMd5CashId_(item.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
            		, DictCodeListExt1.现金流二级类型_利息.getValue());
            valCashCalcRule.setCashId(cashId);
            // 生效日期 = B.生效日
            valCashCalcRule.setEffDate(item.getEffectDate());
            // 根据资产代码、现金流代码查询计算类型信息
            valCashCalcRule.setCalcType(item.getIntType());
            // 生效利率 = B.利率
            valCashCalcRule.setYield(item.getRate());
            // 根据资产代码、现金流代码查询计算基础信息
            valCashCalcRule.setBasis(item.getBasis());
            // 金融产品代码 = B.资产代码
            valCashCalcRule.setFinprodId(item.getAssetId());
            // 金融产品类型 =F01 债券
            valCashCalcRule.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
            // 分支序号 = 设置全局变量(默认为0）
            valCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
            /**add by jinmaodong 20180916 remark:设置默认值**/
            valCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
            valCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
            /**add by jinmaodong 20180916 end**/
            // 备注
            valCashCalcRule.setRemark("");
            result.add(valCashCalcRule);
        }
        return result;
    }
}
