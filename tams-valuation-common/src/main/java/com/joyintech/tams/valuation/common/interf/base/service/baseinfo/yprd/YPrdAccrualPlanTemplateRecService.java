/*
 * 文件名：YPrdRatePlanTemplateRecService.java
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
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdAccrualPlan;
/**
 * 
 * 〈一句话功能简述〉预期收益型产品付息计划（VAL_PRD_ACCRUAL_PLAN）〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月8日
 * @see YPrdAccrualPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdAccrualPlanTemplateRecService {
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
		List<ValPrdAccrualPlan> listValPrdAccrualPlan = new ArrayList<ValPrdAccrualPlan>();
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构		
		listValPrdAccrualPlan = returnResult.getT(ValPrdAccrualPlan.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		List<FinCashPlan> listValCashPlan = handleValPrdAccrualPlan(listValPrdAccrualPlan);
		result.setListValCashPlan(listValCashPlan);

		// 添加日志
		result.setValInterflog(returnResult.getValInterflog());
		// 返回处理结果
		return result;
	}
    /**
     * 产品付息计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValPrdAccrualPlan
     * @return
     */
	private List<FinCashPlan> handleValPrdAccrualPlan(List<ValPrdAccrualPlan> listValPrdAccrualPlan) {
		List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
		for(ValPrdAccrualPlan dto :listValPrdAccrualPlan){
			// 现金流计划表
			FinCashPlan valCashPlan = this.getValCashPlan(dto);
			listValCashPlan.add(valCashPlan);
		}
		return listValCashPlan;
	}
	/**
	 * 预期收益型产品现金流计划表
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinCashPlan
	 */
	
	private FinCashPlan getValCashPlan(ValPrdAccrualPlan dto) {
		FinCashPlan valFinCashPlan = new FinCashPlan();
		//现金流代码=InterfHelper.getMd5CashId_（D.产品代码,D.金融产品类型,数据字典：现金流二级类型.利息)
		/** modify  by dongjun on 20181029 start**/
		String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFinprodType(), DictCodeListExt1.现金流二级类型_利息.getValue());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId(),dto.getFinprodType(),
                DictCodeListExt1.现金流二级类型_利息.getValue());
        }
        valFinCashPlan.setCashId(cashId);
		/** modify  by dongjun on 20181029 end**/                                                                                                                                                                                      
		//现金流序号=D.现金流序号
		valFinCashPlan.setCashNum(dto.getOrderNo().longValue());
		//调整后的计算开始日=D.计息起始日
		valFinCashPlan.setVdate(dto.getIntStrDate());
		//调整后的计算结束日=D.计息结束日
		valFinCashPlan.setMdate(dto.getIntEndDate());
		//调整后的计划支付日=D.支付日
		valFinCashPlan.setPayDate(dto.getPayDate());
		//计算周期天数=D.计息结束日-D.计息起始日
		valFinCashPlan.setTermdays((dto.getIntEndDate().getTime()-dto.getIntStrDate().getTime())/(24*60*60*1000));
		//现金流单位=数据字典：现金流单位.总额
		valFinCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
		//金融产品代码=D.产品代码
		valFinCashPlan.setFinprodId(dto.getProdId());
		//金融产品类型=数据字典：金融产品类型.理财产品
		valFinCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
		//分支序号=设置全局变量(默认为0）
		/** modify  by dongjun on 20181029 start**/
		Long branch = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
        } else {
            branch = InterfCommonConstant.分支序号_默认;
        }
		valFinCashPlan.setBranch(branch);
		/** modify  by dongjun on 20181029 end**/
		//支付方式=数据字典：支付方式.现金
		valFinCashPlan.setPayType(DictCodeList.支付方式_现金.getValue());
		return valFinCashPlan;
	}
}
