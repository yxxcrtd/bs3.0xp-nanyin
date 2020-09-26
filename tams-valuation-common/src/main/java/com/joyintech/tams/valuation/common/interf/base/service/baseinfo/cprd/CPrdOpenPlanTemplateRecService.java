/*
 * 文件名：CPrdOpenPlanTemplateRecService.java
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
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.CPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdOpenPlanC;
import com.joyintech.framework.util.StringUtil;

/**
 * 货币型产品开发计划表(Val_PRD_OPEN_PLAN_N) 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年8月13日
 * @see CPrdOpenPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPrdOpenPlanTemplateRecService {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		CPrdBaseInfoValCommonResult result = new CPrdBaseInfoValCommonResult();
		List<ValPrdOpenPlanC> listValPrdOpenPlanC = new ArrayList<ValPrdOpenPlanC>();
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
	    returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValPrdOpenPlanC = returnResult.getT(ValPrdOpenPlanC.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		List<FinOpenPlan> listValOpenPlan = handleValPrdOpenPlanC(listValPrdOpenPlanC);
		result.setListOpenPlan(listValOpenPlan);

		// 添加日志
		result.setValInterflog(returnResult.getValInterflog());
		// 返回处理结果
		return result;
	}

	/**
	 * 货币型产品产品计划表<一句话功能简述>
	 *  <功能详细描述>
	 * @param listValPrdOpenPlanC
	 * @return
	 */
	private List<FinOpenPlan> handleValPrdOpenPlanC(List<ValPrdOpenPlanC> listValPrdOpenPlanC) {
		List<FinOpenPlan> listValOpenPlan = new ArrayList<FinOpenPlan>();
		for (ValPrdOpenPlanC dto : listValPrdOpenPlanC) {
			// 开放计划表
			FinOpenPlan valOpenPlan = this.getValOpenPlan(dto);
			listValOpenPlan.add(valOpenPlan);
		}
		return listValOpenPlan;
	}

	/**
	 * 货币型产品产品计划 接收 <一句话功能简述>
	 *  <功能详细描述>
	 * @param dto
	 * @return
	 */
	private FinOpenPlan getValOpenPlan(ValPrdOpenPlanC dto) {
		// 开放计划表
		FinOpenPlan valOpenPlan = new FinOpenPlan();
		/**modify by niuqianyu on 20190117 start**/
		// 金融产品代码 = C.产品代码
		//valOpenPlan.setFinprodId(dto.getProdId());

		String finprodId = null;
		String cashId = null;
		if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
			finprodId = dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate());
			cashId = InterfHelper.getMd5CashId_(finprodId+new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),DictCodeListExt1.现金流二级类型_本金.getValue());
		}else{
			finprodId = dto.getProdId();
			cashId = InterfHelper.getMd5CashId_(finprodId,DictCodeListExt1.现金流二级类型_本金.getValue());
		}
		valOpenPlan.setFinprodId(finprodId);
		/**modify by niuqianyu on 20190117 end**/
		// 开放周期起始日 = C.开始周期起始日
		valOpenPlan.setOpenDateStr(dto.getOpenDateStr());
		// 开放周期结束日 = C.开放周期结束日
		valOpenPlan.setOpenDateEnd(dto.getOpenDateEnd());
		// 开放周期起息日 = C.开放周期起息日
		valOpenPlan.setOpenVdate(dto.getOpenVdate());
		
		//TODO  估值系统表结构改动 
//		开放计划表-->开放计划（FIN_VAL_OPEN_PLAN---FIN_OPEN_PLAN）
		valOpenPlan.setOpenDateEndUn(dto.getProdVdate());
		valOpenPlan.setCashId(cashId);
		valOpenPlan.setOpenType("temptype");
		return valOpenPlan;
	}
}
