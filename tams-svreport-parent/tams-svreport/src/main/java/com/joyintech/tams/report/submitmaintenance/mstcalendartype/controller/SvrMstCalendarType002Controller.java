/*
 * 文件名：MstCalendarType002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.mstcalendartype.controller;

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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.dto.SvrMstCalendarType001Dto;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.service.SvrMstCalendarType001MService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 日历类型管理新建
 * 
 * @author liuwei
 * @version 1.0 2018年8月18日
 * @see SvrMstCalendarType002Controller
 * @since 1.0
 */
@Controller
@PageId("SvrMstCalendarType002")
@RequestMapping("/submitmaintenance/mstcalendartype/SvrMstCalendarType002")
public class SvrMstCalendarType002Controller extends BaseController {

    /**
     * 日历类型管理Service
     */
    @Autowired
    private SvrMstCalendarType001MService mstCalendarType001001service;

    /**
     * 业务编号
     */
//    @Autowired(required = false)
//    private BaseGetBusinessId bussGetBussinessIdService;

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
//        String workflowId = getParam("workflowId");
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(tradeCalendarId)) {
            Map<String, Object> param = new HashMap<>();
            // 将需要的数据字典和参数添加到map中
            param.put("YESORNO_YES_02", "N");
            param.put("YESORNO_YES_01", "Y");
            param.put("tradeCalendarId", tradeCalendarId);
            SvrMstCalendarType001Dto mstCalendarTypeInfo = mstCalendarType001001service.selectCalendar(
                param);
            // 设置到model中
            mstCalendarTypeInfo.setTradeCalendarId(tradeCalendarId);
            model.addAttribute("entity", mstCalendarTypeInfo);
        }
//        // 来自于代办
//        else if (!WorkFlowUtil.isNotFromToDoList()) {
//            // 根据工作流与留痕处理提供的共通方法获取业务实体
//            MstCalendarType001Dto mstCalendarTypeInfo = mstCalendarType001001service.getBussinessEntityByWorkflowId(
//                workflowId);
//            // 设置到model中
//            model.addAttribute("pageModel",mstCalendarTypeInfo.getPageModel());
//            model.addAttribute("entity", mstCalendarTypeInfo);
//        }
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
        ValidateGroupEdit.class}) SvrMstCalendarType001Dto dto, BindingResult result) {
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
    public AjaxResult save(SvrMstCalendarType001Dto dto)
        throws Exception {
    	String msg = this.getMessage("REPORT_COMMON_I_0006");
    	if("0".equals(dto.getPageModel())){
    		mstCalendarType001001service.doComplete(dto, OperationType.NEW);
    	}else if("1".equals(dto.getPageModel())){
    		mstCalendarType001001service.doComplete(dto, OperationType.MODIFY);
    	}
        return new AjaxResult(msg);
    }

}
