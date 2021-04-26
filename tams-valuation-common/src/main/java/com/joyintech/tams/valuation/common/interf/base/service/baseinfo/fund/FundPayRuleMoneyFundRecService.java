/*
 * 文件名：FundBaseInfoPayRuleRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.fund;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FundBaseInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundMnfPayRule;

/**
 * 货币基金付息规则 基金类资产定义货币基金付息规则 = "VAL_FUND_MNF_PAY_RULE";
 * 
 * @author HuangJian
 * @version 1.0 2018年6月26日
 * @see FundBaseInfoPayRuleRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FundPayRuleMoneyFundRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;


    /**
     * FundBaseInfoForInterfRecCommonResult
     * 
     * @param templateSourceFlgDTO TemplateSourceFlgDTO
     * @param acctEntity TemplateOrderInfoReaderResult
     * @return FundBaseInfoForInterfRecCommonResult
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FundBaseInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                        TemplateOrderInfoReaderResult acctEntity) {
        FundBaseInfoForInterfRecCommonResult result = new FundBaseInfoForInterfRecCommonResult();
        List<ValFundMnfPayRule> listValFundMnfPayRule = new ArrayList<ValFundMnfPayRule>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFundMnfPayRule = returnResult.getT(ValFundMnfPayRule.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValFundMnfPayRule(listValFundMnfPayRule);
        if (!map.isEmpty()) {
            result.setListValCashInfo((List<FinCashInfo>)map.get("ValCashInfo"));
            result.setListValCashPayRule((List<FinCashPayRule>)map.get("ValCashPayRule"));
        }

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValFundMnfPayRule List<ValFundMnfPayRule>
     * @return Map<String,Object>
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValFundMnfPayRule(List<ValFundMnfPayRule> listValFundMnfPayRule) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();
        List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
        for (ValFundMnfPayRule item : listValFundMnfPayRule) {
            FinCashInfo valCashInfo = this.getValCashInfo(item);
            listValCashInfo.add(valCashInfo);
            FinCashPayRule valCashPayRule = this.getValCashPayRule(item);
            listValCashPayRule.add(valCashPayRule);
        }
        resultMap.put("ValCashInfo", listValCashInfo);
        resultMap.put("ValCashPayRule", listValCashPayRule);
        return resultMap;
    }

    /**
     * 接收 ValCashInfo 现金流信息 T4
     * 
     * @param dto ValFundMnfPayRule
     * @return ValCashInfo
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValFundMnfPayRule dto) {
        FinCashInfo result = new FinCashInfo();
        // 现金流代码 = B.资产代码+数据字典：金融产品类型.基金+数据字典：现金流二级类型.利息
        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_基金.getValue()
        		,DictCodeListExt1.现金流二级类型_利息.getValue());
        result.setCashId(cashId);
        // 现金流一级类型 = 02:利息
        result.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        // 现金流二级类型 = C02：利息
        result.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        // 金融产品代码 = B.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F06：基金
        result.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        result.setBranch(InterfCommonConstant.分支序号_默认);
        return result;
    }

    /**
     * 接收 现金支付规则 T5
     * 
     * @param dto ValFundMnfPayRule
     * @return ValCashPayRule
     * @see
     * @since 1.0
     */
    private FinCashPayRule getValCashPayRule(ValFundMnfPayRule dto) {
        FinCashPayRule result = new FinCashPayRule();
     // 现金流代码 = B.资产代码+数据字典：金融产品类型.基金+数据字典：现金流二级类型.利息
        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_基金.getValue()
        		,DictCodeListExt1.现金流二级类型_利息.getValue());
        // 现金流代码 = B.资产代码
        result.setCashId(cashId);
        // 生效日期 = B.生效日
        result.setEffDate(dto.getEffDate());
        // 支付频率 = B.付息频率
        result.setPayCycle(dto.getPayCycle());
        // 营业日准则 = B.营业日准则
//        result.setTdayRule(dto.getTdayRule());
        
        /**add by wujie on 20210219 start**/
		//支付日营业日准则 = B.营业日准则
        result.setPayTdayRule(dto.getTdayRule());
		/**add by wujie on 20210219 end**/
		
        //
        // result.setCalcRule(dto.getCalcRule());
        // 现金流单位 = 02：总额
        result.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = B.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F06：基金
        result.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        // 支付方式 = B.支付方式
        result.setPayType(dto.getPayType());
        //分支序号
        result.setBranch(InterfCommonConstant.分支序号_默认);
        result.setEndTdayRule(dto.getTdayRule());
        return result;
    }
}
