/*
 * 文件名：CPrdOpenPlanTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdOpenPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 *预期收益型产品开放计划表(Val_PRD_OPEN_PLAN) 〈一句话功能简述〉 〈功能详细描述〉〉
 * @author lishicheng
 * @version 1.0 2018年10月8日
 * @see YPrdOpenPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdOpenPlanTemplateRecService {
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
		List<ValPrdOpenPlan> listValPrdOpenPlan = new ArrayList<ValPrdOpenPlan>();
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
	    returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValPrdOpenPlan = returnResult.getT(ValPrdOpenPlan.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		List<FinOpenPlan> listValOpenPlan = handleValPrdOpenPlan(listValPrdOpenPlan);
		result.setListValOpenPlan(listValOpenPlan);

		// 添加日志
		result.setValInterflog(returnResult.getValInterflog());
		// 返回处理结果
		return result;
	}

	/**
	 * 预期收益型产品开放计划表<一句话功能简述>
	 *  <功能详细描述>
	 * @param listValPrdOpenPlan
	 * @return
	 */
	private List<FinOpenPlan> handleValPrdOpenPlan(List<ValPrdOpenPlan> listValPrdOpenPlan) {
		List<FinOpenPlan> listValOpenPlan = new ArrayList<FinOpenPlan>();
		for (ValPrdOpenPlan dto : listValPrdOpenPlan) {
			// 开放计划表
			FinOpenPlan valOpenPlan = this.getValOpenPlan(dto);
			listValOpenPlan.add(valOpenPlan);
		}
		return listValOpenPlan;
	}

	/**
	 * 预期收益型产品开放计划 接收 <一句话功能简述>
	 *  <功能详细描述>
	 * @param dto
	 * @return
	 */
	private FinOpenPlan getValOpenPlan(ValPrdOpenPlan dto) {
		// 开放计划表
		FinOpenPlan valOpenPlan = new FinOpenPlan();
		// 金融产品代码 = B.产品代码
		/** modify by dongjun on 20181029 start **/
		String prodId = null;
		if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
		    prodId = dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate());
		} else {
		    prodId = dto.getProdId();
		}
		/** modify by yanya on 20210224 增加现金流ID生成 start**/
		String cashId = InterfHelper.getMd5CashId_(prodId,DictCodeListExt1.现金流二级类型_本金.getValue());
		/** modify by yanya on 20210224 增加现金流ID生成 end**/
		valOpenPlan.setFinprodId(prodId);
	    /** modify by dongjun on 20181029 end **/
		// 开放周期起始日 =B.开始周期起始日
		valOpenPlan.setOpenDateStr(dto.getOpenDateStr());
		// 开放周期结束日 = B.开放周期结束日
		valOpenPlan.setOpenDateEnd(dto.getOpenDateEnd());
		// 开放周期起息日 = B.开放周期起息日
		valOpenPlan.setOpenVdate(dto.getOpenVdate());

		/**ADD BY WUJIE ON 20210430 START**/
		valOpenPlan.setOpenDateStrUn(dto.getOpenDateStr());
		valOpenPlan.setOpenDateEndUn(dto.getOpenDateEnd());
		/**ADD BY WUJIE ON 20210430 END**/
		
		
		//TODO  估值系统表结构改动 
//		开放计划表-->开放计划（FIN_VAL_OPEN_PLAN---FIN_OPEN_PLAN）
		/** modify by yanya on 20210224 增加现金流ID、开放类型、募集结束日 start**/
		//valOpenPlan.setCashId("");
		//valOpenPlan.setOpenType("");
		valOpenPlan.setCashId(cashId);
		valOpenPlan.setOpenType("03");
		valOpenPlan.setOpenDateEndUn(dto.getProdVdate());
		/** modify by yanya on 20210224 增加现金流ID、开放类型、募集结束日 start**/
		return valOpenPlan;
	}
}
