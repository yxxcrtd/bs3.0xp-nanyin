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
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeCalPlanN;

/**
 * 净值型产品费用计提计划表 (Val_PRD_FEE_CAL_PLAN_N)- 模板处理 〈功能详细描述〉
 * 
 * @author 吴德宝
 * @version 1.0 2018年6月22日
 * @see NPrdFeeCalPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdFeeCalPlanTemplateRecService {

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
        List<ValPrdFeeCalPlanN> listValPrdFeeCalPlanN = new ArrayList<ValPrdFeeCalPlanN>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdFeeCalPlanN = returnResult.getT(ValPrdFeeCalPlanN.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 业绩报酬计提计划List
        List<FinFloatAccrualSche> listValPerfRewardAccrsche = handleValPrdFeeCalPlanN(listValPrdFeeCalPlanN);
        result.setListValPerfRewardAccrsche(listValPerfRewardAccrsche);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    /**
     * 08-费用计提计划
     * 
     * @param listValPrdFeeCalPlanN 净值型产品费用计提计划List
     * @return
     * @see
     * @since 1.0
     */
    private List<FinFloatAccrualSche> handleValPrdFeeCalPlanN(List<ValPrdFeeCalPlanN> listValPrdFeeCalPlanN) {
        // 业绩报酬计提计划List
        List<FinFloatAccrualSche> listValPerfRewardAccrsche = new ArrayList<FinFloatAccrualSche>();
        for (ValPrdFeeCalPlanN dto : listValPrdFeeCalPlanN) {
            // 业绩报酬计提计划
            FinFloatAccrualSche valPerfRewardAccrsche = this.getValPerfRewardAccrsche(dto);
            listValPerfRewardAccrsche.add(valPerfRewardAccrsche);
        }
        return listValPerfRewardAccrsche;
    }

    /**
     * 业绩报酬计提计划表 接收
     * 
     * @param dto 08-费用计提计划
     * @return 业绩报酬计提计划
     * @see
     * @since 1.0
     */
    private FinFloatAccrualSche getValPerfRewardAccrsche(ValPrdFeeCalPlanN dto) {
        // 业绩报酬计提计划
        FinFloatAccrualSche valPerfRewardAccrsche = new FinFloatAccrualSche();
        // 现金流代码 = H.产品代码+H.费用ID
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }   
        valPerfRewardAccrsche.setCashId(cashId);
        // 计提日 = H.计提日
        valPerfRewardAccrsche.setAccrDate(dto.getAccrDate());
        /**add by wujie on 20180825 start**/
        //区间开始日 = H.区间开始日
        valPerfRewardAccrsche.setAccrStrDate(dto.getVdate());
        //区间结束日 = H.区间结束日
        valPerfRewardAccrsche.setAccrEndDate(dto.getMdate());
        /**add by wujie on 20180825 end**/
        return valPerfRewardAccrsche;
    }

}
