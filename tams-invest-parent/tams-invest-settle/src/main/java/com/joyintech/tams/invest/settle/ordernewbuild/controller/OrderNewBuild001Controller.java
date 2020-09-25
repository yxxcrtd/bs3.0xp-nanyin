package com.joyintech.tams.invest.settle.ordernewbuild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.settle.ordernewbuild.dto.OrderNewBuild001Dto;
import com.joyintech.tams.invest.settle.ordernewbuild.service.OrderNewBuild001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
* @author yaozhenguo
* @version 1.0 2019年10月29日 下午4:27:06
* @ClassName OrderNewBuild001Controller
* @Description 
*/
@Controller
@PageId("OrderNewBuild001")
@RequestMapping("/invest/settle/ordernewbuild/OrderNewBuild001")
public class OrderNewBuild001Controller extends BaseController{
	
	@Autowired
	private OrderNewBuild001Service orderNewBuild001Service;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.平安划款类型, DictCode.平安业务类型,
        DictCode.指令处理状态, DictCode.成功失败标志,DictCode.发送状态);
		model.addAttribute("sendStatus_success", DictCodeList.发送状态_发送成功.getValue());
		model.addAttribute("orderStatus_new",DictCodeListExt1.指令状态_新建.getValue());
		model.addAttribute("orderStatus_revoke",DictCodeListExt1.指令状态_撤销.getValue());
		return super.init(model);
	}
	
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<OrderNewBuild001Dto> selectForPage(){
		return orderNewBuild001Service.selectForPage(RequestUtil.getPageParam());
	}
	
	/**
	 * check
	 * @param dto
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) OrderNewBuild001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}
	
	/**
	 * delete
	 * @param dto
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/revoke")
	public AjaxResult delete(@RequestBody OrderNewBuild001Dto dto, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<OrderNewBuild001Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<OrderNewBuild001Dto> params = new WorkFlowParameter<>();
		params.setPageId(dto.getPageId());
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getActOrderId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_指令新建.getValue());
		params.setComment(entity -> this.getMessage("OrderNewBuild002.orderId") + "("
				+ entity.getActOrderId() + ")-" + this.getMessage("OrderNewBuild002.settleDate") + "("
				+ DateUtil.format(entity.getSettleDate()) + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = orderNewBuild001Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
	
	/**
	 * 指令发送
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("send")
	public AjaxResult send(@RequestBody OrderNewBuild001Dto dto) {
		AjaxResult ar = new AjaxResult();
		List<ValidMessage> errorList = ar.getErrorList();
		if(StringUtil.equals(dto.getSendStatus(), DictCodeList.发送状态_发送成功.getValue())) {
			errorList.add(new ValidMessage(this.getMessage("OrderNewBuild001.send.error")));
		}
		if(errorList.isEmpty()) {
			return new AjaxResult(this.getMessage("StlOrderManagement001_E_001"));
		}else {
			ar.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			ar.setMessageType(MessageType.错误消息);
		}
		return ar;
	}
	
	/**
	 * 指令查验
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("verify")
	public AjaxResult verify(@RequestBody OrderNewBuild001Dto dto) {
		AjaxResult ar = new AjaxResult();
		List<ValidMessage> errorList = ar.getErrorList();
		if(errorList.isEmpty()) {
			return new AjaxResult(this.getMessage("OrderNewBuild001.verify.success"));
		}else {
			ar.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			ar.setMessageType(MessageType.错误消息);
		}
		return ar;
	}
	
	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return orderNewBuild001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}
}
