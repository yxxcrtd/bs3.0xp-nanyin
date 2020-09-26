/*
 * 文件名：FundPayPlanMoneyFundRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.fund;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FundBaseInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundMnfPayPlan;

/**
 * 货币基金付息计划 基金类资产定义货币基金付息计划 = "VAL_FUND_MNF_PAY_PLAN"
 * 
 * @author HuangJian
 * @version 1.0 2018年6月26日
 * @see FundPayPlanMoneyFundRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FundPayPlanMoneyFundRecService {

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
        List<ValFundMnfPayPlan> listValFundMnfPayPlan = new ArrayList<ValFundMnfPayPlan>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFundMnfPayPlan = returnResult.getT(ValFundMnfPayPlan.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构

        result.setListValCashPlan(this.getValCashPlan(listValFundMnfPayPlan));
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;

    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValFundMnfPayPlan List<ValFundMnfPayPlan>
     * @return List<ValCashPlan>
     * @see
     * @since 1.0
     */
    private List<FinCashPlan> getValCashPlan(List<ValFundMnfPayPlan> listValFundMnfPayPlan) {
        List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
        for (ValFundMnfPayPlan item : listValFundMnfPayPlan) {
            FinCashPlan valCashPlan = new FinCashPlan();
            String cashId = InterfHelper.getMd5CashId_(item.getAssetId(), DictCodeListExt1.金融产品类型_基金.getValue()
            		,DictCodeListExt1.现金流二级类型_利息.getValue());
            // 现金流代码 = C.资产代码
            valCashPlan.setCashId(cashId);
            // 现金流序号 = C.现金流序号
            valCashPlan.setCashNum(item.getOrderNo().longValue());
            // 调整后的计算开始日 = C.计息起始日
            valCashPlan.setVdate(item.getVdate());
            // 调整后的计算结束日 = C.计息结束日
            valCashPlan.setMdate(item.getMdate());
            // 调整后的计划支付日 = C.付息日
            valCashPlan.setPayDate(item.getPayDate());
            // 现金流单位 = 02：总额
            valCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
            // 金融产品代码 = C.资产代码
            valCashPlan.setFinprodId(item.getAssetId());
            // 金融产品类型 = F06：基金
            valCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
            // 分支序号 = 设置全局变量(默认为0）
            valCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
            // 创建人 = SYS
            valCashPlan.setPayType(item.getPayType());
            listValCashPlan.add(valCashPlan);
        }
        return listValCashPlan;
    }
}
