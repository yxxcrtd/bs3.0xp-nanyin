/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.controller;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme007Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme001Service;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme007Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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

import java.util.Map;

/**
 * 税务方案 - 会计场景计税配置
 *
 * @author zhangwei
 * @version 1.0 2019-06-29
 * @see TaxScheme007Controller
 * @since 1.0
 */
@Controller
@PageId("TaxScheme007")
@RequestMapping("/valuation/taxparametermanage/taxscheme/TaxScheme007")
public class TaxScheme007Controller extends BaseController {

    @Autowired
    private TaxScheme001Service taxScheme001Service;

    @Autowired
    private TaxScheme007Service taxScheme007Service;

    /**
     * 税务方案 - 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String schemeId = getParam("schemeId");
        String configId = getParam("configId");
        String seqNo = getParam("seqNo");
        String operateType = getParam("operateType");
        TaxScheme007Dto dto = new TaxScheme007Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(seqNo)) { // 查看 、 修改
            dto = taxScheme007Service.findByPrimaryKey(seqNo);
            // 若会计场景计税配置未关联，方案代码为空
            if (StringUtils.isEmpty(dto.getSchemeId())) {
                dto.setSchemeId(schemeId);
            }
            schemeId = dto.getSchemeId();
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = taxScheme007Service.getBussinessEntityByWorkflowId(workflowId);

            seqNo = dto.getSeqNo(); // 避免流程，再生成主键
        }

        if (StringUtils.isEmpty(seqNo) || "copy".equals(operateType)) { // 新建 、复制
            dto.setSeqNo(IDGenerator.generate());
            dto.setSchemeId(schemeId);
        }
        dto.setOperateType(operateType);

        model.addAttribute(GlobalConstant.ENTITY, dto);
        model.addAttribute("BASE_SUBJECT_NATURE",
            JsonUtils.object2Json(taxScheme007Service.getBaseSubjectNatureAll(Maps.newHashMap())));
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

    /**
     * 会计场景计税配置
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/getConfigInfoByKey")
    public TaxScheme007Dto getConfigInfoByKey() {
        return taxScheme007Service.getConfigInfoByKey(getParam("configId"));
    }

    /**
     * 下拉框 - 基础科目性质
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBaseSubjectNature")
    public Page<SelectOptionDto> getBaseSubjectNature() {
        return taxScheme007Service.getBaseSubjectNature(RequestUtil.getPageParam());
    }

    /**
     * 下拉框 - 会计场景计税配置
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/getConfigInfo")
    public Page<SelectOptionDto> getConfigInfo() {
        return taxScheme007Service.getConfigInfo(RequestUtil.getPageParam());
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TaxScheme007Dto dto,
                            BindingResult bindingResult)
        throws Exception {
        String operateType = dto.getOperateType();
        String msg = StringUtils.EMPTY;

        if(!bindingResult.hasErrors()){
            Map<String, Object> map = Maps.newHashMap();
            map.put("schemeId", dto.getSchemeId());
            map.put("configId", dto.getConfigId());
            map.put("owner", dto.getSeqNo());
            long records = taxScheme001Service.findTaxSchaBokConfigByPage(map).getRecords();
            if (records > 0){
                msg = "不可重复配置：" + dto.getConfigId();
                return new AjaxResult(msg, MessageType.错误消息);
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
    public AjaxResult save(@RequestBody TaxScheme007Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<TaxScheme007Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme007Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getConfigId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment("会计场景计税配置", entity.getConfigId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            taxScheme007Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动税务方案流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }
}
