package com.joyintech.tams.invest.riskredis.quota.rskevent.contorller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent003Dto;
import com.joyintech.tams.invest.riskredis.quota.rskevent.service.RskEvent003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 风险事件列表
 * 
 * @author 裴家俊
 * @version 1.0 2019年9月16日
 * @see RskEvent003Controller
 * @since 1.0
 */
@Controller
@PageId("RskEvent003")
@RequestMapping("/invest/riskredis/quota/rskevent/RskEvent003")
public class RskEvent003Controller extends BaseController {

	@Autowired
	private RskEvent003Service rskEvent003Service;

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {

		// 初始化选项字典数据
		this.setSelectOptionToModel(model, BussinessDictCodeRds.操作符, BussinessDictCodeRds.参数粒度, BussinessDictCodeRds.风险事件运算关系对象);
        List<SelectOptionDto> paramList=rskEvent003Service.getIndicatorParamList();
		if(!StringUtil.isEmptyList(paramList)){
			paramList.forEach(obj->obj.setObject(null));
		}
        model.addAttribute("IND_PARAM", JsonUtils.objectArray2Json(paramList.toArray()));
		RskEvent003Dto rskEvent003Dto = null;
		// 获取工作流Id
		String workflowId = getParam("workflowId");
		// 待办跳转情况下进入页面需要注入workflowId并从工作流中获取注入信息
		if (StringUtil.isNotEmpty(workflowId) && !WorkFlowUtil.isNotFromToDoList()) {
			model.addAttribute("workflowId", workflowId);
			rskEvent003Dto = rskEvent003Service.getBussinessEntityByWorkflowId(workflowId);
		} else {
			// 非待办跳转
			// 查询加载页面内容
			if (!getParam("pageModel").equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
				// 获取风险事件代码
				String eventCode = getParam("eventCode");
				// 获取风险事件关联指标参数
				rskEvent003Dto = rskEvent003Service.selectEventRelParaByCode(eventCode)==null?new RskEvent003Dto():rskEvent003Service.selectEventRelParaByCode(eventCode);
			}
			if(StringUtil.isNotEmpty(this.getParam("eventCode"))){
				rskEvent003Dto.setEventCode(this.getParam("eventCode"));
				rskEvent003Dto.setEventName(this.getParam("eventName"));
			}
		}
		model.addAttribute("entity", rskEvent003Dto);
		return "invest/riskredis/quota/rskevent/RskEvent003";
	}

	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid RskEvent003Dto dto, BindingResult br) {
		return commonCheck(br, dto.getOperateType());
	}

	@DTXTransaction
	@ResponseBody
	@RequestMapping(PAGE_SAVE)
	public AjaxResult save(@RequestBody RskEvent003Dto dto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return new AjaxResult(result);
		}
		// 当新建操作时，生成主键
		if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
			dto.setEventCode(IDGenerator.generate());
		}
		// 业务代码生成参数
		ProcessParameter<RskEvent003Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<RskEvent003Dto> params = new WorkFlowParameter<>();
		params.setPageId(dto.getPageId());
		params.setElementId(dto.getElementId());
		// 风险事件代码作为业务编号

		params.setBusinessCode(item -> item == null ? StringUtil.EMPTY : item.getEventCode());
		params.setBusinessType(BussinessDictCodeListRds.共同业务类型_风险事件关联指标参数.getValue());

		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> "风控事项:" + "("+ entity.getEventName()+")");
		processParameter.setWorkFlowParameter(params);
		String msg = rskEvent003Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

}
