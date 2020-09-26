/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.controller;

import com.joyintech.framework.ajax.MessageType;
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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage002Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.service.TaxSchBokCfgManage001Service;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.service.TaxSchBokCfgManage002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 会计场景计税配置
 *
 * @author zhangwei
 * @version 1.0 2019-06-29
 * @see TaxSchaBokConfig002Controller
 * @since 1.0
 */
@Controller
@PageId("TaxSchBokCfgManage002")
@RequestMapping("/valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage002")
public class TaxSchBokCfgManage002Controller extends BaseController {

    @Autowired
    private TaxSchBokCfgManage001Service taxSchBokCfgManage001Service;

    @Autowired
    private TaxSchBokCfgManage002Service taxSchBokCfgManage002Service;

    /**
     * 税务方案 - 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String schemeId = getParam("schemeId");
        String configId = getParam("configId");
        String operateType = getParam("operateType");
        TaxSchBokCfgManage002Dto dto = new TaxSchBokCfgManage002Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(configId)) { // 查看 、 修改
            dto = taxSchBokCfgManage002Service.findByPrimaryKey(configId);
            // 若会计场景计税配置未关联，方案代码为空
            if (StringUtils.isEmpty(dto.getSchemeId())) {
                dto.setSchemeId(schemeId);
            }
            schemeId = dto.getSchemeId();
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = taxSchBokCfgManage002Service.getBussinessEntityByWorkflowId(workflowId);

            configId = dto.getConfigId(); // 避免流程，再生成主键
        }

        if (StringUtils.isEmpty(configId) || "copy".equals(operateType)) { // 新建 、复制
            // 先随机生成，入库时再按规则生成
            dto.setConfigId(IDGenerator.generate());
            dto.setSchemeId(schemeId);
        }
        dto.setOperateType(operateType);
        model.addAttribute(GlobalConstant.ENTITY, dto);

        super.setSelectOptionToModel(model, DictCode.会计场景代码);
        super.setSelectOptionToModel(model, DictCode.增值税类型);
        super.setSelectOptionToModel(model, DictCode.增值税_确认类型);
        super.setSelectOptionToModel(model, DictCode.增值税_发生场景);
        super.setSelectOptionToModel(model, DictCode.增项_减项);
        super.setSelectOptionToModel(model, DictCode.增值税_计算方式);
        super.setSelectOptionToModel(model, DictCode.增值税_占比比例);
        super.setSelectOptionToModel(model, DictCode.借贷方向);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("/getBaseSubjectNature")
    public Page<SelectOptionDto> getBaseSubjectNature() {
        return taxSchBokCfgManage002Service.getBaseSubjectNature(RequestUtil.getPageParam());
    }

    /**
     * 提交/保存 check
     * 
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) TaxSchBokCfgManage002Dto dto, BindingResult bindingResult)
        throws Exception {
        String operateType = dto.getOperateType();

        if ((DictCodeList.增值税_计算方式_发生额计算.getValue().equals(dto.getCalcType())
             || DictCodeList.增值税_计算方式_发生额累加.getValue().equals(dto.getCalcType()))
            && StringUtils.isEmpty(dto.getCdFlag())) {
            bindingResult.rejectValue("cdFlag", "NotBlank", "当计算方式为发生额计算/发生额累加时，发生科目借贷方向必填！");
        }

        if (DictCodeList.增值税_计算方式_占比计算.getValue().equals(dto.getCalcType())) {
            if (StringUtils.isEmpty(dto.getCalcScale())) {
                bindingResult.rejectValue("calcScale", "NotBlank", "当计算方式为占比计算时，占比方式必填！");
            }
            if (StringUtils.isEmpty(dto.getCalcSnature())) {
                bindingResult.rejectValue("calcSnature", "NotBlank", "当计算方式为占比计算时，占比科目性质必填！");
            }
            if (StringUtils.isEmpty(dto.getCalcBase())) {
                bindingResult.rejectValue("calcBase", "NotBlank", "当计算方式为占比计算时，占比基数必填！");
            }
        }

        if (!bindingResult.hasFieldErrors() && !dto.getIsCarrt().matches("^[01]{3}$")) {
            bindingResult.rejectValue("isCarrt", "",
                "金额计入方式格式非法！(二进制表示三位数)</br>" + "第一位:是否计入余额</br>" + "第二位:是否计入发生额</br>"
                                                     + "第三位:是否计入结转金额</br>");
        }

        if (!bindingResult.hasErrors()) {
            int records = taxSchBokCfgManage001Service.countUsed(dto.getConfigId());
            if (records > 0) {
                return new AjaxResult("当前配置已被使用，是否确认修改？", MessageType.确认消息);
            }
        }

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 新建或修改 并启动流程
     * 
     * @param dto
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody TaxSchBokCfgManage002Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<TaxSchBokCfgManage002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxSchBokCfgManage002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getConfigId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment("会计场景计税配置", entity.getConfigId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            taxSchBokCfgManage002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }
}
