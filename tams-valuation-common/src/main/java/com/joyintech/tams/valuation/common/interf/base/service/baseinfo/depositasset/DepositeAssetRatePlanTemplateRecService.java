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

import com.joyintech.tams.common.base.entity.FinCashPlan;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstRatePlan;

/**
 * 存款类资产-付息计划 模板处理Service(VAL_BOT_AST_RATE_ADJUST)
 * 
 * @author weipeng
 * @version 1.0 2018年6月27日
 * @see DepositeAssetRatePlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetRatePlanTemplateRecService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 存款类资产-付息计划 接口模板处理
     * 
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return 存款类资产信息 接口模板处理返回共通结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public DepositeAssetBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                        TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        DepositeAssetBaseInfoValCommonResult result = new DepositeAssetBaseInfoValCommonResult();
        List<ValBotAstRatePlan> listValBotAstRatePlan = new ArrayList<ValBotAstRatePlan>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValBotAstRatePlan = returnResult.getT(ValBotAstRatePlan.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 现金流计划List
        List<FinCashPlan> listValCashPlan = handleValBotAstRatePlan(listValBotAstRatePlan);
        result.setListValCashPlan(listValCashPlan);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    private List<FinCashPlan> handleValBotAstRatePlan(List<ValBotAstRatePlan> listValBotAstRatePlan) {
        // 定义 现金流计划List
        List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();

        for (ValBotAstRatePlan dto : listValBotAstRatePlan) {
            // 现金流计划
            FinCashPlan ValCashPlan = getValCashPlan(dto);
            listValCashPlan.add(ValCashPlan);
        }
        return listValCashPlan;
    }

    /**
     * T6 现金流计划
     * 
     * @param dto 03-付息计划
     * @return 现金流计划
     * @see
     * @since 1.0
     */
    private FinCashPlan getValCashPlan(ValBotAstRatePlan dto) {
        FinCashPlan valCashPlan = new FinCashPlan();

        // 现金流代码 = C.资产代码+数据字典：金融产品类型.存款+数据字典：现金流二级利息.利息
        String caseId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_存款.getValue(),
        		DictCodeListExt1.现金流二级类型_利息.getValue());
        valCashPlan.setCashId(caseId);
        // 现金流序号 = C.现金流序号
        valCashPlan.setCashNum(dto.getCashNum());
        // 调整后的计算开始日 = C.计息起始日
        valCashPlan.setVdate(dto.getVdate());
        // 调整后的计算结束日 = C.计息结束日
        valCashPlan.setMdate(dto.getMdate());
        // 调整后的计划支付日 = C.支付日
        valCashPlan.setPayDate(dto.getPayDate());
        // 计息本金 = C.计息本金
        valCashPlan.setIntPrinAmt(dto.getIntCap());
        // 计划支付利息 = C.应计利息
        valCashPlan.setIntAmt(dto.getUnitInt());
        // 计划支付现金流 = C.应计利息
        valCashPlan.setCashAmt(dto.getUnitInt());
        // 现金流单位 = 02：总额
        valCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = C.资产代码
        valCashPlan.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F07 存款
        valCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        valCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
        // 支付方式 = 02 现金
        valCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
        return valCashPlan;
    }

}
