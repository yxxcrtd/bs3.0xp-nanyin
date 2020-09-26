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
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeDetailN;

/**
 * 净值型产品浮动管理费计算明细规则表(Val_PRD_FLOAT_FEE_DETAIL_N) 〈功能详细描述〉
 * 
 * @author 吴德宝
 * @version 1.0 2018年6月22日
 * @see NPrdFloatFeeDetailTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdFloatFeeDetailTemplateRecService {

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
        List<ValPrdFloatFeeDetailN> listValPrdFloatFeeDetailN = new ArrayList<ValPrdFloatFeeDetailN>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdFloatFeeDetailN = returnResult.getT(ValPrdFloatFeeDetailN.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 业绩报酬计算规则明细List
        List<FinFloatCalcRuleDetail> listValFloatCalcRuleDetail = handleValPrdFloatFeeDetailN(
            listValPrdFloatFeeDetailN);
        result.setListValFloatCalcRuleDetail(listValFloatCalcRuleDetail);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 06-浮动管理费计算明细规则
     * 
     * @param listValPrdFloatFeeDetailN 净值型产品浮动管理费计算明细规则
     * @return 业绩报酬计算规则明细List
     * @see
     * @since 1.0
     */
    private List<FinFloatCalcRuleDetail> handleValPrdFloatFeeDetailN(List<ValPrdFloatFeeDetailN> listValPrdFloatFeeDetailN) {
        // 业绩报酬计算规则明细List
        List<FinFloatCalcRuleDetail> listValFloatCalcRuleDetail = new ArrayList<FinFloatCalcRuleDetail>();
        for (ValPrdFloatFeeDetailN dto : listValPrdFloatFeeDetailN) {
            // 业绩报酬计算规则明细
            FinFloatCalcRuleDetail valFloatCalcRuleDetail = this.getValFloatCalcRuleDetail(dto);
            listValFloatCalcRuleDetail.add(valFloatCalcRuleDetail);
        }
        return listValFloatCalcRuleDetail;
    }

    /**
     * 业绩报酬计算规则明细表 接收
     * 
     * @param dto 06-浮动管理费计算明细规则
     * @return 业绩报酬计算规则明细
     * @see
     * @since 1.0
     */
    private FinFloatCalcRuleDetail getValFloatCalcRuleDetail(ValPrdFloatFeeDetailN dto) {
        FinFloatCalcRuleDetail valFloatCalcRuleDetail = new FinFloatCalcRuleDetail();
        // 现金流代码 = F.产品代码+F.费用ID
        /**modify by wujie on 20190223 start
        valFloatCalcRuleDetail.setCashId(InterfHelper.getMd5CashId_(dto.getProdId(),dto.getFeeId()));
        **/
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }   
        valFloatCalcRuleDetail.setCashId(cashId);
        /**modify by wujie on 20190223 end**/
        
        // 生效日期 = F.生效日
        valFloatCalcRuleDetail.setEffDate(dto.getEffDate());
        // 层级 = F.业绩比较基准层级
        valFloatCalcRuleDetail.setLev(dto.getLev()==null?1L:dto.getLev());
        // 基准利率 = F.基准利率
//        valFloatCalcRuleDetail.setBasicRate(dto.getBasicRate());
        // 调整系数 = F.调整系数
        valFloatCalcRuleDetail.setCoefficient(dto.getCoefficient());
        // 利差 = F.利差
        valFloatCalcRuleDetail.setSpreadRate(dto.getSpreadRate());
        // 银行收取比例 = F.银行收取比例
        valFloatCalcRuleDetail.setChargeRatio(dto.getChargeRatio());
        // 研发部门收取比例 = F.研发部分收取比例
        valFloatCalcRuleDetail.setSaleRatio(dto.getSaleRatio());
        return valFloatCalcRuleDetail;
    }

}
