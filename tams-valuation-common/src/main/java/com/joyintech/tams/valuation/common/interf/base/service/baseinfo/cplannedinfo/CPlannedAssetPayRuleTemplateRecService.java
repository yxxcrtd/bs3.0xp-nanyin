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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfPayRule;

/**
 * 计划类资产(货币型)分红规则模板处理 (ValMngMnfPayRule) 
 * 
 * @author zhangzhao
 * @version 1.0 2019年1月22日
 * @see CPlannedAssetPayRuleTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPlannedAssetPayRuleTemplateRecService {

	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CPlannedAssetBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		CPlannedAssetBaseInfoValCommonResult result = new CPlannedAssetBaseInfoValCommonResult();
		List<ValMngMnfPayRule> listValMngMnfPayRule = new ArrayList<ValMngMnfPayRule>();

		// 解析模板后的list以及日志list
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

		// 1、文件读取解析
		returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValMngMnfPayRule = returnResult.getT(ValMngMnfPayRule.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		Map<String, Object> map = handleValPrdInfoC(listValMngMnfPayRule );

		// 判断返回结果不为空
		if (map != null) {
			
			//现金流信息表
            List<FinCashInfo> listValFinProduct = (List<FinCashInfo>)map.get("ValFinCashInfo");
            //现金流支付规则表
            List<FinCashPayRule> listValCashPayRule = (List<FinCashPayRule>)map.get("ValFinCashPayRule");
            
			result.setListValCashInfo(listValFinProduct);
			result.setListValCashPayRule(listValCashPayRule);
			
		}
		// 添加日志
		result.setListValInterflog(returnResult.getValInterflog());
		return result;

	}

	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param listValMngMnfPayRule
	 * @return
	 * @see
	 * @since 1.0
	 */
	private Map<String, Object> handleValPrdInfoC(List<ValMngMnfPayRule> listValMngMnfPayRule) {
		Map<String, Object> resultMap = new HashMap<>();
		List<FinCashInfo> valFinCashInfoList = new ArrayList<FinCashInfo>();
		List<FinCashPayRule> valFinCashPayRuleList = new ArrayList<FinCashPayRule>();
		
		// 获取分红规则Dto
				for (ValMngMnfPayRule dto : listValMngMnfPayRule) {
					// 现金流信息表 处理
					FinCashInfo valFinCashInfo = getValFinCashInfo(dto);
					valFinCashInfoList.add(valFinCashInfo);
					
					// 现金流支付规则表 待处理
					FinCashPayRule valFinCashPayRule = getValFinCashPayRule(dto,valFinCashInfo);
					valFinCashPayRuleList.add(valFinCashPayRule);
					 
				}
				resultMap.put("ValFinCashInfo", valFinCashInfoList);
				resultMap.put("ValFinCashPayRule", valFinCashPayRuleList);
		       
				return resultMap;
	}

	/**
	 * 现金流信息表 接收 
	 * @param dto
	 * @return 现金流信息表
	 */
	private FinCashInfo getValFinCashInfo(ValMngMnfPayRule dto) {
		FinCashInfo valFinCashInfo =new FinCashInfo();
		// 现金流代码= InterfHelper.getMd5CashId_(B.资产代码,数据字典：金融产品类型.计划类资产,数据字典：现金流二级类型.利息）
		valFinCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue(),
				DictCodeListExt1.现金流二级类型_利息.getValue()));
		// 现金流一级类型= 数据字典：现金流一级类型.利息
		valFinCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
		// 现金流二级类型= 数据字典：现金流二级类型.利息
		valFinCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
		// 金融产品代码= B.资产代码
		valFinCashInfo.setFinprodId(dto.getAssetId());
		// 金融产品类型= 数据字典：金融产品类型.计划类资产
		valFinCashInfo.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
		// 分支序号= 设置全局变量(默认为0）
		valFinCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
		return valFinCashInfo;
	}
	
	/**
	 * 现金流支付规则表 接收 
	 * @param dto
	 * @return 现金流支付规则表
	 */
	private FinCashPayRule getValFinCashPayRule(ValMngMnfPayRule dto,FinCashInfo finCashInfo) {
		FinCashPayRule valFinCashPayRule = new FinCashPayRule();
		// 现金流代码= T4.现金流代码
		valFinCashPayRule.setCashId(finCashInfo.getCashId());
		// 生效日期= B.生效日
		valFinCashPayRule.setEffDate(dto.getEffDate());
		// 支付频率= B.付息频率
		valFinCashPayRule.setPayCycle(dto.getPayCycle());
		// 营业日准则= B.营业日准则
//		valFinCashPayRule.setTdayRule(dto.getTdayRule());
		// 现金流单位= 数据字典：现金流单位.总额
		valFinCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
		// 金融产品代码= B.资产代码
		valFinCashPayRule.setFinprodId(dto.getAssetId());
		// 金融产品类型= 数据字典：金融产品类型.基金
		valFinCashPayRule.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
		// 支付方式= B.支付方式
		valFinCashPayRule.setPayType(dto.getPayType());
		// 分支序号= 设置全局变量(默认为0）
		valFinCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
		return valFinCashPayRule;
	}

	
	
}
