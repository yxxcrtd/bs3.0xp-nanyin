package com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.service.ValWithholdingAmortizeSet001Service;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.service.ValWithholdingAmortizeSet002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 待摊设置
 * 
 * @author tanxuan
 * @version 1.0 2019-11-25
 * @see ValWithholdingAmortizeSet002Controller
 * @since 1.0
 */
@Controller
@PageId("ValWithholdingAmortizeSet002")
@RequestMapping("/valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002")
public class ValWithholdingAmortizeSet002Controller extends BaseController {

    @Autowired
    private ValWithholdingAmortizeSet001Service valWithholdingAmortizeSet001Service;

    @Autowired
    private ValWithholdingAmortizeSet002Service valWithholdingAmortizeSet002Service;

    /**
     * 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String booksetId = getParam("booksetId");
        String operateType = getParam("operateType");
        ValWithholdingAmortizeSet002Dto dto = new ValWithholdingAmortizeSet002Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(booksetId)) { // 查看 、 修改
            dto = valWithholdingAmortizeSet002Service.findByPrimaryKey(RequestUtil.getPageParam());
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = valWithholdingAmortizeSet002Service.getBussinessEntityByWorkflowId(workflowId);
        }

        dto.setOperateType(operateType);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        super.setSelectOptionToModel(model, DictCode.一次性费用类型);
        return super.init(model);
    }

    /**
     * 账套代码 - 业务编号获取
     * 
     * @return list
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return valWithholdingAmortizeSet002Service.getFinprodId(param);
    }

    /**
     * 通过业务编号，获取信息
     * 
     * @return valWithholdingAmortizeSet001Dto
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("getFinprodInfo")
    public ValWithholdingAmortizeSet001Dto getFinprodInfo()
        throws Exception {
        return valWithholdingAmortizeSet002Service.getFinprodInfo(RequestUtil.getPageParam());
    }
    
    /**
     * 获取交易日期
     * 
     * @return valWithholdingAmortizeSet001Dto
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("getTradeDate")
    public ValWithholdingAmortizeSet001Dto getTradeDate()
        throws Exception {
        return valWithholdingAmortizeSet002Service.getTradeDate(RequestUtil.getPageParam());
    }

    /**
     * 提交/保存 check
     * 
     * @param dto
     * @param bindingResult
     * @return ajaxResult
     * @throws Exception exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) ValWithholdingAmortizeSet002Dto dto, BindingResult bindingResult)
        throws Exception {
        String msg = StringUtil.EMPTY;
        String operateType = dto.getOperateType();
        String pageModel = dto.getPageModel();
        int count = 0;

        if (StringUtils.isEmpty(dto.getBooksetId())) {
            // bindingResult.rejectValue("booksetId", "NotBlank", "账套代码不能为空！");
            count += 1;
        }

        if (StringUtils.isEmpty(dto.getFinprodId())) {
            // bindingResult.rejectValue("finprodId", "NotBlank", "业务编号不能为空！");
            count += 1;
        }

        if (dto.getPrin() == null) {
            // bindingResult.rejectValue("prin", "NotBlank", "金额不能为空！");
            count += 1;
        }

        if (StringUtils.isEmpty(dto.getVdate())) {
            // bindingResult.rejectValue("vdate", "NotBlank", "开始日期不能为空！");
            count += 1;
        }

        if (StringUtils.isEmpty(dto.getMdate())) {
            // bindingResult.rejectValue("mdate", "NotBlank", "结束日期不能为空！");
            count += 1;
        }

        if (count != 0) {
            msg = "业务数据录入不完整，请补充!";
            return new AjaxResult(msg, MessageType.错误消息);
        }

        if (StringUtils.isNotEmpty(dto.getVdate()) && StringUtils.isNotEmpty(dto.getMdate())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date vDate = sdf.parse(dto.getVdate());
            Date mDate = sdf.parse(dto.getMdate());
            if (mDate.before(vDate)) {
                msg = "结束日期不能小于开始日期！";
                return new AjaxResult(msg, MessageType.错误消息);
            }
        }
        if (!bindingResult.hasErrors()) {// 基础验证通过
            if (pageModel.equals("1")  && valWithholdingAmortizeSet001Service.check(dto).size() > 0) {
                msg = "已存在预提待摊凭证，是否继续保存修改？";
                return new AjaxResult(msg, MessageType.确认消息);
            }
            else {
                msg = msg.concat("请确认是否要提交?");
                return new AjaxResult(msg, MessageType.确认消息);
            }
        }
        return commonCheck(bindingResult, operateType);
    }

    /**
     * 新建或修改 并启动流程
     * 
     * @param dto
     * @param 
     * @return ajaxResult
     */
    @ResponseBody
    @RequestMapping(value = "saveOrSubmit", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ValWithholdingAmortizeSet002Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<ValWithholdingAmortizeSet002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValWithholdingAmortizeSet002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getBooksetId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_待摊设置.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment("待摊设置", entity.getBooksetId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            valWithholdingAmortizeSet002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动外汇估值信息流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }
}
