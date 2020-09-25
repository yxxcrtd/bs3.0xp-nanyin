package com.joyintech.tams.invest.riskredis.quota.rskevent.contorller;

import javax.validation.Valid;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent002Dto;
import com.joyintech.tams.invest.riskredis.quota.rskevent.service.RskEvent002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 风险事件列表
 * 
 * @author 王朕
 * @version 1.0 2019年8月30日
 * @see RskEvent002Controller
 * @since 1.0
 */
@Controller
@PageId("RskEvent002")
@RequestMapping("/invest/riskredis/quota/rskevent/RskEvent002")
public class RskEvent002Controller extends BaseController {

	@Autowired
	private RskEvent002Service rskEvent002Service;

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {

		// 初始化选项字典数据
		this.setSelectOptionToModel(model, BussinessDictCodeRds.操作符, BussinessDictCodeRds.运算符,
				BussinessDictCodeRds.风险事件特征值类型, BussinessDictCodeRds.特征值偏移单位);
		RskEvent002Dto rskEvent002Dto = null;
		// 获取工作流Id
		String workflowId = getParam("workflowId");
		// 待办跳转情况下进入页面需要注入workflowId并从工作流中获取注入信息
		if (StringUtil.isNotEmpty(workflowId) && !WorkFlowUtil.isNotFromToDoList()) {
			model.addAttribute("workflowId", workflowId);
			rskEvent002Dto = rskEvent002Service.getBussinessEntityByWorkflowId(workflowId);
		} else {
			// 非待办跳转
			// 查询加载页面内容
			if (!getParam("pageModel").equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
				// 获取风险事件代码
				String eventCode = getParam("eventCode");
				// 获取风险事件及特征值信息
				rskEvent002Dto = rskEvent002Service.selectEventDetailInfoByCode(eventCode);
			}
		}
		model.addAttribute("entity", rskEvent002Dto);
		return super.init(model);
	}

	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid RskEvent002Dto dto, BindingResult br) {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		AjaxResult ar = new AjaxResult();
		String btnName = "";
		if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE, dto.getOperateType())) {
			btnName = "SysCommon.save";
		} else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, dto.getOperateType())) {
			btnName = "SysCommon.submit";
		}
		ar.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
		return ar;
	}

	@DTXTransaction
	@ResponseBody
	@RequestMapping(PAGE_SAVE)
	public AjaxResult save(@RequestBody RskEvent002Dto dto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return new AjaxResult(result);
		}
		// 当新建操作时，生成主键
		if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())&&StringUtil.isEmpty(dto.getEventCode())) {
			dto.setEventCode(IDGenerator.generate());
		}
		// 业务代码生成参数
		ProcessParameter<RskEvent002Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<RskEvent002Dto> params = new WorkFlowParameter<>();
		params.setPageId(dto.getPageId());
		params.setElementId(dto.getElementId());
		// BussinessCodeParameter parameter = new BussinessCodeParameter();
		// 风险事件代码作为业务编号

		params.setBusinessCode(item -> item == null ? StringUtil.EMPTY : item.getEventCode());
		params.setBusinessType(BussinessDictCodeListRds.共同业务类型_风险事件管理.getValue());

		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> "风控事项:" + "("+ entity.getEventCode()+"-"+entity.getEventName()+")"
		/*WorkFlowUtil.concatComment(getMessage("RskEvent002.eventName"),
				GlobalConstant.OPEN_PARENTHESIS, entity.getEventName(), GlobalConstant.CLOSE_PARENTHESIS)*/
		);
		processParameter.setWorkFlowParameter(params);
		String msg = rskEvent002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

}
