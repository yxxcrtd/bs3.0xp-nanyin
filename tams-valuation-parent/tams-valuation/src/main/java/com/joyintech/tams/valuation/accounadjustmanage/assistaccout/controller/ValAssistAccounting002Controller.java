

package com.joyintech.tams.valuation.accounadjustmanage.assistaccout.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistAccounting001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistAccounting002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.service.ValAssistAccounting002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author  殷亚
 * @description
 * @date 2022-1-17 9:23   
 */
@Controller
@PageId("ValAssistAccounting002")
@RequestMapping("/valuation/accounadjustmanage/assistaccount/ValAssistAccounting002")
public class ValAssistAccounting002Controller extends BaseController {
    
    /**
     * 辅助核算规则新建服务
     */
    @Autowired
    private ValAssistAccounting002Service valAssistAccounting002Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.底层科目规则,DictCode.科目类型, DictCode.是否);
        String datas = this.getParam("datas");
        if (datas != null) {
            ValAssistAccounting001Dto dto = JsonUtils.json2Object(datas, ValAssistAccounting001Dto.class);
            model.addAttribute("booksetId", dto.getBooksetId());
            model.addAttribute("booksetName", dto.getBooksetName());
            model.addAttribute("subjectInternalCode", dto.getSubjectInternalCode());
            model.addAttribute("subjectInternalName", dto.getSubjectInternalName());
            model.addAttribute("subjectLevel", dto.getSubjectLevel());
            model.addAttribute("accFlagName", dto.getAccFlagName());
            model.addAttribute("subjectTypeName", dto.getSubjectTypeName());
            model.addAttribute("genDetsubRule", dto.getGenDetsubRule());
            model.addAttribute("hands", JsonUtils.object2Json(dto.getHands()));
        }
        
        
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
            ValAssistAccounting002Dto entity = valAssistAccounting002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }
    
    /***
     * @author  殷亚
     * @description doUpdate       
     * @date 2022-1-17 10:18
     * @param dto:  
     * @return com.joyintech.framework.ajax.AjaxResult 
     */
    @ResponseBody
    @RequestMapping(value = "doUpdate", method=RequestMethod.POST)
    public AjaxResult doUpdate(ValAssistAccounting002Dto dto) {
        ProcessParameter<ValAssistAccounting002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssistAccounting002Dto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto.getBooksetId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_账套日期.getValue());
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        //设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(entity.getBooksetId(),
            entity.getSubjectInternalCode()));
        
        proParam.setWorkFlowParameter(params);
        
        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssistAccounting002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        //ValAssistAccounting002Service.doUpdate(dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid ValAssistAccounting002Dto dto, BindingResult result, String operateType) {
        return commonCheck(result, operateType);
    }

    /**
     * 保存提交
     *
     * @param dto
     * @return 画面输入参数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody @Valid ValAssistAccounting002Dto dto, BindingResult br)
            throws Exception {

        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        ProcessParameter<ValAssistAccounting002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValAssistAccounting002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());


        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode(dto.getSubjectInternalCode()+dto.getBooksetId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_辅助核算配置.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("账套代码") + GlobalConstant.COLON + entity.getBooksetId(),
                getMessage("内部科目编码") + GlobalConstant.COLON + entity.getSubjectInternalCode()));

        String msg = valAssistAccounting002Service.start(processParameter, dto);

        return new AjaxResult(msg);
    }
    
}
