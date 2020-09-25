/*
 * 文件名：BussFinProductBonusPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈卓
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductBonusPlanService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductBonusPlanDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 分红计划共通
 * 
 * @author 陈卓
 * @version 1.0 2018年9月17日
 * @see BussFinProductBonusPlanController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductBonusPlan")
//一体化对应etliu
@SubPageId("BussFinProductBonusPlan")
public class BussFinProductBonusPlanController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductBonusPlanService bussFinProductBonusPlanService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 初始化开放计划组件的画面元素
        setSelectOptionToModel(model, new DictCode[] {DictCode.分红方式,
            DictCode.分红规则, DictCode.周期单位, DictCode.营业日准则});
        model.addAttribute("BONUS_RULE_01", DictCodeList.分红规则_到期支付.getValue());
        model.addAttribute("BONUS_RULE_02", DictCodeList.分红规则_周期支付.getValue());
        model.addAttribute("BONUS_RULE_03", DictCodeList.分红规则_日付.getValue());
        model.addAttribute("BONUS_RULE_04", DictCodeList.分红规则_申购同频.getValue());
        model.addAttribute("BONUS_RULE_05", DictCodeList.分红规则_不规则.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("BONUS_MODE_01", DictCodeList.分红方式_现金分红.getValue());
        
     // 一体化对应etliu
//        model.addAttribute("CAL_RULE_05", DictCodeList.推算方法_首次分红日.getValue());
//        model.addAttribute("CAL_RULE_01", DictCodeList.推算方法_起息日向后.getValue());
        model.addAttribute("CAL_RULE_05", DictCodeList.推算方式_首次支付日.getValue());
        model.addAttribute("CAL_RULE_01", DictCodeList.推算方式_起息日向后.getValue());
        
        model.addAttribute("TDAY_RULE_04", DictCodeList.营业日准则_不调整.getValue());
        model.addAttribute("TDAY_RULE_02", DictCodeList.营业日准则_向后调整.getValue());
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductBonusPlanService.DEFAULT_ENTITY_NAME));
        // 父画面处理模式Id
        model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        // 父画面pageId
        model.addAttribute(GlobalConstant.PAGE_ID, StringUtil.defaultString(this.getParam(GlobalConstant.PAGE_ID)));
        // 推算方法二次加工（只保留起息日向后和首次分红日）
     // 一体化对应etliu
//        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.推算方法, null);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.推算方式, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
        	// 一体化对应etliu
//            if (StringUtil.equals(DictCodeList.推算方法_起息日向后.getValue(), dto.getId())
//                || StringUtil.equals(DictCodeList.推算方法_首次分红日.getValue(), dto.getId())) {
        	if (StringUtil.equals(DictCodeList.推算方式_起息日向后.getValue(), dto.getId())
                    || StringUtil.equals(DictCodeList.推算方式_首次支付日.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        
     // 一体化对应etliu
//        model.addAttribute(DictCode.推算方法.getValue(),
//            JsonUtils.objectArray2Json(optionsNew.toArray()));
        model.addAttribute(DictCode.推算方式.getValue(),
                JsonUtils.objectArray2Json(optionsNew.toArray()));
        
        // 周期单位二次加工(保留日月年)
        selectDto = this.getSelectDataFromDataDic(DictCode.周期单位, null);
        options = selectDto.getOptions();
        optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
            if (StringUtil.equals(DictCodeListExt1.周期单位_日.getValue(), dto.getId())
                || StringUtil.equals(DictCodeListExt1.周期单位_月.getValue(), dto.getId())
                || StringUtil.equals(DictCodeListExt1.周期单位_年.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        model.addAttribute(DictCode.周期单位.getValue(),
            JsonUtils.objectArray2Json(optionsNew.toArray()));
        // 设置画面各输入框的值
        setEntity(model);

        return "component/prod/BussFinProductBonusPlan";
    }

    /**
     * 设置分红计划信息
     * 
     * @param model {@link Model}
     */
    private void setEntity(Model model) {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductBonusPlanService.DEFAULT_ENTITY_NAME);
        if (!WorkFlowUtil.isNotFromToDoList()) {
            BussFinProductBonusPlanDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workflowId, entityName, BussFinProductBonusPlanDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
            model.addAttribute(GlobalConstant.ENTITY,
                bussFinProductBonusPlanService.queryBonusPlanInfoByFinprodId(
                    this.getParam("finprodId"), this.getParam("effDate")));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }
    
    /**
     * 根据金融产品代码查询分红信息
     * 
     * @param finprodId 金融产品代码
     * @return 分红信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryBonusPlanInfoByFinprodId")
    public BussFinProductBonusPlanDto queryBonusPlanInfoByFinprodId(String finprodId){
        return bussFinProductBonusPlanService.queryBonusPlanInfoByFinprodId(finprodId, null);
    }
}
