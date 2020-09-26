/*
 * 文件名：CPrdPayPlanTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPayPlanC;
/**
 * 货币型型产品付费计划表(Val_PRD_PAY_PLAN_C) 〈功能详细描述〉
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年8月13日
 * @see CPrdPayPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPrdPayPlanTemplateRecService {
	 /**
     * 货币型产品基本信息模板处理service <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
    	CPrdBaseInfoValCommonResult result = new CPrdBaseInfoValCommonResult();
        List<ValPrdPayPlanC> listValPrdPayPlanC = new ArrayList<ValPrdPayPlanC>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdPayPlanC = returnResult.getT(ValPrdPayPlanC.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 现金流计划List
        List<FinCashPlan> listValCashPlan = handleValPrdPayPlanC(listValPrdPayPlanC);
        result.setListValCashPlan(listValCashPlan);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    /**
     * 07-付费计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValPrdPayPlanC  货币型产品付费计划List
     * @return  现金流计划表list
     */
	private List<FinCashPlan> handleValPrdPayPlanC(List<ValPrdPayPlanC> listValPrdPayPlanC) {
		 // 现金流计划表List
        List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
        for (ValPrdPayPlanC dto : listValPrdPayPlanC) {
            // 现金流计划表
            FinCashPlan valCashPlan = this.getValCashPlan(dto);
            listValCashPlan.add(valCashPlan);
        }
        return listValCashPlan;
	}
	
	/**
	 * 现金流计划表  接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinCashPlan 现金流计划
	 * @see
     * @since 1.0
	 */
	private FinCashPlan getValCashPlan(ValPrdPayPlanC dto) {
		 // 现金流计划表
        FinCashPlan valCashPlan = new FinCashPlan();
        /**modify by niuqianyu on 20190118 start**/
        // 现金流代码 = MD5加密(G.产品代码+G.费用ID)
//        valCashPlan.setCashId(InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId()));
        
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }   
        valCashPlan.setCashId(cashId);
        /**modify by niuqianyu on 20190118 end**/
        // 现金流序号 = G.现金流序号
        valCashPlan.setCashNum(dto.getOrderNo().longValue());
        // 调整后的计算结束日 = G.支付日
        valCashPlan.setMdate(dto.getPayDate());
        // 调整后的计划支付日 = G.支付日
        valCashPlan.setPayDate(dto.getPayDate());
        // 现金流单位 = 02：总额
        valCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = G.产品代码
        valCashPlan.setFinprodId(dto.getProdId());
        /**modify by niuqianyu on 20190118 start**/
//        // 金融产品类型 = F16：理财产品
//        valCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        
        // 金融产品类型 = G.金融产品类型
        valCashPlan.setFinprodType(dto.getFinprodType());
        
        // 分支序号 = 设置全局变量(默认为0）
//        valCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
        
        Long branch = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
        } else {
            branch = InterfCommonConstant.分支序号_默认;
        }   
        valCashPlan.setBranch(branch);
        /**modify by niuqianyu on 20190118 end**/
        // 支付方式 = 02:现金
        valCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
        return valCashPlan;
	}

}
