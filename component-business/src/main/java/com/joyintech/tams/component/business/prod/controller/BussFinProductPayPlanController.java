/*
 * 文件名：BussFinProductPayPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductOpenPlanService;
import com.joyintech.tams.component.business.prod.BussFinProductPayPlanService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 付息计划共通
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月17日
 * @see BussFinProductPayPlanController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductPayPlan")
//一体化对应etliu
@SubPageId("BussFinProductPayPlan")
public class BussFinProductPayPlanController extends BaseController {

    /**
     * 付息计划组件
     */
    @Autowired(required = false)
    private BussFinProductPayPlanService bussFinProductPayPlanService;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@linkplain}
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 初始化开放计划组件的画面元素
     // 一体化对应etliu start
//        setSelectOptionToModel(model, new DictCode[] {DictCode.分红方式,
//            DictCode.付息周期, DictCode.周期单位, DictCode.营业日准则});
        setSelectOptionToModel(model, new DictCode[] {DictCode.分红方式,
            DictCode.付息周期, DictCode.频率, DictCode.营业日准则});
     // 一体化对应etliu end

        // 设置画面常亮值，用于JS中的判断
        model.addAttribute("INTPAY_PERIOD_01", DictCodeListExt1.付息周期_到期支付.getValue());
        model.addAttribute("INTPAY_PERIOD_02", DictCodeListExt1.付息周期_周期支付.getValue());
        
        model.addAttribute("INTPAY_PERIOD_03", DictCodeListExt1.付息周期_申购同频.getValue());

        
     // 一体化对应etliu start
//        model.addAttribute("PERIOD_UNIT_01", DictCodeListExt1.周期单位_日.getValue());
        model.addAttribute("PERIOD_UNIT_01", DictCodeList.频率_日.getValue());
     // 一体化对应etliu end
        
     // 一体化对应etliu
//        model.addAttribute("CALC_METHOD_01", DictCodeList.推算方法_起息日向后.getValue());
//        model.addAttribute("CALC_METHOD_04", DictCodeList.推算方法_首次付息日.getValue());
        model.addAttribute("CALC_METHOD_01", DictCodeList.推算方式_起息日向后.getValue());
        model.addAttribute("CALC_METHOD_04", DictCodeList.推算方式_首次支付日.getValue());
        
        model.addAttribute("TDAY_RULE_02", DictCodeList.营业日准则_向后调整.getValue());
        model.addAttribute("TDAY_RULE_04", DictCodeList.营业日准则_不调整.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());

        // 推算方法二次加工（只保留起息日向后和首次付息日）
     // 一体化对应etliu
//        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.推算方法, null);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.推算方式, null);
        
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
        	// 一体化对应etliu
//            if (StringUtil.equals(DictCodeList.推算方法_起息日向后.getValue(), dto.getId())
//                || StringUtil.equals(DictCodeList.推算方法_首次付息日.getValue(), dto.getId())) {
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
     // 一体化对应etliu start
