/*
 * 文件名：FileCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service.ModBintreeConfig002Service;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 二叉树模型参数配置
 * @author yuluan
 * @version 1.0 2011年7月1日
 * @see ModBintreeConfig002Controller
 * @since 1.0
 */
@Controller
@PageId("ModBintreeConfig002")
@RequestMapping("/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002")
public class ModBintreeConfig002Controller extends BaseController {
	@Autowired
	private ModBintreeConfig002Service modBintreeConfig002Service;
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.市场乘数方法, DictCode.上市状态, DictCode.曲线代码);
    	 String workflowId = getParam("workflowId");
         String creditTagId = getParam("seqno");
         // 待办跳转
         if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
             model.addAttribute("workflowId", workflowId);
             model.addAttribute("isTodoList", getParam("isTodoList"));
             ModBintreeConfigDto CreditTagInfo = modBintreeConfig002Service.getBussinessEntityByWorkflowId(workflowId);

             model.addAttribute("entity", CreditTagInfo);

         }
         //修改
         else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(this.getParam("seqno"))) {

        	 ModBintreeConfigDto CreditTagInfo = modBintreeConfig002Service.getBussinessEntityByIndId(creditTagId);
        	 model.addAttribute("SEQNO", this.getParam("seqno"));
             model.addAttribute(GlobalConstant.ENTITY, CreditTagInfo);
         }
         else {
             ModBintreeConfigDto creditTagInfo = new ModBintreeConfigDto();
             creditTagInfo.setSmoothsection(new BigDecimal("0.00"));
             creditTagInfo.setBintreeStep(new BigDecimal("100"));
             model.addAttribute(GlobalConstant.ENTITY, creditTagInfo);
         }
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        return super.init(model);
    }
    /**
     * 校验标签名称
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping("checkIfUsed")
    public String checkIfUsed(@RequestParam Map<String, Object> param) {
        return modBintreeConfig002Service.checkIfUsed(param);
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated ModBintreeConfigDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    @ResponseBody
    @RequestMapping("getcompStockidForBintree")
    public List<SelectOptionDto> getcompStockidForBintree(@RequestParam Map<String, Object> params) {
      
        return modBintreeConfig002Service.getcompStockidForBintree(params);
    }
    @ResponseBody
    @RequestMapping("getMarketId")
    public Page<SelectOptionDto> getMarketId(@RequestParam Map<String, Object> params) {
      
        return modBintreeConfig002Service.getMarketId(params);
    }

    @ResponseBody
    @RequestMapping("getCurveCode")
    public Page<SelectOptionDto> getCurveCode(@RequestParam Map<String, Object> params) {

        return modBintreeConfig002Service.getCurveCode(params);
    }
    /**
     * 保存
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @SuppressWarnings("deprecation")
	@ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ModBintreeConfigDto dto, BindingResult br) throws Exception {
        // 新建、修改校验
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 调用共通自动生成额度标签代码
        if (StringUtil.isEmpty(dto.getSeqno())) {
            dto.setSeqno(IDGenerator.generate());
        }
        ProcessParameter<ModBintreeConfigDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ModBintreeConfigDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getFinprodId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_二叉树模型参数配置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产代码"+
                    GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodId()+ GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = modBintreeConfig002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
