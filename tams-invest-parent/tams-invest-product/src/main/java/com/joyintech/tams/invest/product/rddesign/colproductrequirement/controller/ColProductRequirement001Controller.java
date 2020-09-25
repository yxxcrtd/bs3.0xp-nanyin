package com.joyintech.tams.invest.product.rddesign.colproductrequirement.controller;

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
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement001Dto;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement002Dto;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.service.ColProductRequirement001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 产品需求收集controller
 * 
 * @author yaozhenguo
 * @version 1.0 2018年8月21日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@Controller
@PageId(value = "ColProductRequirement001")
@RequestMapping("/invest/product/rddesign/colproductrequirement/ColProductRequirement001")
public class ColProductRequirement001Controller extends BaseController {

	@Autowired
	private ColProductRequirement001Service colProductRequirement001Service;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.有效状态, DictCode.是否, DictCode.需求发起端, DictCode.初评结果);
		model.addAttribute("requirementUser", SecurityUtil.getLoginUser().getUid());
		return super.init(model);
	}

	/**
	 * 页面查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectForPage")
	public Page<ColProductRequirement001Dto> selectForPage() {
		Page<ColProductRequirement001Dto> page = colProductRequirement001Service
				.selectForPage(RequestUtil.getPageParam());
		return page;
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
		return colProductRequirement001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}

	/**
	 * 删除按钮点击时的check
	 *
	 * @param dto
	 *            dto
	 * @param result
	 *            BindingResult
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) ColProductRequirement001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @param br
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupDelete.class }) ColProductRequirement001Dto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<ColProductRequirement001Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ColProductRequirement001Dto> params = new WorkFlowParameter<>();
		params.setPageId(dto.getPageId());
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getRequirementId());
		params.setBusinessType(DictCodeList.共通业务类型_产品需求收集.getValue());
		params.setComment(entity -> this.getMessage("ColProductRequirement001.requirementId") + "("
				+ entity.getRequirementId() + ")-" + this.getMessage("ColProductRequirement001.requirementTime") + "("
				+ entity.getRequirementTimeStr() + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = colProductRequirement001Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

	/**
	 * 发起初评
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("reviewStart")
	public AjaxResult reviewStart(@RequestBody ColProductRequirement001Dto colProductRequirement001Dto) {
		AjaxResult ar = new AjaxResult();
		List<ValidMessage> errorList = ar.getErrorList();
		if (StringUtil.equals(colProductRequirement001Dto.getReviewYesno(), DictCodeListExt1.是否_是.getValue())) {
			errorList.add(new ValidMessage(this.getMessage("ColProductRequirement001.review.yes")));
		}
		if (!StringUtil.equals(colProductRequirement001Dto.getDataStatus(), DictCodeListExt1.有效状态_有效.getValue())) {
			errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032", DictCodeListExt1.有效状态_有效.getCodeName())));
		}
		if (errorList.isEmpty()) {
			colProductRequirement001Service.reviewStart(colProductRequirement001Dto);
			return new AjaxResult(this.getMessage("ColProductRequirement001.review.success"));
		}else {
			ar.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			ar.setMessageType(MessageType.错误消息);
		}
		return ar;
	}
}
