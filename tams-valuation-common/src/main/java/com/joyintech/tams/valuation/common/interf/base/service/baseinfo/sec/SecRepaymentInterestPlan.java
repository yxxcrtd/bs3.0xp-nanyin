/*
 * 文件名：SecRepaymentInterestPlan.java
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

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecPayRatePlan;

/**
 * 债券还本付息计划
 * 
 * @author HuangJian
 * @version 1.0 2018年6月28日
 * @see SecRepaymentInterestPlan
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecRepaymentInterestPlan {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SecBaseInfoRecProcessorRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                          TemplateOrderInfoReaderResult acctEntity) {
        SecBaseInfoRecProcessorRecCommonResult result = new SecBaseInfoRecProcessorRecCommonResult();
        List<ValSecPayRatePlan> listValSecPayRatePlan = new ArrayList<ValSecPayRatePlan>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSecPayRatePlan = returnResult.getT(ValSecPayRatePlan.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        result.setListValCashPlan(this.getValCashPlan(listValSecPayRatePlan));
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    private List<FinCashPlan> getValCashPlan(List<ValSecPayRatePlan> listValSecPayRatePlan) {
        List<FinCashPlan> result = new ArrayList<FinCashPlan>();
        for (ValSecPayRatePlan item : listValSecPayRatePlan) {
            FinCashPlan valCashPlan = new FinCashPlan();
            String cashId="";
            // D.交易类型为还本--> 01 本金
            if (DictCodeList.交易方向_还本.getValue().equals(item.getTradeType())) {
                // 现金流代码 = D.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.本金
                cashId = InterfHelper.getMd5CashId_(item.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
                		,DictCodeListExt1.现金流二级类型_本金.getValue());
            }
            // D.交易类型为收息--> 02 利息
            if (DictCodeList.交易方向_收息.getValue().equals(item.getTradeType())) {
                // 现金流代码 = D.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.利息
                cashId = InterfHelper.getMd5CashId_(item.getAssetId(), DictCodeListExt1.金融产品类型_债券.getValue()
                		,DictCodeListExt1.现金流二级类型_利息.getValue());
            }
            valCashPlan.setCashId(cashId);
            
            
            // 现金流序号 =D.现金流序号
            valCashPlan.setCashNum(item.getOrderNo().longValue());
            // 未调整的计算开始日 = D.实际计息开始日
            valCashPlan.setVdateUnadjust(item.getActVdate());
            // 未调整的计算结束日 = D.实际计息截止日
            valCashPlan.setMdateUnadjust(item.getActMdate());
            // 调整后的计算开始日 = D.实际计息开始日
            valCashPlan.setVdate(item.getActVdate());
            // 调整后的计算结束日 = D.实际计息截止日
            valCashPlan.setMdate(item.getActMdate());
            // 调整后的计划支付日 = D.支付日
            valCashPlan.setPayDate(item.getPayDay());
            // 完整周期开始日 = D.理论开始日
            valCashPlan.setVdateTerm(item.getTheVdate());
            // 完整周期结束日 = D.理论截止日
            valCashPlan.setMdateTerm(item.getTheMdate());
            // 计算周期天数 =D.实际计息截止日-D.实际计息开始日
            valCashPlan.setTermdays(
                DateUtil.getBetweenDayNumber(item.getActVdate(), item.getActMdate()));
            // 计划支付本金 = D.还本面额
            valCashPlan.setPrinAmt(item.getRepayAmt());
            // 计息本金= D.计息本金
            valCashPlan.setIntPrinAmt(item.getPrinCapi());
            /** modify by wujie on 20190114 start
            // 是否支付利息
            valCashPlan.setIsPayInt("");
            **/
            //是否支付利息 = D:是否支付利息
            valCashPlan.setIsPayInt(item.getIsPayInt()==null?DictCodeList.是否_是.getValue():item.getIsPayInt());
            /**modify by wujie on 20190114 end**/
            // 计划支付利息=D.利息金额
            valCashPlan.setIntAmt(item.getIntAmt());
            // 计划支付现金流 = D.现金流总额
            valCashPlan.setCashAmt(item.getCashAmt());
            // 现金流单位 = 01 百元法
            valCashPlan.setCashUnitType(DictCodeList.现金流单位_百元法.getValue());
            // 金融产品代码 = E.资产代码
            valCashPlan.setFinprodId(item.getAssetId());
            // 金融产品类型 = F01 债券
            valCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
            // 分支序号 = 设置全局变量(默认为0）
            valCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
            // 支付方式 = 02:现金
            valCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
            result.add(valCashPlan);
        }
        return result;
    }
}
