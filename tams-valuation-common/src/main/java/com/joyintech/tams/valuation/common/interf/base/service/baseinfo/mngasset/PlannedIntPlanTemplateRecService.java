/*
 * 文件名：PlannedIntPlanTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月26日
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

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngIntPlan;

/**
 * 计划类资产（利率型）付息计划模板处理
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年6月26日
 * @see PlannedIntPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlannedIntPlanTemplateRecService {
    
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
        List<ValMngIntPlan> listValMngIntPlan = new ArrayList<ValMngIntPlan>(); 
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
                
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMngIntPlan = returnResult.getT(ValMngIntPlan.class);
    
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String,Object> map = handleListValMngIntPlan(listValMngIntPlan);
        // 判断返回结果不为空
        if (map != null) {
            List<FinCashPlan> listValCashPlan = (List<FinCashPlan>)map.get("ValCashPlan");
            result.setListValCashPlan(listValCashPlan);
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 
     * @param listValMngIntPlan 计划类资产（利率型）付息计划信息
     * @return 现金流计划表 规则
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValMngIntPlan(List<ValMngIntPlan> listValMngIntPlan) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinCashPlan> ValCashPlanList = new ArrayList<FinCashPlan>();
        // 获取付息计划信息
        for (ValMngIntPlan dto : listValMngIntPlan) {
            // 现金流计划信息 处理
            FinCashPlan ValCashPlan = getValCashPlan(dto);
            ValCashPlanList.add(ValCashPlan);
        }
        resultMap.put("ValCashPlan", ValCashPlanList);
        return resultMap;
    }
    
    /**
     * 现金流计划表 接收
     * 
     * @param dto 付息计划
     * @return 现金流计划信息
     * @see
     * @since 1.0
     */
    private FinCashPlan getValCashPlan(ValMngIntPlan dto) {
        FinCashPlan valCashPlan = new FinCashPlan();
        // 现金流代码 = D.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.利息
        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(), DictCodeListExt1.金融产品类型_计划类资产.getValue()
        		,DictCodeListExt1.现金流二级类型_利息.getValue());
        valCashPlan.setCashId(cashId);
        // TODO 现金流序号= D.现金流序号
        valCashPlan.setCashNum(dto.getOrderNo().longValue());
        // 调整后的计算开始日= D.计息起始日
        valCashPlan.setVdate(dto.getVdate());
        // 调整后的计算结束日= D.计息结束日
        valCashPlan.setMdate(dto.getMdate());
        // 调整后的计划支付日= 支付日
        valCashPlan.setPayDate(dto.getPayDate());
        // 计算周期天数= D.计息结束日-D.计息起始日
        valCashPlan.setTermdays(DateUtil.getBetweenDayNumber(dto.getVdate(), dto.getMdate()));
        // 计息本金= D.计息本金
        valCashPlan.setIntPrinAmt(dto.getIntCap());
        // 是否支付利息= D.是否支付利息
        valCashPlan.setIsPayInt(dto.getIsPayInt());
        // 计划支付利息= D.利息金额
        valCashPlan.setIntAmt(dto.getIntAmt());
        //TODO 计划支付现金流= A.利息金额
        valCashPlan.setCashAmt(dto.getIntAmt());
        // 现金流单位= 02：总额
        valCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码= D.资产名称
        valCashPlan.setFinprodId(dto.getAssetId());
        // 金融产品类型= F17:债权类资产
        valCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号= 设置全局变量(默认为0）
        valCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
        // 支付方式= 02:现金
        valCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
        return valCashPlan;
    }
}
