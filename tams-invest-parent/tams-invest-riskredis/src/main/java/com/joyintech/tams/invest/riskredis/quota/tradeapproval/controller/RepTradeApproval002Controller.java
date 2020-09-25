package com.joyintech.tams.invest.riskredis.quota.tradeapproval.controller;

import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.tradeapproval.dto.RepTradeApprovalDto;
import com.joyintech.tams.invest.riskredis.quota.tradeapproval.service.RepTradeApprovalService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * @program: tams-invest-parent
 * @description: RepTradeApproval002
 * @author: liqianqian
 * @create: 2019-09-18 14:29
 **/
@Controller
@PageId("RepTradeApproval002")
@RequestMapping("/invest/riskredis/quota/tradeapproval/RepTradeApproval002")
public class RepTradeApproval002Controller extends BaseController  {

    /**
     * 交易代码查询service
     */
    @Autowired
    private RepTradeApprovalService repTradeApprovalService;
    
    /**
	 * 初始化的查询条件
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model, BussinessDictCodeRds.单位,BussinessDictCodeRds.交易方向,BussinessDictCodeRds.交易流通场所);
		String eventId = this.getParam("eventId");
		String workflowId=getParam(GlobalConstant.WORKFLOW_ID);
		if (StringUtil.isNotEmpty(workflowId)) {
			RepTradeApprovalDto dto = repTradeApprovalService.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("entity", dto);
		}
		if (StringUtil.isNotEmpty(eventId)) {
			RepTradeApprovalDto repTradeApprovalDto = repTradeApprovalService.selectInfo(eventId);
			model.addAttribute("entity", repTradeApprovalDto);
			model.addAttribute("eventId", this.getParam("eventId"));
		}
	     return super.init(model);
	}

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepTradeApprovalDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RepTradeApprovalDto> page = repTradeApprovalService.selectForPageDel(map);
        return page;
    }

    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) RepTradeApprovalDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RepTradeApprovalDto> processParameter = new ProcessParameter<RepTradeApprovalDto>();
        WorkFlowParameter<RepTradeApprovalDto> params = new WorkFlowParameter<RepTradeApprovalDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
      //修改时为画面所选记录的交易编号。
        params.setBusinessCode(dto.getEventId());
       params.setBusinessType(BussinessDictCodeListRds.共同业务类型_风险审批.getValue());
     // 设置流程业务说明, entity即传入的dto
        // 业务说明：“交易编号：”+画面.交易编号t
        params.setComment(entity -> "风险审批 交易代码:" + "(" + entity.getEventId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = repTradeApprovalService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
