package com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.controller;

import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePointDeleteDto;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePointDto;
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
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.service.RskPortAutoRelatePointDeleteService;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.service.RskPortAutoRelatePointService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("RskPortAutoRelatePoint003")
@RequestMapping("/invest/riskredis/quota/rskportautorelatepoint/RskPortAutoRelatePoint003")
public class RskPortAutoRelatePoint003Controller extends BaseController{
	
	@Autowired
	private RskPortAutoRelatePointService rskPortAutoRelatePointService;
	@Autowired
	private RskPortAutoRelatePointDeleteService rskPortAutoRelatePointDeleteService;
	@Override
    public String init(Model model) {
		String ruleId = RequestUtil.getParam("id");
		String ruleName = RequestUtil.getParam("ruleName");
		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
		if(workflowId != null && !StringUtil.equals(workflowId, "")){
			RskPortAutoRelatePointDto dto = rskPortAutoRelatePointService.getBussinessEntityByWorkflowId(workflowId);
			RskPortAutoRelatePointDto entity = new RskPortAutoRelatePointDto();
			entity.setRuleId(dto.getRuleId());
			entity.setWorkflowId(workflowId);
			model.addAttribute("entity", dto);
		}else if(StringUtil.isNotEmpty(ruleId)){
			RskPortAutoRelatePointDto dto = new RskPortAutoRelatePointDto();
			dto.setRuleId(ruleId);
			dto.setRuleName(ruleName);
			model.addAttribute("entity", dto);
		}
        model.addAttribute("NONE", BussinessDictCodeListRds.单位_无.getValue());
		this.setSelectOptionToModel(model, BussinessDictCodeRds.指标类型,BussinessDictCodeRds.单位,BussinessDictCodeRds.监督类型);
		return super.init(model);
	}
    /**
     * 规则修改查询数据
     * 
     * @return Page<PaRskCustomerRatingDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskPortAutoRelatePointDto> findAllInt()
        throws Exception {
        return rskPortAutoRelatePointService.findAllInt(
                RequestUtil.getPageParam());
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskPortAutoRelatePointDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskPortAutoRelatePointDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskPortAutoRelatePointDto> processParameter = new ProcessParameter<RskPortAutoRelatePointDto>();
        WorkFlowParameter<RskPortAutoRelatePointDto> params = new WorkFlowParameter<RskPortAutoRelatePointDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
      //修改时为画面所选记录的规则编号。
        params.setBusinessCode(dto.getRuleId());
        params.setBusinessType(BussinessDictCodeListRds.共同业务类型_组合自动关联指标.getValue());
     // 设置流程业务说明, entity即传入的dto
        // 业务说明：“规则编号：”+画面.规则编号t
        params.setComment(entity -> "规则编号:" + "(" + entity.getRuleId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = rskPortAutoRelatePointService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     *  页面删除
     * @param dto 费用品种
     * @param br br
     * @return 删除结果
     * @throws Exception Exception
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) RskPortAutoRelatePointDeleteDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskPortAutoRelatePointDeleteDto> processParameter = new ProcessParameter<RskPortAutoRelatePointDeleteDto>();
        WorkFlowParameter<RskPortAutoRelatePointDeleteDto> params = new WorkFlowParameter<RskPortAutoRelatePointDeleteDto>();
        params.setPageId("RskPortAutoRelatePoint003");
        params.setBusinessType(BussinessDictCodeListRds.共同业务类型_组合自动关联指标.getValue());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getIndId());
    	params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionPortfPoint002.indId"),
                GlobalConstant.OPEN_PARENTHESIS, entity.getIndId(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionPortfPoint002.indName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getIndName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskPortAutoRelatePointDeleteService.start(processParameter,
            dto.getSelectedItem().toArray(new RskPortAutoRelatePointDeleteDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
}
