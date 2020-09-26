package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.controller;

import javax.validation.Valid;

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
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm004Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm004Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 净值修改
 * 
 * @author hechuan
 *
 */
@Controller
@PageId("ValNetValueConfirm004")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm004")
public class ValNetValueConfirm004Contoller extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private ValNetValueConfirm004Service valNetValueConfirm004Service;

	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		String data = this.getParam("data");
		if (StringUtil.isNotEmpty(data)) {
			ValNetValueConfirm004Dto dto = JsonUtils.json2Object(data, ValNetValueConfirm004Dto.class);
			model.addAttribute("entity", dto);
		}
		// 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {// 判断是否存在于工作流中
        	ValNetValueConfirm004Dto dto = valNetValueConfirm004Service.getBussinessEntityByWorkflowId(workflowId);
        	model.addAttribute("pageModel", dto.getPageModel());
            model.addAttribute("entity", dto);
        }
        model.addAttribute("profitTypeMoney", DictCodeList.收益类型_货币型.getValue());
		return super.init(model);
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
    public AjaxResult check(@RequestBody @Valid ValNetValueConfirm004Dto dto, BindingResult result, String operateType) {
        return commonCheck(result, operateType);
    }

    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br AjaxResult
     * @return AjaxResul
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ValNetValueConfirm004Dto dto, BindingResult br) throws Exception{
    	if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ValNetValueConfirm004Dto> processParameter = new ProcessParameter<ValNetValueConfirm004Dto>();
        WorkFlowParameter<ValNetValueConfirm004Dto> params = new WorkFlowParameter<ValNetValueConfirm004Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode("JZXG-" + dto.getFinprodId() + "-" + dto.getValDate());
        params.setBusinessType(DictCodeList.共通业务类型_净值下发确认.getValue());
        
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> "产品代码"+ "(" + entity.getFinprodId() + ")"
                +  "-" + "估值日期"+ "(" + entity.getValDate() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = valNetValueConfirm004Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
	
	
}
