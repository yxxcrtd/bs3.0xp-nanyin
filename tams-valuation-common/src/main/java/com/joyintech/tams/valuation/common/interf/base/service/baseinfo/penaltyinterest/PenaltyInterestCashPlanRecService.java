/*
 * 文件名：PenaltyInterestCashPlanRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.penaltyinterest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.PenaltyInterestCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFxTargetPlan;
/**
 * 拆借付息计划(VAL_DL_TARGET_RATE_PLAN)
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年8月25日
 * @see PenaltyInterestCashPlanRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PenaltyInterestCashPlanRecService {
	 /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public PenaltyInterestCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {		
		PenaltyInterestCommonResult result = new PenaltyInterestCommonResult();
		List<ValFxTargetPlan> listValFxTargetPlan = new ArrayList<ValFxTargetPlan>();
	    TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFxTargetPlan = returnResult.getT(ValFxTargetPlan.class);
        
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        //现金流计划
        List<FinCashPlan> listValCashPlan = handleValFxTargetPlan(listValFxTargetPlan);
        result.setListValCashPlan(listValCashPlan);
        
        
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
	}
    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValFxTargetPlan
     * @return 现金流支付计划 list
     */
	private List<FinCashPlan> handleValFxTargetPlan(List<ValFxTargetPlan> listValFxTargetPlan) {
		//现金流计划 list
		List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
		for(ValFxTargetPlan dto :listValFxTargetPlan){
			//现金流计划
			FinCashPlan  valCashPlan = this.getFinCashPlan(dto);
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
	private FinCashPlan getFinCashPlan(ValFxTargetPlan dto) {
		FinCashPlan valFinCashPlan = new FinCashPlan();
		// 现金流代码 = InterfHelper.getMd5CashId_(A.罚息资产代码,A.罚息资产类型,数据字典：现金流二级类型.罚息)
        valFinCashPlan.setCashId(InterfHelper.getMd5CashId_(dto.getAssetCode(),dto.getFinprodType(),
            String.valueOf(dto.getBranch()), DictCodeListExt1.现金流二级类型_罚息.getValue()));
        // 未调整的计算开始日 = C.计息开始日
        valFinCashPlan.setVdateUnadjust(dto.getVdate());
        // 未调整的计算结束日 = C.计息截止日
        valFinCashPlan.setMdateUnadjust(dto.getMdate());
        // 未调整的计划支付日 = C.支付日
        valFinCashPlan.setPayDateUnadjust(dto.getPayDay());
        // 调整后的计算开始日  = C.计息开始日
        valFinCashPlan.setVdate(dto.getVdate());
        // 调整后的计算结束日 = C.计息截止日
        valFinCashPlan.setMdate(dto.getMdate());
        // 调整后的计划支付日 = C.支付日
        valFinCashPlan.setPayDate(dto.getPayDay());
        // 现金流单位 = 数据字典：现金流单位.总额
        valFinCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = C.罚息资产代码
        valFinCashPlan.setFinprodId(dto.getAssetCode());
        // 金融产品类型 = C.罚息资产类型
        valFinCashPlan.setFinprodType(dto.getFinprodType());
        // 分支序号 = C.分支序号
        valFinCashPlan.setBranch(dto.getBranch());
        // 支付方式 = 数据字典：支付方式.现金
        valFinCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
		return valFinCashPlan;
	}	
}
