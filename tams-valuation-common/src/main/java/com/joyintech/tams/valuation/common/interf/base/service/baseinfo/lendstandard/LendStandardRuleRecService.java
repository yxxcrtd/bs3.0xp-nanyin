/*
 * 文件名：LendStandardRuleRecService.java
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

import com.joyintech.tams.common.base.entity.FinCashPayRule;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlTargetRateRule;

/**
 * 拆借付息规则(VAL_DL_TARGET_RATE_RULE) 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年8月25日
 * @see LendStandardRuleRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendStandardRuleRecService {
	/**
	 * TemplateAnalysisForXMLService
	 */
	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LendStandardCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		LendStandardCommonResult result = new LendStandardCommonResult();
		List<ValDlTargetRateRule> listValDlTargetRateRule = new ArrayList<ValDlTargetRateRule>();
		// 解析模板后的list以及日志list
	    TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValDlTargetRateRule =returnResult.getT(ValDlTargetRateRule.class);
        
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        //现金流支付规则
        List<FinCashPayRule> listValCashPayRule = handleValDlTargetRateRule(listValDlTargetRateRule);
        result.setListValCashPayRule(listValCashPayRule);
        
        
        //添加日志
        result.setValInterflog(returnResult.getValInterflog());
		return result;
	}
    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValDlTargetRateRule
     * @return 现金流支付规则list
     */
	private List<FinCashPayRule> handleValDlTargetRateRule(List<ValDlTargetRateRule> listValDlTargetRateRule) {
		//现金流支付规则list
		List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
		for(ValDlTargetRateRule dto : listValDlTargetRateRule){
			//现金流支付规则
			FinCashPayRule valCashPayRule = this.getValCashPayRule(dto);
			listValCashPayRule.add(valCashPayRule);
		}
		return listValCashPayRule;
	}
	/**
	 * 现金流支付规则表 接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinCashPayRule
	 */
	private FinCashPayRule getValCashPayRule(ValDlTargetRateRule dto) {
		FinCashPayRule valCashPayRule = new FinCashPayRule();
		if(DictCodeList.交易方向_收息.getValue().equals(dto.getDealType())){
			//现金流代码=InterfHelper.getMd5CashId_(B.拆借标的代码,B.业务类型,数据字典：现金流二级类型.利息）
			valCashPayRule.setCashId(InterfHelper.getMd5CashId_(dto.getDlId(),dto.getBusiType(),DictCodeListExt1.现金流二级类型_利息.getValue()));
		}else{
			//现金流代码=InterfHelper.getMd5CashId_(B.拆借标的代码,B.业务类型,数据字典：现金流二级类型.本金）
			valCashPayRule.setCashId(InterfHelper.getMd5CashId_(dto.getDlId(),dto.getBusiType(),DictCodeListExt1.现金流二级类型_本金.getValue()));
		}
		//生效日期 =B.生效日
		valCashPayRule.setEffDate(dto.getEffDate());
		//支付频率 =B.付息频率
		valCashPayRule.setPayCycle(dto.getPayCycle());
		//营业日准则 =B.营业日准则
//		valCashPayRule.setTdayRule(dto.getTdayRule());
		//推算方式 =B.推算方式
		valCashPayRule.setCalcRule(dto.getCalcRule());
		//首次支付日=B.首次付息日
		valCashPayRule.setFirstPayDate(dto.getFirstPayDate());
		//现金流单位=数据字典：现金流单位.总额
		valCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
		//金融产品代码=B.拆借标的代码
		valCashPayRule.setFinprodId(dto.getDlId());
		//金融产品类型=B.业务类型
		valCashPayRule.setFinprodType(dto.getBusiType());
		//支付方式=数据字典：支付方式.现金
		valCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
		//分支序号=设置全局变量(默认为0）
		valCashPayRule.setBranch(InterfCommonConstant.分支序号_默认);
		return valCashPayRule;
	}

}
