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
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdOpenPlanN;
import com.joyintech.framework.util.StringUtil;

/**
 * 净值型产品开放计划表(Val_PRD_OPEN_PLAN_N) 〈功能详细描述〉
 * 
 * @author 吴德宝
 * @version 1.0 2018年6月22日
 * @see NPrdOpenPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdOpenPlanTemplateRecService {

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
        List<ValPrdOpenPlanN> listValPrdOpenPlanN = new ArrayList<ValPrdOpenPlanN>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdOpenPlanN = returnResult.getT(ValPrdOpenPlanN.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        List<FinOpenPlan> listValOpenPlan = handleValPrdOpenPlanN(listValPrdOpenPlanN);
        result.setListValOpenPlan(listValOpenPlan);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果
        return result;
    }

    /**
     * 03-开放计划
     * 
     * @param listValPrdOpenPlanNDto 净值型产品开放计划表
     * @return 开放计划List
     * @see
     * @since 1.0
     */
    private List<FinOpenPlan> handleValPrdOpenPlanN(List<ValPrdOpenPlanN> listValPrdOpenPlanNDto) {
        List<FinOpenPlan> listValOpenPlan = new ArrayList<FinOpenPlan>();
        for (ValPrdOpenPlanN dto : listValPrdOpenPlanNDto) {
            // 开放计划表
            FinOpenPlan valOpenPlan = this.getValOpenPlan(dto);
            listValOpenPlan.add(valOpenPlan);
        }
        return listValOpenPlan;
    }

    /**
     * 开放计划表 接收
     * 
     * @param dto 03-开放计划
     * @return 开放计划表
     * @see
     * @since 1.0
     */
    private FinOpenPlan getValOpenPlan(ValPrdOpenPlanN dto) {
        // 开放计划表
        FinOpenPlan valOpenPlan = new FinOpenPlan();
        String prodId = null;
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            prodId = dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate());
            cashId = InterfHelper.getMd5CashId_(prodId+new SimpleDateFormat("yyyyMMdd").format(dto.getOpenVdate()),DictCodeListExt1.现金流二级类型_本金.getValue());
        } else {
            prodId = dto.getProdId();
            cashId = InterfHelper.getMd5CashId_(prodId,DictCodeListExt1.现金流二级类型_本金.getValue());
        }
        // 金融产品代码 = C.产品代码
        valOpenPlan.setFinprodId(prodId);
        // 开放周期起始日 = C.开始周期起始日
        valOpenPlan.setOpenDateStr(dto.getOpenDateStr());
        // 开放周期结束日 = C.开放周期结束日
        valOpenPlan.setOpenDateEnd(dto.getOpenDateEnd());
        // 开放周期起息日 = C.开放周期起息日
        valOpenPlan.setOpenVdate(dto.getOpenVdate());

        /**ADD BY WUJIE ON 20210430 START**/
        valOpenPlan.setOpenDateStrUn(dto.getOpenDateStr());
        valOpenPlan.setOpenDateEndUn(dto.getOpenDateEnd());
        /**ADD BY WUJIE ON 20210430 END**/
        
      //TODO  估值系统表结构改动 
//		开放计划表-->开放计划（FIN_VAL_OPEN_PLAN---FIN_OPEN_PLAN）
        valOpenPlan.setCashId(cashId);
        valOpenPlan.setOpenType("03");
        /** modify by yanya on 20210224 增加募集结束日 start**/
        valOpenPlan.setOpenDateEndUn(dto.getProdVdate());
        /** modify by yanya on 20210224 增加募集结束日 end**/
        return valOpenPlan;
    }
}
