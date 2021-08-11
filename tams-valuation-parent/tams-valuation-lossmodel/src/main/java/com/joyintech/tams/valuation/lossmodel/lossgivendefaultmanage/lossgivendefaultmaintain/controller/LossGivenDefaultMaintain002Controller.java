/*
 * 文件名：FileCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.controller;

import java.util.Map;

import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.dto.ModLgdConfigDto;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.service.LossGivenDefaultMaintain002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 附件信息--业务组件
 * @author zhangnian
 * @version 1.0 2018年9月10日
 * @see LossGivenDefaultMaintain002Controller
 * @since 1.0
 */
@Controller
@PageId("LossGivenDefaultMaintain002")
@RequestMapping("/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002")
public class LossGivenDefaultMaintain002Controller extends BaseController{
	@Autowired
	private LossGivenDefaultMaintain002Service lossGivenDefaultMaintain002Service;
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.资产类型, DictCode.资产分类,DictCode.是否);
    	 String workflowId = getParam("workflowId");
         String creditTagId = getParam("seqno");
         // 待办跳转
         if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
             model.addAttribute("workflowId", workflowId);
             model.addAttribute("isTodoList", getParam("isTodoList"));
             ModLgdConfigDto CreditTagInfo = lossGivenDefaultMaintain002Service.getBussinessEntityByWorkflowId(workflowId);

             model.addAttribute("entity", CreditTagInfo);

         }
         //修改
         else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(this.getParam("seqno"))) {

        	 ModLgdConfigDto CreditTagInfo = lossGivenDefaultMaintain002Service.getBussinessEntityByIndId(creditTagId);
        	 model.addAttribute("SEQNO", this.getParam("seqno"));
             model.addAttribute(GlobalConstant.ENTITY, CreditTagInfo);
         }
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
        return lossGivenDefaultMaintain002Service.checkIfUsed(param);
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated ({ValidateGroupEdit.class})ModLgdConfigDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ModLgdConfigDto dto, BindingResult br) throws Exception {
        // 新建、修改校验
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 调用共通自动生成额度标签代码
        if (StringUtil.isEmpty(dto.getSeqno())) {
            dto.setSeqno(IDGenerator.generate());
        }
        ProcessParameter<ModLgdConfigDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ModLgdConfigDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getAssetType()+dto.getAssetDetailType()+dto.getIsContainPledge();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_LGD违约损失率维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产类型"+
                    GlobalConstant.OPEN_PARENTHESIS+entity.getAssetType()+GlobalConstant.CLOSE_PARENTHESIS+
                    "债券类型"+GlobalConstant.OPEN_PARENTHESIS+
                    entity.getAssetDetailType()+GlobalConstant.CLOSE_PARENTHESIS+
                    "是否含有抵质押物"+GlobalConstant.OPEN_PARENTHESIS+
                    entity.getIsContainPledge()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = lossGivenDefaultMaintain002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