//        selectDto = this.getSelectDataFromDataDic(DictCode.周期单位, null);
//        options = selectDto.getOptions();
//        optionsNew = new ArrayList<SelectOptionDto>();
//        for (SelectOptionDto dto : options) {
//            if (StringUtil.equals(DictCodeListExt1.周期单位_日.getValue(), dto.getId())
//                || StringUtil.equals(DictCodeListExt1.周期单位_月.getValue(), dto.getId())
//                || StringUtil.equals(DictCodeListExt1.周期单位_年.getValue(), dto.getId())) {
//                optionsNew.add(dto);
//            }
//        }
//        model.addAttribute(DictCode.周期单位.getValue(),
//            JsonUtils.objectArray2Json(optionsNew.toArray()));
        selectDto = this.getSelectDataFromDataDic(DictCode.频率, null);
        options = selectDto.getOptions();
        optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
            if (StringUtil.equals(DictCodeList.频率_日.getValue(), dto.getId())
            	|| StringUtil.equals(DictCodeList.频率_周.getValue(), dto.getId())	
                || StringUtil.equals(DictCodeList.频率_月.getValue(), dto.getId())
                || StringUtil.equals(DictCodeList.频率_季.getValue(), dto.getId())
                || StringUtil.equals(DictCodeList.频率_年.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        model.addAttribute(DictCode.频率.getValue(),
            JsonUtils.objectArray2Json(optionsNew.toArray()));
     // 一体化对应etliu end

        SelectDto selectDtoTs = this.getSelectDataFromDataDic(DictCode.营业日准则, null);
        List<SelectOptionDto> optionsTs = selectDtoTs.getOptions();
        List<SelectOptionDto> optionsNewTs = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : optionsTs) {
            if (StringUtil.equals(DictCodeList.营业日准则_不调整.getValue(), dto.getId())
            	|| StringUtil.equals(DictCodeList.营业日准则_向后调整.getValue(), dto.getId())) {
            	optionsNewTs.add(dto);
            }
        }
        model.addAttribute(DictCode.营业日准则.getValue(),
            JsonUtils.objectArray2Json(optionsNewTs.toArray()));
        
        /*
         * 收益类型
         */
        String profitType = StringUtil.defaultString(getParam("profitType"));
        model.addAttribute("profitType", profitType);

        /*
         * 实体名称（※必须），需要和主画面使用产品付息计划DTO时的字段名一致，默认为“productPayPlanDto”
         */
        model.addAttribute("entityName", StringUtil.defaultString(getParam("entityName"),
            BussFinProductPayPlanService.DEFAULT_ENTITY_NAME));
        /*
         * 调用方法（非必须），可选值为“product”和“module”，分别表示来自产品和模板调用，默认为“product”
         */
        String pageId = this.getPageId();
        model.addAttribute(GlobalConstant.PAGE_ID, pageId);

        // 如果来自产品
        if (StringUtil.equals(pageId, BussFinProductPayPlanService.DEFAULT_USE_PLACE)) {
            model.addAttribute(DictCode.分红方式.getValue() + "_default", StringUtil.EMPTY);
        }

        if (StringUtil.equals(pageId, BussFinProductPayPlanService.DEFAULT_USE_PLACE)) {
            /*
             * 起息日（※调用方法为产品时必须）
             */
            model.addAttribute("vdateElement", getParam("vdateElement"));
            /*
             * 到期日（※调用方法为产品时必须）
             */
            model.addAttribute("mdateElement", getParam("mdateElement"));
            /*
             * 交易日历代码（※调用方法为产品时必须）
             */
            model.addAttribute("calendarIdElement", getParam("calendarIdElement"));
        }
        // 设置画面各输入框的值
        setEntity(model);

        return "component/prod/BussFinProductPayPlan";
    }

    /**
     * 设置估值信息
     * 
     * @param model {@link Model}
     */
    private void setEntity(Model model) {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductOpenPlanService.DEFAULT_ENTITY_NAME);
        if (!WorkFlowUtil.isNotFromToDoList()) {
            BussFinProductPayPlanDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workflowId, entityName, BussFinProductPayPlanDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
        }
        else {
            // 修改或查看模式
            if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
                // 金融产品代码
                String finprodId = this.getParam("finprodId");
                String effDate = this.getParam("effDate");
                if (StringUtil.isNotEmpty(finprodId) && null != bussFinProductPayPlanService) {
                    model.addAttribute(GlobalConstant.ENTITY,
                        bussFinProductPayPlanService.queryPayPlanInfoByFinprodId(finprodId,
                            effDate));
                    model.addAttribute("finprodId", finprodId);
                }
            }
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
     * 预览按钮点击时的验证
     * 
     * @param previewParam 预览参数
     * @param result BindingResult
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid BussFinProductPayPlanDto previewParam, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        // 验证首次付息日是否节假日
        AjaxResult ar = new AjaxResult();
        if (StringUtil.isNotEmpty(previewParam.getFirstPayDate())) {
            if (dateCalService.isDealCalendarHoliday(previewParam.getFirstPayDate(),
                previewParam.getCalendarId())) {
                ar.setData(this.getMessage("BUSS_COM_E_0039",
                    this.getMessage("BussFinProductPayPlan.firstPayDate")));
            }
        }

        return ar;
    }

    /**
     * 付息计划生成
     * 
     * @param previewParam {@link BussFinProductPayPlanDto}
     * @return 付息计划列表
     */
    @ResponseBody
    @RequestMapping("calcProductInterestPlan")
    public List<FinCashPlan> preview(BussFinProductPayPlanDto previewParam) {
        if (StringUtil.isNotEmpty(this.getParam("workflowId"))
            || StringUtil.isNotEmpty(this.getParam("finprodId"))) {
            if (StringUtil.isNotEmpty(this.getParam("workflowId"))
                && !StringUtil.equals(this.getParam("quickFlag"), "true")) {
                BussFinProductPayPlanDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                    this.getParam("workflowId"), this.getParam("entityName"),
                    BussFinProductPayPlanDto.class);
                if (dto != null && !StringUtil.isEmptyList(dto.getCashPlanList())) {
                    return dto.getCashPlanList();
                }
            }
            else if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
                return bussFinProductPayPlanService.queryPayPlanListByFinprodId(
                    this.getParam("finprodId"));
            }
        }
        else {
            if (null != bussFinProductPayPlanService) {
                return bussFinProductPayPlanService.getProductInterestPlanData(previewParam);
            }
        }

        return Collections.emptyList();
    }

    /**
     * 根据金融产品代码查询付息信息
     * 
     * @param finprodId 金融产品代码
     * @return 付息计划信息数据
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryPayPlanInfoByFinprodId")
    public BussFinProductPayPlanDto queryPayPlanInfoByFinprodId(String finprodId) {
        return bussFinProductPayPlanService.queryPayPlanInfoByFinprodId(finprodId, null);
    }
}
