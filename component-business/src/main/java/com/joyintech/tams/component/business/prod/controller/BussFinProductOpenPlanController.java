/*
 * 文件名：BussFinProductOpenPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductOpenPlanService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductOpenPlanDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 开放规则
 *
 * @author 张亮
 * @version 1.0 2018年9月17日
 * @see BussFinProductOpenPlanController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductOpenPlan")
//一体化对应etliu
@SubPageId("BussFinProductOpenPlan")
public class BussFinProductOpenPlanController extends BaseController {

    /**
     * 开放计划组件
     */
    @Autowired
    private BussFinProductOpenPlanService bussFinProductOpenPlanService;;

    /**
     * 平台日基础组件
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 初始化开放计划组件的画面元素
    	// 一体化对应etliu
//        setSelectOptionToModel(model,
//            new DictCode[] {DictCode.开放模式, DictCode.营业日准则,
//                DictCode.是否, DictCode.周期单位, DictCode.推算规则,
//                DictCode.星期号, DictCode.日期号, DictCode.工作日号});
    	setSelectOptionToModel(model,
                new DictCode[] {DictCode.开放模式, DictCode.营业日准则,
                    DictCode.是否, DictCode.周期单位, DictCode.推算方法,
                    DictCode.星期号, DictCode.日期号, DictCode.工作日号});

        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("OPENMODE_01", DictCodeList.开放模式_定期开放.getValue());
        model.addAttribute("OPENMODE_02", DictCodeList.开放模式_高频开放.getValue());
        model.addAttribute("OPENMODE_03", DictCodeList.开放模式_周期开放.getValue());
        model.addAttribute("DISPOSE_PATTERN_01", DictCodeList.处理模式_普通.getValue());
        model.addAttribute("DISPOSE_PATTERN_05", BussinessDictCodeList.处理模式_开放阶梯.getValue());
        model.addAttribute("DISPOSE_PATTERN_06", BussinessDictCodeList.处理模式_日添利.getValue());
        model.addAttribute("DISPOSE_PATTERN_07", BussinessDictCodeList.处理模式_智能日添利.getValue());
        model.addAttribute("DISPOSE_PATTERN_03", BussinessDictCodeList.处理模式_滚动N天.getValue());
        model.addAttribute("PERIOD_UNIT_01", DictCodeListExt1.周期单位_日.getValue());
        model.addAttribute("PERIOD_UNIT_02", DictCodeListExt1.周期单位_周.getValue());
        model.addAttribute("PERIOD_UNIT_03", DictCodeListExt1.周期单位_月.getValue());
        model.addAttribute("PERIOD_UNIT_04", DictCodeListExt1.周期单位_年.getValue());

     // 一体化对应etliu
//        model.addAttribute("CALC_METHOD_01", DictCodeList.推算方法_起息日向后.getValue());
//        model.addAttribute("CALC_METHOD_03", DictCodeList.推算方法_首次开放日.getValue());
        model.addAttribute("CALC_METHOD_01", DictCodeList.推算方式_起息日向后.getValue());
        model.addAttribute("CALC_METHOD_03", DictCodeList.推算方式_首次支付日.getValue());

        model.addAttribute("TDAY_RULE_02", DictCodeList.营业日准则_向后调整.getValue());
        model.addAttribute("platDate", platformDate.getSysDateStr());
        model.addAttribute("ISSUE_MODE_01", DictCodeList.开放类型_开放式.getValue());

        setEntity(model);

        // 父画面pageId
        model.addAttribute("pageId", StringUtil.defaultString(this.getParam("pageId")));
        // 父画面开放类型Id
        model.addAttribute("operationType",
            StringUtil.defaultString(this.getParam("operationType")));
        // 父画面处理模式Id
        model.addAttribute("dealMode", StringUtil.defaultString(this.getParam("dealMode")));
        // 父画面起息日
        model.addAttribute("vdate", StringUtil.defaultString(this.getParam("vdate")));
        // 父画面到期日
        model.addAttribute("mdate", StringUtil.defaultString(this.getParam("mdate")));
        // 父画面交易日历
        model.addAttribute("tradCalender",
            StringUtil.defaultString(this.getParam("tradCalender")));
        // 父画面募集开始日
        model.addAttribute("raiseStrDate",
            StringUtil.defaultString(this.getParam("raiseStrDate")));
        // 父画面募集结束日
        model.addAttribute("raiseEndDate",
            StringUtil.defaultString(this.getParam("raiseEndDate")));
        // 父画面申购确认天数
        model.addAttribute("purConfDays", StringUtil.defaultString(this.getParam("purConfDays")));
        // 父画面是否周期型
        model.addAttribute("isCycle", StringUtil.defaultString(this.getParam("isCycle")));
        // 父画面封闭期限
        model.addAttribute("closeTerm", StringUtil.defaultString(this.getParam("closeTerm")));

        model.addAttribute("bussFinProductOpenPlanDto",
            StringUtil.defaultString(this.getParam("bussFinProductOpenPlanDto"),
                BussFinProductOpenPlanService.DEFAULT_ENTITY_NAME));

        // 推算方法二次加工（只保留起息日向后和首次开放日）
     // 一体化对应etliu
//        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.推算方法, null);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.推算方式, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {

        	// 一体化对应etliu
//            if (StringUtil.equals(DictCodeList.推算方法_起息日向后.getValue(), dto.getId())
//                || StringUtil.equals(DictCodeList.推算方法_首次开放日.getValue(), dto.getId())) {
        	if (StringUtil.equals(DictCodeList.推算方式_起息日向后.getValue(), dto.getId())
                    || StringUtil.equals(DictCodeList.推算方式_首次支付日.getValue(), dto.getId())) {
        		// 一体化对应etliu
        		if (StringUtil.equals(DictCodeList.推算方式_首次支付日.getValue(), dto.getId())) {
        			dto.setText(DictCodeList.推算方式_首次支付日.getCodeName());
        		}

                optionsNew.add(dto);
            }
        }
     // 一体化对应etliu
//        model.addAttribute(DictCode.推算方法.getValue(),
//            JsonUtils.objectArray2Json(optionsNew.toArray()));
        model.addAttribute(DictCode.推算方式.getValue(),
                JsonUtils.objectArray2Json(optionsNew.toArray()));
        return "component/prod/BussFinProductOpenPlan";
    }

    /**
     * 设置开放计划信息
     *
     * @param model {@link Model}
     */
    private void setEntity(Model model) {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String entityName = StringUtil.defaultString(this.getParam("bussFinProductOpenPlanDto"),
            BussFinProductOpenPlanService.DEFAULT_ENTITY_NAME);
        if (!WorkFlowUtil.isNotFromToDoList()) {
            BussFinProductOpenPlanDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workflowId, entityName, BussFinProductOpenPlanDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("entityName", entityName);
            model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
            List<BussFinProductOpenPlanDto> list = bussFinProductOpenPlanService.queryOpenPlanInfoByFinprodId(
                this.getParam("finprodId"));
            model.addAttribute(GlobalConstant.ENTITY, StringUtil.isEmptyList(list)? null : list.get(0));
            model.addAttribute("finprodId", this.getParam("finprodId"));
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
     * 生成计划check
     *
     * @param dto 开放信息
     * @param result check结果
     * @return check信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkInputParam")
    public AjaxResult checkInputParam(@Valid @RequestBody BussFinProductOpenPlanDto dto,
                                      BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult wr = new AjaxResult();

        if (!StringUtil.isNotEmpty(dto.getDealMode())) {
            wr.appendMessage(new ValidMessage("dealMode",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductBuild002.dealMode"))));
        }
        if (StringUtil.equals(dto.getIsCycle(), DictCodeList.是否_是.getValue())) {
            if (!StringUtil.isNotEmpty(dto.getCloseTerm())) {
                wr.appendMessage(new ValidMessage("closeDays",
                    getMessage("BUSS_COM_E_0038", getMessage("FinProductBuild002.closeDays"))));
            }
        }
        if (!StringUtil.isNotEmpty(dto.getPurConfDays())) {
            wr.appendMessage(new ValidMessage("purSpeed",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductBuild002.purSpeed"))));
        }
        if (!StringUtil.isNotEmpty(dto.getTradCalender())) {
            wr.appendMessage(new ValidMessage("calendarId",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductBuild002.calendarId"))));
        }
        if (!StringUtil.isNotEmpty(dto.getVdate())) {
            wr.appendMessage(new ValidMessage("vdate",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductBuild002.vdate"))));
        }
        if (!StringUtil.isNotEmpty(dto.getMdate())) {
            wr.appendMessage(new ValidMessage("mdate",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductBuild002.mdate"))));
        }
        if (StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getMdate())) {
            if (DateUtil.compareDate(dto.getVdate(), dto.getMdate()) >= 0) {
                wr.appendMessage(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0045", getMessage("FinProductBuild002.vdate"),
                        getMessage("FinProductBuild002.mdate"))));
            }
        }

        // 判断首次开放日是否是节假日
        if (bussFinProductOpenPlanService.checkFirstOpenDateIsHoliday(dto)) {
            wr.setData(this.getMessage("BUSS_COM_E_0039",
                this.getMessage("FinProductOpenRule.firstOpenDate")));
        }
        return wr;
    }

    /**
     * 生成开放计划
     *
     * @return 开放计划
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("previewList")
    public List<FinOpenPlan> previewList() {
        BussFinProductOpenPlanDto dto = BeanUtil.mapToObject(RequestUtil.getPageParam(),
            BussFinProductOpenPlanDto.class);
        if (StringUtil.isNotEmpty(this.getParam("workflowId"))
            && !StringUtil.equals(this.getParam("quickFlag"), "true")) {
            BussFinProductOpenPlanDto bussFinProductOpenPlanDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                this.getParam("workflowId"), this.getParam("entityName"),
                BussFinProductOpenPlanDto.class);
            if (bussFinProductOpenPlanDto != null) {
                return bussFinProductOpenPlanDto.getFinOpenPlanList();
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
            return bussFinProductOpenPlanService.queryOpenPlanListByFinprodId(
                this.getParam("finprodId"));
        }
        return bussFinProductOpenPlanService.genOpenPlanList(dto);
    }

    /**
     * 根据金融产品代码查询开放计划信息
     *
     * @param finprodId 金融产品代码
     * @return 开放计划信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryOpenPlanInfoByFinprodId")
    public List<BussFinProductOpenPlanDto> queryOpenPlanInfoByFinprodId(String finprodId) {
        return bussFinProductOpenPlanService.queryOpenPlanInfoByFinprodId(finprodId);
    }

}
