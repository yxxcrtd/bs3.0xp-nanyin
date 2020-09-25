/*
 * 文件名：MstCalendarType002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcalendartype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.basicdata.mstcalendartype.dto.MstCalendarType001Dto;
import com.joyintech.tams.invest.basicdata.mstcalendartype.service.MstCalendarType001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 日历类型管理新建
 * 
 * @author liuwei
 * @version 1.0 2018年8月18日
 * @see MstCalendarType002Controller
 * @since 1.0
 */
@Controller
@PageId("MstCalendarType002")
@RequestMapping("/invest/basicdata/mstcalendartype/MstCalendarType002")
public class MstCalendarType002Controller extends BaseController {

    /**
     * 日历类型管理Service
     */
    @Autowired
    private MstCalendarType001Service mstCalendarType001001service;

    /**
     * 业务编号
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 新建、编辑、查看界面的初始化
     * 
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String tradeCalendarId = getParam("tradeCalendarId");
        String workflowId = getParam("workflowId");
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(tradeCalendarId)) {
            Map<String, Object> param = new HashMap<>();
            // 将需要的数据字典和参数添加到map中
            param.put("YESORNO_YES_02", DictCodeList.是否_否.getValue());
            param.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
            param.put("tradeCalendarId", tradeCalendarId);
            MstCalendarType001Dto mstCalendarTypeInfo = mstCalendarType001001service.selectCalendar(
                param);
            // 设置到model中
            mstCalendarTypeInfo.setTradeCalendarId(tradeCalendarId);
            model.addAttribute("entity", mstCalendarTypeInfo);
        }
        // 来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList()) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            MstCalendarType001Dto mstCalendarTypeInfo = mstCalendarType001001service.getBussinessEntityByWorkflowId(
                workflowId);
            // 设置到model中
            model.addAttribute("pageModel",mstCalendarTypeInfo.getPageModel());
            model.addAttribute("entity", mstCalendarTypeInfo);
        }
        return super.init(model);
    }

    /**
     * 包含市场日历类型的下拉框事件
     * 
     * @return 查询的包含市场日历类型列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCalendarTypeList")
    public List<SelectOptionDto> getCalendarTypeList() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("calendarType", super.getParam("calendarType"));
        List<SelectOptionDto> temp = mstCalendarType001001service.getCalendarType(param);
        return temp;
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstCalendarType001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstCalendarType001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 业务代码生成参数
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_日历类型管理);
        // 如果是新建则生成交易日历代码
        if (OperationType.NEW.name().equals(dto.getOperateType())) {
            // 修改业务编号生成方式
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(parameter):IDGenerator.generate();
            dto.setTradeCalendarId(code);
        }
        ProcessParameter<MstCalendarType001Dto> processParameter = new ProcessParameter<MstCalendarType001Dto>();
        WorkFlowParameter<MstCalendarType001Dto> params = new WorkFlowParameter<MstCalendarType001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if (StringUtil.isEmpty(dto.getTradeCalendarId())) {
            // 修改业务编号生成方式
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(parameter):IDGenerator.generate();
            dto.setTradeCalendarId(code);
        }
        params.setBusinessCode(dto.getTradeCalendarId());
        params.setBusinessType(DictCodeList.共通业务类型_日历类型管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> this.getMessage("MstCalendarType001.tradeCalendarId") + "(" + entity.getTradeCalendarId() + ")"
            + this.getMessage("MstCalendarType002.calendarName") + "(" + dto.getCalendarName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstCalendarType001001service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
