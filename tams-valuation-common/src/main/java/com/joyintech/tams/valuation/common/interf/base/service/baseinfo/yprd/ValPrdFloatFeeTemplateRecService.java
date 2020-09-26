/*
 * 文件名：CPrdFloatFeeTemplateRecService.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFee;
/**
 * 预期收益型产品浮动管理费计提规则表(VAL_PRD_FLOAT_FEE)
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年10月09日
 * @see ValPrdFloatFeeTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValPrdFloatFeeTemplateRecService {
	
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public YPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
    	YPrdBaseInfoValCommonResult result = new YPrdBaseInfoValCommonResult();
        List<ValPrdFloatFee> listValPrdFloatFee = new ArrayList<ValPrdFloatFee>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdFloatFee = returnResult.getT(ValPrdFloatFee.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValPrdFloatFee(listValPrdFloatFee);
        
        // 业绩报酬计提规则
        List<FinFloatCountingRule> listValFloatCountingRule = (List<FinFloatCountingRule>)map.get(
            "ValFloatCountingRule");
        result.setListValFloatCountingRule(listValFloatCountingRule);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }
    /**
     * 06-浮动管理费计提规则
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValPrdFloatFeeC
     * @return  业绩报酬计提规则List + 业绩报酬计算规则 List
     */
	private Map<String, Object> handleValPrdFloatFee(List<ValPrdFloatFee> listValPrdFloatFee) {
		 Map<String, Object> resultMap = new HashMap<>();
	        // 业绩报酬计提规则List
	        List<FinFloatCountingRule> listValFloatCountingRule = new ArrayList<FinFloatCountingRule>();
	        for (ValPrdFloatFee dto : listValPrdFloatFee) {
	            // 业绩报酬计提规则
	            FinFloatCountingRule valFloatCountingRule = this.getValFloatCountingRule(dto);//modify by dongjun on 20181029
	            listValFloatCountingRule.add(valFloatCountingRule);
	        }
	        resultMap.put("ValFloatCountingRule", listValFloatCountingRule);
	        return resultMap;
	}
	/**
	 * 业绩报酬计提规则 接收
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return FinFloatCountingRule
	 */
	private FinFloatCountingRule getValFloatCountingRule(ValPrdFloatFee dto) {
		// 业绩报酬计提规则
        FinFloatCountingRule valFloatCountingRule = new FinFloatCountingRule();
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }   
        valFloatCountingRule.setCashId(cashId); 
        // 生效日期 = F.生效日
        valFloatCountingRule.setEffDate(dto.getEffDate());       
        // 计提类型 = F.计提类型
        valFloatCountingRule.setAccrType(dto.getDrawType());       
        //计提频率 = F.计提频率数值
        valFloatCountingRule.setAccrCycle(dto.getAccrCycleUnit());  
        // 计提频率单位 = F.计提频率
        valFloatCountingRule.setAccrCycleUnit(dto.getAccrCycle());
        // 计提频率类型 = F.计提频率类型
        valFloatCountingRule.setAccrDayType(dto.getAccrDayType());
        // 计提频率值 = F.计提频率值
        valFloatCountingRule.setAccrDayValue(String.valueOf(dto.getAccrDayValue()));
        // 计提营业日准则 = F.营业日准则
        valFloatCountingRule.setAccrTdayRule(dto.getTdayRule());
        // 首次计提日 = F.首次计提日
        valFloatCountingRule.setfAccrDate(dto.getfAccrDate());
        
        valFloatCountingRule.seteAccrDate(dto.geteAccrDate());
        
        return valFloatCountingRule;
	}	
}
