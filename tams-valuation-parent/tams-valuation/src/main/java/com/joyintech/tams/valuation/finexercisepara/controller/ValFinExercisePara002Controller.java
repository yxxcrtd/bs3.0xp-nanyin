/*
 * 文件名：ValFinExercisePara002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.finexercisepara.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara002Dto;
import com.joyintech.tams.valuation.finexercisepara.service.ValFinExercisePara002Service;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 含权债摊销参数设置 -- 新建
 * 
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara002Controller
 * @since 1.0
 */
@Controller
@PageId("ValFinExercisePara002")
@RequestMapping("/valuation/finexercisepara/ValFinExercisePara002")
public class ValFinExercisePara002Controller extends BaseController {

    @Autowired
    private ValFinExercisePara002Service valFinExercisePara002Service;

    @Override
    public String init(Model model) {
        String booksetId = getParam("booksetId");
        String finprodId = getParam("finprodId");
        String branch = getParam("branch");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
        ValFinExercisePara002Dto dto = new ValFinExercisePara002Dto();

        if (StringUtils.isNotEmpty(booksetId) && StringUtils.isNotEmpty(finprodId)) {// 修改
            dto.setBooksetId(booksetId);
            dto.setFinprodId(finprodId);
            dto.setBranch(Long.valueOf(branch));
            dto = valFinExercisePara002Service.findByPrimaryKey(dto);
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = valFinExercisePara002Service.getBussinessEntityByWorkflowId(workflowId);
        }

        model.addAttribute(GlobalConstant.ENTITY, dto);
        super.setSelectOptionToModel(model, DictCode.含权债摊销日); // 行权摊销日设置
        return super.init(model);
    }

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> getBooksetId() {
        return valFinExercisePara002Service.findBooksetIdByPage(RequestUtil.getPageParam());
    }

    /**
     * 下拉框 ： 产品代码
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findFinprod")
    public Page<SelectOptionDto> findFinprod()
        throws Exception {
        return valFinExercisePara002Service.findFinprod(RequestUtil.getPageParam());
    }

    /**
     * 通过产品代码，获取产品信息
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getFinprodInfo")
    public FinProduct getFinprodInfo()
        throws Exception {
        return valFinExercisePara002Service.getFinprodInfo(RequestUtil.getPageParam());
    }

    /**
     * 提交/保存 check
     * 
     * @param dto
     * @param result
     * @param operateType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ValFinExercisePara002Dto dto,
                            BindingResult bindingResult)
        throws Exception {
//        String msg = StringUtil.EMPTY;
        String operateType = dto.getOperateType();

        if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
            dto.setBranch(0L);
            ValFinExercisePara002Dto entity = valFinExercisePara002Service.findByPrimaryKey(dto);
            if (entity != null) {
                return new AjaxResult("一只账套加一个资产只能有一条有效的配置数据！", MessageType.错误消息);
            }
        }

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 新建或修改 并启动流程
     * 
     * @param dto
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ValFinExercisePara002Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<ValFinExercisePara002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValFinExercisePara002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getBooksetId() + "_" + dto.getFinprodId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_含权债摊销参数设置.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment("含权债摊销参数设置", entity.getBooksetId(), entity.getFinprodId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            valFinExercisePara002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }
}
