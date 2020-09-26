/*
 * 文件名：LendStandardPlanRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.lendstandard;

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
import com.joyintech.tams.valuation.common.interf.base.dto.LendStandardCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlTargetRatePlan;
/**
 * 拆借付息计划(VAL_DL_TARGET_RATE_PLAN)
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年8月25日
 * @see LendStandardPlanRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendStandardPlanRecService {
	 /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public LendStandardCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {		
		LendStandardCommonResult result = new LendStandardCommonResult();
		List<ValDlTargetRatePlan> listValDlTargetRatePlan = new ArrayList<ValDlTargetRatePlan>();
	    TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValDlTargetRatePlan = returnResult.getT(ValDlTargetRatePlan.class);
        
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        //现金流计划
        List<FinCashPlan> listValCashPlan = handleValDlTargetRatePlan(listValDlTargetRatePlan);
        result.setListValCashPlan(listValCashPlan);
        
        
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
	}
    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValDlTargetRatePlan
     * @return 现金流支付计划 list
     */
	private List<FinCashPlan> handleValDlTargetRatePlan(List<ValDlTargetRatePlan> listValDlTargetRatePlan) {
		//现金流计划 list
		List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
		for(ValDlTargetRatePlan dto :listValDlTargetRatePlan){
			//现金流计划
			FinCashPlan  valCashPlan = this.getValDlTargetRatePlan(dto);
			listValCashPlan.add(valCashPlan);			
		}
		return listValCashPlan;
	}
	/**
	 * 现金流计划表 接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinCashPlan
	 */
	private FinCashPlan getValDlTargetRatePlan(ValDlTargetRatePlan dto) {
		FinCashPlan valFinCashPlan = new FinCashPlan();
		//现金流序号 = C.现金流序号
		valFinCashPlan.setCashNum(dto.getOrderNo().longValue());
		//未调整的计算开始日 = C.理论开始日
		valFinCashPlan.setVdateUnadjust(dto.getTheVdate());
		//未调整的计算结束日 = C.理论截止日
		valFinCashPlan.setMdateUnadjust(dto.getTheMdate());
		//调整后的计算开始日 = C.实际计息开始日
		valFinCashPlan.setVdate(dto.getActVdate());
		//调整后的计算结束日 = C.实际计息截止日
		valFinCashPlan.setMdate(dto.getActMdate());
		//调整后的计划支付日 = C.支付日
		valFinCashPlan.setPayDate(dto.getPayDay());
		//收息计划
		if(DictCodeList.交易方向_收息.getValue().equals(dto.getDealType())){
			//现金流代码 =InterfHelper.getMd5CashId_（C.拆借标的代码,C.业务类型,数据字典：现金流二级类型.利息）
			valFinCashPlan.setCashId(InterfHelper.getMd5CashId_(dto.getDlId(),
					dto.getBusiType(),DictCodeListExt1.现金流二级类型_利息.getValue()));
		    //计息本金 = C.计息本金
			valFinCashPlan.setIntPrinAmt(dto.getPrinCapi());
			//是否支付利息 = C.是否支付利息	
			valFinCashPlan.setIsPayInt(dto.getIsPayInt());
			//计划支付利息 = C.利息金额
			valFinCashPlan.setIntAmt(dto.getIntAmt());
			//计划支付现金流 = C.利息金额
			valFinCashPlan.setCashAmt(dto.getIntAmt());
		}
		//还本计划 
		else{
			//现金流代码 =InterfHelper.getMd5CashId_（C.拆借标的代码,C.业务类型,数据字典：现金流二级类型.本金）
			valFinCashPlan.setCashId(InterfHelper.getMd5CashId_(dto.getDlId(),
					dto.getBusiType(),DictCodeListExt1.现金流二级类型_本金.getValue()));
		    //计划支付本金 = C.还本金额
			valFinCashPlan.setPrinAmt(dto.getPrintAmt());
			//是否支付利息  = 默认为 否
			valFinCashPlan.setIsPayInt(DictCodeList.是否_否.getValue());
			//计划支付现金流 = C.还本金额
			valFinCashPlan.setCashAmt(dto.getPrintAmt());
		}
		//现金流单位 = 数据字典：现金流单位.总额
		valFinCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
		//金融产品代码  = C.资产代码
		valFinCashPlan.setFinprodId(dto.getDlId());
		//金融产品类型 = C.业务类型
		valFinCashPlan.setFinprodType(dto.getBusiType());
		//分支序号 = 设置全局变量(默认为0）
		valFinCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
		//支付方式 = 数据字典：支付方式.现金
		valFinCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
		return valFinCashPlan;
	}	
}
