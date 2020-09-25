package com.joyintech.tams.invest.product.nolimit.totalMaintain.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.service.TotalMaintain002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;


/**
 * @program: TotalMaintain001Controller
 * @description: 总量/总比例维护
 * @author: jyl
 * @create: 2019-12-18 17:06
 **/
@Controller
@PageId("TotalMaintain002")
@RequestMapping("/invest/product/nolimit/totalMaintain/TotalMaintain002")
public class TotalMaintain002Controller extends BaseController {
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    @Autowired
    TotalMaintain002Service totalMaintain002Service;

    public String init(Model model) {
        String effDate = this.getParam("effDate");
        String workflowId = this.getParam("workflowId");
        TotalMaintain001Dto entity = new TotalMaintain001Dto();
        if (StringUtil.isNotEmpty(effDate)) {

            entity.setEffDate(effDate);
            entity.setNonStandardLimit(new BigDecimal(this.getParam("nonStandardLimit")));
            entity.setNonStandardRotio(new BigDecimal(this.getParam("nonStandardRotio")));
            model.addAttribute("entity", entity);
        }
        if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(effDate)) {

        } else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(effDate)) {
            model.addAttribute("entity", entity);

        } else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            entity = totalMaintain002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }

    /**
     * 校验
     *
     * @param dto
     * @param result
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class, ValidateGroupDelete.class, ValidateGroupNew.class}) TotalMaintain001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());

    }

    /**
     * 新建、编辑提交
     *
     * @param dto
     * @param br
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated TotalMaintain001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setCreateDept(LoginUserUtil.getDeptCode());
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setUpdateUser(LoginUserUtil.getUserName());

        ProcessParameter<TotalMaintain001Dto> processParameter = new ProcessParameter<TotalMaintain001Dto>();
        WorkFlowParameter<TotalMaintain001Dto> params = new WorkFlowParameter<TotalMaintain001Dto>();
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setUpdateUser(LoginUserUtil.getUserName());
        //判断是否在工作流中
        String effDate = dto.getEffDate();
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId(this.getPageId());
        parameter.setBusinessDataPk1(effDate);
        if (mstWorkflowCommonService.isInWflProcess(parameter)) {
            AjaxResult result = new AjaxResult();
            result.setMessage("不能操作流程中的数据！");
            result.setMessageType(MessageType.错误消息);
            return result;
        }
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam("elementId"));

        params.setBusinessCode(dto.getEffDate());
        params.setBusinessType("非标限额管理");
        params.setComment(entity -> this.getMessage("TotalMaintain001.effDate") + "("
                + entity.getEffDate() + ")" + this.getMessage("TotalMaintain002.createTime") + "("
                + DateUtil.format(DateUtil.now()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = totalMaintain002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }


    /**
     * 删除
     *
     * @param dto
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody TotalMaintain001Dto dto)
            throws Exception {
        ProcessParameter<TotalMaintain001Dto> processParameter = new ProcessParameter<TotalMaintain001Dto>();
        WorkFlowParameter<TotalMaintain001Dto> params = new WorkFlowParameter<TotalMaintain001Dto>();
        //判断是否在工作流中
        String prdDesignId = dto.getEffDate();
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId(this.getPageId());//"TotalMaintain001"
        parameter.setBusinessDataPk1(prdDesignId);
        if (mstWorkflowCommonService.isInWflProcess(parameter)) {
            AjaxResult result = new AjaxResult();
            result.setMessage("不能删除流程中的数据！");
            result.setMessageType(MessageType.错误消息);
            return result;
        }
        params.setPageId(this.getPageId());//"TotalMaintain001"
        dto.setCreateUser(LoginUserUtil.getUserName());
        params.setElementId(this.getParam("elementId"));
        params.setBusinessCode(dto.getEffDate());

        params.setBusinessType("非标限额管理");
        params.setComment(entity -> "非标限额管理生效日" + "(" + entity.getEffDate() + ")"
        );
        processParameter.setWorkFlowParameter(params);

        String msg = totalMaintain002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
