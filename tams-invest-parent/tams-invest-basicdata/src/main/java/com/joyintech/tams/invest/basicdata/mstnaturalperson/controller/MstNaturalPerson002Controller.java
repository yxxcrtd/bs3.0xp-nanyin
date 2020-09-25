/*
 * 文件名：MstNaturalPerson002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstnaturalperson.controller;

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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;
import com.joyintech.tams.invest.basicdata.mstnaturalperson.service.MstNaturalPerson001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 自然人管理新建
 * 
 * @author xiexinting
 * @version 1.0 2018年8月10日
 * @see MstNaturalPerson002Controller
 * @since 1.0
 */
@Controller
@PageId("MstNaturalPerson002")
@RequestMapping("/invest/basicdata/mstnaturalperson/MstNaturalPerson002")
public class MstNaturalPerson002Controller extends BaseController {

    /**
     * 获取service层对象
     */
    @Autowired
    private MstNaturalPerson001Service mstNaturalPerson001Service;
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 新建页面初始化
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.性别);
        // String operateType = getParam("operateType");
        String customerId = getParam("customerId");
        String workflowId = getParam("workflowId");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(customerId)) {
            // 根据主键获取业务实体
            MstNaturalPerson001Dto entity = mstNaturalPerson001Service.selectDetail(customerId);
            // 设置到model中
            model.addAttribute("entity", entity);
        }
        // 来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList()) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            MstNaturalPerson001Dto entity = mstNaturalPerson001Service.getBussinessEntityByWorkflowId(
                workflowId);
            // 设置到model中
            model.addAttribute("entity", entity);
        }  
        return super.init(model);
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
        ValidateGroupEdit.class}) MstNaturalPerson001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 保存提交工作流
     * <功能详细描述>
     * @param dto 保存参数
     * @param br 保存参数
     * @return 保存返回信息
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstNaturalPerson001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getCustomerId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_自然人管理);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setCustomerId(code);
        }
        ProcessParameter<MstNaturalPerson001Dto> processParameter = new ProcessParameter<MstNaturalPerson001Dto>();
        WorkFlowParameter<MstNaturalPerson001Dto> params = new WorkFlowParameter<MstNaturalPerson001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getCustomerId());
        params.setBusinessType(DictCodeList.共通业务类型_自然人管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "客户名称" + "-" + entity.getCustomerName());
        processParameter.setWorkFlowParameter(params);
        String msg = mstNaturalPerson001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
