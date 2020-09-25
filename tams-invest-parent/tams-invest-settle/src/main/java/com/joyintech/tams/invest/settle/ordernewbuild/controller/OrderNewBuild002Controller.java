package com.joyintech.tams.invest.settle.ordernewbuild.controller;


import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.settle.ordernewbuild.dto.OrderNewBuild002Dto;
import com.joyintech.tams.invest.settle.ordernewbuild.service.OrderNewBuild002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
* @author yaozhenguo
* @version 1.0 2019年10月29日 下午4:27:06
* @ClassName OrderNewBuild001Controller
* @Description 
*/
@Controller
@PageId("OrderNewBuild002")
@RequestMapping("/invest/settle/ordernewbuild/OrderNewBuild002")
public class OrderNewBuild002Controller extends BaseController{

	@Autowired
	private OrderNewBuild002Service orderNewBuild002Service;
	@Override
	
	public String init(Model model) {
		setSelectOptionToModel(model,DictCode.平安业务类型,DictCode.平安划款类型,DictCode.币种,DictCode.是否);
		model.addAttribute("CNY", DictCodeList.币种_人民币_CNY.getValue());
		model.addAttribute("platDate",DateUtil.platDateFromCache());
		String workflowId = this.getParam("workflowId");
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			OrderNewBuild002Dto entity = orderNewBuild002Service
					.getBussinessEntityByWorkflowId(workflowId);
			if(OperationType.REVOKE.toString().equalsIgnoreCase(entity.getOperateType())) {
				entity =  orderNewBuild002Service.selectByOrderId(entity.getActOrderId());
			}
			model.addAttribute(GlobalConstant.ENTITY, entity);
			if (entity.getBussTrdTransferOrder() != null) {
                model.addAttribute("stlPayOrdersList",
                    JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
            }
		}else {
			if(StringUtils.equals(this.getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY),
				GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
				OrderNewBuild002Dto entity = orderNewBuild002Service.selectByOrderId(this.getParam("actOrderId"));
				model.addAttribute(GlobalConstant.ENTITY, entity);
				if (entity.getBussTrdTransferOrder() != null) {
	                model.addAttribute("stlPayOrdersList",
	                    JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
	            }
			}
		}
		return super.init(model);
	}
	
	/**
	 * 查询所有清算账户
	 * @return
	 */
	@RequestMapping("/queryCapacctInfo")
	@ResponseBody
	public List<SelectOptionDto> queryCapacctInfo(){
		List<SelectOptionDto> capacctInfos = orderNewBuild002Service.queryCapacctInfo(RequestUtil.getPageParam());
		return capacctInfos;
	}
	
	/**
	 * check
	 * @param dto
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) OrderNewBuild002Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}
	
	/**
	 * 保存
	 * @param dto
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(@RequestBody OrderNewBuild002Dto dto, BindingResult br) throws Exception{
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		
		ProcessParameter<OrderNewBuild002Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<OrderNewBuild002Dto> params = new WorkFlowParameter<>();
		params.setPageId("OrderNewBuild002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getActOrderId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_指令新建.getValue());
		params.setComment(entity -> this.getMessage("OrderNewBuild002.orderId") + "("
				+ entity.getActOrderId() + ")-" + this.getMessage("OrderNewBuild002.settleDate") + "("
				+ DateUtil.format(entity.getSettleDate()) + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = orderNewBuild002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
}
