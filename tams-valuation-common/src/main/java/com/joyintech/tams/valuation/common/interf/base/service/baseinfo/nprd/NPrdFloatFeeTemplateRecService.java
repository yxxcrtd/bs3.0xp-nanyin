/*
 * 文件名：NPrdBaseinfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd;

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
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeN;

/**
 * 净值型产品浮动管理费计提规则表(Val_PRD_FLOAT_FEE_N) 〈功能详细描述〉
 * 
 * @author 吴德宝
 * @version 1.0 2018年6月22日
 * @see NPrdFloatFeeTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdFloatFeeTemplateRecService {

    /**
     * 净值型产品基本信息模板处理service <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public NPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        NPrdBaseInfoValCommonResult result = new NPrdBaseInfoValCommonResult();
        List<ValPrdFloatFeeN> listValPrdFloatFeeN = new ArrayList<ValPrdFloatFeeN>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdFloatFeeN = returnResult.getT(ValPrdFloatFeeN.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValPrdFloatFeeN(listValPrdFloatFeeN);

        // 业绩报酬计提规则List
        List<FinFloatCountingRule> listValFloatCountingRule = (List<FinFloatCountingRule>)map.get(
            "ValFloatCountingRule");
        result.setListValFloatCountingRule(listValFloatCountingRule);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    /**
     * 05-浮动管理费计提规则
     * 
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValPrdFloatFeeN(List<ValPrdFloatFeeN> listValPrdFloatFeeN) {
        Map<String, Object> resultMap = new HashMap<>();
        // 业绩报酬计提规则List
        List<FinFloatCountingRule> listValFloatCountingRule = new ArrayList<FinFloatCountingRule>();
        for (ValPrdFloatFeeN dto : listValPrdFloatFeeN) {
            // 业绩报酬计提规则
            FinFloatCountingRule valFloatCountingRule = this.getValFloatCountingRule(dto);
            listValFloatCountingRule.add(valFloatCountingRule);
        }
        resultMap.put("ValFloatCountingRule", listValFloatCountingRule);
        return resultMap;
    }
    /**
     * 业绩报酬计提规则表 接收
     * 
     * @param dto 05-浮动管理费计提规则
     * @return 业绩报酬计提规则
     * @see
     * @since 1.0
     */
    public FinFloatCountingRule getValFloatCountingRule(ValPrdFloatFeeN dto) {
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
        // 生效日期 = E.生效日
        valFloatCountingRule.setEffDate(dto.getEffDate());
        //计提频率 = E.计提频率数值
        valFloatCountingRule.setAccrCycle(dto.getAccrCycleUnit());
        // 计提频率单位 = E.计提频率
        valFloatCountingRule.setAccrCycleUnit(dto.getAccrCycle());
        // 计提频率类型 = E.计提频率类型
        valFloatCountingRule.setAccrDayType(dto.getAccrDayType());
        // 计提频率值 = E.计提频率值
        valFloatCountingRule.setAccrDayValue(String.valueOf(dto.getAccrDayValue()));
        // 计提营业日准则 = E.营业日准则
        valFloatCountingRule.setAccrTdayRule(dto.getTdayRule());
        // 首次计提日 = E.首次计提日
        valFloatCountingRule.setfAccrDate(dto.getfAccrDate());
        //计提类型 = E.计提类型
        valFloatCountingRule.setAccrType(dto.getDrawType());
        
        valFloatCountingRule.seteAccrDate(dto.geteAccrDate());
        
        return valFloatCountingRule;
    }
}
