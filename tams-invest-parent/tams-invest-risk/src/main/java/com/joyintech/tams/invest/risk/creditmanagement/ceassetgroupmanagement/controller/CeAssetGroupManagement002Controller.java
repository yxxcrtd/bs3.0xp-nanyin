/**
 * 
 */
package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉 资产系列新建
 * 
 * @author dongzhiling
 * @version 1.0 2019年1月7日
 * @see CeAssetGroupManagement001Controller
 * @since 1.0
 */
@Controller
@PageId("CeAssetGroupManagement002")
@RequestMapping("/invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002")
public class CeAssetGroupManagement002Controller extends BaseController {
    
    /**
     * CeAssetGroupManagement002Service
     */
    @Autowired(required = false)
    private CeAssetGroupManagement002Service ceAssetGroupManagement002Service;
    
    /**
     * BaseGetBusinessId
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Override
    public String init(Model model) {
        String workflowId = this.getParam("workflowId");
        String seqno = this.getParam("id");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            model.addAttribute("workflowId", workflowId);
            CeAssetGroupManagement001Dto ceAssetGroupManagement001Dto = ceAssetGroupManagement002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", ceAssetGroupManagement001Dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(seqno)) {
            CeAssetGroupManagement001Dto ceAssetGroupManagement001Dto = ceAssetGroupManagement002Service.getBusinessInfoBySeqno(seqno);
            model.addAttribute("entity", ceAssetGroupManagement001Dto);
        }
        return super.init(model);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 保存按钮按下时check
     * @param dto
     * @param result
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody CeAssetGroupManagement001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param result
     * @return 保存处理结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody CeAssetGroupManagement001Dto dto,BindingResult result) throws Exception {
        if(result.hasErrors()) {
            return new AjaxResult(result);
        }
        ProcessParameter<CeAssetGroupManagement001Dto> processParameter = new ProcessParameter<>();
        // 工作流参数设置
        WorkFlowParameter<CeAssetGroupManagement001Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 业务代码生成参数
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产系列授信);
            dto.setSeqno(baseGetBusinessId.buildBussinessCode(parameter));
        }
        //设置业务编号
        String businessCode = dto.getSeqno();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_资产系列授信.getValue());
        //设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("CeAssetGroupManagement001.seqno"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("CeAssetGroupManagement002.name"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getAssetpoolName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = ceAssetGroupManagement002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
