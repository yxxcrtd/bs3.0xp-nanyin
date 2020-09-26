/*
 * 文件名：CPrdFloatFeeDetailTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月13日
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
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeDetail;


/**
 * 预期收益型产品浮动管理费计算明细规则表(VAL_PRD_FLOAT_FEE_DETAIL)
 * 
 * @author huwei
 * @version 1.0 2018年10月09日
 * @see ValPrdFloatFeeDetailTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValPrdFloatFeeDetailTemplateRecService {
	/**
	 * 预期型产品基础信息模板处理service <功能详细描述>
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
		List<ValPrdFloatFeeDetail> listValPrdFloatFeeDetail = new ArrayList<ValPrdFloatFeeDetail>();
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
	    returnResult = templateAnalysisForXMLService.execute(acctEntity);
		
	    // 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValPrdFloatFeeDetail = returnResult.getT(ValPrdFloatFeeDetail.class);
        
         // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 业绩报酬计算规则明细List
		List<FinFloatCalcRuleDetail> listValFloatCalcRuleDetail = handleValPrdFloatFeeDetail(
				listValPrdFloatFeeDetail);
		result.setListValFloatCalcRuleDetail(listValFloatCalcRuleDetail);
		// 添加日志
		result.setValInterflog(returnResult.getValInterflog());
		return result;

	}
   /**
    * 浮动管理费计算明细规则
    * <一句话功能简述>
    * <功能详细描述>
    * @param listValPrdFloatFeeDetailC 预期收益型产品浮动管理费计算明细规则表
    * @return 业绩报酬计算规则明细List
    */
	private List<FinFloatCalcRuleDetail> handleValPrdFloatFeeDetail(
			List<ValPrdFloatFeeDetail> listValPrdFloatFeeDetail) {
		 // 业绩报酬计算规则明细List
        List<FinFloatCalcRuleDetail> listValFloatCalcRuleDetail = new ArrayList<FinFloatCalcRuleDetail>();
        for (ValPrdFloatFeeDetail dto : listValPrdFloatFeeDetail) {
            // 业绩报酬计算规则明细
            FinFloatCalcRuleDetail valFloatCalcRuleDetail = this.getValFloatCalcRuleDetail(dto);
            listValFloatCalcRuleDetail.add(valFloatCalcRuleDetail);
        }
        return listValFloatCalcRuleDetail;
	}
	
	/**
	 * 业绩报酬计算规则明细表 接收
	 * <一句话功能简述>
	 * <功能详细描述> 
	 * @param dto   浮动管理费计算明细规则
	 * @return 业绩报酬计算规则明细表 接收
	 */
private FinFloatCalcRuleDetail getValFloatCalcRuleDetail(ValPrdFloatFeeDetail dto) {
	 FinFloatCalcRuleDetail valFloatCalcRuleDetail = new FinFloatCalcRuleDetail();
     // 现金流代码 = G.产品代码+G.费用ID
     /** modify  by dongjun on 20181029 start**/
     String cashId = null;
     if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
         cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
             dto.getFeeId());
     } else {
         cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
     }   
     valFloatCalcRuleDetail.setCashId(cashId);
     /** modify  by dongjun on 20181029 end**/
     // 生效日期 = G.生效日
     valFloatCalcRuleDetail.setEffDate(dto.getEffDate());
     // 层级 = G.业绩比较基准层级
     valFloatCalcRuleDetail.setLev(dto.getLev()==null?1L:dto.getLev());
     // 基准利率 = G.基准利率
//     valFloatCalcRuleDetail.setBasicRate(dto.getBasicRate());
     // 调整系数 = G.调整系数
     valFloatCalcRuleDetail.setCoefficient(dto.getCoefficient());
     // 利差 = G.利差
     valFloatCalcRuleDetail.setSpreadRate(dto.getSpreadRate());
     // 银行收取比例 = G.银行收取比例
     valFloatCalcRuleDetail.setChargeRatio(dto.getChargeRatio());
     // 研发部门收取比例 = G.研发部分收取比例
     valFloatCalcRuleDetail.setSaleRatio(dto.getSaleRatio());
     return valFloatCalcRuleDetail;
}

}
