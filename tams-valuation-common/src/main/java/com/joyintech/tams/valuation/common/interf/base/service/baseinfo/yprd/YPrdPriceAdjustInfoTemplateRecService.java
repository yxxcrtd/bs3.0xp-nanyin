/*
 * 文件名：YPrdPriceAdjustInfoTemplateRecService.java
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
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPriceAdjustInfo;

/**
 * 预期收益型产品产品报价调整信息
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月9日
 * @see YPrdPriceAdjustInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdPriceAdjustInfoTemplateRecService {
	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public YPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		YPrdBaseInfoValCommonResult result = new YPrdBaseInfoValCommonResult();
		List<ValPrdPriceAdjustInfo> listValPrdPriceAdjustInfo = new ArrayList<ValPrdPriceAdjustInfo>();
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
	    returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValPrdPriceAdjustInfo = returnResult.getT(ValPrdPriceAdjustInfo.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		List<FinCashCalcRule> listValCashCalcRule = handleValPrdPriceAdjustInfo(listValPrdPriceAdjustInfo);
		result.setListValCashCalcRule(listValCashCalcRule);

		// 添加日志
		result.setValInterflog(returnResult.getValInterflog());
		// 返回处理结果
		return result;
	}
    /**
     * 现金流计算规则
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValPrdPriceAdjustInfo
     * @return  listValCashCalcRule
     */
	private List<FinCashCalcRule> handleValPrdPriceAdjustInfo(List<ValPrdPriceAdjustInfo> listValPrdPriceAdjustInfo) {
		List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
		for(ValPrdPriceAdjustInfo dto :listValPrdPriceAdjustInfo){
		 //现金流计算规则
		  FinCashCalcRule valFinCashCalcRule  =	this.getValPrdPriceAdjust(dto);
		  listValCashCalcRule.add(valFinCashCalcRule);
		}
		return listValCashCalcRule;
	}
	/**
	 * 现金流计算规则接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinCashCalcRule
	 */
	private FinCashCalcRule getValPrdPriceAdjust(ValPrdPriceAdjustInfo dto) {
		FinCashCalcRule valFinCashCalcRule = new FinCashCalcRule();
		//现金流代码=InterfHelper.getMd5CashId_（K.产品代码,K.金融产品类型,数据字典：现金流二级类型.利息)
		/** modify  by dongjun on 20181029 start**/
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFinprodType(), DictCodeListExt1.现金流二级类型_利息.getValue());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId(),dto.getFinprodType()
                ,DictCodeListExt1.现金流二级类型_利息.getValue());
        }   
		valFinCashCalcRule.setCashId(cashId);
		/** modify  by dongjun on 20181029 end**/
		//生效日期=K.生效日
		valFinCashCalcRule.setEffDate(dto.getEffDate());
		//计算类型=K.利率类型
		valFinCashCalcRule.setCalcType(dto.getIntType());//modify by dongjun on 20181023
		//生效利率=K.收益率
		valFinCashCalcRule.setYield(dto.getPrtRate());
		//计算基础=K.计息基础
		valFinCashCalcRule.setBasis(dto.getBasis());
		//金融产品代码=K.产品代码
		valFinCashCalcRule.setFinprodId(dto.getProdId());
		//金融产品类型=K.金融产品类型
		valFinCashCalcRule.setFinprodType(dto.getFinprodType());//modify by dongjun on 20181023
		//分支序号=设置全局变量(默认为0）
		/** modify  by dongjun on 20181029 start**/
        Long branch = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
        } else {
            branch = InterfCommonConstant.分支序号_默认;
        }   
		valFinCashCalcRule.setBranch(branch);
		/** modify  by dongjun on 20181029 end**/
		return valFinCashCalcRule;
	}
}
