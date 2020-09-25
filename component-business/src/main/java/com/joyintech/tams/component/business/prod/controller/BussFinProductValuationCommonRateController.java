/*
 * 文件名：BussFinProductValuationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.PrdValParam;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductValuationService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductValuationDto;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * @author 何阳阳
 * @version 1.0 2018年9月16日
 * @see BussFinProductValuationCommonRateController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductValuationCommonRate")
public class BussFinProductValuationCommonRateController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BussFinProductValuationService valuationInfoService;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.是否, DictCode.尾数处理方式,DictCode.亏损处理方式});
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        model.addAttribute("MANTISSA_PROCESS_MODE_01",
            DictCodeList.尾数处理方式_四舍五入.getValue());
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        model.addAttribute("profitTypeElement", this.getParam("profitTypeElement"));
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductValuationService.DEFAULT_ENTITY_NAME));
        model.addAttribute(GlobalConstant.DETAIL_PAGE_MODEL_KEY, this.getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY));
        return "component/prod/BussFinProductValuationCommonRate";
    }

    /**
     * 设置估值信息
     * 
     * @param model {@link Model}
     */
    private void setEntity(Model model) {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductValuationService.DEFAULT_ENTITY_NAME);
        if (!WorkFlowUtil.isNotFromToDoList()) {
            BussFinProductValuationDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workflowId, entityName, BussFinProductValuationDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else {
            if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
                if (null != valuationInfoService) {
                    PrdValParam param = valuationInfoService.getByProductId(this.getParam("finprodId"));
                    model.addAttribute(GlobalConstant.ENTITY, param);
                }
            }
        }
    }
    
    /**
     * 根据金融产品代码查询估值信息
     * 
     * @param finprodId 金融产品代码
     * @return 估值信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryValParamByFinprodId")
    public PrdValParam queryValParamByFinprodId(String finprodId){
        return valuationInfoService.getByProductId(finprodId);
    }
}
