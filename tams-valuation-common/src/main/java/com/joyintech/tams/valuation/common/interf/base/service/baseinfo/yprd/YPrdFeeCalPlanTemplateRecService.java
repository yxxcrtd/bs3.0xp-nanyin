/*
 * 文件名：YPrdFeeCalPlanTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月9日
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
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeCalPlan;
/**
 * 预期收益型产品费用计提计划
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月9日
 * @see YPrdFeeCalPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdFeeCalPlanTemplateRecService {
	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public YPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		YPrdBaseInfoValCommonResult result = new YPrdBaseInfoValCommonResult();
		List<ValPrdFeeCalPlan> listValPrdFeeCalPlan = new ArrayList<ValPrdFeeCalPlan>();
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
	    returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValPrdFeeCalPlan = returnResult.getT(ValPrdFeeCalPlan.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		List<FinFloatAccrualSche> listValFinFloatAccrualSche = handleValPrdFeeCalPlan(listValPrdFeeCalPlan);
		result.setListFinFloatAccrualSche(listValFinFloatAccrualSche);

		// 添加日志
		result.setValInterflog(returnResult.getValInterflog());
		// 返回处理结果
		return result;
	}
   /**
    * 费用计提计划
    * <一句话功能简述>
    * <功能详细描述>
    * @param listValPrdFeeCalPlan
    * @return 
    */
	private List<FinFloatAccrualSche> handleValPrdFeeCalPlan(List<ValPrdFeeCalPlan> listValPrdFeeCalPlan) {
		List<FinFloatAccrualSche> listFinFloatAccrualSche =new ArrayList<FinFloatAccrualSche>();
		for(ValPrdFeeCalPlan dto :listValPrdFeeCalPlan){
		//业绩报酬计提计划
		 FinFloatAccrualSche  valFinFloatAccrualSche =	 this.getFinFloatAccrualSche(dto);
		 listFinFloatAccrualSche.add(valFinFloatAccrualSche);
		}
		return listFinFloatAccrualSche;
	}
	
	
	/**
	 * 业绩报酬计提计划接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return
	 */
   private FinFloatAccrualSche getFinFloatAccrualSche(ValPrdFeeCalPlan dto) {
	   FinFloatAccrualSche valFinFloatAccrualSche =new FinFloatAccrualSche();
	   //现金流代码=InterfHelper.getMd5CashId_(H.产品代码,H.费用ID)
	   /** modify  by dongjun on 20181029 start**/
       String cashId = null;
       if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
           cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
               dto.getFeeId());
       } else {
           cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
       }   
	   valFinFloatAccrualSche.setCashId(cashId);
	   /** modify  by dongjun on 20181029 end**/
	   //计提日=H.计提日
	   valFinFloatAccrualSche.setAccrDate(dto.getAccrDate());
	   //区间开始日=H.区间开始日
	   valFinFloatAccrualSche.setAccrStrDate(dto.getVdate());
	   //区间结束日=H.区间结束日
	   valFinFloatAccrualSche.setAccrEndDate(dto.getMdate());
	  return valFinFloatAccrualSche;
}
}
