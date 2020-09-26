/*
 * 文件名：YPrdRateRuleTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPayRule;
/**
 * 
 * 〈一句话功能简述〉预期收益型产品付息规则（VAL_PRD_PAY_RULE）
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月8日
 * @see YPrdPayRuleTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdPayRuleTemplateRecService {
	/**
	 * 预期收益型产品基本信息模板处理service <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public YPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		YPrdBaseInfoValCommonResult result = new YPrdBaseInfoValCommonResult();
		List<ValPrdPayRule> listValPrdPayRule = new ArrayList<ValPrdPayRule>();
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
		returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValPrdPayRule = returnResult.getT(ValPrdPayRule.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		 Map<String, Object> map = handleValPrdPayRule(listValPrdPayRule);
		if(map!=null){
			 // 现金流信息List
            List<FinCashInfo> listValCashInfo = (List<FinCashInfo>)map.get("ValCashInfo");
            result.setListValCashInfo(listValCashInfo);
            // 现金流支付规则List
            List<FinCashPayRule> listValCashPayRule = (List<FinCashPayRule>)map.get(
                "ValCashPayRule");
            result.setListValCashPayRule(listValCashPayRule);
		}
		// 添加日志
		result.setValInterflog(returnResult.getValInterflog());
		// 返回处理结果
		return result;
	}
    /**
     * 03产品付息规则
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValPrdPayRule
     * @return  现金流信息表list + 现金流支付规则表list
     */
	private Map<String, Object> handleValPrdPayRule(List<ValPrdPayRule> listValPrdPayRule) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		
		//现金流信息list
		List<FinCashInfo> listValFinCashInfo = new ArrayList<FinCashInfo>();
		//现金流支付规则list
		List<FinCashPayRule> listValFinCashPayRule = new ArrayList<FinCashPayRule>();
		for(ValPrdPayRule dto :listValPrdPayRule){
			 // 现金流信息
			FinCashInfo valFinCashInfo = this.getValFinCashInfo(dto);
			listValFinCashInfo.add(valFinCashInfo);
            // 现金流支付规则
			FinCashPayRule valFinCashPayRule = this.getValFinCashPayRule(dto,valFinCashInfo);
			listValFinCashPayRule.add(valFinCashPayRule);
		}
		 resultMap.put("ValCashInfo", listValFinCashInfo);
	     resultMap.put("ValCashPayRule", listValFinCashPayRule);
		return resultMap;
	}
	/**
	 * 现金流支付规则接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return  FinCashPayRule
	 */
	
	private FinCashPayRule getValFinCashPayRule(ValPrdPayRule dto,FinCashInfo valFinCashInfo) {
		FinCashPayRule valFinCashPayRule = new FinCashPayRule();
        //现金流代码=T4.现金流代码
		valFinCashPayRule.setCashId(valFinCashInfo.getCashId());
		//生效日期=C.生效日期
		valFinCashPayRule.setEffDate(dto.getVdate());
		//支付频率=C.付息规则
		valFinCashPayRule.setPayCycle(dto.getPayRule());
		//营业日准则=C.营业日准则
//		valFinCashPayRule.setTdayRule(dto.getTdayRule());
		//推算方式=C.推算方式
		valFinCashPayRule.setCalcRule(dto.getCalculation());
		//首次支付日=C.首次付息日
		valFinCashPayRule.setFirstPayDate(dto.getFstPayDate());
		//现金流单位=数据字典：现金流单位.总额
		valFinCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
		//金融产品代码=T4.产品代码
		valFinCashPayRule.setFinprodId(valFinCashInfo.getFinprodId());//modify by dongjun on 20181029
		//金融产品类型=T4.金融产品类型
		valFinCashPayRule.setFinprodType(valFinCashInfo.getFinprodType());//modify by dongjun on 20181029
		//支付方式=数据字典：支付方式.现金
		valFinCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
		//分支序号=设置全局变量(默认为0）
		valFinCashPayRule.setBranch(valFinCashInfo.getBranch());//modify by dongjun on 20181029
		return valFinCashPayRule;
	}
	
	
	/**
	 * 现金流信息接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinCashInfo
	 */
	private FinCashInfo getValFinCashInfo(ValPrdPayRule dto) {
		FinCashInfo valFinCashInfo =new FinCashInfo();
		//现金流代码=InterfHelper.getMd5CashId_（C.产品代码,C.金融产品类型,数据字典：现金流二级类型.利息)
		/** modify by dongjun on 20181029 start**/
		String cashId = null;
		if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
		    cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
		        dto.getFinprodType(),DictCodeListExt1.现金流二级类型_利息.getValue());
		} else {
		    cashId = InterfHelper.getMd5CashId_(dto.getProdId(),dto.getFinprodType(),
                DictCodeListExt1.现金流二级类型_利息.getValue());
		}
	    valFinCashInfo.setCashId(cashId);
		/** modify by dongjun on 20181029 end**/
		//现金流一级类型=数据字典：现金流一级类型.利息
		valFinCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
		//现金流二级类型=数据字典：现金流二级类型.利息
		valFinCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
		//金融产品代码=C.产品代码
		valFinCashInfo.setFinprodId(dto.getProdId());
		//金融产品类型=C.金融产品类型
		valFinCashInfo.setFinprodType(dto.getFinprodType());//modify by dongjun on 20181023
		//分支序号=设置全局变量(默认为0）
	    /** modify by dongjun on 20181029 start**/
		Long branch = null;
		if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
		    branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
		} else {
		    branch = InterfCommonConstant.分支序号_默认;
		}
	    valFinCashInfo.setBranch(branch);
		/** modify by dongjun on 20181029 end**/
		return valFinCashInfo;
	}
}
