/*
 * 文件名：ProdDevelopManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.rddesign.proddevelopmanage.controller;

import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.rddesign.proddevelopmanage.dto.ProdDevelopManageDto;
import com.joyintech.tams.invest.product.rddesign.proddevelopmanage.service.ProdDevelopManageService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品研发方案新建
 * 
 * @author zhangJin
 * @version 1.0 2019年10月22日
 * @see ProdDevelopManage002Controller
 * @since 1.0
 */
@Controller
@PageId("ProdDevelopManage002")
@RequestMapping("/invest/product/rddesign/proddevelopmanage/ProdDevelopManage002")
public class ProdDevelopManage002Controller extends BaseController {

    /**
     * 产品研发方案管理Service
     */
    @Autowired
    private ProdDevelopManageService prodDevelopManageService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.是否通过, DictCode.可行性状态, DictCode.产品风险等级);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String seqNo = getParam("seqNo");
        // 工作流获取entity
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            ProdDevelopManageDto entity = prodDevelopManageService.getBussinessEntityByWorkflowId(
                workflowId);
            // 设置到model中
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(seqNo)) {
            ProdDevelopManageDto entity = prodDevelopManageService.getEntityBySeqNo(seqNo);
            // 设置到model中
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }

    /**
     * 通过系列代码联动显示
     * 
     * @return 产品系列
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProdResultInfos")
    public ProdDevelopManageDto getProdResultInfos() {
        return prodDevelopManageService.getProdResultInfos(RequestUtil.getPageParam());
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) ProdDevelopManageDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * 
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) ProdDevelopManageDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (!StringUtil.isNotEmpty(dto.getSeqNo())) {
            dto.setSeqNo(IDGenerator.generate());
        }
        ProcessParameter<ProdDevelopManageDto> processParameter = new ProcessParameter<ProdDevelopManageDto>();
        WorkFlowParameter<ProdDevelopManageDto> params = new WorkFlowParameter<ProdDevelopManageDto>();
        params.setPageId(dto.getPageId());
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        params.setBusinessCode(item -> item.getSeqNo());
        params.setBusinessType(DictCodeListExt2.共通业务类型_产品研发方案管理.getValue());
        params.setComment(entity -> this.getMessage("ProdDevelopManage001.prdDesignId") + "("
                                    + entity.getPrdDesignId() + ")" + "-"
                                    + this.getMessage("ProdDevelopManage001.prdDesignName") + "("
                                    + entity.getPrdDesignName() + ")" + "-"
                                    + this.getMessage("ProdDevelopManage001.generateTime") + "("
                                    + DateUtil.format(entity.getGenerateTime()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = prodDevelopManageService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
