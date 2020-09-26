/*
 * 文件名：CPlannedAssetBaseInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cplannedinfo;

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
import com.joyintech.tams.valuation.common.interf.base.dto.CPlannedAssetBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfPayPlan;

/**
 * 计划类资产(货币型)分红计划模板处理 (ValMngMnfPayPlan) 
 * 
 * @author zhangzhao
 * @version 1.0 2019年1月22日
 * @see CPlannedAssetPayPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPlannedAssetPayPlanTemplateRecService {

	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CPlannedAssetBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		CPlannedAssetBaseInfoValCommonResult result = new CPlannedAssetBaseInfoValCommonResult();
		List<ValMngMnfPayPlan> listValMngMnfPayPlan = new ArrayList<ValMngMnfPayPlan>();

		// 解析模板后的list以及日志list
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

		// 1、文件读取解析
		returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValMngMnfPayPlan = returnResult.getT(ValMngMnfPayPlan.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		result.setListValCashPlan(this.getValCashPlan(listValMngMnfPayPlan));
		// 添加日志
		result.setListValInterflog(returnResult.getValInterflog());
		return result;

	}

	/**
	 * 现金流计划表   接收 
	 * @param dto
	 * @return 现金流计划表
	 */
	private List<FinCashPlan> getValCashPlan(List<ValMngMnfPayPlan> listValMngMnfPayPlan) {
		  List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
	        for (ValMngMnfPayPlan item : listValMngMnfPayPlan) {
		FinCashPlan valFinCashPlan = new FinCashPlan();
		// 现金流代码= InterfHelper.getMd5CashId_(C.资产代码,数据字典：金融产品类型.计划类资产,数据字典：现金流二级类型.利息）
		valFinCashPlan.setCashId(InterfHelper.getMd5CashId_(item.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue(),
				DictCodeListExt1.现金流二级类型_利息.getValue()));
		// 现金流序号= C.现金流序号
		valFinCashPlan.setCashNum(item.getOrderNo().longValue());
		// 调整后的计算开始日= C.计息起始日
		valFinCashPlan.setVdate(item.getVdate());
		// 调整后的计算结束日= C.计息结束日
		valFinCashPlan.setMdate(item.getMdate());
		// 调整后的计划支付日= C.付息日
		valFinCashPlan.setPayDate(item.getPayDate());
		// 现金流单位= 数据字典：现金流单位.总额
		valFinCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
		// 金融产品代码= C.资产代码
		valFinCashPlan.setFinprodId(item.getAssetId());
		// 金融产品类型= 数据字典：金融产品类型.基金
		valFinCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
		// 分支序号= 设置全局变量(默认为0）
		valFinCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
		// 支付方式= C.支付方式
		valFinCashPlan.setPayType(item.getPayType());
		listValCashPlan.add(valFinCashPlan);
	   }
		return listValCashPlan;
	}
	
}
